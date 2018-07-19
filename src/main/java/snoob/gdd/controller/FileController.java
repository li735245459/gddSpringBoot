package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import snoob.gdd.model.Cover;
import snoob.gdd.model.User;
import snoob.gdd.service.FileService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


@RequestMapping("/file")
@RestController
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 导出用户信息
     *
     * @param response
     * @param user
     * @throws Exception
     */
    @PostMapping("/exportUser")
    public void exportUser(HttpServletResponse response, @RequestBody User user) throws Exception {
        fileService.exportUser(response, user);
    }

    /**
     * 导入用户信息(单)
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/importUser")
    public Object importUser(@RequestPart("file") MultipartFile file) throws Exception {
        return fileService.importUser(file);
    }

    /**
     * 导入封面信息(多)
     *
     * @param files
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/importCover")
    public Object importCover(@RequestPart("files") MultipartFile[] files, @RequestParam("coverTypeName") String coverTypeName) throws Exception {
        Cover item = new Cover();
        item.setCoverTypeName(coverTypeName);
        return fileService.importCover(files, item);
    }
}
