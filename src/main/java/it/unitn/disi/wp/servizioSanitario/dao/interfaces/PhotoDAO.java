/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Photo;
import java.util.List;

/**
 *
 * @author Mike_TdT
 */
public interface PhotoDAO {
    
    Photo getById(Integer id) throws DaoException;
    List<Photo> getByUser(Integer user) throws DaoException;
    Photo getByPath(String path) throws DaoException;
    Photo getByLastPhotoUser(Integer user) throws DaoException;
    boolean addPhoto(Photo photo) throws DaoException;
    boolean deletePhoto(Integer id) throws DaoException;
    
}
