package com.mitocode.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.dto.HouseDto;
import com.mitocode.application.port.in.IHouseCreate_in;
import com.mitocode.application.port.out.House_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class HouseCreateUseCase implements IHouseCreate_in {
	
	@Autowired
	private House_out house_out;
	
	public HouseCreateUseCase(House_out house_out) {
		this.house_out = house_out;
	}

	@Override
	public void create(HouseDto houseDto) {
		try {
			house_out.create(houseDto.toEntity());
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	}

}
