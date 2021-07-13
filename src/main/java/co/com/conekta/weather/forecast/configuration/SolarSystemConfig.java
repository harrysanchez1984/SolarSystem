package co.com.conekta.weather.forecast.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.conekta.weather.forecast.domain.Planet;
import co.com.conekta.weather.forecast.domain.SolarSystem;

@Configuration
public class SolarSystemConfig {
	
	@Bean
	public SolarSystem initSolarSystem() {
		List<Planet> planets = new ArrayList<Planet>();
		Planet ferengi = createPlanet("Ferengi", 1, 500.0);
		Planet vulcano = createPlanet("Vulcano", -5, 1000.0);
		Planet betasoide = createPlanet("Betasoide", 3, 2000.0);
		planets.add(ferengi);
		planets.add(vulcano);
		planets.add(betasoide);
		return new SolarSystem(planets);
	}

	private Planet createPlanet(String name, Integer translation, Double SunDistance) {
		Planet planet = new Planet();
		planet.setName(name);
		planet.setTranslation(translation);
		planet.setSunDistance(SunDistance);
		return planet;
	}

}
