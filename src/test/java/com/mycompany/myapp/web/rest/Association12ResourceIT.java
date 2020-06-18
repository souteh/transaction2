package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Transaction2App;
import com.mycompany.myapp.domain.Association12;
import com.mycompany.myapp.domain.Clientglentity;
import com.mycompany.myapp.repository.Association12Repository;
import com.mycompany.myapp.service.Association12Service;
import com.mycompany.myapp.service.dto.Association12Criteria;
import com.mycompany.myapp.service.Association12QueryService;

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
 * Integration tests for the {@link Association12Resource} REST controller.
 */
@SpringBootTest(classes = Transaction2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class Association12ResourceIT {

    private static final Long DEFAULT_IDENTIFIANTCONC = 1L;
    private static final Long UPDATED_IDENTIFIANTCONC = 2L;
    private static final Long SMALLER_IDENTIFIANTCONC = 1L - 1L;

    @Autowired
    private Association12Repository association12Repository;

    @Autowired
    private Association12Service association12Service;

    @Autowired
    private Association12QueryService association12QueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAssociation12MockMvc;

    private Association12 association12;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Association12 createEntity(EntityManager em) {
        Association12 association12 = new Association12()
            .identifiantconc(DEFAULT_IDENTIFIANTCONC);
        return association12;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Association12 createUpdatedEntity(EntityManager em) {
        Association12 association12 = new Association12()
            .identifiantconc(UPDATED_IDENTIFIANTCONC);
        return association12;
    }

    @BeforeEach
    public void initTest() {
        association12 = createEntity(em);
    }

    @Test
    @Transactional
    public void createAssociation12() throws Exception {
        int databaseSizeBeforeCreate = association12Repository.findAll().size();
        // Create the Association12
        restAssociation12MockMvc.perform(post("/api/association-12-s")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(association12)))
            .andExpect(status().isCreated());

        // Validate the Association12 in the database
        List<Association12> association12List = association12Repository.findAll();
        assertThat(association12List).hasSize(databaseSizeBeforeCreate + 1);
        Association12 testAssociation12 = association12List.get(association12List.size() - 1);
        assertThat(testAssociation12.getIdentifiantconc()).isEqualTo(DEFAULT_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void createAssociation12WithExistingId() throws Exception {
        int databaseSizeBeforeCreate = association12Repository.findAll().size();

        // Create the Association12 with an existing ID
        association12.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssociation12MockMvc.perform(post("/api/association-12-s")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(association12)))
            .andExpect(status().isBadRequest());

        // Validate the Association12 in the database
        List<Association12> association12List = association12Repository.findAll();
        assertThat(association12List).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAssociation12s() throws Exception {
        // Initialize the database
        association12Repository.saveAndFlush(association12);

        // Get all the association12List
        restAssociation12MockMvc.perform(get("/api/association-12-s?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(association12.getId().intValue())))
            .andExpect(jsonPath("$.[*].identifiantconc").value(hasItem(DEFAULT_IDENTIFIANTCONC.intValue())));
    }
    
    @Test
    @Transactional
    public void getAssociation12() throws Exception {
        // Initialize the database
        association12Repository.saveAndFlush(association12);

        // Get the association12
        restAssociation12MockMvc.perform(get("/api/association-12-s/{id}", association12.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(association12.getId().intValue()))
            .andExpect(jsonPath("$.identifiantconc").value(DEFAULT_IDENTIFIANTCONC.intValue()));
    }


    @Test
    @Transactional
    public void getAssociation12sByIdFiltering() throws Exception {
        // Initialize the database
        association12Repository.saveAndFlush(association12);

        Long id = association12.getId();

        defaultAssociation12ShouldBeFound("id.equals=" + id);
        defaultAssociation12ShouldNotBeFound("id.notEquals=" + id);

        defaultAssociation12ShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultAssociation12ShouldNotBeFound("id.greaterThan=" + id);

        defaultAssociation12ShouldBeFound("id.lessThanOrEqual=" + id);
        defaultAssociation12ShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllAssociation12sByIdentifiantconcIsEqualToSomething() throws Exception {
        // Initialize the database
        association12Repository.saveAndFlush(association12);

        // Get all the association12List where identifiantconc equals to DEFAULT_IDENTIFIANTCONC
        defaultAssociation12ShouldBeFound("identifiantconc.equals=" + DEFAULT_IDENTIFIANTCONC);

        // Get all the association12List where identifiantconc equals to UPDATED_IDENTIFIANTCONC
        defaultAssociation12ShouldNotBeFound("identifiantconc.equals=" + UPDATED_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void getAllAssociation12sByIdentifiantconcIsNotEqualToSomething() throws Exception {
        // Initialize the database
        association12Repository.saveAndFlush(association12);

        // Get all the association12List where identifiantconc not equals to DEFAULT_IDENTIFIANTCONC
        defaultAssociation12ShouldNotBeFound("identifiantconc.notEquals=" + DEFAULT_IDENTIFIANTCONC);

        // Get all the association12List where identifiantconc not equals to UPDATED_IDENTIFIANTCONC
        defaultAssociation12ShouldBeFound("identifiantconc.notEquals=" + UPDATED_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void getAllAssociation12sByIdentifiantconcIsInShouldWork() throws Exception {
        // Initialize the database
        association12Repository.saveAndFlush(association12);

        // Get all the association12List where identifiantconc in DEFAULT_IDENTIFIANTCONC or UPDATED_IDENTIFIANTCONC
        defaultAssociation12ShouldBeFound("identifiantconc.in=" + DEFAULT_IDENTIFIANTCONC + "," + UPDATED_IDENTIFIANTCONC);

        // Get all the association12List where identifiantconc equals to UPDATED_IDENTIFIANTCONC
        defaultAssociation12ShouldNotBeFound("identifiantconc.in=" + UPDATED_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void getAllAssociation12sByIdentifiantconcIsNullOrNotNull() throws Exception {
        // Initialize the database
        association12Repository.saveAndFlush(association12);

        // Get all the association12List where identifiantconc is not null
        defaultAssociation12ShouldBeFound("identifiantconc.specified=true");

        // Get all the association12List where identifiantconc is null
        defaultAssociation12ShouldNotBeFound("identifiantconc.specified=false");
    }

    @Test
    @Transactional
    public void getAllAssociation12sByIdentifiantconcIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        association12Repository.saveAndFlush(association12);

        // Get all the association12List where identifiantconc is greater than or equal to DEFAULT_IDENTIFIANTCONC
        defaultAssociation12ShouldBeFound("identifiantconc.greaterThanOrEqual=" + DEFAULT_IDENTIFIANTCONC);

        // Get all the association12List where identifiantconc is greater than or equal to UPDATED_IDENTIFIANTCONC
        defaultAssociation12ShouldNotBeFound("identifiantconc.greaterThanOrEqual=" + UPDATED_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void getAllAssociation12sByIdentifiantconcIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        association12Repository.saveAndFlush(association12);

        // Get all the association12List where identifiantconc is less than or equal to DEFAULT_IDENTIFIANTCONC
        defaultAssociation12ShouldBeFound("identifiantconc.lessThanOrEqual=" + DEFAULT_IDENTIFIANTCONC);

        // Get all the association12List where identifiantconc is less than or equal to SMALLER_IDENTIFIANTCONC
        defaultAssociation12ShouldNotBeFound("identifiantconc.lessThanOrEqual=" + SMALLER_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void getAllAssociation12sByIdentifiantconcIsLessThanSomething() throws Exception {
        // Initialize the database
        association12Repository.saveAndFlush(association12);

        // Get all the association12List where identifiantconc is less than DEFAULT_IDENTIFIANTCONC
        defaultAssociation12ShouldNotBeFound("identifiantconc.lessThan=" + DEFAULT_IDENTIFIANTCONC);

        // Get all the association12List where identifiantconc is less than UPDATED_IDENTIFIANTCONC
        defaultAssociation12ShouldBeFound("identifiantconc.lessThan=" + UPDATED_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void getAllAssociation12sByIdentifiantconcIsGreaterThanSomething() throws Exception {
        // Initialize the database
        association12Repository.saveAndFlush(association12);

        // Get all the association12List where identifiantconc is greater than DEFAULT_IDENTIFIANTCONC
        defaultAssociation12ShouldNotBeFound("identifiantconc.greaterThan=" + DEFAULT_IDENTIFIANTCONC);

        // Get all the association12List where identifiantconc is greater than SMALLER_IDENTIFIANTCONC
        defaultAssociation12ShouldBeFound("identifiantconc.greaterThan=" + SMALLER_IDENTIFIANTCONC);
    }


    @Test
    @Transactional
    public void getAllAssociation12sByIdIsEqualToSomething() throws Exception {
        // Initialize the database
        association12Repository.saveAndFlush(association12);
        Clientglentity id = ClientglentityResourceIT.createEntity(em);
        em.persist(id);
        em.flush();
        association12.setId(id);
        association12Repository.saveAndFlush(association12);
        Long idId = id.getId();

        // Get all the association12List where id equals to idId
        defaultAssociation12ShouldBeFound("idId.equals=" + idId);

        // Get all the association12List where id equals to idId + 1
        defaultAssociation12ShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAssociation12ShouldBeFound(String filter) throws Exception {
        restAssociation12MockMvc.perform(get("/api/association-12-s?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(association12.getId().intValue())))
            .andExpect(jsonPath("$.[*].identifiantconc").value(hasItem(DEFAULT_IDENTIFIANTCONC.intValue())));

        // Check, that the count call also returns 1
        restAssociation12MockMvc.perform(get("/api/association-12-s/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAssociation12ShouldNotBeFound(String filter) throws Exception {
        restAssociation12MockMvc.perform(get("/api/association-12-s?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAssociation12MockMvc.perform(get("/api/association-12-s/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingAssociation12() throws Exception {
        // Get the association12
        restAssociation12MockMvc.perform(get("/api/association-12-s/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAssociation12() throws Exception {
        // Initialize the database
        association12Service.save(association12);

        int databaseSizeBeforeUpdate = association12Repository.findAll().size();

        // Update the association12
        Association12 updatedAssociation12 = association12Repository.findById(association12.getId()).get();
        // Disconnect from session so that the updates on updatedAssociation12 are not directly saved in db
        em.detach(updatedAssociation12);
        updatedAssociation12
            .identifiantconc(UPDATED_IDENTIFIANTCONC);

        restAssociation12MockMvc.perform(put("/api/association-12-s")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAssociation12)))
            .andExpect(status().isOk());

        // Validate the Association12 in the database
        List<Association12> association12List = association12Repository.findAll();
        assertThat(association12List).hasSize(databaseSizeBeforeUpdate);
        Association12 testAssociation12 = association12List.get(association12List.size() - 1);
        assertThat(testAssociation12.getIdentifiantconc()).isEqualTo(UPDATED_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void updateNonExistingAssociation12() throws Exception {
        int databaseSizeBeforeUpdate = association12Repository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssociation12MockMvc.perform(put("/api/association-12-s")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(association12)))
            .andExpect(status().isBadRequest());

        // Validate the Association12 in the database
        List<Association12> association12List = association12Repository.findAll();
        assertThat(association12List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAssociation12() throws Exception {
        // Initialize the database
        association12Service.save(association12);

        int databaseSizeBeforeDelete = association12Repository.findAll().size();

        // Delete the association12
        restAssociation12MockMvc.perform(delete("/api/association-12-s/{id}", association12.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Association12> association12List = association12Repository.findAll();
        assertThat(association12List).hasSize(databaseSizeBeforeDelete - 1);
    }
}
