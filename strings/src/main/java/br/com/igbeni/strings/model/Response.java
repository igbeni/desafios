package br.com.igbeni.strings.model;

import javax.ws.rs.core.Response.Status;

/**
 * Object representing the server's response.
 *
 * @param <T> Response class.
 * @author Iggor Alves
 */
public class Response<T> {
    private Status status;
    private T data;

    /**
     * Construts a {@link Response} instance.
     *
     * @param status Response status.
     * @param data   Response data.
     */
    public Response(Status status, T data) {
        this.status = status;
        this.data = data;
    }

    /**
     * Construts a {@link Response} instance with status {@link Status#OK}.
     *
     * @param data Response data.
     */
    public Response(T data) {
        this.status = Status.OK;
        this.data = data;
    }

    /**
     * Function to get the response status.
     *
     * @return Response status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Function to get the response data.
     *
     * @return Response data.
     */
    public T getData() {
        return data;
    }
}
