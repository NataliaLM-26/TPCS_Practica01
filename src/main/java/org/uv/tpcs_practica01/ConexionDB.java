/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tpcs_practica01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan
 */
public class ConexionDB {
    private Connection coneccion=null;
   
    private ConexionDB(){
        String url="jdbc:postgresql://172.17.0.2/empleados";
        String user="david";
        String password="123456";
        
        try{
            coneccion=DriverManager.getConnection(url, user, password);
            Logger.getLogger(ConexionDB.class.getName()).log(Level.INFO, "Se conecto");
        }catch(SQLException ex){
            Logger.getLogger(ConexionDB.class.getName()).log(Level.INFO, "Error de conexion!!");
        }
    }
    
    public static ConexionDB cx=null;
    public static ConexionDB getInstance(){
        if(cx==null){
            cx=new ConexionDB();
        }
        return cx;
    }
    
    public boolean execute(String sql){
        Statement st=null;
        try{
            st=coneccion.createStatement();
            st.execute(sql);
            return true;
        }catch(SQLException ex){
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean execute(TransactionDB t){
        return t.execute(coneccion);
    }
    
    public ResultSet select(String sql){
   
        try{
            Statement st=coneccion.createStatement();
            return st.executeQuery(sql);
            
            
        }catch(SQLException ex){
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public List select(SelectionDB sel){
        return sel.select(coneccion, null);
    }
    
}
