package com.mitocode.application.port.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.application.port.out.IAvalabilityDelete_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class AvailabilityDelete_in implements IAvalabilityDelete_in {
	
	@Autowired
	private IAvalabilityDelete_out avalabilityDelete_out;
	
	@Override
	public void delete(Integer id_availability) {
		try {
			avalabilityDelete_out.delete(id_availability);
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}
	
}
