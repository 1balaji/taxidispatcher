package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_phones database table.
 * 
 */
@Entity
@Table(name="tbl_phones")
public class Phone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TBL_PHONES_PHONEID_GENERATOR", sequenceName="SEQUENCE_TBL_PHONES")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PHONES_PHONEID_GENERATOR")
	@Column(name="phone_id")
	private int phoneId;

	@Column(name="cell_phone")
	private String cellPhone;

	@Column(name="home_phone")
	private String homePhone;

	@Column(name="phone_status")
	private String phoneStatus;

	@Column(name="phone_type")
	private String phoneType;

	@Column(name="work_phone")
	private String workPhone;

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="person_id")
	private Person tblPerson;

    public Phone() {
    }

	public int getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getPhoneStatus() {
		return this.phoneStatus;
	}

	public void setPhoneStatus(String phoneStatus) {
		this.phoneStatus = phoneStatus;
	}

	public String getPhoneType() {
		return this.phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public Person getTblPerson() {
		return this.tblPerson;
	}

	public void setTblPerson(Person tblPerson) {
		this.tblPerson = tblPerson;
	}
	
}