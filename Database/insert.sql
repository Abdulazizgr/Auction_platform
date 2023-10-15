-- Insert some users
INSERT INTO Users (FirstName, LastName, Email, Password)
VALUES
    ('John1', 'Doe', 'john1@gmail.com', 'password1'),
    ('John2', 'Doe', 'john2@gmail.com', 'password2'),
    ('John3', 'Doe', 'john3@gmail.com', 'password3'),
    ('John4', 'Doe', 'john4@gmail.com', 'password4'),
    ('John5', 'Doe', 'john5@gmail.com', 'password5'),
    ('John6', 'Doe', 'john6@gmail.com', 'password6'),
    ('John7', 'Doe', 'john7@gmail.com', 'password7'),
    ('John8', 'Doe', 'john8@gmail.com', 'password8'),
    ('John9', 'Doe', 'john9@gmail.com', 'password9'),
    ('John10', 'Doe', 'john10@gmail.com', 'password10'),
    ('John11', 'Doe', 'john11@gmail.com', 'password11'),
    ('John12', 'Doe', 'john12@gmail.com', 'password12'),
    ('John13', 'Doe', 'john13@gmail.com', 'password13'),
    ('John14', 'Doe', 'john14@gmail.com', 'password14'),
    ('John15', 'Doe', 'john15@gmail.com', 'password15'),
    ('John16', 'Doe', 'john16@gmail.com', 'password16'),
    ('John17', 'Doe', 'john17@gmail.com', 'password17'),
    ('John18', 'Doe', 'john18@gmail.com', 'password18'),
    ('John19', 'Doe', 'john19@gmail.com', 'password19'),
    ('John20', 'Doe', 'john20@gmail.com', 'password20'),
    ('John21', 'Doe', 'john21@gmail.com', 'password21'),
    ('John22', 'Doe', 'john22@gmail.com', 'password22'),
    ('John23', 'Doe', 'john23@gmail.com', 'password23'),
    ('John24', 'Doe', 'john24@gmail.com', 'password24'),
    ('John25', 'Doe', 'john25@gmail.com', 'password25'),
    ('John26', 'Doe', 'john26@gmail.com', 'password26'),
    ('John27', 'Doe', 'john27@gmail.com', 'password27'),
    ('John28', 'Doe', 'john28@gmail.com', 'password28'),
    ('John29', 'Doe', 'john29@gmail.com', 'password29'),
    ('John30', 'Doe', 'john30@gmail.com', 'password30'),
    ('John31', 'Doe', 'john31@gmail.com', 'password31'),
    ('John32', 'Doe', 'john32@gmail.com', 'password32'),
    ('John33', 'Doe', 'john33@gmail.com', 'password33'),
    ('John34', 'Doe', 'john34@gmail.com', 'password34'),
    ('John35', 'Doe', 'john35@gmail.com', 'password35'),
    ('John36', 'Doe', 'john36@gmail.com', 'password36'),
    ('John37', 'Doe', 'john37@gmail.com', 'password37'),
    ('John38', 'Doe', 'john38@gmail.com', 'password38'),
    ('John39', 'Doe', 'john39@gmail.com', 'password39'),
    ('John40', 'Doe', 'john40@gmail.com', 'password40'),
    ('John41', 'Doe', 'john41@gmail.com', 'password41'),
    ('John42', 'Doe', 'john42@gmail.com', 'password42'),
    ('John43', 'Doe', 'john43@gmail.com', 'password43'),
    ('John44', 'Doe', 'john44@gmail.com', 'password44'),
    ('John45', 'Doe', 'john45@gmail.com', 'password45'),
    ('John46', 'Doe', 'john46@gmail.com', 'password46'),
    ('John47', 'Doe', 'john47@gmail.com', 'password47'),
    ('John48', 'Doe', 'john48@gmail.com', 'password48'),
    ('John49', 'Doe', 'john49@gmail.com', 'password49'),
    ('John50', 'Doe', 'john50@gmail.com', 'password50');

