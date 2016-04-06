package net.krglok.realmsBank.Common;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection 
{
	private boolean isOpen = false;
	private Connection connection;
	private String dbName = "realms";
//	private File file ;
	private Statement statement;
	private boolean isDBexist = false;

	private String dbHost = "localhost"; // Hostname
	private String dbPort = "3306";      // Port -- Standard: 3306
	private String dbUser = "aleks";     // Datenbankuser
	private String dbPass = "test";      // Datenbankpasswort	
	/**
	 * Holder for the last update count by a query.
	 */
	protected int lastUpdate;

	public MySQLConnection()
	{
	}
	
	public MySQLConnection(String hostName, String port, String userName, String password, String databaseName)
	{
		this.dbHost = hostName;
		this.dbPort = port;
		this.dbUser = userName;
		this.dbPass = password;
		this.dbName = databaseName;
//		this.fileName = this.dbName+".db";
//		setFile();
	}
	

	/**
	 * get the predefined constant for connecting to database
	 * 
	 * @param databaseName
	 */
	public MySQLConnection(String databaseName)
	{
		dbName = databaseName;		
	}
	
	
	/**
	 * initialize database driver
	 * will be done automatic and only once per connection
	 *  
	 * @return
	 */
	protected boolean initialize() 
	{
		try 
		{
//			Class.forName("org.sqlite.JDBC");
			Class.forName("org.mysql.JDBC");
		  return true;
		} catch (ClassNotFoundException e) 
		{
			System.out.println("Class not found in initialize(): " + e);
		       System.out.println(ConfigBasis.PLUGIN_NAME+" init MySQL,JDBC");
		  return false;
	    }
	}
	

	/**
	 * open connection to database with jdbc for SQLite
	 * @return
	 * @throws SQLException 
	 */
	public boolean open() throws SQLException 
	{
		
		if (initialize()) 
		{
			if (isDBexist == true)
			{
				try
				{
					connection = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+ dbPort+"/"+dbName+"?"+"user="+dbUser+"&"+"password="+dbPass);				
					this.statement  = connection.createStatement();
					isOpen = true;
				} catch (SQLException e) 
				{
			        System.out.println("Verbindung nicht moglich");
			        System.out.println("SQLException: " + e.getMessage());
			        System.out.println("SQLState: " + e.getSQLState());
			        System.out.println("VendorError: " + e.getErrorCode());
				}
			} else
			{
				isOpen = false;
				return false;
			}
			return true;
		} else 
		{
			return false;
		}
	}

	/**
	 * check for tablename in database Metadat
	 * 
	 * @param tableName
	 * @return true if  found
	 * @throws SQLException 
	 */
	public boolean isTable(String tableName) throws SQLException
	{
		if (isOpen)
		{
			DatabaseMetaData md = null;
			md = this.connection.getMetaData();
			ResultSet tables = md.getTables(null, null, tableName, null);
			if (tables.next()) 
			{
				tables.close();
				return true;
			} else {
				tables.close();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * execute query 
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet query(String query) throws SQLException
	{
		return this.statement.executeQuery(query);
	}
	
	public void execute(String query) throws SQLException
	{
		this.statement.execute(query);
//		PreparedStatement pst;
//		pst = connection.prepareStatement(query);
//        pst.executeUpdate();
	}
	
	
	/**
	 * execute insert, return no resultset 
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public boolean insert(String sql) throws SQLException
	{
//		PreparedStatement pQuery = connection.prepareStatement(sql);
//		pQuery.executeUpdate();
//		return true;
	    if (this.statement.executeUpdate(sql) > 0) 
	    {
	    	return true;
	    }
		return false;
	}

	/**
	 * execute upate, return no resltset
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public boolean update(String sql) throws SQLException
	{
		if (this.statement.executeUpdate(sql) > 0)
		{
	    	return true;
	    }
		return false;
	}
	
	/**
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public boolean delete(String sql) throws SQLException
	{
		if (this.statement.executeUpdate(sql) > 0)
		{
	    	return true;
	    }
		return false;
	}
	
	
	public boolean insertBatch()
	{
	
		
		return false;
	}
	
	
	public boolean isOpen()
	{
		return isOpen;
	}

	public void setOpen(boolean isOpen)
	{
		this.isOpen = isOpen;
	}

	public Connection getConnection()
	{
		return connection;
	}

	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}


	public String getDbName()
	{
		return this.dbName;
	}

}
