import dao.ShortSequenceDao;
import http.HttpStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import models.ShortSequence;
import validators.ShortSequenceValidator;

public class MainVerticle extends AbstractVerticle {

    private static final String SHORT_SEQUENCE_URL = "short_sequence";
    private static final String PATH_DELIMITER = "/";
    private static final String ID_PARAMETER = "id";
    private static final String ID_URL_PARAMETER = ":" + ID_PARAMETER;

    @Override
    public void start() throws Exception {
        final Router router = Router.router(vertx);
        router.post().handler(BodyHandler.create());
        //Short sequence
        router.get(PATH_DELIMITER + SHORT_SEQUENCE_URL + PATH_DELIMITER + ID_URL_PARAMETER)
                .handler(this::getShortSequenceById);
        router.post(PATH_DELIMITER + SHORT_SEQUENCE_URL)
                .handler(this::createShortSequence);
        router.put(PATH_DELIMITER + SHORT_SEQUENCE_URL + PATH_DELIMITER + ID_URL_PARAMETER)
                .handler(this::updateShortSequence);
        router.delete(PATH_DELIMITER + SHORT_SEQUENCE_URL + PATH_DELIMITER + ID_URL_PARAMETER)
                .handler(this::deleteShortSequenceById);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080);
    }


    public void end() throws Exception {
    }

    /*****************************************************************************************
    /* Handlers
    /****************************************************************************************/
    private void getShortSequenceById(final RoutingContext routingContext){

        HttpServerResponse response = routingContext.response();
        JsonObject jsonResponse = new JsonObject();
        try {
            Integer id = Integer.parseInt(routingContext.request().getParam(ID_PARAMETER));
            ShortSequence sequence = ShortSequenceDao.getShortSequenceById(id);
            if(sequence == null){
                response.setStatusCode(HttpStatus.NOT_FOUND);
                jsonResponse.put("Error", "Id doesn't exists");
            } else {
                jsonResponse = sequence.toJson();
                response.setStatusCode(HttpStatus.OK);
            }
        } catch (NumberFormatException e) {
            response.setStatusCode(HttpStatus.INTERNAL_ERROR);
            jsonResponse.put("Error", "Invalid id");
        } finally {
            response.putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(jsonResponse));
        }
    }

    private void createShortSequence(final RoutingContext routingContext){
        HttpServerResponse response = routingContext.response();
        JsonObject jsonResponse = new JsonObject();

        JsonObject body = routingContext.getBodyAsJson();
        //Check that the body is valid & that the sequence doesn't exist
        // & that it doesn't overlap other sequences
        if (ShortSequenceValidator.isValidBody(body)
            && ShortSequenceValidator.canCreateSequence(body)) {
            ShortSequence sequence = ShortSequence.buildShortSequenceFromJson(body);
            ShortSequenceDao.putShortSequence(sequence);
            response.setStatusCode(HttpStatus.CREATED);
        } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            jsonResponse.put("Error", "Invalid parameters");
        }
        response.putHeader("content-type", "application/json")
                .end(Json.encodePrettily(jsonResponse));
    }
    private void updateShortSequence(final RoutingContext routingContext){

    }

    private void deleteShortSequenceById(final RoutingContext routingContext){
        HttpServerResponse response = routingContext.response();
        JsonObject jsonResponse = new JsonObject();

        try {
            Integer id = Integer.parseInt(routingContext.request().getParam(ID_PARAMETER));
            ShortSequenceDao.deleteSequenceById(id);
            response.setStatusCode(HttpStatus.ACCEPTED);
        }catch(NumberFormatException e){
            response.setStatusCode(HttpStatus.INTERNAL_ERROR);
            jsonResponse.put("Error", "Invalid id");
        }finally {
            response.putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(jsonResponse));
        }
    }

    private void getLongSequenceById(final RoutingContext routingContext){

    }
    private void createLongSequence(final RoutingContext routingContext){

    }
    private void updateLongSequence(final RoutingContext routingContext){

    }
    private void deleteLongSequenceById(final RoutingContext routingContext){

    }
}
