package com.sitech.wth.util;

import com.jcraft.jsch.*;
import com.sitech.wth.service.impl.FileManagerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

/**
 * @author: wangth_oup
 * @date: 2020-07-28 16:44
 * @description:
 **/
@Component
@ConfigurationProperties(prefix = "ftp.server")
public class FtpUtil {
//    private static final Logger LOG = LoggerFactory.getLogger(FtpUtil.class);
    private Logger logger = LogManager.getLogger(FtpUtil.class);

    @Value("${ftp.server.host}")
    private String host;
    @Value("${ftp.server.port}")
    private Integer port;
    @Value("${ftp.server.username}")
    private String username;
    @Value("${ftp.server.password}")
    private String password;
    @Value("${ftp.server.directory}")
    private String filePath;

    public FtpUtil(){

    }

    public Channel getChannel(Session session) {
        Channel channel = null;
        try {
            channel = session.openChannel("sftp");
            channel.connect();
            logger.info("get Channel success!");
        } catch (JSchException e) {
            logger.info("get Channel fail!", e);
        }
        return channel;
    }

    public Channel getChannel(){
        Session session = null;
        Channel channel = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            session.setConfig(sshConfig);
            session.connect();
            logger.info("Session connected!");
            channel = session.openChannel("sftp");
            channel.connect();
            logger.info("get Channel success!");
        } catch (JSchException e) {
            logger.info("get Channel failed!", e);
        }
        return channel;
    }

    public Session getSession(String host, int port, String username, final String password) {
        Session session = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            session.setConfig(sshConfig);
            session.connect();
            logger.info("Session connected!");
        } catch (JSchException e) {
            logger.info("get Channel failed!", e);
        }
        return session;
    }

    /**
     * 创建文件夹
     *
     * @param sftp
     * @param dir
     *            文件夹名称
     */
    public void mkdir(ChannelSftp sftp, String dir) {
        try {
            sftp.mkdir(dir);
            System.out.println("创建文件夹成功！");
        } catch (SftpException e) {
            System.out.println("创建文件夹失败！");
            logger.error("error!" ,e);
        }
    }

    /**
     * @param sftp
     * @param dir
     *            上传目录
     * @param file
     *            上传文件
     * @return
     */
    public String uploadFile(ChannelSftp sftp, String dir, InputStream file, String fileName) {
        String result = "";
        try {
            sftp.cd(dir);
            if (file != null) {
                sftp.put(file, fileName);
                result = "上传成功！";
            } else {
                result = "文件为空！不能上传！";
            }
        } catch (Exception e) {
            logger.info("上传失败！", e);
            result = "上传失败！";
        }
        return result;
    }

    /**
     * 下载文件
     *
     * @param directory
     *            下载目录
     * @param downloadFile
     *            下载的文件
     * @param saveFile
     *            存在本地的路径
     * @param sftp
     */
    public String download(String directory, String downloadFile,
                           String saveFile, ChannelSftp sftp) {
        String result = "";
        try {
            sftp.cd(directory);
            sftp.get(downloadFile, saveFile);
            result = "下载成功！";
        } catch (Exception e) {
            result = "下载失败！";
            logger.info("下载失败！", e);
            ;
        }
        return result;
    }

    /**
     *  下载文件
     * @param filePath
     * @param response
     * @param sftp
     */
    public void download(ChannelSftp sftp, String filePath, HttpServletResponse response) {
        String directory = "";
        String fileName = "";
        if(filePath.contains("/")){
            directory = filePath.substring(0,filePath.lastIndexOf("/"));
            fileName = filePath.substring(filePath.lastIndexOf("/")+1);
        }
        String savePath = "E:" + File.separator + "wangttthhh" + File.separator;
//        E:\wangttthhh\xxxx
//        String savePath =this.getClass().getResource("/").getPath();
        OutputStream out = null;
        FileInputStream inputStream = null;
        try {
            sftp.cd(directory);

            File file = new File(savePath+fileName);
            sftp.get(fileName, new FileOutputStream(file)); // 之前错误操作：先缓存到本地，再获取流返回给浏览器，最后删除临时文件

            logger.warn("已进入ftp服务器文件夹："+directory);
//            sftp.get(fileName,savePath);
            logger.warn("下载生成的临时文件："+fileName);
//            File file = new File(fileName);
            inputStream = new FileInputStream(file);
            out = response.getOutputStream();
//            int b = 0;
//            byte[] buffer = new byte[512];
//            while (b != -1){
//                b = inputStream.read(buffer);
//                out.write(buffer,0,b);
//            }
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(b)) != -1){
                out.write(b, 0, len);
            }
            inputStream.close();

            String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
            if("xls".equals(fileType)||"xlsx".equals(fileType)){

            }
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            out.flush();
            out.close();
            if(file.exists()){
                System.gc();	//加上确保文件能删除，不然可能删不掉
                boolean delete = file.delete();
                if(delete){
                    logger.info("文件：" + file.getAbsolutePath() + " 删除成功！");
                }
            } else {
                logger.debug("文件：" + file.getAbsolutePath() + "不存在！");
            }
            disConnect(sftp);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("文件下载出现异常，[{}]", e);
            }
            throw new RuntimeException("文件下载出现异常，[{}]", e);
        } finally {
            closeStream(inputStream,out);
        }
    }

    /**
     * 断掉连接
     */
    public void disConnect(ChannelSftp sftp) {
        try {
            sftp.disconnect();
            sftp.getSession().disconnect();
        } catch (Exception e) {
            logger.error("error!" ,e);
        }
    }
    /**
     * 关闭流
     * @param outputStream
     */
    private void closeStream(InputStream inputStream,OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                logger.error("error!" ,e);
            }
        }
        if(inputStream != null){
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error("error!" ,e);
            }
        }
    }




    /**
     * 删除文件
     *
     * @param directory
     * @param deleteFile
     * @param sftp
     */
    public String delete(String directory, String deleteFile, ChannelSftp sftp) {
        String result = "";
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
            result = "删除成功！";
        } catch (Exception e) {
            result = "删除失败！";
            logger.info("删除失败！", e);
        }
        return result;
    }

    private void closeChannel(Channel channel) {
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
        }
    }

    private void closeSession(Session session) {
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

    public void closeAll(ChannelSftp sftp, Channel channel, Session session) {
        try {
            closeChannel(sftp);
            closeChannel(channel);
            closeSession(session);
        } catch (Exception e) {
            logger.info("closeAll", e);
        }
    }
}
