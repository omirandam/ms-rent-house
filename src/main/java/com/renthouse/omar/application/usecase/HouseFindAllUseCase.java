package com.renthouse.omar.application.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renthouse.omar.adapter.dto.HouseDto;
import com.renthouse.omar.application.port.in.IHouseFindAll_in;
import com.renthouse.omar.application.port.out.House_out;

@Service
public class HouseFindAllUseCase implements IHouseFindAll_in {
	
	@Autowired
	private House_out house_out;
	
	public HouseFindAllUseCase(House_out house_out) {
		this.house_out = house_out;
	}
	
	@Override
	public List<HouseDto> findAll(){
		return house_out.findAll().stream().map(HouseDto::of).collect(Collectors.toList());
	}
	

}
