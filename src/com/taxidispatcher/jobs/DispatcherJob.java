package com.taxidispatcher.jobs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.taxidispatcher.android.server.MessageUtil;
import com.taxidispatcher.services.JobDispatchListener;


/**
 * Implementing statefuljob to make it non concurrent
 * @author sghimire
 *
 */
public class DispatcherJob extends QuartzJobBean implements StatefulJob {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private int timeout;	
	private static JobDispatchListener jobDispatchListener;
	

	/**
	   * Setter called after the DispatcherJob is instantiated
	   * with the value from the JobDetailBean (5)
	   */ 
	  public void setTimeout(int timeout) {
	    this.timeout = timeout;
	  }

	public void setJobDispatchListener(JobDispatchListener jobDispatchListener) {
		this.jobDispatchListener = jobDispatchListener;
	}


	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		//logger.debug("Job invoked at : " + new java.util.Date());
		//System.out.println("Job invoked at : " + new java.util.Date());
//		try {
//			jobDispatchListener.dispatchThreadedJob();
//			
//		} catch (InterruptedException e) {
//			logger.debug("Threads got interrupted.");
//		}
		
		String jobXml = MessageUtil.createTestMessageXml();
		logger.info("Job Xml to be sent: " + jobXml);
		System.out.println("Job Xml to be sent: " + jobXml);
	}

}
