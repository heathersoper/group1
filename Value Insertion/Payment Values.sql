INSERT INTO payment_method (paymentmethodID, method_name) VALUES
(1, 'Credit Card'),
(2, 'Debit Card'),
(3, 'Cash on Delivery'),
(4, 'Bank Transfer'),
(5, 'GCash'),
(6, 'Installment Plan');

INSERT INTO discount (discountID, discount_name, description, minim_spend, discount_percent, active, paymentID) VALUES
(10001, 'Holiday Sale', '10% off on orders above 1000', 1000.00, 10.00, TRUE, 1001),
(10002, 'Welcome Discount', '5% off for new users', 500.00, 5.00, TRUE, 1002),
(10003, 'Loyalty Discount', '15% off for returning customers', 2000.00, 15.00, TRUE, 1004),
(10004, 'Flash Sale', '20% off limited-time offer', 500.00, 20.00, FALSE, NULL),
(10005, 'Clearance Sale', '30% off on clearance items', 100.00, 30.00, TRUE, 1010),
(10006, 'Birthday Bonus', 'Special 25% discount for birthdays', 300.00, 25.00, TRUE, 1015),
(10007, 'Black Friday Deal', '50% off sitewide', 5000.00, 50.00, TRUE, 1007),
(10008, 'Cyber Monday', '40% discount on electronics', 5000.00, 40.00, FALSE, NULL),
(10009, 'Student Discount', '10% off with student ID', 200.00, 10.00, TRUE, 1009),
(10010, 'End of Season', '25% off clearance items', 1000.00, 25.00, TRUE, NULL);


INSERT INTO payment (paymentID, paymentmethodID, payment_date, amount, discountID) VALUES
(1001, 1, '2024-11-01', 2250.00, 10001), -- Holiday Sale (10%)
(1002, 2, '2024-11-02', 1140.00, 10002), -- Welcome Discount (5%)
(1003, 3, '2024-11-03', 500.00, NULL), -- No discount
(1004, 4, '2024-11-04', 2550.00, 10003), -- Loyalty Discount (15%)
(1005, 5, '2024-11-05', 800.00, NULL), -- No discount
(1006, 6, '2024-11-06', 1500.00, NULL), -- Flash Sale (inactive)
(1007, 1, '2024-11-07', 5000.00, 10007), -- Black Friday Deal (50%)
(1008, 5, '2024-11-08', 2000.00, NULL), -- Cyber Monday (inactive)
(1009, 4, '2024-11-09', 225.00, 10009), -- Student Discount (10%)
(1010, 3, '2024-11-10', 3500.00, 10005), -- Clearance Sale (30%)
(1011, 6, '2024-11-11', 8000.00, NULL), -- No discount
(1012, 1, '2024-11-12', 4500.00, NULL), -- No discount
(1013, 2, '2024-11-13', 1140.00, 10002), -- Welcome Discount (5%)
(1014, 4, '2024-11-14', 1500.00, NULL), -- No discount
(1015, 5, '2024-11-15', 2250.00, 10006), -- Birthday Bonus (25%)
(1016, 3, '2024-11-16', 250.00, NULL), -- No discount
(1017, 2, '2024-11-17', 3500.00, 10007), -- Black Friday Deal (50%)
(1018, 4, '2024-11-18', 800.00, NULL), -- No discount
(1019, 5, '2024-11-19', 6000.00, NULL), -- Cyber Monday (inactive)
(1020, 1, '2024-11-20', 1000.00, NULL); -- No discount
