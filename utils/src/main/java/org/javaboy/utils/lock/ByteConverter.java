package org.javaboy.utils.lock;

import java.nio.charset.Charset;

/**
 * Created by xianliang on 15/3/2020.
 *
 * @author xianliang
 * @date 2020/03/15
 */
public interface ByteConverter<T> {

    Charset UTF_8 = Charset.forName("UTF-8");

    byte[] serialize(T object);

    T deserialize(byte[] input);

}