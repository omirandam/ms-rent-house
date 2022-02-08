package com.mitocode.adapter.jdbc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.jpa.HouseRepository;
import com.mitocode.application.port.out.IHouseCreate_out;
import com.mitocode.application.port.out.IHouseDelete_out;
import com.mitocode.application.port.out.IHouseFindAll_out;
import com.mitocode.application.port.out.IHouseFind_out;
import com.mitocode.application.port.out.IHouseUpdate_out;
import com.mitocode.config.exception.InternalServerErrorException;
import com.mitocode.config.exception.ResourceNotFoundException;

@Service
public class HouseJdbcAdapter implements IHouseFind_out, IHouseFindAll_out, IHouseCreate_out, IHouseUpdate_out, IHouseDelete_out  {

	@Autowired
	private HouseRepository houseRepository;
	
	public HouseJdbcAdapter(HouseRepository houseRepository) {
		this.houseRepository = houseRepository;
	}
	
	@Override
	public void delete(Integer id) {
		try {
			Optional<House>optionalHouse = houseRepository.findById(id);
			if(optionalHouse.isPresent()) {
				houseRepository.delete(optionalHouse.get());
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
	public void update(Integer id, House house) {
		try {
			Optional<House>optionalHouse = houseRepository.findById(id);
			if(optionalHouse.isPresent()) {
				House h = new House(id, house.getAddress(), house.getOwner(), house.getTelephone_contact(), house.getContact_email(), null);
				houseRepository.save(h);
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
	public void create(House house) {
		try {
			houseRepository.save(house);
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}
	
	@Override
	public List<House> findAll(){
		return houseRepository.findAll();
	}

	@Override
	public House find(Integer id){
		Optional<House>optional = houseRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		else
			throw new ResourceNotFoundException("There is no house with that id.");
	}

}
