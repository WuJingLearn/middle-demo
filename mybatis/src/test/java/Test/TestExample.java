package Test;

import dataobject.AExample;

/**
 * @author:majin.wj
 */
public class TestExample {


    public static void main(String[] args) {
        AExample aExample = new AExample();

//        aExample.or();
        aExample.createCriteria().andNameEqualTo("zs");
//        aExample.and(aExample.createCriteria()).
//        aExample.or()
    }

}
