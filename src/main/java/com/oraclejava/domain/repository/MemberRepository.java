package com.oraclejava.domain.repository;

import java.util.List;

import com.oraclejava.domain.model.Member;

public interface MemberRepository {
	List<Member> findAllMemberMail();
	List<Member> findAllCompanyMemberMail();
	List<Member> findStaffMemberMail();
	List<Member> findTestMemberMail();
	List<Member> findByUidMemberMail(String uid);
}
