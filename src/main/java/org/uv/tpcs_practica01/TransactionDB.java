package org.uv.tpcs_practica01;
import java.sql.Connection;

/**
 *
 * @author miran
 */
public abstract class TransactionDB <T>{
    //el tipo T es generic
    //es una agregación
    protected T p;
    
    public TransactionDB(T p){
        this.p=p;
    }
    
    public abstract boolean execute(Connection con);
}
