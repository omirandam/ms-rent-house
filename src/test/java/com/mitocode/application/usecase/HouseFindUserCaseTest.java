package com.mitocode.application.usecase;

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

import com.renthouse.omar.adapter.dto.HouseDto;
import com.renthouse.omar.adapter.entity.House;
import com.renthouse.omar.adapter.jdbc.HouseJdbcAdapter;
import com.renthouse.omar.adapter.jpa.HouseRepository;
import com.renthouse.omar.application.port.in.IHouseFind_in;
import com.renthouse.omar.application.port.out.House_out;
import com.renthouse.omar.application.usecase.HouseFindUseCase;
import com.mitocode.object_mother.HouseMother;

public class HouseFindUserCaseTest {

	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private House_out house_out = new HouseJdbcAdapter(houseRepository);

	private IHouseFind_in find_in = new HouseFindUseCase(house_out);
	
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
