-- 1.Task
-- Get numbers of bikes sold in Jan and Feb 2020 per each vehicle make.
-- b) Display full sales data including all makes (including those where sales are not made)

SELECT T_VEHICLE_MAKE.*, COUNT(T_LOAN.id) AS sold_count
FROM `loan-schema`.`m_loan` AS T_LOAN
  JOIN `asset-schema`.`asset` AS T_ASSET ON T_LOAN.id = T_ASSET.m_loan_id
    AND T_LOAN.disbursedon_date >= '2020-01-01'
    AND T_LOAN.disbursedon_date < '2021-03-01' -- T_LOAN.disbursedon_date <= '2020-02-29' -- 02-28 or 02-29 depends on the year
  JOIN `asset-schema`.`vehicle_model` AS T_VEHICLE_MODEL ON T_ASSET.model_id = T_VEHICLE_MODEL.id
  RIGHT JOIN `asset-schema`.`vehicle_make` AS T_VEHICLE_MAKE ON T_VEHICLE_MODEL.vehicle_make_id = T_VEHICLE_MAKE.id
GROUP BY T_VEHICLE_MAKE.id
ORDER BY sold_count DESC
