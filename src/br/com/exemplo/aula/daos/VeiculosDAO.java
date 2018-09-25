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
			
				veiculo.setId(rs.getLong("id"));
				veiculo.setIdModelo(rs.getLong("modelo_id"));
				veiculo.setIdAnoModelo(rs.getLong("ano_modelo_id"));
				veiculo.setIdMarca(rs.getLong("marca_id"));
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
            
            ResultSet rs = statement.executeQuery("select * from veiculos");
            
            while (rs.next()) {
            	
            	Veiculos veiculo = new Veiculos();
            	veiculo.setId(rs.getLong("id"));
            	veiculo.setIdModelo(rs.getLong("ano_modelo_id"));
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
  
    
    public void insert(Veiculos veiculos){

	    try {
	    	
	        PreparedStatement preparedStatement = connection.prepareStatement("insert into veiculos(ano_modelo_id, placa, ano_fabricacao, tipo, data_cadastro) values (?, ?, ?, ?, ? )");
	
	        preparedStatement.setLong(1, veiculos.getIdModelo());
	        preparedStatement.setString(2, veiculos.getPlaca());
	        preparedStatement.setString(3, veiculos.getAnoFabricacao());
	        preparedStatement.setString(4, veiculos.getTipo());
	        
	        
	        Date data = new Date();
	        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
	        
	        preparedStatement.setString(5, formatador.format( data ));
	        
	        preparedStatement.executeUpdate();
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    
    }

}
