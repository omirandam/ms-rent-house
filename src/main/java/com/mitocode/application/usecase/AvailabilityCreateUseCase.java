package com.mitocode.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.dto.AvailabilityDto;
import com.mitocode.application.port.in.IAvalabilityCreate_in;
import com.mitocode.application.port.out.Avalability_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class AvailabilityCreateUseCase implements IAvalabilityCreate_in {
	
	@Autowired
	private Avalability_out avalability_out;
	
	public AvailabilityCreateUseCase(Avalability_out avalability_out) {
		super();
		this.avalability_out = avalability_out;
	}

	@Override
	public void create(Integer id_house, AvailabilityDto availabilityDto) {
		try {
			avalability_out.create(id_house, availabilityDto.toEntity());
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}
	
}
