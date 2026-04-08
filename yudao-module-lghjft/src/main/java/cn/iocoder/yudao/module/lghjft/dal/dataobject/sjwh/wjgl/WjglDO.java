package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.wjgl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("file")
@Data
public class WjglDO {
    @TableId
    @TableField("fileid")
    private Long fileid;
    private String deptId;
    @TableField("filename")
    private String filename;
    @TableField("filestatus")
    private String filestatus;
    @TableField("fileurl")
    private String fileurl;
    private String bz;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}
