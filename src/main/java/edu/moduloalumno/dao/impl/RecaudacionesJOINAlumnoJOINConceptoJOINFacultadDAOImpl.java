package edu.moduloalumno.dao.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.moduloalumno.dao.IRecaudacionesJOINAlumnoJOINConceptoJOINFacultadDAO;
import edu.moduloalumno.entity.CodigosporNomApe;

import edu.moduloalumno.entity.RecaudacionesJOINAlumnoJOINConceptoJOINFacultad;
import edu.moduloalumno.rowmapper.CodigosporNomApeRowMapper;
import edu.moduloalumno.rowmapper.RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper;

@Transactional
@Repository
public class RecaudacionesJOINAlumnoJOINConceptoJOINFacultadDAOImpl implements IRecaudacionesJOINAlumnoJOINConceptoJOINFacultadDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getAllRecaudacionesJOINAlumnoJOINConceptoJOINFacultads() {
		String sql = "select r.id_rec, r.id_alum, a.ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, r.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c where (r.id_alum = a.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new
		// BeanPropertyRowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>(RecaudacionesJOINAlumnoJOINConceptoJOINFacultad.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadIdByNombresApellidosRestringido(String nombres, String apellidos) {
		String sql = "select r.id_rec, r.id_alum, a.ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, r.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c where (r.id_alum = a.id_alum) and (a.id_facultad = f.id_facultad) and ((a.ape_nom like '%'|| ? ||'%') and (a.ape_nom like '%'|| ? ||'%')) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<AlumnoPrograma> rowMapper = new
		// BeanPropertyRowMapper<AlumnoPrograma>(AlumnoPrograma.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, nombres, apellidos);
	}
	
	@Override
	public RecaudacionesJOINAlumnoJOINConceptoJOINFacultad getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadById(int idRecaudaciones) {
		String sql = "select r.id_rec, r.id_alum, a.ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, r.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c where (r.id_rec = ? ) and (r.id_alum = a.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) order by c.concepto, r.fecha";
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new BeanPropertyRowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>(RecaudacionesJOINAlumnoJOINConceptoJOINFacultad.class);
		RecaudacionesJOINAlumnoJOINConceptoJOINFacultad recaudaciones = jdbcTemplate.queryForObject(sql, rowMapper, idRecaudaciones);
		return recaudaciones;
	}

	@Override
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByStartDateBetween(Date fechaInicial, Date fechaFinal) {
		String sql = "select r.id_rec, r.id_alum, a.ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, r.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c where (r.fecha between ? and ?) and (r.id_alum = a.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, fechaInicial, fechaFinal);
	}

	@Override
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApeStartDateBetween(String nomApe, Date fechaInicial,
			Date fechaFinal) {
		String sql = "select r.id_rec, r.id_alum, ap.nom_alumno || ' ' || ap.ape_paterno || ' ' || ap.ape_materno as ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, r.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c, alumno_programa ap, alumno_alumno_programa aap where to_tsquery( ? ) @@ to_tsvector(coalesce(ap.nom_alumno,'') || ' ' ||coalesce(ap.ape_paterno,'') || ' ' ||coalesce(ap.ape_materno,'')) and (ap.id_programa = aap.id_programa) and (ap.cod_alumno = aap.cod_alumno) and (aap.id_alum = a.id_alum) and (a.id_alum = r.id_alum) and ((r.fecha between ? and ?) or r.fecha = null) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, nomApe, fechaInicial, fechaFinal);
	}

	@Override
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApe(String nomApe) {
		String sql = "select r.id_rec, r.id_alum, ap.nom_alumno || ' ' || ap.ape_paterno || ' ' || ap.ape_materno as ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, ap.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c, alumno_programa ap, alumno_alumno_programa aap where to_tsquery( ? ) @@ to_tsvector(coalesce(ap.nom_alumno,'') || ' ' ||coalesce(ap.ape_paterno,'') || ' ' ||coalesce(ap.ape_materno,'')) and (ap.id_programa = aap.id_programa) and (ap.cod_alumno = aap.cod_alumno) and (aap.id_alum = a.id_alum) and (a.id_alum = r.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, nomApe);
	}
        
        @Override
/**/	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByCodigo(String codigo) {
		String sql = "select r.id_rec, r.id_alum, ap.nom_alumno || ' ' || ap.ape_paterno || ' ' || ap.ape_materno as ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, r.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c, alumno_programa ap, alumno_alumno_programa aap where to_tsquery( ? ) @@ to_tsvector(coalesce(ap.cod_alumno,'') || ' ') and (ap.id_programa = aap.id_programa) and (ap.cod_alumno = aap.cod_alumno) and (aap.id_alum = a.id_alum) and (a.id_alum = r.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, codigo);
	}

