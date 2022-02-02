package com.mitocode.adapter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.application.dto.HouseDto;
import com.mitocode.application.port.in.IHouseFindAll_in;

@RestController
@RequestMapping("api")
public class HouseController {
	
	@Autowired
    private IHouseFindAll_in findAll_in;
	
    @GetMapping("/houses")
    public ResponseEntity<List<HouseDto>>findAll(){
    	 return new ResponseEntity<>(findAll_in.findAll(), HttpStatus.OK);
    }

}
