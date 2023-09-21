package org.uv.tpcs_practica01;

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
        
        System.out.println("Hello I'm David");
    }
}
