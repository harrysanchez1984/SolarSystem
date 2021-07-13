package co.com.conekta.weather.forecast.application.service;

import java.util.Optional;

import co.com.conekta.weather.forecast.adapter.out.dto.ForecastDto;
import co.com.conekta.weather.forecast.adapter.out.dto.WeatherDto;

public interface WeatherForecast {
	
	Optional<WeatherDto> getWeatherByDay(Integer day);
	Optional<ForecastDto> getWeatherForecast();

}
