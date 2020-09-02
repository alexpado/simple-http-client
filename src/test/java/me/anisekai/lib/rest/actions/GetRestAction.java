package me.anisekai.lib.rest.actions;

import me.anisekai.lib.rest.RestAction;
import me.anisekai.lib.rest.enums.RequestMethod;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetRestAction extends RestAction<JSONObject> {

    @Override
    public @NotNull RequestMethod getRequestMethod() {

        return RequestMethod.GET;
    }

    @Override
    public @NotNull String getRequestURL() {

        return "https://postman-echo.com/get";
    }

    @Override
    public @NotNull Map<String, String> getRequestParameters() {

        Map<String, String> parameters = new HashMap<>();

        parameters.put("var1", "val1");
        parameters.put("var2", "val2");

        return parameters;
    }

    @NotNull
    @Override
    public JSONObject convert(String requestBody) {

        return new JSONObject(requestBody);
    }

}
