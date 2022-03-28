package com.renthouse.omar.adapter.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailabilityInfo {

	public Integer id;
	
	@NotNull(message = "The address cannot be empty")
	public String client_name;

	@NotNull(message = "The address cannot be empty")
	public String from_date;

	@NotNull(message = "The address cannot be empty")
	public String until_date;

	@NotNull(message = "The address cannot be empty")
	public Integer id_house;
	
}
