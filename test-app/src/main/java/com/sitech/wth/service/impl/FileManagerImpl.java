package com.sitech.wth.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.jcraft.jsch.ChannelSftp;
import com.sitech.wth.service.inter.IFileManager;
import com.sitech.wth.util.FtpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author: wangth_oup
 * @date: 2020-07-28 16:39
 * @description: 文件上传下载
 **/
@RequestMapping("/file/api/manage")
@Service
public class FileManagerImpl implements IFileManager {

    private Logger logger = LogManager.getLogger(FileManagerImpl.class);

    @Resource
    FtpUtil ftpUtil;

//    @PostMapping(value = "/importFile")
//    public String importFile(MultipartFile[] files){
//        try {
//            // 文件名集合
//            StringBuilder fileNames = new StringBuilder();
//            // 获取ftp服务器连接
//            ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel();
//            if(files.length > 0){
//                for(MultipartFile file:files){
//                    if(file.getSize() > 0){
//                        // 拼接UUID文件名    获取文件原本的文件名：file.getOriginalFilename()
//                        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//                        String fileName = UUID.randomUUID().toString().replace("-","") + fileType;
//                        ftpUtil.uploadFile(channel,ftpUtil.getDirectory(),file.getInputStream(),fileName);
//                        logger.info("已上传文件："+ fileName);
//                        fileNames.append(ftpUtil.getDirectory()).append("/").append(fileName).append(",");
//                    }
//                }
//            }
//            ftpUtil.disConnect(channel);
//            fileNames.deleteCharAt(fileNames.length()-1);
//            // 省略存储文件路径到数据库的步骤
//            return "已上传完成的文件集合："+fileNames;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "上传失败";
//        }
//    }

    @Override
    @PostMapping(value = "/downloadFile")
    public String downloadFile(@RequestParam String filePath , HttpServletResponse response) {
        try {
            if(StringUtils.isEmpty(filePath)){
                logger.info("下载的文件路径为空："+filePath);
                throw new Exception();
            }
            ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel();
            //1.
            ftpUtil.download(channel,filePath,response);

            //2.
//            String directory = filePath.substring(0,filePath.lastIndexOf("/"));
//            String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
//            String saveFile = "E:"+ File.separator;
//            ftpUtil.download(directory, fileName, saveFile, channel);

            return "下载成功！";
        }catch (Exception e) {
            e.printStackTrace();
            return "下载失败！";
        }
    }

//    public ResponseEntity downloadMultiFile(String[] filePathArray, String zipFileName) {
//        try{
//            if(filePathArray == null || StringUtils.isEmpty(zipFileName)){
//                logger.info("下载的文件路径或文件名异常："+ Arrays.toString(filePathArray));
//                throw new Exception();
//            }
//            ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel();
//            return ftpUtil.download(channel,filePathArray,zipFileName);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity("文件下载异常", HttpStatus.valueOf("500"));
//        }
//    }
}