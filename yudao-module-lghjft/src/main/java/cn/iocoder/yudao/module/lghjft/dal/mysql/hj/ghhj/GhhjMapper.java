package cn.iocoder.yudao.module.lghjft.dal.mysql.hj.ghhj;

import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhhjMapper {
    /**
     * 根据登记序号获取户籍管理详细信息
     */
    GhHjVO selectGhHjBydjxh(String djxh);
}
