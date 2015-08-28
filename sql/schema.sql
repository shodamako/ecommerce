-- 商品

create table items (
  id bigserial not null
  , name text not null
  , description text not null
  , price integer not null
  , imagePath text not null
  , deleted boolean default false not null
  , constraint items_PKC primary key (id)
) ;

alter table items add constraint items_IX1
  unique (name) ;
  
--　注文詳細
  
  create table order_items (
  id bigserial not null
  , item_id bigint not null
  , order_id bigint not null
  , quantity integer not null
  , constraint order_items_PKC primary key (id)
) ;

-- 注文

create table orders (
  id bigserial not null
  , order_number text not null
  , user_id bigint not null
  , status integer not null
  , total_price integer not null
  , date Date not null
  , constraint orders_PKC primary key (id)
) ;

create sequence order_number_count
start with 1
increment by 1;

-- 管理者

create table admin_users (
  id bigserial not null
  , name text not null
  , email text not null
  , password text not null
  , constraint admin_users_PKC primary key (id)
) ;
alter table admin_users add constraint メールアドレス
  unique (email) ;
  
--  利用者
  
  create table users (
  id bigserial not null
  , name text not null
  , email text not null
  , password text not null
  , address text not null
  , telephone text not null
  , constraint users_PKC primary key (id)
) ;