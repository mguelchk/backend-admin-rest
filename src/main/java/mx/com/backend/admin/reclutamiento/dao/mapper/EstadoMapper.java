package mx.com.backend.admin.reclutamiento.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.backend.admin.reclutamiento.models.Estado;

public class EstadoMapper implements RowMapper<Estado> {

	@Override
	public Estado mapRow(ResultSet rs, int rowNum) throws SQLException {

		Estado estado = new Estado();

		estado.setIdEstado(rs.getLong("id_estado"));
		estado.setNombre(rs.getString("nombre"));
		estado.setCodigo(rs.getString("codigo"));

		return estado;
	}
}
