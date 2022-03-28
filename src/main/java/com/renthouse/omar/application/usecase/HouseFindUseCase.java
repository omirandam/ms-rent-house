package com.renthouse.omar.application.usecase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renthouse.omar.adapter.dto.HouseDto;
import com.renthouse.omar.application.port.in.IHouseFind_in;
import com.renthouse.omar.application.port.out.House_out;

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
