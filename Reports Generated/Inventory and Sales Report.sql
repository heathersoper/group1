USE ECommerceProject;

SELECT 	p.productID,
		p.product_name,
		DATE_FORMAT(o.order_date, '%Y-%m') AS sale_month,
		SUM(oi.order_quantity) AS total_quantity_sold,
		p.qty_instock - SUM(oi.order_quantity) AS remaining_inventory,
		SUM(oi.item_total) AS total_sales_value
FROM 	product p
JOIN 	order_items oi ON p.productID = oi.productID
JOIN 	orders o ON oi.orderID = o.orderID
GROUP BY p.productID, sale_month
ORDER BY sale_month, p.productID;
