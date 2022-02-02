package com.mitocode.application.port.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.dto.HouseInfo;
import com.mitocode.application.dto.HouseDto;
import com.mitocode.application.port.out.IHouseUpdate_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class HouseUpdate_in implements IHouseUpdate_in {
	
	@Autowired
	private IHouseUpdate_out houseUpdate_out;
	
	@Override
	public void update(Integer id, HouseInfo houseInfo) {
		try {
			HouseDto houseDto = new HouseDto(null, houseInfo.getAddress(), houseInfo.getOwner(),  houseInfo.getTelephone_contact(),  houseInfo.getContact_email(), null);
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
