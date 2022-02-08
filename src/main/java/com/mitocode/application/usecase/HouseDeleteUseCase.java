package com.mitocode.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.application.port.in.IHouseDelete_in;
import com.mitocode.application.port.out.IHouseDelete_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class HouseDeleteUseCase implements IHouseDelete_in {
	
	@Autowired
	private IHouseDelete_out houseDelete_out;
	
	public HouseDeleteUseCase(IHouseDelete_out houseDelete_out) {
		this.houseDelete_out = houseDelete_out;
	}
	
	@Override
	public void delete(Integer id) {
		try {
			houseDelete_out.delete(id);
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	}

}
