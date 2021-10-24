insert into public.users (id, username, password, authority) values (1, 'user', 'user', 'ROLE_USER');
insert  into public.users (id, username, password, authority) values (2, 'admin', 'admin', 'ROLE_ADMIN, ROLE_USER');
insert into public.users (id, username, password, authority) values (3, 'superuser', 'superuser', 'ROLE_TERMINATOR, ROLE_USER, ROLE_ADMIN');

insert  into public.authors (id, name)
values (1, 'Джеймс Клавелл');
insert  into public.authors (id, name)
values (2, 'Эрих Мария Ремарк');

insert  into public.genres (id, name)
values (1, 'Приключения');
insert  into public.genres (id, name)
values (2, 'Роман');

insert  into public.books (id,name, author_id, genre_id)
values (1, 'Сегун', (select id from public.authors a where a.name = 'Джеймс Клавелл'),
        (select id from public.genres g where g.name = 'Приключения'));

insert  into public.books (id, name, author_id, genre_id)
values (2, 'Тай-пен', (select id from public.authors a where a.name = 'Джеймс Клавелл'),
        (select id from public.genres g where g.name = 'Приключения'));

insert  into public.books (id,name, author_id, genre_id)
values (3, 'Три товарища', (select id from public.authors a where a.name = 'Эрих Мария Ремарк'),
        (select id from public.genres g where g.name = 'Роман'));

insert  into public.books (id, name, author_id, genre_id)
values (4, 'Триумфайльная арка', (select id from public.authors a where a.name = 'Эрих Мария Ремарк'),
        (select id from public.genres g where g.name = 'Роман'));

insert  into public.notes (id, note_text, note_date, note_author, book_id)
values (1, 'отличная книга','2021-07-01','Павлов Д.В.',1);

insert  into public.notes (id, note_text, note_date, note_author, book_id)
values (2, 'одна из лучших книг','2021-07-01','Павлов Д.В.',2);