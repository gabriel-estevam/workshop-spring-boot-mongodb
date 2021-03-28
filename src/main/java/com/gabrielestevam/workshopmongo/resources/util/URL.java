package com.gabrielestevam.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL 
{
//classe utilitária com um metoodo para decodificar parametro de URL 
	
	public static String decodeParam(String text) 
	{
		/*Metodo estatico para decodificar um parametro, esse metodo
		 * retorna um texto decodificado no padrão UTF, porem se o parametro
		 * não seja compativel com a decodificação retorna ""
		 */
		try {
			return URLDecoder.decode(text,"UTF-8"); //retorna o parametro decodificado no padrão UTF8
		} 
		catch (UnsupportedEncodingException e) {
			return ""; //se não for compativel, retorna ""
		}
	}
	
	public static Date convertDate(String textDate, Date defaultValue) 
	{
		/*metodo para converter uma data, temo como parametro uma String que vai armazenar a data
		* e tambem um tipo date para caso a data informada aconteça algum erro ele passa a padrão*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //instanciado um obj para converter a data no padrão ano-mes-dia
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); //seta a data no padrão GMT (poderia pegar o padrão da maquina do usuário)
		try {
			return sdf.parse(textDate); //retorna a data convertida
		} 
		catch (ParseException e) {
			return defaultValue; //caso acontece algum erro na conversão, retona a data padrão
		}
	}
}
