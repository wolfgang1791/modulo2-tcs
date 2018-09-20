package edu.moduloalumno.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import edu.moduloalumno.entity.AlumnoBeneficio;
import edu.moduloalumno.entity.AlumnoProgramaBeneficio;
import edu.moduloalumno.entity.Beneficio;
import edu.moduloalumno.service.IAlumnoBeneficioService;


@RestController
@RequestMapping("/beneficio")
public class AlumnoBeneficioController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IAlumnoBeneficioService alumnobeneficioservice;

	@RequestMapping(value = "/listar/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlumnoBeneficio>> getAllAlumnoBeneficio(@PathVariable("codigo") String codigo) {
		logger.info("> AlumnoBeneficio");

		List<AlumnoBeneficio> list = null;
		try {
			list = alumnobeneficioservice.getAllAlumnoBeneficio(codigo);

			if (list == null) {
				list = new ArrayList<AlumnoBeneficio>();
			}
			
			logger.info("list "+list);
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<AlumnoBeneficio>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< alumnobeneficio");
		return new ResponseEntity<List<AlumnoBeneficio>>(list, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/insertar_b", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean insertBeneficio(@RequestBody Beneficio beneficio) {
		logger.info("> insertBeneficio[ "+beneficio);
		
		boolean resp = false;
		try {	
			resp = alumnobeneficioservice.insertBeneficio(beneficio);
		}
		catch(Exception e) {
			logger.info("> insertBeneficio[ Beneficio: "+beneficio+" APBEneficio: ");
			return false;
		}
		
		
		return resp;
	}
	
	@RequestMapping(value = "/insertar_ab", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean insertAlumnoBeneficio(@RequestBody AlumnoProgramaBeneficio apbeneficio) {
		logger.info("> insertAlumnoProgramaBeneficio[ "+apbeneficio);
		
		boolean resp = false;
		try {
			
			apbeneficio.setId_beneficio(alumnobeneficioservice.getIdBeneficio());
			resp = alumnobeneficioservice.insertAlumnoProgramaBeneficio(apbeneficio);
		}
		catch(Exception e) {
			logger.info("> insertBeneficio[  APBEneficio: "+apbeneficio);
			return false;
		}
		
		
		return resp;
	}
	
	
	
	
}
