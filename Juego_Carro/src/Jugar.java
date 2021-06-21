import java.util.Scanner;

;

public class Jugar {
	
	
	public static void  main(String[] args)
	{
		int en=1;
		String name ="william";
		String quedo = "primero";
		Scanner ingreso =new Scanner(System.in);
		String respuesta;
		boolean seguir=true;
		configuracion confi =  new configuracion();
		Bd_conectar conectado = new Bd_conectar();
		while (seguir)
		{
			conectado.conectarBd();							//conectar a la base de datos
			System.out.println("¡Hola!!");					// se da la bienvenida al juego
			System.out.println("Bienvenido al Juego");

			confi.info();									// llamado de funcion para pedir la informacion del juego
			confi.iniciar_juego();							// se llama funcion para iniciar juego
			confi.podio();									// se llama funcion para presentar el podio

			System.out.println("¿Deseas volver a jugar? \n");
			System.out.println("presione: \n S si desea \n N si no lo desea\n");
			 respuesta = ingreso.next();
				if(respuesta.equals("s"))
				{
					 seguir = true;
				}
				else if (respuesta.equals("n"))
				{
					 seguir = false;
				}
			
		}
		//for (int i=0; i<confi.Cantidad_jugadores;i++)
		//{
			confi.guardar_jugador(en, name, quedo);
			System.out.println("guardando en base de datos......");
		//}
		
	}  
	
	
	
}
