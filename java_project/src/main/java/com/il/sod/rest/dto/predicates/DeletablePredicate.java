package com.il.sod.rest.dto.predicates;

import com.il.sod.db.model.entities.SoftDeleteEntity;
import com.il.sod.rest.dto.db.DeletableDTO;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by cesaregb on 11/19/16.
 */
public class DeletablePredicate {

  public static <T extends DeletableDTO> List<T> getActiveList(List<T> input) {
    List<T> r = input.stream().filter(i -> i.getDeleted() == 0).collect(Collectors.toList());
    return r;
  }

  public static <T extends SoftDeleteEntity> List<T> getActiveEntityList(List<T> input) {
    List<T> r = input.stream().filter(i -> i.getDeleted() == 0).collect(Collectors.toList());
    return r;
  }

  public static Predicate<DeletableDTO> isInactive() {
    return p -> p.getDeleted() == 1;
  }

  public static Predicate<DeletableDTO> isActive() {
    return p -> p.getDeleted() == 0;
  }

  public static <T extends DeletableDTO> List<T> filter(List<T> list, Predicate<DeletableDTO> predicate) {
    return list.stream().filter(predicate).collect(Collectors.toList());
  }


}
