package com.taxidispatcher.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.taxidispatcher.model.TaxiZone;
import com.taxidispatcher.model.Zone;

public class TaxiZoneDaoImpl implements TaxiZoneDao {

	protected final Log logger = LogFactory.getLog(getClass());
	private SessionFactory sessionFactory;	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Get all the taxi-zone for a zone where logout time is null
	 * order by login time in ascending order.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<TaxiZone> getTaxiZoneOrderByLoginTime(Zone zone) {
		String SQL_QUERY = " from TaxiZone as taxizone where taxizone.taxiLogoutTime is null order by taxizone.taxiLoginTime asc";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(SQL_QUERY);		
		List<TaxiZone> taxiZoneList = query.list();
		if(logger.isInfoEnabled())
			logger.info("Getting list of taxi zones order by login time in ascending order for the zone "+ zone.getZoneCode());
		session.close();
		return taxiZoneList;
	}
}
