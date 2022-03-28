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

import com.renthouse.omar.adapter.dto.HouseDto;
import com.renthouse.omar.adapter.entity.House;
import com.renthouse.omar.adapter.jdbc.HouseJdbcAdapter;
import com.renthouse.omar.adapter.jpa.HouseRepository;
import com.renthouse.omar.application.port.in.IHouseDelete_in;
import com.renthouse.omar.application.port.out.House_out;
import com.renthouse.omar.application.usecase.HouseDeleteUseCase;
import com.mitocode.object_mother.HouseMother;

@SpringBootTest
public class HouseDeleteUserCaseTest {

	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private House_out house_out = new HouseJdbcAdapter(houseRepository);

	private IHouseDelete_in delete_in = new HouseDeleteUseCase(house_out);
	
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
