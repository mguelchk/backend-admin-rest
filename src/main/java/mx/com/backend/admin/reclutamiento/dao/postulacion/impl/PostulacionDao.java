package mx.com.backend.admin.reclutamiento.dao.postulacion.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mx.com.backend.admin.reclutamiento.core.exception.DaoDataAccesException;
import mx.com.backend.admin.reclutamiento.core.exception.SpErrorException;
import mx.com.backend.admin.reclutamiento.dao.mapper.PostulacionMapper;
import mx.com.backend.admin.reclutamiento.dao.mapper.VacanteMapper;
import mx.com.backend.admin.reclutamiento.dao.postulacion.IPostulacionDao;
import mx.com.backend.admin.reclutamiento.dao.sp.PostulacionSP;
import mx.com.backend.admin.reclutamiento.models.Postulacion;
import mx.com.backend.admin.reclutamiento.models.Vacante;

@Service
public class PostulacionDao implements IPostulacionDao {

	private final static Logger log = LoggerFactory.getLogger(PostulacionDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Postulacion crearPostulacionPorUsuario(Postulacion postulacion) throws DaoDataAccesException {
		try {

			PostulacionSP sp = new PostulacionSP(jdbcTemplate, "crearPostulacion");

			Integer vac = sp.crearPostulacion(postulacion);

			return new Postulacion();

		} catch (SpErrorException e) {

			log.error("..:: ERROR CAPA DAO AL CREAR POSTULACION::.. [METODO: crearPostulacionPorUsuario] "
					+ e.getMessage(), e);

			throw new DaoDataAccesException(e);
		}
	}

	@Override
	public List<Postulacion> obtenerPostulacionesPorCriterios(String qryRestantes, Object[] values)
			throws DaoDataAccesException {
		List<Postulacion> postulacion;
		try {
			postulacion = jdbcTemplate.query("SELECT \n" + 
					"	V.id_vacante,\n" + 
					"	V.nombre_vacante,\n" + 
					"	V.sueldo,\n" + 
					"	V.descripcion_breve,\n" + 
					"	V.descripcion,\n" + 
					"	V.requisitos,\n" + 
					"	V.num_vacantes,\n" + 
					"	V.fecha_contratacion,\n" + 
					"	V.experiencia,\n" + 
					"	V.estudios,\n" + 
					"	V.viajar,\n" + 
					"	V.idioma,\n" + 
					"	V.edad,\n" + 
					"	V.tipo_licencia,\n" + 
					"	V.discapacidad,\n" + 
					"	V.publicada,\n" + 
					"	C.id_cliente,  \n" + 
					"	C.nombre as nombre_cliente,\n" + 
					"	E.id_estado,\n" + 
					"	E.nombre as nombre_estado,\n" + 
					"	A.id_area, \n" + 
					"	A.nombre as nombre_area,\n" + 
					"     count(*) as total_usuarios\n" + 
					"FROM VACANTES_USUARIOS VU \n" + 
					"INNER JOIN VACANTES V ON  VU.id_vacante = V.id_vacante \n" + 
					"INNER JOIN CLIENTES C ON V.id_cliente = C.id_cliente\n" + 
					"INNER JOIN ESTADOS E ON V.id_estado = E.id_estado\n" + 
					"INNER JOIN AREAS A ON V.id_area = A.id_area\n" + 
					"WHERE V.publicada = 1 AND V.activo = 1 \n" + 
					qryRestantes+
					" group by \n" + 
					"    V.id_vacante,\n" + 
					"	V.nombre_vacante,\n" + 
					"	V.sueldo,\n" + 
					"	V.descripcion_breve,\n" + 
					"	V.descripcion,\n" + 
					"	V.requisitos,\n" + 
					"	V.num_vacantes,\n" + 
					"	V.fecha_contratacion,\n" + 
					"	V.experiencia,\n" + 
					"	V.estudios,\n" + 
					"	V.viajar,\n" + 
					"	V.idioma,\n" + 
					"	V.edad,\n" + 
					"	V.tipo_licencia,\n" + 
					"	V.discapacidad,\n" + 
					"	V.publicada,\n" + 
					"	C.id_cliente,  \n" + 
					"	nombre_cliente,\n" + 
					"	E.id_estado,\n" + 
					"	nombre_estado,\n" + 
					"	A.id_area, \n" + 
					"	nombre_area " , values, new PostulacionMapper());
		} catch (EmptyResultDataAccessException e) {

			log.info("..:: EXCEPTION CONTROLADA ::.. [NO SE ENCONTRO RESULTADOS EN LA CONSULTA]");
			postulacion = null;

		} catch (Exception e) {
			log.error("..:: ERROR CAPA DAO AL BUSCAR USUARIO POR NONMBRE ::.. [METODO: buscarUsuarioPorCorreo] "
					+ e.getMessage(), e);
			throw new DaoDataAccesException(e);
		}
		return postulacion;
	}

}
