package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ProductBean;
import com.bean.ResponseBean;
import com.dao.ProductDao;

@RestController
public class ProductController {

	@Autowired
	ProductDao productDao;
	
	@PostMapping("/products")
	public ResponseBean<ProductBean> insert(@RequestBody ProductBean bean){
		ResponseBean<ProductBean> res = new ResponseBean<>();
		ProductBean bean2 = productDao.insert(bean);
		if(bean2==null) {
			res.setStatus(-1);
			res.setMsg("invalid cradantial");
			res.setBean(bean2);
		}
		else {
			res.setStatus(1);
			res.setMsg("product successfully inserted");
			res.setBean(bean2);
		}
		return res;
	}
	
	@GetMapping("/products")
	public ResponseBean<List<ProductBean>> getAll(){
		ResponseBean<List<ProductBean>> res = new ResponseBean<>();
		List<ProductBean> bean2 = productDao.getAll();
		if(bean2==null) {
			res.setStatus(-1);
			res.setMsg("invalid cradantial");
			res.setBean(bean2);
		}
		else {
			res.setStatus(1);
			res.setMsg("product successfully inserted");
			res.setBean(bean2);
		}
		return res;
	}
	
	@GetMapping("/product")
	public ResponseBean<ProductBean> getById(@RequestBody ProductBean bean){
		ResponseBean<ProductBean> res = new ResponseBean<>();
		ProductBean bean2 = productDao.getById(bean);
		if(bean2==null) {
			res.setStatus(-1);
			res.setMsg("invalid cradantial");
			res.setBean(bean2);
		}
		else {
			res.setStatus(1);
			res.setMsg("product successfully inserted");
			res.setBean(bean2);
		}
		return res;
	}
	
	@PutMapping("/products")
	public ResponseBean<ProductBean> update(@RequestBody ProductBean bean){
		ResponseBean<ProductBean> res = new ResponseBean<>();
		ProductBean bean2 = productDao.updateById(bean);
		if(bean2==null) {
			res.setStatus(-1);
			res.setMsg("invalid cradantial");
			res.setBean(bean2);
		}
		else {
			res.setStatus(1);
			res.setMsg("product successfully inserted");
			res.setBean(bean2);
		}
		return res;
	}
	
	@DeleteMapping("/products")
	public ResponseBean<ProductBean> delete(@RequestBody ProductBean bean){
		ResponseBean<ProductBean> res = new ResponseBean<>();
		ProductBean bean2 = productDao.insert(bean);
		if(bean2==null) {
			res.setStatus(-1);
			res.setMsg("invalid cradantial");
			res.setBean(bean2);
		}
		else {
			res.setStatus(1);
			res.setMsg("product successfully inserted");
			res.setBean(bean2);
		}
		return res;
	}
}
