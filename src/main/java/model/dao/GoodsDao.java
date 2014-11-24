package model.dao;

import model.entity.Goods;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by gandy on 27.10.14.
 *
 */
public interface GoodsDao {

    public int          add(Goods good)         throws SQLException;

    public List<Goods>  get()                   throws SQLException;

    public Goods        getById(int id)         throws SQLException;

    public void         update(Goods newGood)   throws SQLException;

    public void         remove(Goods good)      throws SQLException;

    public void         remove(int id)          throws SQLException;


}
