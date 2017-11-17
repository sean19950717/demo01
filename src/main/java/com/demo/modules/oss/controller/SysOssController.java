package com.demo.modules.oss.controller;

import com.demo.common.entity.Page;
import com.demo.common.entity.R;
import com.demo.common.utils.ConfigConstant;
import com.demo.common.utils.Constant;
import com.demo.common.validator.ValidatorUtils;
import com.demo.common.validator.group.AliyunGroup;
import com.demo.common.validator.group.QcloudGroup;
import com.demo.common.validator.group.QiniuGroup;
import com.demo.modules.oss.cloud.CloudStorageConfig;
import com.demo.modules.oss.cloud.OSSFactory;
import com.demo.modules.oss.entity.SysOssEntity;
import com.demo.modules.oss.service.SysOssService;
import com.demo.modules.sys.service.SysConfigService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传
 * 
 * @author Centling Technologies
 * @email xxx@demo.com
 * @date 2017-03-25 12:13:26
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
	@Autowired
	private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	//@RequiresPermissions("sys:oss:all")
	public Page<SysOssEntity> list(@RequestBody Map<String, Object> params){
		//查询列表数据
		Page page=sysOssService.listForPage(params);
		return page;
	}


    /**
     * 云存储配置信息
     */
    @RequestMapping("/config")
    //@RequiresPermissions("sys:oss:all")
    public R config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }


	/**
	 * 保存云存储配置信息
	 */
	@RequestMapping("/saveConfig")
	//@RequiresPermissions("sys:oss:all")
	public R saveConfig(@RequestBody CloudStorageConfig config){
		//校验类型
		ValidatorUtils.validateEntity(config);

		if(config.getType() == Constant.CloudService.QINIU.getValue()){
			//校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
			//校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
			//校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}

        sysConfigService.updateValueByKey(KEY, new Gson().toJson(config));

		return R.ok();
	}
	

	/**
	 * 上传文件
	 */
	@RequestMapping("/upload")
	//@RequiresPermissions("sys:oss:all")
	public R upload(@RequestBody MultipartFile filename) throws Exception {
		/*if (filename.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}*/
		String uuid= UUID.randomUUID().toString().replaceAll("-","");
		//上传文件
		String suffix = uuid+filename.getOriginalFilename().substring(filename.getOriginalFilename().lastIndexOf("."));
		String url = OSSFactory.build().upload(filename.getBytes(),suffix);

		//保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(url);
		ossEntity.setCreateDate(new Date());
		sysOssService.saveFileUpload(ossEntity);

		return  R.ok().put("url", url);
	}
	/*
	视频下载
	 */
	@RequestMapping("/download")
	public void download(@RequestParam String urllink){
        /*OssEneity config=ossConfigService.select();
        OSSClient ossClient=new OSSClient(config.getAliyunEndPoint(),config.getAliyunAccessKeyId(),
                config.getAliyunAccessKeySecret());
        */
		try{
			URL url=new URL(urllink);
			InputStream inputStream=url.openStream();
			File file=new File(urllink);
			OutputStream outputStream=new FileOutputStream(file);
			int byteCount=0;
			byte[] bytes=new byte[1024*1024];
			while((byteCount = inputStream.read(bytes)) != -1){
				outputStream.write(bytes,0,byteCount);
			}
			inputStream.close();
			outputStream.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}


	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	//@RequiresPermissions("sys:oss:all")
	public R delete(@RequestBody int[] ids){
		sysOssService.deleteById(ids);
		return R.ok();
	}

}
