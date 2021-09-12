package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.UserDao;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserDao userDao;

	@PostMapping("/users")
	public ResponseBean<UserBean> insert(@RequestBody UserBean uBean){
		UserBean iBean = userDao.insert(uBean);
		ResponseBean<UserBean> res = new ResponseBean<>();
		if(iBean==null) {
			res.setStatus(-1);
			res.setMsg("invalid cradantial");
			res.setBean(iBean);
		}
		else {
			res.setStatus(1);
			res.setMsg("User successfully signup");
			res.setBean(iBean);
		}
		return res;
	}
	@GetMapping("/users")
	public ResponseBean<List<UserBean>> getAll(){
		List<UserBean> listBean = userDao.getAll();
		ResponseBean<List<UserBean>> res = new ResponseBean<>();
		if(listBean==null) {
			res.setStatus(-1);
			res.setMsg("users not found!");
		}
		else {
			res.setStatus(1);
			res.setMsg("All users are arrived!");
		}
		res.setBean(listBean);
		return res;
	}
	
	@GetMapping("/user")
	public ResponseBean<UserBean> getById(@RequestBody UserBean uBean){
		UserBean listBean = userDao.getByToken(uBean);
		ResponseBean<UserBean> res = new ResponseBean<>();
		if(listBean==null) {
			res.setStatus(-1);
			res.setMsg("users not found!");
		}
		else {
			res.setStatus(1);
			res.setMsg("All users are arrived!");
		}
		res.setBean(listBean);
		return res;
	}
	
	
	
	@PutMapping("/users")
	public ResponseBean<UserBean> update(@RequestBody UserBean uBean){
		UserBean iBean = userDao.update(uBean);
		ResponseBean<UserBean> res = new ResponseBean<>();
		if(iBean==null) {
			res.setStatus(-1);
			res.setMsg("invalid cradantial");
			res.setBean(iBean);
		}
		else {
			res.setStatus(1);
			res.setMsg("User successfully updated!");
			res.setBean(iBean);
		}
		return res;
		
	}
	@DeleteMapping("/users")
	public ResponseBean<UserBean> delete(@RequestBody UserBean uBean){
		UserBean iBean = userDao.delete(uBean);
		ResponseBean<UserBean> res = new ResponseBean<>();
		if(iBean==null) {
			res.setStatus(-1);
			res.setMsg("invalid cradantial");
			res.setBean(iBean);
		}
		else {
			res.setStatus(1);
			res.setMsg("User successfully deleted!");
			res.setBean(iBean);
		}
		return res;
		
	}
	
	
	
}
