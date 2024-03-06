package org.javaboy.mybatis.source.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;

/**
 * MapperRegistry维护了所有的mapper接口工厂对象。
 * 所有的mapper接口，都会创建一个MapperProxyFactory工厂，然后放入MapperRegistry中维护。
 *
 *
 * MapperRegistry,MapperProxyFactory,MapperProxy; 代理体系
 * MapperMethod,
 * SqlCommand: name记录了sql的唯一标识，
 * 在SqlCommand中，通过接口的名称+方法的名称做为唯一的标识，从Configuration对象中找MappedStatement
 *
 */
public class MapperRegistryTest {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        MapperRegistry registry = new MapperRegistry(configuration);
    }
}
