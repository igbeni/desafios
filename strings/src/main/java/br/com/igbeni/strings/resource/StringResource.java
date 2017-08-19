package br.com.igbeni.strings.resource;

import br.com.igbeni.strings.model.Response;
import com.codahale.metrics.annotation.Timed;
import org.json.JSONException;
import org.json.JSONObject;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static br.com.igbeni.strings.model.Text.*;
import static javax.ws.rs.core.Response.Status;

@Path("/strings")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
public class StringResource {

    @POST
    @Timed
    public Response formatText(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);

        if (!jsonObject.has("text")) {
            return new Response<>(Status.BAD_REQUEST, "There is no text on the input data");
        }

        TextBuilder textBuilder = TextBuilder.create();

        if (jsonObject.has("max")) {
            textBuilder.withMaximumCharactersNumber(jsonObject.getInt("max"));
        }

        if (jsonObject.has("justify")) {
            textBuilder.withJustification(jsonObject.getBoolean("justify"));
        }

        textBuilder.fromString(jsonObject.getString("text"));

        return new Response<>(Status.OK, textBuilder.build().linesAsString());
    }
}
