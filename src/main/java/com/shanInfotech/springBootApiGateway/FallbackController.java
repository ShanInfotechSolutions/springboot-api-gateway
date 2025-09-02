package com.shanInfotech.springBootApiGateway;

//src/main/java/com/example/gateway/FallbackController.java

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

@RequestMapping(
   value = "/owners",
   method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH },
   produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Map<String, Object>> ownersFallback() {
 return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of(
     "route", "owner-service",
     "message", "Owner service is unavailable. Please try again later."
 ));
}

@RequestMapping(
   value = "/property-properties",
   method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH },
   produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Map<String, Object>> propertiesFallback() {
 return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of(
     "route", "property-service-properties",
     "message", "Property endpoint is unavailable. Please try again later."
 ));
}

@RequestMapping(
   value = "/agents",
   method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH },
   produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Map<String, Object>> agentsFallback() {
 return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of(
     "route", "agents-service",
     "message", "Agents endpoint is unavailable. Please try again later."
 ));
}
@RequestMapping(
		   value = "/addresses",
		   method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH },
		   produces = MediaType.APPLICATION_JSON_VALUE
		)
		public ResponseEntity<Map<String, Object>> addressesFallback() {
		 return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of(
		     "route", "addresses-service",
		     "message", "Address endpoint is unavailable. Please try again later."
		 ));
		}
@RequestMapping(
		   value = "/property-types",
		   method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH },
		   produces = MediaType.APPLICATION_JSON_VALUE
		)
		public ResponseEntity<Map<String, Object>> propertyTypesFallback() {
		 return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of(
		     "route", "property-service-property-types",
		     "message", "Property Types endpoint is unavailable. Please try again later."
		 ));
		}
}

