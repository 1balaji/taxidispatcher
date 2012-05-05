package com.taxidispatcher.services;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taxidispatcher.android.server.MessageUtil;
import com.taxidispatcher.constant.ConstantUtil;
import com.taxidispatcher.model.Job;
import com.taxidispatcher.model.Taxi;
import com.taxidispatcher.model.Zone;

/**
 * Class to dispatch the job based on the pick up time
 * and the available taxis.   
 * @author sghimire
 *
 */
public class JobDispatchListener{

	protected final Log logger = LogFactory.getLog(getClass());

	private TaxiZoneListener taxiZoneListener;
	private JobListener jobListener;

	public void setTaxiZoneListener(TaxiZoneListener taxiZoneListener) {
		this.taxiZoneListener = taxiZoneListener;
	}

	public void setJobListener(JobListener jobListener) {
		this.jobListener = jobListener;
	}

	/**
	 * Job dispatcher method.
	 * 1. For each zone finds out if there is any job. 
	 * 2. If there is any job for that zone calculate the  PICKUP_TIME - NOW time
	 * is less than or equal to an hour (by default) or JOB_DISPATCH_TIME allocated for that zone
	 * 3. Dispatch the job to the taxi device id (In this case the device_id is the android phone id
	 * given to that taxi.)
	 */
	public void dispatchJob(){
		Map<String, Queue<Taxi>> taxisInZoneQueue = taxiZoneListener.getInstance();
		Map<String, Queue<Job>> jobsInZoneQueue = jobListener.getInstance();
		/**
		 * loop through each zones and check to see if there are valid jobs.
		 */
		for (String zoneForJobQueue : jobsInZoneQueue.keySet()){
			/**
			 * check to see if this is the right time to send the job to 
			 * the taxi. If yes send it else continue. 
			 */

			//get the job from the job queue.
			Job firstJobInTheList = jobsInZoneQueue.get(zoneForJobQueue).peek();
			Date pickUpDateAndTime = firstJobInTheList.getPickUpTime();
			long timeGap = pickUpDateAndTime.getTime() - new Date().getTime();
			long timeGapInMinutes  = timeGap / (60L*1000L);

			if(timeGapInMinutes <= ConstantUtil.JOB_DISPATCH_WAIT_TIME.get(zoneForJobQueue)){				
				/**
				 * get the device android id that this taxi in the queue is taking.
				 */				
				Taxi firstTaxiInTheZone = taxisInZoneQueue.get(zoneForJobQueue).peek();
				String deviceRegistrationId = null;//firstTaxiInTheZone.getTaxiDeviceId;
				/**
				 * create the payload to be sent to the device.				
				 */
				String jobXml = MessageUtil.createMessageXml(firstJobInTheList);
				/**
				 * send the payload to the device.
				 */
				int response;
				try {
					// need to figure out what should happen to job if bad response received.
					response = MessageUtil.sendMessage(deviceRegistrationId, jobXml);
				} catch (IOException e) {
					logger.debug("Couldn't send message to the the device ==> "+deviceRegistrationId+" for taxi ==> "+firstTaxiInTheZone.getTaxiId());
				}
				/**
				 * After sending the message remove the Taxi and the Job from that queue.
				 */
				taxisInZoneQueue.get(zoneForJobQueue).poll();
				jobsInZoneQueue.get(zoneForJobQueue).poll();


			}			
		}

	}
	
	/**
	 * sample test xml for the job.
	 */
	public void dispatchSampleJob(){
		String jobXml = MessageUtil.createTestMessageXml();
	}
	
	/**
	 * Job dispatcher method.
	 * 1. For each zone finds out if there is any job. 
	 * 2. If there is any job for that zone calculate the  PICKUP_TIME - NOW time
	 * is less than or equal to an hour (by default) or JOB_DISPATCH_TIME allocated for that zone
	 * 3. Dispatch the job to the taxi device id (In this case the device_id is the android phone id
	 * given to that taxi.)
	 */
	public void dispatchThreadedJob() throws InterruptedException{
		Map<String, Queue<Taxi>> taxisInZoneQueue = taxiZoneListener.getInstance();
		Map<String, Queue<Job>> jobsInZoneQueue = jobListener.getInstance();
		/**
		 * loop through each zones and check to see if there are valid jobs.
		 */
		int NUM_THREADS = taxisInZoneQueue.size();		
		Thread[] threads = new Thread[NUM_THREADS];		
		int i=0;
		for (String zoneCode : jobsInZoneQueue.keySet()){
			threads[i] = new Thread(new ConcurrentJobDispatchListener(zoneCode, taxisInZoneQueue.get(zoneCode), jobsInZoneQueue.get(zoneCode)));
			i++;
		}
		/**
		 * start the threads.
		 */
		for (int j = 0; j < NUM_THREADS; j++) {		
			threads[j].start();		
		}
		
//		for (int j = 0; j < NUM_THREADS; j++) {			
//			threads[j].join();			
//		}

	}



}
