package org.tfa.db;

import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.tfa.dto.SilanisCallbackDTO;

/**
 * http://stackoverflow.com/questions/12812256/how-do-i-implement-a-dao-manager-using-jdbc-and-connection-pools
 *
 * To install ojdbc6.jar
 * 		mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar -Dfile=ojdbc6.jar -DgeneratePom=true
 */
public class DAOManager {

    public static DAOManager getInstance() {
        return DAOManagerSingleton.INSTANCE.get();
    }  
    
    public void insertSilanisCallback(SilanisCallbackDTO request){
    	
    	String insertTableSQL = "insert into silanis_callback"
    			+ "(name, packageid, sessionuser, datecreated) VALUES"
    			+ "(?,?,?,?)";
    	PreparedStatement preparedStatement;
		try {
			preparedStatement = this.con.prepareStatement(insertTableSQL);
		
    	preparedStatement.setString(1, request.getName());
    	preparedStatement.setString(2, request.getPackageId());
    	preparedStatement.setString(3, request.getSessionUser());
    	preparedStatement.setTimestamp(4, getCurrentTimeStamp());
    	// execute insert SQL stetement
    	preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public List<SilanisCallbackDTO> getSilanisCallbacks() throws SQLException{
    	
    	List<SilanisCallbackDTO> callbacks = new ArrayList<SilanisCallbackDTO>();
    	ResultSet rs = this.executeQuery("select name, packageid, sessionuser from salinas_callback");
    	while(rs.next()){
    		SilanisCallbackDTO callback = new SilanisCallbackDTO();
    		callback.setName(rs.getString("name"));
    		callback.setPackageId(rs.getString("packageid"));
    		callback.setSessionUser(rs.getString("sessionuser"));
    		callbacks.add(callback);
    	}
    	return callbacks;
    }
    
    private static java.sql.Timestamp getCurrentTimeStamp() {
    	 
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
 
	}
    
    public ResultSet executeQuery(String query){
    	
    	System.out.println(query);
    	
    	Statement createStatement;
    	ResultSet rs = null;
		try {
			createStatement = this.con.createStatement();
			rs = createStatement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rs;
    }

    public void open() throws SQLException {
        try
        {
            if(this.con == null)
                this.con = connectionPool.getConnection();
        }
        catch(SQLException e) { throw e; }
    }

    public void close() throws SQLException {
        try
        {
            if(this.con != null)
                this.con.close();
        }
        catch(SQLException e) { throw e; }
    }

    //Private
    private BasicDataSource connectionPool;
    private Connection con;

    private DAOManager() throws Exception {
        try
        {
        	URI dbUri = new URI(System.getenv("DATABASE_URL"));
        	  String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        	  connectionPool = new BasicDataSource();

        	  if (dbUri.getUserInfo() != null) {
        	    connectionPool.setUsername(dbUri.getUserInfo().split(":")[0]);
        	    connectionPool.setPassword(dbUri.getUserInfo().split(":")[1]);
        	  }
        	  connectionPool.setDriverClassName("org.postgresql.Driver");
        	  connectionPool.setUrl(dbUrl);
        	  connectionPool.setInitialSize(1);
        	this.open();
        }
        catch(Exception e) { throw e; }
    }

    private static class DAOManagerSingleton {

        public static final ThreadLocal<DAOManager> INSTANCE;
        static
        {
            ThreadLocal<DAOManager> dm;
            try
            {
                dm = new ThreadLocal<DAOManager>(){
                    @Override
                    protected DAOManager initialValue() {
                        try
                        {
                            return new DAOManager();
                        }
                        catch(Exception e)
                        {
                            return null;
                        }
                    }
                };
            }
            catch(Exception e){
                dm = null;
            }
            INSTANCE = dm;
        }        

    }

}