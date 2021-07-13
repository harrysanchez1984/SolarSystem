package co.com.conekta.weather.forecast.adapter.out.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ForecastDto {
	
	@JsonIgnore
	private Integer id;
	private Integer periodoSequia;
	private Integer periodoLluvia;
	private Integer periodoOptimo;
	private Integer diaMasLluvioso;

}
