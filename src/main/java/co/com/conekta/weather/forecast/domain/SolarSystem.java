package co.com.conekta.weather.forecast.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SolarSystem {
	
	private List<Planet> planets;

}
