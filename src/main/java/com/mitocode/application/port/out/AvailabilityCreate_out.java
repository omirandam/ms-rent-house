package com.mitocode.application.port.out;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.entity.Availability;
import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.repository.HouseRepository;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class AvailabilityCreate_out implements IAvalabilityCreate_out {
	
	@Autowired
	private HouseRepository houseRepository;
	
	@Override
	public void create(Integer id_house, Availability availability) {
		try {
			Optional<House>optionalHouse = houseRepository.findById(id_house);
			if(optionalHouse.isPresent()) {
				House house = optionalHouse.get();
				house.availabilitys.add(availability);
				houseRepository.save(house);
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
