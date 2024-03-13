package org.javaboy.utils.lock;

import java.io.*;

/**
 * Created by xianliang on 15/3/2020.
 *
 * @author xianliang
 * @date 2020/03/15
 */
public class DefaultByteConverter implements ByteConverter {

    @Override
    public byte[] serialize(Object o) {
        byte[] rv;

        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);

            os.writeObject(o);
            os.close();
            bos.close();
            rv = bos.toByteArray();

        } catch (IOException e) {
            throw new IllegalArgumentException("Non-serializable object", e);
        }

        return rv;
    }

    @Override
    public Object deserialize(byte[] input) {
        Object rv = null;

        try {
            if (input != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(input);
                ObjectInputStream is = new ObjectInputStream(bis);

                rv = is.readObject();
                is.close();
                bis.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("deserialize failed", e);
        }

        return rv;
    }
}
