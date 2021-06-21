import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;



public class configuracion 
{
	
    public int  Cantidad_jugadores;
	private int  km;
	private double  N_carro;
	private String  Nombre_Jugador;
	private double  Carril;
	Scanner teclado = new Scanner(System.in);
	String [] corredores;
	String p_puesto;
	String s_puesto;
	String t_puesto;
	List<Double> Carriles = new ArrayList<Double>();
	List<Double> Carros = new ArrayList<Double>();
	double [] Km_recorridos;
	int num=0;
	
	// Se crea un Metodo para guardar los valores ingresados
	public int guardar_jugador (int id, String Name, String puesto )
	{
		int resultado = 0;
		Bd_conectar BD =  new Bd_conectar();
		BD.conectarBd(); 
		String SSQL = "INSERT INTO jugador(id, name, apellido) " + "values (?,?,?)";
		
		try {
			
			java.sql.PreparedStatement Psql = BD.conexion.prepareStatement(SSQL);
			Psql.setInt(1, id);
			Psql.setString(2, Name);
			Psql.setString(3, puesto);
			
			resultado = Psql.executeUpdate();
			Psql.close();
			
			
		}catch (Exception e) {
			
			JOptionPane.showConfirmDialog(null, "Error al almacenar datos:\n" + e, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			
		} 
		return resultado;
	}
	

	public void info()			//Se crea un metodo para pedir la información de juego
	{
	
		datos_juego();  		// Se inicia ingresando datos del juego
		corredores = new String [Cantidad_jugadores];
		nombre_j();    		    //Se piden los datos de jugadores
		carril_j();             //Se asigna carriles y carros
		carro_j();
		datos_jugadores();      //Se muestra la informacion (nombres, carriles y carros)

		}
	public void iniciar_juego ()
	{
	    System.out.println("Vamos a comenzar la carrera!! " );
	   Km_recorridos = new double [Cantidad_jugadores];
		limite();			
	}

		public void datos_juego()
		{	
			int x;
			System.out.println("Por favor ingrese el numero de corredores\n");
			Cantidad_jugadores= teclado.nextInt();
			System.out.println("Por favor la distancia en kilomentros \n");
			km = teclado.nextInt()*1000;
		}
		
		public void nombre_j()
		{
			
			for (int i=0; i<Cantidad_jugadores;i++)
			{
				int n= i+1;
				System.out.println("Datos del corredor con ID N°: " +n);
				System.out.println("Por favor ingrese el Nombre y Apellido del corredor Nº: " +n); 
				corredores[i]=teclado.next();
				teclado.nextLine();

			}	
			
		}
		public void carril_j()
		{
			for (int i=0; i<Cantidad_jugadores;i++)
			{
				Carril = Math.floor(Math.random()*Cantidad_jugadores+1);
				boolean existe_Carril = Carriles.contains(Carril);
				
				while (existe_Carril)
				{
					Carril = Math.floor(Math.random()*Cantidad_jugadores+1);
				    existe_Carril = Carriles.contains(Carril);
				}
				Carriles.add(Carril);
			}
		}
		
		public void carro_j()
		{	
			for (int i=0; i<Cantidad_jugadores;i++)
			{
				N_carro = Math.floor(Math.random()*Cantidad_jugadores+1);
				boolean existe_Carro = Carros.contains(N_carro);

				while (existe_Carro)
				{
					N_carro = Math.floor(Math.random()*Cantidad_jugadores+1);
				    existe_Carro = Carros.contains(N_carro);
				}
				Carros.add(N_carro);
			}
		}
			
		public void datos_jugadores()
		{	
			
			for(int i=0; i<Cantidad_jugadores; i++) 
			{
				 num= i+1;
				
				System.out.println("Nombre del corredor con ID N° " +num+ ":\n "+ corredores[i]);
				System.out.println("El carril del jugador N: " + num + " es: \n " +Carriles.get(i));	
				System.out.println("El numero de  carro del jugador N: " + num + " es: \n " +Carros.get(i));	
				
			}
		}
		
		
		public void limite()
		{	
			
			int ganadores=0;
			Scanner tecla =new Scanner(System.in);
			double Tecla_dado=0;
			double min=0;
			double n_ganador=0;
			double distancia_jugador=0.0;
			String letra;
			List<Double> n_ganadores = new ArrayList<Double>();
			
			for(int k=0; k<Cantidad_jugadores; k++) 
			{
				
				Km_recorridos[k]=0.0;
			}
			while(min<km)
				
			{
				
				
					for(int i=0; i<Cantidad_jugadores; i++) 
					{
					
					//boolean existe_ganador = n_ganadores.contains(i);
					
					if (Km_recorridos[i]>=km)
					{
						System.out.println("Ok");
						//se busca el jugador con el recorrido mas corto para que cuando se cumpla el limite se acabe el juego
						if(i==0)
						{
						min=Km_recorridos[i];
						}
						else if(min>Km_recorridos[i])
						{
						min=Km_recorridos[i];
						}
					}
					else
					{
							int n= i+1;
							
							System.out.println("Por favor lanzar dado jugador con ID N° " +n + " presionando la tecla l y ENTER");
							
							
						    letra = tecla.next();
							if(letra.equalsIgnoreCase("l"))
							{
								Tecla_dado = new Random().nextInt(6)+1;
								distancia_jugador = (Tecla_dado * 100) + Km_recorridos[i];
								Km_recorridos[i]=distancia_jugador;
							}
							else 
							{
								System.out.println("Debes ingresar la tecla l, pierdes el avance.");
				
							}
							System.out.println("Sacaste el numero: " + Tecla_dado);	
							System.out.println("La distancia recorrida del jugador N: " + n + " es: \n " +Km_recorridos[i]);		
							
							if(Km_recorridos[i]>=km)
							{
								System.out.println("El jugador con ID: " + n + " ha terminado");
								ganadores ++;
								n_ganador ++;
								n_ganadores.add((double) n_ganador);
								
									switch(ganadores)
									{
									case 1: 
										System.out.println("El jugador con ID: " + n + " ocupa el primer puesto");
										p_puesto = corredores[i];
										break;
									case 2: 
										System.out.println("El jugador con ID: " + n + " ocupa el segundo puesto");
										s_puesto = corredores[i];
										break;
									case 3:
										System.out.println("El jugador con ID: " + n + " ocupa el tercer puesto");
										t_puesto = corredores[i];
										break;
									default: 	
										System.out.println("Aun no hay ganador");
									}
							}
							//se busca el jugador con el recorrido mas corto para que cuando se cumpla el limite se acabe el juego
							if(i==0)
							{
							min=Km_recorridos[i];
							}
							else if(min>Km_recorridos[i])
							{
							min=Km_recorridos[i];
							}
						}
					

					
					
					}

					System.out.println("minimo de kilometros recorridos es: " +min);
					for(int w=0; w<Cantidad_jugadores; w++) 
					{
					System.out.println("todos los kilometros recorridos son: " +Km_recorridos[w]);
					}
			}
			System.out.println("El juego ha terminado \n" );
		}
		
		
		public void podio()
		{	
			Scanner ingreso =new Scanner(System.in);
			String respuesta;
			System.out.println("¿Deseas conocer el podio de ganadores? \n");
			System.out.println("presione: \n S si desea \n N si no lo desea\n");
			//ingreso.nextLine();
			 respuesta = ingreso.next();
				if(respuesta.equals("s"))
				{
					System.out.println("Resultado posiciones de la carrera\n");
					System.out.println("Pirmer Lugar: " + p_puesto);
					System.out.println("Segundo Lugar: " + s_puesto);
					System.out.println("Tercer Lugar: " + t_puesto);
				}
				else if (respuesta.equals("n"))
				{
					System.out.println("HASTA PRONTO");
				}
		}
 }
	
 
