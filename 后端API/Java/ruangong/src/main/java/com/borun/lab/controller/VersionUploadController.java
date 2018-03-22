package com.borun.lab.controller;

import com.borun.lab.bean.Version;
import com.borun.lab.service.VersionService;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class VersionUploadController {

    private VersionService versionService;

    @Autowired
    public VersionUploadController(VersionService versionService) {
        this.versionService = versionService;
    }

    private static String UPLOAD_LOCATION = "E:/LabApp/";


    @RequestMapping("/add")
    public String file(){
        return "/file";
    }

    @RequestMapping("/del")
    public String delete(@RequestParam("id") Integer id){
        versionService.deleteById(id);
        return "redirect:/";
    }


    @RequestMapping("/")
    public ModelAndView manage(){
        List<Version> list = versionService.queryByExample(null);
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("verList", list);
        return modelAndView;
    }

    /**
     * 文件上传具体方法
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String AppUpdateFileUpload(@RequestParam("file")MultipartFile file, Version model){
        String fileName = null;
        System.out.println(model.getVernum());
        if(!file.isEmpty()){
            try {
            /**
             * 这段代码执行完毕之后，图片上传到了工程的跟路径；
             * 大家自己扩散下思维，如果我们想把图片上传到 d:/files大家是否能实现呢？
             * 等等;
             * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如：
             * 1、文件路径；
             * 2、文件名；
             * 3、文件格式;
             * 4、文件大小的限制;
             */
            fileName = UPLOAD_LOCATION + file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if(!fileName.endsWith("wgt")) // !fileName.endsWith("apk") &&
                return "上传文件格式不正确！";
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
            out.write(file.getBytes());
            out.flush();
            out.close();
            }catch(FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败,"+e.getMessage();
            }catch (IOException e) {
                e.printStackTrace();
                return "上传失败,"+e.getMessage();
            }
            model.setFileurl(fileName);
            versionService.saveOrUpdate(model);
            return "redirect:/";
            //return "redirect:/?url="+fileName;
        }else{
            return "上传失败，因为文件是空的.";
        }
    }

    /**
     * 文件下载
     * @param response
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/download")
    @ResponseBody
    public String fileDownload(HttpServletResponse response,@RequestParam("id") Integer id) throws Exception {
        Version model = versionService.findById(id);
        if(model != null){
            String fileName = model.getFileurl().replace(UPLOAD_LOCATION,"");
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(new File(model.getFileurl())));
                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
                return "文件下载成功！";
            } catch (IOException e) {
                //e.printStackTrace();
                return "文件下载失败："+e.getMessage();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            return "获取文件失败！";
        }
    }
}
