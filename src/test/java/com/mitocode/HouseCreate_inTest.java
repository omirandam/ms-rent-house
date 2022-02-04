package com.mitocode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mitocode.adapter.dto.HouseDto;
import com.mitocode.adapter.repository.HouseRepository;
import com.mitocode.application.port.in.HouseCreate_in;
import com.mitocode.application.port.in.IHouseCreate_in;
import com.mitocode.application.port.out.HouseCreate_out;
import com.mitocode.application.port.out.IHouseCreate_out;

@SpringBootTest
public class HouseCreate_inTest {

	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private IHouseCreate_out create_out = new HouseCreate_out(houseRepository);

	private IHouseCreate_in create_in = new HouseCreate_in(create_out);
	
	@BeforeEach
    void setMockOutput() {	
		HouseDto houseDto = new HouseDto(null, "Address Test", "Test test", "11111111", "test@test.com", null);

        when(houseRepository.save(houseDto.toEntity())).thenReturn(null);
    }

    @DisplayName("Test House Create")
    @Test
    void testCreate() {
    	HouseDto houseDto = new HouseDto(null, "Address Test", "Test test", "11111111", "test@test.com", null);

    	//assertEquals(null, create_in.create(houseDto), "Test find all houses is not equals!");
    	
    }
    
}
