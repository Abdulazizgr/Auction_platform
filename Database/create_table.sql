USE auction;
-- Create User table 
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    BankName VARCHAR(50) NOT NULL,
    AccountHolderName VARCHAR(50) NOT NULL,
    AccountNumber INT NOT NULL
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
    SellerProfile TEXT,
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Create Buyer table
CREATE TABLE Buyer (
    BuyerID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    BuyerProfile TEXT,
    FOREIGN KEY (UserID) REFERENCES User(UserID)
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
    FOREIGN KEY (SellerID) REFERENCES Seller(SellerID)
);

-- Create Bid table
CREATE TABLE Bid (
    BidID INT AUTO_INCREMENT PRIMARY KEY,
    ItemID INT NOT NULL,
    BuyerID INT NOT NULL,
    BidAmount DECIMAL(10, 2) NOT NULL,
    BeginningBid DECIMAL(10, 2) NOT NULL,
    MinIncrement DECIMAL(10, 2) NOT NULL,
    BidTime DATETIME NOT NULL,
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID),
    FOREIGN KEY (BuyerID) REFERENCES Buyer(BuyerID)
);

-- Create Transaction table
CREATE TABLE Transaction (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    BuyerID INT NOT NULL,
    SellerID INT NOT NULL,
    ItemID INT NOT NULL,
    TransactionStatus ENUM('Pending', 'Completed', 'Cancelled') NOT NULL,
    TransactionDate DATETIME NOT NULL,
    FOREIGN KEY (BuyerID) REFERENCES Buyer(BuyerID),
    FOREIGN KEY (SellerID) REFERENCES Seller(SellerID),
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
);

-- Create Notification table
CREATE TABLE Notification (
    NotificationID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    Message TEXT NOT NULL,
    Timestamp DATETIME NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Create Payment table
CREATE TABLE Payment (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY,
    Amount DECIMAL(10, 2) NOT NULL,
    PaymentDate DATE NOT NULL,
    TransactionID INT NOT NULL,
    PaymentStatus ENUM('Pending', 'Completed', 'Failed') NOT NULL,
    PaymentMethod ENUM('Credit/Debit Card', 'Bank Transfer', 'PayPal', 'Other') NOT NULL,
    BankName VARCHAR(50),
    AccountHolderName VARCHAR(50),
    AccountNumber  INT,
    PaymentReference VARCHAR(50),
    FOREIGN KEY (TransactionID) REFERENCES Transaction(TransactionID)
);

-- Add foreign key constraint to Transaction table
ALTER TABLE Transaction
ADD CONSTRAINT FK_Payment_Transaction
FOREIGN KEY (TransactionID) REFERENCES Payment(TransactionID);
