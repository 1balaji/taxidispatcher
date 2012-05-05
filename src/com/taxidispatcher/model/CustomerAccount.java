package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_customer_account database table.
 * 
 */
@Entity
@Table(name="tbl_customer_account")
public class CustomerAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TBL_CUSTOMER_ACCOUNT_CUSTOMERACCOUNTID_GENERATOR", sequenceName="SEQUENCE_TBL_CUSTOMER_ACCOUNT")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_CUSTOMER_ACCOUNT_CUSTOMERACCOUNTID_GENERATOR")
	@Column(name="customer_account_id")
	private int customerAccountId;

	@Column(name="customer_name")
	private String customerName;

	@Column(name="customer_phone1")
	private String customerPhone1;

	@Column(name="customer_phone2")
	private String customerPhone2;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="tblCustomerAccount")
	private List<Job> tblJobs;

    public CustomerAccount() {
    }

	public int getCustomerAccountId() {
		return this.customerAccountId;
	}

	public void setCustomerAccountId(int customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone1() {
		return this.customerPhone1;
	}

	public void setCustomerPhone1(String customerPhone1) {
		this.customerPhone1 = customerPhone1;
	}

	public String getCustomerPhone2() {
		return this.customerPhone2;
	}

	public void setCustomerPhone2(String customerPhone2) {
		this.customerPhone2 = customerPhone2;
	}

	public List<Job> getTblJobs() {
		return this.tblJobs;
	}

	public void setTblJobs(List<Job> tblJobs) {
		this.tblJobs = tblJobs;
	}
	
}