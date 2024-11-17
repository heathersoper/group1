USE ECommerceProject;

SELECT 	o.order_date,
		SUM(o.order_total) AS total_revenue,
		pm.method_name,
		COUNT(p.paymentID) AS payment_method_usage_count,
		SUM(p.amount) AS total_amount_by_payment_method
FROM 	orders o
JOIN 	payment p ON o.paymentID = p.paymentID
JOIN 	payment_method pm ON p.paymentmethodID = pm.paymentmethodID
WHERE 	DATE_FORMAT(o.order_date, '%Y-%m') = '2024-01' OR '2024-02'
GROUP BY o.order_date, pm.method_name
ORDER BY o.order_date, pm.method_name;