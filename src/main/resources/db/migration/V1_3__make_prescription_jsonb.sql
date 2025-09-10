ALTER TABLE IF EXISTS prescriptions DROP COLUMN IF EXISTS medicine_id,
DROP COLUMN IF EXISTS dosage,
DROP COLUMN IF EXISTS duration_in_days,
DROP COLUMN IF EXISTS special_instruction;

ALTER TABLE IF EXISTS prescriptions ADD COLUMN IF NOT EXISTS prescription_details JSONB;