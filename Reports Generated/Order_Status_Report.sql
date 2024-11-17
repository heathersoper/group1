USE ECommerceProject;

SELECT
    YEAR(order_date) AS year,
    MONTH(order_date) AS month,
    order_status,
    COUNT(*) AS order_count
FROM
    orders
GROUP BY
    YEAR(order_date),
    MONTH(order_date),
    order_status
ORDER BY
    year DESC,
    month DESC,
    order_status;
