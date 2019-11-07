package mx.com.backend.admin.reclutamiento.dao;

import org.springframework.data.repository.CrudRepository;

import mx.com.backend.admin.reclutamiento.dao.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{

}
