package it.polito.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AnagrammaDAO {
	
	public boolean isCorrect(String anagramma)
	{
		boolean trovato=false;
		String sql="SELECT nome "
				+ "FROM parola "
				+ "WHERE nome=?";
		try {
			Connection conn= DBConnect.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setString(1, anagramma);
			ResultSet res= st.executeQuery();
			if(res.first()) {
				trovato=true;
			}
			st.close();
			res.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return trovato;
	}

}
