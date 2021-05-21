/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities.utils;

import java.sql.Date;

/**
 *
 * @author frape
 */
public class PazienteVista {
    private int id_pat;
    private int district;
    private String ssn;
    private String patname;
    private int id_doc;
    private String docname;
    private Date birthday;
    private Date lastvisit;
    private int examstodo;

    public void setId_pat(int id_pat) {
        this.id_pat = id_pat;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setPatname(String patname) {
        this.patname = patname;
    }

    public void setId_doc(int id_doc) {
        this.id_doc = id_doc;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setLastvisit(Date lastvisit) {
        this.lastvisit = lastvisit;
    }

    public void setExamstodo(int examstodo) {
        this.examstodo = examstodo;
    }

    public int getId_pat() {
        return id_pat;
    }

    public int getDistrict() {
        return district;
    }

    public String getSsn() {
        return ssn;
    }

    public String getPatname() {
        return patname;
    }

    public int getId_doc() {
        return id_doc;
    }

    public String getDocname() {
        return docname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Date getLastvisit() {
        return lastvisit;
    }

    public int getExamstodo() {
        return examstodo;
    }

}
