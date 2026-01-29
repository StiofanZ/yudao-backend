package cn.iocoder.yudao.module.file.service;


import cn.iocoder.yudao.module.file.dal.dataobject.dos.FileInfoDO;
import cn.iocoder.yudao.module.file.dal.dataobject.dto.FileInfoDTO;
import cn.iocoder.yudao.module.file.dal.dataobject.vo.FileInfoVO;
import org.apache.tomcat.jni.FileInfo;

import java.util.List;

/**
 * 文件信息Service接口
 * 
 * @author shipj
 * @date 2025-05-09
 */
public interface IFileInfoService 
{

    /**
     * 查询文件信息
     * 
     * @param fileId 文件信息主键
     * @return 文件信息
     */
    public FileInfoDO selectFileInfoByFileId(Long fileId);

    public FileInfoVO selectFileInfoVOByFileId(Long fileId);

    /**
     * 查询文件信息列表
     * 
     * @param fileInfo 文件信息
     * @return 文件信息集合
     */
    public List<FileInfoVO> selectFileInfoList(FileInfoDTO fileInfo);

    /**
     * 新增文件信息
     *
     * @param fileInfo 文件信息
     * @return 结果
     */
    public Long insertFileInfo(FileInfoDO fileInfo);

    /**
     * 修改文件信息
     * 
     * @param fileInfo 文件信息
     * @return 结果
     */
    public int updateFileInfo(FileInfoDO fileInfo);

    /**
     * 批量删除文件信息
     * 
     * @param fileIds 需要删除的文件信息主键集合
     * @return 结果
     */
    public int deleteFileInfoByFileIds(Long[] fileIds);

    /**
     * 删除文件信息信息
     * 
     * @param fileId 文件信息主键
     * @return 结果
     */
    public int deleteFileInfoByFileId(Long fileId);

    /**
     * 业务id查询文件
     * @param bizId
     * @return
     */
    List<FileInfoVO> selectFileInfoVOByBizId(Long bizId);

    List<FileInfo> selectFileInfoByBizId(Long bizId);

    /**
     * 批量插入
     * @param fileInfoList
     * @return
     */
    int insertBizIdBatchFileInfo(List<FileInfoDO> fileInfoList);

    /**
     * 批量更新
     * @param fileInfoList
     * @return
     */
    int updateBizIdBatchFileInfo(List<FileInfoDO> fileInfoList);

    /**
     * 根据业务id删除
     * @param bizId
     * @return
     */
    int deleteFileInfoByBizId(Long bizId);

}
