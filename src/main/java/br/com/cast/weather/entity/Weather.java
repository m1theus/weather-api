package br.com.cast.weather.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="previsao", schema="public")
@Setter
@Getter
public class Weather {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="cidade")
	private String cidade;
	
	@Column(name="temp")
	private double temp;
	
	@Column(name="temp_min")
	private double tempMin;
	
	@Column(name="temp_max")
	private double tempMax;
	
	@Column(name="pressao")
	private double pressao;
	
	@Column(name="umidade")
	private double umidade;
	
	@Column(name="vel_vento")
	private double velocidadeVento;
	
	@Column(name="data")
	private Date data;
	
	@Column(name="icone")
	private String icone;
	
	@Column(name="descricao")
	private String descricao;
	
	public static ResultWeather fromEntity(Weather weather, String cityName) {
		ResultWeather dto = new ResultWeather();
		dto.setTemp(weather.getTemp());
		dto.setTempMin(weather.getTempMin());
		dto.setTempMax(weather.getTempMax());
		dto.setPressao(weather.getPressao());
		dto.setUmidade(weather.getUmidade());
		dto.setVelocidadeVento(weather.getVelocidadeVento());
		dto.setDescricao(weather.getDescricao());
		dto.setIcone(weather.getIcone());
		dto.setData(dateToString(weather.getData()));
		dto.setCidade(cityName);
		return dto;
	}
	
	public static String dateToString(Date date) {
		String dataFormatada = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dataFormatada = format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataFormatada;
	}

}
