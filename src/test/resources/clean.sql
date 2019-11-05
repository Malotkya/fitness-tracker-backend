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
    "[{\"Value\":\"Weight\", \"Static\":false}, {\"Value\":\"Goal\", \"Static\":true}]",
    null,
    false
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
WHERE user_id = 1
ORDER By entry;

SELECT * FROM users;