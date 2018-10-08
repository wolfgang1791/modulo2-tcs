package edu.moduloalumno.model;

public class DataActualizar {
	
	private String idRec;
	private String fecha;
	private String id_concepto;
	private String concepto;
	private String recibo;
	private String ciclo;
	
	public String getId_concepto() {
		return id_concepto;
	}
	public void setId_concepto(String id_concepto) {
		this.id_concepto = id_concepto;
	}
	
	public String getIdRec() {
		return idRec;
	}
	public void setIdRec(String idRec) {
		this.idRec = idRec;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getRecibo() {
		return recibo;
	}
	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	
	@Override
	public String toString() {
		return "DataActualizar [idRec=" + idRec + ", fecha=" + fecha + ", concepto=" + concepto + ", recibo=" + recibo
				+ ", ciclo=" + ciclo + "]";
	}
	
	
	
	
}
