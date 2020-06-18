package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class CatonlinecanalenumTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Catonlinecanalenum.class);
        Catonlinecanalenum catonlinecanalenum1 = new Catonlinecanalenum();
        catonlinecanalenum1.setId(1L);
        Catonlinecanalenum catonlinecanalenum2 = new Catonlinecanalenum();
        catonlinecanalenum2.setId(catonlinecanalenum1.getId());
        assertThat(catonlinecanalenum1).isEqualTo(catonlinecanalenum2);
        catonlinecanalenum2.setId(2L);
        assertThat(catonlinecanalenum1).isNotEqualTo(catonlinecanalenum2);
        catonlinecanalenum1.setId(null);
        assertThat(catonlinecanalenum1).isNotEqualTo(catonlinecanalenum2);
    }
}
