package com.cc.addressbook.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author cclaudiu
 *
 */

@Entity
@Table(name = "contacts")
public class PersonEntity implements TableEntity, Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "mobile_phone", nullable = false, unique = true)
    private String phoneMobileNumber;

    @Column(name = "home_phone", nullable = true, unique = true)
    private String phoneHomeNumber;

    @Column(name = "email", unique = true)
    private String email;

    @Column
    private String age;

    @Column(name = "address", unique = true)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "jobs",
               joinColumns = { @JoinColumn(name = "contact_id") },
               inverseJoinColumns = {@JoinColumn(name = "job_id")})
    private List<JobEntity> job;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneMobileNumber() {
		return phoneMobileNumber;
	}

	public void setPhoneMobileNumber(String phoneMobileNumber) {
		this.phoneMobileNumber = phoneMobileNumber;
	}

	public String getPhoneHomeNumber() {
		return phoneHomeNumber;
	}

	public void setPhoneHomeNumber(String phoneHomeNumber) {
		this.phoneHomeNumber = phoneHomeNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<JobEntity> getJob() {
		return job;
	}

	public void setJob(List<JobEntity> job) {
		this.job = job;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}