package org.uv.tpcs_practica01;
import java.sql.Connection;
import java.util.List;

public abstract class SelectionDB <T>{
    protected T p;
    
    public SelectionDB(T p){
        this.p=p;
    }
    
    public abstract List<T> select(Connection con);      
    
}
