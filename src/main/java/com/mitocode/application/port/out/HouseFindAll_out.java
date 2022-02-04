package com.mitocode.application.port.out;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.repository.HouseRepository;

@Service
public class HouseFindAll_out implements IHouseFindAll_out {
	
	@Autowired
	private HouseRepository houseRepository;
	
	public HouseFindAll_out(HouseRepository houseRepository) {
		this.houseRepository = houseRepository;
	}
	
	@Override
	public List<House> findAll(){
		return houseRepository.findAll();
	}

}
