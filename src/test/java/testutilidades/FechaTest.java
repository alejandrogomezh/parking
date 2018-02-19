package testutilidades;

import java.util.Calendar;
import java.util.Date;

public class FechaTest {
	
	private FechaTest() {
		
	}
	
	public static Date crearFecha(int dia, int mes, int anio){
		Calendar fecha = Calendar.getInstance();
		fecha.set(anio, mes, dia);
		return fecha.getTime();
	}
	public static Date crearFechaConHora(int dia, int mes, int anio, int hora, int minutos){
		Calendar fecha = Calendar.getInstance();
		fecha.set(anio, mes, dia, hora, minutos);
		return fecha.getTime();
	}
}
