package co.com.conekta.weather.forecast.application.out.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.conekta.weather.forecast.application.out.entity.Forecast;

public interface ForecastRepository extends JpaRepository<Forecast, Integer> {

	@Query(value = "SELECT * FROM forecast LIMIT 1", nativeQuery = true)
	Optional<Forecast> getWeatherForecast();

}
