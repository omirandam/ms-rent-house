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
import com.renthouse.omar.adapter.jdbc.AvailabilityJdbcAdapter;
import com.renthouse.omar.adapter.jpa.AvailabilityRepository;
import com.renthouse.omar.adapter.jpa.HouseRepository;
import com.renthouse.omar.application.port.in.IAvalabilityDelete_in;
import com.renthouse.omar.application.port.out.Avalability_out;
import com.renthouse.omar.application.usecase.AvailabilityDeleteUseCase;
import com.mitocode.object_mother.HouseMother;

@SpringBootTest
public class AvalabilityDeleteUserCaseTest {

	@Mock
	private HouseRepository houseRepository;
	
	@Mock
	private AvailabilityRepository availabilityRepository;
	
	@InjectMocks
	private Avalability_out avalability_out = new AvailabilityJdbcAdapter(houseRepository, availabilityRepository);

	private IAvalabilityDelete_in delete_in = new AvailabilityDeleteUseCase(avalability_out);
	
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