INSERT INTO Admins (FirstName, LastName, Email, Password)
VALUES ('Abdulaziz', 'Isa', 'abdu1234@gmail.com', 'admin1234');
INSERT INTO Admins (FirstName, LastName, Email, Password, ItemID)
VALUES
('John', 'Doe', 'johndoe@example.com', 'password1', NULL),
('Jane', 'Smith', 'janesmith@example.com', 'password2', NULL),
('Michael', 'Johnson', 'michaeljohnson@example.com', 'password3', NULL),
('Emily', 'Davis', 'emilydavis@example.com', 'password4', NULL),
('David', 'Wilson', 'davidwilson@example.com', 'password5', NULL);

INSERT INTO BankAccount (UserID, BankName, AccountHolderName, AccountNumber, Balance)
VALUES
    (1, 'Bank A', 'John1', 1001, 1000.00),
    (2, 'Bank B', 'John2', 1002, 2000.00),
    (3, 'Bank C', 'John3', 1003, 3000.00),
    (4, 'Bank D', 'John4', 1004, 4000.00),
    (5, 'Bank E', 'John5', 1005, 5000.00),
    (6, 'Bank F', 'John6', 1006, 6000.00),
    (7, 'Bank G', 'John7', 1007, 7000.00),
    (8, 'Bank H', 'John8', 1008, 8000.00),
    (9, 'Bank I', 'John9', 1009, 9000.00),
    (10, 'Bank J', 'John10', 1010, 10000.00),
    (11, 'Bank K', 'John11', 1011, 11000.00),
    (12, 'Bank L', 'John12', 1012, 12000.00),
    (13, 'Bank M', 'John13', 1013, 13000.00),
    (14, 'Bank N', 'John14', 1014, 14000.00),
    (15, 'Bank O', 'John15', 1015, 15000.00),
    (16, 'Bank P', 'John16', 1016, 16000.00),
    (17, 'Bank Q', 'John17', 1017, 17000.00),
    (18, 'Bank R', 'John18', 1018, 18000.00),
    (19, 'Bank S', 'John19', 1019, 19000.00),
    (20, 'Bank T', 'John20', 1020, 20000.00),
    (21, 'Bank U', 'John21', 1021, 21000.00),
    (22, 'Bank V', 'John22', 1022, 22000.00),
    (23, 'Bank W', 'John23', 1023, 23000.00),
    (24, 'Bank X', 'John24', 1024, 24000.00),
    (25, 'Bank Y', 'John25', 1025, 25000.00),
    (26, 'Bank Z', 'John26', 1026, 26000.00),
    (27, 'Bank AA', 'John27', 1027, 27000.00),
    (28, 'Bank BB', 'John28', 1028, 28000.00),
    (29, 'Bank CC', 'John29', 1029, 29000.00),
    (30, 'Bank DD', 'John30', 1030, 30000.00),
    (31, 'Bank EE', 'John31', 1031, 31000.00),
    (32, 'Bank FF', 'John32', 1032, 32000.00),
    (33, 'Bank GG', 'John33', 1033, 33000.00),
    (34, 'Bank HH', 'John34', 1034, 34000.00),
    (35, 'Bank II', 'John35', 1035, 35000.00),
    (36, 'Bank JJ', 'John36', 1036, 36000.00),
    (37, 'Bank KK', 'John37', 1037, 37000.00),
    (38, 'Bank LL', 'John38', 1038, 38000.00),
    (39, 'Bank MM', 'John39', 1039, 39000.00),
    (40, 'Bank NN', 'John40', 1040, 40000.00),
    (41, 'Bank OO', 'John41', 1041, 41000.00),
    (42, 'Bank PP', 'John42', 1042, 42000.00),
    (43, 'Bank QQ', 'John43', 1043, 43000.00),
    (44, 'Bank RR', 'John44', 1044, 44000.00),
    (45, 'Bank SS', 'John45', 1045, 45000.00),
    (46, 'Bank TT', 'John46', 1046, 46000.00),
    (47, 'Bank UU', 'John47', 1047, 47000.00),
    (48, 'Bank VV', 'John48', 1048, 48000.00),
    (49, 'Bank WW', 'John49', 1049, 49000.00),
    (50, 'Bank XX', 'John50', 1050, 50000.00);




