package com.shanInfotech.springBootApiGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.NetworkInterface;
import java.util.Collections;

@Component
public class ApiNetworkSetupClass {
    private static final Logger log = LoggerFactory.getLogger(ApiNetworkSetupClass.class);

    private final RouteDefinitionLocator locator;

    public ApiNetworkSetupClass(RouteDefinitionLocator locator) {
        this.locator = locator;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        try {
            // Print all active routes
            log.info("=== Active Gateway Routes ===");
            for (RouteDefinition def : locator.getRouteDefinitions().collectList().block()) {
                log.info("Route ID: {} -> {}", def.getId(), def.getUri());
                def.getPredicates().forEach(p -> log.info("   Predicate: {}", p));
                def.getFilters().forEach(f -> log.info("   Filter: {}", f));
            }

            // Print reachable IP addresses
            log.info("=== Reachable Gateway Addresses ===");
            for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) continue;
                var addrs = Collections.list(ni.getInetAddresses());
                for (var addr : addrs) {
                    if (addr.isAnyLocalAddress() || addr.isLoopbackAddress() || addr.isLinkLocalAddress()) continue;
                    log.info("Gateway reachable at: http://{}:{}/", addr.getHostAddress(), 8080);
                }
            }
        } catch (Exception e) {
            log.warn("Could not enumerate startup info", e);
        }
    }
}
