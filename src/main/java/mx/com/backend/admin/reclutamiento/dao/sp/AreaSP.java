package mx.com.backend.admin.reclutamiento.dao.sp;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import mx.com.backend.admin.reclutamiento.core.exception.SpErrorException;
import mx.com.backend.admin.reclutamiento.models.Area;

public class AreaSP extends StoredProcedure {

	public AreaSP(JdbcTemplate jdbcTemplate, String storedProcedure) {
		super(jdbcTemplate, storedProcedure);
	}

	public Integer crearActualizarArea(Area area) throws SpErrorException {

		Map<String, Object> inputs = new HashMap<>();

		declareParameter(new SqlParameter("paIdArea", Types.INTEGER));
		declareParameter(new SqlParameter("paNombre", Types.VARCHAR));
		declareParameter(new SqlParameter("paDescripcion", Types.VARCHAR));

		declareParameter(new SqlOutParameter("paCodigoError", Types.INTEGER));
		declareParameter(new SqlOutParameter("paMensaje", Types.VARCHAR));
		declareParameter(new SqlOutParameter("paIdAreaOut", Types.INTEGER));

		compile();

		inputs.put("paIdArea", area.getIdArea());
		inputs.put("paNombre", area.getNombre());
		inputs.put("paDescripcion", area.getDescripcion());

		Map<String, Object> rs = super.execute(inputs);

		Integer codigo = (Integer) rs.get("paCodigoError");
		String mensaje = (String) rs.get("paMensaje");
		Integer id = (Integer) rs.get("paIdAreaOut");

		if (codigo != null && codigo == -1) {
			throw new SpErrorException("CodigoError : " + codigo + " - MGS :" + mensaje);
		} else {
			return id;
		}
	}

}
