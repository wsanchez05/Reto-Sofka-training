
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bd_conectar {
	private static final String controlador = "com.mysql.jdbc.Driver";
	private static final String Url ="jdbc:mysql://localhost:3306/concurso";
	private static final String usuario = "root";
	private static final String clave = "";
	Connection conexion = null;
	
	public Connection conectarBd() {
		
		try {
			Class.forName(controlador);
			conexion = DriverManager.getConnection(Url, usuario, clave);
			System.out.println("Conexión correcta");
		} catch (ClassNotFoundException e) {
			System.out.print("Error al cargar el controlador ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
		return conexion;
	}
	
	public static void  main(String[] args) {
		
				
	}  
}
