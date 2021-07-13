package co.com.conekta.weather.forecast.application.out.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.com.conekta.weather.forecast.application.out.entity.Forecast;
import co.com.conekta.weather.forecast.application.out.entity.Weather;
import co.com.conekta.weather.forecast.application.service.PlanetPosition;
import co.com.conekta.weather.forecast.domain.Planet;
import co.com.conekta.weather.forecast.domain.WeatherType;

@Component
public class WeatherHelper {
	
	private ForecastRepository forecastRepository;
	private Integer sequia = 1;
	private Integer lluvia = 0;
	private Integer optimo = 0;
	private WeatherType period = WeatherType.DROGHT;
	private Double maxPerimeter = 0.0;
	private Integer rainyDay;
	private Forecast forecast;
	private Planet sun = new Planet("sun", 0, 0.0, 0, 0.0, new java.awt.geom.Point2D.Double(0.0, 0.0));
	
	public WeatherHelper(ForecastRepository forecastRepository) {
		this.forecastRepository = forecastRepository;
	}
	
	public void recordWeatherForecast() {
		forecast = new Forecast();
		forecast.setDroughtPeriods(sequia);
		forecast.setRainyPeriods(lluvia);
		forecast.setMaximumRainDay(rainyDay);
		forecast.setOptimalPeriods(optimo);
		forecastRepository.save(forecast);
	}

	@SuppressWarnings("unchecked")
	public Weather obtainWeatherByDay(Integer day, List<Planet> planets) {
		Weather weather = new Weather();
		weather.setDay(day);
		ArrayList<Planet> solarSystemSun = (ArrayList<Planet>)((ArrayList<Planet>) planets).clone();
		solarSystemSun.add(sun);
		if (PlanetPosition.isPlanetAlignment(planets)) {
			if (PlanetPosition.isPlanetAlignment(solarSystemSun)) {
				setWeatherPeriod(WeatherType.DROGHT);
				weather.setWeather(WeatherType.DROGHT.toString());
			} else {
				setWeatherPeriod(WeatherType.OPTIMUM);
				weather.setWeather(WeatherType.OPTIMUM.toString());
			}
		} else if (PlanetPosition.isSunInside(planets)) {
			setWeatherPeriod(WeatherType.RAIN);
			obtainRainDay(PlanetPosition.calculateTrianglePerimeter(planets), day);
			weather.setWeather(WeatherType.RAIN.toString());
		} else {
			setWeatherPeriod(WeatherType.UNKNOW);
			weather.setWeather(WeatherType.UNKNOW.toString());
		}
		return weather;
	}

	private void obtainRainDay(Double currentPerimeter, int day) {
		if (maxPerimeter < currentPerimeter) {
			maxPerimeter = currentPerimeter;
			rainyDay = day;
		}

	}

	private void setWeatherPeriod(WeatherType currentPeriod) {
		if (period != currentPeriod) {
			period = currentPeriod;
			if (period == WeatherType.DROGHT)
				sequia++;
			else if (period == WeatherType.RAIN)
				lluvia++;
			else if (period == WeatherType.OPTIMUM)
				optimo++;
		}
	}

}
