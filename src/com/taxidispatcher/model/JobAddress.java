package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_job_address database table.
 * 
 */
@Entity
@Table(name="tbl_job_address")
public class JobAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TBL_JOB_ADDRESS_JOBADDRESSID_GENERATOR", sequenceName="SEQUENCE_TBL_JOB_ADDRESS")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_JOB_ADDRESS_JOBADDRESSID_GENERATOR")
	@Column(name="job_address_id")
	private int jobAddressId;

	private String address2;

	private String city;

	private String country;

	private String district;

    @Lob()
	@Column(name="job_address_notes")
	private String jobAddressNotes;

	@Column(name="job_address_type")
	private String jobAddressType;

	@Column(name="street_address")
	private String streetAddress;

	@Column(name="zone_id")
	private String zoneId;

	//bi-directional many-to-one association to Job
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id")
	private Job tblJob;

	//bi-directional many-to-one association to JobStop
	@OneToMany(mappedBy="tblJobAddress")
	private List<JobStop> tblJobStops;

    public JobAddress() {
    }

	public int getJobAddressId() {
		return this.jobAddressId;
	}

	public void setJobAddressId(int jobAddressId) {
		this.jobAddressId = jobAddressId;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getJobAddressNotes() {
		return this.jobAddressNotes;
	}

	public void setJobAddressNotes(String jobAddressNotes) {
		this.jobAddressNotes = jobAddressNotes;
	}

	public String getJobAddressType() {
		return this.jobAddressType;
	}

	public void setJobAddressType(String jobAddressType) {
		this.jobAddressType = jobAddressType;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public Job getTblJob() {
		return this.tblJob;
	}

	public void setTblJob(Job tblJob) {
		this.tblJob = tblJob;
	}
	
	public List<JobStop> getTblJobStops() {
		return this.tblJobStops;
	}

	public void setTblJobStops(List<JobStop> tblJobStops) {
		this.tblJobStops = tblJobStops;
	}
	
}