package com.mitocode.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.application.port.in.IAvalabilityDelete_in;
import com.mitocode.application.port.out.Avalability_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class AvailabilityDeleteUseCase implements IAvalabilityDelete_in {
	
	@Autowired
	private Avalability_out avalability_out;
	
	public AvailabilityDeleteUseCase(Avalability_out avalability_out) {
		this.avalability_out = avalability_out;
	}

	@Override
	public void delete(Integer id_availability) {
		try {
			avalability_out.delete(id_availability);
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}
	
}
