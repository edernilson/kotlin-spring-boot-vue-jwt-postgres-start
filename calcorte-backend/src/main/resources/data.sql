INSERT INTO users (email, full_name, password) SELECT 'admin@test.com', 'Admin', '$2a$10$Cy76t0lrGk9xOy0trE2vS.kLpK2m10PBVhJIoHAaKyagFr/o0Jn8K'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'admin@test.com');
INSERT INTO users (email, full_name, password) SELECT 'eder.nilson@gmail.com', 'Admin', '$2a$10$Cy76t0lrGk9xOy0trE2vS.kLpK2m10PBVhJIoHAaKyagFr/o0Jn8K'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'eder.nilson@gmail.com');