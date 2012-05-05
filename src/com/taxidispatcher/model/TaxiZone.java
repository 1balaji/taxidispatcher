package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_taxi_zone database table.
 * 
 */
@Entity
@Table(name="tbl_taxi_zone")
public class TaxiZone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TBL_TAXI_ZONE_TAXIZONEID_GENERATOR", sequenceName="SEQUENCE_TBL_TAXI_ZONE")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TAXI_ZONE_TAXIZONEID_GENERATOR")
	@Column(name="taxi_zone_id")
	private int taxiZoneId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="taxi_login_time")
	private Date taxiLoginTime;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="taxi_logout_time")
	private Date taxiLogoutTime;

	@Column(name="taxi_zone_status")
	private String taxiZoneStatus;

	//bi-directional many-to-one association to Taxi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="taxi_id")
	private Taxi tblTaxi;

	//bi-directional many-to-one association to Zone
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="zone_id")
	private Zone tblZone;

    public TaxiZone() {
    }

	public int getTaxiZoneId() {
		return this.taxiZoneId;
	}

	public void setTaxiZoneId(int taxiZoneId) {
		this.taxiZoneId = taxiZoneId;
	}

	public Date getTaxiLoginTime() {
		return this.taxiLoginTime;
	}

	public void setTaxiLoginTime(Date taxiLoginTime) {
		this.taxiLoginTime = taxiLoginTime;
	}

	public Date getTaxiLogoutTime() {
		return this.taxiLogoutTime;
	}

	public void setTaxiLogoutTime(Date taxiLogoutTime) {
		this.taxiLogoutTime = taxiLogoutTime;
	}

	public String getTaxiZoneStatus() {
		return this.taxiZoneStatus;
	}

	public void setTaxiZoneStatus(String taxiZoneStatus) {
		this.taxiZoneStatus = taxiZoneStatus;
	}

	public Taxi getTblTaxi() {
		return this.tblTaxi;
	}

	public void setTblTaxi(Taxi tblTaxi) {
		this.tblTaxi = tblTaxi;
	}
	
	public Zone getTblZone() {
		return this.tblZone;
	}

	public void setTblZone(Zone tblZone) {
		this.tblZone = tblZone;
	}
	
}