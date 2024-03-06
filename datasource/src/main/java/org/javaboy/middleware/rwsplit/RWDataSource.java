package org.javaboy.middleware.rwsplit;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author:majin.wj
 */
public class RWDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return null;
    }
}
