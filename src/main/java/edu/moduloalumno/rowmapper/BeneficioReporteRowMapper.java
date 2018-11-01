package edu.moduloalumno.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.moduloalumno.entity.BeneficioReporte;

public class BeneficioReporteRowMapper implements RowMapper<BeneficioReporte> {

	@Override
	public BeneficioReporte mapRow(ResultSet rs, int rowNum) throws SQLException {
		BeneficioReporte br = new BeneficioReporte();
		br.setTotal(rs.getInt("total"));
		br.setEpg(rs.getInt("epg"));
		br.setUpg(rs.getInt("upg"));
		br.setD_total(rs.getFloat("d_total"));
		br.setD_epg(rs.getInt("d_epg"));
		br.setD_upg(rs.getFloat("d_upg"));
		return br;
	}

}
