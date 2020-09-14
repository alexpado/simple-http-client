package fr.alexpado.lib.rest.exceptions;

public class RestException extends Exception {

    private final byte[] responseBody;
    private final int    responseStatus;

    /**
     * Create a new {@link RestException} instance.
     *
     * @param responseBody
     *         The response body that the server returned.
     * @param responseStatus
     *         The response status that the server returned.
     */
    public RestException(byte[] responseBody, int responseStatus) {

        super("The server did not responded with 2xx code.");
        this.responseBody   = responseBody;
        this.responseStatus = responseStatus;
    }

    /**
     * Retrieves the response body that the server returned.
     *
     * @return The response body.
     */
    public byte[] getResponseBody() {

        return responseBody;
    }

    /**
     * Retrieves the response status that the server returned.
     *
     * @return The status code.
     */
    public int getResponseStatus() {

        return responseStatus;
    }
}
