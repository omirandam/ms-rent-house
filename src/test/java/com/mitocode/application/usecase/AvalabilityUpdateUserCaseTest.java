package com.mitocode.application.usecase;

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

import com.renthouse.omar.adapter.dto.AvailabilityDto;
import com.renthouse.omar.adapter.dto.HouseDto;
import com.renthouse.omar.adapter.entity.Availability;
import com.renthouse.omar.adapter.entity.House;
import com.renthouse.omar.adapter.jdbc.AvailabilityJdbcAdapter;
import com.renthouse.omar.adapter.jpa.AvailabilityRepository;
import com.renthouse.omar.adapter.jpa.HouseRepository;
import com.renthouse.omar.application.port.in.IAvalabilityUpdate_in;
import com.renthouse.omar.application.port.out.Avalability_out;
import com.renthouse.omar.application.usecase.AvailabilityUpdateUseCase;
import com.mitocode.object_mother.HouseMother;

@SpringBootTest
public class AvalabilityUpdateUserCaseTest {

	@Mock
	private HouseRepository houseRepository;
	
	@Mock
	private AvailabilityRepository availabilityRepository;
	
	@InjectMocks
	private Avalability_out avalability_out = new AvailabilityJdbcAdapter(houseRepository, availabilityRepository);

	private IAvalabilityUpdate_in update_in = new AvailabilityUpdateUseCase(avalability_out);
	
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
