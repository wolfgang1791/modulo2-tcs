package edu.moduloalumno.dao.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override //aqui
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getAllRecaudacionesJOINAlumnoJOINConceptoJOINFacultads() {
		String sql = "select r.id_rec, r.id_alum, rc.estado , a.ape_nom,r.ciclo ,c.concepto,c.id_concepto,a.dni, r.numero, f.nombre, r.moneda, r.importe, r.fecha,p.nom_programa, p.id_programa, r.cod_alumno, r.observacion from recaudaciones r, registro_carga rc, alumno a, facultad f, concepto c, programa p,alumno_programa ap, alumno_alumno_programa aap where (r.id_alum = a.id_alum) and (rc.id_registro = r.id_registro) and (ap.id_programa = aap.id_programa) and (ap.cod_alumno = aap.cod_alumno) and (aap.id_alum = a.id_alum) and (a.id_alum = r.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) and (ap.id_programa = p.id_programa) order by c.concepto, r.fecha";
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
		logger.info("Facultadupdate id"+ idRecaudaciones);
		
		String sql = "select r.id_rec, r.id_alum, rc.estado, a.ape_nom, c.concepto,r.ciclo,c.id_concepto,a.dni, r.numero, f.nombre, r.moneda, r.importe, r.fecha,p.nom_programa ,p.id_programa, r.cod_alumno, r.observacion from recaudaciones r, registro_carga rc, alumno a, facultad f, concepto c,programa p,alumno_programa ap,alumno_alumno_programa aap where (r.id_rec = ? ) and (rc.id_registro = r.id_registro) and (r.id_alum = a.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and  (ap.cod_alumno = aap.cod_alumno) and (aap.id_alum = a.id_alum) and (a.id_alum = r.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) and (ap.id_programa = p.id_programa) order by c.concepto, r.fecha limit 1";
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new BeanPropertyRowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>(RecaudacionesJOINAlumnoJOINConceptoJOINFacultad.class);
		RecaudacionesJOINAlumnoJOINConceptoJOINFacultad recaudaciones = jdbcTemplate.queryForObject(sql, rowMapper, idRecaudaciones);
		logger.info("Facultadupdate idREC"+ recaudaciones);
		
		return recaudaciones;
	}

	@Override
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByStartDateBetween(Date fechaInicial, Date fechaFinal) {
		String sql = "select r.id_rec, r.id_alum, rc.estado ,a.ape_nom, c.concepto, r.numero, f.nombre, r.moneda, r.importe, r.fecha, p.id_programa, r.cod_alumno, r.observacion from recaudaciones r, registro_carga rc, alumno a, facultad f, concepto c where (r.fecha between ? and ?) and (r.id_alum = a.id_alum) and (rc.id_registro = r.id_registro) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) order by c.concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, fechaInicial, fechaFinal);
	}

	@Override // aqui
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApeStartDateBetween(String nomApe, Date fechaInicial,
			Date fechaFinal) {
		String sql = "select r.id_rec, r.id_alum, rc.estado , ap.nom_alumno || ' ' || ap.ape_paterno || ' ' || ap.ape_materno as ape_nom,r.ciclo ,c.concepto,c.id_concepto,a.dni, r.numero, f.nombre, r.moneda, r.importe, r.fecha,p.nom_programa, p.id_programa, r.cod_alumno, r.observacion from recaudaciones r, registro_carga rc,alumno a, facultad f, concepto c, alumno_programa ap, alumno_alumno_programa aap,programa p where to_tsquery( ? ) @@ to_tsvector(coalesce(ap.cod_alumno,'') ) and (rc.id_registro = r.id_registro) and (ap.id_programa = aap.id_programa) and (ap.cod_alumno = aap.cod_alumno) and (aap.id_alum = a.id_alum) and (a.id_alum = r.id_alum) and ((r.fecha between ? and ?) or r.fecha = null) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) and (ap.id_programa = p.id_programa) order by c.concepto, r.fecha";
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
	}// under claass
        
        @Override
