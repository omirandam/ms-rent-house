package com.mitocode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mitocode.adapter.dto.AvailabilityDto;
import com.mitocode.adapter.dto.HouseDto;
import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.repository.HouseRepository;
import com.mitocode.application.port.in.HouseFindAll_in;
import com.mitocode.application.port.in.IHouseFindAll_in;
import com.mitocode.application.port.out.HouseFindAll_out;
import com.mitocode.application.port.out.IHouseFindAll_out;

@SpringBootTest
public class HouseFindAll_inTest {
	
	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private IHouseFindAll_out findAll_out = new HouseFindAll_out(houseRepository);

	private IHouseFindAll_in findAll_in = new HouseFindAll_in(findAll_out);
	
	@BeforeEach
    void setMockOutput() {	

        List<House>listHouses = new ArrayList<House>();
        
        HouseDto houseDto = new HouseDto(1, "Empedrado #456", "Omar Miranda", "54154965", "omiranda@gmail.com", null);
        List<AvailabilityDto> list = new ArrayList<AvailabilityDto>();
		AvailabilityDto availabilityDto = new AvailabilityDto(1, "Marlene", "2021-02-01", "2021-02-20");
		list.add(availabilityDto);
		houseDto.setAvailabilitys(list);

		listHouses.add(houseDto.toEntity());
		
		houseDto = new HouseDto(3, "Obispo #322", "Amehd Diaz Medina", "56234922", "adiazm@gmail.com", null);
		list = new ArrayList<AvailabilityDto>();
		availabilityDto = new AvailabilityDto(2, "Jenny", "2021-02-11", "2021-02-19");
		list.add(availabilityDto);
		availabilityDto = new AvailabilityDto(3, "Danny", "2021-03-01", "2021-03-09");
		list.add(availabilityDto);
		houseDto.setAvailabilitys(list);
		
		listHouses.add(houseDto.toEntity());
		
        when(houseRepository.findAll()).thenReturn(listHouses);
    }

    @DisplayName("Test House Find All")
    @Test
    void testFindAll() {
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
		
    	assertEquals(listHouses, findAll_in.findAll(), "Test find all houses is not equals!");
    }
	
}
