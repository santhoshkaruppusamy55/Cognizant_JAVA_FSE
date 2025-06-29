
CREATE TABLE savings_accounts (
    account_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    balance NUMBER(15,2),
    account_type VARCHAR2(20) DEFAULT 'SAVINGS'
);

CREATE TABLE employees (
    employee_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    department VARCHAR2(50),
    salary NUMBER(10,2)
);

CREATE TABLE accounts (
    account_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    balance NUMBER(15,2),
    account_type VARCHAR2(20)
);

INSERT INTO savings_accounts VALUES (101, 1, 5000.00, 'SAVINGS');
INSERT INTO savings_accounts VALUES (102, 2, 8000.00, 'SAVINGS');
INSERT INTO savings_accounts VALUES (103, 3, 12000.00, 'SAVINGS');
INSERT INTO savings_accounts VALUES (104, 4, 3000.00, 'SAVINGS');
INSERT INTO savings_accounts VALUES (105, 5, 15000.00, 'SAVINGS');
INSERT INTO savings_accounts VALUES (106, 6, 7500.00, 'SAVINGS');

INSERT INTO employees VALUES (1, 'Rajesh Kumar', 'IT', 50000.00);
INSERT INTO employees VALUES (2, 'Priya Sharma', 'IT', 45000.00);
INSERT INTO employees VALUES (3, 'Amit Singh', 'HR', 48000.00);
INSERT INTO employees VALUES (4, 'Sunita Gupta', 'IT', 52000.00);
INSERT INTO employees VALUES (5, 'Ravi Patel', 'HR', 46000.00);
INSERT INTO employees VALUES (6, 'Kavya Reddy', 'Finance', 55000.00);
INSERT INTO employees VALUES (7, 'Arun Nair', 'IT', 47000.00);
INSERT INTO employees VALUES (8, 'Meera Joshi', 'Finance', 53000.00);

INSERT INTO accounts VALUES (201, 1, 15000.00, 'CHECKING');
INSERT INTO accounts VALUES (202, 1, 8000.00, 'SAVINGS');
INSERT INTO accounts VALUES (203, 2, 12000.00, 'CHECKING');
INSERT INTO accounts VALUES (204, 2, 5000.00, 'SAVINGS');
INSERT INTO accounts VALUES (205, 3, 20000.00, 'CHECKING');
INSERT INTO accounts VALUES (206, 4, 9000.00, 'SAVINGS');
INSERT INTO accounts VALUES (207, 5, 18000.00, 'CHECKING');
INSERT INTO accounts VALUES (208, 6, 11000.00, 'SAVINGS');

COMMIT;

-- SCENARIO 1: Process Monthly Interest for Savings Accounts

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    v_account_id savings_accounts.account_id%TYPE;
    v_old_balance savings_accounts.balance%TYPE;
    v_new_balance savings_accounts.balance%TYPE;
    v_interest_amount NUMBER;
    v_count NUMBER := 0;
    
    CURSOR c_savings IS
        SELECT account_id, balance
        FROM savings_accounts;
        
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== MONTHLY INTEREST PROCESSING ===');
    DBMS_OUTPUT.PUT_LINE('Applying 1% interest to all savings accounts');
    DBMS_OUTPUT.PUT_LINE('');
    
    FOR rec IN c_savings LOOP
        v_account_id := rec.account_id;
        v_old_balance := rec.balance;
        v_interest_amount := v_old_balance * 0.01;
        v_new_balance := v_old_balance + v_interest_amount;
        
        UPDATE savings_accounts 
        SET balance = v_new_balance 
        WHERE account_id = v_account_id;
        
        DBMS_OUTPUT.PUT_LINE('Account ID: ' || v_account_id || 
                           ' | Old Balance: Rs.' || TO_CHAR(v_old_balance, '9,99,999.99') ||
                           ' | Interest: Rs.' || TO_CHAR(v_interest_amount, '999.99') ||
                           ' | New Balance: Rs.' || TO_CHAR(v_new_balance, '9,99,999.99'));
        v_count := v_count + 1;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Total accounts processed: ' || v_count);
    DBMS_OUTPUT.PUT_LINE('');
    COMMIT;
    
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        ROLLBACK;
END ProcessMonthlyInterest;
/

