package org.javaboy.utils.lock;

/**
 * Created by xianliang on 15/3/2020.
 *
 * @author xianliang
 * @date 2020/03/15
 */
public class StringByteConverter implements ByteConverter<String> {

    @Override
    public byte[] serialize(String o) {
        return o.getBytes(UTF_8);
    }

    @Override
    public String deserialize(byte[] input) {
        return new String(input, UTF_8);
    }
}
