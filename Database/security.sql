    
CREATE USER 'Abdulaziz'@'localhost' IDENTIFIED BY 'admin1234';
GRANT ALL PRIVILEGES ON db.* TO 'Abdulaziz'@'localhost';



CREATE USER 'John'@'localhost' IDENTIFIED BY 'password1';
GRANT ALL PRIVILEGES ON test.Item TO 'John'@'localhost';



CREATE USER 'Jane'@'localhost' IDENTIFIED BY 'password2';
GRANT ALL PRIVILEGES ON test.Bid TO 'Jane'@'localhost';
GRANT ALL PRIVILEGES ON test.Sellers TO 'Jane'@'localhost';



CREATE USER 'Michael'@'localhost' IDENTIFIED BY 'password3';
GRANT ALL PRIVILEGES ON test.Notification TO 'Michael'@'localhost';
GRANT ALL PRIVILEGES ON test.Users TO 'Michael'@'localhost';



CREATE USER 'Emily'@'localhost' IDENTIFIED BY 'password4';
GRANT ALL PRIVILEGES ON test.Payment TO 'Emily'@'localhost';



CREATE USER 'David'@'localhost' IDENTIFIED BY 'password5';
GRANT ALL PRIVILEGES ON test.Transaction TO 'David'@'localhost';
GRANT ALL PRIVILEGES ON test.Buyer TO 'David'@'localhost';

 
    
    
    
 
CREATE USER 'Buyer'@'localhost' IDENTIFIED BY 'buyer1234';
GRANT SELECT ON test.* TO 'Buyer'@'localhost';
GRANT UPDATE, INSERT ON test.Bid TO 'Buyer'@'localhost';
GRANT UPDATE ON test.BankAccount TO 'Buyer'@'localhost';
GRANT UPDATE ON test.Payment TO 'Buyer'@'localhost';
GRANT INSERT ON test.Users TO 'Buyer'@'localhost';


CREATE USER 'Sellers'@'localhost' IDENTIFIED BY 'seller1234';
GRANT SELECT ON test.* TO 'Seller'@'localhost';
GRANT UPDATE, INSERT ON test.Item TO 'Seller'@'localhost';
GRANT UPDATE ON test.BankAccount TO 'Seller'@'localhost';
GRANT UPDATE ON test.Payment TO 'Seller'@'localhost';
GRANT INSERT ON test.Users TO 'Seller'@'localhost';
  
   