-- SCENARIO 2: Update Employee Bonus

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department IN VARCHAR2,
    p_bonus_percentage IN NUMBER
) AS
    v_employee_id employees.employee_id%TYPE;
    v_name employees.name%TYPE;
    v_old_salary employees.salary%TYPE;
    v_bonus_amount NUMBER;
    v_new_salary NUMBER;
    v_count NUMBER := 0;
    
    CURSOR c_employees IS
        SELECT employee_id, name, salary
        FROM employees
        WHERE department = p_department;
        
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== EMPLOYEE BONUS UPDATE ===');
    DBMS_OUTPUT.PUT_LINE('Department: ' || p_department);
    DBMS_OUTPUT.PUT_LINE('Bonus Percentage: ' || p_bonus_percentage || '%');
    DBMS_OUTPUT.PUT_LINE('');
    
    FOR rec IN c_employees LOOP
        v_employee_id := rec.employee_id;
        v_name := rec.name;
        v_old_salary := rec.salary;
        v_bonus_amount := v_old_salary * (p_bonus_percentage / 100);
        v_new_salary := v_old_salary + v_bonus_amount;
        
        UPDATE employees 
        SET salary = v_new_salary 
        WHERE employee_id = v_employee_id;
        
        DBMS_OUTPUT.PUT_LINE('Employee: ' || v_name || 
                           ' | Old Salary: Rs.' || TO_CHAR(v_old_salary, '9,99,999.99') ||
                           ' | Bonus: Rs.' || TO_CHAR(v_bonus_amount, '99,999.99') ||
                           ' | New Salary: Rs.' || TO_CHAR(v_new_salary, '9,99,999.99'));
        v_count := v_count + 1;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Total employees updated: ' || v_count);
    DBMS_OUTPUT.PUT_LINE('');
    COMMIT;
    
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        ROLLBACK;
END UpdateEmployeeBonus;
/

-- SCENARIO 3: Transfer Funds Between Accounts

CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account IN NUMBER,
    p_to_account IN NUMBER,
    p_amount IN NUMBER
) AS
    v_from_balance accounts.balance%TYPE;
    v_to_balance accounts.balance%TYPE;
    v_new_from_balance NUMBER;
    v_new_to_balance NUMBER;
    
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== FUND TRANSFER ===');
    DBMS_OUTPUT.PUT_LINE('From Account: ' || p_from_account);
    DBMS_OUTPUT.PUT_LINE('To Account: ' || p_to_account);
    DBMS_OUTPUT.PUT_LINE('Transfer Amount: Rs.' || TO_CHAR(p_amount, '9,99,999.99'));
    DBMS_OUTPUT.PUT_LINE('');
    
  
    SELECT balance INTO v_from_balance 
    FROM accounts 
    WHERE account_id = p_from_account;
    

    IF v_from_balance < p_amount THEN
        DBMS_OUTPUT.PUT_LINE('TRANSFER FAILED: Insufficient balance');
        DBMS_OUTPUT.PUT_LINE('Available Balance: Rs.' || TO_CHAR(v_from_balance, '9,99,999.99'));
        DBMS_OUTPUT.PUT_LINE('Required Amount: Rs.' || TO_CHAR(p_amount, '9,99,999.99'));
        DBMS_OUTPUT.PUT_LINE('');
        RETURN;
    END IF;
    
    SELECT balance INTO v_to_balance 
    FROM accounts 
    WHERE account_id = p_to_account;
    
    v_new_from_balance := v_from_balance - p_amount;
    v_new_to_balance := v_to_balance + p_amount;
    
    UPDATE accounts 
    SET balance = v_new_from_balance 
    WHERE account_id = p_from_account;
    
    UPDATE accounts 
    SET balance = v_new_to_balance 
    WHERE account_id = p_to_account;
    
    DBMS_OUTPUT.PUT_LINE('TRANSFER SUCCESSFUL');
    DBMS_OUTPUT.PUT_LINE('From Account ' || p_from_account || ': Rs.' || 
                        TO_CHAR(v_from_balance, '9,99,999.99') || ' -> Rs.' || 
                        TO_CHAR(v_new_from_balance, '9,99,999.99'));
    DBMS_OUTPUT.PUT_LINE('To Account ' || p_to_account || ': Rs.' || 
                        TO_CHAR(v_to_balance, '9,99,999.99') || ' -> Rs.' || 
                        TO_CHAR(v_new_to_balance, '9,99,999.99'));
    DBMS_OUTPUT.PUT_LINE('');
    COMMIT;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Account not found');
        DBMS_OUTPUT.PUT_LINE('');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        DBMS_OUTPUT.PUT_LINE('');
        ROLLBACK;
END TransferFunds;
/

-- EXECUTE THE STORED PROCEDURES

-- Test Scenario 1: Process Monthly Interest
EXECUTE ProcessMonthlyInterest;

-- Test Scenario 2: Update Employee Bonus (15% bonus to IT department)
EXECUTE UpdateEmployeeBonus('IT', 15);

-- Test Scenario 2: Update Employee Bonus (10% bonus to HR department)
EXECUTE UpdateEmployeeBonus('HR', 10);

-- Test Scenario 3: Transfer Funds
EXECUTE TransferFunds(201, 203, 3000);
EXECUTE TransferFunds(205, 204, 5000);
EXECUTE TransferFunds(207, 208, 2500);
EXECUTE TransferFunds(202, 201, 25000);