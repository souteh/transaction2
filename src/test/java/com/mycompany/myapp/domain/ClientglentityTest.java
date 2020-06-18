package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ClientglentityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Clientglentity.class);
        Clientglentity clientglentity1 = new Clientglentity();
        clientglentity1.setId(1L);
        Clientglentity clientglentity2 = new Clientglentity();
        clientglentity2.setId(clientglentity1.getId());
        assertThat(clientglentity1).isEqualTo(clientglentity2);
        clientglentity2.setId(2L);
        assertThat(clientglentity1).isNotEqualTo(clientglentity2);
        clientglentity1.setId(null);
        assertThat(clientglentity1).isNotEqualTo(clientglentity2);
    }
}
