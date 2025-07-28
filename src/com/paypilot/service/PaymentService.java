package com.paypilot.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.paypilot.model.Payment;

public class PaymentService {
    // Singleton class design patterN
    private static PaymentService inst=null;
	// Function to get instance of PaymentService
	public static PaymentService getInstance() {
		if(inst  == null) {
			inst=new PaymentService();
		}
		return inst;
	}

    // Get the Map with key as payment mode and value as number of payments using that payment mode.
	public  Map<String,Long> getModeCount(List<Payment> payments){
		
		Map<String, Long> modeCount=payments.stream().collect(Collectors.groupingBy(Payment::getMode,Collectors.counting()));
		
		return modeCount;
	}
}
