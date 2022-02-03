package com.mitocode.application.port.out;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.entity.Availability;
import com.mitocode.adapter.repository.AvailabilityRepository;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class AvailabilityDelete_out implements IAvalabilityDelete_out {
	
	@Autowired
	private AvailabilityRepository availabilityRepository;
	
	@Override
	public void delete(Integer id_availability) {
		try {
			Optional<Availability>optionalAvailability = availabilityRepository.findById(id_availability);
			if(optionalAvailability.isPresent()) {
				availabilityRepository.delete(optionalAvailability.get());
			}
			else
				throw new ResourceNotFoundException("There is no availability with that id.");
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}
	
}
