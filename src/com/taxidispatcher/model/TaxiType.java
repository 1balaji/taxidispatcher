package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_taxi_type database table.
 * 
 */
@Entity
@Table(name="tbl_taxi_type")
public class TaxiType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TBL_TAXI_TYPE_TAXITYPEID_GENERATOR", sequenceName="SEQUENCE_TBL_TAXI_TYPE")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TAXI_TYPE_TAXITYPEID_GENERATOR")
	@Column(name="taxi_type_id")
	private int taxiTypeId;

	@Column(name="carriage_available")
	private String carriageAvailable;

	@Column(name="passengar_capacity")
	private int passengarCapacity;

	@Column(name="taxi_type")
	private String taxiType;

	//bi-directional many-to-one association to Taxi
	@OneToMany(mappedBy="tblTaxiType")
	private List<Taxi> tblTaxis;

    public TaxiType() {
    }

	public int getTaxiTypeId() {
		return this.taxiTypeId;
	}

	public void setTaxiTypeId(int taxiTypeId) {
		this.taxiTypeId = taxiTypeId;
	}

	public String getCarriageAvailable() {
		return this.carriageAvailable;
	}

	public void setCarriageAvailable(String carriageAvailable) {
		this.carriageAvailable = carriageAvailable;
	}

	public int getPassengarCapacity() {
		return this.passengarCapacity;
	}

	public void setPassengarCapacity(int passengarCapacity) {
		this.passengarCapacity = passengarCapacity;
	}

	public String getTaxiType() {
		return this.taxiType;
	}

	public void setTaxiType(String taxiType) {
		this.taxiType = taxiType;
	}

	public List<Taxi> getTblTaxis() {
		return this.tblTaxis;
	}

	public void setTblTaxis(List<Taxi> tblTaxis) {
		this.tblTaxis = tblTaxis;
	}
	
}