-- Create User table
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(50),
    Password VARCHAR(50),
    RegistrationDate DATE,
    BankName VARCHAR(50),
    AccountHolderName VARCHAR(50),
    AccountNumber VARCHAR(50)
);

-- Create Seller table
CREATE TABLE Seller (
    SellerID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    SellerProfile TEXT,
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Create Buyer table
CREATE TABLE Buyer (
    BuyerID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    BuyerProfile TEXT,
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Create Item table
CREATE TABLE Item (
    ItemID INT AUTO_INCREMENT PRIMARY KEY,
    SellerID INT,
    Title VARCHAR(255),
    Description TEXT,
    Image BLOB,
    Category VARCHAR(50),
    StartPrice DECIMAL(10, 2),
    CurrentBid DECIMAL(10, 2),
    AuctionStatus ENUM('Active', 'Sold', 'Expired'),
    StartDate DATETIME,
    EndDate DATETIME,
    FOREIGN KEY (SellerID) REFERENCES Seller(SellerID)
);

-- Create Bid table
CREATE TABLE Bid (
    BidID INT AUTO_INCREMENT PRIMARY KEY,
    ItemID INT,
    BuyerID INT,
    BidAmount DECIMAL(10, 2),
    BeginningBid DECIMAL(10, 2),
    MinIncrement DECIMAL(10, 2),
    BidTime DATETIME,
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID),
    FOREIGN KEY (BuyerID) REFERENCES Buyer(BuyerID)
);

-- Create Transaction table
CREATE TABLE Transaction (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    BuyerID INT,
    SellerID INT,
    ItemID INT,
    PaymentID INT,
    TransactionStatus ENUM('Pending', 'Completed', 'Cancelled'),
    TransactionDate DATETIME,
    FOREIGN KEY (BuyerID) REFERENCES Buyer(BuyerID),
    FOREIGN KEY (SellerID) REFERENCES Seller(SellerID),
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID),
    FOREIGN KEY (PaymentID) REFERENCES Payment(PaymentID)
);

-- Create Payment table
CREATE TABLE Payment (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY,
    Amount DECIMAL(10, 2),
    PaymentDate DATE,
    TransactionID INT,
    PaymentStatus ENUM('Pending', 'Completed', 'Failed'),
    PaymentMethod ENUM('Credit/Debit Card', 'Bank Transfer', 'PayPal', 'Other'),
    BankName VARCHAR(50),
    AccountHolderName VARCHAR(50),
    AccountNumber VARCHAR(50),
    PaymentReference VARCHAR(50) GENERATED ALWAYS AS (
        CONCAT('PAYMENT-', DATE_FORMAT(NOW(), '%Y-%m-%d'), '-', LPAD(AUTO_INCREMENT, 4, '0'))
    ) STORED,
    FOREIGN KEY (TransactionID) REFERENCES Transaction(TransactionID)
);

-- Create Notification table
CREATE TABLE Notification (
    NotificationID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    Message TEXT,
    Timestamp DATETIME,
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

-- Create Admin table
CREATE TABLE Admin (
    AdminID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    AccessLevel ENUM('Low', 'Medium', 'High'),
    Role VARCHAR(255),
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

