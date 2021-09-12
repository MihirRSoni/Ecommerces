package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate stmt;
	
	public UserBean insert(UserBean uBean){
		
		String alphanumerics = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
		
		StringBuilder token = new StringBuilder();
		for(int i =0;i<24;i++) {
			token.append(alphanumerics.charAt((int)(Math.random()*alphanumerics.length())));
		}
		uBean.setToken(token.toString());
		int affectedRows = stmt.update("insert into users(name,email,password,token) values(?,?,?,?)",uBean.getName(),uBean.getEmail(),uBean.getPassword(),token.toString());
		if(affectedRows>0) {
			return uBean;
		}
		return null;
	}
	
	public List<UserBean> getAll(){
		List<UserBean> beans = stmt.query("Select * from users ", new BeanPropertyRowMapper<UserBean>(UserBean.class));
		return beans;
	}

	public UserBean update(UserBean uBean) {
		int affectedRows = stmt.update("update users set name=? where token=?",uBean.getName(),uBean.getToken());
		if(affectedRows>0) {
			return uBean;
		}
		return null;
	}

	public UserBean delete(UserBean uBean) {
		int affectedRows = stmt.update("delete from users where token=?",uBean.getToken());
		if(affectedRows>0) {
			return uBean;
		}
		return null;
	}

	public UserBean getById(UserBean uBean) {
		UserBean bean = stmt.queryForObject("Select * from users where id = ? ", new BeanPropertyRowMapper<UserBean>(UserBean.class),uBean.getId());
		return bean;
	}

	public UserBean getByToken(UserBean uBean) {
		UserBean bean = stmt.queryForObject("Select * from users where token = ? ", new BeanPropertyRowMapper<UserBean>(UserBean.class),uBean.getToken());
		return bean;
	}
	public UserBean getCustomerByToken(String token) {
		UserBean customer = null;

		try {
			customer = stmt.queryForObject("select * from users where token like ? ",
					new BeanPropertyRowMapper<UserBean>(UserBean.class), token);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return customer;
	}
}
