package com.ftn.adminbackend.dto;

public class CSRDTO {

    private String CN;
    private String O;
    private String OU;
    private String L;
    private String S;
    private String C;
    private String emailAddress;
    private String username;
    
	public CSRDTO() {
		super();
	}


	public CSRDTO(String cN, String o, String oU, String l, String s, String c, String emailAddress,
			String username) {
		super();
		CN = cN;
		O = o;
		OU = oU;
		L = l;
		S = s;
		C = c;
		this.emailAddress = emailAddress;
		this.username = username;
	}


	@Override
	public String toString() {
		return "{" +
			" CN='" + getCN() + "'" +
			", O='" + getO() + "'" +
			", OU='" + getOU() + "'" +
			", L='" + getL() + "'" +
			", S='" + getS() + "'" +
			", C='" + getC() + "'" +
			", emailAddress='" + getEmailAddress() + "'" +
			", username='" + getUsername() + "'" +
			"}";
	}


	public String getCN() {
		return CN;
	}

	public void setCN(String cN) {
		CN = cN;
	}

	public String getO() {
		return O;
	}

	public void setO(String o) {
		O = o;
	}

	public String getOU() {
		return OU;
	}

	public void setOU(String oU) {
		OU = oU;
	}

	public String getL() {
		return L;
	}

	public void setL(String l) {
		L = l;
	}

	public String getS() {
		return S;
	}

	public void setS(String s) {
		S = s;
	}

	public String getC() {
		return C;
	}

	public void setC(String c) {
		C = c;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    
    
    
}
