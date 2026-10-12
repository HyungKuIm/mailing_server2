package com.oraclejava.mailing_server;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import com.google.common.util.concurrent.RateLimiter;
import com.oraclejava.domain.config.AppConfig;
import com.oraclejava.domain.model.Member;
import com.oraclejava.domain.model.SendMailing;
import com.oraclejava.domain.service.MemberService;
import com.oraclejava.domain.service.SendMailingService;

public class SendMailMain {

    private static final Logger logger = LoggerFactory.getLogger(SendMailMain.class);
    private static final AtomicBoolean running = new AtomicBoolean(false);

    // ⭐ 핵심: 전체 발송 속도 제한 (초당 10건)
    private static final RateLimiter rateLimiter = RateLimiter.create(12.0);

    // ⭐ 핵심: 병렬 처리 (6개 스레드)
    private static final ExecutorService executor = Executors.newFixedThreadPool(12);

    public static void main(String[] args) throws IOException {

        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        MailMail m = ctx.getBean("mailMail", MailMail.class);
        SendMailingService sms = ctx.getBean(SendMailingService.class);
        MemberService ms = ctx.getBean(MemberService.class);

        TaskScheduler scheduler = ctx.getBean(TaskScheduler.class);

        scheduler.schedule(() -> {

            if (!running.compareAndSet(false, true)) {
                logger.warn("이미 실행 중 → skip");
                return;
            }

            try {
                logger.info("메일 발송 시작: {}", new Date());
                long start = System.currentTimeMillis();

                List<SendMailing> sendMailingList = sms.getImmediateMails();

                for (SendMailing mail : sendMailingList) {

                    List<Member> memList = ms.getMemberMail(mail.getJobId(), mail.getUid());
                    if (memList == null || memList.isEmpty()) continue;

                    AtomicInteger success = new AtomicInteger();
                    AtomicInteger fail = new AtomicInteger();

                    CountDownLatch latch = new CountDownLatch(memList.size());

                    for (Member member : memList) {

                        if (!isValid(member.getEmail())) {
                            latch.countDown();
                            continue;
                        }

                        executor.submit(() -> {
                            try {
                                // ⭐ 전역 속도 제한
                                rateLimiter.acquire();

                                boolean result = m.sendMail(
                                        mail.getFromEmailAddr(),
                                        member.getEmail(),
                                        mail.getTitle(),
                                        mail.getBody()
                                );

                                if (result) {
                                    success.incrementAndGet();
                                } else {
                                    //throw new RuntimeException("send fail");
                                	fail.incrementAndGet();
                                   logger.warn("발송 실패: {}", member.getEmail());
                                }

                            } catch (Exception e) {
                                fail.incrementAndGet();
                                logger.warn("발송 오류 - skip: {}", member.getEmail(), e);

                            } finally {
                                latch.countDown();
                            }
                        });
                    }

                    // ⭐ 전체 완료 대기
                    //latch.await();
                 boolean completed = latch.await(40, TimeUnit.MINUTES);

                 if (!completed) {
                        logger.warn("40분 내 완료되지 않은 메일 작업 존재");
                    }

                    // ⭐ 상태 업데이트
                    sms.updateMailing(mail);

                    logger.info("제목: {}, 성공: {}, 실패: {}",
                            mail.getTitle(), success.get(), fail.get());
                }

                long end = System.currentTimeMillis();
                logger.info("총 소요 시간: {}초", (end - start) / 1000.0);

            } catch (Exception e) {
                logger.error("메일 발송 전체 실패", e);
            } finally {
                running.set(false);
            }

        }, new CronTrigger("0 * * * * ?"));

        System.in.read();
        ctx.close();
    }

    private static boolean isValid(String email) {
        if (email == null) return false;

        email = email.trim();

        if (email.length() > 254) return false;

        // @는 반드시 1개
        if (email.chars().filter(c -> c == '@').count() != 1) {
            return false;
        }

        String emailRegex =
                "^[A-Za-z0-9._%+-]+@" +
                "([A-Za-z0-9-]+\\.)+" +
                "[A-Za-z]{2,}$";

        if (!Pattern.matches(emailRegex, email)) {
            return false;
        }

        String domain = email.substring(email.indexOf('@') + 1);

        // 도메인 마지막 부분 확인
        String tld = domain.substring(domain.lastIndexOf('.') + 1);

        // .4, .123 같은 숫자 TLD 차단
        if (!tld.matches("[A-Za-z]{2,}")) {
            return false;
        }

        // 더미 도메인 차단 예시
        String lower = email.toLowerCase();
        if (lower.endsWith("@test.com")
                || lower.endsWith("@example.com")
                || lower.endsWith("@dummy.com")) {
            return false;
        }

        return true;
    }
}
