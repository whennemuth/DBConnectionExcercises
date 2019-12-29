package com.warren.database.test;

import java.sql.Connection;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;
import javax.sql.PooledConnection;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class DBAccessConnectionPoolDataSource extends DBAccess {

	static final String jndiContextId = "jdbc/deckmanDBPool";
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
		
		MysqlConnectionPoolDataSource pool = new MysqlConnectionPoolDataSource();
		pool.setServerName(SERVER);
		pool.setDatabaseName(DATABASE);
		pool.setUser(USER);
		pool.setPassword(PASSWORD);
		
		try {
			ctx = new InitialContext(props);
			ctx.bind(jndiContextId, pool);
		}
		catch(NameAlreadyBoundException e) {
			return true;
		}
		catch (NamingException e) {
			e.printStackTrace(System.out);
			return false;
		}
		
		return true;
	}

	@Override
	public Connection getConnection() throws Exception {
		
		MysqlConnectionPoolDataSource pool = (MysqlConnectionPoolDataSource) ctx.lookup(jndiContextId);
		PooledConnection pooledCtn = pool.getPooledConnection();
		return pooledCtn.getConnection();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if(!setJNDI())
			return;
		
		DBAccessConnectionPoolDataSource dbaccess = new DBAccessConnectionPoolDataSource();
		dbaccess.printDeckmanTableRow();
	}

}
