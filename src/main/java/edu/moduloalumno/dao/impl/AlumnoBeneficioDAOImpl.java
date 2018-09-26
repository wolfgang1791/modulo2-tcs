package edu.moduloalumno.dao.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.moduloalumno.dao.IAlumnoBeneficioDAO;
import edu.moduloalumno.entity.AlumnoProgramaBeneficioCon;
import edu.moduloalumno.entity.AlumnoProgramaBeneficio;
import edu.moduloalumno.entity.TipoBeneficio;
import edu.moduloalumno.entity.CondicionBeneficio;
import edu.moduloalumno.rowmapper.AlumnoBeneficioRowMapper;
import edu.moduloalumno.rowmapper.TipoBeneficioRowMapper;
import edu.moduloalumno.rowmapper.CondicionBeneficioRowMapper;




@Transactional
@Repository
public class AlumnoBeneficioDAOImpl implements IAlumnoBeneficioDAO{
	
	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;//i wanna be adored
	
	@Override
	public AlumnoProgramaBeneficioCon getAllAlumnoBeneficio(String codigo) {
		try {
		String sql = "select abp.cod_alumno,abp.id_programa,abp.id_beneficio,abp.beneficio_otorgado,b.beneficio_max,abp.autorizacion,b.resolucion,b.tipo,abp.id_benef_condicion,bc.condicion,abp.fecha,abp.observacion from alumno_programa_beneficio abp,beneficio b, beneficio_condicion bc where abp.cod_alumno = (?) and (abp.id_beneficio = b.id_beneficio) and (abp.id_benef_condicion =  bc.id_benef_condicion)";
		RowMapper<AlumnoProgramaBeneficioCon> rowMapper = new AlumnoBeneficioRowMapper();
		System.out.println(jdbcTemplate.queryForObject(sql, rowMapper, codigo));
		AlumnoProgramaBeneficioCon alumnobeneficio = jdbcTemplate.queryForObject(sql, rowMapper, codigo);		
		return alumnobeneficio;
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}	
	}

	@Override
	public List<CondicionBeneficio> getAllCondicionB() {
		String sql = "select * from beneficio_condicion;";
		RowMapper<CondicionBeneficio> rowMapper = new CondicionBeneficioRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public List<TipoBeneficio> getAllTipo() {
		String sql = "select * from beneficio order by id_beneficio";
		RowMapper<TipoBeneficio> rowMapper = new TipoBeneficioRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public boolean insertAlumnoProgramaBeneficio(AlumnoProgramaBeneficio apbeneficio) {
		
		String sql="";
		Integer ret = 0;
		
		if(apbeneficio.getToQuery()) {System.out.println("insert");
			sql = "INSERT INTO alumno_programa_beneficio(cod_alumno,id_programa,id_beneficio,observacion,beneficio_otorgado,id_benef_condicion,fecha,autorizacion) values (?,?,?,?,?,?,?,?)";
		 	ret = jdbcTemplate.update(sql, apbeneficio.getCod_alumno(),apbeneficio.getId_programa(),apbeneficio.getId_beneficio(),apbeneficio.getObservacion(),apbeneficio.getBeneficio_otorgado(),apbeneficio.getId_bcondicion(),apbeneficio.getFecha(),apbeneficio.getAutorizacion());
		}
		else {System.out.println("update");
			sql = "UPDATE alumno_programa_beneficio SET id_beneficio = ?,observacion = ?,beneficio_otorgado = ?,id_benef_condicion = ?,fecha = ?,autorizacion = ? where cod_alumno = ?";
			ret = jdbcTemplate.update(sql,apbeneficio.getId_beneficio(),apbeneficio.getObservacion(),apbeneficio.getBeneficio_otorgado(),apbeneficio.getId_bcondicion(),apbeneficio.getFecha(),apbeneficio.getAutorizacion(),apbeneficio.getCod_alumno());
		}
		System.out.println("impla "+ret);	
		
		if(ret.equals(1)) {
			return true;
		}
		else 
		{
			return false;
		}
	}

	@Override
	public boolean getIdBeneficio(String cod) {
		
		try {
			String sql = "select cod_alumno from alumno_programa_beneficio where cod_alumno = ?";
			String cod_alumno = jdbcTemplate.queryForObject(sql, new Object[] { cod }, String.class);
			System.out.println("cod_alumno "+cod_alumno);
			if(cod_alumno.length() == 8)
			{	System.out.println("return true");
				return true;
			}
			else 
			{	
				System.out.println("return false");
				return false;
			}
			
		}
		catch(EmptyResultDataAccessException e) {
			System.out.println("cod_alumno FUCKKKKK");
			return false;
		}
		
	}

}