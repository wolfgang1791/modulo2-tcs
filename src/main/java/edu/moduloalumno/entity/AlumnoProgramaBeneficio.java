package edu.moduloalumno.entity;

import java.util.Date;

public class AlumnoProgramaBeneficio {
	private String cod_alumno;
	private Integer id_programa;
	private Integer id_beneficio;
	private String beneficio_otorgado;
	private String observacion;
	private Integer id_bcondicion;
	private String autorizacion;
	private Date fecha;
	
	private boolean toQuery;
	
	
	public boolean getToQuery() {
		return toQuery;
	}
	public void setToQuery(boolean toQuery) {
		this.toQuery = toQuery;
	}
	public Integer getId_bcondicion() {
		return id_bcondicion;
	}
	public void setId_bcondicion(Integer id_bcondicion) {
		this.id_bcondicion = id_bcondicion;
	}
	public String getAutorizacion() {
		return autorizacion;
	}
	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getBeneficio_otorgado() {
		return beneficio_otorgado;
	}
	public void setBeneficio_otorgado(String beneficio_otorgado) {
		this.beneficio_otorgado = beneficio_otorgado;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getCod_alumno() {
		return cod_alumno;
	}
	public void setCod_alumno(String cod_alumno) {
		this.cod_alumno = cod_alumno;
	}
	public Integer getId_programa() {
		return id_programa;
	}
	public void setId_programa(Integer id_programa) {
		this.id_programa = id_programa;
	}
	public Integer getId_beneficio() {
		return id_beneficio;
	}
	public void setId_beneficio(Integer id_beneficio) {
		this.id_beneficio = id_beneficio;
	}
	@Override
	public String toString() {
		return "AlumnoProgramaBeneficio [cod_alumno=" + cod_alumno + ", id_programa=" + id_programa + ", id_beneficio="
				+ id_beneficio + "]";
	}
	
	
}