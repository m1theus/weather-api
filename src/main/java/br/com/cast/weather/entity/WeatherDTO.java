package br.com.cast.weather.entity;

import java.util.List;

public class WeatherDTO {

	private List<WeatherDataDTO> list;

	public List<WeatherDataDTO> getList() {
		return list;
	}

	public void setList(List<WeatherDataDTO> list) {
		this.list = list;
	}

}
