package co.com.conekta.weather.forecast.application.in.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import co.com.conekta.weather.forecast.adapter.out.dto.ForecastDto;
import co.com.conekta.weather.forecast.adapter.out.dto.WeatherDto;
import co.com.conekta.weather.forecast.application.out.entity.Forecast;
import co.com.conekta.weather.forecast.application.out.entity.Weather;

@Component
public class WeatherTransform {

	public Function<Weather, WeatherDto> transformWeather = new Function<Weather, WeatherDto>() {

		public WeatherDto apply(Weather weather) {
			WeatherDto weatherDto = new WeatherDto();
			if (weather != null) {
				weatherDto.setDia(weather.getDay());
				weatherDto.setClima(weather.getWeather());
			}
			return weatherDto;
		}
	};
	
	public Function<Forecast, ForecastDto> transforForecast = new Function<Forecast, ForecastDto>() {

		public ForecastDto apply(Forecast forecast) {
			ForecastDto forecastDto = new ForecastDto();
			if (forecast != null) {
				forecastDto.setId(forecast.getId());
				forecastDto.setPeriodoSequia(forecast.getDroughtPeriods());
				forecastDto.setPeriodoLluvia(forecast.getRainyPeriods());
				forecastDto.setPeriodoOptimo(forecast.getOptimalPeriods());
				forecastDto.setDiaMasLluvioso(forecast.getMaximumRainDay());
			}
			return forecastDto;
		}
	};

}
