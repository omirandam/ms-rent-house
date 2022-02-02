package com.mitocode.application.port.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.application.port.out.IHouseDelete_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class HouseDelete_in implements IHouseDelete_in {
	
	@Autowired
	private IHouseDelete_out houseDelete_out;
	
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
