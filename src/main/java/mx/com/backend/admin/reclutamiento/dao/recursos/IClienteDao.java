package mx.com.backend.admin.reclutamiento.dao.recursos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.com.backend.admin.reclutamiento.dao.entity.Cliente;
import mx.com.backend.admin.reclutamiento.dao.entity.Region;

public interface IClienteDao extends JpaRepository<Cliente, Long>{

	@Query("from Region")
	public List<Region> findAllRegiones();
}
