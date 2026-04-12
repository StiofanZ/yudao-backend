package cn.iocoder.yudao.module.lghjft.service.hjgl.Xwgl;

import cn.iocoder.yudao.framework.test.core.ut.BaseMockitoUnitTest;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxKzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hj.ghhj.GhhjMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.NsrxxKzMapper;
import cn.iocoder.yudao.module.lghjft.service.nsrxx.NsrxxService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class GhHjServiceImplTest extends BaseMockitoUnitTest {

    @InjectMocks
    private GhHjServiceImpl ghHjService;

    @Mock
    private GhhjMapper ghhjMapper;
    @Mock
    private NsrxxService nsrxxService;
    @Mock
    private NsrxxKzMapper nsrxxKzMapper;

    @Test
    void selectGhHjBydjxh_returnsExistingGhHjFirst() {
        String djxh = "10216204000000296846";
        GhHjVO existing = new GhHjVO();
        existing.setDjxh(djxh);
        existing.setNsrmc("已维护户籍");

        when(ghhjMapper.selectGhHjBydjxh(djxh)).thenReturn(existing);

        GhHjVO result = ghHjService.selectGhHjBydjxh(djxh);

        assertNotNull(result);
        assertEquals("已维护户籍", result.getNsrmc());
        verify(ghhjMapper).selectGhHjBydjxh(djxh);
        verifyNoInteractions(nsrxxService, nsrxxKzMapper);
    }

    @Test
    void selectGhHjBydjxh_returnsFallbackFromTaxTablesWhenGhHjMissing() {
        String djxh = "10216204000000296846";
        Date now = new Date();
        NsrxxDO nsrxx = NsrxxDO.builder()
                .djxh(djxh)
                .nsrsbh("91620421MADBKPYF7Y")
                .shxydm("91620421MADBKPYF7Y")
                .nsrmc("靖远赤诚典当服务有限公司")
                .jdxzDm("620421100")
                .zgswjDm("16204210000")
                .zgswskfjDm("16204216100")
                .ssglyDm("16204210013")
                .zzjgDm("1")
                .hyDm("6633")
                .djzclxDm("173")
                .dwlsgxDm("52")
                .nsrztDm("07")
                .djrq(now)
                .gszxrq(now)
                .zcdz("甘肃省白银市靖远县乌兰镇北城区广场南路121号")
                .sjtbSj(now)
                .build();
        NsrxxKzDO kz = NsrxxKzDO.builder()
                .djxh(djxh)
                .cwfzrxm("孟建起")
                .cwfzryddh("17609495989")
                .zcdyzbm("730600")
                .cyrs(new BigDecimal("3"))
                .build();

        when(ghhjMapper.selectGhHjBydjxh(djxh)).thenReturn(null);
        when(nsrxxService.getNsrxx(djxh)).thenReturn(nsrxx);
        when(nsrxxKzMapper.selectById(djxh)).thenReturn(kz);

        GhHjVO result = ghHjService.selectGhHjBydjxh(djxh);

        assertNotNull(result);
        assertEquals(djxh, result.getDjxh());
        assertEquals(nsrxx.getNsrmc(), result.getNsrmc());
        assertEquals(nsrxx.getNsrmc(), result.getNsrjc());
        assertEquals(nsrxx.getNsrsbh(), result.getNsrsbh());
        assertEquals(nsrxx.getShxydm(), result.getShxydm());
        assertEquals(kz.getCwfzrxm(), result.getLxr());
        assertEquals(kz.getCwfzryddh(), result.getLxdh());
        assertEquals(kz.getCyrs(), result.getZgrs());
        assertEquals(kz.getZcdyzbm(), result.getYzbm());
        verify(ghhjMapper).selectGhHjBydjxh(djxh);
        verify(nsrxxService).getNsrxx(djxh);
        verify(nsrxxKzMapper).selectById(djxh);
    }

    @Test
    void selectGhHjBydjxh_returnsNullWhenNoFallbackSourceExists() {
        String djxh = "missing";

        when(ghhjMapper.selectGhHjBydjxh(djxh)).thenReturn(null);
        when(nsrxxService.getNsrxx(djxh)).thenReturn(null);

        GhHjVO result = ghHjService.selectGhHjBydjxh(djxh);

        assertNull(result);
        verify(ghhjMapper).selectGhHjBydjxh(djxh);
        verify(nsrxxService).getNsrxx(djxh);
    }
}
