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
public class DaoException extends Exception{
    public DaoException(String message) {
            super(message);
    }

    public DaoException(Throwable e) {
            super(e);
    }

    public DaoException(String message, Throwable e) {
            super(message, e);
    }
}
