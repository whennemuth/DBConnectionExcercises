package com.warren.database.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * NOTE: other methods at connection pool:
 * http://www.rndblog.com/how-to-set-up-a-mysql-connection-pool-in-java/
 * http://archive.coreservlets.com/Chapter18.html
 * @author Warren
 */
public abstract class DBAccess {

//	static final String SERVER = "localhost";
//	static final String DATABASE = "deckman";
//	static final String USER = "root";
//	static final String PASSWORD = "mypassword";

	static final String SERVER = "thedeckmancapecod.com";
	static final String DATABASE = "thede41_deckman";
	static final String USER = "thede41_warhen";
	static final String PASSWORD = "[SUBSTITUTE THE PASSWORD HERE]";

	
	void printDeckmanTableRow() {
        
		Connection ctn = null;
        Statement stmt = null;
        ResultSet rst = null;
        
        try {
			ctn = this.getConnection();
			stmt = ctn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY, 
					ResultSet.CONCUR_READ_ONLY);
			
			rst = stmt.executeQuery("select * from album");
			if(rst.next()) {
				ResultSetMetaData meta = rst.getMetaData();
				for(int i=1; i<=meta.getColumnCount(); i++) {
					System.out.print(meta.getColumnName(i));
					System.out.print("=");
					System.out.println(rst.getString(i));
				}
			}
		}
        catch (Exception e) {
			e.printStackTrace(System.out);
		}
        finally {
        	try {
        		if(rst != null)
        			rst.close();
        		if(stmt != null)
        			stmt.close();
        		if(ctn != null)
        			ctn.close(); // returns connection to pool if ctn is instance of PooledConnection
			}
        	catch (SQLException e) {
				e.printStackTrace(System.out);
			}
        }
	}

	public abstract Connection getConnection() throws Exception;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
