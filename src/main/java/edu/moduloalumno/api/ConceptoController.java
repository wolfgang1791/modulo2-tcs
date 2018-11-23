package edu.moduloalumno.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import edu.moduloalumno.entity.Concepto;
import edu.moduloalumno.model.PruebaTCambio;
import edu.moduloalumno.service.IConceptoService;

@RestController
@RequestMapping("/concepto")
public class ConceptoController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IConceptoService service;
	
	@RequestMapping(value = "/buscar/{idConcepto}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Concepto> getConceptoById(@PathVariable("idConcepto") Integer idConcepto) {
		logger.info("> getConceptoById [Concepto]");

		Concepto concepto = null;

		try {
			concepto = service.getConceptoById(idConcepto);
			
			if (concepto == null) {
				concepto = new Concepto();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Concepto>(concepto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		logger.info("< getConceptoById [Concepto]");
		return new ResponseEntity<Concepto>(concepto, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/conceptos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Concepto>> getAllConcepto() {
		logger.info("> getConceptoById [Concepto]");

		List<Concepto> conceptos = null;

		try {
			conceptos = service.getAllConceptos();
			logger.info("conceptos "+conceptos);
			if (conceptos == null) {
				conceptos = new ArrayList<Concepto>();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<Concepto>>(conceptos, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		logger.info("< getConceptoById [Concepto]");
		return new ResponseEntity<List<Concepto>>(conceptos, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/leer/{apeNom}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Concepto>> getConceptoIdByApeNom(@PathVariable("apeNom") String apeNom) {
		logger.info("> getConceptoIdByApeNom [Concepto]");

		List<Concepto> list = null;

		try {
			list = service.getConceptoIdByApeNom(apeNom);
			
			if (list == null) {
				list = new ArrayList<Concepto>();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<Concepto>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		logger.info("< getConceptoIdByApeNom [Concepto]");
		return new ResponseEntity<List<Concepto>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tcambio/{fecha}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Float> getTipodecambio(@PathVariable("fecha") String fecha) {
		logger.info("> getConceptoIdByApeNom [Concepto]");

		Float tcambio = null;
		DateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");

		try {
			tcambio = service.getTipodecambio(formateador.parse(fecha));
			
			if (tcambio == null) {
				tcambio = (float) 1.0;
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<Float>(tcambio, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		logger.info("< getConceptoIdByApeNom [Concepto]");
		return new ResponseEntity<Float>(tcambio, HttpStatus.OK);
	}
	
         
	@RequestMapping(value = "/leer/restringido/{apeNom}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Concepto>> getConceptoIdByApeNomRestringido(@PathVariable("apeNom") String apeNom) {
		logger.info("> getConceptoIdByApeNomRestringido [Concepto]");

		List<Concepto> list = null;

		try {
			list = service.getConceptoIdByApeNomRestringido(apeNom);
			
			if (list == null) {
				list = new ArrayList<Concepto>();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<Concepto>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		logger.info("< getConceptoIdByApeNomRestringido [Concepto]");
		return new ResponseEntity<List<Concepto>>(list, HttpStatus.OK);
	}
	
	
        @RequestMapping(value = "/leer/restringido_cod/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Concepto>> getConceptoIdByApeNomRestringidoCodigo(@PathVariable("codigo") String codigo) {
		logger.info("> getConceptoIdByApeNomRestringido [Concepto]");

		List<Concepto> list = null;

		try {
			list = service.getConceptoIdByApeNomRestringidoCodigo(codigo);
			
			if (list == null) {
				list = new ArrayList<Concepto>();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<Concepto>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		logger.info("< getConceptoIdByApeNomRestringido [Concepto]");
		return new ResponseEntity<List<Concepto>>(list, HttpStatus.OK);
	}
        
        
        @RequestMapping(value = "/leer/{nombres}/{apellidos}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Concepto>> getConceptoApellidosNombres(@PathVariable("nombres") String nombres,@PathVariable("apellidos") String apellidos) {
		logger.info("> getConceptoApellidosNombres [Concepto]");

		List<Concepto> list = null;

		try {
			list = service.getConceptoIdByNombresApellidos(nombres, apellidos);
			
			if (list == null) {
				list = new ArrayList<Concepto>();
			}
		} catch (Exception e) {
			logger.error("Unexpected Exception caught.", e);
			return new ResponseEntity<List<Concepto>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		logger.info("< getConceptoApellidosNombres [Concepto]");
		return new ResponseEntity<List<Concepto>>(list, HttpStatus.OK);
	}
        
    // prueba tipo_cambio
        @RequestMapping(value = "/prueba/{fecha}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    	public ResponseEntity<PruebaTCambio> tipocambio(@PathVariable("fecha") String fecha) throws IOException {
    		logger.info("> getConceptoApellidosNombres [Concepto]");

    //		 String list= null;
    		 PruebaTCambio p= null;
    			//recogiendo datos del SUM FAKE
    				URL url = new URL("https://api.sunat.cloud/cambio/"+fecha);
    		//		Gson gson = new Gson();
    				ObjectMapper mapper = new ObjectMapper();
    			//	TypeReference<String> typeReference = new TypeReference<String>(){};
    		
    				//InputStream inputStream = url.openStream();
    				try {
    					/*Reader reader = new StringReader(inputStream);

    					Car car = objectMapper.readValue(reader, Car.class);
    					
    					list = mapper.readValue(inputStream,typeReference);
    					p = gson.fromJson(list, PruebaTCambio.class);*/
    					//URL oracle = new URL("http://www.oracle.com/");
    			        BufferedReader in = new BufferedReader(
    			        new InputStreamReader(url.openStream()));

    			        String inputLine="",lineafinal="";
    			        int campos=0;
    			        while ((inputLine = in.readLine()) != null) {
    			        	if(campos == 2 || campos == 3)
    			        			lineafinal+=inputLine;
    			         campos++;
    			        }
    			        
    			        lineafinal="{"+lineafinal+"}";
    			        logger.info("> aberrr 1 "+lineafinal.trim());
    			        Reader reader = new StringReader(lineafinal.trim());

    			        p = mapper.readValue(reader, PruebaTCambio.class);
    					
    					logger.info("> aberrr 2"+p);
    					in.close();
    				} catch (IOException e){
    					System.out.println("ERROR! USUARIOS NO GUARDADOS : " + e.getMessage());
    				}
    			
    			//Agregando la lista a la base de datos
    			
    				//boolean xd=service.agregarListaCursos(cursos);
    			
    			
    			
    			return new ResponseEntity<PruebaTCambio>(p, HttpStatus.OK); 
    	}
        
}
