package de.leuphana.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.zipkin.ZipkinSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;

@Configuration
public class OpenTelemetryConfig {

    @Value("${otel.zipkin.url:http://zipkin-server:9411/api/v2/spans}")
    private String zipkinUrl;
    
    @Value("${spring.application.name:unknown_service}")
    private String serviceName;

    @Bean
    public Tracer initTracer() {
        ZipkinSpanExporter zipkinSpanExporter = ZipkinSpanExporter.builder()
                .setEndpoint(zipkinUrl)
                .build();

        BatchSpanProcessor spanProcessor = BatchSpanProcessor.builder(zipkinSpanExporter).build();

        Resource serviceNameResource = Resource.builder()
                .put(ResourceAttributes.SERVICE_NAME, serviceName)
                .build();

        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
                .setResource(serviceNameResource)
                .addSpanProcessor(spanProcessor)
                .build();

        OpenTelemetrySdk openTelemetrySdk = OpenTelemetrySdk.builder()
                .setTracerProvider(tracerProvider)
                .buildAndRegisterGlobal();

        return openTelemetrySdk.getTracer(serviceName);
    }
}
