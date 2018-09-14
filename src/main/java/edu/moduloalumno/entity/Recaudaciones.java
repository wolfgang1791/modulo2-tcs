package edu.moduloalumno.entity;

import java.io.Serializable;
import java.util.Date;

public class Recaudaciones implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idRec;
	
	private Integer idAlum;
	
	private String apeNom;
	
	private String concepto;
    
	private String numero;
    
	private String dni;

	private String nombre;
    
    private String moneda;
    
    private double importe;
    
    private Date fecha;

	public Integer getIdRec() {
		return idRec;
	}

	public void setIdRec(Integer idRec) {
		this.idRec = idRec;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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
	
	@Override
	public String toString() {
		return "Recaudaciones [idRec=" + idRec + ", idAlum=" + idAlum + ", apeNom=" + apeNom + ", concepto=" + concepto
				+ ", numero=" + numero + ", dni=" + dni + ", nombre=" + nombre + ", moneda=" + moneda + ", importe="
				+ importe + ", fecha=" + fecha + "]";
	}
    
    
}
