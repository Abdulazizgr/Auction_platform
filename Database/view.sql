
CREATE VIEW TransactionSummary AS
SELECT
    Transaction.TransactionID,
    Buyer.BuyerID,
    CONCAT(BuyerUser.FirstName, ' ', BuyerUser.LastName) AS BuyerName,
    Sellers.SellerID,
    CONCAT(SellerUser.FirstName, ' ', SellerUser.LastName) AS SellerName,
    Payment.PaymentID,
    Payment.Amount AS PaymentAmount,
    Payment.PaymentDate,
    Transaction.TransactionStatus
FROM
    Transaction
    JOIN Buyer ON Buyer.BuyerID = Transaction.BuyerID
    JOIN Sellers ON Sellers.SellerID = Transaction.SellerID
    JOIN Users AS BuyerUser ON BuyerUser.UserID = Buyer.UserID
    JOIN Users AS SellerUser ON SellerUser.UserID = Sellers.UserID
    JOIN Payment ON Payment.PaymentID = Transaction.PaymentID;



CREATE VIEW BuyerNotification AS
SELECT CONCAT(BuyerUser.FirstName, ' ', buyerUser.LastName) AS BuyerName,
    Notification.BuyerID,
    Notification.MessageType,
    Notification.SellerMessage,
    Notification.Timestamp,
    CONCAT(SellerUser.FirstName, ' ', SellerUser.LastName) AS SellerName
FROM Notification
JOIN Users AS BuyerUser ON BuyerUser.UserID = Notification.BuyerID
JOIN Users AS SellerUser ON SellerUser.UserID = Notification.SellerID;


CREATE VIEW SellerNotification AS
SELECT CONCAT(SellerUser.FirstName, ' ', SellerUser.LastName) AS SellerName,
    Notification.SellerID,
    Notification.MessageType,
    Notification.SellerMessage,
    Notification.Timestamp,
    CONCAT(BuyerUser.FirstName, ' ', BuyerUser.LastName) AS BuyerName
FROM Notification
JOIN Users AS SellerUser ON SellerUser.UserID = Notification.SellerID
JOIN Users AS BuyerUser ON BuyerUser.UserID = Notification.BuyerID;


	
