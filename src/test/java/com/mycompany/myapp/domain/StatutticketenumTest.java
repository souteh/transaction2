package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class StatutticketenumTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Statutticketenum.class);
        Statutticketenum statutticketenum1 = new Statutticketenum();
        statutticketenum1.setId(1L);
        Statutticketenum statutticketenum2 = new Statutticketenum();
        statutticketenum2.setId(statutticketenum1.getId());
        assertThat(statutticketenum1).isEqualTo(statutticketenum2);
        statutticketenum2.setId(2L);
        assertThat(statutticketenum1).isNotEqualTo(statutticketenum2);
        statutticketenum1.setId(null);
        assertThat(statutticketenum1).isNotEqualTo(statutticketenum2);
    }
}
