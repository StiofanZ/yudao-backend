package cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.ghjfcbjqf;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("gh_jf_cbjtsjf")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhjfcbjtsjfDO {

    @TableId(type = IdType.AUTO)
    private Long ghjfId;
    private String spuuid;
    private String tsjfbj;
    private String tsjfsm;
    private String clsj;
    private String tsjfwj;
    private String tsjftp;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
}
