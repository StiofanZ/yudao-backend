import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 申请-退费申请 DO
 *
 * @author 李文军
 */
@TableName("gh_wf_sq_tfsq")
@KeySequence("gh_wf_sq_tfsq_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WfSqTfsqDO extends BaseDO {

    /**
     * 退费申请ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 申请退费类型代码
     */
    private Integer sqtflxDm;
    /**
     * 流程实例的编号
     */
    private String processInstanceId;
    /**
     * 审批结果
     */
    private Integer status;

}
