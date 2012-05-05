package com.taxidispatcher.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.taxidispatcher.model.Zone;

public class ZoneDaoImpl implements ZoneDao {
	
	protected final Log logger = LogFactory.getLog(getClass());
	private SessionFactory sessionFactory;	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Zone> getZones() {
		/**
		 * query to get all the zones.
		 */
		Query query = sessionFactory.getCurrentSession().createQuery("from Zone");		
		List<Zone> zoneList= query.list();		
		if(logger.isInfoEnabled())
			logger.info("Getting list of zones. ");


		return zoneList;
	}

}
