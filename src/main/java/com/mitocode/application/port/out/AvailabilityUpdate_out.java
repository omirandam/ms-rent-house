package com.mitocode.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.entity.Availability;
import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.repository.AvailabilityRepository;
import com.mitocode.adapter.repository.HouseRepository;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class AvailabilityUpdate_out implements IAvalabilityUpdate_out {

	@Autowired
	private HouseRepository houseRepository;
	@Autowired
	private AvailabilityRepository availabilityRepository;
	
	@Override
	public void update(Integer id_house, Availability availability) {
		try {
			Optional<House>optionalHouse = houseRepository.findById(id_house);
			if(optionalHouse.isPresent()) {
				House house = optionalHouse.get();
				Optional<Availability>optionalAvailability = availabilityRepository.findById(availability.getId());
				if(optionalAvailability.isPresent()) {
					
					List<Availability>list = house.availabilitys.stream().map(item -> {
						if(item.getId() == optionalAvailability.get().getId()) {
							item.setClient_name(availability.getClient_name());
							item.setFrom_date(availability.getFrom_date());
							item.setUntil_date(availability.getUntil_date());
						}
						return item;
					}).collect(Collectors.toList());
					
					house.setAvailabilitys(list);
					houseRepository.save(house);
				}
				else 
					throw new ResourceNotFoundException("There is no availability with that id.");
			}
			else
				throw new ResourceNotFoundException("There is no house with that id.");
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}
	
}
