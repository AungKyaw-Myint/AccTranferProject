package com.yoma.service;

import java.util.List;

import com.yoma.model.Trans;
import com.yoma.model.view.ResponseView;

public interface TransactionService {
	
	ResponseView<List<Trans>> getTrans(Trans tran);
	ResponseView<List<Trans>> getAllTrans();
}
