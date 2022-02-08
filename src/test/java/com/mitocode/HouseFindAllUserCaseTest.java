package com.mitocode;

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
import org.springframework.boot.test.context.SpringBootTest;

import com.mitocode.adapter.dto.HouseDto;
import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.jdbc.HouseJdbcAdapter;
import com.mitocode.adapter.jpa.HouseRepository;
import com.mitocode.application.port.in.IHouseFindAll_in;
import com.mitocode.application.port.out.IHouseFindAll_out;
import com.mitocode.application.usecase.HouseFindAllUseCase;
import com.mitocode.object_mother.HouseMother;

@SpringBootTest
public class HouseFindAllUserCaseTest {
	
	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private IHouseFindAll_out findAll_out = new HouseJdbcAdapter(houseRepository);

	private IHouseFindAll_in findAll_in = new HouseFindAllUseCase(findAll_out);
	
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
