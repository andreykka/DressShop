package model.util;


import model.dao.AdminUserDao;

public interface FactoryDao {
	
    public AdminUserDao getAdminUserDao();

}
