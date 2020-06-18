package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Transaction2App;
import com.mycompany.myapp.domain.Pdventity;
import com.mycompany.myapp.repository.PdventityRepository;
import com.mycompany.myapp.service.PdventityService;
import com.mycompany.myapp.service.dto.PdventityCriteria;
import com.mycompany.myapp.service.PdventityQueryService;

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
 * Integration tests for the {@link PdventityResource} REST controller.
 */
@SpringBootTest(classes = Transaction2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class PdventityResourceIT {

    private static final Long DEFAULT_IDPDV = 1L;
    private static final Long UPDATED_IDPDV = 2L;
    private static final Long SMALLER_IDPDV = 1L - 1L;

    private static final String DEFAULT_REFERENCEPDV = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCEPDV = "BBBBBBBBBB";

    @Autowired
    private PdventityRepository pdventityRepository;

    @Autowired
    private PdventityService pdventityService;

    @Autowired
    private PdventityQueryService pdventityQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPdventityMockMvc;

    private Pdventity pdventity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pdventity createEntity(EntityManager em) {
        Pdventity pdventity = new Pdventity()
            .idpdv(DEFAULT_IDPDV)
            .referencepdv(DEFAULT_REFERENCEPDV);
        return pdventity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pdventity createUpdatedEntity(EntityManager em) {
        Pdventity pdventity = new Pdventity()
            .idpdv(UPDATED_IDPDV)
            .referencepdv(UPDATED_REFERENCEPDV);
        return pdventity;
    }

    @BeforeEach
    public void initTest() {
        pdventity = createEntity(em);
    }

    @Test
    @Transactional
    public void createPdventity() throws Exception {
        int databaseSizeBeforeCreate = pdventityRepository.findAll().size();
        // Create the Pdventity
        restPdventityMockMvc.perform(post("/api/pdventities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pdventity)))
            .andExpect(status().isCreated());

        // Validate the Pdventity in the database
        List<Pdventity> pdventityList = pdventityRepository.findAll();
        assertThat(pdventityList).hasSize(databaseSizeBeforeCreate + 1);
        Pdventity testPdventity = pdventityList.get(pdventityList.size() - 1);
        assertThat(testPdventity.getIdpdv()).isEqualTo(DEFAULT_IDPDV);
        assertThat(testPdventity.getReferencepdv()).isEqualTo(DEFAULT_REFERENCEPDV);
    }

    @Test
    @Transactional
    public void createPdventityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pdventityRepository.findAll().size();

        // Create the Pdventity with an existing ID
        pdventity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPdventityMockMvc.perform(post("/api/pdventities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pdventity)))
            .andExpect(status().isBadRequest());

        // Validate the Pdventity in the database
        List<Pdventity> pdventityList = pdventityRepository.findAll();
        assertThat(pdventityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdpdvIsRequired() throws Exception {
        int databaseSizeBeforeTest = pdventityRepository.findAll().size();
        // set the field null
        pdventity.setIdpdv(null);

        // Create the Pdventity, which fails.


        restPdventityMockMvc.perform(post("/api/pdventities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pdventity)))
            .andExpect(status().isBadRequest());

        List<Pdventity> pdventityList = pdventityRepository.findAll();
        assertThat(pdventityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPdventities() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList
        restPdventityMockMvc.perform(get("/api/pdventities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pdventity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idpdv").value(hasItem(DEFAULT_IDPDV.intValue())))
            .andExpect(jsonPath("$.[*].referencepdv").value(hasItem(DEFAULT_REFERENCEPDV)));
    }
    
    @Test
    @Transactional
    public void getPdventity() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get the pdventity
        restPdventityMockMvc.perform(get("/api/pdventities/{id}", pdventity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pdventity.getId().intValue()))
            .andExpect(jsonPath("$.idpdv").value(DEFAULT_IDPDV.intValue()))
            .andExpect(jsonPath("$.referencepdv").value(DEFAULT_REFERENCEPDV));
    }


    @Test
    @Transactional
    public void getPdventitiesByIdFiltering() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        Long id = pdventity.getId();

        defaultPdventityShouldBeFound("id.equals=" + id);
        defaultPdventityShouldNotBeFound("id.notEquals=" + id);

        defaultPdventityShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultPdventityShouldNotBeFound("id.greaterThan=" + id);

        defaultPdventityShouldBeFound("id.lessThanOrEqual=" + id);
        defaultPdventityShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllPdventitiesByIdpdvIsEqualToSomething() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where idpdv equals to DEFAULT_IDPDV
        defaultPdventityShouldBeFound("idpdv.equals=" + DEFAULT_IDPDV);

        // Get all the pdventityList where idpdv equals to UPDATED_IDPDV
        defaultPdventityShouldNotBeFound("idpdv.equals=" + UPDATED_IDPDV);
    }

    @Test
    @Transactional
    public void getAllPdventitiesByIdpdvIsNotEqualToSomething() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where idpdv not equals to DEFAULT_IDPDV
        defaultPdventityShouldNotBeFound("idpdv.notEquals=" + DEFAULT_IDPDV);

        // Get all the pdventityList where idpdv not equals to UPDATED_IDPDV
        defaultPdventityShouldBeFound("idpdv.notEquals=" + UPDATED_IDPDV);
    }

    @Test
    @Transactional
    public void getAllPdventitiesByIdpdvIsInShouldWork() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where idpdv in DEFAULT_IDPDV or UPDATED_IDPDV
        defaultPdventityShouldBeFound("idpdv.in=" + DEFAULT_IDPDV + "," + UPDATED_IDPDV);

        // Get all the pdventityList where idpdv equals to UPDATED_IDPDV
        defaultPdventityShouldNotBeFound("idpdv.in=" + UPDATED_IDPDV);
    }

    @Test
    @Transactional
    public void getAllPdventitiesByIdpdvIsNullOrNotNull() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where idpdv is not null
        defaultPdventityShouldBeFound("idpdv.specified=true");

        // Get all the pdventityList where idpdv is null
        defaultPdventityShouldNotBeFound("idpdv.specified=false");
    }

    @Test
    @Transactional
    public void getAllPdventitiesByIdpdvIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where idpdv is greater than or equal to DEFAULT_IDPDV
        defaultPdventityShouldBeFound("idpdv.greaterThanOrEqual=" + DEFAULT_IDPDV);

        // Get all the pdventityList where idpdv is greater than or equal to UPDATED_IDPDV
        defaultPdventityShouldNotBeFound("idpdv.greaterThanOrEqual=" + UPDATED_IDPDV);
    }

    @Test
    @Transactional
    public void getAllPdventitiesByIdpdvIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where idpdv is less than or equal to DEFAULT_IDPDV
        defaultPdventityShouldBeFound("idpdv.lessThanOrEqual=" + DEFAULT_IDPDV);

        // Get all the pdventityList where idpdv is less than or equal to SMALLER_IDPDV
        defaultPdventityShouldNotBeFound("idpdv.lessThanOrEqual=" + SMALLER_IDPDV);
    }

    @Test
    @Transactional
    public void getAllPdventitiesByIdpdvIsLessThanSomething() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where idpdv is less than DEFAULT_IDPDV
        defaultPdventityShouldNotBeFound("idpdv.lessThan=" + DEFAULT_IDPDV);

        // Get all the pdventityList where idpdv is less than UPDATED_IDPDV
        defaultPdventityShouldBeFound("idpdv.lessThan=" + UPDATED_IDPDV);
    }

    @Test
    @Transactional
    public void getAllPdventitiesByIdpdvIsGreaterThanSomething() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where idpdv is greater than DEFAULT_IDPDV
        defaultPdventityShouldNotBeFound("idpdv.greaterThan=" + DEFAULT_IDPDV);

        // Get all the pdventityList where idpdv is greater than SMALLER_IDPDV
        defaultPdventityShouldBeFound("idpdv.greaterThan=" + SMALLER_IDPDV);
    }


    @Test
    @Transactional
    public void getAllPdventitiesByReferencepdvIsEqualToSomething() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where referencepdv equals to DEFAULT_REFERENCEPDV
        defaultPdventityShouldBeFound("referencepdv.equals=" + DEFAULT_REFERENCEPDV);

        // Get all the pdventityList where referencepdv equals to UPDATED_REFERENCEPDV
        defaultPdventityShouldNotBeFound("referencepdv.equals=" + UPDATED_REFERENCEPDV);
    }

    @Test
    @Transactional
    public void getAllPdventitiesByReferencepdvIsNotEqualToSomething() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where referencepdv not equals to DEFAULT_REFERENCEPDV
        defaultPdventityShouldNotBeFound("referencepdv.notEquals=" + DEFAULT_REFERENCEPDV);

        // Get all the pdventityList where referencepdv not equals to UPDATED_REFERENCEPDV
        defaultPdventityShouldBeFound("referencepdv.notEquals=" + UPDATED_REFERENCEPDV);
    }

    @Test
    @Transactional
    public void getAllPdventitiesByReferencepdvIsInShouldWork() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where referencepdv in DEFAULT_REFERENCEPDV or UPDATED_REFERENCEPDV
        defaultPdventityShouldBeFound("referencepdv.in=" + DEFAULT_REFERENCEPDV + "," + UPDATED_REFERENCEPDV);

        // Get all the pdventityList where referencepdv equals to UPDATED_REFERENCEPDV
        defaultPdventityShouldNotBeFound("referencepdv.in=" + UPDATED_REFERENCEPDV);
    }

    @Test
    @Transactional
    public void getAllPdventitiesByReferencepdvIsNullOrNotNull() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where referencepdv is not null
        defaultPdventityShouldBeFound("referencepdv.specified=true");

        // Get all the pdventityList where referencepdv is null
        defaultPdventityShouldNotBeFound("referencepdv.specified=false");
    }
                @Test
    @Transactional
    public void getAllPdventitiesByReferencepdvContainsSomething() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where referencepdv contains DEFAULT_REFERENCEPDV
        defaultPdventityShouldBeFound("referencepdv.contains=" + DEFAULT_REFERENCEPDV);

        // Get all the pdventityList where referencepdv contains UPDATED_REFERENCEPDV
        defaultPdventityShouldNotBeFound("referencepdv.contains=" + UPDATED_REFERENCEPDV);
    }

    @Test
    @Transactional
    public void getAllPdventitiesByReferencepdvNotContainsSomething() throws Exception {
        // Initialize the database
        pdventityRepository.saveAndFlush(pdventity);

        // Get all the pdventityList where referencepdv does not contain DEFAULT_REFERENCEPDV
        defaultPdventityShouldNotBeFound("referencepdv.doesNotContain=" + DEFAULT_REFERENCEPDV);

        // Get all the pdventityList where referencepdv does not contain UPDATED_REFERENCEPDV
        defaultPdventityShouldBeFound("referencepdv.doesNotContain=" + UPDATED_REFERENCEPDV);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPdventityShouldBeFound(String filter) throws Exception {
        restPdventityMockMvc.perform(get("/api/pdventities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pdventity.getId().intValue())))
            .andExpect(jsonPath("$.[*].idpdv").value(hasItem(DEFAULT_IDPDV.intValue())))
            .andExpect(jsonPath("$.[*].referencepdv").value(hasItem(DEFAULT_REFERENCEPDV)));

        // Check, that the count call also returns 1
        restPdventityMockMvc.perform(get("/api/pdventities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPdventityShouldNotBeFound(String filter) throws Exception {
        restPdventityMockMvc.perform(get("/api/pdventities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPdventityMockMvc.perform(get("/api/pdventities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingPdventity() throws Exception {
        // Get the pdventity
        restPdventityMockMvc.perform(get("/api/pdventities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePdventity() throws Exception {
        // Initialize the database
        pdventityService.save(pdventity);

        int databaseSizeBeforeUpdate = pdventityRepository.findAll().size();

        // Update the pdventity
        Pdventity updatedPdventity = pdventityRepository.findById(pdventity.getId()).get();
        // Disconnect from session so that the updates on updatedPdventity are not directly saved in db
        em.detach(updatedPdventity);
        updatedPdventity
            .idpdv(UPDATED_IDPDV)
            .referencepdv(UPDATED_REFERENCEPDV);

        restPdventityMockMvc.perform(put("/api/pdventities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPdventity)))
            .andExpect(status().isOk());

        // Validate the Pdventity in the database
        List<Pdventity> pdventityList = pdventityRepository.findAll();
        assertThat(pdventityList).hasSize(databaseSizeBeforeUpdate);
        Pdventity testPdventity = pdventityList.get(pdventityList.size() - 1);
        assertThat(testPdventity.getIdpdv()).isEqualTo(UPDATED_IDPDV);
        assertThat(testPdventity.getReferencepdv()).isEqualTo(UPDATED_REFERENCEPDV);
    }

    @Test
    @Transactional
    public void updateNonExistingPdventity() throws Exception {
        int databaseSizeBeforeUpdate = pdventityRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPdventityMockMvc.perform(put("/api/pdventities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pdventity)))
            .andExpect(status().isBadRequest());

        // Validate the Pdventity in the database
        List<Pdventity> pdventityList = pdventityRepository.findAll();
        assertThat(pdventityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePdventity() throws Exception {
        // Initialize the database
        pdventityService.save(pdventity);

        int databaseSizeBeforeDelete = pdventityRepository.findAll().size();

        // Delete the pdventity
        restPdventityMockMvc.perform(delete("/api/pdventities/{id}", pdventity.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Pdventity> pdventityList = pdventityRepository.findAll();
        assertThat(pdventityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
