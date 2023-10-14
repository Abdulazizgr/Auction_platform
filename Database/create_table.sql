-- Users Table to
CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each user
    FirstName VARCHAR(50) NOT NULL, -- First name of the user
    LastName VARCHAR(50) NOT NULL, -- Last name of the user
    Email VARCHAR(50) NOT NULL UNIQUE, -- Email address of the user (must be unique)
    Password VARCHAR(50) NOT NULL, -- Password of the user
    RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- Date and time when the user registered
);

-- Item Table
CREATE TABLE Item (
    ItemID INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each item
    Title VARCHAR(255) NOT NULL, -- Title of the item
    Description TEXT, -- Description of the item
    ImagePath VARCHAR(255), -- Path to the image of the item
    ItemState BOOLEAN DEFAULT FALSE, -- State of the item (e.g., available or not)
    Category VARCHAR(50) NOT NULL, -- Category of the item
    StartPrice DECIMAL(10, 2) NOT NULL, -- Starting price of the item
    CurrentBid DECIMAL(10, 2) NOT NULL, -- Current highest bid on the item
    AuctionStatus ENUM('Active', 'Sold', 'Expired') NOT NULL, -- Status of the auction
    StartDate DATETIME NOT NULL, -- Date and time when the auction starts
    EndDate DATETIME NOT NULL, -- Date and time when the auction ends
    UserID INT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE SET NULL
);

-- Sellers Table
CREATE TABLE Sellers (
    SellerID INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each seller
    UserID INT NOT NULL, -- Foreign key referencing the UserID in the Users table
    ItemID INT, -- Foreign key referencing the ItemID in the Item table
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE, -- Constraint to ensure the user exists
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID) ON DELETE SET NULL -- Constraint to ensure the item exists (can be null if item is removed)
);

-- Admins Table
CREATE TABLE Admins (
    AdminID INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each admin
    FirstName VARCHAR(50) NOT NULL, -- First name of the admin
    LastName VARCHAR(50) NOT NULL, -- Last name of the admin
    Email VARCHAR(50) NOT NULL UNIQUE, -- Email address of the admin (must be unique)
    Password VARCHAR(50) NOT NULL, -- Password of the admin
    ItemID INT NULL, -- Foreign key referencing the ItemID in the Item table (can be null)
    RegistrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Date and time when the admin registered
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID) ON DELETE SET NULL -- Constraint to ensure the item exists (can be null if item is removed)
);

-- Buyer Table
CREATE TABLE Buyer (
    BuyerID INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each buyer
    UserID INT NOT NULL, -- Foreign key referencing the UserID in the Users table
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE -- Constraint to ensure the user exists
);

-- Bid Table
CREATE TABLE Bid (
    BidID INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each bid
    ItemID INT NOT NULL, -- Foreign key referencing the ItemID in the Item table
    UserID INT NOT NULL, -- Foreign key referencing the BuyerID in the Buyer table
    BidAmount DECIMAL(10, 2) NOT NULL, -- Amount of the bid
    MinIncrement DECIMAL(10, 2) NOT NULL, -- Minimum increment for the bid
    BidTime DATETIME NOT NULL, -- Date and time when the bid was placed
    FOREIGN KEY (ItemID) REFERENCES Item(ItemID) ON DELETE CASCADE, -- Constraint to ensure the item exists
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE -- Constraint to ensure the buyer exists
);

-- Notification Table
CREATE TABLE Notification (
    NotificationID INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each notification
    SellerID INT NOT NULL, -- Foreign key referencing the SellerID in the Sellers table
    BuyerID INT NOT NULL, -- Foreign key referencing the BuyerID in the Buyer table
    MessageType ENUM('Sold', 'BidWon') NOT NULL, -- Type of message (sold or bid won)
    SellerMessage TEXT NOT NULL, -- Message content for the seller
    BuyerMessage TEXT NOT NULL, -- Message content for the buyer
    Timestamp DATETIME NOT NULL, -- Date and time when the notification was sent
    FOREIGN KEY (SellerID) REFERENCES Sellers(SellerID) ON DELETE CASCADE, -- Constraint to ensure the seller exists
    FOREIGN KEY (BuyerID) REFERENCES Buyer(BuyerID) ON DELETE CASCADE -- Constraint to ensure the buyer exists
);

