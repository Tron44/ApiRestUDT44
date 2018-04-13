package com.clases;

import java.io.Serializable;
/**
 * 
 * @author rarranz
 *
 */
public class TablaRespuesta implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos del objeto de respuesta. TablaRespuesta
	private int idRespuesta = 0;
	private String textoRespuesta = null;
	private boolean valido = false;
	private int idPreguntaResp = 0;

	public int getIdPreguntaResp() {
		return idPreguntaResp;
	}

	public void setIdPreguntaResp(int idPreguntaResp) {
		this.idPreguntaResp = idPreguntaResp;
	}

	public int getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getTextoRespuesta() {
		return textoRespuesta;
	}

	public void setTextoRespuesta(String textoRespuesta) {
		this.textoRespuesta = textoRespuesta;
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	@Override
	public String toString() {
		return "{\"idRespuesta\":\"" + idRespuesta + "\",  \"idPreguntaResp\":\"" + idPreguntaResp
				+ "\",\"textoRespuesta\":\"" + textoRespuesta + "\", \"valido\":\"" + valido + "\"}";

	}

}
