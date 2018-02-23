package co.ceiba.parking.messages;

public class Messages {
	public static final String DEBE_INGRESAR_TIPO_VEHICULO = "Por favor ingrese el tipo de vehiculo";
	public static final String DEBE_INGRESAR_PLACA = "Por favor ingrese la PLACA";
	
	public static final String INGRESO_SATISFACTORIO = "Ingreso satisfactorio";
	public static final String YA_HABIA_INGRESADO = "Ya habia ingresado";
	public static final String NO_HAY_CUPO = "Yo hay cupo";
	public static final String INGRESO_NO_AUTORIZADO = "No esta autorizado a ingresar";
	
	public static final String SALIDA_SATISFACTORIO = "Salida satisfactorio";
	public static final String VEHICULO_NO_EXISTE = "No Existe el vehiculo";
	public static final String NO_A_INGRESADO = "No a ingresado";

	private String msg;

	public Messages(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Messages [msg=" + msg + "]";
	}
	
}
