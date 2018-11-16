package edu.moduloalumno.api;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import edu.moduloalumno.entity.BeneficioReporte;
import edu.moduloalumno.entity.AlumnoProgramaBeneficio;
import edu.moduloalumno.entity.CondicionBeneficio;
import edu.moduloalumno.entity.TipoAplicaBeneficio;
import edu.moduloalumno.entity.TipoBeneficio;
import edu.moduloalumno.service.IAlumnoBeneficioService;


@RestController
@RequestMapping("/beneficio")//beneficio
public class AlumnoBeneficioController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IAlumnoBeneficioService alumnobeneficioservice;

	@RequestMapping(value = "/listar/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlumnoProgramaBeneficioCon>> getAllAlumnoBeneficio(@PathVariable("codigo") String codigo) {
		logger.info(">> AlumnoBeneficio <<");

		List<AlumnoProgramaBeneficioCon> list = null;
		try {
			list = alumnobeneficioservice.getAllAlumnoBeneficio(codigo);

			if (list == null) {
				list = new ArrayList<AlumnoProgramaBeneficioCon>();
			}
			
			//logger.info("list "+alubeneficio);
		} catch (Exception e) {
			
			logger.error("Unexpected Exception caught." + e.getMessage());
			return new ResponseEntity<List<AlumnoProgramaBeneficioCon>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< alumnobeneficio");
		return new ResponseEntity<List<AlumnoProgramaBeneficioCon>>(list, HttpStatus.OK);
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
	
	
	@RequestMapping(value = "/insertar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean insertAlumnoBeneficio(@RequestBody AlumnoProgramaBeneficio apbeneficio) {
		logger.info("> insertAlumnoProgramaBeneficio[ "+apbeneficio+"]"+" "+apbeneficio.getId_bcondicion());
		
		boolean resp = false;
		try {
			//alumnobeneficioservice.getIdBeneficio(apbeneficio.getId_abp()) alumnobeneficioservice.getIdBeneficio(apbeneficio.getId_abp())
			logger.info("respppp:  abp "+apbeneficio.getId_abp());
			if(!alumnobeneficioservice.getIdBeneficio(apbeneficio.getId_abp()))
			{
				apbeneficio.setToQuery(true);
				logger.info("no existe");
			}
			else {
				apbeneficio.setToQuery(false);
				logger.info("existe");
			}
			
			logger.info("respppp: afte "+apbeneficio.getId_beneficio()+" "+apbeneficio);
			resp = alumnobeneficioservice.insertAlumnoProgramaBeneficio(apbeneficio);
			logger.info(">>>>><<<<"+resp);
		}
		catch(Exception e) {
			logger.info("catch  "+e.getMessage());
			logger.info("> insertBeneficio[  APBEneficio: "+apbeneficio);
			return false;
		}
		
		
		return resp;
	}
	
	@RequestMapping(value = "/tipo_aplica", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoAplicaBeneficio>> getTipoAplicaBeneficio() {
		logger.info(">> AlumnoBeneficio <<");

		List<TipoAplicaBeneficio> list = null;
		try {
			list = alumnobeneficioservice.getTipoAplicaBeneficio();

			if (list == null) {
				list = new ArrayList<TipoAplicaBeneficio>();
			}
			
			//logger.info("list "+alubeneficio);
		} catch (Exception e) {
			
			logger.error("Unexpected Exception caught." + e.getMessage());
			return new ResponseEntity<List<TipoAplicaBeneficio>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< alumnobeneficio");
		return new ResponseEntity<List<TipoAplicaBeneficio>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/breporte/{codigo}/{id_programa}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeneficioReporte> getBeneficioReporte(@PathVariable("codigo") String codigo,@PathVariable("id_programa") Integer id_programa) {
		logger.info(">> getBeneficio Reporte <<");
		
		List<AlumnoProgramaBeneficioCon> list = null;
		BeneficioReporte breporte = null;
		
		float descuento = 1;
		
		try {
			list = alumnobeneficioservice.getAllAlumnoBeneficio(codigo);
			logger.error("lista: " + list);
			if (!(list == null)) {
				for(AlumnoProgramaBeneficioCon alumno:list) {
					
					descuento *= (100 - alumno.getBenef_otrogado()); 
					logger.error("double: " + alumno.getBenef_otrogado());
				}
				descuento = (float) (descuento/(Math.pow(100,list.size())))*100;
				descuento = (100 -descuento)/100;
				System.out.println("descuento: "+descuento);
			
				breporte = alumnobeneficioservice.funcionDescuento(codigo,descuento,id_programa);
				
				breporte.setD_total(round(breporte.getD_total(), 2));
				breporte.setD_upg(round(breporte.getD_upg(), 2));
				
				breporte.setD_Total(breporte.getD_upg()+breporte.getD_epg()+breporte.getD_total());
				breporte.set_Total(breporte.getEpg()+breporte.getUpg()+breporte.getTotal());
				
				logger.error("Breporte: " + breporte);
			}
		
		} catch (Exception e) {
			
			logger.error("Unexpected Exception caught." + e.getMessage());
			return new ResponseEntity<BeneficioReporte>(breporte, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< alumnobeneficio");
		return new ResponseEntity<BeneficioReporte>(breporte, HttpStatus.OK);
	}
	
	public static float round(float number, int decimalPlace) {
		BigDecimal bd = new BigDecimal(number);
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
		/*NumberFormat formatoNumero = NumberFormat.getNumberInstance();
		System.out.println(formatoNumero.format(number));
		return Float.parseFloat(formatoNumero.format(number));*/
		/*
		DecimalFormat df = new DecimalFormat("#,###.00");
	    System.out.println(df.format(new BigDecimal(number)));
	    float res = Float.parseFloat(df.format(new BigDecimal(number)));
	    System.out.println(res);
	    return  res;*/
	}
	
}
