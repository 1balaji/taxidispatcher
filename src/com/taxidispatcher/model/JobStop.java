package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_job_stops database table.
 * 
 */
@Entity
@Table(name="tbl_job_stops")
public class JobStop implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JobStopPK id;

    @Lob()
	@Column(name="job_stop_notes")
	private String jobStopNotes;

	//bi-directional many-to-one association to Job
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id", insertable=false, updatable=false)
	private Job tblJob;

	//bi-directional many-to-one association to JobAddress
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_stop_address_id")
	private JobAddress tblJobAddress;

    public JobStop() {
    }

	public JobStopPK getId() {
		return this.id;
	}

	public void setId(JobStopPK id) {
		this.id = id;
	}
	
	public String getJobStopNotes() {
		return this.jobStopNotes;
	}

	public void setJobStopNotes(String jobStopNotes) {
		this.jobStopNotes = jobStopNotes;
	}

	public Job getTblJob() {
		return this.tblJob;
	}

	public void setTblJob(Job tblJob) {
		this.tblJob = tblJob;
	}
	
	public JobAddress getTblJobAddress() {
		return this.tblJobAddress;
	}

	public void setTblJobAddress(JobAddress tblJobAddress) {
		this.tblJobAddress = tblJobAddress;
	}
	
}