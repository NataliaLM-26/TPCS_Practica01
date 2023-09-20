/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tpcs_practica01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan
 */
public class DAOEmpleado implements IDAOGeneral<Empleado, Long>{
    private PreparedStatement query=null;
    @Override
    public Empleado save(Empleado empleado) {
        ConexionDB con=ConexionDB.getInstance();
        TransactionDB t=new TransactionDB<Empleado>(empleado){
            @Override
            public boolean execute(Connection con){
                try{
                    query=con.prepareStatement("insert into empleados(claveempleado, nombre, dirección)"+
                    " values (?, ?, ?);");
                    query.setLong(1, p.getClave());
                    query.setString(2, p.getNombre());
                    query.setString(3, p.getDireccion());
                    query.execute();
                    return true;
                }catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean res=con.execute(t);
        
        if (res){
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Se guardo");
        }else{
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "No se guardo");
        }
        
        return empleado;
    }

    @Override
    public Empleado update(Empleado empleado, Long id) {
        ConexionDB con=ConexionDB.getInstance();
        TransactionDB t=new TransactionDB<Empleado>(empleado){
            @Override
            public boolean execute(Connection con){
                try{
                    query=con.prepareStatement("UPDATE empleados SET nombre=?, dirección=? WHERE claveempleado=?");
                    query.setString(1, empleado.getNombre());
                    query.setString(2, empleado.getDireccion());
                    query.setLong(3, id);
                    query.execute();
                    return true;
                }catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                    
                }
            }
        };
        boolean res=con.execute(t);
        if(res){
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Actualización realizada con exito.");
        }else{
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Actualización fallida.");
        }
        return empleado;
    }

    @Override
    public boolean delete(Long id) {
        ConexionDB con=ConexionDB.getInstance();
        TransactionDB t = new TransactionDB<Long>(id){
            @Override
            public boolean execute(Connection con){
                try{
                    query=con.prepareStatement("DELETE FROM empleados WHERE claveempleado=?");
                    query.setLong(1, id);
                    query.execute();
                    return true;
                }catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean res=con.execute(t);
        if(res){
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Eliminación realizada con exito.");
        }else{
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Eliminación fallida.");
        }
        return res;
    }

    @Override
    public Empleado findbyID(Long id) {
        ConexionDB con=ConexionDB.getInstance();
        SelectionDB s=new SelectionDB<Empleado>(null){
            @Override
            public List select(Connection con, Empleado e){
                Empleado emp=new Empleado();
                List<Empleado>listaEmpleado=new ArrayList<>();
                try{
                    query=con.prepareStatement("Select * from empleados where claveempleado=?");
                    query.setLong(1, id);
                    ResultSet rs=query.executeQuery();
                    while (rs.next()){
                        emp.setClave(rs.getLong(1));
                        emp.setNombre(rs.getString(3));
                        emp.setDireccion(rs.getString(2));
                        listaEmpleado.add(emp);
                    }
                }catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return listaEmpleado;
            }
        };
        
        List<Empleado>emps=con.select(s);
        if(!emps.isEmpty()){
            return emps.get(0);
        }else{
            return null;
        }
        
    }

    @Override
    public List<Empleado> findAll() {
        ConexionDB con=ConexionDB.getInstance();
        SelectionDB s=new SelectionDB<Empleado>(null){
            @Override
            public List select(Connection con, Empleado e){
                
                List<Empleado> listEmp=new ArrayList<>();
                try{
                    
                    query=con.prepareStatement("Select * from empleados order by claveempleado");
                    ResultSet rs=query.executeQuery();
                    while (rs.next()){
                        Empleado emp=new Empleado();
                        emp.setClave(rs.getLong(1));
                        emp.setNombre(rs.getString(3));
                        emp.setDireccion(rs.getString(2));
                        listEmp.add(emp);
                    }
                    
                }catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return listEmp;
            }
        };
        return con.select(s);
    }
    
}
