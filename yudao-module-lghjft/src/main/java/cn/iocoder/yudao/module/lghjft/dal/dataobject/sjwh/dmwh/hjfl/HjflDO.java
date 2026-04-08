package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.hjfl;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 户籍分类 DO
 *
 * @author 李文军
 */
@TableName("dm_hjfl")
@KeySequence("dm_hjfl_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HjflDO {

    /**
     * 工会机构代码
     */
    private String deptId;
    /**
     * 备注
     * <p>
     * 枚举 {@link // sys_hjfl_gh 对应的类}
     */
    private String bz;
    /**
     * 户籍分类代码
     */
    private String hjflDm;
    /**
     * 户籍分类名称
     */
    private String hjflmc;
    /**
     * 顺序号
     */
    private Long sxh;
    /**
     * 户籍分类id
     */
    @TableId
    private Integer hjflid;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String updateBy;


}