/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.uv.tpcs_practica01;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan
 */
public class Practica02 {

    public static void main(String[] args) {
        DAOEmpleado d=new DAOEmpleado();
        d.delete(9L);
        
        Empleado e=new Empleado();
        e.setClave(8L);
        e.setNombre("Jose");
        e.setDireccion("Calle 22");
        d.update(e, e.getClave());
        
        List<Empleado> lista=d.findAll();
        for(Empleado a:lista){
            System.out.println(a.getNombre());
        }
        
        Empleado b=d.findbyID(8L);
        System.out.print(b.getNombre());
    }
}
