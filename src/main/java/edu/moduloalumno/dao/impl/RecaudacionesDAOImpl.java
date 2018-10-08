package edu.moduloalumno.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.moduloalumno.dao.IRecaudacionesDAO;
import edu.moduloalumno.entity.Recaudaciones;
import edu.moduloalumno.rowmapper.RecaudacionesRowMapper;

@Transactional
@Repository
public class RecaudacionesDAOImpl implements IRecaudacionesDAO {
	@Override
	public void addRecaudaciones(Recaudaciones reacaudaciones) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateRecaudaciones(Recaudaciones reacaudaciones) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteRecaudaciones(int idRec) {
		// TODO Auto-generated method stub
		
	}



	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Recaudaciones> getAllRecaudaciones() {
		String sql = "SELECT id_rec, moneda, numero, importe, carnet, autoseguro, ave, devol_tran, observacion, fecha, validado, id_alum, id_concepto, id_registro, cod_alumno, id_programa, id_ubicacion, id_tipo FROM recaudaciones";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<Recaudaciones> rowMapper = new RecaudacionesRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public Recaudaciones getRecaudacionesById(int idRecaudaciones) {
		String sql = "SELECT id_rec, moneda, numero, importe, carnet, autoseguro, ave, devol_tran, observacion, fecha, validado, id_alum, id_concepto, id_registro, cod_alumno, id_programa, id_ubicacion, id_tipo FROM recaudaciones WHERE id_rec = ?";
		RowMapper<Recaudaciones> rowMapper = new BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		Recaudaciones recaudaciones = jdbcTemplate.queryForObject(sql, rowMapper, idRecaudaciones);
		return recaudaciones;
	}

	@Override
	public List<Recaudaciones> getRecaudacionesByStartDateBetween(Date fechaInicial, Date fechaFinal) {
		String sql = "select r.id_rec, r.moneda, r.numero, r.importe, r.carnet, r.autoseguro, r.ave, r.devol_tran, r.observacion, r.fecha, r.validado, r.id_alum, r.id_concepto, r.id_registro,  r.cod_alumno, r.id_programa, r.id_ubicacion, r.id_tipo from Recaudaciones r where (r.fecha between ? and ?) and (r.id_concepto = some ( select tc.id_concepto from Concepto tc where tc.id_clase_pagos = 2)) order by r.id_concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<Recaudaciones> rowMapper = new RecaudacionesRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, fechaInicial, fechaFinal);
	}

	@Override
	public List<Recaudaciones> getRecaudacionesByNomApeStartDateBetween(String nomApe, Date fechaInicial,
			Date fechaFinal) {
		String sql = "select r.id_rec, r.moneda, r.numero, r.importe, r.carnet, r.autoseguro, r.ave, r.devol_tran, r.observacion, r.fecha, r.validado, r.id_alum, r.id_concepto, r.id_registro,  r.cod_alumno, r.id_programa, r.id_ubicacion, r.id_tipo from Recaudaciones r, alumno a where ((r.fecha between ? and ?) or r.fecha = null) and (r.id_alum = a.id_alum) and (a.ape_nom = ?) and (r.id_concepto = some ( select tc.id_concepto from Concepto tc where tc.id_clase_pagos = 2)) order by r.id_concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<Recaudaciones> rowMapper = new RecaudacionesRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, fechaFinal, fechaInicial, nomApe);
	}

	@Override
	public List<Recaudaciones> getRecaudacionesByNomApe(String nomApe) {
		String sql = "select r.id_rec, r.moneda, r.numero, r.importe, r.carnet, r.autoseguro, r.ave, r.devol_tran, r.observacion, r.fecha, r.validado, r.id_alum, r.id_concepto, r.id_registro,  r.cod_alumno, r.id_programa, r.id_ubicacion, r.id_tipo from Recaudaciones r, alumno a where (r.id_alum = a.id_alum) and (a.ape_nom = ?) and (r.id_concepto = some ( select tc.id_concepto from Concepto tc where tc.id_clase_pagos = 2)) order by r.id_concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<Recaudaciones> rowMapper = new RecaudacionesRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, nomApe);
	}

	@Override
	public List<Recaudaciones> getRecaudacionesByNomApeConcepto(String concepto, String nomApe) {
		String sql = "select r.id_rec, r.moneda, r.numero, r.importe, r.carnet, r.autoseguro, r.ave, r.devol_tran, r.observacion, r.fecha, r.validado, r.id_alum, r.id_concepto, r.id_registro,  r.cod_alumno, r.id_programa, r.id_ubicacion, r.id_tipo from Recaudaciones r, concepto co, alumno a where (r.id_concepto = co.id_concepto) and (co.concepto = ?) and (r.id_alum = a.id_alum) and (a.ape_nom = ?) order by r.id_concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<Recaudaciones> rowMapper = new RecaudacionesRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, concepto, nomApe);
	}

	@Override
	public List<Recaudaciones> getRecaudacionesByNomApeRecibo(String recibo, String nomApe) {
		String sql = "select r.id_rec, r.moneda, r.numero, r.importe, r.carnet, r.autoseguro, r.ave, r.devol_tran, r.observacion, r.fecha, r.validado, r.id_alum, r.id_concepto, r.id_registro,  r.cod_alumno, r.id_programa, r.id_ubicacion, r.id_tipo from Recaudaciones r, alumno a where (r.numero = ?) and (r.id_alum = a.id_alum) and (a.ape_nom = ?) order by r.id_concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<Recaudaciones> rowMapper = new RecaudacionesRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, recibo, nomApe);
	}

	@Override
	public List<Recaudaciones> getRecaudacionesByPosgrado() {
		String sql = "select r.id_rec, r.moneda, r.numero, r.importe, r.carnet, r.autoseguro, r.ave, r.devol_tran, r.observacion, r.fecha, r.validado, r.id_alum, r.id_concepto, r.id_registro,  r.cod_alumno, r.id_programa, r.id_ubicacion, r.id_tipo from Recaudaciones r  where (r.id_concepto = some ( select tc.id_concepto from Concepto tc where tc.id_clase_pagos = 2))  order by r.id_concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<Recaudaciones> rowMapper = new RecaudacionesRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Recaudaciones> getRecaudacionesByNombresApellidosStartDateBetween(String nombres, String apellidos,
			Date fechaInicial, Date fechaFinal) {
		String sql = "select r.id_rec, r.moneda, r.numero, r.importe, r.carnet, r.autoseguro, r.ave, r.devol_tran, r.observacion, r.fecha, r.validado, r.id_alum, r.id_concepto, r.id_registro,  r.cod_alumno, r.id_programa, r.id_ubicacion, r.id_tipo from Recaudaciones r, alumno a where (r.id_alum = a.id_alum) and ((a.ape_nom like '%'|| ? ||'%') and (a.ape_nom like '%'|| ? ||'%')) and ((r.fecha between ? and ? ) or r.fecha = null) and (r.id_concepto = some ( select tc.id_concepto  from Concepto tc where tc.id_clase_pagos = 2)) order by r.id_concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<Recaudaciones> rowMapper = new RecaudacionesRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, nombres, apellidos, fechaInicial, fechaFinal);
	}

	@Override
	public List<Recaudaciones> getRecaudacionesByNombresApellidos(String nombres, String apellidos) {
		String sql = "select r.id_rec, r.moneda, r.numero, r.importe, r.carnet, r.autoseguro, r.ave, r.devol_tran, r.observacion, r.fecha, r.validado, r.id_alum, r.id_concepto, r.id_registro,  r.cod_alumno, r.id_programa, r.id_ubicacion, r.id_tipo from Recaudaciones r, alumno a where (a.id_alum = r.id_alum ) and ((a.ape_nom like '%'|| ? ||'%') and (a.ape_nom like '%'||?||'%')) and (r.id_concepto = some ( select tc.id_concepto from Concepto tc where tc.id_clase_pagos = 2)) order by r.id_concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<Recaudaciones> rowMapper = new RecaudacionesRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, nombres, apellidos);
	}

	@Override
	public List<Recaudaciones> getRecaudacionesByNombresApellidosConcepto(String concepto, String nombres,
			String apellidos) {
		String sql = "select r.id_rec, r.moneda, r.numero, r.importe, r.carnet, r.autoseguro, r.ave, r.devol_tran, r.observacion, r.fecha, r.validado, r.id_alum, r.id_concepto, r.id_registro, r.cod_alumno, r.id_programa, r.id_ubicacion, r.id_tipo from Recaudaciones r, alumno a, concepto co where (r.id_concepto = co.id_concepto) and (co.concepto = ?) and (r.id_alum = a.id_alum) and ((a.ape_nom like '%'|| ? ||'%') and (a.ape_nom like '%'|| ? ||'%')) order by r.id_concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<Recaudaciones> rowMapper = new RecaudacionesRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, concepto, nombres, apellidos);
	}

	@Override
	public List<Recaudaciones> getRecaudacionesByNombresApellidosRecibo(String recibo, String nombres,
			String apellidos) {
		String sql = "select r.id_rec, r.moneda, r.numero, r.importe, r.carnet, r.autoseguro, r.ave, r.devol_tran, r.observacion, r.fecha, r.validado, r.id_alum, r.id_concepto, r.id_registro, r.cod_alumno, r.id_ubicacion, r.id_programa, r.id_tipo from Recaudaciones r, alumno a where (r.numero = ?) and (r.id_alum = a.id_alum ) and ((a.ape_nom like '%'|| ? ||'%') and (a.ape_nom like '%'|| ? ||'%')) order by r.id_concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<Recaudaciones> rowMapper = new RecaudacionesRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, recibo, nombres, apellidos);
	}
	
	

	@Override
	public List<Recaudaciones> getRecaudacionReci(String recibo) {
		String sql = "select r.id_rec, r.id_alum, a.ape_nom, c.concepto,a.dni, r.numero, f.nombre, r.moneda,r.importe, r.fecha from recaudaciones r, alumno a, concepto c, facultad f where  (r.numero = (?)) and (r.id_alum = a.id_alum) and (r.id_concepto = c.id_concepto) and (a.id_facultad = f.id_facultad) "; 
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<Recaudaciones> rowMapper = new RecaudacionesRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, recibo);
	}

