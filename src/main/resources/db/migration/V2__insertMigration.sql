INSERT INTO users(id, login, password, role)
VALUES (1, 'Maestro', '1', 'ADMIN');

INSERT INTO chats(id, name)
    VALUES (1, 'Admin Chat');

INSERT INTO users_chats(chat_id, user_id)
    VALUES (1, 1);
