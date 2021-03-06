package com.sankuai.meituan.waimai.opensdk.api;

import com.sankuai.meituan.waimai.opensdk.constants.ParamRequiredEnum;
import com.sankuai.meituan.waimai.opensdk.exception.ApiOpException;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;
import com.sankuai.meituan.waimai.opensdk.util.FileUtil;
import com.sankuai.meituan.waimai.opensdk.vo.SystemParam;

import java.io.File;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangzhidong on 15/10/29.
 */
public class ImageApi extends API {

    /**
     * 图片上传
     * @param systemParam  系统参数
     * @param appPoiCode  门店code
     * @param imgDatas  图片字节流
     * @param imgName  图片名称
     * @return
     */
    public String imageUpload(SystemParam systemParam, String appPoiCode,
                                       byte[] imgDatas, String imgName) throws ApiOpException,
                                                                                ApiSysException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        beforeMethod(null,systemParam, appPoiCode, String.valueOf(imgDatas),imgName);

        //组织应用级参数
        Map<String,String> applicationParamsMap = new HashMap<String, String>();
        applicationParamsMap.put("app_poi_code", appPoiCode);
        applicationParamsMap.put("img_name", imgName);
        beforeMethod(systemParam, applicationParamsMap, ParamRequiredEnum.ImageUpload);

        return requestApi(methodName, systemParam, applicationParamsMap, imgDatas, imgName);
    }

    /**
     * 图片上传
     * @param systemParam  系统参数
     * @param appPoiCode  门店code
     * @param imgFile  图片文件
     * @param imgName  图片名称
     * @return
     */
    public String imageUpload(SystemParam systemParam, String appPoiCode,
                                     File imgFile, String imgName) throws ApiOpException,
                                                                              ApiSysException, Exception {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        beforeMethod(null,systemParam, appPoiCode, imgFile,imgName);

        byte[] imgDatas;
        try {
            imgDatas = FileUtil.getBytes(imgFile);
            if(imgDatas == null){
                throw new ApiOpException("文件读取错误");
            }
        } catch (Exception e) {
            throw new ApiOpException("文件读取错误");
        }

        //组织应用级参数
        Map<String,String> applicationParamsMap = new HashMap<String, String>();
        applicationParamsMap.put("app_poi_code", appPoiCode);
        applicationParamsMap.put("img_name", imgName);
        beforeMethod(systemParam, applicationParamsMap, ParamRequiredEnum.ImageUpload);

        return requestApi(methodName, systemParam, applicationParamsMap, imgDatas, imgName);
    }
}
