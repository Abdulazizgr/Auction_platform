
DELIMITER //

CREATE PROCEDURE AddItemToSeller(IN p_ItemID INT, IN p_UserID INT)
BEGIN
    -- This procedure adds an item to the Sellers table with the provided ItemID and UserID
    INSERT INTO Sellers (UserID, ItemID)
    VALUES (p_UserID, p_ItemID);
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

CREATE PROCEDURE transfer(IN p_amount DECIMAL(10,2), IN p_Buyer INT, IN p_seller INT)
BEGIN
  -- Declare variables to store the balance of sender and receiver
  DECLARE v_buyer_balance DECIMAL(10,2);
  DECLARE v_seller_balance DECIMAL(10,2);
  DECLARE newPaymentID INT;
  DECLARE v_payment_reference VARCHAR(100);

  -- Check if the sender and receiver are different accounts
  IF p_Buyer = p_seller THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Buyer and Seller cannot be the same account.';
  END IF;

  -- Start a transaction
  START TRANSACTION;

  -- Get the balance of sender and receiver from the bank account table
  SELECT Balance INTO v_buyer_balance FROM BankAccount WHERE AccountNumber = p_Buyer FOR UPDATE;
  SELECT Balance INTO v_seller_balance FROM BankAccount WHERE AccountNumber = p_seller FOR UPDATE;

  -- Check if the sender has enough balance to transfer
  IF v_buyer_balance >= p_amount THEN
    -- Update the balance of sender and receiver
    UPDATE BankAccount SET Balance = Balance - p_amount WHERE AccountNumber = p_Buyer;
    UPDATE BankAccount SET Balance = Balance + p_amount WHERE AccountNumber = p_seller;

    -- Generate the payment reference
    SET v_payment_reference = CONCAT('PAY-', REPLACE(UUID(), '-', ''), '-', DATE_FORMAT(NOW(), '%Y%m%d'));

    -- Insert a new record into the payment table
    INSERT INTO Payment (Amount, PaymentDate, BuyerAccountNo, SellerAccountNo, PaymentStatus, PaymentMethod, PaymentReference)
    VALUES (p_amount, NOW(), p_Buyer, p_seller, 'Completed', 'Bank Transfer', v_payment_reference);

    -- Get the new payment ID
    SET newPaymentID = LAST_INSERT_ID();

    -- Commit the changes
    COMMIT;
  ELSE
    -- Rollback the transaction
    ROLLBACK;
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient balance in the sender account.';
  END IF;
END //

DELIMITER ;




-- // comment
-- Explanation:

-- The procedure transfer is created to facilitate transferring funds from one bank account to another.

-- The variables v_buyer_balance and v_seller_balance are declared to store the balance of the sender and receiver, respectively.

-- An IF statement checks if the buyer and seller are the same account. If they are, an exception is raised to prevent the transfer.

-- The transaction is started using START TRANSACTION. This ensures that the transfer is atomic and all changes will be rolled back if any error occurs.

-- The balances of the sender and receiver are retrieved from the BankAccount table using SELECT INTO statements. The FOR UPDATE clause is used to lock the rows to prevent concurrent modifications.

-- An IF statement checks if the sender has enough balance to transfer the specified amount. If not, an exception is raised to indicate insufficient balance.

-- The balances of the sender and receiver are updated in the BankAccount table using UPDATE statements.

-- A payment reference is generated by concatenating a prefix, a unique identifier, and the current date.

-- A new record is inserted into the Payment table, capturing details such as the amount, payment date, buyer account number, seller account number, payment status, payment method, and payment reference.

-- The ID of the newly inserted payment record is retrieved using LAST_INSERT_ID() and stored in the newPaymentID variable.

-- Finally, if all steps are successful, the changes are committed using COMMIT. Otherwise, if there is an error or insufficient balance, the transaction is rolled back using ROLLBACK, and an exception is raised to notify the error condition.



-- //comment



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

