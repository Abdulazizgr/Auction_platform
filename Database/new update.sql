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
END //

DELIMITER ;