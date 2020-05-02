package com.bit.common;

import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bit.model.CatalogoDiccionarioTiendas;
import com.bit.model.dto.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private static final String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
	private static final String[] meses_completo = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", 
			"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	
	private static final Map<String, String> DICCIONARIO;
	private static InputStream credentialsStream = null;
	static{
		DICCIONARIO = new HashMap<String, String>();
	}
	
	public static String generaCodigoVerficacion() {
		Random rand = new Random();
		String id = String.format("%04d", rand.nextInt(9999));
		
		return id;
	}
	
	public static String generaHash(String originalString) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(
		  originalString.getBytes(StandardCharsets.UTF_8));
		
		return bytesToHex(encodedhash);
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	public static String objectToJSON(Object o) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(o);
	}
	
	public static void cargaDiccionario( List<CatalogoDiccionarioTiendas> list ){
		
		DICCIONARIO.clear();
		
		for( CatalogoDiccionarioTiendas item : list ){
			DICCIONARIO.put( item.getClaveDiccionario(), item.getValorDiccionario());
		}
		
	}
	
	public static void setCredentialsStream( InputStream stream ) {
		credentialsStream = stream;
	}
	
	public static InputStream getCredentialsStream() {
		return credentialsStream;
	}
	
	public static Map<String, String> obtieneCatalogo(){
		return Collections.unmodifiableMap( DICCIONARIO );
	}
	
	public static boolean isEmail( String email ) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		
		Matcher m = pattern.matcher(email);
		return m.find();
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static Period calcularEdad( Calendar fechaNac ) {
		LocalDate today = LocalDate.now();
		
		int year = fechaNac.get( Calendar.YEAR );
		int month = fechaNac.get( Calendar.MONTH ) + 1;
		int dayOfMonth = fechaNac.get( Calendar.DAY_OF_MONTH );
		
		
		LocalDate birthday = LocalDate.of(year, 
				month, 
				dayOfMonth);
		 
		Period p = Period.between(birthday, today);
		 
		return p;
	}
	
	//dd/mm/anio
	public static String formatDateToString(Date date, String format) {
		
		if( null == date ) {
			return "";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
		return sdf.format(date);
	}
	
	public static Date formatStringToDate(String strDate, String format) {
		Date date = null;
		if( null == strDate ) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		return date;
	}
	
	public static String getCurrentFormatDate( String format ) {
		LocalDate localDate = LocalDate.now(ZoneId.of("UTC"));
		
		if( null == format || "".equals(format.trim()) ) {
			format = "dd-MMM-yy";
		}
		String formattedDate = localDate.format(DateTimeFormatter.ofPattern(format));
		
		return formattedDate;
	}
	
	public static String formatNumeros( Double numero, String format ) {
		
		if( null == numero ) {
			return "";
		}
		
		DecimalFormat dm = new DecimalFormat( format );
		return dm.format(numero);
	}
	
	public static String getDateRangeFromWeek( long week ) {
		String format = "dd-MMM-yy";
		
		LocalDate startDate = LocalDate.now()
	            .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week)
	            .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		
		LocalDate endDate = startDate.plusDays(6);
		
		String strStartDate = startDate.format(DateTimeFormatter.ofPattern(format));
		String strEndDate = endDate.format(DateTimeFormatter.ofPattern(format));
		
		return strStartDate + " - " + strEndDate; 
	}
	
	
	public static void initEscaneosPorMes(List<Item> list, List<Item> resultData) {
		
		//Se inicializa la lista con objetos Item, con los valores del mes
		for( String mes : meses ) {
			list.add( new Item(mes, BigInteger.valueOf(0)) );
		}
		
		//Se busca el elemento en la lista que coincida con el valor del campo indice
		//del resultData para asignar el total correspondiente
		for( Item data : resultData ) {
			Item i = list.get( data.getIndice() - 1 );
			
			if( null != i ) {
				i.setTotal( data.getTotal() );
				i.setImporte( data.getImporte() );
			}
		}
	}
	
	public static void initListaMensual(List<Item> list, List<Item> resultData) {
		
		//Se inicializa la lista con objetos Item, con los valores del mes
		for( String mes : meses ) {
			list.add( new Item(mes, BigInteger.valueOf(0)) );
		}
		
		//Se busca el elemento en la lista que coincida con el valor del campo indice
		//del resultData para asignar el total correspondiente
		for( Item data : resultData ) {
			Item i = list.get( data.getIndice() - 1 );
			
			if( null != i ) {
				i.setTotal( data.getTotal() );
				i.setImporte( data.getImporte() );
			}
		}
	}
	
	public static void initListaSemanal( List<Item> list ) {
		for (Item item : list) {
			String range = Utils.getDateRangeFromWeek( item.getIndice() );
			item.setTopico(range);
		}
	}
	
	/*
	 * Para devolver dias, meses, anios
	 */
	public static List<Item> obtieneDias() {
		List<Item> dias = new ArrayList<>();
		
		for( int i = 1; i <= 31; i++ ) {
			Item j = new Item();
			j.setIndice( i );
			j.setTopico( String.valueOf( i ) );
			dias.add( j );
		}
		
		return dias;
		
	}
	
	public static List<Item> obtieneMeses() {
		List<Item> meses = new ArrayList<>();
		
		for( int i = 0; i < meses_completo.length; i++ ) {
			Item j = new Item();
			j.setIndice( i );
			j.setTopico( meses_completo[i] );
			meses.add( j );
		}
		
		return meses;
		
	}
	
	public static List<Item> obtieneAnios() {
		List<Item> anios = new ArrayList<>();
		
		for( int i = 2001; i >= 1919; i-- ) {
			Item j = new Item();
			j.setIndice( i );
			j.setTopico( String.valueOf( i ) );
			anios.add( j );
		}
		
		return anios;
	}
	
	public static void main (String[] args) throws ParseException {
		
		final long calendarWeek = 1;
		LocalDate startDate = LocalDate.now()
		            .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, calendarWeek)
		            .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		
		System.out.println( startDate + " - " + startDate.plusDays(6) );
		
		LocalDate localDate = LocalDate.now(ZoneId.of("UTC"));
		System.out.println( "LocalDate: " + localDate.toString() );
		
		String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
		System.out.println( "LocalDate custom format: " + formattedDate );
		
		String s = "5534714616";
		System.out.printf("%s es email valido?: %s \n", s, Utils.isEmail(s));
		
		String f = "21-may-2019";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date d = sdf.parse(f);
		
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String d1 = sdf.format(d);
		System.out.println( d1 );
		
//		System.out.println("codigo de verificacion: " + Utils.generaCodigoVerficacion());
	}

	
}
