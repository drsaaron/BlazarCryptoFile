/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * a simple data source that will wrap a raw data source, <em>e.g.</em> a JNDI
 * data source, but establish a connection using the password from the crypto
 * file.  With this approach, we don't need to define connection pool resources
 * in the resource or in Spring xml that have hard-coded, plaintext passwords.
 * 
 * @author aar1069
 * @version $Id: BlazarCryptoDataSource.java 431 2016-11-07 17:13:47Z aar1069 $
 */

/* $Log$
 *******************************************************************************/
public class BlazarCryptoDataSource implements DataSource {

    private DataSource rawDataSource;
    private BlazarCryptoFile cryptoFile;
    private String userID;
    private String resource;

    public String getResource() {
        return resource;
    }

    public void setResource(String server) {
        this.resource = server;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public BlazarCryptoFile getCryptoFile() {
        return cryptoFile;
    }

    public void setCryptoFile(BlazarCryptoFile cryptoFile) {
        this.cryptoFile = cryptoFile;
    }

    public DataSource getRawDataSource() {
        return rawDataSource;
    }

    public void setRawDataSource(DataSource rawDataSource) {
        this.rawDataSource = rawDataSource;
    }
    
    @Override
    public Connection getConnection() throws SQLException {
        String password = getCryptoFile().getPassword(userID, resource);
        return getConnection(userID, password);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getRawDataSource().getConnection(username, password);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return getRawDataSource().getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        getRawDataSource().setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        getRawDataSource().setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return getRawDataSource().getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return getRawDataSource().getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return getRawDataSource().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return getRawDataSource().isWrapperFor(iface);
    }
    
}
