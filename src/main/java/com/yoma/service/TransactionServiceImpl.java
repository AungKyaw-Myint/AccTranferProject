package com.yoma.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoma.exception.ErrorHandling;
import com.yoma.exception.NoDataFoundException;
import com.yoma.model.Payment;
import com.yoma.model.Trans;
import com.yoma.model.view.ResponseView;
import com.yoma.repo.TransactionRepo;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionRepo tranRepo;
	
	@Autowired
	ErrorHandling errorHandle;
	
	@Override
	public ResponseView<List<Trans>> getTrans(Trans tran) {
		ResponseView<List<Trans>> resp=new ResponseView<>();
		List<Trans> tranList=new ArrayList<Trans>();
		try {
			tranList=tranRepo.getTrans(tran.getFrom_acc(),tran.getTo_acc(),tran.getTran_date());
				
			if(tranList == null) {
				throw new NoDataFoundException();
			}
			resp.setStatusCode("200");
			resp.setMessage("Transaction List");
			resp.setModel(tranList);
		}catch (Exception e) {
			errorHandle.errorHandling(e, resp);
		}
		
		return resp;
	}

	@Override
	public ResponseView<List<Trans>> getAllTrans() {
		ResponseView<List<Trans>> resp=new ResponseView<>();
		List<Trans> tranList=new ArrayList<Trans>();
		try {
			tranList=tranRepo.findAll();
				
			if(tranList == null) {
				throw new NoDataFoundException();
			}
			resp.setStatusCode("200");
			resp.setMessage("Transaction List");
			resp.setModel(tranList);
		}catch (Exception e) {
			errorHandle.errorHandling(e, resp);
		}
		
		return resp;
	}

}
