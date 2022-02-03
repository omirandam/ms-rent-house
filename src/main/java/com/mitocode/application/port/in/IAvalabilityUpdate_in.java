package com.mitocode.application.port.in;


import com.mitocode.adapter.dto.AvailabilityDto;

public interface IAvalabilityUpdate_in {
	
	public void update(Integer id_house, AvailabilityDto availabilityDto);

}
