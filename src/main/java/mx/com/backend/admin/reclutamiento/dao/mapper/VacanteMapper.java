package mx.com.backend.admin.reclutamiento.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.backend.admin.reclutamiento.models.Area;
import mx.com.backend.admin.reclutamiento.models.Cliente;
import mx.com.backend.admin.reclutamiento.models.Estado;
import mx.com.backend.admin.reclutamiento.models.Vacante;

public class VacanteMapper implements RowMapper<Vacante> {

	@Override
	public Vacante mapRow(ResultSet rs, int rowNum) throws SQLException {

		Vacante vacante = new Vacante();

		vacante.setIdVacante(rs.getLong("id_vacante"));
		vacante.setNombre(rs.getString("nombre_vacante"));
		vacante.setSueldo(rs.getString("sueldo"));
		vacante.setDescripcionBreve(rs.getString("descripcion_breve"));
		
		Area area = new Area();
		area.setIdArea(rs.getLong("id_area"));
		area.setNombre(rs.getString("nombre_area"));
		vacante.setArea(area);
		
		Estado estado = new Estado();
		estado.setIdEstado(rs.getLong("id_estado"));
		estado.setNombre(rs.getString("nombre_estado"));
		vacante.setEstado(estado);
		
		Cliente cliente = new Cliente();
		cliente.setIdCliente(rs.getLong("id_cliente"));
		cliente.setNombre(rs.getString("nombre_cliente"));
		vacante.setCliente(cliente);
		
		vacante.setDescripcion(rs.getString("descripcion"));
		vacante.setRequisitos(rs.getString("requisitos"));
		vacante.setNumVacante(rs.getInt("num_vacantes"));
		vacante.setFechaContratacion(rs.getDate("fecha_contratacion"));
		vacante.setExperiencia(rs.getString("experiencia"));
		vacante.setNivelEstudios(rs.getString("estudios"));
		vacante.setDisponibilidadViajar(rs.getBoolean("viajar"));
		vacante.setIdioma(rs.getString("idioma"));
		vacante.setRangoEdad(rs.getString("edad"));
		vacante.setTipoLicencia(rs.getString("tipo_licencia"));
		vacante.setDiscapacidad(rs.getString("discapacidad"));
		vacante.setPublicada(rs.getBoolean("publicada"));
		vacante.setVacantePostulada(rs.getBoolean("vacante_postulada"));
		

		return vacante;
	}
}
