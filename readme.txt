https启用
    1.https启用步骤 application.properties ssl注释打开 ,
    2.WebConfig中EmbeddedServletContainerFactory@Bean注释打开
国际化：
    配置:
     1.cookie设置lang,对所有请求有效
     2.请求参数设置lang，只对当前请求有效,且优先采用

    spring 通过LocaleResolver来设置Locale。
    默认AcceptHeaderLocaleResolver通过accept-language值设置，由于无法修改用户操作系统的区域设置,所以无法更改。

    SessionLocaleResolver 通过用户会话中预设的属性来解析Locale
    CookieLocaleResolver  通过浏览器Cookie中设置的属性来解析Locale

    LocaleChangeInterceptor 通过获取请求中特殊参数来解析Locale(可以特殊处理一些请求url)
