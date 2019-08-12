package com.bit.common;

public class TicketAnalizerFactory {
	public static TicketAnalizer getAnalizer( String identifier ) {
		if ( null == identifier ) {
			return null;
		}
		
		else if( "WALMART".equalsIgnoreCase(identifier) ) {
			return new WalmartTicketAnalizer();
		}
		
		else if( "OXXO".equalsIgnoreCase(identifier) ) {
			return new OxxoTicketAnalizer();
		}
		
		else if( "SORIANA".equalsIgnoreCase(identifier) ) {
			return new SorianaTicketAnalizer();
		}
		else if( "7ELEVEN".equalsIgnoreCase(identifier) ) {
			return new SevenTicketAnalizer();
		}
		
		return null;
	}
}
