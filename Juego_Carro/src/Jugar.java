import java.util.Scanner;

;

public class Jugar {
	
	
	public static void  main(String[] args) {
		Scanner ingreso =new Scanner(System.in);
		String respuesta;
		boolean seguir=true;
		
		
		while (seguir)
		{
			
			System.out.println("¡Hola!!");
			System.out.println("Bienvenido al Juego");
			
			configuracion confi =  new configuracion();
			confi.info();
			confi.iniciar_juego();
			confi.podio();

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
		
		
	}  
	
	
	
}