INSERT INTO Item (Title, Description, ImagePath, ItemState, Category, StartPrice, CurrentBid, AuctionStatus, StartDate, EndDate, UserID)
VALUES
('iPhone 12 Pro', 'Description for iPhone 12 Pro', '/images/iphone12pro.jpg', 1, 'Electronics', 999.99, 999.99, 'Active', NOW(), NOW(), 1),
('Samsung Galaxy S21', 'Description for Samsung Galaxy S21', '/images/galaxys21.jpg', 1, 'Electronics', 899.99, 899.99, 'Active', NOW(), NOW(), 2),
('Nike Air Max', 'Description for Nike Air Max', '/images/airmax.jpg', 1, 'Footwear', 129.99, 129.99, 'Active', NOW(), NOW(), 3),
('Sony PlayStation 5', 'Description for Sony PlayStation 5', '/images/ps5.jpg', 1, 'Electronics', 499.99, 499.99, 'Active', NOW(), NOW(), 4),
('Levi\'s Jeans', 'Description for Levi\'s Jeans', '/images/levisjeans.jpg', 1, 'Apparel', 79.99, 79.99, 'Active', NOW(), NOW(), 5),
('Canon EOS R5', 'Description for Canon EOS R5', '/images/eosr5.jpg', 1, 'Electronics', 3499.99, 3499.99, 'Active', NOW(), NOW(), 6),
('Apple Watch Series 6', 'Description for Apple Watch Series 6', '/images/applewatch.jpg', 1, 'Electronics', 399.99, 399.99, 'Active', NOW(), NOW(), 7),
('Adidas Ultraboost', 'Description for Adidas Ultraboost', '/images/ultraboost.jpg', 1, 'Footwear', 169.99, 169.99, 'Active', NOW(), NOW(), 8),
('Samsung 65" 4K TV', 'Description for Samsung 65" 4K TV', '/images/samsungtv.jpg', 1, 'Electronics', 899.99, 899.99, 'Active', NOW(), NOW(), 9),
('Coach Handbag', 'Description for Coach Handbag', '/images/coachhandbag.jpg', 1, 'Accessories', 249.99, 249.99, 'Active', NOW(), NOW(), 10);


INSERT INTO Bid (ItemID, UserID, BidAmount, MinIncrement, BidTime)
VALUES 
    (1, 1, 100.00, 10.00, '2022-01-01 09:00:00'),
    (2, 2, 150.00, 10.00, '2022-01-02 10:00:00'),
    (3, 3, 200.00, 20.00, '2022-01-03 11:00:00'),
    (4, 4, 250.00, 10.00, '2022-01-04 12:00:00'),
    (5, 5, 300.00, 10.00, '2022-01-05 13:00:00'),
    (6, 6, 350.00, 20.00, '2022-01-06 14:00:00'),
    (7, 7, 400.00, 10.00, '2022-01-07 15:00:00'),
    (8, 8, 450.00, 10.00, '2022-01-08 16:00:00'),
    (9, 9, 500.00, 20.00, '2022-01-09 17:00:00'),
    (10, 10, 550.00, 10.00, '2022-01-10 18:00:00');


INSERT INTO Notification (SellerID, BuyerID, MessageType, SellerMessage, BuyerMessage, Timestamp)
VALUES
(1, 1, 'Sold', 'Congratulations! Your item has been sold.', 'Congratulations! You have successfully purchased an item.', NOW()),
(2, 2, 'Sold', 'Congratulations! Your item has been sold.', 'Congratulations! You have successfully purchased an item.', NOW()),
(3, 3, 'Sold', 'Congratulations! Your item has been sold.', 'Congratulations! You have successfully purchased an item.', NOW()),
(4, 4, 'Sold', 'Congratulations! Your item has been sold.', 'Congratulations! You have successfully purchased an item.', NOW()),
(5, 5, 'Sold', 'Congratulations! Your item has been sold.', 'Congratulations! You have successfully purchased an item.', NOW()),
(6, 6, 'Sold', 'Congratulations! Your item has been sold.', 'Congratulations! You have successfully purchased an item.', NOW()),
(7, 7, 'Sold', 'Congratulations! Your item has been sold.', 'Congratulations! You have successfully purchased an item.', NOW()),
(8, 8, 'Sold', 'Congratulations! Your item has been sold.', 'Congratulations! You have successfully purchased an item.', NOW()),
(9, 9, 'Sold', 'Congratulations! Your item has been sold.', 'Congratulations! You have successfully purchased an item.', NOW()),
(10, 10, 'Sold', 'Congratulations! Your item has been sold.', 'Congratulations! You have successfully purchased an item.', NOW());

