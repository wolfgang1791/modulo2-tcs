package edu.moduloalumno.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import edu.moduloalumno.entity.RecaudacionesJOINAlumnoJOINConceptoJOINFacultad;
import edu.moduloalumno.model.Filtro;
import edu.moduloalumno.service.IRecaudacionesJOINAlumnoJOINConceptoJOINFacultadService;
import edu.moduloalumno.util.Operaciones;

@RestController
@RequestMapping("recaudaciones/alumno/concepto")
public class RecaudacionesJOINAlumnoJOINConceptoJOINFacultadController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IRecaudacionesJOINAlumnoJOINConceptoJOINFacultadService recaudacionesJOINAlumnoJOINConceptoJOINFacultadservice;

	@RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>> getAllRecaudacionesJOINAlumnoJOINConceptoJOINFacultads() {
		logger.info("> getAllRecaudacionesJOINAlumnoJOINConceptoJOINFacultads [RecaudacionesJOINAlumnoJOINConceptoJOINFacultad]");

		List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> list = null;
		try {
			list = recaudacionesJOINAlumnoJOINConceptoJOINFacultadservice.getAllRecaudacionesJOINAlumnoJOINConceptoJOINFacultads();

			if (list == null) {
				list = new ArrayList<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>();
			}
			
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< getAllRecaudacionesJOINAlumnoJOINConceptoJOINFacultads [RecaudacionesJOINAlumnoJOINConceptoJOINFacultad]");
		return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/leer/restringido/{nombres}/{apellidos}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadIdByNombresApellidosRestringido(@PathVariable("nombres") String nombres, @PathVariable("apellidos") String apellidos) {
		logger.info("> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNombresApellidosRestringido [RecaudacionesJOINAlumnoJOINConceptoJOINFacultad]");

		List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> list = null;
		try {
			list = recaudacionesJOINAlumnoJOINConceptoJOINFacultadservice.getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadIdByNombresApellidosRestringido(nombres, apellidos);

			if (list == null) {
				list = new ArrayList<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>();
			}
			
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNombresApellidosRestringido [RecaudacionesJOINAlumnoJOINConceptoJOINFacultad]");
		return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listar/{nomApe}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApe(@PathVariable("nomApe") String nomApe) {
		logger.info("> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApe [Recaudaciones]");

		List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> list = null;

		try {

			list = recaudacionesJOINAlumnoJOINConceptoJOINFacultadservice.getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApe(nomApe);
			if (list == null) {
				list = new ArrayList<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>();
			}

		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< getRecaudacionesByNomApe [Recaudaciones]");
		return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list, HttpStatus.OK);
	}
        
        
  /**/  @RequestMapping(value = "/listar_cod/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByCodigo(@PathVariable("codigo") String codigo) {
		logger.info("> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByCodigo [Recaudaciones]");

		List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> list = null;

		try {

			list = recaudacionesJOINAlumnoJOINConceptoJOINFacultadservice.getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByCodigo(codigo);
			if (list == null) {
				list = new ArrayList<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>();
			}

		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< getRecaudacionesByNomApe [Recaudaciones]");
		return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/listar/{fechaInicial}/{fechaFinal}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByStartDateBetween(@PathVariable("fechaInicial") String fechaInicial,
			@PathVariable("fechaFinal") String fechaFinal) {
		logger.info("> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByStartDateBetween [RecaudacionesJOINAlumnoJOINConceptoJOINFacultad]");

		List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> list = null;
		Date fInicial;
		Date fFinal;

		DateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");

		try {

			fInicial = formateador.parse(fechaInicial);
			fFinal = formateador.parse(fechaFinal);

			list = recaudacionesJOINAlumnoJOINConceptoJOINFacultadservice.getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByStartDateBetween(fInicial, fFinal);

			if (list == null) {
				list = new ArrayList<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>();
			}

		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByStartDateBetween [RecaudacionesJOINAlumnoJOINConceptoJOINFacultad]");
		return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/listar/{nom_ape}/{fechaInicial}/{fechaFinal}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApeStartDateBetween(@PathVariable("nom_ape") String nom_ape,
			@PathVariable("fechaInicial") String fechaInicial, @PathVariable("fechaFinal") String fechaFinal) {
		logger.info("> getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApeStartDateBetween [RecaudacionesJOINAlumnoJOINConceptoJOINFacultad]");

		List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> list = null;
		Date fInicial;
		Date fFinal;

		DateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");

		try {

			fInicial = formateador.parse(fechaInicial);
			fFinal = formateador.parse(fechaFinal);

			list = recaudacionesJOINAlumnoJOINConceptoJOINFacultadservice.getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApeStartDateBetween(nom_ape, fInicial, fFinal);

			if (list == null) {
				list = new ArrayList<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>();
			}

		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApeStartDateBetween [RecaudacionesJOINAlumnoJOINConceptoJOINFacultad]");
		return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/listar/filtrar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>> getRecaudacionesByFilter(@RequestBody Filtro filtro) {
		logger.info("> filterByAlumno [RecaudacionesJOINAlumnoJOINConceptoJOINFacultad]");

		String fechaFinal = filtro.getFechaFinal();

		String nom_ape = filtro.getNom_ape();

		String fechaInicial = filtro.getFechaInicial();

		String[] conceptos = filtro.getConceptos();

		String[] recibos = filtro.getRecibos();

		List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> list01 = new ArrayList<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>();
		List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> list02 = new ArrayList<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>();
		List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> list03 = null;

		Date fInicial;
		Date fFinal;

		DateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");

		Operaciones operacion = new Operaciones();

		try {

			for (String concepto : conceptos) {
				list01 = operacion.union(list01,
						recaudacionesJOINAlumnoJOINConceptoJOINFacultadservice.getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApeConcepto(concepto, nom_ape));
			}

			logger.info("LISTA DE RECAUDACIONES POR CONCEPTO: \n" + list01);

			for (String recibo : recibos) {
				list02 = operacion.union(list02, recaudacionesJOINAlumnoJOINConceptoJOINFacultadservice.getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApeRecibo(recibo, nom_ape));
			}

			logger.info("LISTA DE RECAUDACIONES POR RECIBOS: \n" + list02);

			fInicial = formateador.parse(fechaInicial);
			fFinal = formateador.parse(fechaFinal);

			list03 = recaudacionesJOINAlumnoJOINConceptoJOINFacultadservice.getRecaudacionesJOINAlumnoJOINConceptoJOINFacultadByNomApeStartDateBetween(nom_ape, fInicial, fFinal);

			if (list03 == null) {
				list03 = new ArrayList<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>();
			}

			logger.info("LISTA DE RECAUDACIONES POR FECHAS: \n" + list03);

			list03 = operacion.intersection(list03, conceptos.length != 0 ? list01 : list03);
			list03 = operacion.intersection(list03, recibos.length != 0 ? list02 : list03);
			
			logger.info("LISTA DE RECAUDACIONES INTERSECCION: \n " + list03);
			logger.info("Hola mundo");

		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list03, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< filterByAlumno [Recaudaciones]");
		return new ResponseEntity<List<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad>>(list03, HttpStatus.OK);
	}

}
