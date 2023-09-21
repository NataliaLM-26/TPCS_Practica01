package org.uv.tpcs_practica01;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TPCS_Practica01 {

    public static void main(String[] args) {
        Empleado emp = new Empleado();
        DAOEmpleado dao = new DAOEmpleado();
        emp.setClave(589);
        emp.setNombre("nombre");
        emp.setDireccion("dir");
        emp.setTelefono("1258");
        dao.save(emp);
        System.out.println("Cambios listos!");
        
        Logger.getLogger(TPCS_Practica01.class.getName()).log(Level.INFO, "Hello I am David");
        
        DAOEmpleado daoEmpleado=new DAOEmpleado();
        List<Empleado> lista=daoEmpleado.findAll();
        for (Empleado e:lista){
            System.out.println(e.getNombre());
        }
    }
}
