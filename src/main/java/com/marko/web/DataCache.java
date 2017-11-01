package com.marko.web;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by msav on 11/1/2017.
 */
public class DataCache {

    private static Map<Integer, RefinementValueObject> refinementMap = new HashMap<>();

    public static void addToCache(RefinementValueObject object) {
        refinementMap.put(object.getProject().getProjectId(), object);
    }

    public static RefinementValueObject getFromCache(int id) {
        return refinementMap.get(id);
    }
}
