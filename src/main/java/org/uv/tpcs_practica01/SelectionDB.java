package org.uv.tpcs_practica01;


import java.sql.Connection;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author juan
 */
public abstract class SelectionDB<T> {
    public abstract List<T> select(Connection con, T t);
    protected T p;
    public SelectionDB(T t){
        this.p=t;
    }
}
