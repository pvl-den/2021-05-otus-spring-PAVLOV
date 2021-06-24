insert into authors (`name`) values ('Джеймс Клавелл');
insert into authors (`name`) values ('Эрих Мария Ремарк');

insert into genres (`name`) values ('Приключения');
insert into genres (`name`) values ('Роман');

insert into  books (`name`, author_id, genre_id)
values
( 'Сегун', (select id from authors a where a.name = 'Джеймс Клавелл'),
              (select id from genres g where g.name = 'Приключения'));

insert into  books (`name`, author_id, genre_id) values
( 'Тай-пен', (select id from authors a where a.name = 'Джеймс Клавелл'),
    (select id from genres g where g.name = 'Приключения'));

insert into  books (`name`, author_id, genre_id) values
( 'Три товарища', (select id from authors a where a.name = 'Эрих Мария Ремарк'),
  (select id from genres g where g.name = 'Роман'));

insert into  books (`name`, author_id, genre_id) values
('Триумфайльная арка', (select id from authors a where a.name = 'Эрих Мария Ремарк'),
    (select id from genres g where g.name = 'Роман'));
