package fr.alexpado.lib.rest.actions;

import fr.alexpado.lib.rest.RestAction;
import fr.alexpado.lib.rest.enums.RequestMethod;
import fr.alexpado.lib.rest.interfaces.IRestResponse;
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
    public JSONObject convert(IRestResponse response) {

        return new JSONObject(new String(response.getBody()));
    }

}
