package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TypepaiementenumTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Typepaiementenum.class);
        Typepaiementenum typepaiementenum1 = new Typepaiementenum();
        typepaiementenum1.setId(1L);
        Typepaiementenum typepaiementenum2 = new Typepaiementenum();
        typepaiementenum2.setId(typepaiementenum1.getId());
        assertThat(typepaiementenum1).isEqualTo(typepaiementenum2);
        typepaiementenum2.setId(2L);
        assertThat(typepaiementenum1).isNotEqualTo(typepaiementenum2);
        typepaiementenum1.setId(null);
        assertThat(typepaiementenum1).isNotEqualTo(typepaiementenum2);
    }
}
