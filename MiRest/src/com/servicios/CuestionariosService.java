package com.servicios;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.clases.TablaCuestionario;
import com.daos.CuestionarioDAO;
import com.daos.DaoException;

/**
 * Servicio para recuperar los cuestinarios existentes en el sistema, se le pasa
 * la asignatura para sacar los relativos a este último.
 * 
 * @author rarranz
 *
 */
@Path("/cuestionarios/{asigpas}")
public class CuestionariosService {

	@GET
	@Produces("application/json")
	public Response getCuestionarios(@PathParam("asigpas") String asigpas) throws JSONException {
		CuestionarioDAO cdao = new CuestionarioDAO();
		ArrayList<TablaCuestionario> listaCuestionarios = null;		
		try {
			listaCuestionarios = cdao.getCuestionariosByAsignatura(Integer.parseInt(asigpas));
		} catch (DaoException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(listaCuestionarios.toString()).build();
	}
}