package com.il.sod.rest.dto.helper;

import com.il.sod.db.model.entities.SoftDeleteEntity;
import com.il.sod.rest.dto.db.DeletableDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cesaregb on 9/4/16.
 */
public class ListsHelper {

    public static <T extends DeletableDTO> List<T> getActiveList(List<T> input){
        List<T> r = input.stream().filter(i ->  i.getDeleted() == 0).collect(Collectors.toList());
        return r;
    }

    public static <T extends SoftDeleteEntity> List<T> getActiveEntityList(List<T> input){
        List<T> r = input.stream().filter(i ->  i.getDeleted() == 0).collect(Collectors.toList());
        return r;
    }
}
