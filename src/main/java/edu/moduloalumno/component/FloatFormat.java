package edu.moduloalumno.component;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component("floatformat")
public class FloatFormat {
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
}
