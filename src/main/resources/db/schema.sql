create table if not exists post (
    id serial primary key,
    title text,
    link text,
    description text,
    time timestamp without time zone,
    unique (name, text)
);