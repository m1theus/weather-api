package br.com.cast.clima.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.cast.clima.entity.Weather;

@Repository
public class WeatherRepository{
	
    @Autowired
	public EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Weather> findAllByCidade(String cidade){
		StringBuilder hql = new StringBuilder();
		hql.append("FROM ")
			.append(Weather.class.getName())
				.append(" WHERE cidade = :cidade");
		
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("cidade", cidade);
		return query.getResultList();
	}

	
	public void inserir(Weather weather) {
		entityManager.persist(weather);
	}
	
}
