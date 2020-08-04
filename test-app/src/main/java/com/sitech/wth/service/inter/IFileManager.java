package com.sitech.wth.service.inter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: wangth_oup
 * @date: 2020-07-28 16:40
 * @description:
 **/
public interface IFileManager {

    String downloadFile(String filePath , HttpServletResponse response);
}
