package com.jean.util;

import java.util.List;

import com.jean.CustomDfmException;
import com.jean.entity.AbstractFish;

public interface FishAnalyzer {

    List<String> getHungryRating(AbstractFish fish) throws CustomDfmException;
}