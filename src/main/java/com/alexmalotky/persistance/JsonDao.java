package com.alexmalotky.persistance;

import com.alexmalotky.entity.*;
import com.alexmalotky.util.JsonParser;

import javax.json.Json;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Set;

public class JsonDao {
    private GenericDao<User> dao = new GenericDao<>(User.class);

    public String getByUserId(int id) {
        return ((User)dao.getById(id)).toJson();
    }

    public String insert(String json) {
        User u = JsonParser.parse(json);

        GenericDao<Log> logDao = new GenericDao<>(Log.class);
        Log log = buildFirstLog(u);

        int id = dao.insert(u);
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
        User u = JsonParser.parse(json);
        saveOrUpdateMachines(u.getMachines());

        dao.saveOrUpdate(u);
        return getByUserId(u.getId());
    }

    private static Log buildFirstLog(User user) {
        GenericDao<Machine> machineDao = new GenericDao<>(Machine.class);
        Log l = new Log();
        l.setMachine(machineDao.getById(1));
        l.setUser(user);

        DateFormat now = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        l.setDate(now.format(LocalDate.now()));
        l.setValue("");

        return l;
    }

    private void saveOrUpdateMachines(Set<Machine> set) {
        GenericDao<Machine> machineDao = new GenericDao<>(Machine.class);

        for (Machine m: set) {
            if(machineDao.getById(m.getId()) == null)
                machineDao.insert(m);

            saveOrUpdateLogs(m.getLogs());
        }
    }

    private void saveOrUpdateLogs(Set<Log> set) {
        GenericDao<Log> machineDao = new GenericDao<>(Log.class);

        for (Log l: set) {
                machineDao.insert(l);
        }
    }

}
