package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_zone database table.
 * 
 */
@Entity
@Table(name="tbl_zone")
public class Zone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TBL_ZONE_ZONEID_GENERATOR", sequenceName="SEQUENCE_TBL_ZONE")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ZONE_ZONEID_GENERATOR")
	@Column(name="zone_id")
	private int zoneId;

	@Column(name="zone_code")
	private String zoneCode;

	@Column(name="zone_name")
	private String zoneName;

	@Column(name="zone_status")
	private String zoneStatus;

	//bi-directional many-to-one association to TaxiZone
	@OneToMany(mappedBy="tblZone")
	private List<TaxiZone> tblTaxiZones;

    public Zone() {
    }

	public int getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneCode() {
		return this.zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getZoneName() {
		return this.zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getZoneStatus() {
		return this.zoneStatus;
	}

	public void setZoneStatus(String zoneStatus) {
		this.zoneStatus = zoneStatus;
	}

	public List<TaxiZone> getTblTaxiZones() {
		return this.tblTaxiZones;
	}

	public void setTblTaxiZones(List<TaxiZone> tblTaxiZones) {
		this.tblTaxiZones = tblTaxiZones;
	}
	
}