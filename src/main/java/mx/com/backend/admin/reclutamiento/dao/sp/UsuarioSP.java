package mx.com.backend.admin.reclutamiento.dao.sp;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import mx.com.backend.admin.reclutamiento.models.Usuario;

public class UsuarioSP extends StoredProcedure {


	public UsuarioSP(JdbcTemplate jdbcTemplate, String storedProcedure) {
		super(jdbcTemplate, storedProcedure);
	}

	public int agregarUsuario(Usuario usuario) {

		Map<String, Object> inputs = new HashMap<>();

		declareParameter(new SqlParameter("paUsuario", Types.VARCHAR));
		declareParameter(new SqlParameter("paContrasenia", Types.VARCHAR));
		declareParameter(new SqlParameter("paCorreo", Types.VARCHAR));
		declareParameter(new SqlParameter("paTelefono", Types.VARCHAR));

		declareParameter(new SqlOutParameter("paCodigoError", Types.INTEGER));
		declareParameter(new SqlOutParameter("paMensaje", Types.VARCHAR));
		declareParameter(new SqlOutParameter("paIdUsuario", Types.INTEGER));

		compile();

		inputs.put("paUsuario", usuario.getNombreUsuario());
		inputs.put("paContrasenia", usuario.getPassword());
		inputs.put("paCorreo", usuario.getEmail());
		inputs.put("paTelefono", usuario.getTelefono());

		Map<String, Object> rs = super.execute(inputs);

		Integer codigo = (Integer) rs.get("paCodigoError");
		String mensaje = (String) rs.get("paMensaje");
		Integer id = (Integer) rs.get("paIdUsuario");
		return id;
//		if (codigo != -1)
//			return codigo;
//		else
//			return (int) rs.get("paIdUsuario");
	}

}
