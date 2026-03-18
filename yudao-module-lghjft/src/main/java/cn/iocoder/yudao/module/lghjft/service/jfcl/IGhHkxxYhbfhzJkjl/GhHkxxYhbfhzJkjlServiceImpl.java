//package cn.iocoder.yudao.module.lghjft.service.jfcl.IGhHkxxYhbfhzJkjl;
//import cn.iocoder.yudao.module.file.utils.DateUtils;
//import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hkxxyhbfhzjkjl.GhHkxxYhbfhzJkjl;
//import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.GhHkxxYhbfhzJkjl.GhHkxxYhbfhzJkjlMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * 【请填写功能名称】Service业务层处理
// *
// * @author ruoyi
// * @date 2024-01-27
// */
//@Service
//public class GhHkxxYhbfhzJkjlServiceImpl implements IGhHkxxYhbfhzJkjlService
//{
//    @Autowired
//    private GhHkxxYhbfhzJkjlMapper ghHkxxYhbfhzJkjlMapper;
//
//    /**
//     * 查询【请填写功能名称】
//     *
//     * @param yhbfhzJkjlId 【请填写功能名称】主键
//     * @return 【请填写功能名称】
//     */
//    @Override
//    public GhHkxxYhbfhzJkjl selectGhHkxxYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId)
//    {
//        return ghHkxxYhbfhzJkjlMapper.selectGhHkxxYhbfhzJkjlByYhbfhzJkjlId(yhbfhzJkjlId);
//    }
//
//    /**
//     * 查询【请填写功能名称】列表
//     *
//     * @param ghHkxxYhbfhzJkjl 【请填写功能名称】
//     * @return 【请填写功能名称】
//     */
//    @Override
//    public List<GhHkxxYhbfhzJkjl> selectGhHkxxYhbfhzJkjlList(GhHkxxYhbfhzJkjl ghHkxxYhbfhzJkjl)
//    {
//        return ghHkxxYhbfhzJkjlMapper.selectGhHkxxYhbfhzJkjlList(ghHkxxYhbfhzJkjl);
//    }
//
//    /**
//     * 新增【请填写功能名称】
//     *
//     * @param ghHkxxYhbfhzJkjl 【请填写功能名称】
//     * @return 结果
//     */
//    @Override
//    public int insertGhHkxxYhbfhzJkjl(GhHkxxYhbfhzJkjl ghHkxxYhbfhzJkjl)
//    {
//        ghHkxxYhbfhzJkjl.setCreateTime(DateUtils.getNowDate());
//        return ghHkxxYhbfhzJkjlMapper.insertGhHkxxYhbfhzJkjl(ghHkxxYhbfhzJkjl);
//    }
//
//    /**
//     * 修改【请填写功能名称】
//     *
//     * @param ghHkxxYhbfhzJkjl 【请填写功能名称】
//     * @return 结果
//     */
//    @Override
//    public int updateGhHkxxYhbfhzJkjl(GhHkxxYhbfhzJkjl ghHkxxYhbfhzJkjl)
//    {
//        ghHkxxYhbfhzJkjl.setUpdateTime(DateUtils.getNowDate());
//        return ghHkxxYhbfhzJkjlMapper.updateGhHkxxYhbfhzJkjl(ghHkxxYhbfhzJkjl);
//    }
//
//    /**
//     * 批量删除【请填写功能名称】
//     *
//     * @param yhbfhzJkjlIds 需要删除的【请填写功能名称】主键
//     * @return 结果
//     */
//    @Override
//    public int deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlIds(Long[] yhbfhzJkjlIds)
//    {
//        return ghHkxxYhbfhzJkjlMapper.deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlIds(yhbfhzJkjlIds);
//    }
//
//    /**
//     * 删除【请填写功能名称】信息
//     *
//     * @param yhbfhzJkjlId 【请填写功能名称】主键
//     * @return 结果
//     */
//    @Override
//    public int deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId)
//    {
//        return ghHkxxYhbfhzJkjlMapper.deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlId(yhbfhzJkjlId);
//    }
//}
