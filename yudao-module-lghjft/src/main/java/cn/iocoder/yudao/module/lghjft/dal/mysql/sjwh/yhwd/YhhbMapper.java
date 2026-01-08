package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.yhwd;

import java.util.*;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.yhwd.YhhbDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 银行行别 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface YhhbMapper extends BaseMapperX<YhhbDO> {

    default YhhbDO selectByYhhbDm(String yhhbDm) {
        return selectOne(YhhbDO::getYhhbDm, yhhbDm);
    }

    default int deleteByYhhbDm(String yhhbDm) {
        return delete(YhhbDO::getYhhbDm, yhhbDm);
    }

	default int deleteByYhhbDms(List<String> yhhbDms) {
	    return deleteBatch(YhhbDO::getYhhbDm, yhhbDms);
	}

}