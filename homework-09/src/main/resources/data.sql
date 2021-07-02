insert into authors (id, `name`)
values (1, 'Джеймс Клавелл');
insert into authors (id, `name`)
values (2, 'Эрих Мария Ремарк');

insert into genres (id, `name`)
values (1, 'Приключения');
insert into genres (id, `name`)
values (2, 'Роман');

insert into books (`name`, author_id, genre_id)
values ('Сегун', (select id from authors a where a.name = 'Джеймс Клавелл'),
        (select id from genres g where g.name = 'Приключения'));

insert into books (`name`, author_id, genre_id)
values ('Тай-пен', (select id from authors a where a.name = 'Джеймс Клавелл'),
        (select id from genres g where g.name = 'Приключения'));

insert into books (`name`, author_id, genre_id)
values ('Три товарища', (select id from authors a where a.name = 'Эрих Мария Ремарк'),
        (select id from genres g where g.name = 'Роман'));

insert into books (`name`, author_id, genre_id)
values ('Триумфайльная арка', (select id from authors a where a.name = 'Эрих Мария Ремарк'),
        (select id from genres g where g.name = 'Роман'));

insert into notes (note_text, note_date, note_author, book_id)
values ('отличная книга','2021-07-01','Павлов Д.В.',1);

insert into notes (note_text, note_date, note_author, book_id)
values ('одна из лучших книг','2021-07-01','Павлов Д.В.',2);