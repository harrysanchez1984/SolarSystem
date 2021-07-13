package co.com.conekta.weather.forecast.adapter.in.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.conekta.weather.forecast.adapter.out.dto.ForecastDto;
import co.com.conekta.weather.forecast.adapter.out.dto.WeatherDto;
import co.com.conekta.weather.forecast.application.service.WeatherForecast;
import co.com.conekta.weather.forecast.exception.ServiceException;

@RestController
@RequestMapping("/weather/forecast")
public class ForecastController {
	
	private WeatherForecast weatherForecast;
	
	public ForecastController(WeatherForecast weatherForecast) {
		this.weatherForecast = weatherForecast;
	}
	
	@GetMapping("/clima")
	public ResponseEntity<WeatherDto> getWeatherByDay(@RequestParam(required = true) Integer dia) {
		Optional<WeatherDto> response = weatherForecast.getWeatherByDay(dia);
		if(!response.isPresent()){
			throw new ServiceException("Dia invalida o sin informacion", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<WeatherDto>(response.get(), HttpStatus.OK);
	}
	
	@GetMapping("/pronostico")
	public ResponseEntity<ForecastDto> getWeatherForecast(){
		Optional<ForecastDto> response = weatherForecast.getWeatherForecast();
		if(!response.isPresent()){
			throw new ServiceException("Sin pronostico del clima", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ForecastDto>(response.get(), HttpStatus.OK);
	}
}
