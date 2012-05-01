/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.sql;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import ultimate.konyvesbolt.Hozzaszolas;

/**
 *
 * @author lorda
 */
public class DAO {
    
    Map<Integer, Hozzaszolas> hozzaszolas = new HashMap<Integer, Hozzaszolas>();
    
    private static final String SQL_addHozzaszolas =
		"insert into Hozzaszolas(h_id, tartalom) values " +
		"(?,?)";
    private static final String SQL_getHozzaszolas =
		"select * from hozzaszolas order by h_id";
    
  String url = "jdbc:oracle:thin:@//localhost:1521/xe";
  
  public DAO(){
  //konstruktorban betöltjuk a JDBC drivert
      try{  
    Class.forName("oracle.jdbc.driver.OracleDriver");
    }
    catch (ClassNotFoundException e){
        System.err.println("Nem sikerült betölteni a JDBC drivert!");
    }
          
  }
    
  public Map<Integer, Hozzaszolas> getHozzaszolas(){
      
    Connection conn = null;
    Statement st = null;
    hozzaszolas.clear();
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		
		st = conn.createStatement();
                        
		ResultSet rs = st.executeQuery(SQL_getHozzaszolas);
                
		while(rs.next()){
                    Hozzaszolas h = new Hozzaszolas();
                    h.setH_id(rs.getInt("h_id"));
                    h.setTartalom(rs.getString("tartalom"));
                    
                    hozzaszolas.put(h.getH_id(), h);
		}
	} catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
	} finally {
		try {
                    if(st != null)
        		st.close();
		} catch (SQLException e) {
			e.printStackTrace();
                }
		try {
                    if(conn != null)
			conn.close();
		} catch (SQLException e) {
			System.err.println("Nem sikerült lezárni az SQL kapcsolatot!");
		}
	}
    
        return hozzaszolas;
        
  }
  
  public boolean addHozzaszolas(Hozzaszolas h) {
      
    Connection conn = null;
    PreparedStatement pst = null;
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		pst = conn.prepareStatement(SQL_addHozzaszolas);
                       
		int index = 1;
                       
                pst.setInt(index++, h.getH_id());
		pst.setString(index++, h.getTartalom());
		
		pst.executeUpdate();
                       
	} catch (SQLException e) {
                //System.err.println("SQL injekciós hiba");
                //return false;
	} finally {
		
            try {
		if(pst != null)
                    pst.close();
            } catch (SQLException e) {
		e.printStackTrace();
            }
            try {
		if(conn != null)
		conn.close();
            } catch (SQLException e) {
		System.err.println("Nem sikerült lezárni az SQL kapcsolatot!");
            }
	}
        return true;
    }
  
  public void testHozzaszolas(){
      for(int i = 1; i < 21; i++){
          Hozzaszolas h = new Hozzaszolas();
          h.setH_id(i);
          h.setTartalom(i+". tesz hsz");
          if(addHozzaszolas(h) == false) System.err.println("Nem sikerült hozza adni a hozzaszolast, valoszinuleg mar letezik ilyen!");
          else
          addHozzaszolas(h);
      }
      
      String test = getHozzaszolas().toString();
      System.out.println(test);
      
  }

}