package com.bit.communication;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class ExampleTwilio {
	// Find your Account Sid and Token at twilio.com/user/account
	public static final String ACCOUNT_SID = "AC021b53d8f2e2a1ba77deee1627bfad27";
	public static final String AUTH_TOKEN = "e7098c00b8713b538575558cc2671015";

	public static void main(String[] args) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Message message = Message.creator(
				new PhoneNumber("+525550679875"), 
				new PhoneNumber("+12512205528"),
				"Ya la hicimos").create();

		System.out.println(message.getSid());
	}
}