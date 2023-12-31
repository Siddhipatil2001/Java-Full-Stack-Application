package com.casestudy.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.models.Ewallet;
import com.casestudy.models.Statement;
import com.casestudy.models.TransactionType;
import com.casestudy.repositories.EwalletRepository;
import com.casestudy.repositories.StatementRepository;

@Service
public class EwalletServiceImpl implements EwalletService {
	
	@Autowired
	EwalletRepository ewalletRepo;
	
	@Autowired
	StatementRepository statementRepo;

	@Override
	public List<Ewallet> getWallets() {
		return ewalletRepo.findAll();
	}

//	@Override
//	public Ewallet addWallet(Ewallet addwallet) {
//		return ewalletRepo.save(addwallet);
//	}
	
	@Override
	public Ewallet addWalletForProfile(int profileId) {
		Ewallet newWallet = new Ewallet(profileId,0);
		return ewalletRepo.save(newWallet);
	}


	@Override
	public void addMoney(int profileId, double amount) {
		// update balance
		Ewallet ewallet = ewalletRepo.findByProfileId(profileId);
		
		double totalBal = amount + ewallet.getCurrentBalance();
		ewallet.setCurrentBalance(totalBal);	
		ewalletRepo.save(ewallet);
		// generate statement
		Statement stmt = new Statement(profileId,TransactionType.CREDIT, amount, LocalDateTime.of(LocalDate.now(), LocalTime.now()), ewallet);
		statementRepo.save(stmt);
	}
	
	

	@Override
	public void doTransaction(int profileId , double amount) {
		// update balance
		Ewallet ewallet=ewalletRepo.findByProfileId(profileId);
		double totalBal =  ewallet.getCurrentBalance() -amount;
		ewallet.setCurrentBalance(totalBal);		
		ewalletRepo.save(ewallet);
		// generate statement
		Statement stmt = new Statement(ewallet.getProfileId(),TransactionType.DEBIT, amount, LocalDateTime.of(LocalDate.now(), LocalTime.now()),  ewallet);
		statementRepo.save(stmt);
	}

	@Override
	public Ewallet getWalletById(int profileId) {
		return ewalletRepo.findByProfileId(profileId);
	}

	@Override
	public List<Statement> getStatementById(int statementId) {
		return statementRepo.findByStatementId(statementId);
	}

	@Override
	public List<Statement> getAllStatements() {
		return statementRepo.findAll();
	}

	@Override
	public String deleteWalletById(int ewalletid){
		Ewallet ewallet =ewalletRepo.findByProfileId(ewalletid);
		ewallet.setCurrentBalance(0);
		ewalletRepo.save(ewallet);
		ewalletRepo.delete(ewallet);
		return "Wallet deleted!";
	}

}