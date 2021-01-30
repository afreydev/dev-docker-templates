/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import core.db.JDBCConnection;
import core.dto.Company;

/**
 *
 * @author Usuario
 */
public class CompaniesService {
    
    public Collection<Company> getCompanies() {
        JDBCConnection connDriver = new JDBCConnection();
        Collection<Company> ret = null;
        Statement oStatement = null;
        ResultSet oResultSet = null;
        try {
            Connection conn = connDriver.getConn();
            oStatement = conn.createStatement();
            oResultSet = oStatement.executeQuery("select * from companies");
            ret = this.mapCompanies(oResultSet);
        } catch(SQLException e ) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
    
    private Collection<Company> mapCompanies(ResultSet rs) throws SQLException {
        Collection<Company> ret = new LinkedList();
        while (rs.next()) {
            Company oCompany = new Company();
            oCompany.setId(rs.getInt(1));
            oCompany.setName(rs.getString(2));
            oCompany.setDescription(rs.getString(3));
            ret.add(oCompany);
        }
        return ret;
    }
    
}
