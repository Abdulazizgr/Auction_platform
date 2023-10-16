CREATE TABLE Buyer (
    BuyerID INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each buyer
    UserID INT NOT NULL, -- Foreign key referencing the UserID in the Users table
    ItemID INT NOT NULL, -- Foreign key referencing the ItemID in the Items table
    Message Varchar(500),
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE, -- Constraint to ensure the user exists
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID) ON DELETE CASCADE -- Constraint to ensure the item exists
);

CREATE TABLE Sellers (
    SellerID INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each seller
    UserID INT NOT NULL, -- Foreign key referencing the UserID in the Users table
    ItemID INT, -- Foreign key referencing the ItemID in the Item table
    Message Varchar(500),
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE, -- Constraint to ensure the user exists
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID) ON DELETE SET NULL -- Constraint to ensure the item exists (can be null if item is removed)
);


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
  INSERT INTO Transaction (BuyerID, SellerID, PaymentID, TransactionStatus, TransactionDate)
  VALUES (v_BuyerID, v_SellerID, NEW.PaymentID, 'Pending', NOW());

  -- Update TransactionStatus based on PaymentStatus
  UPDATE Transaction
  SET TransactionStatus =
    CASE
      WHEN NEW.PaymentStatus = 'Completed' THEN 'Completed'
      WHEN NEW.PaymentStatus = 'Failed' THEN 'Cancelled'
      ELSE TransactionStatus
    END
  WHERE TransactionID = NEW.PaymentID;
  
  -- Insert notification messages for the buyer and seller
  INSERT INTO Notification (BuyerID, Message)
  VALUES (v_BuyerID, 'Your transaction has been completed successfully.');

  INSERT INTO Notification (SellerID, Message)
  VALUES (v_SellerID, 'Your transaction has been completed successfully.');
END //

DELIMITER ;