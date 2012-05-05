package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_taxi database table.
 * 
 */
@Entity
@Table(name="tbl_taxi")
public class Taxi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TBL_TAXI_TAXIID_GENERATOR", sequenceName="SEQUENCE_TBL_TAXI")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TAXI_TAXIID_GENERATOR")
	@Column(name="taxi_id")
	private int taxiId;

	@Column(name="company_id")
	private int companyId;

	@Column(name="device_id")
	private String deviceId;

	@Column(name="driver_licence_no")
	private String driverLicenceNo;

	@Column(name="plate_number")
	private String plateNumber;

	@Column(name="user_login_id")
	private int userLoginId;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="tblTaxi")
	private List<Job> tblJobs;

	//bi-directional many-to-one association to TaxiType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="taxi_type_id")
	private TaxiType tblTaxiType;

	//bi-directional many-to-one association to TaxiZone
	@OneToMany(mappedBy="tblTaxi")
	private List<TaxiZone> tblTaxiZones;

    public Taxi() {
    }

	public int getTaxiId() {
		return this.taxiId;
	}

	public void setTaxiId(int taxiId) {
		this.taxiId = taxiId;
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDriverLicenceNo() {
		return this.driverLicenceNo;
	}

	public void setDriverLicenceNo(String driverLicenceNo) {
		this.driverLicenceNo = driverLicenceNo;
	}

	public String getPlateNumber() {
		return this.plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public int getUserLoginId() {
		return this.userLoginId;
	}

	public void setUserLoginId(int userLoginId) {
		this.userLoginId = userLoginId;
	}

	public List<Job> getTblJobs() {
		return this.tblJobs;
	}

	public void setTblJobs(List<Job> tblJobs) {
		this.tblJobs = tblJobs;
	}
	
	public TaxiType getTblTaxiType() {
		return this.tblTaxiType;
	}

	public void setTblTaxiType(TaxiType tblTaxiType) {
		this.tblTaxiType = tblTaxiType;
	}
	
	public List<TaxiZone> getTblTaxiZones() {
		return this.tblTaxiZones;
	}

	public void setTblTaxiZones(List<TaxiZone> tblTaxiZones) {
		this.tblTaxiZones = tblTaxiZones;
	}
	
}