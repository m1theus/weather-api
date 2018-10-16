package br.com.cast.clima.entity;

public class ResultWeather {

	private double temp;
	private double tempMin;
	private double tempMax;
	private double pressao;
	private double umidade;
	private double velocidadeVento;
	private String data;
	private String icone;
	private String descricao;
	private String cidade;

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getTempMin() {
		return tempMin;
	}

	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}

	public double getTempMax() {
		return tempMax;
	}

	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	public double getPressao() {
		return pressao;
	}

	public void setPressao(double pressao) {
		this.pressao = pressao;
	}

	public double getUmidade() {
		return umidade;
	}

	public void setUmidade(double umidade) {
		this.umidade = umidade;
	}

	public double getVelocidadeVento() {
		return velocidadeVento;
	}

	public void setVelocidadeVento(double velVento) {
		this.velocidadeVento = velVento;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	

	public static ResultWeather fromDTO(String cityName, WeatherDataDTO dto) {
		ResultWeather result = new ResultWeather();
		result.setTemp(dto.getMain().getTemp());
		result.setTempMin(dto.getMain().getTempMin());
		result.setTempMax(dto.getMain().getTempMax());
		result.setUmidade(dto.getMain().getHumidity());
		result.setPressao(dto.getMain().getPressure());
		result.setVelocidadeVento(dto.getWind().getSpeed());
		result.setData(dto.getDtTxt());
		result.setCidade(cityName);

		for (WeatherDescriptionDTO resultWeather : dto.getWeather()) {
			result.setIcone(resultWeather.getIcon());
			result.setDescricao(resultWeather.getDescription());
		}
		return result;
	}

}
