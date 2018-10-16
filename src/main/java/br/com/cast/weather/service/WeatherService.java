package br.com.cast.weather.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cast.weather.controller.WeatherClient;
import br.com.cast.weather.entity.ResultWeather;
import br.com.cast.weather.entity.Weather;
import br.com.cast.weather.entity.WeatherDTO;
import br.com.cast.weather.entity.WeatherDataDTO;
import br.com.cast.weather.repository.WeatherRepository;

@Service
@Transactional
public class WeatherService {

	@Autowired
	private WeatherRepository repository;

	@Autowired
	private WeatherClient client;

	public List<ResultWeather> getWeather(String cityName) {

		List<Weather> listWeather = repository.findAllByCidade(cityName);
		List<ResultWeather> listResultWeather = new ArrayList<>();

		if (listWeather.size() >= 5) {
			
			for (Weather weather : listWeather) {
				ResultWeather result = Weather.fromEntity(weather, cityName);
				listResultWeather.add(result);
			}
			
		} else {
			
			deleteByCityName(cityName);
			Map<String, ResultWeather> map = new HashMap<>();
			WeatherDTO weatherDTO = client.getWeather(cityName);

			for (WeatherDataDTO dto : weatherDTO.getList()) {
				
				String data = dto.getDtTxt().substring(0, 10);
				if (map.containsKey(data)) {
					continue;
				}

				ResultWeather result = ResultWeather.fromDTO(cityName, dto);

				map.put(data, result);
				listResultWeather.add(result);
				insert(result);
			}

		}
		return listResultWeather;
	}

	private void deleteByCityName(String cityName) {
		repository.delete(cityName);
	}

	private void insert(ResultWeather result) {
		Weather weather = new Weather();
		if (result != null) {
			weather.setTemp(result.getTemp());
			weather.setTempMin(result.getTempMin());
			weather.setTempMax(result.getTempMax());
			weather.setPressao(result.getPressao());
			weather.setUmidade(result.getUmidade());
			weather.setVelocidadeVento(result.getVelocidadeVento());
			weather.setDescricao(result.getDescricao());
			weather.setIcone(result.getIcone());
			
			Date data = stringToDate(result.getData().substring(0, 10));
			
			weather.setData(data);
			weather.setCidade(result.getCidade());
		}
		repository.inserir(weather);
	}
	
	public Date stringToDate(String data) {
		Date dataFormatada = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dataFormatada = format.parse(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataFormatada;
	}
}
