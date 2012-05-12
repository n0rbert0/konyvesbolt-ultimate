/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.sql;

import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import ultimate.konyvesbolt.Felhasznalo;
import ultimate.konyvesbolt.Hozzaszolas;
import ultimate.konyvesbolt.Konyv;
import ultimate.konyvesbolt.Rendeles;

/**
 *
 * @author lorda
 */
public class DAO {
    
    Map<Integer, Hozzaszolas> hozzaszolas = new HashMap<Integer, Hozzaszolas>();
    Map<Integer, Konyv> konyv = new HashMap<Integer, Konyv>();
    Map<Integer, Felhasznalo> felhasznalo = new HashMap<Integer, Felhasznalo>();
    Map<Integer, Rendeles> rendeles = new HashMap<Integer, Rendeles>();
    Map<Integer, Konyv> existsKonyv = new HashMap<Integer, Konyv>();
    
    // SQL lekérdezések
    private static final String SQL_addHozzaszolas =
		"insert into Hozzaszolas(h_id, tartalom, ho_datum) values " +
		"(?,?,?)";
    private static final String SQL_getHozzaszolas =
		"select * from hozzaszolas order by h_id";
    
    private static final String SQL_maxHozzaszolasId =
		"select max(h_id) AS max from hozzaszolas";
    
    private static final String SQL_addKonyv =
		"insert into Konyv(isbn, cim, ar, db, kep, leiras) values " +
		"(?,?,?,?,?,?)";
    private static final String SQL_addMufaj =
		"insert into mufaj(isbn, mufaj) values " +
		"(?,?)";
    private static final String SQL_addSzerzo =
		"insert into szerzo(isbn, szerzo) values " +
		"(?,?)";
    private static final String SQL_getKonyv =
		"select * from konyv order by isbn";
    private static final String SQL_getMufaj =
		"select * from mufaj where isbn=?";  
    private static final String SQL_getSzerzo =
		"select * from szerzo where isbn=?";
    private static final String SQL_maxKonyvIsbn =
		"select max(isbn) AS max from konyv";
    private static final String SQL_addFelhasznalo =
		"insert into Felhasznalo(f_id, f_nev, pass, e_mail, teljesnev, jog) values " +
		"(?,?,?,?,?,?)";
    private static final String SQL_addLakcim =
		"insert into lakcim(f_id, ir_szam, varos, utca, hazszam) values " +
		"(?,?,?,?,?)";
    private static final String SQL_getFelhasznalo =
		"select * from felhasznalo order by f_id";
    private static final String SQL_getLakcim=
		"select ir_szam, varos, utca, hazszam from lakcim where f_id=?";
    private static final String SQL_maxFelhasznaloId =
		"select max(f_id) AS max from felhasznalo";
    private static final String SQL_addRendeles =
		"insert into rendeles(r_id, f_id, datum, o_ar, isbn, db, kiszallitva) values " +
		"(?,?,?,?,?,?,?)";
    private static final String SQL_addR_cim =
		"insert into r_cim(r_id, ir_szam, varos, utca, hazszam) values " +
		"(?,?,?,?,?)";
    private static final String SQL_getRendeles =
		"select * from rendeles order by r_id";
    private static final String SQL_getR_cim=
		"select ir_szam, varos, utca, hazszam from r_cim where r_id=?";
    private static final String SQL_maxRendelesId =
		"select max(r_id) AS max from rendeles";
    private static final String SQL_belepes = 
                "select * from felhasznalo where f_nev=? AND pass=?";
    private static final String SQL_existsKonyv = 
                "select * from konyv,mufaj,szerzo " + ""
                + "where konyv.isbn=mufaj.isbn AND konyv.isbn=szerzo.isbn AND "
                + "(cim=? OR mufaj=? OR szerzo=?)";
    
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
  
  // hozzászolások lekérdezéséhez
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
                    h.setDate(rs.getString("ho_datum"));
                    
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
  
  // könyvek lekérdezéséhez
  public Map<Integer, Konyv> getKonyv(){
      
    Connection conn = null;
    Statement st = null;
    konyv.clear();
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		    
		st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL_getKonyv);    

                        while(rs.next()){
                            Konyv k = new Konyv();  
                            int isbn = rs.getInt("isbn");
                            k.setIsbn(isbn);
                            k.setCim(rs.getString("cim"));
                            k.setAr(rs.getInt("ar"));
                            k.setDb(rs.getInt("db"));
                            k.setMufaj(getMufaj(isbn));
                            k.setSzerzo(getSzerzo(isbn));
                            k.setKep(rs.getString("kep"));
                            k.setLeiras(rs.getString("leiras"));

                            konyv.put(k.getIsbn(), k);
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
    
        return konyv;
        
  }
  
