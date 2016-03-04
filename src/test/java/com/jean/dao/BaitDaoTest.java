package com.jean.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jean.BaseTest;
import com.jean.CustomDfmException;
import com.jean.analyzers.weather.ConstantsAnalyzer.BRIGHT_LEVEL;
import com.jean.entity.Bait;
import com.jean.entity.BaitProperties;

public class BaitDaoTest extends BaseTest {
    
    private int fishId;
    private double cloudLevel;
    private Date date;
    List<Bait> baits;
    List<BaitProperties> props;
    
    @Before
    public void init(){
	fishId = 7;
	cloudLevel = 27;
	date = Date.valueOf("2016-06-22");
    }
    
    @Test
    public void getBaitsForFishByDateTest() throws CustomDfmException{
	baits = baitDao.getBaitsForFishByDate(date, fishId);
	assertTrue(!baits.isEmpty());
    }
    
    @Test
    public void getBaitColorsByCloudLevelTest() throws CustomDfmException{
	props = baitDao.getBaitColors(cloudLevel);
	assertTrue(!props.isEmpty());
    }
    
    @Test
    public void getBaitColorByNameTest() throws CustomDfmException{
	props = baitDao.getBaitColors(BRIGHT_LEVEL.DARK.toString());
	assertTrue(!props.isEmpty());
    }
}
