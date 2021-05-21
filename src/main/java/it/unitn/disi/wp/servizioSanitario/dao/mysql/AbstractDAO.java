/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.database.Database;
import java.sql.Connection;

/**
 *
 * @author Mike_TdT
 */
public abstract class AbstractDAO {
	protected DAOFactory dAOFactory;

	public AbstractDAO(DAOFactory dAOFactory) {
		this.dAOFactory=dAOFactory;
	}

	public Connection getCon() {
		return dAOFactory.getConn();
	}

}
