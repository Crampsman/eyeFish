package com.jean.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.jean.Constants;
import com.jean.servlet.model.Coordinates;

/**
 * Created by stas on 18.07.15.
 */
public class Utils {

	private static Map<String, MinMaxHolder> degreesMap;

	static {
		degreesMap = new HashMap<String, MinMaxHolder>();

		degreesMap.put("N", new MinMaxHolder(348.76f, 11.25f));
		degreesMap.put("NNE", new MinMaxHolder(11.26f, 33.75f));
		degreesMap.put("NE", new MinMaxHolder(33.76f, 56.25f));
		degreesMap.put("ENE", new MinMaxHolder(56.26f, 78.75f));
		degreesMap.put("E", new MinMaxHolder(78.76f, 101.25f));
		degreesMap.put("ESE", new MinMaxHolder(101.26f, 123.75f));
		degreesMap.put("SE", new MinMaxHolder(123.76f, 146.25f));
		degreesMap.put("SSE", new MinMaxHolder(146.26f, 168.75f));
		degreesMap.put("S", new MinMaxHolder(168.76f, 191.25f));
		degreesMap.put("SSW", new MinMaxHolder(191.26f, 213.75f));
		degreesMap.put("SW", new MinMaxHolder(213.76f, 236.25f));
		degreesMap.put("WSW", new MinMaxHolder(236.26f, 258.75f));
		degreesMap.put("W", new MinMaxHolder(258.76f, 281.25f));
		degreesMap.put("WNW", new MinMaxHolder(281.26f, 303.75f));
		degreesMap.put("NW", new MinMaxHolder(303.76f, 326.25f));
		degreesMap.put("NNW", new MinMaxHolder(326.26f, 348.75f));

	}

	public static float kelvinToCelsius(float kelvinTemp) {
		float c = (kelvinTemp - Constants.KELVIN_VALUE) * 1;

		return round(c);
	}

	public static float millibarToMmHg(float millibar) {
		return millibar / Constants.MILLIBAR_VALUE;
	}

	private static float round(float number) {
		float tmp = number * 100;
		return (float) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / 100;
	}

	public static boolean isPointCircle(double lon, double lat, Coordinates coordinates) {
		double hashLon = coordinates.getLongitude();
		double hashLat = coordinates.getLatitude();
		int R = 5;

		return Math.pow((lon - hashLon), 2) + Math.pow((lat - hashLat), 2) <= Math.pow(R, 2);

	}

	public static String getDirection(double degree) {

		String result = "";

		for (Entry<String, MinMaxHolder> entry : degreesMap.entrySet()) {
			if ((entry.getValue().startValue <= degree & entry.getValue().endValue >= degree)
					|| entry.getValue().endValue == degree) {
				result = entry.getKey();
			}
		}
		return result;
	}
	
	public static Date getJavaUtilDate(String dateText) {
		Date date = new Date();
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = formatter.parse(dateText.substring(0, 11));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}

class MinMaxHolder {

	float startValue;
	float endValue;

	public MinMaxHolder(float startDegree, float endDegree) {
		super();
		this.startValue = startDegree;
		this.endValue = endDegree;
	}
}
