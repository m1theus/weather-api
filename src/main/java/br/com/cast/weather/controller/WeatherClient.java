package br.com.cast.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.cast.weather.entity.WeatherDTO;

@Component
public class WeatherClient {
	
	@Autowired
	private Environment env;
	
	private final RestTemplate restTemplate;
	
	public WeatherClient(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	
	public WeatherDTO getWeather(String cityName) {
		String openWeatherUrl = env.getProperty("weather.url");
		String appId = env.getProperty("weather.appid");
		return this.restTemplate.getForObject(openWeatherUrl , WeatherDTO.class, cityName, appId);
	}

}
