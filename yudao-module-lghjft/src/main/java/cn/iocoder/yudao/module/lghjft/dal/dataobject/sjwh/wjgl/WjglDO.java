package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.wjgl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("file")
@Data
public class WjglDO {
    @TableId
    private Long fileid;
    private String deptId;
    private String filename;
    private String filestatus;
    private String fileurl;
    private String bz;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}
