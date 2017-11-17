package com.demo.common.utils.spring;/**
 * Created by lin on 17-11-13.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author 林建伟
 *         17-11-13 上午8:51
 */
@Component
public class Messages {
    private static MessageSource messageSource;

    public static String get(String key){
       Locale locale = LocaleContextHolder.getLocale();
       return messageSource.getMessage(key,null,locale);
    }

    @Autowired
    private void setMessageSource(MessageSource messageSource){
        Messages.messageSource = messageSource;
    }
}
