package com.javabuster.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javabuster.dao.ProfileDao;
import com.javabuster.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileDao profileDao;
	
	@Override
	public String testProfile() {
		return profileDao.testProfile();
		
	}

}
