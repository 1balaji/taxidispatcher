package com.taxidispatcher.services;

import java.io.IOException;
import java.util.Date;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taxidispatcher.android.server.MessageUtil;
import com.taxidispatcher.constant.ConstantUtil;
import com.taxidispatcher.model.Job;
import com.taxidispatcher.model.Taxi;


public class ConcurrentJobDispatchListener implements Runnable {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private String zone;
	private Queue<Taxi> taxiQueue;
	private Queue<Job> jobQueue;
	
	public ConcurrentJobDispatchListener(String zone,Queue<Taxi> taxiQueue,Queue<Job> jobQueue){
		this.zone = zone;
		this.taxiQueue = taxiQueue;
		this.jobQueue = jobQueue;
	}

	@Override
	public void run() {
		dispatchJob(zone, taxiQueue, jobQueue);
	}
	
	/**
	 * Dispatch the job to the corresponding taxi in the queue.
	 * @param zone
	 * @param taxiQueue
	 * @param jobQueue
	 */
	public void dispatchJob(String zone, Queue<Taxi> taxiQueue,Queue<Job> jobQueue){					
		//get the job from the job queue.
		boolean queueTerminated = false;

		while(!queueTerminated){
			Job firstJobInTheList = jobQueue.peek();
			Date pickUpDateAndTime = firstJobInTheList.getPickUpTime();
			long timeGap = pickUpDateAndTime.getTime() - new Date().getTime();
			long timeGapInMinutes  = timeGap / (60L*1000L);

			if(timeGapInMinutes <= ConstantUtil.JOB_DISPATCH_WAIT_TIME.get(zone)){				
				/**
				 * get the device android id that this taxi in the queue is taking.
				 */				
				Taxi firstTaxiInTheZone = taxiQueue.peek();
				String deviceRegistrationId = firstTaxiInTheZone.getDeviceId();
				/**
				 * create the payload to be sent to the device.				
				 */
				String jobXml = MessageUtil.createMessageXml(firstJobInTheList);
				try {
					MessageUtil.sendMessage(deviceRegistrationId, jobXml);
				} catch (IOException e) {
					logger.debug("Couldn't send message to the the device ==> "+deviceRegistrationId+" for taxi ==> "+firstTaxiInTheZone.getTaxiId());
				}
				/**
				 * After sending the message remove the Taxi and the Job from that queue.
				 */
				taxiQueue.poll();
				jobQueue.poll();


			}else{
				queueTerminated = true;
				break;
			}
		}

	}



}
