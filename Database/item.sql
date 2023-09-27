create database auction;
use auction;
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


-- Insert some users
INSERT INTO User (FirstName, LastName, Email, Password, BankName, AccountHolderName, AccountNumber)
VALUES
    ('John1', 'Doe', 'john1@gmail.com', 'password1', 'Bank1', 'John Doe 1', 1001),
    ('John2', 'Doe', 'john2@gmail.com', 'password2', 'Bank2', 'John Doe 2', 1002),
    ('John3', 'Doe', 'john3@gmail.com', 'password3', 'Bank3', 'John Doe 3', 1003),
    ('John4', 'Doe', 'john4@gmail.com', 'password4', 'Bank4', 'John Doe 4', 1004),
    ('John5', 'Doe', 'john5@gmail.com', 'password5', 'Bank5', 'John Doe 5', 1005),
    ('John6', 'Doe', 'john6@gmail.com', 'password6', 'Bank6', 'John Doe 6', 1006),
    ('John7', 'Doe', 'john7@gmail.com', 'password7', 'Bank7', 'John Doe 7', 1007),
    ('John8', 'Doe', 'john8@gmail.com', 'password8', 'Bank8', 'John Doe 8', 1008),
    ('John9', 'Doe', 'john9@gmail.com', 'password9', 'Bank9', 'John Doe 9', 1009),
    ('John10', 'Doe', 'john10@gmail.com', 'password10', 'Bank10', 'John Doe 10', 1010),
    ('John11', 'Doe', 'john11@gmail.com', 'password11', 'Bank11', 'John Doe 11', 1011),
    ('John12', 'Doe', 'john12@gmail.com', 'password12', 'Bank12', 'John Doe 12', 1012),
    ('John13', 'Doe', 'john13@gmail.com', 'password13', 'Bank13', 'John Doe 13', 1013),
    ('John14', 'Doe', 'john14@gmail.com', 'password14', 'Bank14', 'John Doe 14', 1014),
    ('John15', 'Doe', 'john15@gmail.com', 'password15', 'Bank15', 'John Doe 15', 1015),
    ('John16', 'Doe', 'john16@gmail.com', 'password16', 'Bank16', 'John Doe 16', 1016),
    ('John17', 'Doe', 'john17@gmail.com', 'password17', 'Bank17', 'John Doe 17', 1017),
    ('John18', 'Doe', 'john18@gmail.com', 'password18', 'Bank18', 'John Doe 18', 1018),
    ('John19', 'Doe', 'john19@gmail.com', 'password19', 'Bank19', 'John Doe 19', 1019),
    ('John20', 'Doe', 'john20@gmail.com', 'password20', 'Bank20', 'John Doe 20', 1020),
    ('John21', 'Doe', 'john21@gmail.com', 'password21', 'Bank21', 'John Doe 21', 1021),
    ('John22', 'Doe', 'john22@gmail.com', 'password22', 'Bank22', 'John Doe 22', 1022),
    ('John23', 'Doe', 'john23@gmail.com', 'password23', 'Bank23', 'John Doe 23', 1023),
    ('John24', 'Doe', 'john24@gmail.com', 'password24', 'Bank24', 'John Doe 24', 1024),
    ('John25', 'Doe', 'john25@gmail.com', 'password25', 'Bank25', 'John Doe 25', 1025),
    ('John26', 'Doe', 'john26@gmail.com', 'password26', 'Bank26', 'John Doe 26', 1026),
    ('John27', 'Doe', 'john27@gmail.com', 'password27', 'Bank27', 'John Doe 27', 1027),
    ('John28', 'Doe', 'john28@gmail.com', 'password28', 'Bank28', 'John Doe 28', 1028),
    ('John29', 'Doe', 'john29@gmail.com', 'password29', 'Bank29', 'John Doe 29', 1029),
    ('John30', 'Doe', 'john30@gmail.com', 'password30', 'Bank30', 'John Doe 30', 1030),
    ('John31', 'Doe', 'john31@gmail.com', 'password31', 'Bank31', 'John Doe 31', 1031),
    ('John32', 'Doe', 'john32@gmail.com', 'password32', 'Bank32', 'John Doe 32', 1032),
    ('John33', 'Doe', 'john33@gmail.com', 'password33', 'Bank33', 'John Doe 33', 1033),
    ('John34', 'Doe', 'john34@gmail.com', 'password34', 'Bank34', 'John Doe 34', 1034),
    ('John35', 'Doe', 'john35@gmail.com', 'password35', 'Bank35', 'John Doe 35', 1035),
    ('John36', 'Doe', 'john36@gmail.com', 'password36', 'Bank36', 'John Doe 36', 1036),
    ('John37', 'Doe', 'john37@gmail.com', 'password37', 'Bank37', 'John Doe 37', 1037),
    ('John38', 'Doe', 'john38@gmail.com', 'password38', 'Bank38', 'John Doe 38', 1038),
    ('John39', 'Doe', 'john39@gmail.com', 'password39', 'Bank39', 'John Doe 39', 1039),
    ('John40', 'Doe', 'john40@gmail.com', 'password40', 'Bank40', 'John Doe 40', 1040),
    ('John41', 'Doe', 'john41@gmail.com', 'password41', 'Bank41', 'John Doe 41', 1041),
    ('John42', 'Doe', 'john42@gmail.com', 'password42', 'Bank42', 'John Doe 42', 1042),
    ('John43', 'Doe', 'john43@gmail.com', 'password43', 'Bank43', 'John Doe 43', 1043),
    ('John44', 'Doe', 'john44@gmail.com', 'password44', 'Bank44', 'John Doe 44', 1044),
    ('John45', 'Doe', 'john45@gmail.com', 'password45', 'Bank45', 'John Doe 45', 1045),
    ('John46', 'Doe', 'john46@gmail.com', 'password46', 'Bank46', 'John Doe 46', 1046),
    ('John47', 'Doe', 'john47@gmail.com', 'password47', 'Bank47', 'John Doe 47', 1047),
    ('John48', 'Doe', 'john48@gmail.com', 'password48', 'Bank48', 'John Doe 48', 1048),
    ('John49', 'Doe', 'john49@gmail.com', 'password49', 'Bank49', 'John Doe 49', 1049),
    ('John50', 'Doe', 'john50@gmail.com', 'password50', 'Bank50', 'John Doe 50', 1050);

