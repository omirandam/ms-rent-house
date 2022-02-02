package com.mitocode.application.dto;


import org.springframework.beans.BeanUtils;

import com.mitocode.adapter.entity.Availability;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailabilityDto {

	public Integer id;
	
	public String client_name;

	public String from;

	public String until;
	
	public Availability toEntity() {
		Availability availability = new Availability();
		BeanUtils.copyProperties(this, availability);
		return availability;
	}
	
	public static AvailabilityDto of(Availability availability) {
		AvailabilityDto dto = new AvailabilityDto();
		BeanUtils.copyProperties(availability, dto);
		return dto;
	}
	
	
}
