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
public class Exam extends Disposition{
    
    private int examcode;
    private String result;
    private int providedby;
    private String name;
    final public int TICKET = 11;

    public int getExamcode() {
        return examcode;
    }

    public void setExamcode(int examcode) {
        this.examcode = examcode;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getProvidedby() {
        return providedby;
    }

    public void setProvidedby(int providedby) {
        this.providedby = providedby;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.examcode;
        hash = 29 * hash + Objects.hashCode(this.result);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + this.providedby;
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
        final Exam other = (Exam) obj;
        return true;
    }
    
}