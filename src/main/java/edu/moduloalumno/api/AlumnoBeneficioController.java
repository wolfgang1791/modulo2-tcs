package edu.moduloalumno.api;

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
	
	@RequestMapping(value = "/insertar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean insertBeneficio(@RequestBody Beneficio beneficio,@RequestBody AlumnoProgramaBeneficio apbeneficio) {
		logger.info("> insertBeneficio[ Benficio: "+beneficio+" APBEneficio: "+apbeneficio);
		
		boolean resp = false;
		try {
			
			boolean resp0 = alumnobeneficioservice.insertBeneficio(beneficio);
			
			if(resp0) {
				resp = alumnobeneficioservice.insertAlumnoProgramaBeneficio(apbeneficio);
			}
			
		}
		catch(Exception e) {
			logger.info("> insertBeneficio[ Benficio: "+beneficio+" APBEneficio: "+apbeneficio);
			return false;
		}
		
		
		return resp;
	}
	
	
	
	
}
