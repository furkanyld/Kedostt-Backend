
INSERT INTO roles (id, name)
VALUES (1, 'ADMIN')
ON CONFLICT (name) DO NOTHING;

INSERT INTO users (id, username, password, email)
VALUES (
    1,
    'admin',
    '"$2a$12$tq0a8jpBQpuqTOWclhvAaOtzkIKqCHIzMH1FnF6841JJfEBaonK72"', -- 'admin123' hashed
    'admin@kedostt.com'
)
ON CONFLICT (username) DO NOTHING;

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1)
ON CONFLICT DO NOTHING;
