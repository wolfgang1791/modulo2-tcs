package edu.moduloalumno.entity;

import java.util.Date;

public class RecaudacionesJOINAlumnoJOINConceptoJOINFacultad {
	
	private Integer idRec;
	
	private Integer idAlum;
	
	private String apeNom;
	
	private Integer ciclo;
	
	private String concepto;
	
	private Integer idconcepto;
    
	private String numero;
    
	private String dni;

	private String nombre;
    
    private String moneda;
    
    private double importe;
    
    private Date fecha;
    
    private Integer idPrograma;
    
    private String nomPrograma;
    
    private String codAlumno;
    
    private String estado;
    
    public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	private String observacion;
    

	public Integer getIdRec() {
		return idRec;
	}

	public void setIdRec(Integer idRec) {
		this.idRec = idRec;
	}

	public Integer getCiclo() {
		return ciclo;
	}

	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	public Integer getIdAlum() {
		return idAlum;
	}

	public void setIdAlum(Integer idAlum) {
		this.idAlum = idAlum;
	}

	public String getApeNom() {
		return apeNom;
	}

	public void setApeNom(String apeNom) {
		this.apeNom = apeNom;
	}
	
	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	
	public String getNomPrograma() {
		return nomPrograma;
	}

	public void setNomPrograma(String nomPrograma) {
		this.nomPrograma = nomPrograma;
	}
	
	public String getCodAlumno() {
		return codAlumno;
	}

	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public Integer getIdconcepto() {
		return idconcepto;
	}

	public void setIdconcepto(Integer idconcepto) {
		this.idconcepto = idconcepto;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRec == null) ? 0 : idRec.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecaudacionesJOINAlumnoJOINConceptoJOINFacultad other = (RecaudacionesJOINAlumnoJOINConceptoJOINFacultad) obj;
		if (idRec == null) {
			if (other.idRec != null)
				return false;
		} else if (!idRec.equals(other.idRec))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RecaudacionesJOINAlumnoJOINConceptoJOINFacultad [idRec=" + idRec + ", idAlum=" + idAlum + ", apeNom="
				+ apeNom + ", concepto=" + concepto + ", idconcepto=" + idconcepto + ", numero=" + numero + ", dni="
				+ dni + ", nombre=" + nombre + ", moneda=" + moneda + ", importe=" + importe + ", fecha=" + fecha
				+ ", idPrograma=" + idPrograma + ", nomPrograma=" + nomPrograma + ", codAlumno=" + codAlumno
				+ ", observacion=" + observacion + "]";
	}

	
}
