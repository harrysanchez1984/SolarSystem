package co.com.conekta.weather.forecast.application.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.conekta.weather.forecast.application.out.entity.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {

}
