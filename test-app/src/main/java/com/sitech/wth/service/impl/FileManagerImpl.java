package com.sitech.wth.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.jcraft.jsch.ChannelSftp;
import com.sitech.ijcf.message6.dt.in.InDTO;
import com.sitech.wth.dto.PubReq;
import com.sitech.wth.entity.mapdto.UserInfo;
import com.sitech.wth.service.inter.IFileManager;
import com.sitech.wth.service.inter.IUserAccept;
import com.sitech.wth.util.FtpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
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
import java.util.List;
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
    private IUserAccept iUserAccept;

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


    //接口地址：http://localhost:8771/file/api/manage/ExcelDownload
    @RequestMapping("/ExcelDownload")
    @Override
    public void excelDownload(HttpServletResponse response) {
        try {
            String excelName = "UserInfo";
            //表头数据
            String[] header = {"ID", "姓名", "年龄", "性别", "职位", "联系电话"};

            //声明一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();

            //生成一个表格，设置表格名称为"员工表"
            HSSFSheet sheet = workbook.createSheet("员工表");

            //设置表格列宽度为10个字节
            sheet.setDefaultColumnWidth(10);
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //创建第一行表头
            HSSFRow headrow = sheet.createRow(0);

            //遍历添加表头
            for (int i = 0; i < header.length; i++) {
                //创建一个单元格
                HSSFCell cell = headrow.createCell(i);

                //创建一个内容对象
                HSSFRichTextString text = new HSSFRichTextString(header[i]);

                //将内容对象的文字内容写入到单元格中
                cell.setCellValue(text);
                cell.setCellStyle(headerStyle);
            }

            //获取所有的userInfos
            List<UserInfo> userInfos = iUserAccept.qryAllUser();
            if(null == userInfos){
                return;
            }

            //遍历添加表内容
            for (int i = 0; i < userInfos.size(); i++) {
                //创建一行
                HSSFRow row1 = sheet.createRow(i+1);
                //第一列创建并赋值
                if(userInfos.get(i).getUserId() != null){
                    row1.createCell(0).setCellValue(new HSSFRichTextString(userInfos.get(i).getUserId().toString()));
                }
                if(userInfos.get(i).getUserName() != null){
                    row1.createCell(1).setCellValue(new HSSFRichTextString(userInfos.get(i).getUserName()));
                }
                if(userInfos.get(i).getAge() != null){
                    row1.createCell(2).setCellValue(new HSSFRichTextString(userInfos.get(i).getAge().toString()));
                }
                if(userInfos.get(i).getSex() != null){
                    row1.createCell(3).setCellValue(new HSSFRichTextString(userInfos.get(i).getSex()));
                }
                if(userInfos.get(i).getPosition() != null){
                    row1.createCell(4).setCellValue(new HSSFRichTextString(userInfos.get(i).getPosition()));
                }
                if(userInfos.get(i).getPhoneNo() != null){
                    row1.createCell(5).setCellValue(new HSSFRichTextString(userInfos.get(i).getPhoneNo()));
                }
            }

            //准备将Excel的输出流通过response输出到页面下载
            //八进制输出流
            response.setContentType("application/octet-stream");

            //这后面可以设置导出Excel的名称，此例中名为student.xls
            response.setHeader("Content-disposition", "attachment;filename="+excelName+".xls");

            //刷新缓冲
            response.flushBuffer();

            //workbook将Excel写入到response的输出流中，供页面下载
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}