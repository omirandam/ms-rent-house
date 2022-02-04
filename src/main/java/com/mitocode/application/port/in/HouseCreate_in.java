package com.mitocode.application.port.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.dto.HouseDto;
import com.mitocode.application.port.out.IHouseCreate_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class HouseCreate_in implements IHouseCreate_in {
	
	@Autowired
	private IHouseCreate_out iCreate_out;
	
	public HouseCreate_in(IHouseCreate_out iCreate_out) {
		this.iCreate_out = iCreate_out;
	}

	@Override
	public void create(HouseDto houseDto) {
		try {
			iCreate_out.create(houseDto.toEntity());
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	}

}
