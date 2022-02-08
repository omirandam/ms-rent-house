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

import com.mitocode.adapter.dto.HouseDto;
import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.jdbc.HouseJdbcAdapter;
import com.mitocode.adapter.jpa.HouseRepository;
import com.mitocode.application.port.in.IHouseDelete_in;
import com.mitocode.application.port.out.IHouseDelete_out;
import com.mitocode.application.usecase.HouseDeleteUseCase;
import com.mitocode.object_mother.HouseMother;

@SpringBootTest
public class HouseDeleteUserCaseTest {

	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private IHouseDelete_out delete_out = new HouseJdbcAdapter(houseRepository);

	private IHouseDelete_in delete_in = new HouseDeleteUseCase(delete_out);
	
	@BeforeEach
    void setMockOutput() {	
		List<HouseDto>listHouses = HouseMother.dummy();
		HouseDto houseDto = listHouses.get(0);
		Optional<House>op = Optional.ofNullable(houseDto.toEntity());
        when(houseRepository.findById(1)).thenReturn(op);
    }

    @DisplayName("Delete House Test")
    @Test
    void testDelete() {
    	HouseDto houseDto = HouseMother.dummy().get(0);
		
		char result = 'P';
		try {
			delete_in.delete(houseDto.getId());
		}
		catch (Exception e) {
			result = 'N';
		}
		
    	assertNotEquals(result, 'N', "The delete house test was negative");
    	
    }
    
}
