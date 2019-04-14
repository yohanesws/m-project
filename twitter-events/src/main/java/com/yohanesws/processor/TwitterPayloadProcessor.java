package com.yohanesws.processor;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import twitter4j.QueryResult;

public class TwitterPayloadProcessor implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		// TODO Auto-generated method stub
		QueryResult Query = (QueryResult) eventContext.getMessage().getPayload();
		return Query.getTweets();
	}

}
