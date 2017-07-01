package com.il.sod.rest.dto;

/**
 * Created by cesaregb on 12/5/16.
 */
public class SecurityKeyDto {

  private String token;
  private String app;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getApp() {
    return app;
  }

  public void setApp(String app) {
    this.app = app;
  }
}
