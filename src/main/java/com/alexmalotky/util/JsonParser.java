package com.alexmalotky.util;

import com.alexmalotky.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class JsonParser {

    private static final Logger logger = LogManager.getLogger(JsonParser.class);

    public static String stringify(User user) {
        return user.toJson();
    }

    public static User parse(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);

        User user = new User(jsonObject);
        user.setText(pullLeftOvers(jsonObject));

        return user;
    }

    public static String errorCode(String message, int code) {
        return "{\"Error\":\"" + message +
                "\", \"Code\":" + code + "}";
    }

    private static String pullLeftOvers(JSONObject object) {
        object.remove("id");
        object.remove("userName");
        object.remove("firstName");
        object.remove("lastName");
        object.remove("email");
        object.remove("Activities");

        String output = object.toString().trim();
        //logger.debug(output);

        if(output.length() <= 2)
            return "";

        output = output.substring(1, output.length()-1);
        //logger.debug(output);

        return output;
    }




}
