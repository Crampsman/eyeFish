package com.jean.analyzers.fish;

import java.util.*;


import com.jean.DaoDfmException;
import com.jean.enums.Hungry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jean.CustomDfmException;
import com.jean.dao.WeatherDao;
import com.jean.entity.Fish;
import com.jean.entity.Weather;

@Component
public class FishAnalyzerImpl implements FishAnalyzer {

    @Autowired
    private WeatherDao weatherDao;

    public List<String> getHungryRating(Fish fish) throws CustomDfmException, DaoDfmException {

        List<Weather> listWeather = weatherDao.getLimitWeatherByDate(new Date());

//        Map<String, WeatherState> hungry = fish.getHungry();



        List<String> listState = new ArrayList<>();


        for (Weather weather : listWeather) {

//            for (Map.Entry<String, WeatherState> entry : hungry.entrySet()) {
//                WeatherState param = entry.getValue();
//                    if (param.getMin() < weather.getTempDay() || param.getMax() < weather.getTempDay()) {
//                        //raiting.put(entry.getKey(), new Integer(value));
//                    }
//
//            }

        }

        return listState;

    }

//    public FishAnalyzerImpl() {
//
//////        this.raiting = ;
////        for (Hungry stateMark : Hungry.values()) {
//////            Map<String, Integer> raiting = new HashMap<>();
////            raiting.put(stateMark.toString(), null);
////        }
//    }


}
