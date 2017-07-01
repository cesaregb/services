package com.il.sod.rest.dto.db;

import java.util.Set;

public class RouteDTO {
  private int idRoutes;
  private int category;
  private String description;
  private String name;
  private Set<StopDTO> stops;
  private Set<CalendarRouteDTO> calendarRoutes;

  public int getIdRoutes() {
    return idRoutes;
  }

  public void setIdRoutes(int idRoutes) {
    this.idRoutes = idRoutes;
  }

  public int getCategory() {
    return category;
  }

  public void setCategory(int category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<StopDTO> getStops() {
    return stops;
  }

  public void setStops(Set<StopDTO> stops) {
    this.stops = stops;
  }

  public Set<CalendarRouteDTO> getCalendarRoutes() {
    return calendarRoutes;
  }

  public void setCalendarRoutes(Set<CalendarRouteDTO> calendarRoutes) {
    this.calendarRoutes = calendarRoutes;
  }

}