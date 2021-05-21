/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Sex;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Mike_TdT
 */
public class Paziente extends User{
    
    private String ssn; //codice fiscale
    private String firstname;
    private String lastname;
    private Date birthday;
    private String birthplace;
    private Sex sex;
    private int familydoctor;
    private int district;
    
    public Paziente() {
        
    }
    
    /*
       @return the codicefiscale associated with the patient
    */
    public String getSsn() {
        return ssn;
    }

    /*
        @param codicefiscale associated with the patient
    */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /*
        @return the firstname associated with the patient
    */
    public String getFirstname() {
        return firstname;
    }

    /*
        @param firstname associated with the patient
    */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /*
        @return the lastname associated with the patient
    */
    public String getLastname() {
        return lastname;
    }

    /*
        @param lastname associated with the patient
    */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /*
        @return the birthday associated with the patient
    */
    public Date getBirthday() {
        return birthday;
    }

    /*
        @param birthday associated with the patient
    */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /*
        @return the birthplace associated with the patient
    */
    public String getBirthplace() {
        return birthplace;
    }

    /*
        @param birthplace associated with the patient
    */
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
    /*
        @return the familydoctor_id associated with the patient
    */
    public int getFamilydoctor() {
        return familydoctor;
    }

    /*
        @param familydoctor_id associated with the patient
    */
    public void setFamilydoctor(int familydoctor) {
        this.familydoctor = familydoctor;
    }

    /*
        @return the district_id associated with the patient
    */
    public int getDistrict() {
        return district;
    }

    /*
        @param district_id associated with the patient
    */
    public void setDistrict(int district) {
        this.district = district;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.ssn);
        hash = 97 * hash + Objects.hashCode(this.firstname);
        hash = 97 * hash + Objects.hashCode(this.lastname);
        hash = 97 * hash + Objects.hashCode(this.birthday);
        hash = 97 * hash + Objects.hashCode(this.birthplace);
        hash = 97 * hash + Objects.hashCode(this.sex);
        hash = 97 * hash + this.familydoctor;
        hash = 97 * hash + this.district;
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
        final Paziente other = (Paziente) obj;
        return true;
    }
    
    protected void validaSalva(DAOFactory daoFactory) throws DaoException{

        //SSN modificabile?
        
        if (firstname == null || firstname.equals("")) {
            setError("firstname", "Not empty!");
        }
        if (firstname.length() > 30)
            setError("firstname", "Not more than 30chars. If your firsstname is longer, than press max 30 chars!");
        
        if (lastname == null || lastname.equals("")) {
            setError("lastname", "Not empty!");
        }
        if (lastname.length() > 30)
            setError("lastname", "Not more than 30chars. If your firsstname is longer, than press max 30 chars!");
        
        if (birthday == null) {
            setError("birthday", "Not empty!");
        }
        if (birthplace == null || birthplace.equals("")) {
            setError("birthplace", "Not empty!");
        }
        if (birthplace.length() > 30)
            setError("birthplace", "Not more than 30chars. If your birthplace is longer, than press max 30 chars!");
        
        if (sex != Sex.M || sex != Sex.F) {
            setError("sex", "Invalid sex: please type M for Man, F for Woman, N for the others!");
        }
        
        //controllo FamilyDoctor e District
    }

    ;
    protected void validaUpdeita(DAOFactory daoFactory) throws DaoException{
        
    }

    ;
}
