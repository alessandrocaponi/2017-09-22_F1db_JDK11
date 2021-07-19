package it.polito.tdp.formulaone.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.formulaone.model.EdgeAndWeight;
import it.polito.tdp.formulaone.model.Race;
import it.polito.tdp.formulaone.model.Season;

public class FormulaOneDAO {
	
	public List<EdgeAndWeight> getEdgeAndWeight(int year) {
		String sql = "select r1.raceId as id1, r2.raceId as id2, count (distinct r1.driverId) as peso "
				+ "from results r1, results r2, races ra1, races ra2 "
				+ "where r1.raceId>r2.raceId and r1.driverId = r2.driverId "
				+ "and ra1.year = ra2.year and ra1.year =? and r1.raceId = ra1.raceId "
				+ "and r2.raceId = ra2.raceId "
				+ "group by r1.raceId, r2.raceId ";
				 List<EdgeAndWeight> result = new ArrayList<>();
				 Connection conn = DBConnect.getConnection();
					try {
						
						PreparedStatement st = conn.prepareStatement(sql);
						st.setInt(1, year);
						ResultSet rs = st.executeQuery();
						
						while (rs.next()) {
							result.add(new EdgeAndWeight(rs.getInt("id1"), rs.getInt("id2"), rs.getInt("peso")));
						}
						conn.close();
						return result;

					} catch (SQLException e) {
						e.printStackTrace();
						return null;
					}
				 
		
		
	}
	
	public List<Race> getRaceSeason (int year) {
	
		String sql = "select r.raceId as id, r.name as n "
				+ "from races r "
				+ "where r.year =? ";
		List<Race> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();
		try {
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, year);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				result.add(new Race(rs.getInt("id"), rs.getString("n")));
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
		
	

	public List<Season> getAllSeasons() {
		String sql = "SELECT year, url FROM seasons ORDER BY year";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Season> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Season(rs.getInt("year"), rs.getString("url")));
			}
			conn.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}

