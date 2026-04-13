package cn.iocoder.yudao.module.lghjft.service.cxtj.zgjrgh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.test.core.ut.BaseMockitoUnitTest;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo.ZgjrghPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo.ZgjrghResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zgjrgh.ZgjrghDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.zgjrgh.ZgjrghMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ZgjrghServiceImplTest extends BaseMockitoUnitTest {

    @InjectMocks
    private ZgjrghServiceImpl service;

    @Mock
    private ZgjrghMapper zgjrghMapper;

    @Mock
    private DeptFilterHelper deptFilterHelper;

    @Test
    void getZgjrghPage_normalizesLegacyDateFiltersBeforeQuery() {
        ZgjrghPageReqVO reqVO = new ZgjrghPageReqVO();
        reqVO.setDeptId("620000");
        reqVO.setSkssqq("2026-04-01");
        reqVO.setSkssqz("2026-04-30");
        reqVO.setBeginRkrq("2026-03-01");
        reqVO.setEndRkrq("2026-03-31");
        reqVO.setBeginJsrq("2026-02-01");
        reqVO.setEndJsrq("2026-02-28");

        PageResult<ZgjrghDO> expected = new PageResult<>(List.of(), 0L);
        when(deptFilterHelper.filterDeptId("620000")).thenReturn("620001");
        when(zgjrghMapper.selectPage(any(ZgjrghPageReqVO.class))).thenReturn(expected);

        PageResult<ZgjrghDO> actual = service.getZgjrghPage(reqVO);

        assertSame(expected, actual);
        ArgumentCaptor<ZgjrghPageReqVO> reqCaptor = ArgumentCaptor.forClass(ZgjrghPageReqVO.class);
        verify(zgjrghMapper).selectPage(reqCaptor.capture());
        ZgjrghPageReqVO actualReq = reqCaptor.getValue();
        assertEquals("620001", actualReq.getDeptId());
        assertEquals("20260401", actualReq.getSkssqq());
        assertEquals("20260430", actualReq.getSkssqz());
        assertEquals("20260301", actualReq.getBeginRkrq());
        assertEquals("20260331", actualReq.getEndRkrq());
        assertEquals("20260201", actualReq.getBeginJsrq());
        assertEquals("20260228", actualReq.getEndJsrq());
    }

    @Test
    void zgjrghDateLikeFields_useStringTypesToMatchLegacyIntSchema() throws Exception {
        assertEquals(String.class, ZgjrghDO.class.getDeclaredField("nssbrq").getType());
        assertEquals(String.class, ZgjrghDO.class.getDeclaredField("skssqq").getType());
        assertEquals(String.class, ZgjrghDO.class.getDeclaredField("skssqz").getType());
        assertEquals(String.class, ZgjrghDO.class.getDeclaredField("rkrq").getType());
        assertEquals(String.class, ZgjrghDO.class.getDeclaredField("jsrq").getType());
        assertEquals(String.class, ZgjrghDO.class.getDeclaredField("cbjthrq").getType());
        assertEquals(String.class, ZgjrghDO.class.getDeclaredField("createTime").getType());
        assertEquals(String.class, ZgjrghDO.class.getDeclaredField("updateTime").getType());

        assertEquals(String.class, ZgjrghResVO.class.getDeclaredField("nssbrq").getType());
        assertEquals(String.class, ZgjrghResVO.class.getDeclaredField("skssqq").getType());
        assertEquals(String.class, ZgjrghResVO.class.getDeclaredField("skssqz").getType());
        assertEquals(String.class, ZgjrghResVO.class.getDeclaredField("rkrq").getType());
        assertEquals(String.class, ZgjrghResVO.class.getDeclaredField("jsrq").getType());
        assertEquals(String.class, ZgjrghResVO.class.getDeclaredField("cbjthrq").getType());
        assertEquals(String.class, ZgjrghResVO.class.getDeclaredField("createTime").getType());
        assertEquals(String.class, ZgjrghResVO.class.getDeclaredField("updateTime").getType());

        assertEquals(String.class, ZgjrghPageReqVO.class.getDeclaredField("skssqq").getType());
        assertEquals(String.class, ZgjrghPageReqVO.class.getDeclaredField("skssqz").getType());
    }
}
