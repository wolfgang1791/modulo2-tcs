package edu.moduloalumno.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.moduloalumno.dao.IAlumnoBeneficioDAO;
import edu.moduloalumno.entity.AlumnoProgramaBeneficio;
import edu.moduloalumno.entity.AlumnoProgramaBeneficioCon;
import edu.moduloalumno.entity.CondicionBeneficio;
import edu.moduloalumno.entity.TipoBeneficio;
import edu.moduloalumno.service.IAlumnoBeneficioService;

@Service
public class AlumnoBeneficioServiceImpl implements IAlumnoBeneficioService{
	
	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IAlumnoBeneficioDAO alumnobeneficiodao;

	@Override
	public boolean insertAlumnoProgramaBeneficio(AlumnoProgramaBeneficio apbeneficio) {
		return alumnobeneficiodao.insertAlumnoProgramaBeneficio(apbeneficio);
	}

	@Override
	public AlumnoProgramaBeneficioCon getAllAlumnoBeneficio(String codigo) {
		return alumnobeneficiodao.getAllAlumnoBeneficio(codigo);
	}

	@Override
	public boolean getIdBeneficio(String cod) {
		return alumnobeneficiodao.getIdBeneficio(cod);
	}

	@Override
	public List<CondicionBeneficio> getAllCondicionB() {
		return alumnobeneficiodao.getAllCondicionB();
	}

	@Override
	public List<TipoBeneficio> getAllTipo() {
		return alumnobeneficiodao.getAllTipo();
	}
	
	
	

}