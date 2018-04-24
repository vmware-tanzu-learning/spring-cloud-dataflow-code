DROP TABLE IF EXISTS person;

create table person (
  id int not null auto_increment,
	 name varchar(255) not null,
	 email varchar(255) not null unique,
	 quote varchar(255),
	 zipcode int,
	 primary key (id)
);
