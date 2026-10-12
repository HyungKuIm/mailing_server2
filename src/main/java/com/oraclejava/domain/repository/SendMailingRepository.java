package com.oraclejava.domain.repository;

import java.util.List;

import com.oraclejava.domain.model.SendMailing;

public interface SendMailingRepository {
	List<SendMailing> findImmediateMails();
	boolean updateMailing(SendMailing sendMailing);
}
