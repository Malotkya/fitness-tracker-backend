use fitnessTracker;

drop table log;
drop table machines;
drop table users;

create table users (
	id int primary key auto_increment,
    user_name varchar(20) unique, 
    password varchar(256),
    first_name varchar(20),
    last_name varchar(20),
    email varchar(50),
    static text -- {"Column2":value}
);

create table machines (
	id int primary key auto_increment, 
    title varchar (30),
    settings text, -- [ {"Value":"Column1", "Static":false}, {"Value":"Column2", "Static":true}, {"Value":"Column3", "Static":false}]
    notes varchar(255),
    viewable bool
);

create table log (
	entry bigint,
    user_id int,
    machine_id int,
    value text, -- {"Column1":value, "Column3":value}
    primary key (entry, user_id, machine_id),
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (machine_id) references machines(id) on delete cascade
);

insert into users
values (
	1,
    "ajmalotky",
    "qwerty",
    "Alex",
    "Malotky",
    null,
    "\"Settings\":{\"Goal\":250}"
);

insert into machines
values (
	1,
    "Settings",
    "[{\"Value\":\"Weight\", \"Static\":false},{\"Value\":\"Goal\", \"Static\":true}]",
    null,
    true
);

insert into log
values (
	NOW(),
    1,
    1,
    "{\"Weight\":300}"
);

SELECT entry, title, value, settings, notes FROM machines
JOIN log ON log.machine_id = machines.id
ORDER By entry;

SELECT * FROM users;
SELECT * FROM machines;