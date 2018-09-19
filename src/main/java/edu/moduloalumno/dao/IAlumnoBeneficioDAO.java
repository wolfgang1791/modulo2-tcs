package edu.moduloalumno.dao;

import java.util.List;

import edu.moduloalumno.entity.AlumnoBeneficio;
import edu.moduloalumno.entity.AlumnoProgramaBeneficio;
import edu.moduloalumno.entity.Beneficio;

public interface IAlumnoBeneficioDAO {
	public List<AlumnoBeneficio> getAllAlumnoBeneficio(String codigo);
	
	public boolean insertBeneficio(Beneficio beneficio);
	
	public boolean insertAlumnoProgramaBeneficio(AlumnoProgramaBeneficio apbeneficio);
}

