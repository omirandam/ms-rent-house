package com.mitocode.application.port.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.dto.AvailabilityInfo;
import com.mitocode.application.dto.AvailabilityDto;
import com.mitocode.application.port.out.IAvalabilityUpdate_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class AvailabilityUpdate_in implements IAvalabilityUpdate_in {

	@Autowired
	private IAvalabilityUpdate_out avalabilityUpdate_out;
	
	@Override
	public void update(Integer id_availability, AvailabilityInfo availabilityInfo) {
		try {
			AvailabilityDto availabilityDto = new AvailabilityDto(id_availability, availabilityInfo.getClient_name(), availabilityInfo.getFrom_date(), availabilityInfo.getUntil_date());
			avalabilityUpdate_out.update(availabilityInfo.getId_house(), availabilityDto.toEntity());
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}
	
}
