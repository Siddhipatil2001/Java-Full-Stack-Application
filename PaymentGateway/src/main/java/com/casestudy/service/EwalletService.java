package com.casestudy.service;

import java.util.List;
import java.util.Optional;

import com.casestudy.models.Ewallet;
import com.casestudy.models.Statement;

public interface EwalletService {
	
	public List<Ewallet> getWallets();
//	public Ewallet addWallet(Ewallet addwallet);
	public Ewallet addWalletForProfile(int profileId);
	public void addMoney(int profileId, double amount);
	
	// To Update EWallet Details & Generate Statements
	public void doTransaction(int profileId, double amount);	
	public Ewallet getWalletById(int profileId);
	public List<Statement> getStatementById(int statementId);
	public List<Statement> getAllStatements();
	public String deleteWalletById(int ewalletid);
	
}