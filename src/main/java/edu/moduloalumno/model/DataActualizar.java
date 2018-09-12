package edu.moduloalumno.model;

import java.util.Arrays;

public class DataActualizar {
	
	private String[] idRec;
	private String[] fecha;
	private String[] obs;
	
	public String[] getObs() {
		return obs;
	}

	public void setObs(String[] obs) {
		this.obs = obs;
	}

	public String[] getIdRec() {
		return idRec;
	}

	public void setIdRec(String[] idRec) {
		this.idRec = idRec;
	}

	public String[] getFecha() {
		return fecha;
	}
	
	public void setFecha(String[] fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "DataActualizar [idRec=" + Arrays.toString(idRec) + ", fecha=" + Arrays.toString(fecha) + ", obs="
				+ Arrays.toString(obs) + "]";
	}
	
}
