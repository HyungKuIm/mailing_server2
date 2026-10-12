package com.oraclejava.domain.service;

import java.util.List;

import com.oraclejava.domain.model.Member;

public interface MemberService {
	//List<Member> getMemberMail(int jobId);
	List<Member> getMemberMail(int jobId, String uid);
}
