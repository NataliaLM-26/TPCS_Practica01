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

    @Override
    public Empleado save(Empleado pojo) {
        TransactionDB t = new TransactionDB<Empleado>(pojo) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "insert into usuario (clave,nombre,direccion,telefono) values "
                            + "(?,?,?,?)";
                    PreparedStatement pstm = con.prepareStatement(sql);
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
        return pojo;
    }
    

    @Override
    public Empleado update(Empleado pojo, int id) {
        TransactionDB t = new TransactionDB<Empleado>(pojo){
            @Override
            public boolean execute(Connection con) {
                try{
                    String sql= "UPDATE empleado SET  nombre=?, direccion=?, telefono=? WHERE clave=?";
                    PreparedStatement pstm=con.prepareStatement(sql);
                    pstm.setLong(1, p.getClave());
                    pstm.setString(2, p.getNombre());
                    pstm.setString(3, p.getDireccion());
                    pstm.setString(4, p.getTelefono());
                    pstm.setLong(5, id);
                    int rowsUpdate = pstm.executeUpdate();
                    
                    if(rowsUpdate >0){
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
                     PreparedStatement pstm = con.prepareStatement(sql);
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
                try {
                    String sql = "SELECT * FROM empleado WHERE id=?";
                    PreparedStatement pstm = con.prepareStatement(sql);
                    pstm.setLong(1, id);
                    ResultSet reg = pstm.executeQuery();
                    List<Empleado> lstEmpleado = new ArrayList<>();

                    while (reg.next()) {
                        Empleado emp = new Empleado();
                        emp.setClave(reg.getInt("clave"));
                        emp.setNombre(reg.getString("nombre"));
                        emp.setDireccion(reg.getString("direccion"));
                        emp.setTelefono(reg.getString("telefono"));
                        lstEmpleado.add(emp);
                    }
                return lstEmpleado;
                } catch (SQLException ex) {
                Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                return null;
              }
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
                try{
                List<Empleado> lstEmpleado=new ArrayList<>();
                String sql="select * from empleado";
                Statement st=null;
                ResultSet reg=null;
                st = con.createStatement();
                reg = st.executeQuery(sql);
                
                while (reg.next()){
                    Empleado emp= new Empleado();
                    emp.setClave(reg.getInt(1));
                    emp.setNombre(reg.getString(2));
                    lstEmpleado.add(emp);
                }
                return lstEmpleado;
                }catch (SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
            }
            
        };
        ConexionDB con=ConexionDB.getInstance();
        return con.select(sel);
    }
    
}