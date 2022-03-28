package com.renthouse.omar.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renthouse.omar.application.port.in.IAvalabilityDelete_in;
import com.renthouse.omar.application.port.out.Avalability_out;
import com.renthouse.omar.config.exception.InternalServerErrorException;
import com.renthouse.omar.config.exception.ResourceNotFoundException;

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
