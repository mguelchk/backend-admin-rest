package mx.com.backend.admin.reclutamiento.dao.sp;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import mx.com.backend.admin.reclutamiento.core.exception.SpErrorException;
import mx.com.backend.admin.reclutamiento.models.Postulacion;

public class PostulacionSP extends StoredProcedure {

	public PostulacionSP(JdbcTemplate jdbcTemplate, String storedProcedure) {
		super(jdbcTemplate, storedProcedure);
	}

	public Integer crearPostulacion(Postulacion postulacion) throws SpErrorException {

		Map<String, Object> inputs = new HashMap<>();

		declareParameter(new SqlParameter("paIdVacante", Types.INTEGER));
		declareParameter(new SqlParameter("paIdUsuario", Types.INTEGER));

		declareParameter(new SqlOutParameter("paCodigoError", Types.INTEGER));
		declareParameter(new SqlOutParameter("paMensaje", Types.VARCHAR));
		declareParameter(new SqlOutParameter("paIdPostulacion", Types.INTEGER));

		compile();

		inputs.put("paIdVacante", postulacion.getVacante().getIdVacante().intValue());
		inputs.put("paIdUsuario", postulacion.getUsuario().getIdUsuario().intValue());

		Map<String, Object> rs = super.execute(inputs);

		Integer codigo = (Integer) rs.get("paCodigoError");
		String mensaje = (String) rs.get("paMensaje");
		Integer id = (Integer) rs.get("paIdPostulacion");

		if (codigo != null && codigo == -1) {
			throw new SpErrorException("CodigoError : " + codigo + " - MGS :" + mensaje);
		} else {
			return id;
		}
	}

}
