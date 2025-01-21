-- Create table
CREATE TABLE users
(
    idx             BIGSERIAL PRIMARY KEY,                                            -- Auto-increment primary key
    email           VARCHAR(255) NOT NULL UNIQUE,                                     -- Unique email
    password        VARCHAR(255) NOT NULL,                                            -- Password
    name            VARCHAR(100) NOT NULL,                                            -- User name
    status          VARCHAR(50) DEFAULT 'ACTIVE',                                     -- Status (default to 'ACTIVE')
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                              -- Creation timestamp
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                              -- Last update timestamp
    last_login_ip   VARCHAR(16),                                                      -- Last login device IP
    last_login_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                              -- Last login timestamp
    failed_attempts INT DEFAULT 0                                                     -- Login failed attempts (default to 0)
);

-- Add trigger to update `updated_at` on row update
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_updated_at
    BEFORE UPDATE ON users
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();