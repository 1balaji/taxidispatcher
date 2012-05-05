package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_user_type database table.
 * 
 */
@Entity
@Table(name="tbl_user_type")
public class UserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TBL_USER_TYPE_USERTYPEID_GENERATOR", sequenceName="SEQUENCE_TBL_USER_TYPE")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_USER_TYPE_USERTYPEID_GENERATOR")
	@Column(name="user_type_id")
	private int userTypeId;

	@Column(name="user_type")
	private String userType;

	//bi-directional many-to-one association to UserLogin
	@OneToMany(mappedBy="tblUserType")
	private List<UserLogin> tblUserLogins;

    public UserType() {
    }

	public int getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<UserLogin> getTblUserLogins() {
		return this.tblUserLogins;
	}

	public void setTblUserLogins(List<UserLogin> tblUserLogins) {
		this.tblUserLogins = tblUserLogins;
	}
	
}