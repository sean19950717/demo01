package com.demo.modules.sys.service.impl;

import com.demo.common.entity.R;
import com.demo.common.utils.CommonUtils;
import com.demo.modules.sys.dao.SmsCaptchaMapper;
import com.demo.modules.sys.entity.SmsCaptcha;
import com.demo.modules.sys.service.SmsCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsCaptchaServiceImpl implements SmsCaptchaService {
    @Autowired
    private SmsCaptchaMapper smsCaptchaMapper;
    @Override
    public R save(SmsCaptcha smsCaptcha) {
        int count=smsCaptchaMapper.insert(smsCaptcha);
        return CommonUtils.msg(count);
    }
}
