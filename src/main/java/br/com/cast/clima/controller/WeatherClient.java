package br.com.cast.clima.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.cast.clima.entity.WeatherDTO;

@Component
public class WeatherClient {

	private static final String API_URL = "http://api.openweathermap.org/data/2.5/forecast?q={cityName},br&units=metric&mode=json&appid=734547ee8ad10cf414aae8638be088ff&lang=pt";
	
	private final RestTemplate restTemplate;
	
	public WeatherClient(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	
	
	public WeatherDTO getWeather(String cityName) {
		return this.restTemplate.getForObject(API_URL, WeatherDTO.class, cityName);
	}

}
