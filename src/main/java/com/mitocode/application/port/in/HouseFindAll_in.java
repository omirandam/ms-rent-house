package com.mitocode.application.port.in;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.application.dto.HouseDto;
import com.mitocode.application.port.out.IHouseFindAll_out;

@Service
public class HouseFindAll_in implements IHouseFindAll_in {
	
	@Autowired
	private IHouseFindAll_out findAll_out;
	
	@Override
	public List<HouseDto> findAll(){
		return findAll_out.findAll().stream().map(HouseDto::of).collect(Collectors.toList());
	}
	

}
