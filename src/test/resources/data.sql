create table customers
(
    id          uuid         not null
        constraint customers_pkey
            primary key,
    created_at  timestamp    not null,
    is_deleted  boolean      not null,
    modified_at timestamp,
    title       varchar(255) not null
);

alter table customers
    owner to postgres;

create table products
(
    id          uuid           not null
        constraint products_pkey
            primary key,
    created_at  timestamp      not null,
    description varchar(1024),
    is_deleted  boolean        not null,
    modified_at timestamp,
    price       numeric(10, 2) not null,
    title       varchar(255)   not null,
    customer_id uuid           not null
        constraint fk29w1glmsx19fyn0ts34ak8pc5
            references customers
);

alter table products
    owner to postgres;