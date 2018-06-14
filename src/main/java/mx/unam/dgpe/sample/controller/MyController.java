package mx.unam.dgpe.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
    import java.math.BigInteger;

public class MyController extends AbstractVerticle {
    private static final Logger logger = Logger.getLogger(MyController.class);
    
    public void start(Future<Void> fut) {
        logger.info("Inicializando Vertical");
        Router router = Router.router(vertx);
        //router.route("/*").handler(StaticHandler.create("assets")); // para invocar asi: http://localhost:8080/index.html
        // el directorio "upload-folder" será creado en la misma ubicación que el jar fue ejecutado
        router.route().handler(BodyHandler.create().setUploadsDirectory("upload-folder"));
        router.get("/api/primero").handler(this::primero);
        router.post("/api/segundo").handler(this::segundo);
	router.get("/api/calculando").handler(this::calculando);
        
        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx.createHttpServer().requestHandler(router::accept).listen(
                config().getInteger("http.port", 8080), result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });        
        
        logger.info("Vertical iniciada !!!");
    }  
    private void primero(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        String mode = request.getParam("mode");
        String jsonResponse = procesa(mode);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }
    
    private void segundo(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        String decoded = routingContext.getBodyAsString();
        String jsonResponse = procesa(decoded);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }

    private String procesa(String decoded) {
        Map<String, String> autos = new HashMap<>();
        autos.put("primero", "Ferrari");
        autos.put("segundo", "Lamborgini");
        autos.put("tercero", "Bugatti");
        
        Map<Object, Object> info = new HashMap<>();
        info.put("decoded", decoded);
        info.put("nombre", "gustavo");
        info.put("edad", "21");
        info.put("autos", autos);
        return Json.encodePrettily(info);
    }
  private void calculando(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
       
	String operandoA = request.getParam("a");
        String jsonResponse = factorial(operandoA);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
}
	private String factorial(String operandoA) {
		Integer numero=Integer.parseInt(operandoA);
		BigInteger resultado= bigFactorial(numero);
		
		String cantidad=resultado.toString();
		logger.info("cantidad:"+cantidad);
		System.out.println("cantidad:"+cantidad);
		BigInteger longitud = BigInteger.valueOf(cantidad.size());
		logger.info("longitud:"+longitud);
		System.out.println("longitud:"+longitud);
			
	
        Map<String, String> resultado = new HashMap<>();
        
    	resultado.put("resultado", ""+longitud);
    	
        return Json.encodePrettily(resultado);
}
	public static BigInteger bigFactorial(int n)
{
    // Empezamos la base del factorial
    BigInteger fac = new BigInteger("1");

    // Obtenemos el factorial solo si el numero es mayor o igual a 2
    if(n >= 2){
        // Proceso para obtener el factorial
        for(int i = 1; i <= n; i++){
            // Multiplicamos los numeros
            fac = fac.multiply(new BigInteger(i + ""));
        }
    }

    return fac;
}

}
