package com.taxidispatcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_permission database table.
 * 
 */
@Entity
@Table(name="tbl_permission")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="TBL_PERMISSION_PERMISSIONID_GENERATOR", sequenceName="SEQUENCE_TBL_PERMISSION")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PERMISSION_PERMISSIONID_GENERATOR")
	@Column(name="permission_id")
	private int permissionId;

	private String permission;

	//bi-directional many-to-many association to UserLogin
	@ManyToMany(mappedBy="tblPermissions")
	private List<UserLogin> tblUserLogins;

    public Permission() {
    }

	public int getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public List<UserLogin> getTblUserLogins() {
		return this.tblUserLogins;
	}

	public void setTblUserLogins(List<UserLogin> tblUserLogins) {
		this.tblUserLogins = tblUserLogins;
	}
	
}