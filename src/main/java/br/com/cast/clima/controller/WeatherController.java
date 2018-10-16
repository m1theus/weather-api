package br.com.cast.clima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.clima.business.WeatherBusiness;
import br.com.cast.clima.entity.ResultWeather;

@RestController("/weather")
public class WeatherController {
	
	@Autowired
	private WeatherBusiness weatherBusiness;

	@GetMapping("/weather/{cityName}")
	public List<ResultWeather> buscarCidade(@PathVariable String cityName) {
		return weatherBusiness.getWeather(cityName.toLowerCase());
	}
	
}
