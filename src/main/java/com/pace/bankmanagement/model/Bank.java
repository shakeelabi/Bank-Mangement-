package com.pace.bankmanagement.model;

public class Bank {
	protected int id;
    protected String account_holder;
    protected String account_number;
    protected String phone_number;
    protected int balence;
    
    public Bank() {}
	public Bank(String account_holder,String account_number, String phone_number,int balence) {
		super();
		this.account_holder = account_holder;
		this.account_number = account_number;
		this.phone_number = phone_number;
		this.balence = balence;
		
	}
	public Bank(int id, String account_holder, String account_number, String phone_number,int balence) {
		super();
		this.id = id;
		this.account_holder = account_holder;
		this.account_number = account_number;
		this.phone_number = phone_number;
		this.balence = balence;
	}
	public int getBalence() {
		return balence;
	}
	public void setBalence(int balence) {
		this.balence = balence;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount_holder() {
		return account_holder;
	}
	public void setAccount_holder(String account_holder) {
		this.account_holder = account_holder;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
}