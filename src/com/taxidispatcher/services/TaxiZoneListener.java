package com.taxidispatcher.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.taxidispatcher.dao.TaxiZoneDao;
import com.taxidispatcher.dao.ZoneDao;
import com.taxidispatcher.model.Taxi;
import com.taxidispatcher.model.TaxiZone;
import com.taxidispatcher.model.Zone;
/**
 * A  class that initializes the zones.
 * @author sghimire
 *
 */
public class TaxiZoneListener {

	private Map<String, Queue<Taxi>> taxiOnZones = new LinkedHashMap<String,Queue<Taxi>>();
	
	private ZoneDao zoneDao;
	private TaxiZoneDao taxiZoneDao;

	public void setZoneDao(ZoneDao zoneDao) {
		this.zoneDao = zoneDao;
	}

	public void setTaxiZoneDao(TaxiZoneDao taxiZoneDao) {
		this.taxiZoneDao = taxiZoneDao;
	}

	public synchronized Map<String, Queue<Taxi>> getInstance(){
		// if(taxiOnZones.isEmpty()){
			/**
			 * test zone from test stub.
			 */
			//taxiOnZones  = getTestTaxiOnZones();
			taxiOnZones = getTaxiOnZones();
		// }
		return taxiOnZones;
	}
	
	/**
	 * get list of zones by their map.
	 * @return
	 */
	private synchronized Map<String,Queue<Taxi>> getTaxiOnZones(){
		Map<String,Queue<Taxi>> zones = new LinkedHashMap<String,Queue<Taxi>>();		
		/**
		 * get all the zone supported to create taxi logged in the zone.
		 */		
		List<Zone> zoneList = zoneDao.getZones();
		for(Zone zone : zoneList){	
			/**
			 * get taxi on that zone order by login time and hasn't logged out in descending order.
			 * (Need to figure out for how long can a taxi be logged in in the zone)
			 */
			List<TaxiZone> taxiList = taxiZoneDao.getTaxiZoneOrderByLoginTime(zone);
			/**
			 * create queue for each taxi.
			 */
			Queue<Taxi> taxiOnZone = new LinkedBlockingQueue<Taxi>();
			for(TaxiZone taxiZone: taxiList){
				Taxi taxi = taxiZone.getTblTaxi();
				taxiOnZone.add(taxi);
			}
			
			String zoneCode = zone.getZoneCode();
			zones.put(zoneCode, taxiOnZone);
			
		}
		
		return zones;
	}
	
	/**
	 * get list of zones by their map.
	 * @return
	 */
	private Map<String,Queue<Taxi>> getTestTaxiOnZones(){
		Map<String,Queue<Taxi>> zones = new LinkedHashMap<String,Queue<Taxi>>();		
		//Taxis on Zone 1.
		Queue<Taxi> taxiOnZone1 = new LinkedBlockingQueue<Taxi>();
		Taxi taxi1 = new Taxi();
		taxi1.setTaxiId(1);
		taxiOnZone1.add(taxi1);		
		Taxi taxi2 = new Taxi();
		taxi1.setTaxiId(2);
		taxiOnZone1.add(taxi2);
		zones.put("zone1", taxiOnZone1);
		//Taxis on Zone 2
		Queue<Taxi> taxiOnZone2 = new LinkedBlockingQueue<Taxi>();
		Taxi taxi3 = new Taxi();
		taxi1.setTaxiId(3);
		taxiOnZone1.add(taxi3);		
		Taxi taxi4 = new Taxi();
		taxi1.setTaxiId(4);
		taxiOnZone1.add(taxi4);
		zones.put("zone2", taxiOnZone2);
		
		return zones;
	}
		
}
