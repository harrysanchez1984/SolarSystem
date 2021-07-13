package co.com.conekta.weather.forecast.domain;

import java.awt.geom.Point2D;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planet {

	private String name;
	private Integer translation;
	private Double SunDistance;
	private Integer angle;
	private Double radians;
	private Point2D position;

}
