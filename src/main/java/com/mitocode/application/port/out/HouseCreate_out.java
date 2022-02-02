package com.mitocode.application.port.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.repository.HouseRepository;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class HouseCreate_out implements IHouseCreate_out {
	
	@Autowired
	private HouseRepository houseRepository;
	

	@Override
	public void create(House house) {
		try {
			houseRepository.save(house);
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}
	
}
