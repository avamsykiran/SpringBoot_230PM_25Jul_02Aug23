package com.cts.swbd.service;

import org.springframework.stereotype.Service;

import com.cts.swbd.model.Loan;

@Service
public class LoanService {
	public double getSimpleInterest(Loan loan) {
		return loan.getPrinciple()*loan.getInterestRate()*loan.getTerms()/100;
	}
}
