/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/3/1 9:51:18                             */
/*==============================================================*/


drop table if exists appdiscount;

drop table if exists goods;

drop table if exists manager;

drop table if exists mgr_shopdis;

drop table if exists shop;

drop table if exists shopdiscount;

drop table if exists super_appdiscout;

drop table if exists supermanager;

drop table if exists uer_shopdiscount;

drop table if exists user;

drop table if exists user_appdiscount;

drop table if exists user_goods;

/*==============================================================*/
/* Table: appdiscount                                           */
/*==============================================================*/
create table appdiscount
(
   id                   bigint not null,
   disvalue             float,
   nowamout             int,
   totalamout           int,
   restriction          float,
   expirytime           date,
   discharge            float,
   primary key (id)
);

/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
   id                   bigint not null,
   shop_id              bigint,
   price                float,
   goodsintro           varchar(1024),
   leftamout            int,
   totalamout           int,
   goodsname            varchar(1024),
   goodspic             varchar(1024),
   primary key (id)
);

/*==============================================================*/
/* Table: manager                                               */
/*==============================================================*/
create table manager
(
   id                   bigint not null,
   shop_id              bigint,
   mpass                varchar(1024),
   mtype                bool,
   mtel                 int,
   primary key (id)
);

/*==============================================================*/
/* Table: mgr_shopdis                                           */
/*==============================================================*/
create table mgr_shopdis
(
   shopdis_id           bigint not null,
   mgr_id               bigint not null,
   id                   bigint not null,
   primary key (id)
);

/*==============================================================*/
/* Table: shop                                                  */
/*==============================================================*/
create table shop
(
   id                   bigint not null,
   mgr_id               bigint,
   shoptype             varchar(1024),
   shopadress           varchar(1024),
   shopintro            varchar(1024),
   ischeck              bool,
   shoppic              varchar(1024),
   shopname             varchar(1024),
   checkadvice          varchar(1024),
   primary key (id)
);

/*==============================================================*/
/* Table: shopdiscount                                          */
/*==============================================================*/
create table shopdiscount
(
   id                   bigint not null,
   shop_id              bigint,
   disvalue             float,
   nowamout             int,
   totalamout           int,
   restriction          float,
   expirytime           date,
   discharge            float,
   primary key (id)
);

/*==============================================================*/
/* Table: super_appdiscout                                      */
/*==============================================================*/
create table super_appdiscout
(
   supermgr_id          bigint not null,
   appdis_id            bigint not null,
   id                   bigint not null,
   primary key (id)
);

/*==============================================================*/
/* Table: supermanager                                          */
/*==============================================================*/
create table supermanager
(
   id                   bigint not null,
   smpass               varchar(1024),
   smtel                int,
   primary key (id)
);

/*==============================================================*/
/* Table: uer_shopdiscount                                      */
/*==============================================================*/
create table uer_shopdiscount
(
   uer_id               bigint not null,
   shopdis_id           bigint not null,
   id                   bigint not null,
   isuse                boolean,
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   bigint not null,
   upass                varchar(1024),
   utel                 varchar(1024),
   uadress              varchar(1024),
   uremain              float,
   openid               varchar(1024),
   primary key (id)
);

/*==============================================================*/
/* Table: user_appdiscount                                      */
/*==============================================================*/
create table user_appdiscount
(
   usei_id              bigint not null,
   appdis_id            bigint not null,
   id                   bigint not null,
   isuse                boolean,
   primary key (id)
);

/*==============================================================*/
/* Table: user_goods                                            */
/*==============================================================*/
create table user_goods
(
   user_id              bigint not null,
   goo_id               bigint not null,
   id                   bigint not null,
   otime                time,
   payway               int,
   isover               bool,
   isdiscount           bool,
   shouldpay            float,
   actualpay            float,
   primary key (id)
);

alter table goods add constraint FK_sales foreign key (shop_id)
      references shop (id) on delete restrict on update restrict;

alter table manager add constraint FK_manager_shop foreign key (shop_id)
      references shop (id) on delete restrict on update restrict;

alter table mgr_shopdis add constraint FK_mgr_shopdis foreign key (shopdis_id)
      references shopdiscount (id) on delete restrict on update restrict;

alter table mgr_shopdis add constraint FK_mgr_shopdis2 foreign key (mgr_id)
      references manager (id) on delete restrict on update restrict;

alter table shop add constraint FK_manager_shop2 foreign key (mgr_id)
      references manager (id) on delete restrict on update restrict;

alter table shopdiscount add constraint FK_shop_shopdiscout foreign key (shop_id)
      references shop (id) on delete restrict on update restrict;

alter table super_appdiscout add constraint FK_super_appdiscout foreign key (supermgr_id)
      references supermanager (id) on delete restrict on update restrict;

alter table super_appdiscout add constraint FK_super_appdiscout2 foreign key (appdis_id)
      references appdiscount (id) on delete restrict on update restrict;

alter table uer_shopdiscount add constraint FK_uer_appdiscount foreign key (uer_id)
      references user (id) on delete restrict on update restrict;

alter table uer_shopdiscount add constraint FK_uer_appdiscount2 foreign key (shopdis_id)
      references shopdiscount (id) on delete restrict on update restrict;

alter table user_appdiscount add constraint FK_user_shopdiscount foreign key (usei_id)
      references user (id) on delete restrict on update restrict;

alter table user_appdiscount add constraint FK_user_shopdiscount2 foreign key (appdis_id)
      references appdiscount (id) on delete restrict on update restrict;

alter table user_goods add constraint FK_user_goods foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_goods add constraint FK_user_goods2 foreign key (goo_id)
      references goods (id) on delete restrict on update restrict;

