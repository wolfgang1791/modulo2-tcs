package edu.moduloalumno.api;
/*
import java.text.DateFormat;
import java.text.SimpleDateFormat;
*/
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.moduloalumno.entity.AlumnoProgramaBeneficioCon;
import edu.moduloalumno.entity.AlumnoProgramaBeneficio;
import edu.moduloalumno.entity.CondicionBeneficio;
import edu.moduloalumno.entity.TipoBeneficio;
import edu.moduloalumno.service.IAlumnoBeneficioService;


@RestController
@RequestMapping("/beneficio")//beneficio
public class AlumnoBeneficioController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IAlumnoBeneficioService alumnobeneficioservice;

	@RequestMapping(value = "/listar/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlumnoProgramaBeneficioCon> getAllAlumnoBeneficio(@PathVariable("codigo") String codigo) {
		logger.info(">> AlumnoBeneficio <<");

		AlumnoProgramaBeneficioCon alubeneficio = null;
		try {
			alubeneficio = alumnobeneficioservice.getAllAlumnoBeneficio(codigo);

			if (alubeneficio == null) {
				alubeneficio = new AlumnoProgramaBeneficioCon();
			}
			
			logger.info("list "+alubeneficio);
		} catch (Exception e) {
			
			logger.error("Unexpected Exception caught." + e.getMessage()+alubeneficio);
			return new ResponseEntity<AlumnoProgramaBeneficioCon>(alubeneficio, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< alumnobeneficio");
		return new ResponseEntity<AlumnoProgramaBeneficioCon>(alubeneficio, HttpStatus.OK);
	}
	
	/* retorna condicion */ 
	@RequestMapping(value = "/condicion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CondicionBeneficio>> getAllCondicion() {
		logger.info(">> AlumnoBeneficio <<");

		List<CondicionBeneficio> condicionbeneficio = null;
		try {
			condicionbeneficio = alumnobeneficioservice.getAllCondicionB();

			if (condicionbeneficio == null) {
				condicionbeneficio = new ArrayList<CondicionBeneficio>();
			}
			
			logger.info("list "+condicionbeneficio);
		} catch (Exception e) {
			logger.error("Unexpected Exception caught. "+ e.getMessage());
			return new ResponseEntity<List<CondicionBeneficio>>(condicionbeneficio, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< alumnobeneficio");
		return new ResponseEntity<List<CondicionBeneficio>>(condicionbeneficio, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/tipo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoBeneficio>> getAllTipo() {
		logger.info(">> TipoBeneficio <<");

		List<TipoBeneficio> tipobeneficio = null;
		try {
			tipobeneficio = alumnobeneficioservice.getAllTipo();

			if (tipobeneficio == null) {
				tipobeneficio = new ArrayList<TipoBeneficio>();
			}
			
			logger.info("list "+tipobeneficio);
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<TipoBeneficio>>(tipobeneficio, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< alumnobeneficio");
		return new ResponseEntity<List<TipoBeneficio>>(tipobeneficio, HttpStatus.OK);
	}
	/*
	@RequestMapping(value = "/insertar_b", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean insertBeneficio(@RequestBody Beneficio beneficio) {
		logger.info("> insertBeneficio[ "+beneficio);
		
		boolean resp = false;
		try {
			resp = alumnobeneficioservice.insertBeneficio(beneficio);
		}
		catch(Exception e) {
			logger.info("> insertBeneficio[ Beneficio: "+beneficio+" APBEneficio: catch ");
			return false;
		}
		
		logger.info(" "+resp);
		return resp;
	}*/
	
	@RequestMapping(value = "/insertar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean insertAlumnoBeneficio(@RequestBody AlumnoProgramaBeneficio apbeneficio) {
		logger.info("> insertAlumnoProgramaBeneficio[ "+apbeneficio+"]"+" "+apbeneficio.getId_bcondicion());
		
		boolean resp = false;
		try {
			
			logger.info("respppp: "+alumnobeneficioservice.getIdBeneficio(apbeneficio.getCod_alumno())+" "+apbeneficio.getObservacion());
			if(!alumnobeneficioservice.getIdBeneficio(apbeneficio.getCod_alumno()))
			{
				apbeneficio.setToQuery(true);
				logger.info("existe");
			}
			else {
				apbeneficio.setToQuery(false);
				logger.info("no existe");
			}
			
			logger.info("respppp: afte "+apbeneficio.getId_beneficio()+" "+apbeneficio);
			resp = alumnobeneficioservice.insertAlumnoProgramaBeneficio(apbeneficio);
			logger.info(">>>>><<<<"+resp);
		}
		catch(Exception e) {
			logger.info("> insertBeneficio[  APBEneficio: "+apbeneficio);
			return false;
		}
		
		
		return resp;
	}
	
	
	
	
}
