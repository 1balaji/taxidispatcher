package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_job database table.
 * 
 */
@Entity
@Table(name="tbl_job")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TBL_JOB_JOBID_GENERATOR", sequenceName="SEQUENCE_TBL_JOB")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_JOB_JOBID_GENERATOR")
	@Column(name="job_id")
	private int jobId;

    @Lob()
	@Column(name="driver_instruction")
	private String driverInstruction;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="drop_off_time")
	private Date dropOffTime;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="job_end_time")
	private Date jobEndTime;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="job_start_time")
	private Date jobStartTime;

	@Column(name="job_status")
	private String jobStatus;

	@Column(name="no_of_passenger")
	private int noOfPassenger;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="pick_up_time")
	private Date pickUpTime;

	//bi-directional many-to-one association to CustomerAccount
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_account_id")
	private CustomerAccount tblCustomerAccount;

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="person_id")
	private Person tblPerson;

	//bi-directional many-to-one association to Taxi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="taxi_id")
	private Taxi tblTaxi;

	//bi-directional many-to-one association to JobAddress
	@OneToMany(mappedBy="tblJob")
	private List<JobAddress> tblJobAddresses;

	//bi-directional many-to-one association to JobStop
	@OneToMany(mappedBy="tblJob")
	private List<JobStop> tblJobStops;

    public Job() {
    }

	public int getJobId() {
		return this.jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getDriverInstruction() {
		return this.driverInstruction;
	}

	public void setDriverInstruction(String driverInstruction) {
		this.driverInstruction = driverInstruction;
	}

	public Date getDropOffTime() {
		return this.dropOffTime;
	}

	public void setDropOffTime(Date dropOffTime) {
		this.dropOffTime = dropOffTime;
	}

	public Date getJobEndTime() {
		return this.jobEndTime;
	}

	public void setJobEndTime(Date jobEndTime) {
		this.jobEndTime = jobEndTime;
	}

	public Date getJobStartTime() {
		return this.jobStartTime;
	}

	public void setJobStartTime(Date jobStartTime) {
		this.jobStartTime = jobStartTime;
	}

	public String getJobStatus() {
		return this.jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public int getNoOfPassenger() {
		return this.noOfPassenger;
	}

	public void setNoOfPassenger(int noOfPassenger) {
		this.noOfPassenger = noOfPassenger;
	}

	public Date getPickUpTime() {
		return this.pickUpTime;
	}

	public void setPickUpTime(Date pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	public CustomerAccount getTblCustomerAccount() {
		return this.tblCustomerAccount;
	}

	public void setTblCustomerAccount(CustomerAccount tblCustomerAccount) {
		this.tblCustomerAccount = tblCustomerAccount;
	}
	
	public Person getTblPerson() {
		return this.tblPerson;
	}

	public void setTblPerson(Person tblPerson) {
		this.tblPerson = tblPerson;
	}
	
	public Taxi getTblTaxi() {
		return this.tblTaxi;
	}

	public void setTblTaxi(Taxi tblTaxi) {
		this.tblTaxi = tblTaxi;
	}
	
	public List<JobAddress> getTblJobAddresses() {
		return this.tblJobAddresses;
	}

	public void setTblJobAddresses(List<JobAddress> tblJobAddresses) {
		this.tblJobAddresses = tblJobAddresses;
	}
	
	public List<JobStop> getTblJobStops() {
		return this.tblJobStops;
	}

	public void setTblJobStops(List<JobStop> tblJobStops) {
		this.tblJobStops = tblJobStops;
	}
	
}