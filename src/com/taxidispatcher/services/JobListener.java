package com.taxidispatcher.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taxidispatcher.dao.JobDao;
import com.taxidispatcher.dao.ZoneDao;
import com.taxidispatcher.model.Job;
import com.taxidispatcher.model.Zone;

public class JobListener {
	
	protected final Log logger = LogFactory.getLog(getClass());
		
	private Map<String, Queue<Job>> jobOnZones = new LinkedHashMap<String,Queue<Job>>();
		
	private ZoneDao zoneDao;
	private JobDao jobDao;

	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}

	public void setZoneDao(ZoneDao zoneDao) {
		this.zoneDao = zoneDao;
	}
	
	public synchronized Map<String, Queue<Job>> getInstance(){
		return getJobQueue();		
	}

	/**
	 * get all the valid jobs order by created time in ascending order.
	 * @return
	 */
	private Map<String, Queue<Job>> getJobQueue() {			
		/**
		 * get all the zone
		 */		
		List<Zone> zoneList = zoneDao.getZones();
		for(Zone zone : zoneList){	
			/**
			 * get job on that zone order by pickup time in descending order.			 
			 */
			List<Job> jobOnZoneList = jobDao.getJobOnZonesOrderByPickUpTime(zone); 
			Queue<Job> jobOnZoneQueue = new LinkedBlockingQueue<Job>(jobOnZoneList);
			String zoneCode = zone.getZoneCode();
			jobOnZones.put(zoneCode, jobOnZoneQueue);			
		}		
		return jobOnZones;		
	}
	

}
