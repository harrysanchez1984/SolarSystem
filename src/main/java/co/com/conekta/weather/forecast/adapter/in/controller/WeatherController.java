package co.com.conekta.weather.forecast.adapter.in.controller;

import co.com.conekta.weather.forecast.application.service.CalculateWeather;

public class WeatherController {
	
	private CalculateWeather calculateWeather;
	
	public WeatherController(CalculateWeather calculateWeather) {
		this.calculateWeather = calculateWeather;
	}
	
	public void calculateWeather() {
		this.calculateWeather.calculate();
	}

}
