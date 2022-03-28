package com.renthouse.omar.application.port.in;

import com.renthouse.omar.adapter.dto.AvailabilityDto;

public interface IAvalabilityCreate_in {
	
	public void create(Integer id_house, AvailabilityDto availabilityDto);

}
