package dropper.sandbox;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataTeste {

	public static void main(String[] args) {
		
		
		
		Date d = new Date();
		Calendar cal = new GregorianCalendar();
		
		cal.setTime(d);
		
		System.out.println(cal.toString());
		System.out.println(cal.getTime());
		
		
		java.sql.Date d2 = new java.sql.Date(1);
		
		//d2 = new java.sql.Date(0, 0, 0);
		
		d2 = (java.sql.Date) d;
		cal.setTime(d2);
		
		System.out.println(cal.getTime());
		
		
		
		
		
	}//Fim Main

}//Fim Classe
