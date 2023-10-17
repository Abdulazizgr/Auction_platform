DELIMITER //

CREATE PROCEDURE AddItemToSeller(IN p_ItemID INT, IN p_UserID INT)
BEGIN
    DECLARE seller_id INT;
    DECLARE active_item_count INT;
    
    -- Check if the seller already exists in the Sellers table
    SELECT SellerID INTO seller_id FROM Sellers WHERE UserID = p_UserID;
    
    -- Check if the seller has any active items
    SELECT COUNT(*) INTO active_item_count FROM Item WHERE UserID = p_UserID AND AuctionStatus = 'Active';
    
    -- If the seller does not have an entry in the Sellers table, insert a new row
    IF seller_id IS NULL THEN
        INSERT INTO Sellers (UserID, ItemID)
        VALUES (p_UserID, p_ItemID);
    ELSE
        -- If the seller has active items, raise an error
        IF active_item_count > 0 THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot add a new item until the previous item is sold.';
        ELSE
            INSERT INTO Sellers (SellerID, UserID, ItemID)
            VALUES (seller_id, p_UserID, p_ItemID);
        END IF;
    END IF;
END//

DELIMITER ;

DELIMITER //

CREATE TRIGGER AfterInsertItem
AFTER INSERT ON Item
FOR EACH ROW
BEGIN
    -- This trigger is executed after each row is inserted into the Item table
    -- It calls the AddItemToSeller procedure to add the item to the Sellers table
    CALL AddItemToSeller(NEW.ItemID, NEW.UserID);
END//

DELIMITER ;



DELIMITER //

CREATE TRIGGER AfterUpdatePaymentStatus
AFTER UPDATE ON Payment
FOR EACH ROW
BEGIN
    DECLARE seller_id INT;

    -- Get the SellerID associated with the updated Payment's ItemID
    SELECT SellerID INTO seller_id FROM Sellers WHERE ItemID = NEW.ItemID;

    -- Check if the payment status is completed
    IF NEW.PaymentStatus = 'Completed' THEN
        -- Delete the seller associated with the ItemID
        DELETE FROM Sellers WHERE SellerID = seller_id;
    END IF;
END //
DELIMITER ;




DELIMITER //

CREATE PROCEDURE AddItemToBuyer(IN p_ItemID INT, IN p_UserID INT)
BEGIN
    -- This procedure adds an item to the Buyer table with the provided ItemID and UserID
    INSERT INTO Buyer (UserID, ItemID)
    VALUES (p_UserID, p_ItemID);
END//

DELIMITER ;

DELIMITER //

CREATE TRIGGER AfterInsertBid
AFTER INSERT ON Bid
FOR EACH ROW
BEGIN
    -- This trigger is executed after each row is inserted into the Bid table
    -- It calls the AddItemToBuyer procedure to add the item to the Buyer table
    CALL AddItemToBuyer(NEW.ItemID, NEW.UserID);
END//

DELIMITER ;


-- //comment
-- Explanation:

-- The AddItemToSeller procedure is created to add an item to the Sellers table. It takes p_ItemID and p_UserID as input parameters and inserts a new row into the Sellers table with the provided values.

-- The AfterInsertItem trigger is created to execute after each row is inserted into the Item table. It calls the AddItemToSeller procedure, passing the newly inserted ItemID and UserID from the NEW keyword. This ensures that whenever a new item is inserted into the Item table, it is automatically added to the Sellers table.

-- The AddItemToBuyer procedure is created to add an item to the Buyer table. It takes p_ItemID and p_UserID as input parameters and inserts a new row into the Buyer table with the provided values.

-- The AfterInsertBid trigger is created to execute after each row is inserted into the Bid table. It calls the AddItemToBuyer procedure, passing the newly inserted ItemID and UserID from the NEW keyword. This ensures that whenever a new bid is inserted into the Bid table, it is automatically added to the Buyer table.
-- //comment



DELIMITER //

CREATE PROCEDURE transfer(IN p_amount DECIMAL(10,2), IN p_item_id INT, IN p_buyer INT, IN p_seller INT)
BEGIN
  -- Declare variables to store the balance of sender and receiver
  DECLARE v_buyer_balance DECIMAL(10,2);
  DECLARE v_seller_balance DECIMAL(10,2);
  DECLARE newPaymentID INT;
  DECLARE v_payment_reference VARCHAR(100);

  -- Check if the sender and receiver are different accounts
  IF p_buyer = p_seller THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Buyer and Seller cannot be the same account.';
  END IF;

  -- Check if the item ID exists in the item table
  IF NOT EXISTS (SELECT * FROM Item WHERE ItemID = p_item_id) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Item ID does not exist in the Item table.';
  END IF;

