package siarhei.luskanau.sql.example.demo

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param
import siarhei.luskanau.sql.example.demo.model.TestModel1
import siarhei.luskanau.sql.example.demo.model.TestModel2
import siarhei.luskanau.sql.example.demo.model.TestModel3

interface TaskRepository : Repository<TestModel1, Int> {
    @Query(
        """
SELECT T_VEHICLE_MAKE.*, COUNT(T_LOAN.id) AS sold_count
FROM `loan-schema`.`m_loan` AS T_LOAN
  JOIN `asset-schema`.`asset` AS T_ASSET ON T_LOAN.id = T_ASSET.m_loan_id
  JOIN `asset-schema`.`vehicle_model` AS T_VEHICLE_MODEL ON T_ASSET.model_id = T_VEHICLE_MODEL.id
  JOIN `asset-schema`.`vehicle_make` AS T_VEHICLE_MAKE ON T_VEHICLE_MODEL.vehicle_make_id = T_VEHICLE_MAKE.id
WHERE T_LOAN.disbursedon_date >= :dateFrom
  AND T_LOAN.disbursedon_date < :dateTo
GROUP BY T_VEHICLE_MAKE.id
HAVING sold_count > :soldCount
ORDER BY sold_count DESC
        """,
    )
    fun task1a(
        @Param("dateFrom") dateFrom: String,
        @Param("dateTo") dateTo: String,
        @Param("soldCount") soldCount: Int,
    ): List<TestModel1>

    @Query(
        """
SELECT T_VEHICLE_MAKE.*, COUNT(T_LOAN.id) AS sold_count
FROM `loan-schema`.`m_loan` AS T_LOAN
  JOIN `asset-schema`.`asset` AS T_ASSET ON T_LOAN.id = T_ASSET.m_loan_id
    AND T_LOAN.disbursedon_date >= :dateFrom
    AND T_LOAN.disbursedon_date < :dateTo
  JOIN `asset-schema`.`vehicle_model` AS T_VEHICLE_MODEL ON T_ASSET.model_id = T_VEHICLE_MODEL.id
  RIGHT JOIN `asset-schema`.`vehicle_make` AS T_VEHICLE_MAKE ON T_VEHICLE_MODEL.vehicle_make_id = T_VEHICLE_MAKE.id
GROUP BY T_VEHICLE_MAKE.id
ORDER BY sold_count DESC
        """,
    )
    fun task1b(
        @Param("dateFrom") dateFrom: String,
        @Param("dateTo") dateTo: String,
    ): List<TestModel1>

    @Query(
        """
SELECT
  T_LP_SCHEDULE.loan_id,
  T_LP_SCHEDULE.duedate,
  IFNULL(T_LP_SCHEDULE.principal_amount, 0) + IFNULL(T_LP_SCHEDULE.interest_amount, 0) AS payment_amount
FROM `loan-schema`.`m_loan_repayment_schedule` AS T_LP_SCHEDULE
WHERE T_LP_SCHEDULE.completed_derived = FALSE AND T_LP_SCHEDULE.duedate IN (
  SELECT MIN(T_SUB.duedate)
  FROM `loan-schema`.`m_loan_repayment_schedule` AS T_SUB
  WHERE T_SUB.completed_derived = FALSE
  GROUP BY T_SUB.loan_id
)
ORDER BY T_LP_SCHEDULE.loan_id, T_LP_SCHEDULE.duedate
        """,
    )
    fun task2(): List<TestModel2>

    @Query(
        """
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
        """,
    )
    fun task3(): List<TestModel3>
}
