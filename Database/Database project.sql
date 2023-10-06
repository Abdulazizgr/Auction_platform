CREATE TABLE bank_account (
  account_no VARCHAR(10) PRIMARY KEY,
  balance DECIMAL(10,2) NOT NULL,
  owner VARCHAR(50) NOT NULL
);

CREATE TABLE transaction (
  id INT AUTO_INCREMENT PRIMARY KEY,
  date DATE NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  sender VARCHAR(10) NOT NULL,
  receiver VARCHAR(10) NOT NULL,
  FOREIGN KEY (sender) REFERENCES bank_account(account_no),
  FOREIGN KEY (receiver) REFERENCES bank_account(account_no)
);

INSERT INTO bank_account (account_no, balance, owner) VALUES 
('A001', 1000.00, 'Alice'),
('A002', 2000.00, 'Bob'),
('A003', 3000.00, 'Charlie'),
('A004', 4000.00, 'David'),
('A005', 5000.00, 'Eve'),
('A006', 6000.00, 'Frank'),
('A007', 7000.00, 'Grace'),
('A008', 8000.00, 'Harry'),
('A009', 9000.00, 'Iris'),
('A010', 10000.00, 'Jack'),
('A011', 11000.00, 'Kelly'),
('A012', 12000.00, 'Leo'),
('A013', 13000.00, 'Mia'),
('A014', 14000.00, 'Noah'),
('A015', 15000.00, 'Olivia'),
('A016', 16000.00, 'Peter'),
('A017', 17000.00, 'Quinn'),
('A018', 18000.00, 'Ruby'),
('A019', 19000.00, 'Sam'),
('A020', 20000.00, 'Tina'),
('A021', 21000.00, 'Uma'),
('A022', 22000.00, 'Victor'),
('A023', 23000.00, 'Wendy'),
('A024', 24000.00, 'Xavier'),
('A025', 25000.00, 'Yara'),
('A026', 26000.00, 'Zack'),
('B001', 2700.00, 'Adam'),
('B002', 2800.00, 'Beth'),
('B003', 2900.00, 'Carl'),
('B004', 3000.00, 'Dana'),
('B005', 3100.00, 'Eric'),
('B006', 3200.00, 'Fiona'),
('B007', 3300.00, 'Gary'),
('B008', 3400.00, 'Helen'),
('B009', 3500.00, 'Ian'),
('B010', 3600.00, 'Jill'),
('B011', 3700.00, 'Ken'),
('B012', 3800.00, 'Lily'),
('B013', 3900.00, 'Mark'),
('B014', 4000.00, 'Nina'),
('B015', 4100.00, 'Oscar'),
('B016', 4200.00, 'Pamela'),
('B017', 4300.00, 'Quentin'),
('B018', 4400.00, 'Rachel'),
('B019', 4500.00, 'Scott'),
('B020', 4600.00, 'Tracy'),
('B021', 4700.00, 'Umar'),
('B022', 4800.00, 'Vanessa'),
('B023', 4900.00, 'Wayne'),
('B024', 5000.00, 'Xena');





DELIMITER //
CREATE PROCEDURE transfer(IN p_amount DECIMAL(10,2), IN p_sender VARCHAR(10), IN p_receiver VARCHAR(10))
BEGIN
  -- Declare variables to store the balance of sender and receiver
  DECLARE v_sender_balance DECIMAL(10,2);
  DECLARE v_receiver_balance DECIMAL(10,2);
  
  -- Check if the sender and receiver are different accounts
  IF p_sender = p_receiver THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Sender and receiver cannot be the same account.';
  END IF;
  
  -- Start a transaction
  START TRANSACTION;
  
  -- Get the balance of sender and receiver from the bank account table
  SELECT balance INTO v_sender_balance FROM bank_account WHERE account_no = p_sender FOR UPDATE;
  SELECT balance INTO v_receiver_balance FROM bank_account WHERE account_no = p_receiver FOR UPDATE;
  
  -- Check if the sender has enough balance to transfer
  IF v_sender_balance >= p_amount THEN
    -- Update the balance of sender and receiver
    UPDATE bank_account SET balance = balance - p_amount WHERE account_no = p_sender;
    UPDATE bank_account SET balance = balance + p_amount WHERE account_no = p_receiver;
    
    -- Insert a new record into the transaction table
    INSERT INTO transaction (`date`, amount, sender, receiver) VALUES (CURDATE(), p_amount, p_sender, p_receiver);
    
    -- Commit the changes
    COMMIT;
  ELSE
    -- Rollback the transaction
    ROLLBACK;
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient balance in the sender account.';
  END IF;
END //
DELIMITER ;

CALL transfer(500.00, 'A001', 'A002');