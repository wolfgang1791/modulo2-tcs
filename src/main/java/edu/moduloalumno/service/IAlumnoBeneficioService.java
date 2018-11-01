package edu.moduloalumno.service;

import java.util.List;

import edu.moduloalumno.entity.AlumnoProgramaBeneficio;
import edu.moduloalumno.entity.AlumnoProgramaBeneficioCon;
import edu.moduloalumno.entity.BeneficioReporte;
import edu.moduloalumno.entity.CondicionBeneficio;
import edu.moduloalumno.entity.TipoAplicaBeneficio;
import edu.moduloalumno.entity.TipoBeneficio;

public interface IAlumnoBeneficioService {
	
	public List<AlumnoProgramaBeneficioCon> getAllAlumnoBeneficio(String codigo);
	
	public BeneficioReporte funcionDescuento(String codigo, float descuento);
	
	public boolean insertAlumnoProgramaBeneficio(AlumnoProgramaBeneficio apbeneficio);
	
	public boolean getIdBeneficio(Integer id_apb);
	
	public List<CondicionBeneficio> getAllCondicionB();
	
	public List<TipoBeneficio> getAllTipo();

	public List<TipoAplicaBeneficio> getTipoAplicaBeneficio();

}
