package com.taxidispatcher.dao;

import java.util.List;
import java.util.Queue;

import com.taxidispatcher.model.Job;
import com.taxidispatcher.model.JobAddress;
import com.taxidispatcher.model.Zone;

public interface JobDao {

	Queue<Job> getJobsOrderByCreatedDate();

	List<Job> getJobOnZonesOrderByPickUpTime(Zone zone);
	
	List<JobAddress> getJobAddress(Job job);

}
