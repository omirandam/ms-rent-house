package com.mitocode;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mitocode.adapter.dto.HouseDto;
import com.mitocode.adapter.jdbc.HouseJdbcAdapter;
import com.mitocode.adapter.jpa.HouseRepository;
import com.mitocode.application.port.in.IHouseCreate_in;
import com.mitocode.application.port.out.IHouseCreate_out;
import com.mitocode.application.usecase.HouseCreateUseCase;
import com.mitocode.object_mother.HouseMother;

@SpringBootTest
public class HouseCreateUserCaseTest {

	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private IHouseCreate_out create_out = new HouseJdbcAdapter(houseRepository);

	private IHouseCreate_in create_in = new HouseCreateUseCase(create_out);
	

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
