package edu.moduloalumno.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.moduloalumno.dao.IAlumnoBeneficioDAO;
import edu.moduloalumno.entity.AlumnoBeneficio;
import edu.moduloalumno.entity.AlumnoProgramaBeneficio;
import edu.moduloalumno.entity.Beneficio;
import edu.moduloalumno.service.IAlumnoBeneficioService;

@Service
public class AlumnoBeneficioServiceImpl implements IAlumnoBeneficioService{
	
	@Autowired
	IAlumnoBeneficioDAO alumnobeneficiodao;

	@Override
	public boolean insertBeneficio(Beneficio beneficio) {
		return alumnobeneficiodao.insertBeneficio(beneficio);
	}

	@Override
	public boolean insertAlumnoProgramaBeneficio(AlumnoProgramaBeneficio apbeneficio) {
		return alumnobeneficiodao.insertAlumnoProgramaBeneficio(apbeneficio);
	}

	@Override
	public List<AlumnoBeneficio> getAllAlumnoBeneficio(String codigo) {
		return alumnobeneficiodao.getAllAlumnoBeneficio(codigo);
	}

	@Override
	public Integer getIdBeneficio() {
		return alumnobeneficiodao.getIdBeneficio();
	}
	
	
	

}