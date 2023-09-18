package org.uv.tpcs_practica01;

import java.sql.Connection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author juan
 */
public abstract class TransactionDB <T>{
    
    protected T p;
    
    public TransactionDB(T p){
        this.p=p;
    }
    
    public abstract boolean execute(Connection con);
}
