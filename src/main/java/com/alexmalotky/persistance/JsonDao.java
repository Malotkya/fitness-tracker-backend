package com.alexmalotky.persistance;

import com.alexmalotky.entity.*;
import com.alexmalotky.util.JsonParser;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonDao {
    private GenericDao<User> dao = new GenericDao<>(User.class);

    public String getByUserId(int id) {
        User u = dao.getById(id);
        return u.toJson();
    }

    public String insert(String json) {
        return insert(JsonParser.parse(json));
    }

    public String insert(User u) {
        GenericDao<Log> logDao = new GenericDao<>(Log.class);
        Log log = buildFirstLog(u);

        int id = (int)dao.insert(u);
        logDao.insert(log);

        return getByUserId(id);
    }

    public void delete(String json) {
        User u = JsonParser.parse(json);
        Set<Machine> set = u.getMachines();

        dao.delete(u);

        GenericDao<Machine> machineDao = new GenericDao<>(Machine.class);
        for(Machine m: set) {
            if( !m.getViewable() )
                machineDao.delete(m);
        }
    }

    public String saveOrUpdate(String json) {
        User input = JsonParser.parse(json);
        saveOrUpdateMachines(input);

        User user = dao.getById(input.getId());
        user.set(input);

        dao.saveOrUpdate(user);
        return user.toJson();
    }

    private static Log buildFirstLog(User user) {
        GenericDao<Machine> machineDao = new GenericDao<>(Machine.class);
        Log l = new Log();
        l.setMachine(machineDao.getById(1));
        l.setUser(user);

        l.setDate(new Date());
        l.setValue("{}");

        return l;
    }

    private void saveOrUpdateMachines(User user) {
        GenericDao<Machine> machineDao = new GenericDao<>(Machine.class);
        Set<Machine> set = user.getMachines();

        for (Machine m: set) {
            if(machineDao.getById(m.getId()) == null) {
                Set<Log> buffer = m.getLogs();
                m.setLogs(new HashSet<>());
                machineDao.insert(m);
                m.setLogs(buffer);
            }
            saveOrUpdateLogs(m, user);
        }
    }

    private void saveOrUpdateLogs(Machine machine, User user) {
        GenericDao<Log> logDao = new GenericDao<>(Log.class);
        Set<Log> set = machine.getLogs();
        List<Log> list = logDao.findByPropertyEqual("machine", machine);

        for (Log l: set) {
            if(!list.contains(l)) {
                l.setMachine(machine);
                l.setUser(user);
                logDao.insert(l);
            }

        }
    }

}
