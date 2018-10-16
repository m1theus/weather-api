package br.com.cast.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.weather.entity.ResultWeather;
import br.com.cast.weather.service.WeatherService;

@RestController("/weather")
public class WeatherController {
	
	@Autowired
	private WeatherService weatherBusiness;

	@GetMapping("/weather/{cityName}")
	public List<ResultWeather> buscarCidade(@PathVariable String cityName) {
		return weatherBusiness.getWeather(cityName.toLowerCase());
	}
	
}
