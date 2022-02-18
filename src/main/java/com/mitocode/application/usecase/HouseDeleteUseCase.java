package com.mitocode.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.application.port.in.IHouseDelete_in;
import com.mitocode.application.port.out.House_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class HouseDeleteUseCase implements IHouseDelete_in {
	
	@Autowired
	private House_out house_out;
	
	public HouseDeleteUseCase(House_out house_out) {
		this.house_out = house_out;
	}
	
	@Override
	public void delete(Integer id) {
		try {
			house_out.delete(id);
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	}

}
