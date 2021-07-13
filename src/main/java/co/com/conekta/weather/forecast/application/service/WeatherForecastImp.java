package co.com.conekta.weather.forecast.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.com.conekta.weather.forecast.adapter.out.dto.ForecastDto;
import co.com.conekta.weather.forecast.adapter.out.dto.WeatherDto;
import co.com.conekta.weather.forecast.application.in.mapper.WeatherTransform;
import co.com.conekta.weather.forecast.application.out.repository.ForecastRepository;
import co.com.conekta.weather.forecast.application.out.repository.WeatherRepository;

@Service
public class WeatherForecastImp implements WeatherForecast {

	private WeatherRepository weatherRepository;
	private ForecastRepository forecastRepository;
	private WeatherTransform weatherTransform;

	public WeatherForecastImp(WeatherRepository weatherRepository, ForecastRepository forecastRepository,
			WeatherTransform weatherTransform) {
		this.weatherRepository = weatherRepository;
		this.forecastRepository = forecastRepository;
		this.weatherTransform = weatherTransform;
	}

	@Override
	public Optional<WeatherDto> getWeatherByDay(Integer day) {
		return weatherRepository.findById(day).map(weatherTransform.transformWeather);
	}

	@Override
	public Optional<ForecastDto> getWeatherForecast() {
		return forecastRepository.getWeatherForecast().map(weatherTransform.transforForecast);
	}

}
