package edu.moduloalumno.entity;

public class AlumnoProgramaBeneficio {
	private String cod_alumno;
	private Integer id_programa;
	private Integer id_beneficio;
	
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