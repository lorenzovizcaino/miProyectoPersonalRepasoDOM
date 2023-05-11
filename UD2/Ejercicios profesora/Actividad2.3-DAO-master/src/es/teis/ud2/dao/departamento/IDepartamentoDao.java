/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.teis.ud2.dao.departamento;

import es.teis.ud2.model.Departamento;

/**
 *
 * @author mfernandez
 */
public interface IDepartamentoDao {
    
    public Departamento create(Departamento departamento);
    
    public Departamento read(int id);
    
    public boolean update(Departamento departamento);
    
    public boolean delete(int id);
    
}
