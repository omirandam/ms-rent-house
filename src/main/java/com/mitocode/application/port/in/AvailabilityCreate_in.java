package com.mitocode.application.port.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.dto.AvailabilityDto;
import com.mitocode.application.port.out.AvailabilityCreate_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class AvailabilityCreate_in implements IAvalabilityCreate_in {
	
	@Autowired
	private AvailabilityCreate_out availabilityCreate_out;
	
	@Override
	public void create(Integer id_house, AvailabilityDto availabilityDto) {
		try {
			availabilityCreate_out.create(id_house, availabilityDto.toEntity());
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}
	
}
