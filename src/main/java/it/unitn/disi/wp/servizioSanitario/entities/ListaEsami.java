/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities;

import it.unitn.disi.wp.servizioSanitario.entities.utils.AbstractEntity;
import java.util.Objects;

/**
 *
 * @author Mike_TdT
 */
public class ListaEsami extends AbstractEntity{
    
    private String name;
    private int category;
    private String description;
    private String categoryname;

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + this.category;
        hash = 97 * hash + Objects.hashCode(this.description);
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
        final ListaEsami other = (ListaEsami) obj;
        return true;
    }
    
    
}
