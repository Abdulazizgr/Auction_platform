

-- Create User table
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create Admin table
CREATE TABLE Admin (
    AdminID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create Seller table
CREATE TABLE Seller (
    SellerID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    ItemID INT,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

-- Create Buyer table
CREATE TABLE Buyer (
    BuyerID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    BidID INT,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

-- Create Item table
CREATE TABLE Item (
    ItemID INT AUTO_INCREMENT PRIMARY KEY,
    SellerID INT NOT NULL,
    Title VARCHAR(255) NOT NULL,
    Description TEXT,
    Image BLOB,
    Category VARCHAR(50) NOT NULL,
    StartPrice DECIMAL(10, 2) NOT NULL,
    CurrentBid DECIMAL(10, 2) NOT NULL,
    AuctionStatus ENUM('Active', 'Sold', 'Expired') NOT NULL,
    StartDate DATETIME NOT NULL,
    EndDate DATETIME NOT NULL,
    FOREIGN KEY (SellerID) REFERENCES Seller(SellerID) ON DELETE CASCADE
);

-- Create Bid table
CREATE TABLE Bid (
    BidID INT AUTO_INCREMENT PRIMARY KEY,
    ItemID INT NOT NULL,
    UserID INT NOT NULL,
    BidAmount DECIMAL(10, 2) NOT NULL,
    BeginningBid DECIMAL(10, 2) NOT NULL,
    MinIncrement DECIMAL(10, 2) NOT NULL,
    BidTime DATETIME NOT NULL,
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID) ON DELETE CASCADE,
    FOREIGN KEY (BuyerID) REFERENCES Buyer(BuyerID) ON DELETE CASCADE
);



-- Create Notification table
CREATE TABLE Notification (
    NotificationID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    Message TEXT NOT NULL,
    Timestamp DATETIME NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);


-- Create BankAccount table with Balance
CREATE TABLE BankAccount (
    BankAccountID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    BankName VARCHAR(50) NOT NULL,
    AccountHolderName VARCHAR(50) NOT NULL,
    AccountNumber INT NOT NULL,
    Balance DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    INDEX idx_BuyerAccountNo (AccountNumber),
    INDEX idx_SellerAccountNo (AccountNumber),
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);
-- Create Payment table
CREATE TABLE Payment (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY,
    Amount DECIMAL(10, 2) NOT NULL,
    PaymentDate DATETIME NOT NULL,
    BuyerAccountNo INT NOT NULL,
    SellerAccountNo INT NOT NULL,
    PaymentStatus ENUM('Pending', 'Completed', 'Failed') NOT NULL,
    PaymentMethod ENUM('Credit/Debit Card', 'Bank Transfer', 'PayPal', 'Other') NOT NULL,
    BankName VARCHAR(50),
    AccountHolderName VARCHAR(50),
    AccountNumber INT,
    PaymentReference VARCHAR(50),
    FOREIGN KEY (BuyerAccountNo) REFERENCES BankAccount(AccountNumber) ON DELETE CASCADE,
    FOREIGN KEY (SellerAccountNo) REFERENCES BankAccount(AccountNumber) ON DELETE CASCADE
);


-- Transaction table
CREATE TABLE Transaction (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    SellerID INT NOT NULL,
    PaymentID INT NOT NULL,
    TransactionStatus ENUM('Pending', 'Completed', 'Cancelled') NOT NULL,
    TransactionDate DATETIME NOT NULL,
    FOREIGN KEY (BuyerID) REFERENCES Buyer(BuyerID) ON DELETE CASCADE,
    FOREIGN KEY (SellerID) REFERENCES Seller(SellerID) ON DELETE CASCADE,
    FOREIGN KEY (PaymentID) REFERENCES Payment(PaymentID) ON DELETE CASCADE
);




-- Create Taxes table with SellerID
CREATE TABLE Taxes (
    TaxID INT PRIMARY KEY,
    SellerID INT,
    TaxType VARCHAR(50),
    TaxAmount DECIMAL(10, 2),
    FOREIGN KEY (SellerID) REFERENCES Seller(SellerID) ON DELETE CASCADE
);
