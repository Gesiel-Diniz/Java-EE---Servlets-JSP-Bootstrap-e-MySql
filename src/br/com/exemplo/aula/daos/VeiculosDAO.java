package br.com.exemplo.aula.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.exemplo.aula.conexao.ConexaoMySQL;
import br.com.exemplo.aula.entidades.AnoModelos;
import br.com.exemplo.aula.entidades.Marcas;
import br.com.exemplo.aula.entidades.Modelos;
import br.com.exemplo.aula.entidades.Veiculos;


public class VeiculosDAO {
	
	private Connection connection;

    public VeiculosDAO() {
    	connection = ConexaoMySQL.getConexaoMySQL();
    }
    
    
	public Veiculos buscaVeiculo(Long id) {
		
		Veiculos veiculo = new Veiculos();

		try {

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("SELECT * FROM veiculos WHERE id = " + id + " Limit 1;");
			
			while (rs.next()) {
				
				Marcas marca = new Marcas();
            	marca.setId(Long.parseLong(rs.getString("marca_id")));
            	
            	Modelos modelo = new Modelos();
        		modelo.setId(rs.getString("modelo_id"));
        		
        		AnoModelos anoModelo = new AnoModelos();
        		anoModelo.setId(Long.parseLong(rs.getString("ano_modelo_id")));
			
				veiculo.setId(rs.getLong("id"));
				veiculo.setIdModelo(modelo);
				veiculo.setIdAnoModelo(anoModelo);
				veiculo.setIdMarca(marca);
				veiculo.setPlaca(rs.getString("placa"));
				veiculo.setAnoFabricacao(rs.getString("ano_fabricacao"));
				veiculo.setTipo(rs.getString("tipo"));
				veiculo.setDataCadastro(rs.getString("data_cadastro"));
			
			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return veiculo;

	}
    
    public void delete(Veiculos veiculos){

    	 try {

             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM veiculos WHERE id = ?;");
             preparedStatement.setLong(1, veiculos.getId());
             
             preparedStatement.executeUpdate();
             
         } catch (SQLException e) {
         	
             e.printStackTrace();
             
         }
    
    }
    
 
    public List<Veiculos> listaVeiculos() {
    	
        List<Veiculos> veiculos = new ArrayList<Veiculos>();
        
        try {
        	
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM veiculos as v LEFT JOIN marca as m on m.id = v.marca_id LEFT JOIN modelo as mo on mo.id = v.modelo_id LEFT JOIN ano_modelo as am on am.id = v.ano_modelo_id order by data_cadastro DESC");
            
            while (rs.next()) {
            	
            	Marcas marca = new Marcas();
            	marca.setId(Long.parseLong(rs.getString("ano_modelo_id")));
            	marca.setNome(rs.getString("m.nome"));
            	
            	Modelos modelo = new Modelos();
        		modelo.setId(rs.getString("ano_modelo_id"));
        		modelo.setNome(rs.getString("mo.nome"));
        		
        		AnoModelos anoModelo = new AnoModelos();
        		anoModelo.setId(Long.parseLong(rs.getString("ano_modelo_id")));
        		anoModelo.setNome(rs.getString("am.nome"));
            	
            	Veiculos veiculo = new Veiculos();
            	veiculo.setId(rs.getLong("id"));
            	veiculo.setIdAnoModelo(anoModelo);
            	veiculo.setIdModelo(modelo);
            	veiculo.setIdMarca(marca);
            	veiculo.setPlaca(rs.getString("placa"));
            	veiculo.setAnoFabricacao(rs.getString("ano_fabricacao"));
            	veiculo.setTipo(rs.getString("tipo"));
            	
				veiculo.setDataCadastro(rs.getString("data_cadastro"));
				
               
            	veiculos.add(veiculo);
                
            }
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        }
	
        return veiculos;
        
    }
  
    
    public boolean insert(Veiculos veiculos){

    	boolean sucesso = true;
    	
	    try {
	    	
	        PreparedStatement preparedStatement = connection.prepareStatement("insert into veiculos( marca_id, modelo_id, ano_modelo_id, placa, ano_fabricacao, tipo, data_cadastro) values (?, ?, ?, ?, ?, ?, ? )");
	
	        preparedStatement.setLong(1, veiculos.getIdMarca().getId());
	        preparedStatement.setString(2, veiculos.getIdModelo().getId());
	        preparedStatement.setLong(3, veiculos.getIdAnoModelo().getId());
	        preparedStatement.setString(4, veiculos.getPlaca());
	        preparedStatement.setString(5, veiculos.getAnoFabricacao());
	        preparedStatement.setString(6, veiculos.getTipo());
	        
	        Date data = new Date();
	        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
	        
	        preparedStatement.setString(7, formatador.format( data ));
	        
	        preparedStatement.executeUpdate();
	
	    } catch (SQLException e) {
	    	sucesso = false;
	        e.printStackTrace();
	    }
	    
	    return sucesso;
    
    }
    
    
    public boolean edita(Veiculos veiculos){
    	
    	boolean sucesso = true;
    	
	    try {
	    	
	        PreparedStatement preparedStatement = connection.prepareStatement("update veiculos set marca_id = ?, modelo_id= ?, ano_modelo_id= ?, placa= ?, ano_fabricacao= ?, tipo= ? WHERE id = ?");
	
	        preparedStatement.setLong(1, veiculos.getIdMarca().getId());
	        preparedStatement.setString(2, veiculos.getIdModelo().getId());
	        preparedStatement.setLong(3, veiculos.getIdAnoModelo().getId());
	        preparedStatement.setString(4, veiculos.getPlaca());
	        preparedStatement.setString(5, veiculos.getAnoFabricacao());
	        preparedStatement.setString(6, veiculos.getTipo());
	        preparedStatement.setLong(7, veiculos.getId());
	        
	        preparedStatement.executeUpdate();

	    } catch (SQLException e) {
	    	
	    	sucesso = false;
	    	
	        e.printStackTrace();
	        
	    }
	    
	    return sucesso;
    
    }

}
