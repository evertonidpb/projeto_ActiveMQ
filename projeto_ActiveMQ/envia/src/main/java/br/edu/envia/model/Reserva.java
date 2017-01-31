package br.edu.envia.model;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Reserva implements Serializable {

	private static final long serialVersionUID = 9369695818057571L;
	
	@Id
	@GeneratedValue
	private int id;
	private Usuario usuario;
	
	
	public Reserva(){
		
		
	}
	
	public Reserva(int id, Usuario usuario) {
		super();
		this.id = id;
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Reserva [id=" + id + ", usuario=" + usuario + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario( Usuario usuario) {
		this.usuario = usuario;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}