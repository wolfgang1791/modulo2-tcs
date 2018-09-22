package edu.moduloalumno.service;

import edu.moduloalumno.entity.AlumnoBeneficio;
import edu.moduloalumno.entity.AlumnoProgramaBeneficio;
import edu.moduloalumno.entity.Beneficio;

public interface IAlumnoBeneficioService {
	
	public AlumnoBeneficio getAllAlumnoBeneficio(String codigo);
	
	public boolean insertBeneficio(Beneficio beneficio);
	
	public boolean insertAlumnoProgramaBeneficio(AlumnoProgramaBeneficio apbeneficio);
	
	public Integer getIdBeneficio();

}
