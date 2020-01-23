package com.bit.model.dto;

import java.util.List;

public class Category {

	private String titulo;
	private List<Item> items;

	public Category() {
	}
	
	public Category( String titulo, List<Item> items ) {
		this.titulo = titulo;
		this.items = items;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Category [" + (titulo != null ? "titulo=" + titulo + ", " : "")
				+ (items != null ? "items=" + items : "") + "]";
	}

}
