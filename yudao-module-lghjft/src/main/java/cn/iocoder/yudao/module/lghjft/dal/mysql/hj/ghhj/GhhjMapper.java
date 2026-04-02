package cn.iocoder.yudao.module.lghjft.dal.mysql.hj.ghhj;

import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjNsrxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GhhjMapper {
    /**
     * 根据登记序号获取户籍管理详细信息
     */
    GhHjVO selectGhHjBydjxh(String djxh);

    /**
     * 户籍管理分页查询
     */
    IPage<GhHjVO> selectGhHjPage(Page<GhHjVO> page, @Param("query") GhHjPageReqVO query);

    /**
     * 户籍管理列表查询（导出用）
     */
    List<GhHjVO> selectGhHjList(@Param("query") GhHjPageReqVO query);

    /**
     * 新增户籍
     */
    int insertGhHj(@Param("vo") GhHjSaveReqVO vo);

    /**
     * 批量删除户籍
     */
    int deleteGhHjBydjxhs(@Param("djxhs") String[] djxhs);

    /**
     * 批量复审 - 更新基层工会银行信息
     */
    int fushenPlBydjxhs(@Param("djxhs") List<String> djxhs);

    /**
     * 户籍调拨 - 批量更新部门和行业工会标志，清空银行信息
     */
    int updateAllocationGhHj(@Param("deptId") String deptId,
                             @Param("hyghbz") String hyghbz,
                             @Param("djxhs") List<String> djxhs);

    /**
     * 根据纳税人关键字查询户籍列表（含部门名称）
     * 用于判断是否已被其他机构维护
     */
    List<GhHjNsrxxResVO> getListByDjNsrxxDto(@Param("searchNsrKey") String searchNsrKey,
                                             @Param("djxh") String djxh,
                                             @Param("shxydm") String shxydm);
}
