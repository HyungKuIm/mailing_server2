package com.oraclejava.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oraclejava.domain.model.Member;
import com.oraclejava.domain.repository.MemberRepository;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public List<Member> getMemberMail(int jobId, String uid) {
		switch (jobId) {
		case 2:	//총회원 
			
			return memberRepository.findAllMemberMail();
		case 3:   // 기업회원
			return memberRepository.findAllCompanyMemberMail();
		case 4:	//직원메일 
			
			return memberRepository.findStaffMemberMail();
		case 5:	//테스트메일(임형구)
			
			return memberRepository.findTestMemberMail();
			
		case 6:   // 개별 메일 전송
			return memberRepository.findByUidMemberMail(uid);
		default:
			return null;
		}

	}
	
}
