use fitnessTracker;

delete from log;
delete from machines;
delete from users;

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