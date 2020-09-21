CREATE TABLE chats (
                     id  serial not null,
                     name varchar(50),
                     primary key (id)
);

CREATE TABLE messages (
                        id  serial not null,
                        message varchar(50),
                        username varchar(50),
                        chat_id int4,
                        primary key (id)
);

CREATE TABLE users (
                     id  serial not null,
                     login varchar(50),
                     password varchar(50),
                     role varchar(255),
                     primary key (id)
);

CREATE TABLE users_chats (
                           chat_id int4 not null,
                           user_id int4 not null,
                           primary key (user_id, chat_id)
);

ALTER TABLE messages
  add constraint FK64w44ngcpqp99ptcb9werdfmb
    foreign key (chat_id)
      references chats;

ALTER TABLE users_chats
  add constraint FKm9idubc8h2nd586vuvands3ti
    foreign key (user_id)
      references users;

ALTER TABLE users_chats
  add constraint FKpnxkruh2u71cnyc8y91s6ydpf
    foreign key (chat_id)
      references chats;