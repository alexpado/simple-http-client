package fr.alexpado.lib.rest.exceptions;

import fr.alexpado.lib.rest.interfaces.IRestResponse;

import java.util.Map;

public class RestException extends Exception implements IRestResponse {

    private final byte[]              responseBody;
    private final int                 responseStatus;
    private final Map<String, String> headers;

    /**
     * Create a new {@link RestException} instance.
     *
     * @param responseBody
     *         The response body that the server returned.
     * @param responseStatus
     *         The response status that the server returned.
     * @param headers
     *         The response headers that the server returned.
     */
    public RestException(byte[] responseBody, int responseStatus, Map<String, String> headers) {

        super("The server did not responded with 2xx code.");
        this.responseBody   = responseBody;
        this.responseStatus = responseStatus;
        this.headers        = headers;
    }

    @Override
    public byte[] getBody() {

        return this.responseBody;
    }

    @Override
    public int getCode() {

        return this.responseStatus;
    }

    @Override
    public Map<String, String> getHeaders() {

        return this.headers;
    }
}
