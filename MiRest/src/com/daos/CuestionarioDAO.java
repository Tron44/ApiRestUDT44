package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.clases.TablaAsignatura;
import com.clases.TablaCuestionario;
import com.clases.TablaGrado;
import com.clases.TablaPregunta;
import com.clases.TablaRespuesta;

/**
 * @author rarranz
 * Clase DAO de acceso a los grados, asignaturas, cuestionarios y preguntas/respuestas del modelo de datos
 *
 */
public class CuestionarioDAO {
	private static final String NOMBREDAO = "CuestionarioDAO ";
	private Connection conexion = null;

	public CuestionarioDAO() {
		conexion = ConexionBd.getConnection("ias");
	}

	/**
	 * recuperación de grados
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public ArrayList<TablaGrado> getGrados() throws DaoException, SQLException {

		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "getGrados", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}

		PreparedStatement stmt = null;
		ArrayList<TablaGrado> listaGrado = new ArrayList<TablaGrado>();
		TablaGrado tablagrado = null;
		try {
			stmt = conexion.prepareStatement("SELECT idGrado, nombreGrado FROM cuestionarioudima.grado");
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				tablagrado = new TablaGrado();
				tablagrado.setIdGrado(rs.getInt("idGrado"));
				tablagrado.setNombreGrado(rs.getString("nombreGrado"));
				listaGrado.add(tablagrado);
			}

		} catch (SQLException sqle) {			
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "getGrados", "getGrados", "conn",
					"Error al ejecutar la sentencia sql");
		} finally {
			// Cerramos la PreparedStatement
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex2) {
					ex2.printStackTrace();
				}
			}
			// Cerramos la conexión con la base de datos
			if (conexion != null) {
				try {
					conexion.close();
					conexion = null;
				} catch (Exception ex3) {
					ex3.printStackTrace();
				}
			}
		}

		return listaGrado;
	}

	/**
	 * recuperación de asignaturas por grado
	 * @param gradoSel
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public ArrayList<TablaAsignatura> getAsignaturasByGrado(int gradoSel) throws DaoException, SQLException {

		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "getAsignaturasByGrado", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}

		PreparedStatement stmt = null;
		ArrayList<TablaAsignatura> listaAsignaturas = new ArrayList<TablaAsignatura>();
		TablaAsignatura tablaAsignatura = null;
		try {

			stmt = conexion.prepareStatement(
					"select idAsignatura, nombreAsignatura, codigoAsignatura, idGrado from cuestionarioudima.asignatura\r\n"
							+ "where idGrado = " + gradoSel);

			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				tablaAsignatura = new TablaAsignatura();
				tablaAsignatura.setIdAsignatura(rs.getInt("idAsignatura"));
				tablaAsignatura.setNombreAsignatura(rs.getString("nombreAsignatura"));
				tablaAsignatura.setCodigoAsignatura(rs.getString("codigoAsignatura"));
				tablaAsignatura.setIdGrado(rs.getInt("idGrado"));
				listaAsignaturas.add(tablaAsignatura);
			}

		} catch (SQLException sqle) {			
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "getAsignaturasByGrado", "getAsignaturasByGrado", "conn",
					"Error al ejecutar la sentencia sql");
		} finally {
			// Cerramos la PreparedStatement
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex2) {
					ex2.printStackTrace();
				}
			}
			// Cerramos la conexión con la base de datos
			if (conexion != null) {
				try {
					conexion.close();
					conexion = null;
				} catch (Exception ex3) {
					ex3.printStackTrace();
				}
			}
		}

		return listaAsignaturas;
	}

	/**
	 * recuperación de cuestionarios por asignatura
	 * @param asignaturaSel
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public ArrayList<TablaCuestionario> getCuestionariosByAsignatura(int asignaturaSel)
			throws DaoException, SQLException {

		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "getAsignaturasByGrado", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}

		PreparedStatement stmt = null;
		ArrayList<TablaCuestionario> listaCuestionarios = new ArrayList<TablaCuestionario>();
		TablaCuestionario tablaCuestionario = null;
		try {
			stmt = conexion.prepareStatement(
					"select idCuestionario, nombreCuestionario, publicacion, idAsignatura from cuestionarioudima.cuestionario\r\n"
							+ "where publicacion=1 and idAsignatura = " + asignaturaSel);			
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				tablaCuestionario = new TablaCuestionario();
				tablaCuestionario.setIdCuestionario(rs.getInt("idCuestionario"));
				tablaCuestionario.setNombreCuestionario(rs.getString("nombreCuestionario"));
				tablaCuestionario.setPublicacion(rs.getInt("publicacion"));
				tablaCuestionario.setIdAsignatura(rs.getInt("idAsignatura"));
				listaCuestionarios.add(tablaCuestionario);
			}

		} catch (SQLException sqle) {			
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "getCuestionariosByAsignatura", "getCuestionariosByAsignatura",
					"conn", "Error al ejecutar la sentencia sql");
		} finally {
			// Cerramos la PreparedStatement
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex2) {
					ex2.printStackTrace();
				}
			}
			// Cerramos la conexión con la base de datos
			if (conexion != null) {
				try {
					conexion.close();
					conexion = null;
				} catch (Exception ex3) {
					ex3.printStackTrace();
				}
			}
		}

		return listaCuestionarios;
	}

	/**
	 * recuperación de preguntas y respuestas por cuestionario
	 * @param controlSel
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public ArrayList<TablaPregunta> getPreguntasRespuestasByCuestionario(int controlSel)
			throws DaoException, SQLException {

		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "getPreguntasRespuestasByCuestionario", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}

		PreparedStatement stmt = null;
		ArrayList<TablaPregunta> listaPreguntasRespuestas = new ArrayList<TablaPregunta>();
		ArrayList<TablaRespuesta> listaRespuestas = new ArrayList<TablaRespuesta>();
		TablaPregunta tablaPregunta = null;
		TablaRespuesta tablaRespuesta = null;
		int idPreg = 0;		
		int idPregdeRespuesta = 0;
		int primeravez = 0;
		String txtPreg = "";
		String tipoPreg = "";
		int idResp = 0;
		String txtResp = "";
		int finalbucle = 0;		
		try {
			String sq = "select idCuestionario, idPregunta, textoPregunta, tipoPregunta, idPreguntaResp, idRespuesta, textoRespuesta, validaRespuesta, numRespuestas"
					+ " from cuestionarioudima.pregunta, cuestionarioudima.respuesta where idPregunta=idPreguntaResp"
					+ " and idCuestionario= " + controlSel;
			stmt = conexion.prepareStatement(sq);			
			ResultSet rs = stmt.executeQuery();
			rs.next();			
			bucletotal: while (finalbucle == 0) {
				listaRespuestas = new ArrayList<TablaRespuesta>();
				tablaPregunta = new TablaPregunta();
				idPreg = rs.getInt("idPregunta");
				txtPreg = rs.getString("textoPregunta");
				tipoPreg = rs.getString("tipoPregunta");
				tablaPregunta.setIdPregunta(idPreg);
				tablaPregunta.setTextoPregunta(txtPreg);
				tablaPregunta.setTipoPregunta(tipoPreg);
				tablaPregunta.setNumeroRespuestas(rs.getString("numRespuestas"));

				do {
					tablaRespuesta = new TablaRespuesta();
					idResp = rs.getInt("idRespuesta");
					txtResp = rs.getString("textoRespuesta");
					tablaRespuesta.setIdRespuesta(idResp);
					tablaRespuesta.setTextoRespuesta(txtResp);
					if (primeravez == 0) {
						tablaRespuesta.setIdPreguntaResp(rs.getInt("idPreguntaResp"));
						primeravez = 1;
					}
					if (rs.getInt("validaRespuesta") == 1) {
						tablaRespuesta.setValido(true);
					} else {
						tablaRespuesta.setValido(false);
					}
					listaRespuestas.add(tablaRespuesta);
					try {
						rs.next();
						idPregdeRespuesta = rs.getInt("idPreguntaResp");
						if (idPregdeRespuesta == idPreg) {
							tablaRespuesta.setIdPreguntaResp(idPregdeRespuesta);
						} else {
							tablaRespuesta.setIdPreguntaResp(idPreg);
						}

					} catch (Exception e) {						
						finalbucle = 1;
						tablaRespuesta.setIdPreguntaResp(idPregdeRespuesta);
						tablaPregunta.setListaRespuestas(listaRespuestas);
						listaPreguntasRespuestas.add(tablaPregunta);
						break bucletotal;
					}
				} while (idPreg == idPregdeRespuesta);

				tablaPregunta.setListaRespuestas(listaRespuestas);
				listaPreguntasRespuestas.add(tablaPregunta);
			}

		} catch (SQLException sqle) {			
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "getPreguntasRespuestasByCuestionario",
					"getPreguntasRespuestasByCuestionario", "conn", "Error al ejecutar la sentencia sql");
		} finally {
			// Cerramos la PreparedStatement
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex2) {
					ex2.printStackTrace();
				}
			}
			// Cerramos la conexión con la base de datos
			if (conexion != null) {
				try {
					conexion.close();
					conexion = null;
				} catch (Exception ex3) {
					ex3.printStackTrace();
				}
			}
		}

		return listaPreguntasRespuestas;
	}
}
