import http.HttpStatus;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;

import io.vertx.ext.unit.TestSuite;
import junit.framework.Assert;
import models.ShortSequence;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.sql.Timestamp;

public class ShortSequenceTest {
    private static Vertx vertx;
    private static Integer port = 8080;
    private static String PATH_DELIMITER = "/";
    private static String SHORT_SEQUENCE_URL = PATH_DELIMITER + "short_sequence";

    private static Long TIMESTAMP1 = Long.valueOf("1575483191000");//04/12/2019 à 19:13:11
    private static ShortSequence FAKE_SHORT_SEQUENCE_0 = new ShortSequence(
            0,
            0,
            "name1",
            "description",
            new Timestamp(TIMESTAMP1),
            15,
            3600,
            true,
            "jpeg",
            true,
            new BigInteger("10000"),
            "running",
            555,
            666);

    @Before
    public void before(TestContext context) throws IOException {
        vertx = Vertx.vertx();
        ServerSocket socket = new ServerSocket(0);
        port = socket.getLocalPort();
        socket.close();
        DeploymentOptions options = new DeploymentOptions()
                .setConfig(new JsonObject().put("http.port", port)
                );
        vertx.deployVerticle(MainVerticle.class.getName(), options, context.asyncAssertSuccess());
    }

    @Test
    public void getInvalidSequenceTest(TestContext context){
        final Async async = context.async();
        final Integer invalidId = 999;
        vertx.createHttpClient()
                .getNow(port, "localhost", SHORT_SEQUENCE_URL + PATH_DELIMITER + invalidId,
                        response -> {
                            context.assertTrue(response.statusCode() == HttpStatus.NOT_FOUND);
                            response.handler(body -> {
                                context.assertTrue(body.toString().contains("Hello"));
                                async.complete();
                            });
                        });
    }

    @Test
    public void getValidSequenceTest(TestContext context){
        final Async async = context.async();
        final Integer validId = 0;
        vertx.createHttpClient()
                .getNow(port, "localhost", SHORT_SEQUENCE_URL + PATH_DELIMITER + validId,
                        response -> {
                            context.assertTrue(response.statusCode() == HttpStatus.NOT_FOUND);
                            response.handler(body -> {
                                context.assertTrue(body.toString().contains("Hello"));
                                async.complete();
                            });
                        });
    }

    @Test
    public void postInvalidSequenceTest(TestContext context){
        final Async async = context.async();

        ShortSequence sequence = FAKE_SHORT_SEQUENCE_0;
        sequence.setId(8);
        sequence.setTikeeId(10);

        final String json = sequence.toJson().toString();
        final String length = Integer.toString(json.length());
        vertx.createHttpClient().post(port, "localhost", SHORT_SEQUENCE_URL)
                .putHeader("content-type", "application/json")
                .putHeader("content-length", length)
                .handler(response -> {
                    context.assertEquals(response.statusCode(), HttpStatus.BAD_REQUEST);
                    context.assertTrue(response.headers().get("content-type").contains("application/json"));
                })
                .write(json)
                .end();
    }
}
