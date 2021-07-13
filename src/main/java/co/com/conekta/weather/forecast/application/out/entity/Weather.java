package co.com.conekta.weather.forecast.application.out.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Weather {
	
	@Id
	private Integer day;
	private String weather;
	
}
