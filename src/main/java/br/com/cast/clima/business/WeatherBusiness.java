package br.com.cast.clima.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cast.clima.controller.WeatherClient;
import br.com.cast.clima.entity.ResultWeather;
import br.com.cast.clima.entity.Weather;
import br.com.cast.clima.entity.WeatherDTO;
import br.com.cast.clima.entity.WeatherDataDTO;
import br.com.cast.clima.entity.WeatherDescriptionDTO;
import br.com.cast.clima.repository.WeatherRepository;

@Service
@Transactional
public class WeatherBusiness {

	@Autowired
	private WeatherRepository repository;

	@Autowired
	private WeatherClient client;

	public List<ResultWeather> getWeather(String cityName) {

		List<Weather> lista = repository.findAllByCidade(cityName);
		List<ResultWeather> resultList = new ArrayList<>();
		Map<String, ResultWeather> map = new HashMap<>();

		if (lista != null && lista.size() >= 1) {
			for (Weather w : lista) {
				ResultWeather result = new ResultWeather();
				result.setTemp(w.getTemp());
				result.setTempMin(w.getTempMin());
				result.setTempMax(w.getTempMax());
				result.setPressao(w.getPressao());
				result.setUmidade(w.getUmidade());
				result.setVelocidadeVento(w.getVelocidadeVento());
				result.setDescricao(w.getDescricao());
				result.setIcone(w.getIcone());
				result.setData(w.getData());
				result.setCidade(w.getCidade());

				resultList.add(result);
			}
		} else {
			WeatherDTO weatherDTO = client.getWeather(cityName);

			for (WeatherDataDTO dto : weatherDTO.getList()) {
				String data = dto.getDtTxt().substring(0, 10);

				if (map.containsKey(data)) {
					continue;
				}

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

				map.put(data, result);
				resultList.add(result);
				insert(result);
			}

		}
		return resultList;
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
			weather.setData(result.getData());
			weather.setCidade(result.getCidade());
		}
		repository.inserir(weather);
	}
}
