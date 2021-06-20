import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class configuracion 
{
	
	private int  Cantidad_jugadores;
	private int  km;
	private double  N_carro;
	private String  Nombre_Jugador;
	private double  Carril;
	
	
	// Se crea un Metodo para guardar los valores ingresados
	public int guardar_jugador (int id, String Name, String apellido ) {
		int resultado = 0;
		Bd_conectar BD =  new Bd_conectar();
		BD.conectarBd(); 
		String SSQL = "INTERT TO jugador(id, name, apellido) " + "values (?,?,?)";
		
		try {
			
			java.sql.PreparedStatement Psql = BD.conexion.prepareStatement(SSQL);
			Psql.setInt(1, id);
			Psql.setString(1, Name);
			Psql.setString(1, apellido);
			
			resultado = Psql.executeUpdate();
			Psql.close();
			
			
		}catch (Exception e) {
			
			JOptionPane.showConfirmDialog(null, "Error al almacenar datos:\n" + e, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
			
		} 
		return resultado;
	}
	
	//Se crea un metodo para pedir la información de juego
	
	public void info()
	{
		Scanner teclado = new Scanner(System.in);
		int x;
		System.out.println("Por favor ingrese el numero de corredores\n");
		x= teclado.nextInt();
		System.out.println("Por favor la distancia en kilomentros \n");
		km = teclado.nextInt();
		Cantidad_jugadores =x;
		String [][] corredores = new String [x][2];
		List<Double> Carriles = new ArrayList<Double>();
		List<Double> Carros = new ArrayList<Double>();
		for (int i=0; i<x;i++)
		{
			int n= i+1;
			System.out.println("Datos del corredor con ID N°: " +n);
			
					System.out.println("Por favor ingrese el Nombre de corredor Nº: " +n);
					Nombre_Jugador= teclado.next();
					corredores[i][0]=Nombre_Jugador;
					
					System.out.println("Por favor ingrese el Apellido del corredor Nº: " +n);
					corredores[i][1]= teclado.next();	
					
					Carril = Math.floor(Math.random()*Cantidad_jugadores+1);
					boolean existe_Carril = Carriles.contains(Carril);
					N_carro = Math.floor(Math.random()*Cantidad_jugadores+1);
					boolean existe_Carro = Carros.contains(N_carro);
					
					while (existe_Carril)
					{
						Carril = Math.floor(Math.random()*Cantidad_jugadores+1);
					    existe_Carril = Carriles.contains(Carril);
					}
					while (existe_Carro)
					{
						N_carro = Math.floor(Math.random()*Cantidad_jugadores+1);
					    existe_Carro = Carros.contains(N_carro);
					}
					
					Carriles.add(Carril);
					Carros.add(N_carro);
					
		}
		
		for(int i=0; i<x; i++) 
		{
			int n= i+1;
			
			System.out.println("Nombre del corredor con ID N° " +n+ ":\n "+ corredores[i][0]  +"  "  +corredores[i][1]);
			System.out.println("El carril del jugador N: " + n + " es: \n " +Carriles.get(i));	
			System.out.println("El numero de  carro del jugador N: " + n + " es: \n " +Carros.get(i));	
			
		}
		
		}
		

	}
 
