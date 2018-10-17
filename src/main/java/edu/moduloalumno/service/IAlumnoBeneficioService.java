package edu.moduloalumno.service;

import java.util.List;

import edu.moduloalumno.entity.AlumnoProgramaBeneficio;
import edu.moduloalumno.entity.AlumnoProgramaBeneficioCon;
import edu.moduloalumno.entity.CondicionBeneficio;
import edu.moduloalumno.entity.TipoBeneficio;

public interface IAlumnoBeneficioService {
	
	public List<AlumnoProgramaBeneficioCon> getAllAlumnoBeneficio(String codigo);
	
	//public boolean insertBeneficio(Beneficio beneficio);
	
	public boolean insertAlumnoProgramaBeneficio(AlumnoProgramaBeneficio apbeneficio);
	
	public boolean getIdBeneficio(Integer id_apb);
	
	public List<CondicionBeneficio> getAllCondicionB();
	
	public List<TipoBeneficio> getAllTipo();

}
