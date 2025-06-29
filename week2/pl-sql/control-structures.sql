
CREATE TABLE customers (
    customer_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    age NUMBER,
    balance NUMBER(15,2),
    is_vip VARCHAR2(5) DEFAULT 'FALSE'
);

-- Create Loans table
CREATE TABLE loans (
    loan_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    loan_amount NUMBER(15,2),
    interest_rate NUMBER(5,2),
    due_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Insert sample data
INSERT INTO customers VALUES (1, 'Akash', 65, 15000.00, 'FALSE');
INSERT INTO customers VALUES (2, 'Kumar', 45, 8000.00, 'FALSE');
INSERT INTO customers VALUES (3, 'Ram', 72, 25000.00, 'FALSE');


INSERT INTO loans VALUES (1, 1, 50000.00, 7.5, DATE '2025-07-15');
INSERT INTO loans VALUES (2, 2, 30000.00, 8.0, DATE '2025-07-20');
INSERT INTO loans VALUES (3, 3, 75000.00, 6.5, DATE '2025-08-10');

COMMIT;

-- SCENARIO 1: Senior Citizen Loan Discount

DECLARE
    v_customer_id customers.customer_id%TYPE;
    v_name customers.name%TYPE;
    v_age customers.age%TYPE;
    v_old_rate loans.interest_rate%TYPE;
    v_new_rate loans.interest_rate%TYPE;
    v_count NUMBER := 0;
    
    CURSOR c_senior_customers IS
        SELECT DISTINCT c.customer_id, c.name, c.age
        FROM customers c
        INNER JOIN loans l ON c.customer_id = l.customer_id
        WHERE c.age > 60;
        
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== SENIOR CITIZEN LOAN DISCOUNT PROGRAM ===');
    DBMS_OUTPUT.PUT_LINE('Applying 1% discount to customers above 60 years old');
    DBMS_OUTPUT.PUT_LINE('');
    
    FOR rec IN c_senior_customers LOOP
        v_customer_id := rec.customer_id;
        v_name := rec.name;
        v_age := rec.age;
        
        DBMS_OUTPUT.PUT_LINE('Processing: ' || v_name || ' (Age: ' || v_age || ')');
        
        FOR loan_rec IN (SELECT loan_id, interest_rate 
                        FROM loans 
                        WHERE customer_id = v_customer_id) LOOP
            
            v_old_rate := loan_rec.interest_rate;
            v_new_rate := v_old_rate - 1.0; 
            
            IF v_new_rate < 1.0 THEN
                v_new_rate := 1.0;
            END IF;
            
            UPDATE loans 
            SET interest_rate = v_new_rate 
            WHERE loan_id = loan_rec.loan_id;
            
            DBMS_OUTPUT.PUT_LINE('  Loan ID: ' || loan_rec.loan_id || 
                               ' | Old Rate: ' || v_old_rate || '% | New Rate: ' || v_new_rate || '%');
            v_count := v_count + 1;
        END LOOP;
        
        DBMS_OUTPUT.PUT_LINE('');
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('Total loans updated: ' || v_count);
    DBMS_OUTPUT.PUT_LINE('');

    COMMIT;
    
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM);
        ROLLBACK;
END;
/

-- SCENARIO 2: VIP Status Promotion


DECLARE
    v_customer_id customers.customer_id%TYPE;
    v_name customers.name%TYPE;
    v_balance customers.balance%TYPE;
    v_count NUMBER := 0;
    
    CURSOR c_all_customers IS
        SELECT customer_id, name, balance
        FROM customers
        ORDER BY balance DESC;
        
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== VIP STATUS PROMOTION PROGRAM ===');
    DBMS_OUTPUT.PUT_LINE('Promoting customers with balance > $10,000 to VIP status');
    DBMS_OUTPUT.PUT_LINE('');
    
    
    FOR rec IN c_all_customers LOOP
        v_customer_id := rec.customer_id;
        v_name := rec.name;
        v_balance := rec.balance;
        
        IF v_balance > 10000 THEN
            UPDATE customers 
            SET is_vip = 'TRUE' 
            WHERE customer_id = v_customer_id;
            
            DBMS_OUTPUT.PUT_LINE('PROMOTED: ' || v_name || ' (Balance: $' || 
                               TO_CHAR(v_balance, '999,999.99') || ') -> VIP STATUS');
            v_count := v_count + 1;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Regular: ' || v_name || ' (Balance: $' || 
                               TO_CHAR(v_balance, '999,999.99') || ') -> Regular Customer');
        END IF;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Total customers promoted to VIP: ' || v_count);
    COMMIT;
    
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM);
        ROLLBACK;
END;
/

-- SCENARIO 3: Loan Due Reminders

DECLARE
    v_loan_id loans.loan_id%TYPE;
    v_customer_id loans.customer_id%TYPE;
    v_customer_name customers.name%TYPE;
    v_loan_amount loans.loan_amount%TYPE;
    v_due_date loans.due_date%TYPE;
    v_days_until_due NUMBER;
    v_count NUMBER := 0;
    
    CURSOR c_due_loans IS
        SELECT l.loan_id, l.customer_id, c.name, l.loan_amount, l.due_date,
               (l.due_date - SYSDATE) AS days_until_due
        FROM loans l
        INNER JOIN customers c ON l.customer_id = c.customer_id
        WHERE l.due_date BETWEEN SYSDATE AND SYSDATE + 30
        ORDER BY l.due_date;
        
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== LOAN DUE REMINDER SYSTEM ===');
    DBMS_OUTPUT.PUT_LINE('Checking for loans due within the next 30 days...');
    DBMS_OUTPUT.PUT_LINE('Current Date: ' || TO_CHAR(SYSDATE, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('');
    
    FOR rec IN c_due_loans LOOP
        v_loan_id := rec.loan_id;
        v_customer_id := rec.customer_id;
        v_customer_name := rec.name;
        v_loan_amount := rec.loan_amount;
        v_due_date := rec.due_date;
        v_days_until_due := ROUND(rec.days_until_due);
        
        DBMS_OUTPUT.PUT_LINE(' REMINDER NOTICE #' || (v_count + 1));
        DBMS_OUTPUT.PUT_LINE('   Customer: ' || v_customer_name || ' (ID: ' || v_customer_id || ')');
        DBMS_OUTPUT.PUT_LINE('   Loan ID: ' || v_loan_id);
        DBMS_OUTPUT.PUT_LINE('   Amount: $' || TO_CHAR(v_loan_amount, '999,999.99'));
        DBMS_OUTPUT.PUT_LINE('   Due Date: ' || TO_CHAR(v_due_date, 'DD-MON-YYYY'));
        
        IF v_days_until_due <= 7 THEN
            DBMS_OUTPUT.PUT_LINE('    URGENT: Payment due in ' || v_days_until_due || ' days!');
        ELSIF v_days_until_due <= 15 THEN
            DBMS_OUTPUT.PUT_LINE('   IMPORTANT: Payment due in ' || v_days_until_due || ' days');
        ELSE
            DBMS_OUTPUT.PUT_LINE('     NOTICE: Payment due in ' || v_days_until_due || ' days');        END IF;
        
        DBMS_OUTPUT.PUT_LINE('   Message: Dear ' || v_customer_name || ', your loan payment is due soon. Please ensure timely payment.');
        DBMS_OUTPUT.PUT_LINE('');
        
        v_count := v_count + 1;
    END LOOP;
    
    IF v_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('loans due within the next 30 days.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('SUMMARY: ' || v_count || ' reminder(s) generated for loans due within 30 days.');
    END IF;
    
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM);
END;
/

