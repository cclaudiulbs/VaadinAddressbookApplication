package com.cc.addressbook.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author cclaudiu
 *
 */

@Entity
@Table(name = "jobs")
public class JobEntity implements TableEntity, Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long id;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "job_description")
    private String jobDescription;

    @Version
    @Column(name = "optimistic_lock")
    private Long optimisticLock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public Long getOptimisticLock() {
		return optimisticLock;
	}

	public void setOptimisticLock(Long optimisticLock) {
		this.optimisticLock = optimisticLock;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}