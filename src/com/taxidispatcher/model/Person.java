package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_person database table.
 * 
 */
@Entity
@Table(name="tbl_person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TBL_PERSON_PERSONID_GENERATOR", sequenceName="SEQUENCE_TBL_PERSON")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PERSON_PERSONID_GENERATOR")
	@Column(name="person_id")
	private int personId;

	@Column(name="contact_method")
	private String contactMethod;

	@Column(name="person_dob")
	private String personDob;

	@Column(name="person_first_name")
	private String personFirstName;

	@Column(name="person_gender")
	private String personGender;

	@Column(name="person_last_name")
	private String personLastName;

	@Column(name="person_middle_name")
	private String personMiddleName;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="person_modified")
	private Date personModified;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="person_registered")
	private Date personRegistered;

	@Column(name="person_status")
	private String personStatus;

	@Column(name="person_type")
	private String personType;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="tblPerson")
	private List<Job> tblJobs;

	//bi-directional many-to-one association to UserLogin
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_login_id")
	private UserLogin tblUserLogin;

	//bi-directional many-to-one association to PersonAddress
	@OneToMany(mappedBy="tblPerson")
	private List<PersonAddress> tblPersonAddresses;

	//bi-directional many-to-one association to Phone
	@OneToMany(mappedBy="tblPerson")
	private List<Phone> tblPhones;

    public Person() {
    }

	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getContactMethod() {
		return this.contactMethod;
	}

	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

	public String getPersonDob() {
		return this.personDob;
	}

	public void setPersonDob(String personDob) {
		this.personDob = personDob;
	}

	public String getPersonFirstName() {
		return this.personFirstName;
	}

	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}

	public String getPersonGender() {
		return this.personGender;
	}

	public void setPersonGender(String personGender) {
		this.personGender = personGender;
	}

	public String getPersonLastName() {
		return this.personLastName;
	}

	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}

	public String getPersonMiddleName() {
		return this.personMiddleName;
	}

	public void setPersonMiddleName(String personMiddleName) {
		this.personMiddleName = personMiddleName;
	}

	public Date getPersonModified() {
		return this.personModified;
	}

	public void setPersonModified(Date personModified) {
		this.personModified = personModified;
	}

	public Date getPersonRegistered() {
		return this.personRegistered;
	}

	public void setPersonRegistered(Date personRegistered) {
		this.personRegistered = personRegistered;
	}

	public String getPersonStatus() {
		return this.personStatus;
	}

	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}

	public String getPersonType() {
		return this.personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public List<Job> getTblJobs() {
		return this.tblJobs;
	}

	public void setTblJobs(List<Job> tblJobs) {
		this.tblJobs = tblJobs;
	}
	
	public UserLogin getTblUserLogin() {
		return this.tblUserLogin;
	}

	public void setTblUserLogin(UserLogin tblUserLogin) {
		this.tblUserLogin = tblUserLogin;
	}
	
	public List<PersonAddress> getTblPersonAddresses() {
		return this.tblPersonAddresses;
	}

	public void setTblPersonAddresses(List<PersonAddress> tblPersonAddresses) {
		this.tblPersonAddresses = tblPersonAddresses;
	}
	
	public List<Phone> getTblPhones() {
		return this.tblPhones;
	}

	public void setTblPhones(List<Phone> tblPhones) {
		this.tblPhones = tblPhones;
	}
	
}