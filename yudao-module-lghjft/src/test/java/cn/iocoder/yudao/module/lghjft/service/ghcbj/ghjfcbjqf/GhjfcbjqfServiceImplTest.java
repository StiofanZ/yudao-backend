package cn.iocoder.yudao.module.lghjft.service.ghcbj.ghjfcbjqf;

import cn.iocoder.yudao.framework.test.core.ut.BaseMockitoUnitTest;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo.GhjfcbjqfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.ghjfcbjqf.GhjfcbjqfDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.ghjfcbjqf.GhjfcbjtsjfDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.ghjfcbjqf.GhjfcbjqfMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GhjfcbjqfServiceImplTest extends BaseMockitoUnitTest {

    @InjectMocks
    private GhjfcbjqfServiceImpl service;

    @Mock
    private GhjfcbjqfMapper mapper;

    @Test
    void getGhjfcbjqf_returnsDetailWithChildRows() {
        GhjfcbjqfDO detail = new GhjfcbjqfDO();
        detail.setGhjfId(6030270L);
        detail.setCbjqfzt("A");
        detail.setGhJfCbjtsjfList(List.of(
                GhjfcbjtsjfDO.builder().ghjfId(6030270L).tsjfbj("A").build()
        ));
        when(mapper.selectDetailById(6030270L)).thenReturn(detail);

        GhjfcbjqfDO result = service.getGhjfcbjqf(6030270L);

        assertNotNull(result);
        assertEquals(1, result.getGhJfCbjtsjfList().size());
        assertEquals("A", result.getGhJfCbjtsjfList().get(0).getTsjfbj());
        verify(mapper).selectDetailById(6030270L);
    }

    @Test
    void updateGhjfcbjqf_replacesChildRows() {
        GhjfcbjqfDO existing = new GhjfcbjqfDO();
        existing.setGhjfId(6030270L);
        when(mapper.selectById(6030270L)).thenReturn(existing);

        GhjfcbjqfSaveReqVO reqVO = new GhjfcbjqfSaveReqVO();
        reqVO.setGhjfId(6030270L);
        reqVO.setCbjqfzt("F");
        reqVO.setGhJfCbjtsjfList(List.of(
                new GhjfcbjqfSaveReqVO.GhJfCbjtsjfItem()
        ));

        service.updateGhjfcbjqf(reqVO);

        verify(mapper).deleteCbjtsjfByGhjfId(6030270L);
        ArgumentCaptor<List<GhjfcbjtsjfDO>> captor = ArgumentCaptor.forClass(List.class);
        verify(mapper).batchInsertCbjtsjf(captor.capture());
        assertEquals(6030270L, captor.getValue().get(0).getGhjfId());
        verify(mapper).updateById(any(GhjfcbjqfDO.class));
    }
}