/**/	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByCodigo(String codigo) {
		String sql = "select r.id_rec, r.id_alum, rc.estado,ap.nom_alumno || ' ' || ap.ape_paterno || ' ' || ap.ape_materno as ape_nom, r.ciclo,c.concepto,c.id_concepto, a.dni, r.numero, f.nombre, r.moneda, r.importe, r.fecha, p.nom_programa,p.id_programa, r.cod_alumno, r.observacion from recaudaciones r, registro_carga rc,alumno a, facultad f, concepto c, alumno_programa ap,programa p, alumno_alumno_programa aap where to_tsquery(?) @@ to_tsvector(coalesce(ap.cod_alumno,'') || ' ') and (rc.id_registro = r.id_registro) and (ap.id_programa = aap.id_programa) and (ap.cod_alumno = aap.cod_alumno) and (aap.id_alum = a.id_alum) and (a.id_alum = r.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) and (ap.id_programa = p.id_programa) order by c.concepto, r.fecha"; 
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


	@Override // aqui
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApeConcepto(String concepto, String nomApe) {
		String sql = "select r.id_rec, r.id_alum, rc.estado, ap.nom_alumno || ' ' || ap.ape_paterno || ' ' || ap.ape_materno as ape_nom,r.ciclo,c.concepto,c.id_concepto,a.dni, r.numero, f.nombre, r.moneda, r.importe, r.fecha,p.nom_programa, p.id_programa, r.cod_alumno, r.observacion from recaudaciones r, registro_carga rc, alumno a, facultad f, concepto c, alumno_programa ap, alumno_alumno_programa aap,programa p where (c.concepto = ?) and to_tsquery( ? ) @@ to_tsvector(coalesce(ap.cod_alumno,'')) and (rc.id_registro = r.id_registro) and (ap.id_programa = aap.id_programa) and (ap.cod_alumno = aap.cod_alumno) and (aap.id_alum = a.id_alum) and (a.id_alum = r.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) and (ap.id_programa = p.id_programa) order by c.concepto, r.fecha";
		// RowMapper<Recaudaciones> rowMapper = new
		// BeanPropertyRowMapper<Recaudaciones>(Recaudaciones.class);
		RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> rowMapper = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, concepto, nomApe);
	}

	@Override // aqui
	public List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApeRecibo(String recibo, String nomApe) {
		String sql = "select r.id_rec, r.id_alum,rc.estado, ap.nom_alumno || ' ' || ap.ape_paterno || ' ' || ap.ape_materno as ape_nom,r.ciclo ,c.concepto,c.id_concepto,a.dni,r.numero, f.nombre, r.moneda, r.importe, r.fecha,p.nom_programa, p.id_programa, r.cod_alumno, r.observacion from recaudaciones r, registro_carga rc,alumno a, facultad f, concepto c, alumno_programa ap, alumno_alumno_programa aap,programa p where (r.numero = ?) and to_tsquery( ? ) @@ to_tsvector(coalesce(ap.cod_alumno,''))and (rc.id_registro = r.id_registro) and (ap.id_programa = aap.id_programa) and (ap.cod_alumno = aap.cod_alumno) and (aap.id_alum = a.id_alum) and (a.id_alum = r.id_alum) and (a.id_facultad = f.id_facultad) and (r.id_concepto = c.id_concepto) and (c.id_clase_pagos = 2) and (ap.id_programa = p.id_programa) order by c.concepto, r.fecha";
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
	public int updateRecaudacionesCodAlumno(Integer id_rec,String cod_alumno) {
		String sql = "UPDATE recaudaciones SET cod_alumno = ? WHERE id_rec = ?";
		return jdbcTemplate.update(sql, cod_alumno, id_rec);
	}

	
	
	@Override
	public void deleteRecaudacionesJOINAlumnoJOINConceptoJOINFacultad(int idRecaudaciones) {
		String sql = "DELETE FROM recaudaciones WHERE id_rec = ?";
		jdbcTemplate.update(sql, idRecaudaciones);
	}

	@Override
	public boolean updateRecaudacionesJOINAlumnoJOINConceptoJOINFacultad(Date fecha, String numero,int ciclo,int idRec) {
		
		logger.info("Facultad DAO "+fecha+" "+" "+numero+" "+idRec);
		
		String sql = "UPDATE recaudaciones SET fecha = ?, numero = ?,ciclo=? WHERE id_rec = ?";
		logger.info("Facultad DAO "+sql);
		Integer resp = jdbcTemplate.update(sql,fecha,numero,ciclo,idRec);
		logger.info("resp :"+resp);
		if(resp.equals(1)) {
			return true;
		}
		else {
			return false;
		}

	}

	@Override
	public boolean insertObservacion(String observacion,Integer idrec) {
		String sql = "update recaudaciones set observacion = ? where id_rec = ?";
		Integer resp = jdbcTemplate.update(sql,observacion,idrec);
		logger.info("resp :"+resp);
		if(resp.equals(1)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
}
	
