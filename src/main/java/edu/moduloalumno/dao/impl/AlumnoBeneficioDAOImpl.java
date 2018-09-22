package edu.moduloalumno.dao.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.moduloalumno.dao.IAlumnoBeneficioDAO;
import edu.moduloalumno.entity.AlumnoBeneficio;
import edu.moduloalumno.entity.AlumnoProgramaBeneficio;
import edu.moduloalumno.entity.Beneficio;
import edu.moduloalumno.rowmapper.AlumnoBeneficioRowMapper;




@Transactional
@Repository
public class AlumnoBeneficioDAOImpl implements IAlumnoBeneficioDAO{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;//i wanna be adored
	
	@Override
	public AlumnoBeneficio getAllAlumnoBeneficio(String codigo) {
		String sql = "select abp.cod_alumno,abp.id_programa,abp.id_beneficio,b.autorizacion,b.resolucion,b.condicion,b.fecha,b.beneficio from alumno_programa_beneficio abp,beneficio b where abp.cod_alumno = (?) and abp.id_beneficio = b.id_beneficio";
		RowMapper<AlumnoBeneficio> rowMapper = new AlumnoBeneficioRowMapper();
		AlumnoBeneficio alumnobeneficio = jdbcTemplate.queryForObject(sql, rowMapper, codigo);
		return alumnobeneficio;
	}

	@Override
	public boolean insertBeneficio(Beneficio beneficio) {
		
		logger.info("dao: "+beneficio);
		
		String sql = "INSERT INTO beneficio(autorizacion, resolucion, condicion, fecha,beneficio) values (?, ?, ?, ?,?)";
		Integer ret = jdbcTemplate.update(sql,beneficio.getAutorizacion(),beneficio.getResolucion(), beneficio.getCondicion(),beneficio.getFecha(),beneficio.getBeneficio());
		logger.info("dao: shit"+ret);
		
		if(ret.equals(1)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean insertAlumnoProgramaBeneficio(AlumnoProgramaBeneficio apbeneficio) {
		String sql = "INSERT INTO alumno_programa_beneficio(cod_alumno,id_programa,id_beneficio) values (?, ?, ?)";
		Integer ret = jdbcTemplate.update(sql, apbeneficio.getCod_alumno(),apbeneficio.getId_programa(),apbeneficio.getId_beneficio());
		if(ret.equals(1)) {
			return true;
		}
		else 
		{
			return false;
		}
	}

	@Override
	public Integer getIdBeneficio() {
		
		String sql = "select max(id_beneficio) from beneficio";
		
		String id = jdbcTemplate.queryForObject(sql, String.class);
			
		return Integer.parseInt(id) ;
	}

}