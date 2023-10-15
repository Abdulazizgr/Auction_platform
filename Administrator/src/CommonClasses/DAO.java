package CommonClasses;

import java.sql.SQLException;
import java.util.List;

/* This is where we list our function blueprints
 * Any class extending this interface will implement those methods
 * Therefore add what u think is usefull
 */

public interface DAO<T> {
    T get(int ID) throws SQLException;
    List<T> getAll() throws SQLException;
    int insert(T t) throws SQLException;
    int update(T t) throws SQLException;
    int delete(T t) throws SQLException;
}
