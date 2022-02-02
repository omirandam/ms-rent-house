package com.mitocode.application.port.out;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.repository.HouseRepository;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class HouseFind_out implements IHouseFind_out {
	
	@Autowired
	private HouseRepository houseRepository;
	
	@Override
	public House find(Integer id){
		Optional<House>optional = houseRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		else
			throw new ResourceNotFoundException("There is no house with that id.");
	}
	

}
