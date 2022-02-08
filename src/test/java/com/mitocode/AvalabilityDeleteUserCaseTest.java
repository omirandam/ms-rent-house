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
import com.mitocode.adapter.jdbc.AvailabilityJdbcAdapter;
import com.mitocode.adapter.jpa.AvailabilityRepository;
import com.mitocode.adapter.jpa.HouseRepository;
import com.mitocode.application.port.in.IAvalabilityDelete_in;
import com.mitocode.application.port.out.IAvalabilityDelete_out;
import com.mitocode.application.usecase.AvailabilityDeleteUseCase;
import com.mitocode.object_mother.HouseMother;

@SpringBootTest
public class AvalabilityDeleteUserCaseTest {

	@Mock
	private HouseRepository houseRepository;
	
	@Mock
	private AvailabilityRepository availabilityRepository;
	
	@InjectMocks
	private IAvalabilityDelete_out delete_out = new AvailabilityJdbcAdapter(houseRepository, availabilityRepository);

	private IAvalabilityDelete_in delete_in = new AvailabilityDeleteUseCase(delete_out);
	
	@BeforeEach
    void setMockOutput() {	
		List<HouseDto>listHouses = HouseMother.dummy();
		HouseDto houseDto = listHouses.get(0);
		Optional<Availability>op = Optional.ofNullable(houseDto.toEntity().availabilitys.get(0));
        when(availabilityRepository.findById(1)).thenReturn(op);
    }

    @DisplayName("Delete Availability Test")
    @Test
    void testDelete() {
    	AvailabilityDto availabilityDto = HouseMother.dummy().get(0).availabilitys.get(0);
		
		char result = 'P';
		try {
			delete_in.delete(availabilityDto.getId());
		}
		catch (Exception e) {
			result = 'N';
		}
		
    	assertNotEquals(result, 'N', "The delete availability test was negative");
    	
    }
    
}
