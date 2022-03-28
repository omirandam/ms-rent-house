package com.renthouse.omar.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renthouse.omar.adapter.dto.AvailabilityDto;
import com.renthouse.omar.application.port.in.IAvalabilityCreate_in;
import com.renthouse.omar.application.port.out.Avalability_out;
import com.renthouse.omar.config.exception.InternalServerErrorException;
import com.renthouse.omar.config.exception.ResourceNotFoundException;

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
