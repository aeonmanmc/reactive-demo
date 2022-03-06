package my.aeonmanmc.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                .POST("/customers", handler::addCustomer)
                .GET("/customers", handler::getAllCustomers)
                .PUT("/customers/{id}", handler::updateCustomer)
                .DELETE("/customers/{id}", handler::deleteCustomer)
                .build();
    }
}
