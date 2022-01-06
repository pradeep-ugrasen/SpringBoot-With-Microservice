package com.javabuster.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javabuster.dao.ProfileDao;

@Repository
public class ProfileDaoImpl implements ProfileDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String testProfile() {
		
		String msg = "Error";
		String sql = "INSERT INTO testuser (name, email, password) VALUES (?, ?, ?)";
        
        int result = jdbcTemplate.update(sql, "Pradeep", "pradeep@gmail.com", "123");
         
        if (result > 0) {
        	msg = "Data inserted";
        }
        return msg;
	}

}
