package mx.com.backend.admin.reclutamiento.dao.sp;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import mx.com.backend.admin.reclutamiento.core.exception.SpErrorException;
import mx.com.backend.admin.reclutamiento.models.Vacante;

public class VacanteSP extends StoredProcedure {

	public VacanteSP(JdbcTemplate jdbcTemplate, String storedProcedure) {
		super(jdbcTemplate, storedProcedure);
	}

	public Integer crearVacante(Vacante vacante) throws SpErrorException {

		Map<String, Object> inputs = new HashMap<>();

		declareParameter(new SqlParameter("paUsuario", Types.VARCHAR));
		declareParameter(new SqlParameter("paContrasenia", Types.VARCHAR));
		declareParameter(new SqlParameter("paCorreo", Types.VARCHAR));
		declareParameter(new SqlParameter("paTelefono", Types.VARCHAR));

		declareParameter(new SqlOutParameter("paCodigoError", Types.INTEGER));
		declareParameter(new SqlOutParameter("paMensaje", Types.VARCHAR));
		declareParameter(new SqlOutParameter("paIdUsuario", Types.INTEGER));

		compile();

//		inputs.put("paUsuario", usuario.getNombreUsuario());
//		inputs.put("paContrasenia", usuario.getPassword());
//		inputs.put("paCorreo", usuario.getEmail());
//		inputs.put("paTelefono", usuario.getTelefono());

		Map<String, Object> rs = super.execute(inputs);

		Integer codigo = (Integer) rs.get("paCodigoError");
		String mensaje = (String) rs.get("paMensaje");
		Integer id = (Integer) rs.get("paIdUsuario");

		if (codigo != null && codigo == -1) {
			throw new SpErrorException("CodigoError : " + codigo + " - MGS :" + mensaje);
		} else {
			return id;
		}
	}

}