-- Check if the item state is acceptable by the admin
  IF EXISTS (SELECT * FROM Item WHERE ItemID = p_item_id AND ItemState = 0) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Item is not acceptable by the admin.';
  END IF;
  
  
  -- Check if the sold date is null for the item
  IF EXISTS (SELECT * FROM Item WHERE ItemID = p_item_id AND SoldDate IS NOT NULL) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Item is already sold.';
  END IF;

  -- Start a transaction
  START TRANSACTION;

  -- Get the balance of sender and receiver from the bank account table
  SELECT Balance INTO v_buyer_balance FROM BankAccount WHERE AccountNumber = p_buyer FOR UPDATE;
  SELECT Balance INTO v_seller_balance FROM BankAccount WHERE AccountNumber = p_seller FOR UPDATE;

  -- Check if the sender has enough balance to transfer
  IF v_buyer_balance >= p_amount THEN
    -- Update the balance of sender and receiver
    UPDATE BankAccount SET Balance = Balance - p_amount WHERE AccountNumber = p_buyer;
    UPDATE BankAccount SET Balance = Balance + p_amount WHERE AccountNumber = p_seller;

    -- Generate the payment reference
    SET v_payment_reference = CONCAT('PAY-', REPLACE(UUID(), '-', ''), '-', DATE_FORMAT(NOW(), '%Y%m%d'));

    -- Insert a new record into the payment table
    INSERT INTO Payment (Amount, PaymentDate, BuyerAccountNo, SellerAccountNo, ItemID, PaymentStatus, PaymentMethod, PaymentReference)
    VALUES (p_amount, NOW(), p_buyer, p_seller, p_item_id, 'Completed', 'Bank Transfer', v_payment_reference);

    -- Get the new payment ID
    SET newPaymentID = LAST_INSERT_ID();

    -- Update the sold date for the item
    UPDATE Item SET SoldDate = NOW(), AuctionStatus = 'Sold' WHERE ItemID = p_item_id;

    -- Commit the changes
    COMMIT;
  ELSE
    -- Rollback the transaction
    ROLLBACK;
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient balance in the sender account.';
  END IF;
END //

DELIMITER ;











DELIMITER //

CREATE PROCEDURE InsertTax(IN p_SellerAccountNo INT, IN p_BuyerAccountNo INT, IN p_TaxType VARCHAR(50), IN p_TaxAmount DECIMAL(10, 2))
BEGIN
  -- This procedure inserts a tax record into the Taxes table with the provided parameters
  INSERT INTO Taxes (SellerAccountNo, BuyerAccountNo, TaxType, TaxAmount)
  VALUES (p_SellerAccountNo, p_BuyerAccountNo, p_TaxType, p_TaxAmount);
END //

CREATE TRIGGER trg_InsertTax
AFTER INSERT ON Payment
FOR EACH ROW
BEGIN
  -- This trigger is executed after each row is inserted into the Payment table
  -- It calls the InsertTax procedure to insert a tax record into the Taxes table
  -- The values for SellerAccountNo, BuyerAccountNo, and TaxAmount are derived from the newly inserted Payment row
  -- The TaxType is set to 'Sales Tax' and the TaxAmount is calculated as 15% of the Payment Amount
  CALL InsertTax(NEW.SellerAccountNo, NEW.BuyerAccountNo, 'Sales Tax', NEW.Amount * 0.15);
END //

DELIMITER ;




-- //comment
-- Explanation:

-- The InsertTax procedure is created to insert a tax record into the Taxes table. It takes p_SellerAccountNo, p_BuyerAccountNo, p_TaxType, and p_TaxAmount as input parameters and inserts a new row into the Taxes table with the provided values.

-- The trg_InsertTax trigger is created to execute after each row is inserted into the Payment table. It calls the InsertTax procedure, passing the SellerAccountNo and BuyerAccountNo from the newly inserted row in the Payment table. The TaxType is set to 'Sales Tax', and the TaxAmount is calculated as 15% of the Amount in the Payment table.

-- The DELIMITER command is used to change the delimiter to // so that the entire procedure and trigger code can be executed as a single statement.

-- The // delimiter is used to separate the procedure and trigger creation statements.

-- Finally, the DELIMITER ; command is used to change the delimiter back to ;

-- //comment








DELIMITER //

