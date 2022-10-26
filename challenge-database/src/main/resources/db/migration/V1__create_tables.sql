-- CUSTOMER
create table if not exists customer(
  id UUID    constraint customer_pk primary key,
  name       varchar(255) NOT NULL,
  email      varchar(255) UNIQUE NOT NULL,
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp
);

-- PRODUCT
create table if not exists product(
  id UUID      constraint product_pk primary key,
  image        varchar(255) NOT NULL,
  brand        varchar(255) NOT NULL,
  title        varchar(255) UNIQUE NOT NULL,
  price        decimal(10,2) NOT NULL,
  review_score integer NOT NULL,
  created_at   timestamp default current_timestamp,
  updated_at   timestamp default current_timestamp
);

-- CUSTOMER_PRODUCT_FAVORITE
create table if not exists customer_product_favorite(
  id UUID     constraint customer_product_favorite_pk primary key,
  customer_id UUID NOT NULL,
  product_id  UUID NOT NULL,
  active      BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT  uc_customer_product UNIQUE(customer_id, product_id),
  CONSTRAINT  fk_customer_customer_product_favorite FOREIGN KEY (customer_id) REFERENCES customer(id),
  CONSTRAINT  fk_product_customer_product_favorite FOREIGN KEY (product_id) REFERENCES product(id)
);

create index if not exists idx_customer_product_favorite on customer_product_favorite(active);
