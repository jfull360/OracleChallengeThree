create table IF NOT EXISTS topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(100) not null,
    fecha_creacion varchar(100) not null,
    estatus varchar(100) not null,
    autor varchar(100) not null,
    curso varchar(100) not null,
    primary key(id)
);
