/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tpcs_practica01;

import java.util.List;

/**
 *
 * @author juan
 */
public interface IDAOGeneral <T,S>  {
    public T save(T t);
    public T update(T t, S s);
    public boolean delete(S s);
    
    public T findbyID(S s);
    public List<T> findAll();
    
}
