package cn.iocoder.yudao.module.file.dal.mysql;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.file.dal.dataobject.FileInfoDO;
import cn.iocoder.yudao.module.file.dal.dataobject.dto.FileInfoDTO;
import cn.iocoder.yudao.module.file.dal.dataobject.vo.FileInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.jni.FileInfo;

import java.util.List;
import java.util.Map;

/**
 * 文件信息Mapper接口
 * 
 * @author shipj
 * @date 2025-05-09
 */
@Mapper
public interface FileInfoMapper extends BaseMapperX<FileInfoDO>
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
    public int insertFileInfo(FileInfoDO fileInfo);

    /**
     * 修改文件信息
     * 
     * @param fileInfo 文件信息
     * @return 结果
     */
    public int updateFileInfo(FileInfoDO fileInfo);

    /**
     * 删除文件信息
     * 
     * @param params 文件信息主键
     * @return 结果
     */
    public int deleteFileInfoByFileId(Map<String, Object> params);

    /**
     * 批量删除文件信息
     * 
     * @param params 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFileInfoByFileIds(Map<String, Object> params);

    /**
     * 业务id查询文件
     * @param bizId
     * @return
     */
    List<FileInfoVO> selectFileInfoVOByBizId(Long bizId);

    List<FileInfo> selectFileInfoByBizId(Long bizId);

    /**
     * 批量更新
     * @param fileInfoList
     * @return
     */
    int updateBizIdBatchFileInfo(@Param("fileInfoList") List<FileInfoDO> fileInfoList);

    /**
     * 根据业务id删除
     * @param params
     * @return
     */
    int deleteFileInfoByBizId(Map<String, Object> params);

    /**
     * 批量插入
     * @param fileInfoList
     * @return
     */
    int insertBizIdBatchFileInfo(@Param("fileInfoList") List<FileInfoDO> fileInfoList);
}
