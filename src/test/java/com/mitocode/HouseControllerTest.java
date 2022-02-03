package com.mitocode;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mitocode.adapter.controller.HouseController;
import com.mitocode.adapter.dto.AvailabilityDto;
import com.mitocode.adapter.dto.HouseDto;
import com.mitocode.adapter.repository.AvailabilityRepository;
import com.mitocode.adapter.repository.HouseRepository;
import com.mitocode.application.port.in.IAvalabilityCreate_in;
import com.mitocode.application.port.in.IAvalabilityDelete_in;
import com.mitocode.application.port.in.IAvalabilityUpdate_in;
import com.mitocode.application.port.in.IHouseCreate_in;
import com.mitocode.application.port.in.IHouseDelete_in;
import com.mitocode.application.port.in.IHouseFindAll_in;
import com.mitocode.application.port.in.IHouseFind_in;
import com.mitocode.application.port.in.IHouseUpdate_in;

public class HouseControllerTest {

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

	private HouseRepository houseRepoMock = Mockito.mock(HouseRepository.class);
	
	private AvailabilityRepository availabilityRepoMock = Mockito.mock(AvailabilityRepository.class);
	
	@Autowired
	HouseController houseController = new HouseController(houseRepoMock);
	
	@BeforeEach
	void setup() {
		HouseDto houseDto = new HouseDto(1, "Empedrado #456", "Omar Miranda", "54154965", "omiranda@gmail.com", null);
		AvailabilityDto availabilityDto = new AvailabilityDto(1, "Marlene", "2021-02-01", "2021-02-20");
		List<AvailabilityDto> list = new ArrayList<AvailabilityDto>();
		list.add(availabilityDto);
		houseDto.setAvailabilitys(list);
		Mockito.when(houseController.find(1)).thenReturn(new ResponseEntity<>(houseDto, HttpStatus.OK));
	}

	@Test
	void findAll() {
		ResponseEntity<HouseDto> respuesta;
		respuesta = houseController.find(1);
		System.out.println(respuesta);
	}

}
