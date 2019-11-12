use fitnessTracker;

SET SQL_SAFE_UPDATES=0;
delete from log;
delete from machines;
delete from users;
SET SQL_SAFE_UPDATES=1;

insert into users
values (
	1,
    "ajmalotky",
    "$2a$10$2H0t6upOnchDj0HE7raHi.2ycn2NbrLQYqKmgdfbzgc7pw/vKshDC",
    "Alex",
    "Malotky",
    null,
    "\"Settings\":{\"Goal\":250}, \"Running\":{\"Laps\":4}, \"Bench Press\":{\"Seat Position\":2.5}"
);

insert into machines
values (
	1,
    "Settings",
    "[{\"Value\":\"Weight\", \"Static\":false},{\"Value\":\"Goal\", \"Static\":true}]",
    null,
    true
);

insert into machines
values (
	2,
    "Running",
    "[{\"Value\":\"Laps\", \"Static\":true},{\"Value\":\"Time\", \"Static\":false}]",
    null,
    true
);

insert into machines
values (
	3,
    "Bench Press",
    "[{\"Value\":\"Seat Position\", \"Static\":true},{\"Value\":\"Weight\", \"Static\":false},{\"Value\":\"Reps\", \"Static\":false}]",
    null,
    true
);

insert into log
values (
	1573553736000,
    1,
    1,
    "{\"Weight\":300}"
);

insert into log
values (
	1573553736000,
    1,
    2,
    "{\"Time\":\"10 minutes\"}"
);

insert into log
values (
	1573553736000,
    1,
    3,
    "{\"Reps\":12, \"Weight\":100}"
);

select * from log;