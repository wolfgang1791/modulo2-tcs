package edu.moduloalumno.entity;


import java.util.Date;

public class AlumnoBeneficio {
	
	private String cod_alumno;
	private Integer id_programa;
	private Integer id_beneficio;
	private String autorizacion;
	private String resolucion;
	private String condicion;
	private Integer beneficio;
	
	
	public Integer getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(Integer beneficio) {
		this.beneficio = beneficio;
	}

	private Date fecha;
	
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
		return "AlumnoBeneficio [cod_alumno=" + cod_alumno + ", id_programa=" + id_programa + ", id_beneficio="
				+ id_beneficio + ", autorizacion=" + autorizacion + ", resolucion=" + resolucion + ", condicion="
				+ condicion + ", fecha=" + fecha + "]";
	}
	
	
}
