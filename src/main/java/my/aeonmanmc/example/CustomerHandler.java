package my.aeonmanmc.example;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class CustomerHandler {

    //@Autowired
    //private CustomerRepo customerRepo;

    public Mono<ServerResponse> addCustomer(ServerRequest request) {
        Flux<CustomerDto> customers = Flux.range(0, 10).map(i -> new CustomerDto(i, "Customer " + i));
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(customers, CustomerDto.class);
    }

    public Mono<ServerResponse> getAllCustomers(ServerRequest request) {
        Flux<CustomerDto> customers = Flux.range(0, 10).delayElements(Duration.ofSeconds(1)).map(i -> new CustomerDto(i, "Customer " + i));
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(customers, CustomerDto.class);
    }

    public Mono<ServerResponse> getCustomer(String id) {
        Flux<CustomerDto> customers = Flux.range(0, 10).map(i -> new CustomerDto(i, "Customer " + i));
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(customers, CustomerDto.class);
    }

    public Mono<ServerResponse> updateCustomer(ServerRequest request) {
        Flux<CustomerDto> customers = Flux.range(0, 10).map(i -> new CustomerDto(i, "Customer " + i));
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(customers, CustomerDto.class);
    }

    public Mono<ServerResponse> deleteCustomer(ServerRequest request) {
        Flux<CustomerDto> customers = Flux.range(0, 10).map(i -> new CustomerDto(i, "Customer " + i));
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(customers, CustomerDto.class);
    }

}
