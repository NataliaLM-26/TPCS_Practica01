package org.uv.tpcs_practica01;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOEmpleado implements IDAOGeneral<Empleado, Long>{
    private PreparedStatement pstm=null;
    @Override
    public Empleado save(Empleado pojo) {
        TransactionDB t = new TransactionDB<Empleado>(pojo) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "insert into empleado (clave,nombre,direccion,telefono) values "
                            + "(?,?,?,?)";
                    pstm = con.prepareStatement(sql);
                    pstm.setLong(1, p.getClave());
                    pstm.setString(2, p.getNombre());
                    pstm.setString(3, p.getDireccion());
                    pstm.setString(4, p.getTelefono());
                    pstm.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "2", ex);
                    return false;
                }
            }
        };
        //asigna instancia e inyecta dependencia
        ConexionDB con = ConexionDB.getInstance();
        boolean res = con.execute(t);
        if (res){
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "Se guardo");
        }else{
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.INFO, "No se guardo");
        }
        return pojo;
    }
    

    @Override
    public Empleado update(Empleado pojo, Long id) {
        TransactionDB t = new TransactionDB<Empleado>(pojo){
            @Override
            public boolean execute(Connection con) {
                try{

                    String sql= "UPDATE empleado SET  nombre=?, direccion=?, telefono=? WHERE clave=?";
                    PreparedStatement pstm=con.prepareStatement(sql);
                    pstm.setString(1, p.getNombre());
                    pstm.setString(2, p.getDireccion());
                    pstm.setString(3, p.getTelefono());
                      pstm.setLong(4, id);
                    int rowsUpdate = pstm.executeUpdate();
                    
                    if(rowsUpdate){
                        return true;
                    } else {
                        return false;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        ConexionDB con = ConexionDB.getInstance();
        boolean res = con.execute(t);
        if (res) {
            return pojo;
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
         TransactionDB t = new TransactionDB<Boolean>(false){
             @Override
             public boolean execute(Connection con) {
                 try {
                     String sql = "DELETE FROM empleado WHERE clave=?";
                     pstm = con.prepareStatement(sql);
                     pstm.setLong(1, id);
                     int deleted = pstm.executeUpdate();
                     
                     if(deleted >0){
                         return true;
                     } else {
                         return false;
                     }
                 } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
             }
         };
         
        ConexionDB con = ConexionDB.getInstance();
        return con.execute(t);
    }

    @Override
    public Empleado findById(Long id) {
        SelectionDB sel=new SelectionDB(id) {
            @Override
            public List select(Connection con) {
                List<Empleado> lstEmpleado = new ArrayList<>();
                try {
                    String sql = "SELECT * FROM empleado WHERE clave=?";

                    PreparedStatement pstm = con.prepareStatement(sql);

                    pstm = con.prepareStatement(sql);

                    pstm.setLong(1, id);
                    ResultSet reg = pstm.executeQuery();

                    while (reg.next()) {
                        Empleado emp = new Empleado();
                        emp.setClave(reg.getInt("clave"));
                        emp.setNombre(reg.getString("nombre"));
                        emp.setDireccion(reg.getString("direccion"));
                        emp.setTelefono(reg.getString("telefono"));
                        lstEmpleado.add(emp);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return lstEmpleado;
            }
        };
    ConexionDB con = ConexionDB.getInstance();
    List<Empleado> result = con.select(sel);

    // Devuelve el primer elemento de la lista si se encuentra, o null si no se encuentra ninguno.
    return result.isEmpty() ? null : result.get(0);
    }

   @Override
    public List<Empleado> findAll() {
        SelectionDB sel=new SelectionDB(null){
            @Override
            public List select(Connection con) {
                List<Empleado> lstEmpleado=new ArrayList<>();
                try{
                String sql="select * from empleado";
                ResultSet reg=null;
                pstm = con.prepareStatement(sql);
                reg = pstm.executeQuery(sql);
                
                while (reg.next()){
                    Empleado emp= new Empleado();
                    emp.setClave(reg.getInt(1));
                    emp.setNombre(reg.getString(2));
                    emp.setTelefono(reg.getString(4));
                    emp.setDireccion(reg.getString(3));
                    lstEmpleado.add(emp);
                }
                }catch (SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return lstEmpleado;
            }
            
        };
        ConexionDB con=ConexionDB.getInstance();
        return con.select(sel);
    }
    
}
