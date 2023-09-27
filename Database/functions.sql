
DELIMITER //
CREATE PROCEDURE transfer(IN p_amount DECIMAL(10,2), IN p_Buyer INT, IN p_seller INT)
BEGIN
  -- Declare variables to store the balance of sender and receiver
  DECLARE v_buyer_balance DECIMAL(10,2);
  DECLARE v_seller_balance DECIMAL(10,2);
  DECLARE v_tax DECIMAL(10,2);

  -- Calculate tax amount (15% of the transaction amount)
  SET v_tax = p_amount * 0.15;

  -- Check if the sender and receiver are different accounts
  IF p_Buyer = p_seller THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Buyer and Seller cannot be the same account.';
  END IF;

  -- Start a transaction
  START TRANSACTION;

  -- Get the balance of sender and receiver from the bank account table
  SELECT Balance INTO v_buyer_balance FROM BankAccount WHERE AccountNumber = p_Buyer FOR UPDATE;
  SELECT Balance INTO v_seller_balance FROM BankAccount WHERE AccountNumber = p_seller FOR UPDATE;

  -- Check if the sender has enough balance to transfer (including tax)
  IF v_buyer_balance >= (p_amount + v_tax) THEN
    -- Update the balance of sender and receiver
    UPDATE BankAccount SET Balance = Balance - (p_amount + v_tax) WHERE AccountNumber = p_Buyer;
    UPDATE BankAccount SET Balance = Balance + p_amount WHERE AccountNumber = p_seller;

    -- Insert a new record into the payment table
    INSERT INTO Payment (Amount, PaymentDate, BuyerAccountNo, SellerAccountNo, PaymentStatus, PaymentMethod)
    VALUES (p_amount, NOW(), p_Buyer, p_seller, 'Completed', 'Bank Transfer');

    -- Insert a new record into the Taxes table
    INSERT INTO Taxes (SellerID, BuyerAccountNo, SellerAccountNo, TaxType, TaxAmount)
    VALUES (p_seller, p_Buyer, p_seller, 'Sales Tax', v_tax);

    -- Commit the changes
    COMMIT;
  ELSE
    -- Rollback the transaction
    ROLLBACK;
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient balance in the sender account.';
  END IF;
END //
DELIMITER ;





-------

DELIMITER //

CREATE TRIGGER Payment_Insert_Trigger
AFTER INSERT ON Payment
FOR EACH ROW
BEGIN
    DECLARE newPaymentID INT;

    -- Get the newly inserted payment ID
    SELECT PaymentID INTO newPaymentID FROM Payment WHERE PaymentID = NEW.PaymentID;

    -- Update the PaymentReference field with payment date and payment ID
    UPDATE Payment
    SET PaymentReference = CONCAT(DATE_FORMAT(NEW.PaymentDate, '%Y-%m-%d'), '#', newPaymentID)
    WHERE PaymentID = newPaymentID;
END;
//
DELIMITER ;
