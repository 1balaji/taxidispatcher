package com.taxidispatcher.constant;

import java.util.HashMap;
import java.util.Map;

public final class ConstantUtil {
	
	public static final int MAX_JOB_RESULTS = 50;
	/**
	 * The time gap for a job to be  dispatched to the Taxi Device thats in the first of the queue.
	 */
	public static final Map<String,Long> JOB_DISPATCH_WAIT_TIME = new HashMap<String, Long>();	
	static{
		JOB_DISPATCH_WAIT_TIME.put("ZONE1", 60L);
		JOB_DISPATCH_WAIT_TIME.put("ZONE2", 60L);
		JOB_DISPATCH_WAIT_TIME.put("ZONE3", 60L);
		JOB_DISPATCH_WAIT_TIME.put("ZONE4", 60L);
		JOB_DISPATCH_WAIT_TIME.put("ZONE5", 60L);
	}

}
