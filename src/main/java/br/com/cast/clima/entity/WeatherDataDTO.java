package br.com.cast.clima.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDataDTO {

	@JsonProperty("main")
	private WeatherMainDTO main;
	@JsonProperty("weather")
	private List<WeatherDescriptionDTO> weather;
	@JsonProperty("wind")
	private WeatherWindDTO wind;
	@JsonProperty("dt_txt")
	private String dtTxt;

	public WeatherMainDTO getMain() {
		return main;
	}

	public void setMain(WeatherMainDTO main) {
		this.main = main;
	}

	public List<WeatherDescriptionDTO> getWeather() {
		return weather;
	}

	public void setWeather(List<WeatherDescriptionDTO> description) {
		this.weather = description;
	}

	public WeatherWindDTO getWind() {
		return wind;
	}

	public void setWind(WeatherWindDTO wind) {
		this.wind = wind;
	}

	public String getDtTxt() {
		return dtTxt;
	}

	public void setDtTxt(String dtTxt) {
		this.dtTxt = dtTxt;
	}

}
