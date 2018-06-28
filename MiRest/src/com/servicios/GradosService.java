package com.servicios;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONException;

import com.clases.TablaGrado;
import com.daos.CuestionarioDAO;
import com.daos.DaoException;
import com.sun.jersey.api.json.JSONWithPadding;

/**
 * Servicio para recuperar los grados existentes en el sistema
 * 
 * @author rarranz
 *
 */
@Path("/grados")
public class GradosService {

	@GET
	@Produces("application/json")
	public JSONWithPadding getGrados(@QueryParam("callback") String callback) throws JSONException {
		CuestionarioDAO cdao = new CuestionarioDAO();
		ArrayList<TablaGrado> listaGrados = new ArrayList<TablaGrado>();
		try {
			listaGrados = cdao.getGrados();
		} catch (DaoException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		// para json:return Response.status(Response.Status.OK).entity(new JSONWithPadding(listaGrados.toString(), callback)).build();
		return new JSONWithPadding("callback(" + listaGrados.toString()+ ")", callback);
	}
}
