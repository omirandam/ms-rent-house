package com.renthouse.omar.application.port.in;


import com.renthouse.omar.adapter.dto.AvailabilityDto;

public interface IAvalabilityUpdate_in {
	
	public void update(Integer id_house, AvailabilityDto availabilityDto);

}
