package cn.iocoder.yudao.module.file.service;

import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.file.dal.dataobject.FileInfoDO;
import cn.iocoder.yudao.module.file.dal.dataobject.dto.FileInfoDTO;
import cn.iocoder.yudao.module.file.dal.dataobject.vo.FileInfoVO;
import cn.iocoder.yudao.module.file.dal.mysql.FileInfoMapper;
import cn.iocoder.yudao.module.file.utils.DateUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件信息Service业务层处理
 * 
 * @author shipj
 * @date 2025-05-09
 */
@Slf4j
@Service
public class FileInfoServiceImpl implements IFileInfoService
{
    @Resource
    private FileInfoMapper fileInfoMapper;

    @Value("${project.profile}")
    private String profile;

    /**
     * 查询文件信息
     * 
     * @param fileId 文件信息主键
     * @return 文件信息
     */
    @Override
    public FileInfoVO selectFileInfoVOByFileId(Long fileId)
    {
        return fileInfoMapper.selectFileInfoVOByFileId(fileId);
    }

    @Override
    public FileInfoDO selectFileInfoByFileId(Long fileId)
    {
        return fileInfoMapper.selectFileInfoByFileId(fileId);
    }

    /**
     * 查询文件信息列表
     * 
     * @param fileInfo 文件信息
     * @return 文件信息
     */
    @Override
    public List<FileInfoVO> selectFileInfoList(FileInfoDTO fileInfo)
    {
        return fileInfoMapper.selectFileInfoList(fileInfo);
    }

    /**
     * 新增文件信息
     *
     * @param fileInfo 文件信息
     * @return 结果
     */
    @Override
    public Long insertFileInfo(FileInfoDO fileInfo)
    {
        fileInfo.setCreateBy(SecurityFrameworkUtils.getLoginUserId());
        fileInfo.setCreateTime(DateUtils.getNowDate());
        int result = fileInfoMapper.insertFileInfo(fileInfo);
        if(result > 0){
           return fileInfo.getFileId();
        }else{
            return 0L;
        }
    }

    /**
     * 修改文件信息
     * 
     * @param fileInfo 文件信息
     * @return 结果
     */
    @Override
    public int updateFileInfo(FileInfoDO fileInfo)
    {
        fileInfo.setUpdateBy(SecurityFrameworkUtils.getLoginUserId());
        fileInfo.setUpdateTime(DateUtils.getNowDate());
        return fileInfoMapper.updateFileInfo(fileInfo);
    }

    /**
     * 批量删除文件信息
     * 
     * @param fileIds 需要删除的文件信息主键
     * @return 结果
     */
    @Override
    public int deleteFileInfoByFileIds(Long[] fileIds)
    {
        Map<String, Object> params = new HashMap<>(3);
        params.put("fileIds", fileIds);
        params.put("updateTime", DateUtils.getNowDate());
        params.put("updateBy", SecurityFrameworkUtils.getLoginUserId());
        return fileInfoMapper.deleteFileInfoByFileIds(params);
    }

    /**
     * 删除文件信息信息
     * 
     * @param fileId 文件信息主键
     * @return 结果
     */
    @Override
    public int deleteFileInfoByFileId(Long fileId)
    {
        Map<String, Object> params = new HashMap<>(3);
        params.put("fileId", fileId);
        params.put("updateTime", DateUtils.getNowDate());
        params.put("updateBy", SecurityFrameworkUtils.getLoginUserId());
        return fileInfoMapper.deleteFileInfoByFileId(params);
    }

    /**
     * 业务id查询文件
     * @param bizId
     * @return
     */
    @Override
    public List<FileInfoVO> selectFileInfoVOByBizId(Long bizId) {
        return fileInfoMapper.selectFileInfoVOByBizId(bizId);
    }

    @Override
    public List<FileInfo> selectFileInfoByBizId(Long bizId) {
        return fileInfoMapper.selectFileInfoByBizId(bizId);
    }

    /**
     * 批量插入
     * @param fileInfoList
     * @return
     */
    @Override
    public int insertBizIdBatchFileInfo(List<FileInfoDO> fileInfoList) {
        return fileInfoMapper.insertBizIdBatchFileInfo(fileInfoList);
    }

    /**
     * 批量更新
     * @param fileInfoList
     * @return
     */
    @Override
    public int updateBizIdBatchFileInfo(List<FileInfoDO> fileInfoList) {
        return fileInfoMapper.updateBizIdBatchFileInfo(fileInfoList);
    }

    /**
     * 根据业务id删除
     * @param bizId
     * @return
     */
    @Override
    public int deleteFileInfoByBizId(Long bizId) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("bizId", bizId);
        params.put("updateTime", DateUtils.getNowDate());
        params.put("updateBy", SecurityFrameworkUtils.getLoginUserId());
        return fileInfoMapper.deleteFileInfoByBizId(params);
    }

}
