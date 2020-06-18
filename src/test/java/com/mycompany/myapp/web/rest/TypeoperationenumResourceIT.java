package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Transaction2App;
import com.mycompany.myapp.domain.Typeoperationenum;
import com.mycompany.myapp.repository.TypeoperationenumRepository;
import com.mycompany.myapp.service.TypeoperationenumService;
import com.mycompany.myapp.service.dto.TypeoperationenumCriteria;
import com.mycompany.myapp.service.TypeoperationenumQueryService;

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
 * Integration tests for the {@link TypeoperationenumResource} REST controller.
 */
@SpringBootTest(classes = Transaction2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeoperationenumResourceIT {

    private static final String DEFAULT_PARI = "AAAAAAAAAA";
    private static final String UPDATED_PARI = "BBBBBBBBBB";

    private static final String DEFAULT_ANNULATION = "AAAAAAAAAA";
    private static final String UPDATED_ANNULATION = "BBBBBBBBBB";

    private static final String DEFAULT_PAIEMENT = "AAAAAAAAAA";
    private static final String UPDATED_PAIEMENT = "BBBBBBBBBB";

    @Autowired
    private TypeoperationenumRepository typeoperationenumRepository;

    @Autowired
    private TypeoperationenumService typeoperationenumService;

    @Autowired
    private TypeoperationenumQueryService typeoperationenumQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeoperationenumMockMvc;

    private Typeoperationenum typeoperationenum;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Typeoperationenum createEntity(EntityManager em) {
        Typeoperationenum typeoperationenum = new Typeoperationenum()
            .pari(DEFAULT_PARI)
            .annulation(DEFAULT_ANNULATION)
            .paiement(DEFAULT_PAIEMENT);
        return typeoperationenum;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Typeoperationenum createUpdatedEntity(EntityManager em) {
        Typeoperationenum typeoperationenum = new Typeoperationenum()
            .pari(UPDATED_PARI)
            .annulation(UPDATED_ANNULATION)
            .paiement(UPDATED_PAIEMENT);
        return typeoperationenum;
    }

    @BeforeEach
    public void initTest() {
        typeoperationenum = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeoperationenum() throws Exception {
        int databaseSizeBeforeCreate = typeoperationenumRepository.findAll().size();
        // Create the Typeoperationenum
        restTypeoperationenumMockMvc.perform(post("/api/typeoperationenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeoperationenum)))
            .andExpect(status().isCreated());

        // Validate the Typeoperationenum in the database
        List<Typeoperationenum> typeoperationenumList = typeoperationenumRepository.findAll();
        assertThat(typeoperationenumList).hasSize(databaseSizeBeforeCreate + 1);
        Typeoperationenum testTypeoperationenum = typeoperationenumList.get(typeoperationenumList.size() - 1);
        assertThat(testTypeoperationenum.getPari()).isEqualTo(DEFAULT_PARI);
        assertThat(testTypeoperationenum.getAnnulation()).isEqualTo(DEFAULT_ANNULATION);
        assertThat(testTypeoperationenum.getPaiement()).isEqualTo(DEFAULT_PAIEMENT);
    }

    @Test
    @Transactional
    public void createTypeoperationenumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeoperationenumRepository.findAll().size();

        // Create the Typeoperationenum with an existing ID
        typeoperationenum.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeoperationenumMockMvc.perform(post("/api/typeoperationenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeoperationenum)))
            .andExpect(status().isBadRequest());

        // Validate the Typeoperationenum in the database
        List<Typeoperationenum> typeoperationenumList = typeoperationenumRepository.findAll();
        assertThat(typeoperationenumList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeoperationenums() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList
        restTypeoperationenumMockMvc.perform(get("/api/typeoperationenums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeoperationenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].pari").value(hasItem(DEFAULT_PARI)))
            .andExpect(jsonPath("$.[*].annulation").value(hasItem(DEFAULT_ANNULATION)))
            .andExpect(jsonPath("$.[*].paiement").value(hasItem(DEFAULT_PAIEMENT)));
    }
    
    @Test
    @Transactional
    public void getTypeoperationenum() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get the typeoperationenum
        restTypeoperationenumMockMvc.perform(get("/api/typeoperationenums/{id}", typeoperationenum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeoperationenum.getId().intValue()))
            .andExpect(jsonPath("$.pari").value(DEFAULT_PARI))
            .andExpect(jsonPath("$.annulation").value(DEFAULT_ANNULATION))
            .andExpect(jsonPath("$.paiement").value(DEFAULT_PAIEMENT));
    }


    @Test
    @Transactional
    public void getTypeoperationenumsByIdFiltering() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        Long id = typeoperationenum.getId();

        defaultTypeoperationenumShouldBeFound("id.equals=" + id);
        defaultTypeoperationenumShouldNotBeFound("id.notEquals=" + id);

        defaultTypeoperationenumShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTypeoperationenumShouldNotBeFound("id.greaterThan=" + id);

        defaultTypeoperationenumShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTypeoperationenumShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTypeoperationenumsByPariIsEqualToSomething() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where pari equals to DEFAULT_PARI
        defaultTypeoperationenumShouldBeFound("pari.equals=" + DEFAULT_PARI);

        // Get all the typeoperationenumList where pari equals to UPDATED_PARI
        defaultTypeoperationenumShouldNotBeFound("pari.equals=" + UPDATED_PARI);
    }

    @Test
    @Transactional
    public void getAllTypeoperationenumsByPariIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where pari not equals to DEFAULT_PARI
        defaultTypeoperationenumShouldNotBeFound("pari.notEquals=" + DEFAULT_PARI);

        // Get all the typeoperationenumList where pari not equals to UPDATED_PARI
        defaultTypeoperationenumShouldBeFound("pari.notEquals=" + UPDATED_PARI);
    }

    @Test
    @Transactional
    public void getAllTypeoperationenumsByPariIsInShouldWork() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where pari in DEFAULT_PARI or UPDATED_PARI
        defaultTypeoperationenumShouldBeFound("pari.in=" + DEFAULT_PARI + "," + UPDATED_PARI);

        // Get all the typeoperationenumList where pari equals to UPDATED_PARI
        defaultTypeoperationenumShouldNotBeFound("pari.in=" + UPDATED_PARI);
    }

    @Test
    @Transactional
    public void getAllTypeoperationenumsByPariIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where pari is not null
        defaultTypeoperationenumShouldBeFound("pari.specified=true");

        // Get all the typeoperationenumList where pari is null
        defaultTypeoperationenumShouldNotBeFound("pari.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeoperationenumsByPariContainsSomething() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where pari contains DEFAULT_PARI
        defaultTypeoperationenumShouldBeFound("pari.contains=" + DEFAULT_PARI);

        // Get all the typeoperationenumList where pari contains UPDATED_PARI
        defaultTypeoperationenumShouldNotBeFound("pari.contains=" + UPDATED_PARI);
    }

    @Test
    @Transactional
    public void getAllTypeoperationenumsByPariNotContainsSomething() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where pari does not contain DEFAULT_PARI
        defaultTypeoperationenumShouldNotBeFound("pari.doesNotContain=" + DEFAULT_PARI);

        // Get all the typeoperationenumList where pari does not contain UPDATED_PARI
        defaultTypeoperationenumShouldBeFound("pari.doesNotContain=" + UPDATED_PARI);
    }


    @Test
    @Transactional
    public void getAllTypeoperationenumsByAnnulationIsEqualToSomething() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where annulation equals to DEFAULT_ANNULATION
        defaultTypeoperationenumShouldBeFound("annulation.equals=" + DEFAULT_ANNULATION);

        // Get all the typeoperationenumList where annulation equals to UPDATED_ANNULATION
        defaultTypeoperationenumShouldNotBeFound("annulation.equals=" + UPDATED_ANNULATION);
    }

    @Test
    @Transactional
    public void getAllTypeoperationenumsByAnnulationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where annulation not equals to DEFAULT_ANNULATION
        defaultTypeoperationenumShouldNotBeFound("annulation.notEquals=" + DEFAULT_ANNULATION);

        // Get all the typeoperationenumList where annulation not equals to UPDATED_ANNULATION
        defaultTypeoperationenumShouldBeFound("annulation.notEquals=" + UPDATED_ANNULATION);
    }

    @Test
    @Transactional
    public void getAllTypeoperationenumsByAnnulationIsInShouldWork() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where annulation in DEFAULT_ANNULATION or UPDATED_ANNULATION
        defaultTypeoperationenumShouldBeFound("annulation.in=" + DEFAULT_ANNULATION + "," + UPDATED_ANNULATION);

        // Get all the typeoperationenumList where annulation equals to UPDATED_ANNULATION
        defaultTypeoperationenumShouldNotBeFound("annulation.in=" + UPDATED_ANNULATION);
    }

    @Test
    @Transactional
    public void getAllTypeoperationenumsByAnnulationIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where annulation is not null
        defaultTypeoperationenumShouldBeFound("annulation.specified=true");

        // Get all the typeoperationenumList where annulation is null
        defaultTypeoperationenumShouldNotBeFound("annulation.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeoperationenumsByAnnulationContainsSomething() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where annulation contains DEFAULT_ANNULATION
        defaultTypeoperationenumShouldBeFound("annulation.contains=" + DEFAULT_ANNULATION);

        // Get all the typeoperationenumList where annulation contains UPDATED_ANNULATION
        defaultTypeoperationenumShouldNotBeFound("annulation.contains=" + UPDATED_ANNULATION);
    }

    @Test
    @Transactional
    public void getAllTypeoperationenumsByAnnulationNotContainsSomething() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where annulation does not contain DEFAULT_ANNULATION
        defaultTypeoperationenumShouldNotBeFound("annulation.doesNotContain=" + DEFAULT_ANNULATION);

        // Get all the typeoperationenumList where annulation does not contain UPDATED_ANNULATION
        defaultTypeoperationenumShouldBeFound("annulation.doesNotContain=" + UPDATED_ANNULATION);
    }


    @Test
    @Transactional
    public void getAllTypeoperationenumsByPaiementIsEqualToSomething() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where paiement equals to DEFAULT_PAIEMENT
        defaultTypeoperationenumShouldBeFound("paiement.equals=" + DEFAULT_PAIEMENT);

        // Get all the typeoperationenumList where paiement equals to UPDATED_PAIEMENT
        defaultTypeoperationenumShouldNotBeFound("paiement.equals=" + UPDATED_PAIEMENT);
    }

    @Test
    @Transactional
    public void getAllTypeoperationenumsByPaiementIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where paiement not equals to DEFAULT_PAIEMENT
        defaultTypeoperationenumShouldNotBeFound("paiement.notEquals=" + DEFAULT_PAIEMENT);

        // Get all the typeoperationenumList where paiement not equals to UPDATED_PAIEMENT
        defaultTypeoperationenumShouldBeFound("paiement.notEquals=" + UPDATED_PAIEMENT);
    }

    @Test
    @Transactional
    public void getAllTypeoperationenumsByPaiementIsInShouldWork() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where paiement in DEFAULT_PAIEMENT or UPDATED_PAIEMENT
        defaultTypeoperationenumShouldBeFound("paiement.in=" + DEFAULT_PAIEMENT + "," + UPDATED_PAIEMENT);

        // Get all the typeoperationenumList where paiement equals to UPDATED_PAIEMENT
        defaultTypeoperationenumShouldNotBeFound("paiement.in=" + UPDATED_PAIEMENT);
    }

    @Test
    @Transactional
    public void getAllTypeoperationenumsByPaiementIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where paiement is not null
        defaultTypeoperationenumShouldBeFound("paiement.specified=true");

        // Get all the typeoperationenumList where paiement is null
        defaultTypeoperationenumShouldNotBeFound("paiement.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeoperationenumsByPaiementContainsSomething() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where paiement contains DEFAULT_PAIEMENT
        defaultTypeoperationenumShouldBeFound("paiement.contains=" + DEFAULT_PAIEMENT);

        // Get all the typeoperationenumList where paiement contains UPDATED_PAIEMENT
        defaultTypeoperationenumShouldNotBeFound("paiement.contains=" + UPDATED_PAIEMENT);
    }

    @Test
    @Transactional
    public void getAllTypeoperationenumsByPaiementNotContainsSomething() throws Exception {
        // Initialize the database
        typeoperationenumRepository.saveAndFlush(typeoperationenum);

        // Get all the typeoperationenumList where paiement does not contain DEFAULT_PAIEMENT
        defaultTypeoperationenumShouldNotBeFound("paiement.doesNotContain=" + DEFAULT_PAIEMENT);

        // Get all the typeoperationenumList where paiement does not contain UPDATED_PAIEMENT
        defaultTypeoperationenumShouldBeFound("paiement.doesNotContain=" + UPDATED_PAIEMENT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTypeoperationenumShouldBeFound(String filter) throws Exception {
        restTypeoperationenumMockMvc.perform(get("/api/typeoperationenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeoperationenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].pari").value(hasItem(DEFAULT_PARI)))
            .andExpect(jsonPath("$.[*].annulation").value(hasItem(DEFAULT_ANNULATION)))
            .andExpect(jsonPath("$.[*].paiement").value(hasItem(DEFAULT_PAIEMENT)));

        // Check, that the count call also returns 1
        restTypeoperationenumMockMvc.perform(get("/api/typeoperationenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTypeoperationenumShouldNotBeFound(String filter) throws Exception {
        restTypeoperationenumMockMvc.perform(get("/api/typeoperationenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTypeoperationenumMockMvc.perform(get("/api/typeoperationenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingTypeoperationenum() throws Exception {
        // Get the typeoperationenum
        restTypeoperationenumMockMvc.perform(get("/api/typeoperationenums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeoperationenum() throws Exception {
        // Initialize the database
        typeoperationenumService.save(typeoperationenum);

        int databaseSizeBeforeUpdate = typeoperationenumRepository.findAll().size();

        // Update the typeoperationenum
        Typeoperationenum updatedTypeoperationenum = typeoperationenumRepository.findById(typeoperationenum.getId()).get();
        // Disconnect from session so that the updates on updatedTypeoperationenum are not directly saved in db
        em.detach(updatedTypeoperationenum);
        updatedTypeoperationenum
            .pari(UPDATED_PARI)
            .annulation(UPDATED_ANNULATION)
            .paiement(UPDATED_PAIEMENT);

        restTypeoperationenumMockMvc.perform(put("/api/typeoperationenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeoperationenum)))
            .andExpect(status().isOk());

        // Validate the Typeoperationenum in the database
        List<Typeoperationenum> typeoperationenumList = typeoperationenumRepository.findAll();
        assertThat(typeoperationenumList).hasSize(databaseSizeBeforeUpdate);
        Typeoperationenum testTypeoperationenum = typeoperationenumList.get(typeoperationenumList.size() - 1);
        assertThat(testTypeoperationenum.getPari()).isEqualTo(UPDATED_PARI);
        assertThat(testTypeoperationenum.getAnnulation()).isEqualTo(UPDATED_ANNULATION);
        assertThat(testTypeoperationenum.getPaiement()).isEqualTo(UPDATED_PAIEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeoperationenum() throws Exception {
        int databaseSizeBeforeUpdate = typeoperationenumRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeoperationenumMockMvc.perform(put("/api/typeoperationenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeoperationenum)))
            .andExpect(status().isBadRequest());

        // Validate the Typeoperationenum in the database
        List<Typeoperationenum> typeoperationenumList = typeoperationenumRepository.findAll();
        assertThat(typeoperationenumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeoperationenum() throws Exception {
        // Initialize the database
        typeoperationenumService.save(typeoperationenum);

        int databaseSizeBeforeDelete = typeoperationenumRepository.findAll().size();

        // Delete the typeoperationenum
        restTypeoperationenumMockMvc.perform(delete("/api/typeoperationenums/{id}", typeoperationenum.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Typeoperationenum> typeoperationenumList = typeoperationenumRepository.findAll();
        assertThat(typeoperationenumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
