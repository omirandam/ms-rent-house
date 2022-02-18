package com.mitocode.application.port.out;

import com.mitocode.adapter.entity.Availability;

public interface Avalability_out {

	public void create(Integer id_house, Availability availability);
	
	public void delete(Integer id_availability);

	public void update(Integer id_house, Availability availability);
}
