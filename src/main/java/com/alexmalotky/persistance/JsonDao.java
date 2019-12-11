package com.alexmalotky.persistance;

import com.alexmalotky.entity.*;
import com.alexmalotky.util.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class JsonDao {
    private GenericDao<User> dao = new GenericDao<>(User.class);
    private final Logger logger = LogManager.getLogger(this.getClass());

    public String getByUserId(int id) {
        User u = dao.getById(id);
        return u.toJson();
    }

    public String insert(String json) {
        return insert(JsonParser.parse(json));
    }

    public String insert(User u) {
        GenericDao<Log> logDao = new GenericDao<>(Log.class);

        u.setText("\"Settings\":{\"Goal\":\"\"}");
        int id = (int)dao.insert(u);

        Log log = buildFirstLog(u);
        logDao.insert(log);

        return getByUserId(id);
    }

    public void delete(String json) {
        User u = JsonParser.parse(json);
        Set<Machine> set = u.getMachines();

        dao.delete(u);

        GenericDao<Machine> machineDao = new GenericDao<>(Machine.class);
        for(Machine m: set) {
            deleteMachine(m);
        }
    }

    public String saveOrUpdate(String json) {
        User input = JsonParser.parse(json);

        updateMachines(input);
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

        l.setDate(new Date().getTime());
        l.setValue("{\"Weight\":\"\"}");

        return l;
    }

    private void updateMachines(User user) {

        //Set up session
        //Session session = getSession();
        Set<Machine> set = user.getMachines();
        GenericDao<Machine> machineDao = new GenericDao<>(Machine.class);


        for(Machine m: set) {
            //Add new logs
            /*Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(m);
            transaction.commit();*/
            machineDao.saveOrUpdate(m);

            //Remove old logs
            Machine records = machineDao.getById(m.getId()); //.get(Machine.class, m.getId());
            GenericDao<Log> logDao = new GenericDao<>(Log.class);

            for(Log l : records.getLogs()) {
                if( (l.getUser().getId() == user.getId()) && (!m.getLogs().contains(l)) ){
                    /*for(Log test: m.getLogs() ) {
                        if( test.equals(l) )*/
                            logDao.delete(l);
                    //}

                }
            }
        }

        //session.close();
    }

    private void deleteMachine(Machine machine) {
        if( !machine.getViewable() ) {
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(machine);
            transaction.commit();
            session.close();
        }
    }

    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

}
