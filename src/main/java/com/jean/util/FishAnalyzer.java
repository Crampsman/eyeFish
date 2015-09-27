package com.jean.util;

import java.util.List;

import com.jean.CustomDfmException;
import com.jean.entity.Fish;

public interface FishAnalyzer {

    List<String> getHungryRating(Fish fish) throws CustomDfmException;
}