package com.mitocode.application.port.out;


import com.mitocode.adapter.entity.Availability;

public interface IAvalabilityUpdate_out {
	
	public void update(Integer id_house, Availability availability);

}
