package cn.iocoder.yudao.module.lghjft.service.jfcl.dqzldssj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.test.core.ut.BaseMockitoUnitTest;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqzldssj.vo.DqzldssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqzldssj.vo.DqzldssjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.JfzcDqdssjVo;
import cn.iocoder.yudao.module.lghjft.service.jfcl.dqdssj.DqdssjService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DqzldssjServiceImplTest extends BaseMockitoUnitTest {

    @InjectMocks
    private DqzldssjServiceImpl service;

    @Mock
    private DqdssjService dqdssjService;

    @Test
    void getDqzldssjPage_delegatesToSharedService() {
        DqzldssjPageReqVO reqVO = new DqzldssjPageReqVO();
        PageResult<JfzcDqdssjVo> expected = new PageResult<>(List.of(new JfzcDqdssjVo()), 1L);
        when(dqdssjService.getDqzldssjPage(reqVO)).thenReturn(expected);

        PageResult<JfzcDqdssjVo> actual = service.getDqzldssjPage(reqVO);

        assertSame(expected, actual);
        verify(dqdssjService).getDqzldssjPage(reqVO);
    }

    @Test
    void updateDqzldssjrk_delegatesToSharedService() {
        DqzldssjSaveReqVO reqVO = new DqzldssjSaveReqVO();
        when(dqdssjService.updateDqdssjrkzl(reqVO)).thenReturn("ok");

        String actual = service.updateDqzldssjrk(reqVO);

        assertEquals("ok", actual);
        verify(dqdssjService).updateDqdssjrkzl(reqVO);
    }
}
