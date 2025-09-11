CREATE TABLE IF NOT EXISTS patients  (
    id bigserial PRIMARY KEY,
    name text NOT NULL,
    date_of_birth TIMESTAMP NOT NULL,
    phone_number text,
    gender text,
    comments text,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version int default 0
);

CREATE TABLE IF NOT EXISTS appointments  (
    id bigserial PRIMARY KEY,
    patient_id bigint NOT NULL,
    date_of_appointment TIMESTAMP NOT NULL,
    comments text,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version int default 0
);

CREATE TABLE IF NOT EXISTS prescriptions (
    id bigserial PRIMARY KEY,
    patient_id bigint NOT NULL,
    appointment_id bigint NOT NULL,
    medicine_id bigint NOT NULL,
    dosage text NOT NULL,
    duration_in_days int NOT NULL,
    special_instruction text,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version int default 0
);

CREATE TABLE IF NOT EXISTS medicines (
    id bigserial PRIMARY KEY,
    name text,
    strength_in_mg int,
    comments text,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version int default 0
);
