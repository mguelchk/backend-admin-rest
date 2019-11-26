package mx.com.backend.admin.reclutamiento.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.backend.admin.reclutamiento.models.Area;

public class AreaMapper implements RowMapper<Area> {

	@Override
	public Area mapRow(ResultSet rs, int rowNum) throws SQLException {

		Area estado = new Area();

		estado.setIdArea(rs.getLong("id_area"));
		estado.setNombre(rs.getString("nombre"));
		estado.setDescripcion(rs.getString("descripcion"));
		estado.setActivo(rs.getBoolean("activo"));

		return estado;
	}
}
