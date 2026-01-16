//package cn.iocoder.yudao.module.lghjft.dal.mysql.auth;
//
//import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
//import cn.iocoder.yudao.module.lghjft.dal.dataobject.auth.GhAppSsoDO;
//import org.apache.ibatis.annotations.Mapper;
//
//import java.util.List;
//
//@Mapper
//public interface GhAppSsoMapper extends BaseMapperX<GhAppSsoDO> {
//
//    default GhAppSsoDO selectByAppUserId(String appUserId) {
//        return selectOne(GhAppSsoDO::getAppUserId, appUserId);
//    }
//
//    default GhAppSsoDO selectByUserId(Long userId) {
//        return selectOne(GhAppSsoDO::getUserId, userId);
//    }
//
//    default List<GhAppSsoDO> selectListByMobile(String mobile) {
//        return selectList(GhAppSsoDO::getMobile, mobile);
//    }
//}
