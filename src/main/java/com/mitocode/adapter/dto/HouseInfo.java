package com.mitocode.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HouseInfo {

    public Integer id;
	
	public String address;

	public String owner;

	public String telephone_contact;

	public String contact_email;
	
}
