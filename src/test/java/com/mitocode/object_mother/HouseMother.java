package com.mitocode.object_mother;

import java.util.ArrayList;
import java.util.List;

import com.renthouse.omar.adapter.dto.AvailabilityDto;
import com.renthouse.omar.adapter.dto.HouseDto;

public class HouseMother {

	public static List<HouseDto> dummy() {
        List<HouseDto>listHouses = new ArrayList<HouseDto>();
    	
    	HouseDto houseDto = new HouseDto(1, "Empedrado #456", "Omar Miranda", "54154965", "omiranda@gmail.com", null);
    	List<AvailabilityDto> list = new ArrayList<AvailabilityDto>();
		AvailabilityDto availabilityDto = new AvailabilityDto(1, "Marlene", "2021-02-01", "2021-02-20");
		list.add(availabilityDto);
		houseDto.setAvailabilitys(list);
		
		listHouses.add(houseDto);
		
		houseDto = new HouseDto(3, "Obispo #322", "Amehd Diaz Medina", "56234922", "adiazm@gmail.com", null);
		list = new ArrayList<AvailabilityDto>();
		availabilityDto = new AvailabilityDto(2, "Jenny", "2021-02-11", "2021-02-19");
		list.add(availabilityDto);
		availabilityDto = new AvailabilityDto(3, "Danny", "2021-03-01", "2021-03-09");
		list.add(availabilityDto);
		houseDto.setAvailabilitys(list);
		
		listHouses.add(houseDto);
		
		return listHouses;
	}
	
	
}
