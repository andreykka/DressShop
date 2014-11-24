package model.dao;

import model.entity.Categories;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by gandy on 18.11.14.
 */
public interface CategoryDAO {

    public List<Categories> getCategories() throws SQLException;

    public Categories getCategoryById(int id) throws SQLException;

    public void removeCategory(Categories category) throws SQLException;

    public void removeCategory(int idCategory) throws SQLException;

    public void updateCategory (Categories newCategory) throws SQLException;

    public void addCategory(Categories category) throws SQLException;


}
