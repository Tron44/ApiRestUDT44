package com.servicios;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONException;

import com.clases.TablaPregunta;
import com.daos.CuestionarioDAO;
import com.daos.DaoException;
import com.sun.jersey.api.json.JSONWithPadding;

/**
 * Servicio para recuperar las preguntas y respuestas existentes en el sistema,
 * se le pasa el cuestionario para sacar las relativos a este último.
 * 
 * @author rarranz
 *
 */
@Path("/preguntas/{cuestpas}")
public class PreguntasRespService {

	@GET
	@Produces("application/json")
	public JSONWithPadding getPreguntas(@PathParam("cuestpas") String cuestpas, @QueryParam("callback") String callback) throws JSONException {
		CuestionarioDAO cdao = new CuestionarioDAO();
		ArrayList<TablaPregunta> listaPreguntas = null;
		try {
			listaPreguntas = cdao.getPreguntasRespuestasByCuestionario(Integer.parseInt(cuestpas));
		} catch (DaoException e) {
			listaPreguntas = new ArrayList<TablaPregunta>();
		} catch (SQLException e) {
			listaPreguntas = new ArrayList<TablaPregunta>();
		}		
		return new JSONWithPadding("callback(" + listaPreguntas.toString()+ ")", callback);
		
	}
}