select * from User;



-- Insert admin
INSERT INTO Admin (FirstName, LastName, Email, Password)
VALUES ('Abdulaziz', 'Isa', 'abdu1234@gmail.com', 'admin1234');
select * from Admin;


INSERT INTO Item(SellerID,Title,Description,Image,Category,StartPrice,CurrentBid,AuctionStatus,StartDate,EndDate) 
VALUES (1,"Paint","An original painting by , Leonardo da Vinci created in 1503-1519.",LOAD_FILE('//path//to//Monalisa.jpg'),"Art",  10000.00,12000.00,"Active","2023-09-27 10:00:00","2023-10-04 18:00:00"  ),
(2, "Vintage Watch", "A classic vintage wristwatch from the 1960s.", LOAD_FILE('//path//to//watch.jpg'), "Fashion", 500.00, 600.00, "Active", "2023-09-28 09:00:00", "2023-10-05 17:00:00"),
 (3,  "Vintage Vinyl Records", "A collection of rare vintage vinyl records from various artists.",LOAD_FILE('//path//to//Vintage Vinyl Record.jpg'), "Music", 300.00, 350.00, "Active", "2023-10-01 11:00:00", "2023-10-08 19:00:00"),
 ( 4,"Diamond Necklace", "A stunning diamond necklace with a platinum setting.", LOAD_FILE('//path//to//Diamond Necklace image.jpg'), "Jewelry", 5000.00, 6000.00, "Active", "2023-09-30 14:00:00", "2023-10-07 22:00:00"),
( 5,"Collectible Coins", "A collection of rare and valuable collectible coins from different eras.",LOAD_FILE('//path//to//Ethiopian coin.jpg'), "Collectibles", 400.00, 450.00, "Active", "2023-10-04 10:00:00", "2023-10-11 18:00:00"),
( 6,"Sports Memorabilia", "A collection of autographed sports memorabilia from famous athletes.", LOAD_FILE('//path//to//NBA.jpg'), "Sports", 800.00, 900.00, "Active", "2023-10-02 15:00:00", "2023-10-09 23:00:00"),
( 7,"Designer Handbag", "A luxurious designer handbag made from high-quality leather.", LOAD_FILE('//path//to//Bags.jpg'), "Fashion", 700.00, 800.00, "Active", "2023-10-05 12:00:00", "2023-10-12 20:00:00"),
(8, "Vintage Camera", "A classic vintage camera from the 1950s with original accessories.", LOAD_FILE('//path//to//Camera.jpg'), "Electronics", 400.00, 450.00, "Active", "2023-10-07 10:00:00", "2023-10-14 18:00:00"),
( 9, "Rare Stamps", "A collection of rare postage stamps from different countries.", LOAD_FILE('//path//to//Stamps.jpg'), "Collectibles", 200.00, 250.00, "Active", "2023-10-06 09:00:00", "2023-10-13 17:00:00"),
( 10,"Art Deco Sculpture", "An exquisite art deco sculpture made from bronze.", LOAD_FILE('//path//to//Art deco sculpture.jpg'), "Art", 1200.00, 1400.00, "Active", "2023-10-08 09:00:00", "2023-10-15 17:00:00");

select * from Item;