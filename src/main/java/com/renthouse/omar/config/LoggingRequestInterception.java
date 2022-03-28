package com.renthouse.omar.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.renthouse.omar.adapter.kafka.KafkaSender;

@Component
public class LoggingRequestInterception implements HandlerInterceptor {

	@Autowired
	private KafkaSender kafkaSender;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception 
	{
		String url = request.getRequestURI();
		kafkaSender.sendData(url);
		return true;
	}
	

}
