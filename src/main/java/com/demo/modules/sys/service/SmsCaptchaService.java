package com.demo.modules.sys.service;

import com.demo.common.entity.R;
import com.demo.modules.sys.entity.SmsCaptcha;
import org.springframework.stereotype.Service;

@Service
public interface SmsCaptchaService {
    R save(SmsCaptcha smsCaptcha);
}
