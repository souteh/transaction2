package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PdventityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pdventity.class);
        Pdventity pdventity1 = new Pdventity();
        pdventity1.setId(1L);
        Pdventity pdventity2 = new Pdventity();
        pdventity2.setId(pdventity1.getId());
        assertThat(pdventity1).isEqualTo(pdventity2);
        pdventity2.setId(2L);
        assertThat(pdventity1).isNotEqualTo(pdventity2);
        pdventity1.setId(null);
        assertThat(pdventity1).isNotEqualTo(pdventity2);
    }
}
