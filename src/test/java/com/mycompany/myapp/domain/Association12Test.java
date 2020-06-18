package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class Association12Test {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Association12.class);
        Association12 association121 = new Association12();
        association121.setId(1L);
        Association12 association122 = new Association12();
        association122.setId(association121.getId());
        assertThat(association121).isEqualTo(association122);
        association122.setId(2L);
        assertThat(association121).isNotEqualTo(association122);
        association121.setId(null);
        assertThat(association121).isNotEqualTo(association122);
    }
}
