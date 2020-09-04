package me.anisekai.lib.rest.interfaces;

import me.anisekai.lib.rest.enums.RequestMethod;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface IRestOptions<T> {

    /**
     * Retrieve the {@link RequestMethod} to use when sending the request.
     *
     * @return The {@link RequestMethod} to use.
     */
    @NotNull RequestMethod getRequestMethod();

    /**
     * Retrieve the request URL to which the request should be sent.
     *
     * @return The request URL.
     */
    @NotNull String getRequestURL();

    /**
     * Retrieve a map of header that should be included in the request.
     *
     * @return A map of http headers.
     */
    @NotNull Map<String, String> getRequestHeaders();

    /**
     * Retrieve a map of parameters that should be included in the request URL.
     *
     * @return A map of parameters.
     */
    @NotNull Map<String, String> getRequestParameters();

    /**
     * Retrieve the request body to send along with the request.
     *
     * @return The request body.
     */
    @NotNull String getRequestBody();

    /**
     * Convert the response body to the desired type for this request.
     *
     * @param requestBody
     *         The response body received.
     *
     * @return The response body as byte array.
     */
    T convert(byte[] requestBody);

}
