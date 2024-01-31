package com.library.books.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.books.AdminEntity;
import com.library.books.repository.AdminRepository;

@Service
public class AdminService {
	private static Logger logger = LoggerFactory.getLogger(AdminService.class);
	@Autowired
	AdminRepository adminRepository;

	public String signupService(AdminEntity adminEntity) {
		adminRepository.save(adminEntity);
		return adminEntity.getName();
	}

	public String loginService(String email, String pswd) {
		if (adminRepository.existsById(email)) {
			AdminEntity adminEntity = adminRepository.findById(email).get();
			// System.out.println(adminEntity);
			if (adminEntity.getPswd().equals(pswd)) {
				return adminEntity.getName();
			}

		}
		return null;
	}

	public int forgotpasswordservice(String email, String pswd) {
		if (adminRepository.existsById(email)) {
			adminRepository.updatePasswordByEmail(email, pswd);
			return 1;

		}
		return 0;

	}
}
