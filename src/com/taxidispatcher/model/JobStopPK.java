package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tbl_job_stops database table.
 * 
 */
@Embeddable
public class JobStopPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="job_id")
	private int jobId;

	@Column(name="job_stop_seq_id")
	private int jobStopSeqId;

    public JobStopPK() {
    }
	public int getJobId() {
		return this.jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getJobStopSeqId() {
		return this.jobStopSeqId;
	}
	public void setJobStopSeqId(int jobStopSeqId) {
		this.jobStopSeqId = jobStopSeqId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JobStopPK)) {
			return false;
		}
		JobStopPK castOther = (JobStopPK)other;
		return 
			(this.jobId == castOther.jobId)
			&& (this.jobStopSeqId == castOther.jobStopSeqId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.jobId;
		hash = hash * prime + this.jobStopSeqId;
		
		return hash;
    }
}