package com.oraclejava.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oraclejava.domain.model.SendMailing;
import com.oraclejava.domain.repository.SendMailingRepository;

@Transactional
@Service
public class SendMailingServiceImpl implements SendMailingService {
	
	@Autowired
	private SendMailingRepository sendMailingRepository;
	
	@Override
	public List<SendMailing> getImmediateMails() {
		return sendMailingRepository.findImmediateMails();
	}

	@Override
	public boolean updateMailing(SendMailing sendMailing) {
		return sendMailingRepository.updateMailing(sendMailing);
	}
}
