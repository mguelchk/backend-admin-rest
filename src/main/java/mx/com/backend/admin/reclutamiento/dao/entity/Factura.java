package mx.com.backend.admin.reclutamiento.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String descripcion;

	private String observacion;

	private Date createAt;

	private Cliente cliente;

	private List<ItemFactura> items;

	public Factura() {
		items = new ArrayList<>();
	}

	public void prePersist() {
		this.createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

	public Double getTotal() {
		Double total = 0.00;
		for (ItemFactura item : items) {
			total += item.getImporte();
		}
		return total;
	}

	
}
