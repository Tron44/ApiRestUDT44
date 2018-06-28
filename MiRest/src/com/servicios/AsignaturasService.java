package com.servicios;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONException;

import com.clases.TablaAsignatura;
import com.daos.CuestionarioDAO;
import com.daos.DaoException;
import com.sun.jersey.api.json.JSONWithPadding;

/**
 * Servicio para recuperar las asignaturas existentes en el sistema, se le pasa
 * el grado para sacar las relativas a este último.
 * 
 * @author rarranz
 *
 */
@Path("/asignaturas/{gradopas}")
public class AsignaturasService {

	@GET
	@Produces("application/json")
	public JSONWithPadding getAsignaturas(@PathParam("gradopas") String gradopas, @QueryParam("callback") String callback) throws JSONException {
		CuestionarioDAO cdao = new CuestionarioDAO();
		ArrayList<TablaAsignatura> listaAsignaturas = null;
		try {
			listaAsignaturas = cdao.getAsignaturasByGrado(Integer.parseInt(gradopas));
		} catch (DaoException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return new JSONWithPadding("callback(" + listaAsignaturas.toString()+ ")", callback);
	}
}