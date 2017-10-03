package com.archangel.skarest.domain.util;

import java.util.List;

public class Result<K> {

    public String cursor;
    public int limit;
    public List<K> result;

    public Result(List<K> result, String cursor, int limit) {
        this.result = result;
        this.cursor = cursor;
        this.limit = limit;
    }

    public Result(List<K> result, int limit) {
        this.result = result;
        this.cursor = null;
        this.limit = limit;
    }
}
