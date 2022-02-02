package com.mitocode.application.port.in;


import com.mitocode.adapter.dto.AvailabilityInfo;

public interface IAvalabilityUpdate_in {
	
	public void update(Integer id_availability, AvailabilityInfo availabilityInfo);

}
