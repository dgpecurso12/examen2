package mx.unam.dgpe.sample.controller;

import org.apache.log4j.Logger;
import org.junit.Test;

import io.vertx.core.AbstractVerticle;
import static mx.unam.dgpe.sample.controller.RestUtil.*;
import static org.junit.Assert.*;

public class TestMyController extends AbstractVerticle {
    private static final Logger logger = Logger.getLogger(TestMyController.class);
    
    @Test
    public void ok() throws Exception {
        String result = sendGet("https://www.binance.com/api/v3/ticker/price?symbol=BTCUSDT");
        logger.info(result);
        assertTrue(false);
    }

}
