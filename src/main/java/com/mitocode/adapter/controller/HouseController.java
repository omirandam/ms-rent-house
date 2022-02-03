package com.mitocode.adapter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.adapter.dto.AvailabilityDto;
import com.mitocode.adapter.dto.AvailabilityInfo;
import com.mitocode.adapter.dto.HouseDto;
import com.mitocode.adapter.dto.HouseInfo;
import com.mitocode.application.port.in.IAvalabilityCreate_in;
import com.mitocode.application.port.in.IAvalabilityDelete_in;
import com.mitocode.application.port.in.IAvalabilityUpdate_in;
import com.mitocode.application.port.in.IHouseCreate_in;
import com.mitocode.application.port.in.IHouseDelete_in;
import com.mitocode.application.port.in.IHouseFindAll_in;
import com.mitocode.application.port.in.IHouseFind_in;
import com.mitocode.application.port.in.IHouseUpdate_in;


@RestController
@RequestMapping("api")
public class HouseController {

	@Autowired
    private IHouseFindAll_in findAll_in;

	@Autowired
    private IHouseCreate_in create_in;

	@Autowired
    private IHouseUpdate_in update_in;

	@Autowired
    private IHouseDelete_in delete_in;

	@Autowired
    private IHouseFind_in find_in;

	@Autowired
    private IAvalabilityCreate_in avalabilityCreate_in;

	@Autowired
    private IAvalabilityUpdate_in avalabilityUpdate_in;

	@Autowired
    private IAvalabilityDelete_in avalabilityDelete_in;
	
    @GetMapping("/house")
    public ResponseEntity<List<HouseDto>>findAll(){
    	 return new ResponseEntity<>(findAll_in.findAll(), HttpStatus.OK);
    }
    
    @PostMapping("/house")
    public ResponseEntity<String> create(@RequestBody @Valid HouseInfo houseInfo){
    	HouseDto houseDto = new HouseDto(null, houseInfo.getAddress(), houseInfo.getOwner(),  houseInfo.getTelephone_contact(),  houseInfo.getContact_email(), null);
		create_in.create(houseDto);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @PutMapping("/house/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id,@RequestBody @Valid HouseInfo houseInfo){
    	HouseDto houseDto = new HouseDto(null, houseInfo.getAddress(), houseInfo.getOwner(),  houseInfo.getTelephone_contact(),  houseInfo.getContact_email(), null);
		update_in.update(id, houseDto);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
    
    @DeleteMapping("/house/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
    	delete_in.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
    
    @GetMapping("/house/{id}")
    public ResponseEntity<HouseDto> find(@PathVariable Integer id){
    	return new ResponseEntity<>(find_in.find(id), HttpStatus.OK);
    }
    
    @PostMapping("/availability")
    public ResponseEntity<String> createAvailability(@RequestBody @Valid AvailabilityInfo availabilityInfo){
    	AvailabilityDto availabilityDto = new AvailabilityDto(null, availabilityInfo.getClient_name(), availabilityInfo.getFrom_date(), availabilityInfo.getUntil_date());
		avalabilityCreate_in.create(availabilityInfo.getId_house(),availabilityDto);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @PutMapping("/availability/{id}")
    public ResponseEntity<String> updateAvailability(@PathVariable Integer id,@RequestBody @Valid AvailabilityInfo availabilityInfo){
    	AvailabilityDto availabilityDto = new AvailabilityDto(id, availabilityInfo.getClient_name(), availabilityInfo.getFrom_date(), availabilityInfo.getUntil_date());
		avalabilityUpdate_in.update(availabilityInfo.getId_house(), availabilityDto);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
    
    @DeleteMapping("/availability/{id}")
    public ResponseEntity<String> deleteAvailability(@PathVariable Integer id){
    	avalabilityDelete_in.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

}
