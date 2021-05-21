/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.exceptions;

/**
 *
 * @author Mike_TdT
 */
public class NotFoundDAOException extends DaoException{

    public NotFoundDAOException(String message) {
        super(message);
    }

    public NotFoundDAOException(Throwable e) {
        super(e);
    }

    public NotFoundDAOException(String message, Throwable e) {
        super(message, e);
    }
    
}
