package com.mitocode;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mitocode.adapter.dto.AvailabilityDto;
import com.mitocode.adapter.dto.HouseDto;
import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.jdbc.AvailabilityJdbcAdapter;
import com.mitocode.adapter.jpa.AvailabilityRepository;
import com.mitocode.adapter.jpa.HouseRepository;
import com.mitocode.application.port.in.IAvalabilityCreate_in;
import com.mitocode.application.port.out.IAvalabilityCreate_out;
import com.mitocode.application.usecase.AvailabilityCreateUseCase;
import com.mitocode.object_mother.HouseMother;

@SpringBootTest
public class AvalabilityCreateUserCaseTest {

	@Mock
	private HouseRepository houseRepository;

	@Mock
	private AvailabilityRepository availabilityRepository;
	
	@InjectMocks
	private IAvalabilityCreate_out create_out = new AvailabilityJdbcAdapter(houseRepository, availabilityRepository);

	private IAvalabilityCreate_in create_in = new AvailabilityCreateUseCase(create_out);
	
	@BeforeEach
    void setMockOutput() {	
		List<HouseDto>listHouses = HouseMother.dummy();
		HouseDto houseDto = listHouses.get(0);
		Optional<House>opHouse = Optional.ofNullable(houseDto.toEntity());
		
		when(houseRepository.findById(1)).thenReturn(opHouse);
    }

	
    @DisplayName("Create Availability Test")
    @Test
    void testCreate() {
    	Integer id_house = HouseMother.dummy().get(0).getId();
    	AvailabilityDto availabilityDto = HouseMother.dummy().get(0).availabilitys.get(0);
        availabilityDto.setId(null);
		
		char result = 'P';
		try {
			create_in.create(id_house, availabilityDto);
		}
		catch (Exception e) {
			result = 'N';
		}
		
    	assertNotEquals(result, 'N', "The create availability test was negative");
    	
    }
    
}
