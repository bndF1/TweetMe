package Presentation;

import Bussiness.Controlador;
import Exceptions.DAOExcepcion;

public class FactoryControlador {

	public static Controlador getControlador() throws Exception{
		return  Controlador.dameControlador();
	
	}
	public static ControladorP getControladoP() throws DAOExcepcion{
		return  ControladorP.dameControlador();
	}
	
}