/*new*/@Override
	public List<CodigosporNomApe> getCodigoByNombre(String nomApe) {
		String sql = "select ap.cod_alumno, ap.nom_alumno || ' ' || ap.ape_paterno || ' ' || ap.ape_materno as nombre_alumno, p.nom_programa as nombre_programa from alumno_programa as ap,programa as p where to_tsquery( ? ) @@ to_tsvector(coalesce(ap.nom_alumno,'') || ' ' ||coalesce(ap.ape_paterno,'') || ' ' ||coalesce(ap.ape_materno,'')) and (ap.id_programa = p.id_programa)";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<CodigosporNomApe> rowMapper = new CodigosporNomApeRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, nomApe);
	}        


	@Override
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApeConcepto(String concepto, String nomApe) {
		String sql = "select r.id_rec, r.id_alum, ap.nom_alumno || ' ' || ap.ape_paterno || ' ' || ap.ape_materno as ape_nom,c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, r.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c, alumno_programa ap, alumno_alumno_programa aap where (c.concepto = ?) and to_tsquery( ? ) @@ to_tsvector(coalesce(ap.cod_alumno,'')) and (ap.id_programa = aap.id_programa) and (ap.cod_alumno = aap.cod_alumno) and (aap.id_alum = a.id_alum) and (a.id_alum = r.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, concepto, nomApe);
	}

	@Override
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApeRecibo(String recibo, String nomApe) {
		String sql = "select r.id_rec, r.id_alum, ap.nom_alumno || ' ' || ap.ape_paterno || ' ' || ap.ape_materno as ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, r.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c, alumno_programa ap, alumno_alumno_programa aap where (r.numero = ?) and to_tsquery( ? ) @@ to_tsvector(coalesce(ap.nom_alumno,'') || ' ' ||coalesce(ap.ape_paterno,'') || ' ' ||coalesce(ap.ape_materno,'')) and (ap.id_programa = aap.id_programa) and (ap.cod_alumno = aap.cod_alumno) and (aap.id_alum = a.id_alum) and (a.id_alum = r.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, recibo, nomApe);
	}

	@Override
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByPosgrado() {
		String sql = "select r.id_rec, r.id_alum, a.ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, r.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c where (r.id_alum = a.id_alum) and (r.id_alum = a.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNombresApellidosStartDateBetween(String nombres, String apellidos,
			Date fechaInicial, Date fechaFinal) {
		String sql = "select r.id_rec, r.id_alum, a.ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, r.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c where (r.id_alum = a.id_alum) and (a.id_facultad = f.id_facultad) and ((a.ape_nom like '%'|| ? ||'%') and (a.ape_nom like '%'|| ? ||'%')) and ((r.fecha between ? and ? ) or r.fecha = null) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, nombres, apellidos, fechaInicial, fechaFinal);
	}

	@Override
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNombresApellidos(String nombres, String apellidos) {
		String sql = "select r.id_rec, r.id_alum, a.ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, r.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c where (r.id_alum = a.id_alum) and (a.id_facultad = f.id_facultad) and ((a.ape_nom like '%'|| ? ||'%') and (a.ape_nom like '%'|| ? ||'%')) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, nombres, apellidos);
	}

	@Override
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNombresApellidosConcepto(String concepto, String nombres,
			String apellidos) {
		String sql = "select r.id_rec, r.id_alum, a.ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, r.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c where (r.id_alum = a.id_alum) and (a.id_facultad = f.id_facultad) and (c.concepto = ? ) and ((a.ape_nom like '%'|| ? ||'%') and (a.ape_nom like '%'|| ? ||'%')) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, concepto, nombres, apellidos);
	}

	@Override
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNombresApellidosRecibo(String recibo, String nombres,
			String apellidos) {
		String sql = "select r.id_rec, r.id_alum, a.ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, r.id_programa, r.cod_alumno, r.observacion from recaudaciones r, alumno a, facultad f, concepto c where (r.id_alum = a.id_alum) and (a.id_facultad = f.id_facultad) and (r.numero = ? ) and ((a.ape_nom like '%'|| ? ||'%') and (a.ape_nom like '%'|| ? ||'%')) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, recibo, nombres, apellidos);
	}

	@Override
	public int updateRecaudacionesJOINAlumnoJOINConceptoJOINFacultad(RecaudacionesJOINAlumnoJOINConceptoJOINFacultad recaudaciones) {
		String sql = "UPDATE recaudaciones SET id_programa = ?, cod_alumno = ? WHERE id_rec = ?";
		return jdbcTemplate.update(sql, recaudaciones.getIdPrograma(), recaudaciones.getCodAlumno(), recaudaciones.getIdRec());
	}

	@Override
	public void deleteRecaudacionesJOINAlumnoJOINConceptoJOINFacultad(int idRecaudaciones) {
		String sql = "DELETE FROM recaudaciones WHERE id_rec = ?";
		jdbcTemplate.update(sql, idRecaudaciones);
	}
	
}