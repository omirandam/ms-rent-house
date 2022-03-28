package com.renthouse.omar.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renthouse.omar.adapter.dto.HouseDto;
import com.renthouse.omar.application.port.in.IHouseCreate_in;
import com.renthouse.omar.application.port.out.House_out;
import com.renthouse.omar.config.exception.InternalServerErrorException;
import com.renthouse.omar.config.exception.ResourceNotFoundException;

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
