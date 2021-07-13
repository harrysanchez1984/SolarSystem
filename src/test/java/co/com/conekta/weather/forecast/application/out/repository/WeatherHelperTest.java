package co.com.conekta.weather.forecast.application.out.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import co.com.conekta.weather.forecast.application.out.entity.Weather;
import co.com.conekta.weather.forecast.application.service.PlanetPosition;
import co.com.conekta.weather.forecast.domain.Planet;
import co.com.conekta.weather.forecast.domain.WeatherType;

public class WeatherHelperTest {
	
	private WeatherHelper weatherHelper;
	
	@Mock
	private ForecastRepository forecastRepository;
	
	private List<Planet> planets;
	private static final Integer DROGHT_DAY = 0;
	private static final Integer RAINY_DAY = 51;
	private static final Integer OPTIMAL_DAY = 14;
	private static final Integer UNKNOW_DAY = 5;
	
	@BeforeEach
	public void setup() {
		weatherHelper = new WeatherHelper(forecastRepository);
		planets = new ArrayList<Planet>();
		Planet planetA = new Planet();
		planetA.setName("PlanetA");
		planetA.setTranslation(1);
		planetA.setSunDistance(500.0);
		Planet planetB = new Planet();
		planetB.setName("PlanetB");
		planetB.setTranslation(-5);
		planetB.setSunDistance(1000.0);
		Planet planetC = new Planet();
		planetC.setName("PlanetC");
		planetC.setTranslation(3);
		planetC.setSunDistance(2000.0);
		planets.add(planetA);
		planets.add(planetB);
		planets.add(planetC);
	}
	
	@Test
	public void whenDroughtWeather() {
		planets.forEach(planet -> PlanetPosition.positionByDay(planet, DROGHT_DAY));
		Weather weather = weatherHelper.obtainWeatherByDay(DROGHT_DAY, planets);
		assertEquals(WeatherType.DROGHT.toString(), weather.getWeather());
	}
	
	@Test
	public void whenRainyWeather() {
		planets.forEach(planet -> PlanetPosition.positionByDay(planet, RAINY_DAY));
		Weather weather = weatherHelper.obtainWeatherByDay(RAINY_DAY, planets);
		assertEquals(WeatherType.RAIN.toString(), weather.getWeather());
	}
	
	@Test
	public void whenOptimalWeather() {
		planets.forEach(planet -> PlanetPosition.positionByDay(planet, OPTIMAL_DAY));
		Weather weather = weatherHelper.obtainWeatherByDay(OPTIMAL_DAY, planets);
		assertEquals(WeatherType.OPTIMUM.toString(), weather.getWeather());
	}
	
	@Test
	public void whenUnknowWeather() {
		planets.forEach(planet -> PlanetPosition.positionByDay(planet, UNKNOW_DAY));
		Weather weather = weatherHelper.obtainWeatherByDay(UNKNOW_DAY, planets);
		assertEquals(WeatherType.UNKNOW.toString(), weather.getWeather());
	}

}
