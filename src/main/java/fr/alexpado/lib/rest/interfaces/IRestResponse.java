package fr.alexpado.lib.rest.interfaces;

import java.util.List;
import java.util.Map;

public interface IRestResponse {

    /**
     * Retrieve the byte array read from the HTTP response.
     *
     * @return A byte array.
     */
    byte[] getBody();

    /**
     * Retrieve the headers contained in the HTTP response.
     *
     * @return A map containing the headers.
     */
    Map<String, List<String>> getHeaders();

    /**
     * Retrieve the HTTP code.
     *
     * @return The HTTP code.
     */
    int getStatusCode();

}
