package br.com.igbeni.strings.model;

import org.junit.Test;

import javax.ws.rs.core.Response.Status;

/**
 * Test suite for {@link Response}.
 *
 * @author Iggor Alves
 */
public class ResponseTest {
    private Response<? extends Object> response;
    private Status status;

    /**
     * Tests the creating a {@link Response}.
     */
    @Test
    public void createResponse() {
        response = new Response<>(new Object());
    }

    /**
     * Tests the creating a {@link Response} with status.
     */
    @Test
    public void createResponseWithStatus() {
        response = new Response<>(status, new Object());
    }
}