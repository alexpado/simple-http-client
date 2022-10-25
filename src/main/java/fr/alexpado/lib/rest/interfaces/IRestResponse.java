package fr.alexpado.lib.rest.interfaces;

import java.util.Map;

public interface IRestResponse {

    byte[] getBody();

    int getCode();

    Map<String, String> getHeaders();
}
