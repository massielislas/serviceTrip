package edu.byu.hbll.serviceTrip;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSProvider implements ContainerResponseFilter {

  public CORSProvider() {}

  @Override
  public void filter(ContainerRequestContext req, ContainerResponseContext res) throws IOException {
    res.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
    res.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
    res.getHeaders()
        .putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, OPTIONS");
    res.getHeaders()
        .putSingle("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");
  }
}
