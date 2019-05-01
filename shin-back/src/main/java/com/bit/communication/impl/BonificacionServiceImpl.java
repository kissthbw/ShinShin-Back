package com.bit.communication.impl;

import org.springframework.stereotype.Service;

import com.bit.communication.BonificacionService;
import com.bit.exception.CommunicationException;
import com.bit.model.dto.BonificacionDTO;
import com.bit.model.dto.SimpleResponse;

@Service
public class BonificacionServiceImpl implements BonificacionService {

	@Override
	public SimpleResponse bonificacionPayPal(BonificacionDTO data) throws CommunicationException {
		return null;
	}

	@Override
	public SimpleResponse bonificacionTiempoAire(BonificacionDTO data) throws CommunicationException {
		return null;
	}

}
