package co.com.conekta.weather.forecast.application.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import co.com.conekta.weather.forecast.application.out.repository.WeatherHelper;
import co.com.conekta.weather.forecast.application.out.repository.WeatherRepository;
import co.com.conekta.weather.forecast.domain.Planet;
import co.com.conekta.weather.forecast.domain.SolarSystem;

@Service
public class PredictWeather implements CalculateWeather {

	private SolarSystem solarSystem;
	private WeatherHelper weatherHelper;
	private WeatherRepository weatherRepository;

	private static final Integer DAYS_TO_FORECAST = 3600;

	public PredictWeather(SolarSystem solarSystem, WeatherHelper weatherHelper, WeatherRepository weatherRepository) {
		this.solarSystem = solarSystem;
		this.weatherHelper = weatherHelper;
		this.weatherRepository = weatherRepository;
	}

	@Override
	public void calculate() {
		final Integer[] i = { 0 };
		while (i[0] < DAYS_TO_FORECAST) {
			this.solarSystem.getPlanets().forEach(planet -> {
				PlanetPosition.positionByDay(planet, i[0]);
			});
			weatherRepository
					.save(weatherHelper.obtainWeatherByDay(i[0], (ArrayList<Planet>) this.solarSystem.getPlanets()));
			i[0]++;
		}
		weatherHelper.recordWeatherForecast();
	}

}
