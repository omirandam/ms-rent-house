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
import com.mitocode.adapter.entity.Availability;
import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.jdbc.AvailabilityJdbcAdapter;
import com.mitocode.adapter.jpa.AvailabilityRepository;
import com.mitocode.adapter.jpa.HouseRepository;
import com.mitocode.application.port.in.IAvalabilityUpdate_in;
import com.mitocode.application.port.out.IAvalabilityUpdate_out;
import com.mitocode.application.usecase.AvailabilityUpdateUseCase;
import com.mitocode.object_mother.HouseMother;

@SpringBootTest
public class AvalabilityUpdateUserCaseTest {

	@Mock
	private HouseRepository houseRepository;
	
	@Mock
	private AvailabilityRepository availabilityRepository;
	
	@InjectMocks
	private IAvalabilityUpdate_out update_out = new AvailabilityJdbcAdapter(houseRepository, availabilityRepository);

	private IAvalabilityUpdate_in update_in = new AvailabilityUpdateUseCase(update_out);
	
	@BeforeEach
    void setMockOutput() {	
		List<HouseDto>listHouses = HouseMother.dummy();
		HouseDto houseDto = listHouses.get(0);
		Optional<House>opHouse = Optional.ofNullable(houseDto.toEntity());
		Optional<Availability>opAvailability = Optional.ofNullable(houseDto.toEntity().availabilitys.get(0));
        
		when(houseRepository.findById(1)).thenReturn(opHouse);
        when(availabilityRepository.findById(1)).thenReturn(opAvailability);
    }

    @DisplayName("Update Availability Test")
    @Test
    void testUpdate() {
    	Integer id_house = HouseMother.dummy().get(0).getId();
    	AvailabilityDto availabilityDto = HouseMother.dummy().get(0).availabilitys.get(0);
		
		char result = 'P';
		try {
			update_in.update(id_house, availabilityDto);
		}
		catch (Exception e) {
			result = 'N';
		}
		
    	assertNotEquals(result, 'N', "The update availability test was negative");
    	
    }
    
}
