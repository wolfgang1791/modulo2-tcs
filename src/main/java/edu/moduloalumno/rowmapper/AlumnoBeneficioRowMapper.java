package edu.moduloalumno.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.moduloalumno.entity.AlumnoBeneficio;



public class AlumnoBeneficioRowMapper implements RowMapper<AlumnoBeneficio> {

	@Override
	public AlumnoBeneficio mapRow(ResultSet rs, int rowNum) throws SQLException {
		AlumnoBeneficio ab = new AlumnoBeneficio();
		ab.setCod_alumno(rs.getString("cod_alumno"));
		ab.setId_programa(rs.getInt("id_programa"));
		ab.setId_beneficio(rs.getInt("id_beneficio"));
		ab.setAutorizacion(rs.getString("autorizacion"));
		ab.setResolucion(rs.getString("resolucion"));
		ab.setCondicion(rs.getString("condicion"));
		ab.setFecha(rs.getDate("fecha"));
		
		return ab;
	}

}