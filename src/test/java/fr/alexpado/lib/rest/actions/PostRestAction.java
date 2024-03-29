package fr.alexpado.lib.rest.actions;

import fr.alexpado.lib.rest.RestAction;
import fr.alexpado.lib.rest.enums.RequestMethod;
import fr.alexpado.lib.rest.interfaces.IRestResponse;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PostRestAction extends RestAction<JSONObject> {

    @Override
    public @NotNull RequestMethod getRequestMethod() {

        return RequestMethod.POST;
    }

    @Override
    public @NotNull String getRequestURL() {

        return "https://postman-echo.com/post";
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
    public JSONObject convert(IRestResponse response) {

        return new JSONObject(new String(response.getBody()));
    }
}
