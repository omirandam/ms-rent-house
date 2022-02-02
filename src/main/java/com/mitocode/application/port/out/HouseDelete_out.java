package com.mitocode.application.port.out;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.repository.HouseRepository;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class HouseDelete_out implements IHouseDelete_out {
	
	@Autowired
	private HouseRepository houseRepository;
	
	@Override
	public void delete(Integer id) {
		try {
			Optional<House>optionalHouse = houseRepository.findById(id);
			if(optionalHouse.isPresent()) {
				houseRepository.delete(optionalHouse.get());
			}
			else
				throw new ResourceNotFoundException("There is no house with that id.");
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}
	
}
