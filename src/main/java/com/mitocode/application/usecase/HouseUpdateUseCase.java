package com.mitocode.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.dto.HouseDto;
import com.mitocode.application.port.in.IHouseUpdate_in;
import com.mitocode.application.port.out.IHouseUpdate_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class HouseUpdateUseCase implements IHouseUpdate_in {
	
	@Autowired
	private IHouseUpdate_out houseUpdate_out;
	
	public HouseUpdateUseCase (IHouseUpdate_out houseUpdate_out) {
		this.houseUpdate_out = houseUpdate_out;
	}
	
	@Override
	public void update(Integer id, HouseDto houseDto) {
		try {
			houseUpdate_out.update(id, houseDto.toEntity());
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	}

}