CREATE TRIGGER trg_InsertTransaction AFTER INSERT ON Payment FOR EACH ROW
BEGIN
  DECLARE v_BuyerID INT;
  DECLARE v_SellerID INT;

  -- Retrieve BuyerID based on BuyerAccountNo in inserted rows
  SELECT b.buyerid INTO v_BuyerID
  FROM buyer b
  JOIN users u ON b.userid = u.userid
  WHERE u.userID = (SELECT userID FROM bankaccount WHERE accountnumber = NEW.buyeraccountno);

  -- Retrieve SellerID based on SellerAccountNo in inserted rows
  SELECT s.sellerid INTO v_SellerID
  FROM sellers s
  JOIN users u ON s.userid = u.userid
  WHERE u.userID = (SELECT userID FROM bankaccount WHERE accountnumber = NEW.selleraccountno);

  -- Insert into Transaction table
  INSERT INTO Transaction (BuyerID, SellerID, PaymentID, ItemID, TransactionStatus, TransactionDate)
  VALUES (v_BuyerID, v_SellerID, NEW.PaymentID, NEW.ItemID, 'Pending', NOW());

  -- Update TransactionStatus based on PaymentStatus
  UPDATE Transaction
  SET TransactionStatus =
    CASE
      WHEN NEW.PaymentStatus = 'Completed' THEN 'Completed'
      WHEN NEW.PaymentStatus = 'Failed' THEN 'Cancelled'
      ELSE TransactionStatus
    END
  WHERE TransactionID = NEW.PaymentID;
END //

DELIMITER ;













DELIMITER //

CREATE TRIGGER trg_TransactionCompleted
AFTER INSERT ON Transaction
FOR EACH ROW
BEGIN
    DECLARE seller_id INT;
    DECLARE buyer_id INT;
    DECLARE seller_message TEXT;
    DECLARE buyer_message TEXT;
    DECLARE timestamp DATETIME;

    -- Get the necessary data from the inserted row
    SET seller_id = NEW.SellerID;
    SET buyer_id = NEW.BuyerID;
    SET seller_message = CONCAT('Your item has been sold. Transaction ID: ', NEW.TransactionID);
    SET buyer_message = CONCAT('Congratulations! You won the bid. Transaction ID: ', NEW.TransactionID);
    SET timestamp = NOW();

    -- Insert the notification into the Notification table
    INSERT INTO Notification (SellerID, BuyerID, MessageType, SellerMessage, BuyerMessage, Timestamp)
    VALUES (seller_id, buyer_id, 'Sold', seller_message, buyer_message, timestamp);
END //

DELIMITER ;











DELIMITER //

CREATE TRIGGER trg_NotificationCompleted
AFTER INSERT ON Notification
FOR EACH ROW
BEGIN
    DECLARE seller_id INT;
    DECLARE buyer_id INT;
    DECLARE seller_message TEXT;
    DECLARE buyer_message TEXT;

    -- Get the necessary data from the inserted row
    SET seller_id = NEW.SellerID;
    SET buyer_id = NEW.BuyerID;
    SET seller_message = NEW.SellerMessage;
    SET buyer_message = NEW.BuyerMessage;

    -- Update the Seller table with the notification message
    UPDATE Sellers
    SET Message = seller_message
    WHERE SellerID = seller_id;

    -- Update the Buyer table with the notification message
    UPDATE Buyer
    SET Message = buyer_message
    WHERE BuyerID = buyer_id;
END //

DELIMITER ;



DELIMITER //

CREATE PROCEDURE UpdateItemState(IN p_item_id INT)
BEGIN
  -- Check if the item ID exists in the item table
  IF NOT EXISTS (SELECT * FROM Item WHERE ItemID = p_item_id) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Item ID does not exist in the Item table.';
  END IF;

  -- Update the item state to true
  UPDATE Item SET ItemState = 1 WHERE ItemID = p_item_id;
END //

DELIMITER //




CREATE TRIGGER CheckBidIncrement
BEFORE INSERT ON Bid
FOR EACH ROW
BEGIN
  DECLARE max_bid DECIMAL(10, 2);
  
  -- Get the maximum bid amount for the item
  SELECT MAX(BidAmount) INTO max_bid FROM Bid WHERE ItemID = NEW.ItemID;

  -- Check if the difference between the maximum bid and the new bid is less than the minimum increment
  IF (max_bid IS NOT NULL) AND (NEW.BidAmount - max_bid < NEW.MinIncrement) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The difference between the new bid and the maximum bid must be equal to or greater than the minimum increment.';
  END IF;
END //

DELIMITER ;
