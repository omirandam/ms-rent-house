package com.mitocode.adapter.dto;

import java.text.SimpleDateFormat;
import java.sql.Date;

import org.springframework.beans.BeanUtils;

import com.mitocode.adapter.entity.Availability;
import com.mitocode.config.exception.InternalServerErrorException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailabilityDto {

	public Integer id;
	
	public String client_name;

	public String from_date;

	public String until_date;
	
	public Availability toEntity() {
		Availability availability = new Availability();
		BeanUtils.copyProperties(this, availability);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date fromDate = new Date(dateFormat.parse(this.getFrom_date()).getTime());
			Date untilDate = new Date(dateFormat.parse(this.getUntil_date()).getTime());
			
			availability.setFrom_date(fromDate);
			availability.setUntil_date(untilDate);
			
		} 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
		
		return availability;
	}
	
	public static AvailabilityDto of(Availability availability) {
		AvailabilityDto dto = new AvailabilityDto();
		BeanUtils.copyProperties(availability, dto);
		dto.setFrom_date(availability.getFrom_date().toString());
		dto.setUntil_date(availability.getUntil_date().toString());
		return dto;
	}
	
	
}
