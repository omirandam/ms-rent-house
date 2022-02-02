package com.mitocode.application.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.mitocode.adapter.entity.Availability;
import com.mitocode.adapter.entity.House;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HouseDto {

    public Integer id;
	
	public String address;

	public String owner;

	public String telephone_contact;

	public String contact_email;
	
	public List<AvailabilityDto> availabilitys;
	
	public House toEntity() {
		House house = new House();
		BeanUtils.copyProperties(this, house);
		if(this.availabilitys != null) {
			List<Availability> listAvailabilities = this.availabilitys.stream().map(item -> item.toEntity()).collect(Collectors.toList());
			house.setAvailabilitys(listAvailabilities); 
		}
		return house;
	}
	
	public static HouseDto of(House house) {
		HouseDto dto = new HouseDto();
		BeanUtils.copyProperties(house, dto);
		if(house.availabilitys != null) {
			List<AvailabilityDto> listAvailabilityDtos = house.availabilitys.stream().map(AvailabilityDto::of).collect(Collectors.toList());
			dto.setAvailabilitys(listAvailabilityDtos); 
		}
		return dto;
	}
}
