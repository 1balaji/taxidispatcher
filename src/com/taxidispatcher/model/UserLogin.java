package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_user_login database table.
 * 
 */
@Entity
@Table(name="tbl_user_login")
public class UserLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TBL_USER_LOGIN_USERLOGINID_GENERATOR", sequenceName="SEQUENCE_TBL_USER_LOGIN")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_USER_LOGIN_USERLOGINID_GENERATOR")
	@Column(name="user_login_id")
	private int userLoginId;

	private String password;

	@Column(name="user_login_status")
	private String userLoginStatus;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="user_login_time")
	private Date userLoginTime;

	@Column(name="user_logout_time")
	private String userLogoutTime;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="tblUserLogin")
	private List<Person> tblPersons;

	//bi-directional many-to-many association to Permission
    @ManyToMany
	@JoinTable(
		name="tbl_user_permission"
		, joinColumns={
			@JoinColumn(name="user_login_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="permission_id")
			}
		)
	private List<Permission> tblPermissions;

	//bi-directional many-to-one association to UserType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_type_id")
	private UserType tblUserType;

    public UserLogin() {
    }

	public int getUserLoginId() {
		return this.userLoginId;
	}

	public void setUserLoginId(int userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserLoginStatus() {
		return this.userLoginStatus;
	}

	public void setUserLoginStatus(String userLoginStatus) {
		this.userLoginStatus = userLoginStatus;
	}

	public Date getUserLoginTime() {
		return this.userLoginTime;
	}

	public void setUserLoginTime(Date userLoginTime) {
		this.userLoginTime = userLoginTime;
	}

	public String getUserLogoutTime() {
		return this.userLogoutTime;
	}

	public void setUserLogoutTime(String userLogoutTime) {
		this.userLogoutTime = userLogoutTime;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Person> getTblPersons() {
		return this.tblPersons;
	}

	public void setTblPersons(List<Person> tblPersons) {
		this.tblPersons = tblPersons;
	}
	
	public List<Permission> getTblPermissions() {
		return this.tblPermissions;
	}

	public void setTblPermissions(List<Permission> tblPermissions) {
		this.tblPermissions = tblPermissions;
	}
	
	public UserType getTblUserType() {
		return this.tblUserType;
	}

	public void setTblUserType(UserType tblUserType) {
		this.tblUserType = tblUserType;
	}
	
}