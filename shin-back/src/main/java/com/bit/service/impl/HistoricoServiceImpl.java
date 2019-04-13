package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.HistoricoDAO;
import com.bit.model.Historico;
import com.bit.service.HistoricoService;

@Service
public class HistoricoServiceImpl implements HistoricoService {

	@Autowired
	private HistoricoDAO historicoDAO;

	@Override
	@Transactional
	public List<Historico> getHistoricos() {
		List<Historico> list = historicoDAO.getHistoricos();
		return list;
	}

	@Override
	@Transactional
	public void guardarHistoricos(Historico item) {
		historicoDAO.save(item);
	}

}
