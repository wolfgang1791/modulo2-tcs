package edu.moduloalumno.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.moduloalumno.model.PruebaTCambio;

@Component("floatformat")
public class FloatFormat {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static Date tcambio_hoy = new Date();
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
   ; 
	
	public float round(float number, int decimalPlace) {
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
	
	// tipo de cambio api sunat
	public PruebaTCambio dolares_a_soles(Date fecha) throws MalformedURLException, ParseException {
  		PruebaTCambio p= null;
		URL url = new URL("https://api.sunat.cloud/cambio/"+fecha);
		ObjectMapper mapper = new ObjectMapper();

		try {
			
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
	        
	        logger.info("cuerpo "+lineafinal.trim());
	        
	        Reader reader = new StringReader(lineafinal.trim());
	        p = mapper.readValue(reader, PruebaTCambio.class);
			
			logger.info("> objeto "+p);
			if(p.getCompra() == 0.0) {
				 Calendar hoy = new GregorianCalendar(); // funciona pero es fake :v
				 int año = hoy.get(Calendar.YEAR);
				 int mes = hoy.get(Calendar.MONTH);
				 int dia = hoy.get(Calendar.DAY_OF_MONTH);
				 String nuevo = año+"-"+(mes+1)+"-"+(dia-1);
				 logger.info("> nuevoo "+nuevo);
				 p=tipo_cambio_null(nuevo);
				//p.setCompra(1);
			}
			logger.info("> objeto "+p);
			in.close();
		
		} catch (IOException e){
			System.out.println("ERROR! USUARIOS NO GUARDADOS : " + e.getMessage());
		}
	
		return p; 
  	}
	
	public static PruebaTCambio tipo_cambio_null(String fecha) {
			
			PruebaTCambio p = null;
		 	try {
	 		URL url = new URL("https://api.sunat.cloud/cambio/"+fecha);
			ObjectMapper mapper = new ObjectMapper();

			
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
	     
	        
	        Reader reader = new StringReader(lineafinal.trim());
	        p = mapper.readValue(reader, PruebaTCambio.class);
		 	}
		 	catch(Exception e) {
		 		return null;
		 	}
		 	
		 	return p;
	}
}
