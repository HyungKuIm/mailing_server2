package com.oraclejava.domain.service;

import java.util.List;

import com.oraclejava.domain.model.SendMailing;

public interface SendMailingService {
	List<SendMailing> getImmediateMails();
	boolean updateMailing(SendMailing sendMailing);
}
