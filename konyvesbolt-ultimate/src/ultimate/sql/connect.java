/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.sql;

import java.sql.*;

/**
 *
 * @author lorda
 */
public class connect {
    
    private static final String SQL_addHozzaszolas =
		"insert into Hozzaszolas (h_id, tartalom) values " +
		"(?,?)";
    
    String url = "jdbc:oracle:thin:@//localhost:1521/xe";
    
  public void listazas(){
      
    try{  
    Class.forName("oracle.jdbc.driver.OracleDriver");
    }
    catch (ClassNotFoundException e){
        e.printStackTrace();
    }
    
    Connection conn = null;
    Statement st = null;
			
	try {
        	conn = DriverManager.getConnection(url,"root","root");
		
		st = conn.createStatement();
                        
		ResultSet rs = st.executeQuery("select * from hozzaszolas");
			
		while(rs.next()){
                    System.out.print(rs.getString("h_id")+"  ");
                    System.out.println(rs.getString("tartalom"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
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
			e.printStackTrace();
		}
	}
    
  }
  
  public void beszuras() {
      
    Connection conn = null;
    PreparedStatement pst = null;
	try {
		conn = DriverManager.getConnection(url,"root","root");
		pst = conn.prepareStatement(SQL_addHozzaszolas);
                       
		int index = 1;
                       
                pst.setInt(index++, 11);
		pst.setString(index++, "adfdsv");
		
		pst.executeUpdate();
                       
	} catch (SQLException e) {
                System.err.println("SQL injekciós hiba");
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
		e.printStackTrace();
            }
	}
  
}

}


