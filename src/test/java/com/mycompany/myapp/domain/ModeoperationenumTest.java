package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ModeoperationenumTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Modeoperationenum.class);
        Modeoperationenum modeoperationenum1 = new Modeoperationenum();
        modeoperationenum1.setId(1L);
        Modeoperationenum modeoperationenum2 = new Modeoperationenum();
        modeoperationenum2.setId(modeoperationenum1.getId());
        assertThat(modeoperationenum1).isEqualTo(modeoperationenum2);
        modeoperationenum2.setId(2L);
        assertThat(modeoperationenum1).isNotEqualTo(modeoperationenum2);
        modeoperationenum1.setId(null);
        assertThat(modeoperationenum1).isNotEqualTo(modeoperationenum2);
    }
}
