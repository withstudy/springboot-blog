package com.xhb.blog.controller;

import com.xhb.blog.entity.User;
import com.xhb.blog.service.UserService;
import com.xhb.blog.utils.GetFileList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/blog")
@Api(value = "IndexController", description = "首页、文件上传管理")
public class IndexController {
    @Autowired
    private UserService userService;
    @GetMapping("/index")
    @ApiOperation(value="index",notes = "跳转到首页")
    public ModelAndView index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView mav = new ModelAndView();
        User user = (User) session.getAttribute("user");
        if (user.getUsername() == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        //System.out.println(username);
        mav.addObject("user", user);
        mav.setViewName("index");
        return mav;
    }
    @GetMapping("/upload")
    @ApiOperation(value="upload",notes = "跳转到上传界面")
    public ModelAndView upload(HttpServletRequest request,String type){
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        User ouser = (User) session.getAttribute("user");
        mav.addObject("type",type);
        mav.setViewName("upload/index");
        mav.addObject("user",ouser);
        return mav;
    }

    @PostMapping("/upload")
    @ApiOperation(value="upload",notes = "上传文件处理")
    public ModelAndView upload(@RequestParam("file") MultipartFile file,HttpServletRequest request,String type) throws Exception {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        User ouser = (User) session.getAttribute("user");
        if (file.isEmpty()) {
            mav.setViewName("upload/index");
            mav.addObject("user",ouser);
            return mav;
        }
        // 文件存储路径
        String path =null;
        String filename=null;
        if(type.equals("img")){
            path="E:\\file\\img\\";
            // 文件名
            String fileName = new SimpleDateFormat("yyyyMMddHHssmmSS").format(new Date());
            // 文件类型
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            filename=fileName+suffix;
            ouser.setImg(fileName+suffix);
            userService.updateUser(ouser);
            session.setAttribute("user",ouser);
            mav.setViewName("redirect:/blog/index");
        }else{
            path="E:\\file\\files\\";
            filename=file.getOriginalFilename();
            mav.setViewName("redirect:/blog/download");
        }
        //String path = request.getServletContext().getRealPath("/");

        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(path + filename));
        mav.addObject("user",ouser);

        return mav;
    }

    @GetMapping("/download")
    public ModelAndView download(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        User ouser = (User) session.getAttribute("user");

        ArrayList<String> fileNameList = new ArrayList<String>();;
        GetFileList.getAllFileName("E:\\file\\files\\", fileNameList);

        mav.setViewName("download/index");
        mav.addObject("user",ouser);
        mav.addObject("fileNameList",fileNameList);

        return mav;
    }

    @GetMapping("/downloadFile")
    @ApiOperation(value="downloadFile",notes = "下载文件")
    @ResponseBody
    public String downloadFile(HttpServletRequest request, HttpServletResponse response,String fileName) {
        if (fileName != null) {
            //设置文件路径
            File file = new File("E://file/files/"+fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }
}
