-- 3. Task
-- Calculate current balance (scheduled amount - payed amount) for each loan.
-- Use tables m_loan_repayment_schedule for payment schedule data.
-- Total scheduled payment amount on current date must be calculated by sum of all amount field values.
-- Some values can be null. Payment data are stored in table m_loan_transaction.

SELECT T_union.loan_id, SUM(T_union.current_balance) AS current_balance
FROM (
  (
    SELECT
      T_LP_SCHEDULE.loan_id,
      IFNULL(T_LP_SCHEDULE.principal_amount, 0) + IFNULL(T_LP_SCHEDULE.interest_amount, 0) AS current_balance
    FROM `loan-schema`.`m_loan_repayment_schedule` AS T_LP_SCHEDULE
  ) UNION (
    SELECT T_transaction.loan_id, (-1 * SUM(T_transaction.amount)) AS current_balance
    FROM `loan-schema`.`m_loan_transaction` AS T_transaction
    GROUP BY T_transaction.loan_id
  )
) as T_union
GROUP BY T_union.loan_id
ORDER BY T_union.loan_id
