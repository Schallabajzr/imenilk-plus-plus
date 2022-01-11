-- auto-generated definition
create table kontakt
(
    id                text not null
        constraint kontakt_pk
            primary key,
    ime               text not null,
    priimek           text not null,
    naslov            text,
    elektronska_posta text,
    telefon           text,
    mobilni_telefon   text,
    opomba            text
);

alter table kontakt
    owner to postgres;

create unique index kontakt_id_uindex
    on kontakt (id);