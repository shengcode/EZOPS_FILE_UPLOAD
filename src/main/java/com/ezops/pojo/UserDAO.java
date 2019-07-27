package com.ezops.pojo;
import javax.sql.DataSource;
public interface UserDAO {
	    public void setDataSource(DataSource ds);
	    public User getUser(String id);
	    public void setUserPassword(String id, String password);
	    public DataSource getDataSource();

}
