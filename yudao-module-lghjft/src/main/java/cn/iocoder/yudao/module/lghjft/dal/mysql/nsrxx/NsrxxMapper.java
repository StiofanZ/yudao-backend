package cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.vo.NsrxxQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 纳税人信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface NsrxxMapper extends BaseMapperX<NsrxxDO> {

    /**
     * 根据查询参数获取纳税人信息列表
     * <p>
     * 替代原有的 selectListByKeyword, selectListByShxydm, selectListByDjxhs 等方法
     *
     * @param queryParam 查询参数对象
     * @return 纳税人信息列表
     */
    default List<NsrxxDO> getNsrxxList(NsrxxQueryParam queryParam) {
        return selectList(new LambdaQueryWrapperX<NsrxxDO>()
                // 仅查询有效状态 (原逻辑 lt "07")
                .ltIfPresent(NsrxxDO::getNsrztDm, queryParam.isOnlyValid() ? "07" : null)
                // 登记序号批量查询
                .inIfPresent(NsrxxDO::getDjxh, queryParam.getDjxhList())
                // 精确匹配纳税人识别号
                .eqIfPresent(NsrxxDO::getNsrsbh, queryParam.getNsrsbh())
                // 精确匹配社会信用代码
                .eqIfPresent(NsrxxDO::getShxydm, queryParam.getShxydm())
                // 模糊匹配纳税人名称
                .likeIfPresent(NsrxxDO::getNsrmc, queryParam.getNsrmc())
                // 关键字查询 (OR 逻辑) - 放在最后，因为 and() 可能返回父类类型
                .and(queryParam.getKeyword() != null, w -> w
                        .eq(NsrxxDO::getNsrsbh, queryParam.getKeyword())
                        .or()
                        .eq(NsrxxDO::getShxydm, queryParam.getKeyword())
                        .or()
                        .like(NsrxxDO::getNsrmc, queryParam.getKeyword())
                )
        );
    }

    /**
     * 根据登记序号查询单个纳税人信息
     */
    default NsrxxDO getNsrxx(String djxh) {
        return selectOne(NsrxxDO::getDjxh, djxh);
    }


}
