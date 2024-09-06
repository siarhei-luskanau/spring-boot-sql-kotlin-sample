-- 2. Task
-- Get current weekly payment amount for each loan.
-- Table m_loan_repayment_schedule contains weekly payment records.
-- Weekly payment record should be selected for the first week
-- where obligations are not met (value for field completed_derived=0).
-- Use principal_amount plus interest_amount to acquire weekly payment amount.

SELECT
  T_LP_SCHEDULE.loan_id,
  T_LP_SCHEDULE.duedate,
  IFNULL(T_LP_SCHEDULE.principal_amount, 0) + IFNULL(T_LP_SCHEDULE.interest_amount, 0) AS payment_amount
FROM `loan-schema`.`m_loan_repayment_schedule` AS T_LP_SCHEDULE
WHERE T_LP_SCHEDULE.completed_derived = 0 AND T_LP_SCHEDULE.duedate IN (
  SELECT MIN(T_SUB.duedate)
  FROM `loan-schema`.`m_loan_repayment_schedule` AS T_SUB
  WHERE T_SUB.completed_derived = 0
  GROUP BY T_SUB.loan_id
)
ORDER BY T_LP_SCHEDULE.loan_id, T_LP_SCHEDULE.duedate
