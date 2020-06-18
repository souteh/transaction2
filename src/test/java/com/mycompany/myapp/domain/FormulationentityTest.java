package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class FormulationentityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Formulationentity.class);
        Formulationentity formulationentity1 = new Formulationentity();
        formulationentity1.setId(1L);
        Formulationentity formulationentity2 = new Formulationentity();
        formulationentity2.setId(formulationentity1.getId());
        assertThat(formulationentity1).isEqualTo(formulationentity2);
        formulationentity2.setId(2L);
        assertThat(formulationentity1).isNotEqualTo(formulationentity2);
        formulationentity1.setId(null);
        assertThat(formulationentity1).isNotEqualTo(formulationentity2);
    }
}
