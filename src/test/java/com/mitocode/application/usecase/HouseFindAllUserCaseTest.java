package com.mitocode.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.renthouse.omar.adapter.dto.HouseDto;
import com.renthouse.omar.adapter.entity.House;
import com.renthouse.omar.adapter.jdbc.HouseJdbcAdapter;
import com.renthouse.omar.adapter.jpa.HouseRepository;
import com.renthouse.omar.application.port.in.IHouseFindAll_in;
import com.renthouse.omar.application.port.out.House_out;
import com.renthouse.omar.application.usecase.HouseFindAllUseCase;
import com.mitocode.object_mother.HouseMother;


public class HouseFindAllUserCaseTest {
	
	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private House_out house_outt = new HouseJdbcAdapter(houseRepository);

	private IHouseFindAll_in findAll_in = new HouseFindAllUseCase(house_outt);
	
	@BeforeEach
    void setMockOutput() {	

        List<House>listHouses = new ArrayList<House>();
        List<HouseDto>listHousesDto = HouseMother.dummy();
        listHouses = listHousesDto.stream().map(item -> item.toEntity()).collect(Collectors.toList());
		
        when(houseRepository.findAll()).thenReturn(listHouses);
    }

    @DisplayName("Test House Find All")
    @Test
    void testFindAll() {
    	List<HouseDto>listHouses = HouseMother.dummy();
		
    	assertEquals(listHouses, findAll_in.findAll(), "The find all house test was negative");
    }
	
}
