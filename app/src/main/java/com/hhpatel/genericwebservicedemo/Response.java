package com.hhpatel.genericwebservicedemo;

/**
 * Created by hhpatel on 18-Feb-18.
 */

public interface Response {
    public <T> void success(T outputType, int resultCode);

    public void error(Object error, int resultCode);


}
