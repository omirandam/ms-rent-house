package com.mitocode.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailabilityInfo {

	public Integer id;
	
	public String client_name;

	public String from;

	public String until;

	public Integer id_house;
	
}
