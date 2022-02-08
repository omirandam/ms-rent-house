package com.mitocode;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.mitocode.application.port.in.IHouseFind_in;
import com.mitocode.application.port.out.IHouseFind_out;
import com.mitocode.application.usecase.HouseFindUseCase;
import com.mitocode.object_mother.HouseMother;

@SpringBootTest
public class HouseFindUserCaseTest {

	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private IHouseFind_out find_out = new HouseJdbcAdapter(houseRepository);

	private IHouseFind_in find_in = new HouseFindUseCase(find_out);
	
	@BeforeEach
    void setMockOutput() {	
		List<HouseDto>listHouses = HouseMother.dummy();
		HouseDto houseDto = listHouses.get(0);
		Optional<House>op = Optional.ofNullable(houseDto.toEntity());
        when(houseRepository.findById(1)).thenReturn(op);
    }

    @DisplayName("Test House Find")
    @Test
    void testFind() {
    	List<HouseDto>listHouses = HouseMother.dummy();
		HouseDto houseDto = listHouses.get(0);
		assertEquals(houseDto, find_in.find(1), "The find house test was negative");
    }
	
}
