import java.sql.SQLException;

public interface ItemDAO extends DAO<Item> {
    public Seller getSellerForItem(int itemId) throws SQLException;
}
