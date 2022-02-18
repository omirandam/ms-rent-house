package com.mitocode.application.usecase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.dto.HouseDto;
import com.mitocode.application.port.in.IHouseFind_in;
import com.mitocode.application.port.out.House_out;

@Service
public class HouseFindUseCase implements IHouseFind_in {
	
	@Autowired
	private House_out house_out;
	
	public HouseFindUseCase(House_out house_out) {
		this.house_out = house_out;
	}
	
	@Override
	public HouseDto find(Integer id){
		return HouseDto.of(house_out.find(id));
	}
	

}
