package br.com.exemplo.aula.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
	
	public static String status = "Não conectou.....";
	
	
	public ConexaoMySQL(){
	}
	
	private static Connection connection = null;
	
	public static java.sql.Connection getConexaoMySQL(){

		try {
			
			String driverName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverName);
			
			String serverName = "127.0.0.1";
			String mydatabase = "aula_veiculo";
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase+"?useTimezone=true&serverTimezone=UTC";
			String username = "root";
            String password = "";
 
            connection = DriverManager.getConnection(url, username, password);
            
            if (connection != null){
            	
                status = ("STATUS--->Conectado com sucesso!");
 
            } else {
 
                status = ("STATUS--->Não foi possivel realizar conexão");
 
            }
 
  
 
            return connection;
			
		} catch (ClassNotFoundException e) {

			System.out.println("O driver expecificado nao foi encontrado.");
			 
            return null;
			
		}catch (SQLException e) {
			
			System.out.println("Nao foi possivel conectar ao Banco de Dados."+e);
			 
            return null;
		}
		
	}
	
	public static String statusConection() {
		 
        return status;
 
    }
	
	public static boolean FecharConexao() {

		try {

			ConexaoMySQL.getConexaoMySQL().close();

			return true;

		} catch (SQLException e) {

			return false;

		}

	}

	public static java.sql.Connection ReiniciarConexao() {

		FecharConexao();

		return ConexaoMySQL.getConexaoMySQL();

	}
	

}
