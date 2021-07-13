package co.com.conekta.weather.forecast.application.service;

import java.awt.geom.Point2D;
import java.util.List;

import org.apache.commons.math3.util.Precision;

import co.com.conekta.weather.forecast.domain.Planet;
import lombok.Data;

public class PlanetPosition {

	private static final Double TOLERANCE_LIMIT = 0.8;

	public static boolean isPlanetAlignment(List<Planet> planets) {
		CalculationFactors factors = new PlanetPosition.CalculationFactors();
		Double PearsonCoefficient = 0.0;

		for (Planet planet : planets) {
			factors.setXy(factors.getXy() + (planet.getPosition().getX() * planet.getPosition().getY()));
			factors.setX(planet.getPosition().getX() + factors.getX());
			factors.setY(planet.getPosition().getY() + factors.getY());
			factors.setPowX(Math.pow(planet.getPosition().getX(), 2) + factors.getPowX());
			factors.setPowY(Math.pow(planet.getPosition().getY(), 2) + factors.getPowY());
		}
		factors.setPlanetCount(planets.size());
		PearsonCoefficient = calculatePearsonCoefficient(factors);

		return (PearsonCoefficient > TOLERANCE_LIMIT || Double.isNaN(PearsonCoefficient));
	}

	public static boolean isSunInside(List<Planet> planets) {
		Point2D sun = new java.awt.geom.Point2D.Double(0.0,0.0);
		Double abc = calculateDifferenceProducts(planets.get(0).getPosition(), planets.get(1).getPosition(),
				planets.get(2).getPosition());
		Double abd = calculateDifferenceProducts(planets.get(0).getPosition(), planets.get(1).getPosition(),
				sun);
		Double bcd = calculateDifferenceProducts(planets.get(1).getPosition(), planets.get(2).getPosition(),
				sun);
		Double acd = calculateDifferenceProducts(planets.get(0).getPosition(), planets.get(2).getPosition(),
				sun);

		return abc * abd > 0 && abc * bcd > 0 && abc * acd > 0;
	}

	public static void positionByDay(Planet planet, Integer day) {
		planet.setAngle((planet.getTranslation() * day) % 360);
		if (planet.getTranslation() < 0) {
			planet.setAngle(360 + planet.getAngle());
		}
		planet.setRadians(Math.toRadians(planet.getAngle()));
		double xPosition = Precision.round(Math.cos(planet.getRadians()) * planet.getSunDistance(), 12);
		double yPosition = Precision.round(Math.sin(planet.getRadians()) * planet.getSunDistance(), 12);
		planet.setPosition(new java.awt.geom.Point2D.Double(xPosition, yPosition));
	}

	private static Double calculatePearsonCoefficient(CalculationFactors factors) {
		Double numerator = (factors.getPlanetCount() * factors.getXy()) - (factors.getX() * factors.getY());
		Double denominatorX = Math.sqrt((factors.getPlanetCount() * factors.getPowX()) - Math.pow(factors.getX(), 2));
		Double denominatorY = Math.sqrt((factors.getPlanetCount() * factors.getPowY()) - Math.pow(factors.getY(), 2));
		Double denominator = denominatorX * denominatorY;

		return Math.abs(Math.pow(numerator / denominator, 2));
	}

	private static Double calculateDifferenceProducts(Point2D coordA, Point2D coordB, Point2D coordC) {
		return (coordB.getX() - coordA.getX()) * (coordC.getY() - coordA.getY())
				- (coordB.getY() - coordA.getY()) * (coordC.getX() - coordA.getX());
	}

	public static Double calculateTrianglePerimeter(List<Planet> planets) {
		Double ab = distanceBetweenPoints(planets.get(0).getPosition(), planets.get(1).getPosition());
		Double ac = distanceBetweenPoints(planets.get(0).getPosition(), planets.get(2).getPosition());
		Double bc = distanceBetweenPoints(planets.get(1).getPosition(), planets.get(2).getPosition());
		return ab + ac + bc;
	}

	private static Double distanceBetweenPoints(Point2D coordA, Point2D coordB) {
		return Math.sqrt(Math.pow((coordB.getX() - coordA.getX()), 2) + Math.pow((coordB.getY() - coordA.getY()), 2));
	}

	@Data
	static class CalculationFactors {
		private Double xy = 0.0;
		private Double x = 0.0;
		private Double y = 0.0;
		private Double powX = 0.0;
		private Double powY = 0.0;
		private Integer planetCount;

	}

}
