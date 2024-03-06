package com.example.spring.ioc.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tc.rpc")
public class TcRpcProperties {
    
   private String registryType;
   private String registryIp;
   private Integer registryPort;

   public String getRegistryType() {
      return registryType;
   }

   public void setRegistryType(String registryType) {
      this.registryType = registryType;
   }

   public String getRegistryIp() {
      return registryIp;
   }

   public void setRegistryIp(String registryIp) {
      this.registryIp = registryIp;
   }

   public Integer getRegistryPort() {
      return registryPort;
   }

   public void setRegistryPort(Integer registryPort) {
      this.registryPort = registryPort;
   }
}
