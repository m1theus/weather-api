package br.com.cast.weather.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.cast.weather.entity.Weather;

@Repository
public class WeatherRepository{
	
    @Autowired
	public EntityManager entityManager;

	
	public void inserir(Weather weather) {
		entityManager.persist(weather);
	}


	public void delete(String cityName) {
		StringBuilder hql = new StringBuilder();
		hql.append("DELETE FROM ")
			.append(Weather.class.getName())
			.append(" WHERE cidade = :cidade");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("cidade", cityName);
		query.executeUpdate();
	}


	@SuppressWarnings("unchecked")
	public List<Weather> findAllByCidade(String cityName) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM ")
			.append(Weather.class.getName())
				.append(" WHERE cidade = :cidade and data >= :dataAtual");
		
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("cidade", cityName);
		query.setParameter("dataAtual", new Date());
		return query.getResultList();
	}
	
}
