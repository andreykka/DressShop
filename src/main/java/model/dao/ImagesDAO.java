package model.dao;

import model.entity.Images;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by gandy on 19.11.14.
 */
public interface ImagesDAO {

    public int          add(Images image)           throws SQLException;

    public List<Images> get()                       throws SQLException;

    public Images       getById(int idImage)        throws SQLException;

    public void         update(Images newImg)       throws SQLException;

    public void         remove(Images image)        throws SQLException;

    public void         remove(int id)              throws SQLException;


}
