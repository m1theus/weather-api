package br.com.cast.clima.entity;

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
	private String data;
	
	@Column(name="icone")
	private String icone;
	
	@Column(name="descricao")
	private String descricao;

}
