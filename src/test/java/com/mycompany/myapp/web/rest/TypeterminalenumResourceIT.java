package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Transaction2App;
import com.mycompany.myapp.domain.Typeterminalenum;
import com.mycompany.myapp.repository.TypeterminalenumRepository;
import com.mycompany.myapp.service.TypeterminalenumService;
import com.mycompany.myapp.service.dto.TypeterminalenumCriteria;
import com.mycompany.myapp.service.TypeterminalenumQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TypeterminalenumResource} REST controller.
 */
@SpringBootTest(classes = Transaction2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeterminalenumResourceIT {

    private static final String DEFAULT_T_2020 = "AAAAAAAAAA";
    private static final String UPDATED_T_2020 = "BBBBBBBBBB";

    private static final String DEFAULT_T_2030 = "AAAAAAAAAA";
    private static final String UPDATED_T_2030 = "BBBBBBBBBB";

    private static final String DEFAULT_B_2062 = "AAAAAAAAAA";
    private static final String UPDATED_B_2062 = "BBBBBBBBBB";

    private static final String DEFAULT_PDA = "AAAAAAAAAA";
    private static final String UPDATED_PDA = "BBBBBBBBBB";

    @Autowired
    private TypeterminalenumRepository typeterminalenumRepository;

    @Autowired
    private TypeterminalenumService typeterminalenumService;

    @Autowired
    private TypeterminalenumQueryService typeterminalenumQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeterminalenumMockMvc;

    private Typeterminalenum typeterminalenum;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Typeterminalenum createEntity(EntityManager em) {
        Typeterminalenum typeterminalenum = new Typeterminalenum()
            .t2020(DEFAULT_T_2020)
            .t2030(DEFAULT_T_2030)
            .b2062(DEFAULT_B_2062)
            .pda(DEFAULT_PDA);
        return typeterminalenum;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Typeterminalenum createUpdatedEntity(EntityManager em) {
        Typeterminalenum typeterminalenum = new Typeterminalenum()
            .t2020(UPDATED_T_2020)
            .t2030(UPDATED_T_2030)
            .b2062(UPDATED_B_2062)
            .pda(UPDATED_PDA);
        return typeterminalenum;
    }

    @BeforeEach
    public void initTest() {
        typeterminalenum = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeterminalenum() throws Exception {
        int databaseSizeBeforeCreate = typeterminalenumRepository.findAll().size();
        // Create the Typeterminalenum
        restTypeterminalenumMockMvc.perform(post("/api/typeterminalenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeterminalenum)))
            .andExpect(status().isCreated());

        // Validate the Typeterminalenum in the database
        List<Typeterminalenum> typeterminalenumList = typeterminalenumRepository.findAll();
        assertThat(typeterminalenumList).hasSize(databaseSizeBeforeCreate + 1);
        Typeterminalenum testTypeterminalenum = typeterminalenumList.get(typeterminalenumList.size() - 1);
        assertThat(testTypeterminalenum.gett2020()).isEqualTo(DEFAULT_T_2020);
        assertThat(testTypeterminalenum.gett2030()).isEqualTo(DEFAULT_T_2030);
        assertThat(testTypeterminalenum.getb2062()).isEqualTo(DEFAULT_B_2062);
        assertThat(testTypeterminalenum.getPda()).isEqualTo(DEFAULT_PDA);
    }

    @Test
    @Transactional
    public void createTypeterminalenumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeterminalenumRepository.findAll().size();

        // Create the Typeterminalenum with an existing ID
        typeterminalenum.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeterminalenumMockMvc.perform(post("/api/typeterminalenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeterminalenum)))
            .andExpect(status().isBadRequest());

        // Validate the Typeterminalenum in the database
        List<Typeterminalenum> typeterminalenumList = typeterminalenumRepository.findAll();
        assertThat(typeterminalenumList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeterminalenums() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList
        restTypeterminalenumMockMvc.perform(get("/api/typeterminalenums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeterminalenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].t2020").value(hasItem(DEFAULT_T_2020)))
            .andExpect(jsonPath("$.[*].t2030").value(hasItem(DEFAULT_T_2030)))
            .andExpect(jsonPath("$.[*].b2062").value(hasItem(DEFAULT_B_2062)))
            .andExpect(jsonPath("$.[*].pda").value(hasItem(DEFAULT_PDA)));
    }
    
    @Test
    @Transactional
    public void getTypeterminalenum() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get the typeterminalenum
        restTypeterminalenumMockMvc.perform(get("/api/typeterminalenums/{id}", typeterminalenum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeterminalenum.getId().intValue()))
            .andExpect(jsonPath("$.t2020").value(DEFAULT_T_2020))
            .andExpect(jsonPath("$.t2030").value(DEFAULT_T_2030))
            .andExpect(jsonPath("$.b2062").value(DEFAULT_B_2062))
            .andExpect(jsonPath("$.pda").value(DEFAULT_PDA));
    }


    @Test
    @Transactional
    public void getTypeterminalenumsByIdFiltering() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        Long id = typeterminalenum.getId();

        defaultTypeterminalenumShouldBeFound("id.equals=" + id);
        defaultTypeterminalenumShouldNotBeFound("id.notEquals=" + id);

        defaultTypeterminalenumShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTypeterminalenumShouldNotBeFound("id.greaterThan=" + id);

        defaultTypeterminalenumShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTypeterminalenumShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTypeterminalenumsByt2020IsEqualToSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where t2020 equals to DEFAULT_T_2020
        defaultTypeterminalenumShouldBeFound("t2020.equals=" + DEFAULT_T_2020);

        // Get all the typeterminalenumList where t2020 equals to UPDATED_T_2020
        defaultTypeterminalenumShouldNotBeFound("t2020.equals=" + UPDATED_T_2020);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByt2020IsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where t2020 not equals to DEFAULT_T_2020
        defaultTypeterminalenumShouldNotBeFound("t2020.notEquals=" + DEFAULT_T_2020);

        // Get all the typeterminalenumList where t2020 not equals to UPDATED_T_2020
        defaultTypeterminalenumShouldBeFound("t2020.notEquals=" + UPDATED_T_2020);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByt2020IsInShouldWork() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where t2020 in DEFAULT_T_2020 or UPDATED_T_2020
        defaultTypeterminalenumShouldBeFound("t2020.in=" + DEFAULT_T_2020 + "," + UPDATED_T_2020);

        // Get all the typeterminalenumList where t2020 equals to UPDATED_T_2020
        defaultTypeterminalenumShouldNotBeFound("t2020.in=" + UPDATED_T_2020);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByt2020IsNullOrNotNull() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where t2020 is not null
        defaultTypeterminalenumShouldBeFound("t2020.specified=true");

        // Get all the typeterminalenumList where t2020 is null
        defaultTypeterminalenumShouldNotBeFound("t2020.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeterminalenumsByt2020ContainsSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where t2020 contains DEFAULT_T_2020
        defaultTypeterminalenumShouldBeFound("t2020.contains=" + DEFAULT_T_2020);

        // Get all the typeterminalenumList where t2020 contains UPDATED_T_2020
        defaultTypeterminalenumShouldNotBeFound("t2020.contains=" + UPDATED_T_2020);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByt2020NotContainsSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where t2020 does not contain DEFAULT_T_2020
        defaultTypeterminalenumShouldNotBeFound("t2020.doesNotContain=" + DEFAULT_T_2020);

        // Get all the typeterminalenumList where t2020 does not contain UPDATED_T_2020
        defaultTypeterminalenumShouldBeFound("t2020.doesNotContain=" + UPDATED_T_2020);
    }


    @Test
    @Transactional
    public void getAllTypeterminalenumsByt2030IsEqualToSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where t2030 equals to DEFAULT_T_2030
        defaultTypeterminalenumShouldBeFound("t2030.equals=" + DEFAULT_T_2030);

        // Get all the typeterminalenumList where t2030 equals to UPDATED_T_2030
        defaultTypeterminalenumShouldNotBeFound("t2030.equals=" + UPDATED_T_2030);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByt2030IsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where t2030 not equals to DEFAULT_T_2030
        defaultTypeterminalenumShouldNotBeFound("t2030.notEquals=" + DEFAULT_T_2030);

        // Get all the typeterminalenumList where t2030 not equals to UPDATED_T_2030
        defaultTypeterminalenumShouldBeFound("t2030.notEquals=" + UPDATED_T_2030);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByt2030IsInShouldWork() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where t2030 in DEFAULT_T_2030 or UPDATED_T_2030
        defaultTypeterminalenumShouldBeFound("t2030.in=" + DEFAULT_T_2030 + "," + UPDATED_T_2030);

        // Get all the typeterminalenumList where t2030 equals to UPDATED_T_2030
        defaultTypeterminalenumShouldNotBeFound("t2030.in=" + UPDATED_T_2030);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByt2030IsNullOrNotNull() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where t2030 is not null
        defaultTypeterminalenumShouldBeFound("t2030.specified=true");

        // Get all the typeterminalenumList where t2030 is null
        defaultTypeterminalenumShouldNotBeFound("t2030.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeterminalenumsByt2030ContainsSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where t2030 contains DEFAULT_T_2030
        defaultTypeterminalenumShouldBeFound("t2030.contains=" + DEFAULT_T_2030);

        // Get all the typeterminalenumList where t2030 contains UPDATED_T_2030
        defaultTypeterminalenumShouldNotBeFound("t2030.contains=" + UPDATED_T_2030);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByt2030NotContainsSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where t2030 does not contain DEFAULT_T_2030
        defaultTypeterminalenumShouldNotBeFound("t2030.doesNotContain=" + DEFAULT_T_2030);

        // Get all the typeterminalenumList where t2030 does not contain UPDATED_T_2030
        defaultTypeterminalenumShouldBeFound("t2030.doesNotContain=" + UPDATED_T_2030);
    }


    @Test
    @Transactional
    public void getAllTypeterminalenumsByb2062IsEqualToSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where b2062 equals to DEFAULT_B_2062
        defaultTypeterminalenumShouldBeFound("b2062.equals=" + DEFAULT_B_2062);

        // Get all the typeterminalenumList where b2062 equals to UPDATED_B_2062
        defaultTypeterminalenumShouldNotBeFound("b2062.equals=" + UPDATED_B_2062);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByb2062IsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where b2062 not equals to DEFAULT_B_2062
        defaultTypeterminalenumShouldNotBeFound("b2062.notEquals=" + DEFAULT_B_2062);

        // Get all the typeterminalenumList where b2062 not equals to UPDATED_B_2062
        defaultTypeterminalenumShouldBeFound("b2062.notEquals=" + UPDATED_B_2062);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByb2062IsInShouldWork() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where b2062 in DEFAULT_B_2062 or UPDATED_B_2062
        defaultTypeterminalenumShouldBeFound("b2062.in=" + DEFAULT_B_2062 + "," + UPDATED_B_2062);

        // Get all the typeterminalenumList where b2062 equals to UPDATED_B_2062
        defaultTypeterminalenumShouldNotBeFound("b2062.in=" + UPDATED_B_2062);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByb2062IsNullOrNotNull() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where b2062 is not null
        defaultTypeterminalenumShouldBeFound("b2062.specified=true");

        // Get all the typeterminalenumList where b2062 is null
        defaultTypeterminalenumShouldNotBeFound("b2062.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeterminalenumsByb2062ContainsSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where b2062 contains DEFAULT_B_2062
        defaultTypeterminalenumShouldBeFound("b2062.contains=" + DEFAULT_B_2062);

        // Get all the typeterminalenumList where b2062 contains UPDATED_B_2062
        defaultTypeterminalenumShouldNotBeFound("b2062.contains=" + UPDATED_B_2062);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByb2062NotContainsSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where b2062 does not contain DEFAULT_B_2062
        defaultTypeterminalenumShouldNotBeFound("b2062.doesNotContain=" + DEFAULT_B_2062);

        // Get all the typeterminalenumList where b2062 does not contain UPDATED_B_2062
        defaultTypeterminalenumShouldBeFound("b2062.doesNotContain=" + UPDATED_B_2062);
    }


    @Test
    @Transactional
    public void getAllTypeterminalenumsByPdaIsEqualToSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where pda equals to DEFAULT_PDA
        defaultTypeterminalenumShouldBeFound("pda.equals=" + DEFAULT_PDA);

        // Get all the typeterminalenumList where pda equals to UPDATED_PDA
        defaultTypeterminalenumShouldNotBeFound("pda.equals=" + UPDATED_PDA);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByPdaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where pda not equals to DEFAULT_PDA
        defaultTypeterminalenumShouldNotBeFound("pda.notEquals=" + DEFAULT_PDA);

        // Get all the typeterminalenumList where pda not equals to UPDATED_PDA
        defaultTypeterminalenumShouldBeFound("pda.notEquals=" + UPDATED_PDA);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByPdaIsInShouldWork() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where pda in DEFAULT_PDA or UPDATED_PDA
        defaultTypeterminalenumShouldBeFound("pda.in=" + DEFAULT_PDA + "," + UPDATED_PDA);

        // Get all the typeterminalenumList where pda equals to UPDATED_PDA
        defaultTypeterminalenumShouldNotBeFound("pda.in=" + UPDATED_PDA);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByPdaIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where pda is not null
        defaultTypeterminalenumShouldBeFound("pda.specified=true");

        // Get all the typeterminalenumList where pda is null
        defaultTypeterminalenumShouldNotBeFound("pda.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeterminalenumsByPdaContainsSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where pda contains DEFAULT_PDA
        defaultTypeterminalenumShouldBeFound("pda.contains=" + DEFAULT_PDA);

        // Get all the typeterminalenumList where pda contains UPDATED_PDA
        defaultTypeterminalenumShouldNotBeFound("pda.contains=" + UPDATED_PDA);
    }

    @Test
    @Transactional
    public void getAllTypeterminalenumsByPdaNotContainsSomething() throws Exception {
        // Initialize the database
        typeterminalenumRepository.saveAndFlush(typeterminalenum);

        // Get all the typeterminalenumList where pda does not contain DEFAULT_PDA
        defaultTypeterminalenumShouldNotBeFound("pda.doesNotContain=" + DEFAULT_PDA);

        // Get all the typeterminalenumList where pda does not contain UPDATED_PDA
        defaultTypeterminalenumShouldBeFound("pda.doesNotContain=" + UPDATED_PDA);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTypeterminalenumShouldBeFound(String filter) throws Exception {
        restTypeterminalenumMockMvc.perform(get("/api/typeterminalenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeterminalenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].t2020").value(hasItem(DEFAULT_T_2020)))
            .andExpect(jsonPath("$.[*].t2030").value(hasItem(DEFAULT_T_2030)))
            .andExpect(jsonPath("$.[*].b2062").value(hasItem(DEFAULT_B_2062)))
            .andExpect(jsonPath("$.[*].pda").value(hasItem(DEFAULT_PDA)));

        // Check, that the count call also returns 1
        restTypeterminalenumMockMvc.perform(get("/api/typeterminalenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTypeterminalenumShouldNotBeFound(String filter) throws Exception {
        restTypeterminalenumMockMvc.perform(get("/api/typeterminalenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTypeterminalenumMockMvc.perform(get("/api/typeterminalenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingTypeterminalenum() throws Exception {
        // Get the typeterminalenum
        restTypeterminalenumMockMvc.perform(get("/api/typeterminalenums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeterminalenum() throws Exception {
        // Initialize the database
        typeterminalenumService.save(typeterminalenum);

        int databaseSizeBeforeUpdate = typeterminalenumRepository.findAll().size();

        // Update the typeterminalenum
        Typeterminalenum updatedTypeterminalenum = typeterminalenumRepository.findById(typeterminalenum.getId()).get();
        // Disconnect from session so that the updates on updatedTypeterminalenum are not directly saved in db
        em.detach(updatedTypeterminalenum);
        updatedTypeterminalenum
            .t2020(UPDATED_T_2020)
            .t2030(UPDATED_T_2030)
            .b2062(UPDATED_B_2062)
            .pda(UPDATED_PDA);

        restTypeterminalenumMockMvc.perform(put("/api/typeterminalenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeterminalenum)))
            .andExpect(status().isOk());

        // Validate the Typeterminalenum in the database
        List<Typeterminalenum> typeterminalenumList = typeterminalenumRepository.findAll();
        assertThat(typeterminalenumList).hasSize(databaseSizeBeforeUpdate);
        Typeterminalenum testTypeterminalenum = typeterminalenumList.get(typeterminalenumList.size() - 1);
        assertThat(testTypeterminalenum.gett2020()).isEqualTo(UPDATED_T_2020);
        assertThat(testTypeterminalenum.gett2030()).isEqualTo(UPDATED_T_2030);
        assertThat(testTypeterminalenum.getb2062()).isEqualTo(UPDATED_B_2062);
        assertThat(testTypeterminalenum.getPda()).isEqualTo(UPDATED_PDA);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeterminalenum() throws Exception {
        int databaseSizeBeforeUpdate = typeterminalenumRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeterminalenumMockMvc.perform(put("/api/typeterminalenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeterminalenum)))
            .andExpect(status().isBadRequest());

        // Validate the Typeterminalenum in the database
        List<Typeterminalenum> typeterminalenumList = typeterminalenumRepository.findAll();
        assertThat(typeterminalenumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeterminalenum() throws Exception {
        // Initialize the database
        typeterminalenumService.save(typeterminalenum);

        int databaseSizeBeforeDelete = typeterminalenumRepository.findAll().size();

        // Delete the typeterminalenum
        restTypeterminalenumMockMvc.perform(delete("/api/typeterminalenums/{id}", typeterminalenum.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Typeterminalenum> typeterminalenumList = typeterminalenumRepository.findAll();
        assertThat(typeterminalenumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