-- BankAccount Table
CREATE TABLE BankAccount (
    BankAccountID INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each bank account
    UserID INT NOT NULL, -- Foreign key referencing the UserID in the Users table
    BankName VARCHAR(50) NOT NULL, -- Name of the bank
    AccountHolderName VARCHAR(50) NOT NULL, -- Name of the account holder
    AccountNumber INT NOT NULL, -- Account number
    Balance DECIMAL(10, 2) NOT NULL DEFAULT 0.00, -- Current balance of the account
    INDEX idx_BuyerAccountNo (AccountNumber), -- Index for buyer account number
    INDEX idx_SellerAccountNo (AccountNumber), -- Index for seller account number
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE -- Constraint to ensure the user exists
);

-- Payment Table
CREATE TABLE Payment (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each payment
    Amount DECIMAL(10, 2) NOT NULL, -- Amount of the payment
    PaymentDate DATETIME NOT NULL, -- Date and time of the payment
    BuyerAccountNo INT NOT NULL, -- Account number of the buyer
    SellerAccountNo INT NOT NULL, -- Account number of the seller
    PaymentStatus ENUM('Pending', 'Completed', 'Failed') NOT NULL, -- Status of the payment
    PaymentMethod ENUM('Credit/Debit Card', 'Bank Transfer', 'PayPal', 'Other') NOT NULL, -- Payment method used
    BankName VARCHAR(50), -- Name of the bank (if applicable)
    AccountHolderName VARCHAR(50), -- Name of the account holder (if applicable)
    AccountNumber INT, -- Account number (if applicable)
    PaymentReference VARCHAR(50), -- Reference for the payment
    FOREIGN KEY (BuyerAccountNo) REFERENCES BankAccount(AccountNumber) ON DELETE CASCADE, -- Constraint to ensure the buyer account exists
    FOREIGN KEY (SellerAccountNo) REFERENCES BankAccount(AccountNumber) ON DELETE CASCADE -- Constraint to ensure the seller account exists
);

-- Transaction Table
CREATE TABLE Transaction (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each transaction
    BuyerID INT NOT NULL, -- Foreign key referencing the BuyerID in the Buyer table
    SellerID INT NOT NULL, -- Foreign key referencing the SellerID in the Sellers table
    PaymentID INT NOT NULL, -- Foreign key referencing the PaymentID in the Payment table
    TransactionStatus ENUM('Pending', 'Completed', 'Cancelled') NOT NULL, -- Status of the transaction
    TransactionDate DATETIME NOT NULL, -- Date and time of the transaction
    FOREIGN KEY (BuyerID) REFERENCES Buyer(BuyerID) ON DELETE CASCADE, -- Constraint to ensure the buyer exists
    FOREIGN KEY (SellerID) REFERENCES Sellers(SellerID) ON DELETE CASCADE, -- Constraint to ensure the seller exists
    FOREIGN KEY (PaymentID) REFERENCES Payment(PaymentID) ON DELETE CASCADE -- Constraint to ensure the payment exists
);

-- Taxes Table
CREATE TABLE Taxes (
    TaxID INT PRIMARY KEY, -- Unique identifier for each tax
    SellerID INT, -- Foreign key referencing the SellerID in the Sellers table
    TaxType VARCHAR(50), -- Type of tax
    TaxAmount DECIMAL(10, 2), -- Amount of tax
    FOREIGN KEY (SellerID) REFERENCES Sellers(SellerID) ON DELETE CASCADE -- Constraint to ensure the seller exists
);
