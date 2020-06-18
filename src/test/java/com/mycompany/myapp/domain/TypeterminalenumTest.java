package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TypeterminalenumTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Typeterminalenum.class);
        Typeterminalenum typeterminalenum1 = new Typeterminalenum();
        typeterminalenum1.setId(1L);
        Typeterminalenum typeterminalenum2 = new Typeterminalenum();
        typeterminalenum2.setId(typeterminalenum1.getId());
        assertThat(typeterminalenum1).isEqualTo(typeterminalenum2);
        typeterminalenum2.setId(2L);
        assertThat(typeterminalenum1).isNotEqualTo(typeterminalenum2);
        typeterminalenum1.setId(null);
        assertThat(typeterminalenum1).isNotEqualTo(typeterminalenum2);
    }
}
