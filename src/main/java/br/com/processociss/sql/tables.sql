create table if not exists dynamicQuery(

    id serial primary key,
    route varchar(70),
    sql varchar,
    tablebase varchar(50)

);

create table if not exists pessoa(

    id serial primary key,
    nome varchar(30),
    sobrenome varchar(30),
    nis int,
    email varchar(60)
);

--mnu_explorer
insert into dynamicQuery(route, sql,tablebase) values('mnu_funcionario', 'select * from pessoa ', ' pessoa where id > 0');

