package co.com.conekta.weather.forecast.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.conekta.weather.forecast.adapter.in.controller.WeatherController;
import co.com.conekta.weather.forecast.application.service.CalculateWeather;

@Configuration
public class ForecastWeather {
	
	private CalculateWeather calculate;
	
	public ForecastWeather(CalculateWeather calculate) {
		this.calculate = calculate;
	}
	
	@Bean
	public void forecast() {
		new WeatherController(calculate).calculateWeather();
	}

}
