ALTER TABLE adoptions ADD COLUMN user_id BIGINT;
UPDATE adoptions SET user_id = 2 WHERE id = 1;
ALTER TABLE adoptions
ADD CONSTRAINT fk_adoption_user
FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE adoptions ALTER COLUMN user_id SET NOT NULL;