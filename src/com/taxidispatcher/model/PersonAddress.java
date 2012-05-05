package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_person_address database table.
 * 
 */
@Entity
@Table(name="tbl_person_address")
public class PersonAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TBL_PERSON_ADDRESS_PERSONADDRESSID_GENERATOR", sequenceName="SEQUENCE_TBL_PERSON_ADDRESS")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PERSON_ADDRESS_PERSONADDRESSID_GENERATOR")
	@Column(name="person_address_id")
	private int personAddressId;

	@Column(name="address_type")
	private String addressType;

	private String address2;

	private String city;

	private String country;

	private String district;

	@Column(name="house_no")
	private String houseNo;

	@Column(name="street_address")
	private String streetAddress;

	private String zone;

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="person_id")
	private Person tblPerson;

    public PersonAddress() {
    }

	public int getPersonAddressId() {
		return this.personAddressId;
	}

	public void setPersonAddressId(int personAddressId) {
		this.personAddressId = personAddressId;
	}

	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
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

	public String getHouseNo() {
		return this.houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getZone() {
		return this.zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public Person getTblPerson() {
		return this.tblPerson;
	}

	public void setTblPerson(Person tblPerson) {
		this.tblPerson = tblPerson;
	}
	
}