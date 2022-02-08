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
import com.mitocode.application.port.in.IHouseUpdate_in;
import com.mitocode.application.port.out.IHouseUpdate_out;
import com.mitocode.application.usecase.HouseUpdateUseCase;
import com.mitocode.object_mother.HouseMother;

@SpringBootTest
public class HouseUpdateUserCaseTest {

	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private IHouseUpdate_out update_out = new HouseJdbcAdapter(houseRepository);

	private IHouseUpdate_in update_in = new HouseUpdateUseCase(update_out);
	
	@BeforeEach
    void setMockOutput() {	
		List<HouseDto>listHouses = HouseMother.dummy();
		HouseDto houseDto = listHouses.get(0);
		Optional<House>op = Optional.ofNullable(houseDto.toEntity());
        when(houseRepository.findById(1)).thenReturn(op);
    }

    @DisplayName("Update House Test")
    @Test
    void testUpdate() {
    	HouseDto houseDto = HouseMother.dummy().get(0);

		char result = 'P';
		try {
			update_in.update(houseDto.getId(), houseDto);
		}
		catch (Exception e) {
			result = 'N';
		}
		
    	assertNotEquals(result, 'N', "The update house test was negative");
    	
    }
    
}
