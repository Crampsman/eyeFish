package com.jean.service;

import static org.junit.Assert.*;
import com.jean.BaseTest;
import com.jean.servlet.model.owm.current.CurrentWeatherOWM;
import com.jean.servlet.model.owm.detail.DayWeatherDataOWM;
import com.jean.servlet.model.owm.GeneralWeatherStateOWM;
import com.jean.servlet.model.owm.hours.HoursWeatherDataOWM;

import org.junit.Test;

public class WeatherServiceTest extends BaseTest {

	@Test
	public void testGetDetailWeatherState() {
		String lat = "40.7143528";
		String log = "74.0059731";

		GeneralWeatherStateOWM<DayWeatherDataOWM> hoursWeatherStateOWM = weatherService.getDayWeatherState(lat, log);
		assertTrue(hoursWeatherStateOWM.getList().get(0).getClass().equals(DayWeatherDataOWM.class));
	}

	@Test
	public void testGetCurrentWeatherState(){
		String lat = "40.7143528";
		String log = "74.0059731";

		CurrentWeatherOWM currentWeatherOWM = weatherService.getCurrentWeatherState(lat, log);
		assertTrue(currentWeatherOWM.getClass().equals(CurrentWeatherOWM.class));
	}

	@Test
	public void testGetListWeatherState() {
		String lat = "40.7143528";
		String log = "74.0059731";

		GeneralWeatherStateOWM<HoursWeatherDataOWM> dayWeatherStateOWM = weatherService.getHourWeathers(lat, log);
		assertTrue((dayWeatherStateOWM.getList().get(0)).getClass().equals(HoursWeatherDataOWM.class));
//		assertTrue(hoursWeatherStateOWM.getList().get(0).getClass().equals(HoursWeatherDataOWM.class));

	}

}
