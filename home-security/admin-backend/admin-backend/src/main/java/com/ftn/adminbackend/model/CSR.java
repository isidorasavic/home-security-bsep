package com.ftn.adminbackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document("csrs")
public class CSR {

    @Id
    private String sn;

    private String csr;

    private Boolean isDeleted;

	public CSR() {
		super();
	}

	public CSR(String sn, String csr, Boolean isDeleted) {
		super();
		this.sn = sn;
		this.csr = csr;
		this.isDeleted = isDeleted;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getCsr() {
		return csr;
	}

	public void setCsr(String csr) {
		this.csr = csr;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

    
    
}
