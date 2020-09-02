package me.anisekai.lib.rest;


import me.anisekai.lib.rest.actions.GetRestAction;
import me.anisekai.lib.rest.actions.PostFormAction;
import me.anisekai.lib.rest.actions.PostRestAction;
import me.anisekai.lib.rest.actions.PutRestAction;
import me.anisekai.lib.rest.interfaces.IRestAction;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("REST Client Tests")
public class RestActionTests {

    @Test
    @DisplayName("GET")
    public void getRestActionTest() throws Exception {

        IRestAction<JSONObject> action = new GetRestAction();

        JSONObject result = action.complete();
        Assertions.assertTrue(result.has("args"));

        JSONObject args = result.getJSONObject("args");
        Assertions.assertTrue(args.has("var1"));
        Assertions.assertTrue(args.has("var2"));
    }

    @Test
    @DisplayName("POST")
    public void postRestActionTest() throws Exception {

        IRestAction<JSONObject> action = new PostRestAction();

        JSONObject result = action.complete();
        Assertions.assertTrue(result.has("data"));

        JSONObject data = result.getJSONObject("data");
        Assertions.assertTrue(data.has("var1"));
        Assertions.assertTrue(data.has("var2"));
    }

    @Test
    @DisplayName("POST Form")
    public void postFormActionTest() throws Exception {

        IRestAction<JSONObject> action = new PostFormAction();

        JSONObject result = action.complete();
        Assertions.assertTrue(result.has("form"));

        JSONObject data = result.getJSONObject("form");
        Assertions.assertTrue(data.has("var1"));
        Assertions.assertTrue(data.has("var2"));
    }

    @Test
    @DisplayName("PUT")
    public void putRestActionTest() throws Exception {

        IRestAction<JSONObject> action = new PutRestAction();

        JSONObject result = action.complete();
        Assertions.assertTrue(result.has("data"));

        JSONObject data = result.getJSONObject("data");
        Assertions.assertTrue(data.has("var1"));
        Assertions.assertTrue(data.has("var2"));
    }

    @Test
    @DisplayName("DELETE")
    public void deleteRestActionTest() throws Exception {

        IRestAction<JSONObject> action = new PutRestAction();

        JSONObject result = action.complete();
        Assertions.assertTrue(result.has("data"));

        JSONObject data = result.getJSONObject("data");
        Assertions.assertTrue(data.has("var1"));
        Assertions.assertTrue(data.has("var2"));
    }


}
