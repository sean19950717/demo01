约定

表中至少有一个名为id的主键


spring boot中https设置:
说明：
 spring boot 不支持同时在配置文件application中配置Http和Https connector
 必须使用硬编码方式配置其中一个，官方建议在application配置文件中设置https,硬编码方式设置Http
 参考文档：https://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-servlet-containers.html
    
开启步骤:
   1)生成证书:
     keytool -genkey -alias tomcat  -storetype PKCS12 -keyalg RSA -keysize 2048  -keystore keystore.p12 -validity 3650
   2).配置Https:
    application配置文件中设置
    server:
        port: 8443
        ssl:
          key-store: ${user.home}/keystore.p12
          key-store-password: password
          key-store-type: PKCS12
          key-alias: tomcat
    证书建议放到外部
   3).设置Http connector 并自动转向Https
      @Bean
          public EmbeddedServletContainerFactory servletContainer() {
      
              TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
      
                  @Override
                  protected void postProcessContext(Context context) {
      
                      SecurityConstraint securityConstraint = new SecurityConstraint();
                      securityConstraint.setUserConstraint("CONFIDENTIAL");
                      SecurityCollection collection = new SecurityCollection();
                      collection.addPattern("/*");
                      securityConstraint.addCollection(collection);
                      context.addConstraint(securityConstraint);
                  }
              };
              tomcat.addAdditionalTomcatConnectors(httpConnector());
              return tomcat;
          }
      
          private Connector httpConnector() {
              Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
              connector.setScheme("http");
              connector.setPort(8080);
              connector.setSecure(false);
              connector.setRedirectPort(8443);与服务器运行端口一致
              return connector;
          }        
          
          
          
          
国际化设置：
   默认：Accept-Language 中覆盖locale