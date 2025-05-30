create table if not exists post (
    id serial primary key,
    name text,
    text text,
    link text,
    created timestamp without time zone,
    unique (name, text)
);