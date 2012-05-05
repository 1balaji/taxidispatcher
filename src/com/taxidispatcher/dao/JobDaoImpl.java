package com.taxidispatcher.dao;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.taxidispatcher.constant.ConstantUtil;
import com.taxidispatcher.model.Job;
import com.taxidispatcher.model.JobAddress;
import com.taxidispatcher.model.Zone;

public class JobDaoImpl implements JobDao {

	private SessionFactory sessionFactory;			

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional(readOnly = true)
	public Queue<Job> getJobsOrderByCreatedDate() {
		String SQL_QUERY = "from Job job order where job.jobStatus='ACTIVE' by job.pickUpTime asc";
		Session session = sessionFactory.openSession();
		List<Job> jobList = session.createQuery(SQL_QUERY).list();
		Queue<Job> jobQueue = new LinkedBlockingQueue<Job>(jobList);
		session.close();
		return jobQueue;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Job> getJobOnZonesOrderByPickUpTime(Zone zone) {
		String SQL_QUERY = "from Job job where job.jobStatus='ACTIVE' and job.zoneId=:zoneId by job.pickUpTime asc";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(SQL_QUERY);
		query.setInteger("zoneId", zone.getZoneId());
		query.setMaxResults(ConstantUtil.MAX_JOB_RESULTS);
		List<Job> jobList = query.list();	
		session.close();
		return jobList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<JobAddress> getJobAddress(Job job) {
		String SQL_QUERY = "from JobAddress ja where ja.jobId=:jobId";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(SQL_QUERY);
		query.setInteger("jobId", job.getJobId());		
		List<JobAddress> jobAddressList = query.list();		
		session.close();
		return jobAddressList;
	}
	
}
