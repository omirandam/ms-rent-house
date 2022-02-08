package com.mitocode.application.port.in;

import com.mitocode.adapter.dto.AvailabilityDto;

public interface IAvalabilityCreate_in {
	
	public void create(Integer id_house, AvailabilityDto availabilityDto);

}
