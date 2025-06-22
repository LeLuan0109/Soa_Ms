package com.project.app.org.domain;


import com.project.app.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orgs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblOrg extends BaseEntity {

	@Id
	@Column(name = "org_id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "logo")
	private String logo;

	@Column(name = "taxcode")
	private String taxCode;

	@Column(name = "website")
	private String website;


	public TblOrg(Integer id, String address, String companyName, String email, String phone, String logo, String taxCode, String website) {
		this.id = id;
		this.address = address;
		this.companyName = companyName;
		this.email = email;
		this.phone = phone;
		this.logo = logo;
		this.taxCode = taxCode;
		this.website = website;
	}
}
