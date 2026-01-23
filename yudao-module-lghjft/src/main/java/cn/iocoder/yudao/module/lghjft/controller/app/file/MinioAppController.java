package cn.iocoder.yudao.module.lghjft.controller.app.file;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.file.dal.dataobject.vo.FileInfoInputVO;
import cn.iocoder.yudao.module.file.dal.dataobject.vo.FileInfoVO;
import cn.iocoder.yudao.module.file.service.IMinioStorageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


/**
 * @author shipj
 */
@RestController
@RequestMapping("/common/minio")
public class MinioAppController {

    @Autowired
    private IMinioStorageService minioStorageService;

    @GetMapping("/createBucket")
    public CommonResult<Boolean> createBucket(@RequestParam("bucketName") String bucketName) {
        minioStorageService.createBucket(bucketName);
        return success(Boolean.TRUE);
    }

    /**
     * 文件上传
     * @param fileInfoInputVO
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadFile")
    public CommonResult<FileInfoVO> uploadFile(FileInfoInputVO fileInfoInputVO) {
        return success(minioStorageService.uploadFile(fileInfoInputVO));
    }

    /**
     * 文件上传加水印
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadWatermark")
    public CommonResult<FileInfoVO> uploadWatermark(@RequestParam("file") MultipartFile file) {
        return success(minioStorageService.uploadWatermark(file));
    }

    /**
     * 删除文件
     *
     * @param fileId
     * @return
     */
    @GetMapping("/removeFile")
    public CommonResult<Boolean> removeFile(@RequestParam("fileId") Long fileId) {
        minioStorageService.removeFile(fileId);
        return success(Boolean.TRUE);
    }

    /**
     * 下载文件
     *
     * @param fileId
     * @return
     */
    @PostMapping("/downloadFile")
    public void downloadFile(@RequestParam("fileId") Long fileId, HttpServletResponse response) throws IOException {
        OutputStream outputStream = null;
        // 获取文件对象
        InputStream inputStream = minioStorageService.downloadFile(fileId);
        try {
            byte buf[] = new byte[1024];
            int length = 0;
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileId.toString().getBytes("UTF-8"), "ISO8859-1"));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            outputStream = response.getOutputStream();
            // 输出文件
            while ((length = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, length);
            }
        } catch (Exception ex) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            OutputStream ps = response.getOutputStream();
            ps.write("文件下载失败".getBytes("UTF-8"));
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

}
