package fr.alexpado.lib.rest.exceptions;

import fr.alexpado.lib.rest.interfaces.IRestResponse;

import java.util.List;
import java.util.Map;

public class RestException extends Exception implements IRestResponse {

    private final IRestResponse response;

    /**
     * Create a new {@link RestException} instance.
     *
     * @param response
     *         The rest response read from the HTTP return
     */
    public RestException(IRestResponse response) {

        super("The server did not responded with 2xx code.");
        this.response = response;
    }

    /**
     * Retrieve the byte array read from the HTTP response.
     *
     * @return A byte array.
     */
    @Override
    public byte[] getBody() {

        return this.response.getBody();
    }

    /**
     * Retrieve the headers contained in the HTTP response.
     *
     * @return A map containing the headers.
     */
    @Override
    public Map<String, List<String>> getHeaders() {

        return this.response.getHeaders();
    }

    /**
     * Retrieve the HTTP code.
     *
     * @return The HTTP code.
     */
    @Override
    public int getStatusCode() {

        return this.response.getStatusCode();
    }
}
