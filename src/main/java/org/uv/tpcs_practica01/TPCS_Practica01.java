package org.uv.tpcs_practica01;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TPCS_Practica01 {

    public static void main(String[] args) {
        Logger.getLogger(TPCS_Practica01.class.getName()).log(Level.INFO, "Hello World");
        
        Logger.getLogger(TPCS_Practica01.class.getName()).log(Level.INFO, "Hello I am David");
        
        DAOEmpleado daoEmpleado=new DAOEmpleado();
        List<Empleado> lista=daoEmpleado.findAll();
        for (Empleado e:lista){
            System.out.println(e.getNombre());
        }
    }
}
