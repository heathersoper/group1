USE ECommerceProject;

SELECT c.customerID,
	   c.last_name, 
       c.first_name, 
       c.email,
       c.phone_number,
       YEAR(o.order_date) AS order_year,
       MONTH(o.order_date) AS order_month,
       COUNT(o.orderID) AS total_purchases,
       SUM(o.order_total) AS amount_spent 
FROM customer c 
LEFT JOIN orders o ON c.customerID = o.customerID
WHERE o.order_status = 'Completed'
GROUP BY c.customerID, YEAR(o.order_date), MONTH(o.order_date)
ORDER BY c.customerID, order_year, order_month;
