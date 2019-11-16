package com.bit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "usuario")
public class Usuario {

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "ap_paterno")
	private String apPaterno;

	@Column(name = "ap_materno")
	private String apMaterno;

	@Column(name = "fecha_nac")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaNac;

	@Column(name = "foto_usuario")
	private String fotoUsuario;

	@Column(name = "tel_movil")
	private String telMovil;

	@Column(name = "correo_electronico")
	private String correoElectronico;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "contrasenia")
	private String contrasenia;

	@Column(name = "calle")
	private String calle;

	@Column(name = "num_ext")
	private String numeroExt;

	@Column(name = "num_int")
	private String numeroInt;

	@Column(name = "colonia")
	private String colonia;

	@Column(name = "codigo_postal")
	private String codigoPostal;

	@Column(name = "del_mun")
	private String delMun;

	@Column(name = "estado")
	private String estado;

	@Column(name = "estatus_activacion")
	private boolean estatusActivacion;

	@Column(name = "codigo_verificacion")
	private String codigoVerificacion;

	@Column(name = "id_catalogo_sexo")
	private Integer idCatalogoSexo;
	
	@Column(name = "id_catalogo_red_social")
	private Integer idRedSocial;
	
	@Column(name = "estatus")
	private Integer estatus;
	
	@Transient
	private String imageData;
	
	@Transient
	private String contraseniaActual;
	
	@Transient
	private String confirmarContrasenia;
	
	@Column(name = "img_url")
	private String imgUrl;
	
	@Column(name = "hash")
	private String hash;
	
	@Column(name = "password_restore_link")
	private String password_restore_link;
	
	@Column(name = "time_restore_link")
	@Temporal(TemporalType.TIMESTAMP)
	private Date time_restore_link;

	// Obtener productos favoritos
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "producto_favorito", joinColumns = { @JoinColumn(name = "id_usuario") }, inverseJoinColumns = {
			@JoinColumn(name = "id_producto") })
	private List<Producto> productosFavoritos = new ArrayList<>();

	// Guardar tickets
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "historico_tickets", joinColumns = {
			@JoinColumn(name = "usuario_id_usuario") }, inverseJoinColumns = { @JoinColumn(name = "ticket_id_ticket") })
	private List<Ticket> tickets = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") })
    private Set<Authority> authorities = new HashSet<>();

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public String getFotoUsuario() {
		return fotoUsuario;
	}

	public void setTelMovil(String telMovil) {
		this.telMovil = telMovil;
	}

	public String getTelMovil() {
		return telMovil;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCalle() {
		return calle;
	}

	public void setNumeroExt(String numeroExt) {
		this.numeroExt = numeroExt;
	}

	public String getNumeroExt() {
		return numeroExt;
	}

	public void setNumeroInt(String numeroInt) {
		this.numeroInt = numeroInt;
	}

	public String getNumeroInt() {
		return numeroInt;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getColonia() {
		return colonia;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setDelMun(String delMun) {
		this.delMun = delMun;
	}

	public String getDelMun() {
		return delMun;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstatusActivacion(boolean estatusActivacion) {
		this.estatusActivacion = estatusActivacion;
	}

	public boolean isEstatusActivacion() {
		return estatusActivacion;
	}

	public void setCodigoVerificacion(String codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}

	public String getCodigoVerificacion() {
		return codigoVerificacion;
	}

	public Integer getIdCatalogoSexo() {
		return idCatalogoSexo;
	}

	public void setIdCatalogoSexo(Integer idCatalogoSexo) {
		this.idCatalogoSexo = idCatalogoSexo;
	}

	public Integer getIdRedSocial() {
		return idRedSocial;
	}

	public void setIdRedSocial(Integer idRedSocial) {
		this.idRedSocial = idRedSocial;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public String getContraseniaActual() {
		return contraseniaActual;
	}

	public void setContraseniaActual(String contraseniaActual) {
		this.contraseniaActual = contraseniaActual;
	}

	public String getConfirmarContrasenia() {
		return confirmarContrasenia;
	}

	public void setConfirmarContrasenia(String confirmarContrasenia) {
		this.confirmarContrasenia = confirmarContrasenia;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPassword_restore_link() {
		return password_restore_link;
	}

	public void setPassword_restore_link(String password_restore_link) {
		this.password_restore_link = password_restore_link;
	}

	public Date getTime_restore_link() {
		return time_restore_link;
	}

	public void setTime_restore_link(Date time_restore_link) {
		this.time_restore_link = time_restore_link;
	}

	public List<Producto> getProductosFavoritos() {
		return productosFavoritos;
	}

	public void setProductosFavoritos(List<Producto> productos) {
		this.productosFavoritos = productos;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public void addProductoFavorito(Producto producto) {
		productosFavoritos.add(producto);
		// address.getOwners().add( this );
	}

	public void removeProductoFavoritos(Producto producto) {
		productosFavoritos.remove(producto);
		// address.getOwners().remove( this );
	}

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

}
