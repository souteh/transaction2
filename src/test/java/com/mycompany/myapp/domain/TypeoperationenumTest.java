package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TypeoperationenumTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Typeoperationenum.class);
        Typeoperationenum typeoperationenum1 = new Typeoperationenum();
        typeoperationenum1.setId(1L);
        Typeoperationenum typeoperationenum2 = new Typeoperationenum();
        typeoperationenum2.setId(typeoperationenum1.getId());
        assertThat(typeoperationenum1).isEqualTo(typeoperationenum2);
        typeoperationenum2.setId(2L);
        assertThat(typeoperationenum1).isNotEqualTo(typeoperationenum2);
        typeoperationenum1.setId(null);
        assertThat(typeoperationenum1).isNotEqualTo(typeoperationenum2);
    }
}
