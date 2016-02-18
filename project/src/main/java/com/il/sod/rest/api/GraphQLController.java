package com.il.sod.rest.api;

import java.io.InputStream;

import javax.annotation.security.PermitAll;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.il.sod.rest.graphql.TodoSchema;
import com.mongodb.util.JSON;

import graphql.GraphQL;
import io.swagger.annotations.Api;

//@Component
//@Path("/graphql")
public class GraphQLController {

//    @PermitAll
//	@POST
//    public Response executeOperation(InputStream body) {
//    	GraphQL graphql = new GraphQL(TodoSchema.schema);
//    	String requestBody = convertStreamToString(body);
//        return Response.ok(JSON.serialize( graphql.execute(requestBody).getData() ))
//        		.status(200).type(MediaType.APPLICATION_JSON).build();
//    }
//
//    
//    static String convertStreamToString(java.io.InputStream is) {
//        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
//        return s.hasNext() ? s.next() : "";
//    }

}