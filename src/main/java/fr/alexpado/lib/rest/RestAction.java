package fr.alexpado.lib.rest;

import fr.alexpado.lib.rest.enums.RequestMethod;
import fr.alexpado.lib.rest.exceptions.RestException;
import fr.alexpado.lib.rest.interfaces.IRestAction;
import fr.alexpado.lib.rest.interfaces.IRestOptions;
import fr.alexpado.lib.rest.interfaces.IRestResponse;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class RestAction<T> implements IRestAction<T>, IRestOptions<T> {

    public static String mergeMapAsQuery(Map<String, String> map) {

        return map.keySet()
                  .stream()
                  .map(key -> String.format("%s=%s", key, map.get(key)))
                  .collect(Collectors.joining("&"));
    }

    /**
     * Retrieve a map of header that should be included in the request.
     *
     * @return A map of http headers.
     */
    @Override
    public @NotNull Map<String, String> getRequestHeaders() {

        return new HashMap<>();
    }

    /**
     * Retrieve a map of parameters that should be included in the request URL.
     *
     * @return A map of parameters.
     */
    @Override
    public @NotNull Map<String, String> getRequestParameters() {

        return new HashMap<>();
    }

    /**
     * Retrieve the request body to send along with the request.
     *
     * @return The request body.
     */
    @Override
    public @NotNull String getRequestBody() {

        return "";
    }

    /**
     * Convert the response body to the desired type for this request.
     *
     * @param response
     *         The response body received.
     *
     * @return The converted response body
     */
    @Override
    public T convert(IRestResponse response) {

        return null;
    }

    /**
     * Sends the http request asynchronously.
     */
    @Override
    public void queue() {

        this.queue(null);
    }

    /**
     * Send the http request asynchronously, calling the consumer once the response handled.
     *
     * @param onSuccess
     *         The consumer that will receive the http response.
     */
    @Override
    public void queue(Consumer<T> onSuccess) {

        this.queue(null, null);
    }

    /**
     * Send the http request asynchronously, calling the consumer once the response handled, or calling the error
     * consumer if an error occurred.
     *
     * @param onSuccess
     *         The consumer that will receive the http response.
     * @param onFailure
     *         The consumer that will receive the error.
     */
    @Override
    public void queue(Consumer<T> onSuccess, Consumer<Throwable> onFailure) {

        new Thread(() -> {
            try {
                T entity = this.complete();

                if (onSuccess != null) {
                    onSuccess.accept(entity);
                }

            } catch (Exception e) {

                if (onFailure != null) {
                    onFailure.accept(e);
                }
            }

        }).start();
    }

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
    @Override
    public T complete() throws Exception {

        String urlStr;
        // Build the URL.
        if (this.getRequestMethod() == RequestMethod.GET && !this.getRequestParameters().isEmpty()) {
            urlStr = String.format("%s?%s", this.getRequestURL(), mergeMapAsQuery(this.getRequestParameters()));
        } else {
            urlStr = this.getRequestURL();
        }

        URLConnection     connection = new URL(urlStr).openConnection();
        HttpURLConnection http       = ((HttpURLConnection) connection);

        http.setRequestMethod(this.getRequestMethod().name());
        this.getRequestHeaders().forEach(http::setRequestProperty);

        String requestBody = this.getRequestBody();

        if (this.getRequestMethod().isOutputSupported() && !requestBody.isEmpty()) {
            http.setDoOutput(true);

            try (OutputStream os = http.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }

        int     httpCode = http.getResponseCode();
        boolean isOk = httpCode >= HttpURLConnection.HTTP_OK && httpCode < HttpURLConnection.HTTP_MULT_CHOICE;

        if (httpCode == HttpURLConnection.HTTP_NO_CONTENT) {
            return null;
        }

        byte[] responseBody;

        try (InputStream stream = (isOk ? http.getInputStream() : http.getErrorStream())) {
            responseBody = stream.readAllBytes();
        }

        Map<String, String> headers = new HashMap<>();
        http.getHeaderFields().forEach((key, values) -> {
            if (key != null && values != null) {
                headers.put(key, String.join(", ", values));
            }
        });

        if (!isOk) {
            throw new RestException(responseBody, httpCode);
        }

        return this.convert(new IRestResponse() {

            @Override
            public byte[] getBody() {

                return responseBody;
            }

            @Override
            public int getCode() {

                return httpCode;
            }

            @Override
            public Map<String, String> getHeaders() {

                return headers;
            }
        });
    }

}
