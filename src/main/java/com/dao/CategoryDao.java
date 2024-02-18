package com.dao;

import java.util.List;

import com.entity.Category;

public class CategoryDao extends JpaDao<Category> implements GenericDao<Category> {

	@Override
	public Category create(Category cat) {
		return super.create(cat);
	}

	@Override
	public Category update(Category cat) {
		return super.update(cat);
	}

	@Override
	public Category get(Object id) {
		return super.get(Category.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Category.class, id);
	}

	@Override
	public List<Category> listAll() {
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Category.countAll");
	}
	
	public Category findByName(String name) {
		List<Category> cat= super.findWithNamedQuery("Category.findByName","name",name);
		if(cat!=null && cat.size()>0) {
			return cat.get(0);
		}
		return null;
	}
}
