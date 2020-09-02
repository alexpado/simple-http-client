package me.anisekai.lib.rest.actions;

import me.anisekai.lib.rest.RestAction;
import me.anisekai.lib.rest.enums.RequestMethod;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DeleteRestAction extends RestAction<JSONObject> {

    @Override
    public @NotNull RequestMethod getRequestMethod() {

        return RequestMethod.DELETE;
    }

    @Override
    public @NotNull String getRequestURL() {

        return "https://postman-echo.com/delete";
    }

    @Override
    public @NotNull Map<String, String> getRequestHeaders() {

        Map<String, String> parameters = new HashMap<>();

        parameters.put("Content-Type", "application/json");

        return parameters;
    }

    @Override
    public @NotNull String getRequestBody() {

        JSONObject data = new JSONObject();

        data.put("var1", "val1");
        data.put("var2", "val2");

        return data.toString();
    }

    @NotNull
    @Override
    public JSONObject convert(String requestBody) {

        return new JSONObject(requestBody);
    }
}