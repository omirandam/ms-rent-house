package com.mitocode.adapter.jdbc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.entity.Availability;
import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.jpa.AvailabilityRepository;
import com.mitocode.adapter.jpa.HouseRepository;
import com.mitocode.application.port.out.IAvalabilityCreate_out;
import com.mitocode.application.port.out.IAvalabilityDelete_out;
import com.mitocode.application.port.out.IAvalabilityUpdate_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class AvailabilityJdbcAdapter implements IAvalabilityCreate_out, IAvalabilityUpdate_out, IAvalabilityDelete_out {

	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private AvailabilityRepository availabilityRepository;
	
	public AvailabilityJdbcAdapter(HouseRepository houseRepository, AvailabilityRepository availabilityRepository) {
		super();
		this.houseRepository = houseRepository;
		this.availabilityRepository = availabilityRepository;
	}

	@Override
	public void delete(Integer id_availability) {
		try {
			Optional<Availability>optionalAvailability = availabilityRepository.findById(id_availability);
			if(optionalAvailability.isPresent()) {
				availabilityRepository.delete(optionalAvailability.get());
			}
			else
				throw new ResourceNotFoundException("There is no availability with that id.");
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}
	
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

	@Override
	public void create(Integer id_house, Availability availability) {
		try {
			Optional<House>optionalHouse = houseRepository.findById(id_house);
			if(optionalHouse.isPresent()) {
				House house = optionalHouse.get();
				house.availabilitys.add(availability);
				houseRepository.save(house);
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
