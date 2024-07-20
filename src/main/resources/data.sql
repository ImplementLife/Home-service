INSERT INTO ROLES
(id, name)
VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_MANAGER'),
(3, 'ROLE_ADMIN')
;

INSERT INTO USERS
(ID, ACCOUNT_NON_LOCKED, ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, PHONE, USERNAME)
VALUES
(1, 'true', 'true', 'Test User FN', 'Test User LN', '$2a$10$MXKxZZBSBGASoWUDtlxj3.I9gdYzp5lQmar7AU6vyqbolHLWk.ykS', '+380000000001', 't@il.com'),
(2, 'true', 'true', 'MGR User FN', 'MGR User LN', '$2a$10$MXKxZZBSBGASoWUDtlxj3.I9gdYzp5lQmar7AU6vyqbolHLWk.ykS', '+380000000002', 'm@il.com'),
(3, 'true', 'true', 'Admin User FN', 'Admin User LN', '$2a$10$MXKxZZBSBGASoWUDtlxj3.I9gdYzp5lQmar7AU6vyqbolHLWk.ykS', '+380000000003', 'a@il.com')
;

INSERT INTO USERS_ROLES
(USER_ID, ROLES_ID)
VALUES
(1, 1),
(2, 2),
(3, 3)
;