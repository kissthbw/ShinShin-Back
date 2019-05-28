package com.bit.communication;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bit.config.WebConfig;
import com.bit.exception.CommunicationException;
import com.bit.model.dto.request.PayPalPayout;
import com.bit.model.dto.request.PayPalPayoutAmount;
import com.bit.model.dto.request.PayPalPayoutRQT;
import com.bit.model.dto.request.PayPalSenderHeader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class BonificacionServiceTest {

	@Autowired
	private PayPalCommunication bonificacionService;
	
	@Test
	public void token() throws CommunicationException {
		bonificacionService.token();
	}
	
	@Test
	public void payout() throws CommunicationException {
		PayPalPayoutRQT rqt = new PayPalPayoutRQT();
		PayPalSenderHeader header = new PayPalSenderHeader();
		header.setEmail_message("Tienes una bonificacion");
		header.setEmail_subject("Bonificacion");
		header.setSender_batch_id("2014020004");
		
		
		PayPalPayout item = new PayPalPayout();
		PayPalPayoutAmount amount = new PayPalPayoutAmount();
		amount.setCurrency("USD");
		amount.setValue("1.00");
		
		item.setRecipient_type("EMAIL");
		item.setNote("Gracias por usar ShinShin");
		item.setSender_item_id("0001");
		item.setReceiver("kissthbw@gmail.com");
		item.setAmount(amount);
		
		List<PayPalPayout> items = new ArrayList<>();
		items.add(item);
		
		rqt.setSender_batch_header(header);
		rqt.setItems(items);
		
		bonificacionService.bonificacionPayPal(rqt, "A21AAFepZKntSaXjJJhT9AIDE1KUajmxbO46i_GNlnN7Dv9WZhBpOX4V03k3QC2Y9fDweqhTvtXfmodJ_c0tYxARcHkNH7Eww");
	}
	
	@Test
	public void test() {
		try {
			bonificacionService.bonificacionPayPal(null, null);
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
