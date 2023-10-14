DELIMITER //

CREATE PROCEDURE AddItemToSeller(IN p_ItemID INT, IN p_UserID INT)
BEGIN
    INSERT INTO Sellers (UserID, ItemID)
    VALUES (p_UserID, p_ItemID);
END//

DELIMITER ;

DELIMITER //

CREATE TRIGGER AfterInsertItem
AFTER INSERT ON Item
FOR EACH ROW
BEGIN
    CALL AddItemToSeller(NEW.ItemID, NEW.UserID);
END//

DELIMITER ;




DELIMITER //

CREATE PROCEDURE AddItemToBuyer(IN p_ItemID INT, IN p_UserID INT)
BEGIN
    INSERT INTO Buyer (UserID, ItemID)
    VALUES (p_UserID, p_ItemID);
END//

DELIMITER ;



DELIMITER //

CREATE TRIGGER AfterInsertBid
AFTER INSERT ON Bid
FOR EACH ROW
BEGIN
    CALL AddItemToBuyer(NEW.ItemID, NEW.UserID);
END//

DELIMITER ;
