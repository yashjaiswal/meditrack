CREATE TABLE IF NOT EXISTS accounts  (
    id bigserial PRIMARY KEY,
    user_name text NOT NULL,
    password text NOT NULL,
    full_name text NOT NULL,
    email text NOT NULL,
    role text,
    is_active boolean,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version int default 0
);