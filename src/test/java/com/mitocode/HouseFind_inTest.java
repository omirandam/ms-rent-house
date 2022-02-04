package com.mitocode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import com.mitocode.adapter.entity.House;
import com.mitocode.adapter.repository.HouseRepository;
import com.mitocode.application.port.in.HouseFind_in;
import com.mitocode.application.port.in.IHouseFind_in;
import com.mitocode.application.port.out.HouseFind_out;
import com.mitocode.application.port.out.IHouseFind_out;

@SpringBootTest
public class HouseFind_inTest {

	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private IHouseFind_out find_out = new HouseFind_out(houseRepository);

	private IHouseFind_in find_in = new HouseFind_in(find_out);
	
	@BeforeEach
    void setMockOutput() {	
		HouseDto houseDto = new HouseDto(1, "Empedrado #456", "Omar Miranda", "54154965", "omiranda@gmail.com", null);
		AvailabilityDto availabilityDto = new AvailabilityDto(1, "Marlene", "2021-02-01", "2021-02-20");
		List<AvailabilityDto> list = new ArrayList<AvailabilityDto>();
		list.add(availabilityDto);
		houseDto.setAvailabilitys(list);
		Optional<House>op = Optional.ofNullable(houseDto.toEntity());
        when(houseRepository.findById(1)).thenReturn(op);
    }

    @DisplayName("Test House Find")
    @Test
    void testFind() {
    	HouseDto houseDto = new HouseDto(1, "Empedrado #456", "Omar Miranda", "54154965", "omiranda@gmail.com", null);
		AvailabilityDto availabilityDto = new AvailabilityDto(1, "Marlene", "2021-02-01", "2021-02-20");
		List<AvailabilityDto> list = new ArrayList<AvailabilityDto>();
		list.add(availabilityDto);
		houseDto.setAvailabilitys(list);
    	assertEquals(houseDto, find_in.find(1), "Test find house is not equals!");
    }
	
}
