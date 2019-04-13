package com.bit.service;

import java.util.List;

import com.bit.model.Historico;

public interface HistoricoService {

	List<Historico> getHistoricos();

	void guardarHistoricos(Historico item);

}
