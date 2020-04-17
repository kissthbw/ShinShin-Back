package com.bit.model.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.bit.model.CatalogoTienda;
import com.bit.model.dto.SimpleResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class DetalleProducoRSP extends SimpleResponse {

	private static final long serialVersionUID = 4428570789013267038L;

	private int ranking;
	private boolean isFavorite;
	private List<CatalogoTienda> tiendas = new ArrayList<>();

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public List<CatalogoTienda> getTiendas() {
		return tiendas;
	}

	public void setTiendas(List<CatalogoTienda> tiendas) {
		this.tiendas = tiendas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
