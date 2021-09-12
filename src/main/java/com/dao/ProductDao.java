package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.ProductBean;

@Repository
public class ProductDao {

	@Autowired
	JdbcTemplate stmt;
	
	public ProductBean insert(ProductBean bean) {
		int affectedRows = stmt.update("insert into products(name,qty,price,details) values(?,?,?,?)",bean.getName(),bean.getQty(),bean.getPrice(),bean.getDetails());
		if(affectedRows > 0) {
			bean = (ProductBean) stmt.query("select * from product order by id desc limit(1)", new BeanPropertyRowMapper<ProductBean>(ProductBean.class));
		}else {
			bean =null;
		}
		return bean;
	}

	public List<ProductBean> getAll() {
		List<ProductBean> beans = stmt.query("Select * from products ", new BeanPropertyRowMapper<ProductBean>(ProductBean.class));
		return beans;
	}

	public ProductBean getById(ProductBean bean) {
		ProductBean rbean = stmt.queryForObject("Select * from products where id=? ", new BeanPropertyRowMapper<ProductBean>(ProductBean.class),bean.getId());
		return rbean;
	}
	
	public ProductBean updateById(ProductBean bean){
		
		int affectedRow = stmt.update("update products set name=?, qty=?, details=?, price=? where id=?",bean.getName(),bean.getQty(),bean.getDetails(),bean.getPrice(),bean.getId());
		if(affectedRow>0) {
			return stmt.queryForObject("Select * from products where id=? ", new BeanPropertyRowMapper<ProductBean>(ProductBean.class),bean.getId());
		}
		return null;
	}
	
	public boolean updateQty(ProductBean bean) {
		int affectedRow = stmt.update("update products set qty=? where id=?",bean.getQty(),bean.getId());
		if(affectedRow>0) {
			return true;
		}
		return false;
	}
}
