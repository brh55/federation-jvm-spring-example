package com.example.products;

import com.apollographql.federation.graphqljava.Federation;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.apollographql.federation.graphqljava.tracing.FederatedTracingInstrumentation;


@Configuration
public class GraphQLConfiguration {

  // Add federated tracing to the GraphQL server
  @Bean
  public FederatedTracingInstrumentation federatedTracingInstrumentation() {
    return new FederatedTracingInstrumentation();
  }

  @Bean
  public GraphQlSourceBuilderCustomizer federationTransform() {
    return builder -> {
      builder.schemaFactory((registry, wiring)->
        Federation.transform(registry, wiring)
          .fetchEntities(env -> null)
          .resolveEntityType(env -> null)
          .build()
      );
    };
  }
}
