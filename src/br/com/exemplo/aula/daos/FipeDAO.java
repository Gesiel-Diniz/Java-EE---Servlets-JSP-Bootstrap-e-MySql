package br.com.exemplo.aula.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.exemplo.aula.conexao.ConexaoMySQL;
import br.com.exemplo.aula.entidades.AnoModelos;
import br.com.exemplo.aula.entidades.Marcas;
import br.com.exemplo.aula.entidades.Modelos;


public class FipeDAO {
	
	private Connection connection;

    public FipeDAO() {
    	connection = ConexaoMySQL.getConexaoMySQL();
    }
    
    
    public List<AnoModelos> buscaAnoModelos(String idModelo) {
    	
        List<AnoModelos> anoModelos = new ArrayList<AnoModelos>();
        
        try {
        	
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM ano_modelo WHERE modelo = '"+idModelo+"' Order by nome ASC");
            
            while (rs.next()) {
            	
            	AnoModelos anoModelo = new AnoModelos();
            	
            	anoModelo.setId(rs.getLong("id"));
            	anoModelo.setNome(rs.getString("nome"));
            	anoModelo.setModelo(rs.getString("modelo"));
            	anoModelo.setValor(rs.getFloat("valor"));
            	anoModelo.setTipo(rs.getString("tipo"));
               
            	anoModelos.add(anoModelo);
                
            }
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        }
	
        return anoModelos;
        
    }
    
    
    public List<Modelos> buscaModelo(Long idMarca) {
    	
        List<Modelos> modelos = new ArrayList<Modelos>();
        
        try {
        	
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM modelo WHERE marca = "+idMarca+" order by nome ASC");
            
            while (rs.next()) {
            	
            	Modelos modelo = new Modelos();
            	
            	modelo.setId(rs.getString("id"));
            	modelo.setNome(rs.getString("nome"));
            	modelo.setMarca(rs.getInt("marca"));
            	modelo.setTipo(rs.getString("tipo"));
               
				modelos.add(modelo);
                
            }
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        }
	
        return modelos;
        
    }
    
 
    public List<Marcas> listaMarcas() {
    	
        List<Marcas> marcas = new ArrayList<Marcas>();
        
        try {
        	
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery("select * from marca order by nome ASC");
            
            while (rs.next()) {
            	
            	Marcas marca = new Marcas();
            	
				marca.setId(rs.getLong("id"));
				marca.setNome(rs.getString("nome"));
				marca.setTipo(rs.getString("tipo"));

				marcas.add(marca);
                
            }
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        }

        return marcas;
        
    }

}
