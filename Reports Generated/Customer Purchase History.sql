USE ECommerceProject;

SELECT c.customer ID,
       c.last_name,
       c.first_name,
       c.email,
       c.phone_number,
       YEAR(o.order_date) AS year,
       MONTH(o.order_date) AS month,
       COUNT(o.orderID) AS total_purchases,
       SUM(o.order_total) AS amount_spent
FROM   customer c 
LEFT JOIN orders o ON c.customerID = o.customerID
WHERE o.order_status = 'Completed'
GROUP BY c.customerID, c.first_name, c.last_name
ORDER BY amount_spent DESC;
