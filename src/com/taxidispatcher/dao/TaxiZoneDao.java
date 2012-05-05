package com.taxidispatcher.dao;

import java.util.List;

import com.taxidispatcher.model.TaxiZone;
import com.taxidispatcher.model.Zone;

public interface TaxiZoneDao {

	List<TaxiZone> getTaxiZoneOrderByLoginTime(Zone zone);
	
}
