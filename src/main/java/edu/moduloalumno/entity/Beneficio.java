package edu.moduloalumno.entity;

import java.util.Date;

public class Beneficio {
	
	private Integer id_beneficio;
	private String autorizacion;
	private String resolucion;
	private String condicion;
	private Date fecha;
	
	public Integer getId_beneficio() {
		return id_beneficio;
	}
	public void setId_beneficio(Integer id_beneficio) {
		this.id_beneficio = id_beneficio;
	}
	public String getAutorizacion() {
		return autorizacion;
	}
	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}
	public String getResolucion() {
		return resolucion;
	}
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}
	public String getCondicion() {
		return condicion;
	}
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "Beneficio [id_beneficio=" + id_beneficio + ", autorizacion=" + autorizacion + ", resolucion="
				+ resolucion + ", condicion=" + condicion + ", fecha=" + fecha + "]";
	}
}
