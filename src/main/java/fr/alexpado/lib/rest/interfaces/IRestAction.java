package fr.alexpado.lib.rest.interfaces;

import fr.alexpado.lib.rest.exceptions.RestException;

import java.util.function.Consumer;


public interface IRestAction<T> {

    /**
     * Sends the http request asynchronously.
     */
    void queue();

    /**
     * Send the http request asynchronously, calling the consumer once the response handled.
     *
     * @param onSuccess
     *         The consumer that will receive the http response.
     */
    void queue(Consumer<T> onSuccess);

    /**
     * Send the http request asynchronously, calling the consumer once the response handled, or calling the error
     * consumer if an error occurred.
     *
     * @param onSuccess
     *         The consumer that will receive the http response.
     * @param onFailure
     *         The consumer that will receive the error.
     */
    void queue(Consumer<T> onSuccess, Consumer<Throwable> onFailure);

    /**
     * Send the http request synchronously, returning the http response, or throwing an exception if an error occurs.
     *
     * @return The http response.
     *
     * @throws RestException
     *         If the http response code is not in the 2xx range.
     * @throws Exception
     *         If the http couldn't be sent or if the response couldn't be parsed.
     */
    T complete() throws Exception;

}
