package com.warren.database.test;

import java.io.File;
import java.sql.Connection;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

// http://java.sun.com/developer/technicalArticles/Programming/jndi/index.html
//http://www.java2s.com/Code/Java/Database-SQL-JDBC/JndiDataSource.htm

public class DBAccessDataSource extends DBAccess {

	static final String jndiContextId = "MY_JNDI_FILE_SYSTEM_CONTEXT";
	static Context ctx = null;
	
	public static boolean setJNDI() {

		Hashtable props = new Hashtable ();		
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");	
			// The following specifies c:\myTechnicalStuff, and this is where the .bindings file is created.
//		props.put(Context.PROVIDER_URL, "file:///myTechnicalStuff");
			// The following specifies c:\, however it's not clear where the .bindings file is created.
		props.put(Context.PROVIDER_URL, "file:///c:");
		
//		props.put(Context.INITIAL_CONTEXT_FACTORY, 
//				"com.sun.enterprise.naming.SerialInitContextFactory");
//		props.put(Context.PROVIDER_URL, "localhost:1099");
		
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setServerName(SERVER);
		mysqlDS.setDatabaseName(DATABASE);
		mysqlDS.setUser(USER);
		mysqlDS.setPassword(PASSWORD);
		
		try {
			ctx = new InitialContext(props);
			ctx.bind(jndiContextId, mysqlDS);
		}
		catch(NameAlreadyBoundException e) {
			return true;
		}
		catch (NamingException e) {
			e.printStackTrace(System.out);
			return false;
		}
		catch (Exception e) {
			e.printStackTrace(System.out);
			return false;
		}
		
		return true;
	}

	
	@Override
	public Connection getConnection() throws Exception {
		
		DataSource ds = (DataSource) ctx.lookup(jndiContextId);
		Connection ctn = ds.getConnection();
		
		return ctn;
	}
	
	
	public static void main(String[] args) {
		
		if(!setJNDI())
			return;
		
		DBAccessDataSource dbaccess = new DBAccessDataSource();
		dbaccess.printDeckmanTableRow();
	}
}