  // mivel külön táblában van megvalósítva a műfaj, ezért ezt is le kell kérdezni
  public String getMufaj(int isbn){
      
    Connection conn = null;
    PreparedStatement pst = null;
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		    
		pst = conn.prepareStatement(SQL_getMufaj);
                int index = 1;

                pst.setInt(index++, isbn);
                
                ResultSet rs = pst.executeQuery(); 
                if (rs.next())
		 return rs.getString("mufaj");

	} catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
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
    
        return null;
        
  }
  
  // mivel külön táblában van megvalósítva a szerző, ezért ezt is le kell kérdezni
  public String getSzerzo(int isbn){
      
    Connection conn = null;
    PreparedStatement pst = null;
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		    
		pst = conn.prepareStatement(SQL_getSzerzo);
                int index = 1;

                pst.setInt(index++, isbn);
                
                ResultSet rs = pst.executeQuery(); 
                if (rs.next())
		 return rs.getString("szerzo");

	} catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
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
    
        return null;
        
  }
  
  //felhasználó lekérdezéséhez
  public Map<Integer, Felhasznalo> getFelhasznalo(){
      
    Connection conn = null;
    Statement st = null;
    felhasznalo.clear();
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		    
		st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL_getFelhasznalo);    

                        while(rs.next()){
                            Felhasznalo f = new Felhasznalo();  
                            f.setF_id(rs.getInt("f_id"));
                            f.setF_nev(rs.getString("f_nev"));
                            f.setPass(rs.getString("pass"));
                            f.setEmail(rs.getString("e_mail"));
                            f.setTeljesnev(rs.getString("teljesnev"));
                            f.setJog(rs.getInt("jog"));
                            f.setIrSzam(Integer.parseInt(getLakcim(rs.getInt("f_id"),1)));
                            f.setVaros(getLakcim(rs.getInt("f_id"),2));
                            f.setUtca(getLakcim(rs.getInt("f_id"),3));
                            f.setHazszam(Integer.parseInt(getLakcim(rs.getInt("f_id"),4)));

                            felhasznalo.put(f.getF_id(), f);
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
    
        return felhasznalo;
        
  }
  
  // mivel külön táblában van megvalósítva a lakcím, ezért ezt is le kell kérdezni
  public String getLakcim(int f_id, int i){
      
    Connection conn = null;
    PreparedStatement pst = null;
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		    
		pst = conn.prepareStatement(SQL_getLakcim);
                int index = 1;

                pst.setInt(index++, f_id);
                
                ResultSet rs = pst.executeQuery(); 
                
                if (rs.next()){
                   //a lkacímnek több, mint 1 oszlopa van, ezért
                   //egy itt döntöm el, hogy melyik kell pontosan
                   switch(i){
                       case 1:  return Integer.toString(rs.getInt("ir_szam")); 
                       case 2:  return rs.getString("varos"); 
                       case 3:  return rs.getString("utca");
                       case 4:  return Integer.toString(rs.getInt("hazszam"));    
                   }        
                }        
                    
	} catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
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
    
        return null;
        
  }
  
  //rendeles tabla lekerdezese
  public Map<Integer, Rendeles> getRendeles(){
      
    Connection conn = null;
    Statement st = null;
    rendeles.clear();
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		    
		st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL_getRendeles);    

                        while(rs.next()){
                            Rendeles r = new Rendeles();  
                            r.setR_id(rs.getInt("r_id"));
                            r.setF_id(rs.getInt("f_id"));
                            r.setDatum(rs.getString("datum"));
                            r.setAr(rs.getInt("o_ar"));
                            r.setIsbn(rs.getInt("isbn"));
                            r.setDb(rs.getInt("db"));
                            if(rs.getInt("kiszallitva") == 1)
                            r.setKiszalitva(true);
                            else
                            r.setKiszalitva(false);
                            r.setIrSzam(Integer.parseInt(getR_cim(rs.getInt("r_id"),1)));
                            r.setVaros(getR_cim(rs.getInt("r_id"),2));
                            r.setUtca(getR_cim(rs.getInt("r_id"),3));
                            r.setHazszam(Integer.parseInt(getR_cim(rs.getInt("r_id"),4)));

                            rendeles.put(r.getR_id(), r);
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
    
        return rendeles;
        
  }
  
  // mivel külön táblában van megvalósítva a lakcím, ezért ezt is le kell kérdezni
  public String getR_cim(int r_id, int i){
      
    Connection conn = null;
    PreparedStatement pst = null;
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		    
		pst = conn.prepareStatement(SQL_getR_cim);
                int index = 1;

                pst.setInt(index++, r_id);
                
                ResultSet rs = pst.executeQuery(); 
                
                if (rs.next()){
                   //a lkacímnek több, mint 1 oszlopa van, ezért
                   //egy itt döntöm el, hogy melyik kell pontosan
                   switch(i){
                       case 1:  return Integer.toString(rs.getInt("ir_szam")); 
                       case 2:  return rs.getString("varos"); 
                       case 3:  return rs.getString("utca");
                       case 4:  return Integer.toString(rs.getInt("hazszam"));    
                   }        
                }        
                    
	} catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
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
    
        return null;
        
  }
  
  //hozzászólás felvétele az adatbázisba
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
                pst.setString(index++, aktIdo());
		
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
  
  //könyv felvétele az adatbázisba
  public boolean addKonyv(Konyv k) {

    Connection conn = null;
    PreparedStatement pst = null;
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }   
            
                //konyv tabla feltoltese
		pst = conn.prepareStatement(SQL_addKonyv);
                       
		int index = 1;
                       
                pst.setInt(index++, k.getIsbn());
		pst.setString(index++, k.getCim());
                pst.setInt(index++, k.getAr());
                pst.setInt(index++, k.getDb());    
                
                //mivel nem akarom külön metódust meghívni a
                //mufaj es a szerzo tabla feltoltesere ezert itt csinalom
                
                //mufaj tabla feltoltese
                addMufaj(k.getMufaj(), k.getIsbn());
                //szerzo tabla feltoltese
                addSzerzo(k.getSzerzo(), k.getIsbn());  
                
                pst.setString(index++, k.getKep());
                pst.setString(index++, k.getLeiras());
                
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
  
  //műfaj felvétele az adatbázisba az addKonyv-on belul hivom meg
   public boolean addMufaj(String mufaj, int isbn) {
      
    Connection conn = null;
    PreparedStatement pst = null;
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }   
                //mufaj tabla feltoltese
                pst = conn.prepareStatement(SQL_addMufaj);
                       
		int index = 1;
                
                pst.setInt(index++, isbn);
		pst.setString(index++, mufaj);

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
   
   //szerző felvétele az adatbázisba az addKonyv-on belul hivom meg
   public boolean addSzerzo(String szerzo, int isbn) {
      
    Connection conn = null;
    PreparedStatement pst = null;
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }   
                //szerzo tabla feltoltese
                pst = conn.prepareStatement(SQL_addSzerzo);
                       
		int index = 1;
                       
                pst.setInt(index++, isbn);
		pst.setString(index++, szerzo);

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
   
   //felhasznalo felvetele az adatbazisba
   public boolean addFelhasznalo(Felhasznalo f) {

    Connection conn = null;
    PreparedStatement pst = null;
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }   
            
                //felhasznalo tabla feltoltese
		pst = conn.prepareStatement(SQL_addFelhasznalo);
                       
		int index = 1;
                       
                pst.setInt(index++, f.getF_id());
		pst.setString(index++, f.getF_nev());
                pst.setString(index++, f.getPass());
                pst.setString(index++, f.getEmail());
                pst.setString(index++, f.getTeljesnev());
                pst.setInt(index++, f.getJog());   
                
                //mivel nem akarom külön metódust meghívni a
                //lakcim tabla feltoltesere ezert itt csinalom
                
                //lakcim tabla feltoltese
                addLakcim(f.getF_id(), f.getIrSzam(), f.getVaros(), f.getUtca(), f.getHazszam());
                
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
   
   //lakcim feltoltese az adatbazisba, az addFelhasznalo-n belul hivom meg
   public boolean addLakcim(int f_id, int irSzam, String varos, String utca, int hazszam) {

    Connection conn = null;
    PreparedStatement pst = null;
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }   
            
                //felhasznalo tabla feltoltese
		pst = conn.prepareStatement(SQL_addLakcim);
                       
		int index = 1;
                       
                pst.setInt(index++, f_id);
		pst.setInt(index++, irSzam);
                pst.setString(index++, varos);
                pst.setString(index++, utca);
                pst.setInt(index++, hazszam);
                                
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
   
   public boolean addRendeles(Rendeles r) {

    Connection conn = null;
    PreparedStatement pst = null;
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }   
            
                //felhasznalo tabla feltoltese
		pst = conn.prepareStatement(SQL_addRendeles);
		int index = 1;
                       
                pst.setInt(index++, r.getR_id());
                pst.setInt(index++, r.getF_id());
                pst.setString(index++, aktIdo());
                pst.setInt(index++, r.getAr());
                pst.setInt(index++, r.getIsbn());
                pst.setInt(index++, r.getDb());
		
                if(r.isKiszalitva()==true)
                pst.setInt(index++, 1);
                else
                pst.setInt(index++, 0);    
               
                //mivel nem akarom külön metódust meghívni a
                //r_cim tabla feltoltesere ezert itt csinalom
                
                //r_cim tabla feltoltese
                addR_cim(r.getR_id(), r.getIrSzam(), r.getVaros(), r.getUtca(), r.getHazszam());
                
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
   
   //r_cim tabla feltoltese, a addRendeles metodusba hivom meg
   public boolean addR_cim(int r_id, int irSzam, String varos, String utca, int hazszam) {

    Connection conn = null;
    PreparedStatement pst = null;
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }   
            
                //felhasznalo tabla feltoltese
		pst = conn.prepareStatement(SQL_addR_cim);
                       
		int index = 1;
                       
                pst.setInt(index++, r_id);
		pst.setInt(index++, irSzam);
                pst.setString(index++, varos);
                pst.setString(index++, utca);
                pst.setInt(index++, hazszam);
                                
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
  
  //az autoincremet miatt kell ez a metodus
  // lekerdezi, hogy mi volt a legnagyobb sorszam
  public int maxHozzaszolasId(){
    Connection conn = null;
    PreparedStatement pst = null;
    
    int max = 0;
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		
		pst = conn.prepareStatement(SQL_maxHozzaszolasId);
                        
		ResultSet rs = pst.executeQuery();
                
		if(rs.next()){
                    max = rs.getInt("max");
		}
                
	} catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
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
    
        return max;
  }
  
  //az autoincremet miatt kell ez a metodus
  // lekerdezi, hogy mi volt a legnagyobb sorszam
  //nincs minden konyvhoz kulon isbn, az azonos konyvek
  //azonos isbn ala tartoznak
  public int maxKonyvIsbn(){
    Connection conn = null;
    PreparedStatement pst = null;
    
    int max = 0;
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		
		pst = conn.prepareStatement(SQL_maxKonyvIsbn);
                        
		ResultSet rs = pst.executeQuery();
                
		if(rs.next()){
                    max = rs.getInt("max");
		}
                
	} catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
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
    
        return max;
  }
  
  //az autoincremet miatt kell ez a metodus
  // lekerdezi, hogy mi volt a legnagyobb sorszam
  public int maxFelhasznaloId(){
    Connection conn = null;
    PreparedStatement pst = null;
    
    int max = 0;
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		
		pst = conn.prepareStatement(SQL_maxFelhasznaloId);
                        
		ResultSet rs = pst.executeQuery();
                
		if(rs.next()){
                    max = rs.getInt("max");
		}
                
	} catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
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
    
        return max;
  }
  
  //az autoincremet miatt kell ez a metodus
  // lekerdezi, hogy mi volt a legnagyobb sorszam
  public int maxRendelesId(){
    Connection conn = null;
    PreparedStatement pst = null;
    
    int max = 0;
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		
		pst = conn.prepareStatement(SQL_maxRendelesId);
                        
		ResultSet rs = pst.executeQuery();
                
		if(rs.next()){
                    max = rs.getInt("max");
		}
                
	} catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
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
    
        return max;
  }
  
  
  public boolean belepes(String f_nev, String pass){
      
    Connection conn = null;
    PreparedStatement pst = null;
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		    
		pst = conn.prepareStatement(SQL_belepes);
                String[] adatok = new String[2];
                int index = 1;

                pst.setString(index++, f_nev);
                pst.setString(index++, pass);
                
                ResultSet rs = pst.executeQuery(); 
                
                if (rs.next()){
                       adatok[0] = rs.getString("f_nev"); 
                       adatok[1] = rs.getString("pass");
                       return true;
                }       
                    
	} catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
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
    
        return false;
        
  }
  
  public Map<Integer, Konyv> existsKonyv(String keres){
      
    Connection conn = null;
    PreparedStatement pst = null;
    existsKonyv.clear();
			
	try {
            try {
        	conn = DriverManager.getConnection(url,"root","root");
            } catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
            }    
		    
		pst = conn.prepareStatement(SQL_existsKonyv);
                int index = 1;

                pst.setString(index++, keres);
                pst.setString(index++, keres);
                pst.setString(index++, keres);

                ResultSet rs = pst.executeQuery(); 
                
                while(rs.next()){
                            Konyv k = new Konyv();  
                            k.setIsbn(rs.getInt("isbn"));
                            k.setCim(rs.getString("cim"));
                            k.setAr(rs.getInt("ar"));
                            k.setDb(rs.getInt("db"));
                            k.setMufaj(rs.getString("mufaj"));
                            k.setSzerzo(rs.getString("szerzo"));
                            k.setKep(rs.getString("kep"));
                            k.setLeiras(rs.getString("leiras"));
                             
                            existsKonyv.put(k.getIsbn(), k);
                        }       
                    
	} catch (SQLException e) {
		System.err.println("Nem jött létre az SQL kapcsolat!");
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
    
        return existsKonyv;
        
  }
  
  //----------teszteleshez-----------
  public void testHozzaszolas(String hsz){
      
          Hozzaszolas h = new Hozzaszolas();
          
            h.setH_id(maxHozzaszolasId()+1);
            h.setTartalom(hsz);
            if(addHozzaszolas(h) == false) System.err.println("Nem sikerült hozza adni a hozzaszolast, valoszinuleg mar letezik ilyen!");
            else
            addHozzaszolas(h);
      
      String test = getHozzaszolas().toString();
      System.out.println(test);
      
  }
  
  public boolean testKonyv(String cim, int ar, int db, String mufaj, String szerzo, String kep, String leiras){
      
          Konyv k = new Konyv();
          
            k.setIsbn(maxKonyvIsbn()+1);
            k.setCim(cim);
            k.setAr(ar);
            k.setDb(db);
            k.setMufaj(mufaj);
            k.setSzerzo(szerzo);
            k.setKep(kep);
            k.setLeiras(leiras);
            if(addKonyv(k) == false) return false;// System.err.println("Nem sikerült hozza adni a könyvet, valoszinuleg mar letezik ilyen!");
            else{
            addKonyv(k);
            return true;
            }
      
     /* String test = getKonyv().toString();
      System.out.println(test);
      */
  }
  
  public boolean testFelhasznalo(String f_nev, String pass, String email, String teljesnev, int jog, int irszam, String varos, String utca, int hazszam){
      
          Felhasznalo f = new Felhasznalo();
          
           f.setF_id(maxFelhasznaloId()+1);
           f.setF_nev(f_nev);
           f.setPass(pass);
           f.setEmail(email);
           f.setTeljesnev(teljesnev);
           f.setJog(jog);
           f.setIrSzam(irszam);
           f.setVaros(varos);
           f.setUtca(utca);
           f.setHazszam(hazszam);
            if(addFelhasznalo(f) == false) return false; //System.err.println("Nem sikerült hozza adni a felhasználót, valoszinuleg mar letezik ilyen!");
            else{
            addFelhasznalo(f);
            return true;
                    }
      
      //String test = getFelhasznalo().toString();
      //System.out.println(test);
      
  }
  
   public void testRendeles(int f_id, int isbn, int db, int ar,
           int irSzam, String varos, String utca, int hazszam, boolean kiszallitva){
      
          Rendeles r = new Rendeles();
          
           r.setR_id(maxRendelesId()+1);
           r.setF_id(f_id);
           r.setIsbn(isbn);
           r.setDb(db);
           r.setAr(ar);
           r.setIrSzam(irSzam);
           r.setVaros(varos);
           r.setUtca(utca);
           r.setHazszam(hazszam);
           r.setKiszalitva(kiszallitva);
            if(addRendeles(r) == false) System.err.println("Nem sikerült hozza adni a hozzaszolast, valoszinuleg mar letezik ilyen!");
            else
            addRendeles(r);
      
      String test = getRendeles().toString();
      System.out.println(test);
      
  }
  
  //aktualis rendszerido
  
  public String aktIdo(){
        Calendar actDate = Calendar.getInstance();
        String aktIdo = actDate.get(Calendar.YEAR) + "." +
                        (actDate.get(Calendar.MONTH)+1) + "." +
                         actDate.get(Calendar.DATE) + ", " + 
                         actDate.get(Calendar.HOUR_OF_DAY) + ":" + 
                         actDate.get(Calendar.MINUTE);
        return aktIdo;      
  
  }
  

}