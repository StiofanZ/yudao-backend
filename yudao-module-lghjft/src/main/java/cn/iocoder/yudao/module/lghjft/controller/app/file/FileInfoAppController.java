package cn.iocoder.yudao.module.lghjft.controller.app.file;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.file.dal.dataobject.dos.FileInfoDO;
import cn.iocoder.yudao.module.file.dal.dataobject.dto.FileInfoDTO;
import cn.iocoder.yudao.module.file.dal.dataobject.vo.FileInfoVO;
import cn.iocoder.yudao.module.file.service.IFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 文件信息Controller
 * 
 * @author shipj
 * @date 2025-05-09
 */
@RestController
@RequestMapping("/lghjft/file")
public class FileInfoAppController
{
    @Autowired
    private IFileInfoService fileInfoService;

    /**
     * 查询文件信息列表
     */
    @PreAuthorize("@ss.hasPermi('lghjft:file:list')")
    @GetMapping("/list")
    public CommonResult<List<FileInfoVO>> list(FileInfoDTO fileInfo)
    {
        List<FileInfoVO> list = fileInfoService.selectFileInfoList(fileInfo);
        return success(list);
    }

    /**
     * 获取文件信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('lghjft:file:query')")
    @GetMapping(value = "/{fileId}")
    public CommonResult<FileInfoDO> getInfo(@PathVariable("fileId") Long fileId)
    {
        return success(fileInfoService.selectFileInfoByFileId(fileId));
    }

    /**
     * 删除文件信息
     */
    @PreAuthorize("@ss.hasPermi('lghjft:file:remove')")
	@DeleteMapping("/{fileIds}")
    public CommonResult<Integer> remove(@PathVariable Long[] fileIds)
    {
        return success(fileInfoService.deleteFileInfoByFileIds(fileIds));
    }
}
