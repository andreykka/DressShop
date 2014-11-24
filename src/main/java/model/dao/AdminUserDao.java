package model.dao;

import java.sql.SQLException;

public interface AdminUserDao {
	
    public boolean getIsAdmin(String login, String password) throws SQLException;
	

}
