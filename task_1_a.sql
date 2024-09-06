-- 1.Task
-- Get numbers of bikes sold in Jan and Feb 2020 per each vehicle make.
-- a) Display only those makes where total sales are more than 1000 units

SELECT T_VEHICLE_MAKE.*, COUNT(T_VEHICLE_MAKE.id) AS sold_count
FROM `loan-schema`.`m_loan` AS T_LOAN
  JOIN `asset-schema`.`asset` AS T_ASSET ON T_LOAN.id = T_ASSET.m_loan_id
  JOIN `asset-schema`.`vehicle_model` AS T_VEHICLE_MODEL ON T_ASSET.model_id = T_VEHICLE_MODEL.id
  JOIN `asset-schema`.`vehicle_make` AS T_VEHICLE_MAKE ON T_VEHICLE_MODEL.vehicle_make_id = T_VEHICLE_MAKE.id
WHERE T_LOAN.disbursedon_date >= '2020-01-01'
  AND T_LOAN.disbursedon_date < '2020-03-01' -- T_LOAN.disbursedon_date <= '2020-02-29' -- 02-28 or 02-29 depends on the year
GROUP BY T_VEHICLE_MAKE.id
HAVING sold_count > 100
ORDER BY sold_count DESC
