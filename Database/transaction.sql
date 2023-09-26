DELIMITER //
CREATE PROCEDURE transfer(IN p_amount DECIMAL(10,2), IN p_Buyer VARCHAR(10), IN p_seller VARCHAR(10))
BEGIN
  -- Declare variables to store the balance of sender and receiver
  DECLARE v_buyer_balance DECIMAL(10,2);
  DECLARE v_seller_balance DECIMAL(10,2);

  -- Check if the sender and receiver are different accounts
  IF p_Buyer = p_seller THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Buyer and Seller cannot be the same account.';
  END IF;

  -- Start a transaction
  START TRANSACTION;

  -- Get the balance of sender and receiver from the bank account table
  SELECT balance INTO v_buyer_balance FROM bank_account WHERE account_no = p_Buyer FOR UPDATE;
  SELECT balance INTO v_seller_balance FROM bank_account WHERE account_no = p_seller FOR UPDATE;

  -- Check if the sender has enough balance to transfer
  IF v_buyer_balance >= p_amount THEN
    -- Update the balance of sender and receiver
    UPDATE bank_account SET balance = balance - p_amount WHERE account_no = p_Buyer;
    UPDATE bank_account SET balance = balance + p_amount WHERE account_no = p_seller;

    -- Insert a new record into the transaction table
    INSERT INTO transaction (`date`, amount, Buyer, Seller) VALUES (CURDATE(), p_amount, p_Buyer, p_seller);

    -- Commit the changes
    COMMIT;
  ELSE
    -- Rollback the transaction
    ROLLBACK;
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient balance in the sender account.';
  END IF;
END //
DELIMITER ;

CREATE TABLE Transaction (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    date Date not null,
    amount DECIMAL(10,2) NOT NULL,
        Buyer VARCHAR(10) NOT NULL,
        Seller VARCHAR(10) NOT NULL,
    ItemID INT NOT NULL,
    TransactionStatus ENUM('Pending', 'Completed', 'Cancelled') NOT NULL,
    TransactionDate DATETIME NOT NULL,
    FOREIGN KEY (Buyer) REFERENCES bank_account(account_no),
new database_project.sql [dos] (14:59 26/09/2023)                                                                                                                                        1,1 Top
"new database_project.sql" [dos] 51L, 2049B

