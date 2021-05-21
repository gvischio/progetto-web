/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities;

import java.util.Objects;

/**
 *
 * @author Mike_TdT
 */
public class Speckvisit extends Disposition{
    
    private int specialist;
    private int visitcode;
    private String result;
    private String name;
    final public int TICKET = 50;
    
    
    public int getSpecialist() {
        return specialist;
    }

    public void setSpecialist(int specialist) {
        this.specialist = specialist;
    }

    public int getVisitcode() {
        return visitcode;
    }

    public void setVisitcode(int visitcode) {
        this.visitcode = visitcode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.specialist;
        hash = 73 * hash + this.visitcode;
        hash = 73 * hash + Objects.hashCode(this.result);
        hash = 73 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Speckvisit other = (Speckvisit) obj;
        return true;
    }
    
}