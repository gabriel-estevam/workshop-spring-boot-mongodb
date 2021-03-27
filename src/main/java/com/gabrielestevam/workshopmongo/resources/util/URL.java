package com.gabrielestevam.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
}
