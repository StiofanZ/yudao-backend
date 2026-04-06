package cn.iocoder.yudao.module.system.dal.mysql.user;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface AdminUserMapper extends BaseMapperX<AdminUserDO> {

    default AdminUserDO selectByUsername(String username) {
        return selectOne(AdminUserDO::getUsername, username);
    }

    default AdminUserDO selectByEmail(String email) {
        return selectOne(AdminUserDO::getEmail, email);
    }

    default AdminUserDO selectByMobile(String mobile) {
        return selectOne(AdminUserDO::getMobile, mobile);
    }

    default PageResult<AdminUserDO> selectPage(UserPageReqVO reqVO, Collection<Long> deptIds, Collection<Long> userIds) {
        LambdaQueryWrapperX<AdminUserDO> wrapper = new LambdaQueryWrapperX<AdminUserDO>()
                .likeIfPresent(AdminUserDO::getUsername, reqVO.getUsername())
                .likeIfPresent(AdminUserDO::getMobile, reqVO.getMobile())
                .eqIfPresent(AdminUserDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(AdminUserDO::getCreateTime, reqVO.getCreateTime())
                .inIfPresent(AdminUserDO::getDeptId, deptIds)
                .inIfPresent(AdminUserDO::getId, userIds)
                .orderByDesc(AdminUserDO::getId);
        // 按部门归属过滤：true=仅内部用户（有部门），false=仅外部用户（无部门）
        if (Boolean.TRUE.equals(reqVO.getHasDeptId())) {
            wrapper.isNotNull(AdminUserDO::getDeptId);
        } else if (Boolean.FALSE.equals(reqVO.getHasDeptId())) {
            wrapper.isNull(AdminUserDO::getDeptId);
        }
        return selectPage(reqVO, wrapper);
    }

    default List<AdminUserDO> selectListByNickname(String nickname) {
        return selectList(new LambdaQueryWrapperX<AdminUserDO>().like(AdminUserDO::getNickname, nickname));
    }

    default List<AdminUserDO> selectListByStatus(Integer status) {
        return selectList(AdminUserDO::getStatus, status);
    }

    default List<AdminUserDO> selectListByDeptIds(Collection<Long> deptIds) {
        return selectList(AdminUserDO::getDeptId, deptIds);
    }

    default AdminUserDO selectByShxydm(String shxydm) {
        return selectOne(AdminUserDO::getShxydm, shxydm);
    }

    /**
     * 统一登录查询：用同一个标识匹配 用户名 / 手机号 / 邮箱 / 社会信用代码
     */
    default AdminUserDO selectByLogin(String dlzh) {
        if (StrUtil.isBlank(dlzh)) {
            return null;
        }
        return selectOne(new LambdaQueryWrapperX<AdminUserDO>()
                .eq(AdminUserDO::getUsername, dlzh)
                .or().eq(AdminUserDO::getMobile, dlzh)
                .or().eq(AdminUserDO::getEmail, dlzh)
                .or().eq(AdminUserDO::getShxydm, dlzh));
    }

}
