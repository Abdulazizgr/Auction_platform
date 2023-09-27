-- Insert some users
INSERT INTO User (FirstName, LastName, Email, Password)
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

INSERT INTO Admin (FirstName, LastName, Email, Password)
VALUES ('Abdulaziz', 'Isa', 'abdu1234@gmail.com', 'admin1234');



-- Insert seller data for UserIDs 1 to 10 with concise descriptions
INSERT INTO Seller (UserID, SellerProfile)
VALUES
    (1, 'Experienced seller specializing in electronics.'),
    (2, 'Home appliance expert offering kitchenware.'),
    (3, 'Fashionista with a variety of clothing items.'),
    (4, 'Collector of rare antiques and collectibles.'),
    (5, 'Automotive parts and accessories expert.'),
    (6, 'Artisan crafting unique jewelry and accessories.'),
    (7, 'Sports equipment guru for athletes.'),
    (8, 'Book lover with a collection of novels and literature.'),
    (9, 'Music enthusiast selling musical instruments.'),
    (10, 'Talented artist showcasing artwork and paintings.');


-- Insert buyer data for UserIDs 11 to 25
INSERT INTO Buyer (UserID, BuyerProfile)
VALUES
    (11, 'I am an avid shopper looking for great deals on electronics.'),
    (12, 'I am a home improvement enthusiast searching for quality kitchenware.'),
    (13, 'I love fashion and am always on the lookout for stylish clothing.'),
    (14, 'I enjoy collecting unique items and antiques.'),
    (15, 'I am passionate about cars and need automotive parts.'),
    (16, 'I have a passion for jewelry and accessories.'),
    (17, 'I am a sports enthusiast in need of sports equipment.'),
    (18, 'I am an avid reader looking for new books and novels.'),
    (19, 'I am a musician searching for musical instruments.'),
    (20, 'I appreciate art and am interested in artwork and paintings.'),
    (21, 'I enjoy tech gadgets and electronics.'),
    (22, 'I am a culinary enthusiast looking for kitchenware.'),
    (23, 'I love fashion and am seeking trendy clothing.'),
    (24, 'I collect rare items and antiques.'),
    (25, 'I am a car enthusiast in need of automotive parts.');




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

-- Insert bid data for the items
INSERT INTO Bid (ItemID, BuyerID, BidAmount, BeginningBid, MinIncrement, BidTime)
VALUES
    (1, 11, 11000.00, 10000.00, 100.00, '2023-09-27 10:15:00'),
    (1, 12, 11500.00, 11000.00, 100.00, '2023-09-27 10:30:00'),
    (1, 13, 12000.00, 11500.00, 100.00, '2023-09-27 10:45:00'),
    (2, 14, 550.00, 500.00, 50.00, '2023-09-28 09:15:00'),
    (2, 15, 600.00, 550.00, 50.00, '2023-09-28 09:30:00'),
    (3, 1, 320.00, 300.00, 20.00, '2023-10-01 11:30:00'),
    (3, 2, 350.00, 320.00, 20.00, '2023-10-01 11:45:00'),
    (4, 3, 5200.00, 5000.00, 200.00, '2023-09-30 14:15:00'),
    (4, 4, 5500.00, 5200.00, 200.00, '2023-09-30 14:30:00'),
    (4, 5, 6000.00, 5500.00, 200.00, '2023-09-30 14:45:00');



INSERT INTO Notification (UserID, Message, Timestamp)
VALUES
    (1, 'You have a new message.', NOW()),
    (2, 'Your order has been shipped.', NOW()),
    (3, 'Reminder: Meeting at 3 PM today.', NOW()),
    (12, 'New item listed: Vintage Watch.', NOW()),
    (11, 'Your bid has been accepted.', NOW());




-- Insert values into the BankAccount table for User IDs 1 to 20 with different banks and account holders
INSERT INTO BankAccount (UserID, BankName, AccountHolderName, AccountNumber, Balance)
VALUES
    (1, 'Bank A', 'John1', 1001, 1000.00),
    (2, 'Bank B', 'John2', 1002, 2000.00),
    (3, 'Bank C', 'John3', 1003, 1500.00),
    (4, 'Bank D', 'John4', 1004, 500.00),
    (5, 'Bank E', 'John5', 1005, 3000.00),
    (6, 'Bank F', 'John6', 1006, 2500.00),
    (7, 'Bank G', 'John7', 1007, 1800.00),
    (8, 'Bank H', 'John8', 1008, 3500.00),
    (9, 'Bank I', 'John9', 1009, 1200.00),
    (10, 'Bank J', 'John10', 1010, 900.00),
    (11, 'Bank K', 'John11', 1011, 3000.00),
    (12, 'Bank L', 'John12', 1012, 2200.00),
    (13, 'Bank M', 'John13', 1013, 500.00),
    (14, 'Bank N', 'John14', 1014, 600.00),
    (15, 'Bank O', 'John15', 1015, 700.00),
    (16, 'Bank P', 'John16', 1016, 800.00),
    (17, 'Bank Q', 'John17', 1017, 2500.00),
    (18, 'Bank R', 'John18', 1018, 300.00),
    (19, 'Bank S', 'John19', 1019, 1200.00),
    (20, 'Bank T', 'John20', 1020, 750.00);



