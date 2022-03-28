package com.mitocode.application.usecase;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.renthouse.omar.adapter.dto.HouseDto;
import com.renthouse.omar.adapter.jdbc.HouseJdbcAdapter;
import com.renthouse.omar.adapter.jpa.HouseRepository;
import com.renthouse.omar.application.port.in.IHouseCreate_in;
import com.renthouse.omar.application.port.out.House_out;
import com.renthouse.omar.application.usecase.HouseCreateUseCase;
import com.mitocode.object_mother.HouseMother;

@SpringBootTest
public class HouseCreateUserCaseTest {

	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private House_out house_out = new HouseJdbcAdapter(houseRepository);

	private IHouseCreate_in create_in = new HouseCreateUseCase(house_out);
	

    @DisplayName("Create House Test")
    @Test
    void testCreate() {
    	HouseDto houseDto = HouseMother.dummy().get(0);
		houseDto.setAvailabilitys(null);
		houseDto.setId(null);

		char result = 'P';
		try {
			create_in.create(houseDto);
		}
		catch (Exception e) {
			result = 'N';
		}
		
    	assertNotEquals(result, 'N', "The create house test was negative");
    	
    }
    
}
