package com.renthouse.omar.adapter.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HouseInfo {

    public Integer id;
	
    @NotNull(message = "The address cannot be empty")
	public String address;

    @NotNull(message = "The owner cannot be empty")
	public String owner;
    
    @NotNull(message = "The telephone contact cannot be empty")
    @Size(message = "The telephone contact must be 8 characters",max = 8, min = 8)
	public String telephone_contact;
    
    @NotNull(message = "The contact email cannot be empty")
	@Email(message = "The email is not valid")
	public String contact_email;
	
}
