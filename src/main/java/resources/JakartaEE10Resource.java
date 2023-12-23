package resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 * Recurso REST para demonstrar o uso de Jakarta EE 10.
 */
@Path("jakartaee10")
public class JakartaEE10Resource {

    /**
     * Método GET que responde com uma mensagem simples.
     * @return Resposta com a mensagem "ping Jakarta EE".
     */
    @GET
    public Response ping() {
        return Response
                .ok("ping Jakarta EE")
                .build();
    }

    // Considerar adicionar outros métodos para POST, PUT, DELETE, etc.
}
