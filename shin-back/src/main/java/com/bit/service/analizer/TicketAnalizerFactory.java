package com.bit.service.analizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bit.service.analizer.impl.OxxoTicketAnalizer;
import com.bit.service.analizer.impl.SevenTicketAnalizer;
import com.bit.service.analizer.impl.SorianaTicketAnalizer;
import com.bit.service.analizer.impl.WalmartTicketAnalizer;

@Component
public class TicketAnalizerFactory {
	
	@Autowired
	private WalmartTicketAnalizer walmartTicketAnalizer;
	
	@Autowired
	private OxxoTicketAnalizer oxxoTicketAnalizer;
	
	@Autowired
	private SorianaTicketAnalizer sorianaTicketAnalizer;
	
	@Autowired
	private SevenTicketAnalizer sevenTicketAnalizer;
	
	public TicketAnalizer getAnalizer( String identifier ) {
		if ( null == identifier ) {
			return null;
		}
		
		else if( "WALMART".equalsIgnoreCase(identifier) ) {
			return walmartTicketAnalizer;
		}
		
		else if( "OXXO".equalsIgnoreCase(identifier) ) {
			return oxxoTicketAnalizer;
		}
		
		else if( "SORIANA".equalsIgnoreCase(identifier) ) {
			return sorianaTicketAnalizer;
		}
		else if( "7ELEVEN".equalsIgnoreCase(identifier) ) {
			return sevenTicketAnalizer;
		}
		
		return null;
	}
}
