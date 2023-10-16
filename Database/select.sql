-- select 
select * from Users;
select * from Item;
select * from Sellers;
select * from Admins;
select * from Buyer;
select * from Bid;
select * from Notification;
select * from BankAccount;
select * from Payment;
select * from Transaction;
select * from Taxes;

select * from Users;




SELECT  Users.FirstName, 
Users.LastName, Item.* FROM Item 
JOIN Sellers ON Item.ItemID = Sellers.ItemID
JOIN Users ON Sellers.UserID = Users.UserID;




SELECT Users.FirstName, Users.LastName, Sellers.SellerId, 
Users.UserId,Item.ItemId, Item.Title, Item.Description
FROM Sellers
JOIN Users ON Sellers.UserID = Users.UserID
JOIN Item ON Sellers.ItemID = Item.ItemID
WHERE Users.UserID=Sellers.UserID; 





SELECT *, 'Manager of Database' AS Roles FROM Admins WHERE AdminID = 1
UNION ALL
SELECT *, 'Manager of Item' AS Roles FROM Admins WHERE AdminID = 2
UNION ALL
SELECT *, 'Manager of Bid and Sellers' AS Roles FROM Admins WHERE AdminID = 3
UNION ALL
SELECT *, 'Manager of Notification and Users' AS Roles FROM Admins WHERE AdminID = 4
UNION ALL
SELECT *, 'Manager of Payment' AS Roles FROM Admins WHERE AdminID = 5
UNION ALL
SELECT *, 'Manager of Transaction and Buyer' AS Roles FROM Admins WHERE AdminID = 6;





SELECT Users.FirstName, Users.LastName,Buyer.*,Item.Title AS Item_Title,Item.Category
FROM Buyer
JOIN Users ON Buyer.UserID = Users.UserID
JOIN Item ON Buyer.ItemID=Item.ItemID ; 


SELECT  Users.FirstName, 
Users.LastName, BankAccount.* FROM BankAccount
JOIN Users ON BankAccount.UserID = Users.UserID;





SELECT  BuyerAccount.AccountHolderName AS BuyerAccountHolderName, Payment.BuyerAccountNo,
 SellerAccount.AccountHolderName AS SellerAccountHolderName, Payment.SellerAccountNo,
 Payment.Amount, Payment.PaymentID,Payment.PaymentStatus,Payment.PaymentMethod,
 Payment. PaymentReference,Payment.PaymentDate
FROM Payment
JOIN BankAccount AS BuyerAccount ON Payment.BuyerAccountNo = BuyerAccount.AccountNumber
JOIN BankAccount AS SellerAccount ON Payment.SellerAccountNo = SellerAccount.AccountNumber;



SELECT Taxes.TaxID, Taxes.TaxType, Taxes.TaxAmount,
(SELECT AccountHolderName FROM BankAccount
WHERE BankAccount.AccountNumber = Taxes.SellerAccountNo) AS SellerAccountHolderName,
(SELECT AccountHolderName FROM BankAccount WHERE
 BankAccount.AccountNumber = Taxes.BuyerAccountNo) AS BuyerAccountHolderName,
(SELECT SUM(TaxAmount) FROM Taxes) AS TotalTaxAmount
FROM
    Taxes;
    
     SELECT MIN(TaxAmount) AS TaxAmount, 'Minimum' AS TaxType,
 (SELECT SUM(TaxAmount) FROM Taxes) AS TotalTaxAmount FROM Taxes
UNION
SELECT MAX(TaxAmount) AS TaxAmount, 'Maximum' AS TaxType ,
(SELECT SUM(TaxAmount) FROM Taxes) AS TotalTaxAmount FROM Taxes;

    
