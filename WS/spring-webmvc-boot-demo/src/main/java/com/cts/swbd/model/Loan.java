package com.cts.swbd.model;

public class Loan {

	private double principle;
	private double terms;
	private double interestRate;
	
	public Loan() {
		// TODO Auto-generated method stub

	}

	public Loan(double principle, double terms, double inteestRate) {
		super();
		this.principle = principle;
		this.terms = terms;
		this.interestRate = inteestRate;
	}

	public double getPrinciple() {
		return principle;
	}

	public void setPrinciple(double principle) {
		this.principle = principle;
	}

	public double getTerms() {
		return terms;
	}

	public void setTerms(double terms) {
		this.terms = terms;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	
}
