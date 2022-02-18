package com.mitocode.application.port.out;

import java.util.List;

import com.mitocode.adapter.entity.House;

public interface House_out {

	public List<House> findAll();
	
	public void create(House house);

	public void delete(Integer id);
	
	public House find(Integer id);

	public void update(Integer id, House house);
}
