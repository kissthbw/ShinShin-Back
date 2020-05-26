package com.bit.model.report;

import java.io.Serializable;

public class ProductoTicketUsuarioReport implements Serializable {

	private static final long serialVersionUID = -7796444237712215261L;

	private Long idUsuario;
	private String usuario;
	private String nombre;
	private String edad;
	private String sexo;
	private String email;
	private String telefono;
	private String cp;
	private String desde;
	private String via;
	private String retirosBancarios;
	private String retirosPayPal;
	private String recargas;
	private Integer productosEscaneados;
	private String bonificacionActual;
	private String tickets;
	private String tienda;
	private String fechaTicket;
	private String horaTicket;
	private String fechaEscaneo;
	private String horaEscaneo;
	private String cpTicketTienda;
	private String cpTicketFiscal;
	private String producto;
	private String contenido;
	private String marca;
	private String depto;
	private String tipo;
	private String bonificacion;

	public ProductoTicketUsuarioReport() {
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getRetirosBancarios() {
		return retirosBancarios;
	}

	public void setRetirosBancarios(String retirosBancarios) {
		this.retirosBancarios = retirosBancarios;
	}

	public String getRetirosPayPal() {
		return retirosPayPal;
	}

	public void setRetirosPayPal(String retirosPayPal) {
		this.retirosPayPal = retirosPayPal;
	}

	public String getRecargas() {
		return recargas;
	}

	public void setRecargas(String recargas) {
		this.recargas = recargas;
	}

	public Integer getProductosEscaneados() {
		return productosEscaneados;
	}

	public void setProductosEscaneados(Integer productosEscaneados) {
		this.productosEscaneados = productosEscaneados;
	}

	public String getBonificacionActual() {
		return bonificacionActual;
	}

	public void setBonificacionActual(String bonificacionActual) {
		this.bonificacionActual = bonificacionActual;
	}

	public String getTickets() {
		return tickets;
	}

	public void setTickets(String tickets) {
		this.tickets = tickets;
	}

	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public String getFechaTicket() {
		return fechaTicket;
	}

	public void setFechaTicket(String fechaTicket) {
		this.fechaTicket = fechaTicket;
	}

	public String getHoraTicket() {
		return horaTicket;
	}

	public void setHoraTicket(String horaTicket) {
		this.horaTicket = horaTicket;
	}

	public String getFechaEscaneo() {
		return fechaEscaneo;
	}

	public void setFechaEscaneo(String fechaEscaneo) {
		this.fechaEscaneo = fechaEscaneo;
	}

	public String getHoraEscaneo() {
		return horaEscaneo;
	}

	public void setHoraEscaneo(String horaEscaneo) {
		this.horaEscaneo = horaEscaneo;
	}

	public String getCpTicketTienda() {
		return cpTicketTienda;
	}

	public void setCpTicketTienda(String cpTicketTienda) {
		this.cpTicketTienda = cpTicketTienda;
	}

	public String getCpTicketFiscal() {
		return cpTicketFiscal;
	}

	public void setCpTicketFiscal(String cpTicketFiscal) {
		this.cpTicketFiscal = cpTicketFiscal;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(String bonificacion) {
		this.bonificacion = bonificacion;
	}

	@Override
	public String toString() {
		return "ProductoTicketUsuarioReport [" + (idUsuario != null ? "idUsuario=" + idUsuario + ", " : "")
				+ (usuario != null ? "usuario=" + usuario + ", " : "")
				+ (nombre != null ? "nombre=" + nombre + ", " : "") + (edad != null ? "edad=" + edad + ", " : "")
				+ (sexo != null ? "sexo=" + sexo + ", " : "") + (email != null ? "email=" + email + ", " : "")
				+ (telefono != null ? "telefono=" + telefono + ", " : "") + (cp != null ? "cp=" + cp + ", " : "")
				+ (desde != null ? "desde=" + desde + ", " : "") + (via != null ? "via=" + via + ", " : "")
				+ (retirosBancarios != null ? "retirosBancarios=" + retirosBancarios + ", " : "")
				+ (retirosPayPal != null ? "retirosPayPal=" + retirosPayPal + ", " : "")
				+ (recargas != null ? "recargas=" + recargas + ", " : "")
				+ (productosEscaneados != null ? "productosEscaneados=" + productosEscaneados + ", " : "")
				+ (bonificacionActual != null ? "bonificaciónActual=" + bonificacionActual + ", " : "")
				+ (tickets != null ? "tickets=" + tickets + ", " : "")
				+ (tienda != null ? "tienda=" + tienda + ", " : "")
				+ (fechaTicket != null ? "fechaTicket=" + fechaTicket + ", " : "")
				+ (horaTicket != null ? "horaTicket=" + horaTicket + ", " : "")
				+ (fechaEscaneo != null ? "fechaEscaneo=" + fechaEscaneo + ", " : "")
				+ (horaEscaneo != null ? "horaEscaneo=" + horaEscaneo + ", " : "")
				+ (cpTicketTienda != null ? "cpTicketTienda=" + cpTicketTienda + ", " : "")
				+ (cpTicketFiscal != null ? "cpTicketFiscal=" + cpTicketFiscal + ", " : "")
				+ (producto != null ? "producto=" + producto + ", " : "")
				+ (contenido != null ? "contenido=" + contenido + ", " : "")
				+ (marca != null ? "marca=" + marca + ", " : "") + (depto != null ? "depto=" + depto + ", " : "")
				+ (tipo != null ? "tipo=" + tipo + ", " : "")
				+ (bonificacion != null ? "bonificacion=" + bonificacion : "") + "]";
	}

	
}
