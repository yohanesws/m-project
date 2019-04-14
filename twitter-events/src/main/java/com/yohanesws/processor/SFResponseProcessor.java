package com.yohanesws.processor;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import com.sforce.soap.partner.SaveResult;
import com.yohanesws.exception.SalesForceBusinessException;

public class SFResponseProcessor implements Callable {
	private final static String ignoreStatusCode = "DUPLICATES_DETECTED";

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		SaveResult response = (SaveResult) eventContext.getMessage().getPayload();
		StringBuffer statuscode = new StringBuffer();
		Boolean hasError = false;
//		if (response.getErrors().length > 0)
//			hasError = true;
		for (com.sforce.soap.partner.Error error : response.getErrors()) {
			if (!error.getStatusCode().name().equals(ignoreStatusCode)) {
				statuscode.append(error.getStatusCode().name());
				statuscode.append(":::");
				hasError = true;
			}
		}
		if (hasError) {
			throw new SalesForceBusinessException(statuscode.toString());
		}

		return null;
	}

}
