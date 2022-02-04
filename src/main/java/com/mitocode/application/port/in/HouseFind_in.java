package com.mitocode.application.port.in;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.dto.HouseDto;
import com.mitocode.application.port.out.IHouseFind_out;

@Service
public class HouseFind_in implements IHouseFind_in {
	
	@Autowired
	private IHouseFind_out find_out;
	
	public HouseFind_in(IHouseFind_out find_out) {
		this.find_out = find_out;
	}
	
	@Override
	public HouseDto find(Integer id){
		return HouseDto.of(find_out.find(id));
	}
	

}