	//@Override
	/*public void addRecaudaciones(Recaudaciones recaudaciones) {
		// Add recaudaciones
		String sql = "INSERT INTO recaudaciones (id_rec, moneda, numero, importe, carnet, autoseguro, ave, devol_tran, observacion, fecha, validado, id_alum, id_concepto, id_registro, cod_alumno, id_ubicacion, id_programa, id_tipo) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, recaudaciones.getIdRec(), recaudaciones.getMoneda(), recaudaciones.getNumero(),
				recaudaciones.getImporte(), recaudaciones.getCarnet(), recaudaciones.getAutoseguro(),
				recaudaciones.getAve(), recaudaciones.getDevolTran(), recaudaciones.getObservacion(),
				recaudaciones.getFecha(), recaudaciones.getValidado(), recaudaciones.getIdAlum(),
				recaudaciones.getIdConcepto(), recaudaciones.getIdRegistro(), recaudaciones.getIdPrograma(),
				recaudaciones.getCodAlumno(), recaudaciones.getIdUbicacion(), recaudaciones.getIdTipo());

		// Fetch recaudaciones id
		sql = "SELECT id_rec FROM recaudaciones WHERE id_rec = ?";
		int idRecaudaciones = jdbcTemplate.queryForObject(sql, Integer.class, recaudaciones.getIdRec());

		// Set recaudaciones id
		recaudaciones.setIdRec(idRecaudaciones);
	}

	@Override
	public int updateRecaudaciones(Recaudaciones recaudaciones) {
		String sql = "UPDATE recaudaciones SET id_programa = ?, cod_alumno = ? WHERE id_rec = ?";
		return jdbcTemplate.update(sql, recaudaciones.getIdPrograma(), recaudaciones.getCodAlumno(), recaudaciones.getIdRec());
	}

	@Override
	public void deleteRecaudaciones(int idRecaudaciones) {
		String sql = "DELETE FROM recaudaciones WHERE id_rec = ?";
		jdbcTemplate.update(sql, idRecaudaciones);
	}
	*/
}