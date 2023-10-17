CREATE USER 'Abdulaziz'@'localhost' IDENTIFIED BY 'admin1234';
GRANT ALL PRIVILEGES ON auction.* TO 'Abdulaziz'@'localhost';

CREATE USER 'John'@'localhost' IDENTIFIED BY 'password1';
GRANT ALL PRIVILEGES ON auction.Item TO 'John'@'localhost';

CREATE USER 'Jane'@'localhost' IDENTIFIED BY 'password2';
GRANT ALL PRIVILEGES ON auction.Bid TO 'Jane'@'localhost';
GRANT ALL PRIVILEGES ON auction.Sellers TO 'Jane'@'localhost';

CREATE USER 'Michael'@'localhost' IDENTIFIED BY 'password3';
GRANT ALL PRIVILEGES ON auction.Notification TO 'Michael'@'localhost';
GRANT ALL PRIVILEGES ON auction.Users TO 'Michael'@'localhost';

CREATE USER 'Emily'@'localhost' IDENTIFIED BY 'password4';
GRANT ALL PRIVILEGES ON auction.Payment TO 'Emily'@'localhost';

CREATE USER 'David'@'localhost' IDENTIFIED BY 'password5';
GRANT ALL PRIVILEGES ON auction.Transaction TO 'David'@'localhost';
GRANT ALL PRIVILEGES ON auction.Buyer TO 'David'@'localhost';

CREATE USER 'Buyer'@'localhost' IDENTIFIED BY 'buyer1234';
GRANT SELECT ON auction.* TO 'Buyer'@'localhost';
GRANT UPDATE, INSERT ON auction.Bid TO 'Buyer'@'localhost';
GRANT UPDATE ON auction.BankAccount TO 'Buyer'@'localhost';
GRANT UPDATE ON auction.Payment TO 'Buyer'@'localhost';
GRANT INSERT ON auction.Users TO 'Buyer'@'localhost';

CREATE USER 'Seller'@'localhost' IDENTIFIED BY 'seller1234';
GRANT SELECT ON auction.* TO 'Seller'@'localhost';
GRANT UPDATE, INSERT ON auction.Item TO 'Seller'@'localhost';
GRANT UPDATE ON auction.BankAccount TO 'Seller'@'localhost';
GRANT UPDATE ON auction.Payment TO 'Seller'@'localhost';
GRANT INSERT ON auction.Users TO 'Seller'@'localhost';
