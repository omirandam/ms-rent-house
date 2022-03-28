package com.renthouse.omar.application.util;

import java.sql.Timestamp;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Time {
	
	public Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
}
