create database db_gestionstock;
use db_gestionstock;

create table produit(
 id  int NOT NULL AUTO_INCREMENT,
 nom varchar(20) NOT NULL,
 description varchar(100) NOT NULL,
 quantity int ,
 prix int ,
 Catégorie varchar (40),

);