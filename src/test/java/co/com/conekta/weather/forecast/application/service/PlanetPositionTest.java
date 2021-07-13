package co.com.conekta.weather.forecast.application.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.com.conekta.weather.forecast.domain.Planet;

public class PlanetPositionTest {

	private List<Planet> planets;

	@BeforeEach
	public void setup() {
		planets = new ArrayList<Planet>();
		Planet planetA = new Planet();
		planetA.setName("PlanetA");
		planetA.setTranslation(3);
		planetA.setSunDistance(100.0);
		Planet planetB = new Planet();
		planetB.setName("PlanetB");
		planetB.setTranslation(5);
		planetB.setSunDistance(200.0);
		Planet planetC = new Planet();
		planetC.setName("PlanetC");
		planetC.setTranslation(-5);
		planetC.setSunDistance(300.0);
		planets.add(planetA);
		planets.add(planetB);
		planets.add(planetC);
	}

	@Test
	public void whenPlanetIsNoAlignment() {
		planets.forEach(planet -> PlanetPosition.positionByDay(planet, 5));
		boolean isAligment = PlanetPosition.isPlanetAlignment(planets);
		assertFalse(isAligment);
	}

	@Test
	public void whenPlanetIsAlignment() {
		planets.forEach(planet -> PlanetPosition.positionByDay(planet, 0));
		boolean isAligment = PlanetPosition.isPlanetAlignment(planets);
		assertTrue(isAligment);
	}

	@Test
	public void whenSunInsideTriangle() {
		planets.forEach(planet -> PlanetPosition.positionByDay(planet, 110));
		boolean isSunInside = PlanetPosition.isSunInside(planets);
		assertTrue(isSunInside);
	}
	
	@Test
	public void whenSunNotInsideTriangle() {
		planets.forEach(planet -> PlanetPosition.positionByDay(planet, 300));
		boolean isSunInside = PlanetPosition.isSunInside(planets);
		assertFalse(isSunInside);
	}
	
}
