package org.uv.tpcs_practica01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {    
    private static ConexionDB cx=null;
    String bd="empleados";
    String ip="localhost";
    String puerto="5432";
    String cadena="jdbc:postgresql://"+ip+":"+puerto+"/"+bd;
    
    public static ConexionDB getInstance(){
        if(cx==null)
            cx=new ConexionDB();
        return cx;
    }
    
    private Connection con = null;
    
    private ConexionDB() {
//        try {
//            con=DriverManager.getConnection(cadena, "postgres", "pass");
//            Logger.getLogger(ConexionDB.class.getName()).log(Level.INFO,"se conecto");
//        } catch (SQLException ex) {
//            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE,"error1", ex);
//        }
//    }
   try {
            String url= "jdbc:mysql://localhost:3306/CRUD?characterEncoding=UTF-8"; // crear database CRUD
            con =DriverManager.getConnection(url, "crud", "password");  //usuario sql con privilegios y contraseña
            Logger.getLogger(ConexionDB.class.getName()).log(Level.INFO,"se conecto");
        } catch (SQLException ex) {
             Logger.getLogger(ConexionDB.class.getName()).log(Level.INFO,"2",ex);
        }
    }
    
    public boolean execute(String sql){
        Statement st= null;
        try {
            st=con.createStatement();
            st.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE,null, ex);
            return false;
        }
        finally{
            if(st!=null){
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE,null, ex);
                }
            }
        }
    }
    
    //anonima
    public boolean execute(TransactionDB t){
        return t.execute(con);
    }
    
    public List select(SelectionDB sel){
       return  sel.select(con);
    }
    
    public ResultSet select(String sql){
        try(Statement st = con.createStatement()){
            ResultSet reg = st.executeQuery(sql);
            return reg;
        } catch (SQLException ex){
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null,ex);
            return null;
        }
    }
}
