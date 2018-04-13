package com.clases;

import java.io.Serializable;
/**
 * 
 * @author rarranz
 *
 */
public class TablaGrado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// atributos del objeto de grado. TablaGrado
	private int idGrado = 0;
	private String nombreGrado = null;

	public String getNombreGrado() {
		return nombreGrado;
	}

	public int getIdGrado() {
		return idGrado;
	}

	public void setIdGrado(int idGrado) {
		this.idGrado = idGrado;
	}

	public void setNombreGrado(String nombreGrado) {
		this.nombreGrado = nombreGrado;
	}

	@Override
	public String toString() {
		// [{"id":1,"description":"DESC1"},{"id":2,"description":"DESC2"},{"id":3,"description":"DESC3"}]
		return "{\"idGrado\":\"" + idGrado + "\", \"nombreGrado\":\"" + nombreGrado + "\"}";
	}

}
