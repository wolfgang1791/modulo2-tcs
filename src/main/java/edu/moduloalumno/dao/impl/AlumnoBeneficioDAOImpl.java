package edu.moduloalumno.dao.impl;
import java.util.List;

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
	
	@Autowired
	private JdbcTemplate jdbcTemplate;//i wanna be adored
	
	@Override
	public List<AlumnoBeneficio> getAllAlumnoBeneficio(String codigo) {
		String sql = "select abp.cod_alumno,abp.id_programa,abp.id_beneficio,b.autorizacion,b.resolucion,b.condicion,b.fecha from alumno_programa_beneficio abp,beneficio b where abp.cod_alumno = (?) and abp.id_beneficio = b.id_beneficio;";
		RowMapper<AlumnoBeneficio> rowMapper = new AlumnoBeneficioRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, codigo);
	}

	@Override
	public boolean insertBeneficio(Beneficio beneficio) {
		String sql = "INSERT INTO beneficio(id_beneficio, autorizacion, resolucion, condicion, fecha) values (?, ?, ?, ?, ?)";
		Integer ret = jdbcTemplate.update(sql, beneficio.getId_beneficio(),beneficio.getAutorizacion(),beneficio.getResolucion(), beneficio.getCondicion(),beneficio.getFecha());
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
		else {
			return false;
		}
	}

}