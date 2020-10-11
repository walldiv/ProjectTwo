create table if not exists "that-team_schema".Addresses (
	addressID serial primary key,
	number integer not null,
	street varchar(50) not null,
	address2 varchar(50),
	city varchar(50) not null,
	state varchar(2) not null,
	country varchar(50) not null,
	zipcode numeric(5) not null
);

create table if not exists "that-team_schema".PhoneCarriers (
	phoneCarrierID serial primary key,
	phoneCarrier varchar(8) not null
);

create table if not exists "that-team_schema"."Users" (
	email varchar(30) primary key,
	firstname varchar(30) not null,
	lastname varchar(30) not null,
	password varchar(30) not null,
	phone numeric(11),
	phoneCarrierID integer not null references "that-team_schema".PhoneCarriers(phoneCarrierID),
	addressID integer not null references "that-team_schema".Addresses(addressID),
	experiencePoints integer not null
);

create table if not exists "that-team_schema".DifficultyLevels (
	difficultyLevelID serial primary key,
	difficultyLevel varchar(30)
);

create table if not exists "that-team_schema".Items (
	itemID serial primary key,
	itemName varchar(50) not null,
	description varchar(100) not null,
	quantity integer not null,
	image bytea not null,
	GPSLocation varchar(150) not null,
	difficultyLevel integer not null references "that-team_schema".DifficultyLevels(difficultyLevelID)
);

create table if not exists "that-team_schema".ItemHistorys (
	email varchar(30) not null references "that-team_schema"."Users"(email),
	itemID integer not null references "that-team_schema".Items(itemid),
	dateCollected timestamp not null,
	"comment" varchar(300) not null,
	rating smallint not null,
	primary key (email, itemid)
);

alter table "that-team_schema"."Users" add column if not exists "password" varchar(30) not null;
drop table "that-team_schema".itemhistorys;