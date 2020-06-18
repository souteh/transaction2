package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Transaction2App;
import com.mycompany.myapp.domain.Catonlinecanalenum;
import com.mycompany.myapp.repository.CatonlinecanalenumRepository;
import com.mycompany.myapp.service.CatonlinecanalenumService;
import com.mycompany.myapp.service.dto.CatonlinecanalenumCriteria;
import com.mycompany.myapp.service.CatonlinecanalenumQueryService;

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
 * Integration tests for the {@link CatonlinecanalenumResource} REST controller.
 */
@SpringBootTest(classes = Transaction2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class CatonlinecanalenumResourceIT {

    private static final String DEFAULT_PEL = "AAAAAAAAAA";
    private static final String UPDATED_PEL = "BBBBBBBBBB";

    private static final String DEFAULT_PTEL = "AAAAAAAAAA";
    private static final String UPDATED_PTEL = "BBBBBBBBBB";

    @Autowired
    private CatonlinecanalenumRepository catonlinecanalenumRepository;

    @Autowired
    private CatonlinecanalenumService catonlinecanalenumService;

    @Autowired
    private CatonlinecanalenumQueryService catonlinecanalenumQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCatonlinecanalenumMockMvc;

    private Catonlinecanalenum catonlinecanalenum;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Catonlinecanalenum createEntity(EntityManager em) {
        Catonlinecanalenum catonlinecanalenum = new Catonlinecanalenum()
            .pel(DEFAULT_PEL)
            .ptel(DEFAULT_PTEL);
        return catonlinecanalenum;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Catonlinecanalenum createUpdatedEntity(EntityManager em) {
        Catonlinecanalenum catonlinecanalenum = new Catonlinecanalenum()
            .pel(UPDATED_PEL)
            .ptel(UPDATED_PTEL);
        return catonlinecanalenum;
    }

    @BeforeEach
    public void initTest() {
        catonlinecanalenum = createEntity(em);
    }

    @Test
    @Transactional
    public void createCatonlinecanalenum() throws Exception {
        int databaseSizeBeforeCreate = catonlinecanalenumRepository.findAll().size();
        // Create the Catonlinecanalenum
        restCatonlinecanalenumMockMvc.perform(post("/api/catonlinecanalenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(catonlinecanalenum)))
            .andExpect(status().isCreated());

        // Validate the Catonlinecanalenum in the database
        List<Catonlinecanalenum> catonlinecanalenumList = catonlinecanalenumRepository.findAll();
        assertThat(catonlinecanalenumList).hasSize(databaseSizeBeforeCreate + 1);
        Catonlinecanalenum testCatonlinecanalenum = catonlinecanalenumList.get(catonlinecanalenumList.size() - 1);
        assertThat(testCatonlinecanalenum.getPel()).isEqualTo(DEFAULT_PEL);
        assertThat(testCatonlinecanalenum.getPtel()).isEqualTo(DEFAULT_PTEL);
    }

    @Test
    @Transactional
    public void createCatonlinecanalenumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = catonlinecanalenumRepository.findAll().size();

        // Create the Catonlinecanalenum with an existing ID
        catonlinecanalenum.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatonlinecanalenumMockMvc.perform(post("/api/catonlinecanalenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(catonlinecanalenum)))
            .andExpect(status().isBadRequest());

        // Validate the Catonlinecanalenum in the database
        List<Catonlinecanalenum> catonlinecanalenumList = catonlinecanalenumRepository.findAll();
        assertThat(catonlinecanalenumList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCatonlinecanalenums() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get all the catonlinecanalenumList
        restCatonlinecanalenumMockMvc.perform(get("/api/catonlinecanalenums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catonlinecanalenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].pel").value(hasItem(DEFAULT_PEL)))
            .andExpect(jsonPath("$.[*].ptel").value(hasItem(DEFAULT_PTEL)));
    }
    
    @Test
    @Transactional
    public void getCatonlinecanalenum() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get the catonlinecanalenum
        restCatonlinecanalenumMockMvc.perform(get("/api/catonlinecanalenums/{id}", catonlinecanalenum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(catonlinecanalenum.getId().intValue()))
            .andExpect(jsonPath("$.pel").value(DEFAULT_PEL))
            .andExpect(jsonPath("$.ptel").value(DEFAULT_PTEL));
    }


    @Test
    @Transactional
    public void getCatonlinecanalenumsByIdFiltering() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        Long id = catonlinecanalenum.getId();

        defaultCatonlinecanalenumShouldBeFound("id.equals=" + id);
        defaultCatonlinecanalenumShouldNotBeFound("id.notEquals=" + id);

        defaultCatonlinecanalenumShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCatonlinecanalenumShouldNotBeFound("id.greaterThan=" + id);

        defaultCatonlinecanalenumShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCatonlinecanalenumShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCatonlinecanalenumsByPelIsEqualToSomething() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get all the catonlinecanalenumList where pel equals to DEFAULT_PEL
        defaultCatonlinecanalenumShouldBeFound("pel.equals=" + DEFAULT_PEL);

        // Get all the catonlinecanalenumList where pel equals to UPDATED_PEL
        defaultCatonlinecanalenumShouldNotBeFound("pel.equals=" + UPDATED_PEL);
    }

    @Test
    @Transactional
    public void getAllCatonlinecanalenumsByPelIsNotEqualToSomething() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get all the catonlinecanalenumList where pel not equals to DEFAULT_PEL
        defaultCatonlinecanalenumShouldNotBeFound("pel.notEquals=" + DEFAULT_PEL);

        // Get all the catonlinecanalenumList where pel not equals to UPDATED_PEL
        defaultCatonlinecanalenumShouldBeFound("pel.notEquals=" + UPDATED_PEL);
    }

    @Test
    @Transactional
    public void getAllCatonlinecanalenumsByPelIsInShouldWork() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get all the catonlinecanalenumList where pel in DEFAULT_PEL or UPDATED_PEL
        defaultCatonlinecanalenumShouldBeFound("pel.in=" + DEFAULT_PEL + "," + UPDATED_PEL);

        // Get all the catonlinecanalenumList where pel equals to UPDATED_PEL
        defaultCatonlinecanalenumShouldNotBeFound("pel.in=" + UPDATED_PEL);
    }

    @Test
    @Transactional
    public void getAllCatonlinecanalenumsByPelIsNullOrNotNull() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get all the catonlinecanalenumList where pel is not null
        defaultCatonlinecanalenumShouldBeFound("pel.specified=true");

        // Get all the catonlinecanalenumList where pel is null
        defaultCatonlinecanalenumShouldNotBeFound("pel.specified=false");
    }
                @Test
    @Transactional
    public void getAllCatonlinecanalenumsByPelContainsSomething() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get all the catonlinecanalenumList where pel contains DEFAULT_PEL
        defaultCatonlinecanalenumShouldBeFound("pel.contains=" + DEFAULT_PEL);

        // Get all the catonlinecanalenumList where pel contains UPDATED_PEL
        defaultCatonlinecanalenumShouldNotBeFound("pel.contains=" + UPDATED_PEL);
    }

    @Test
    @Transactional
    public void getAllCatonlinecanalenumsByPelNotContainsSomething() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get all the catonlinecanalenumList where pel does not contain DEFAULT_PEL
        defaultCatonlinecanalenumShouldNotBeFound("pel.doesNotContain=" + DEFAULT_PEL);

        // Get all the catonlinecanalenumList where pel does not contain UPDATED_PEL
        defaultCatonlinecanalenumShouldBeFound("pel.doesNotContain=" + UPDATED_PEL);
    }


    @Test
    @Transactional
    public void getAllCatonlinecanalenumsByPtelIsEqualToSomething() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get all the catonlinecanalenumList where ptel equals to DEFAULT_PTEL
        defaultCatonlinecanalenumShouldBeFound("ptel.equals=" + DEFAULT_PTEL);

        // Get all the catonlinecanalenumList where ptel equals to UPDATED_PTEL
        defaultCatonlinecanalenumShouldNotBeFound("ptel.equals=" + UPDATED_PTEL);
    }

    @Test
    @Transactional
    public void getAllCatonlinecanalenumsByPtelIsNotEqualToSomething() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get all the catonlinecanalenumList where ptel not equals to DEFAULT_PTEL
        defaultCatonlinecanalenumShouldNotBeFound("ptel.notEquals=" + DEFAULT_PTEL);

        // Get all the catonlinecanalenumList where ptel not equals to UPDATED_PTEL
        defaultCatonlinecanalenumShouldBeFound("ptel.notEquals=" + UPDATED_PTEL);
    }

    @Test
    @Transactional
    public void getAllCatonlinecanalenumsByPtelIsInShouldWork() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get all the catonlinecanalenumList where ptel in DEFAULT_PTEL or UPDATED_PTEL
        defaultCatonlinecanalenumShouldBeFound("ptel.in=" + DEFAULT_PTEL + "," + UPDATED_PTEL);

        // Get all the catonlinecanalenumList where ptel equals to UPDATED_PTEL
        defaultCatonlinecanalenumShouldNotBeFound("ptel.in=" + UPDATED_PTEL);
    }

    @Test
    @Transactional
    public void getAllCatonlinecanalenumsByPtelIsNullOrNotNull() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get all the catonlinecanalenumList where ptel is not null
        defaultCatonlinecanalenumShouldBeFound("ptel.specified=true");

        // Get all the catonlinecanalenumList where ptel is null
        defaultCatonlinecanalenumShouldNotBeFound("ptel.specified=false");
    }
                @Test
    @Transactional
    public void getAllCatonlinecanalenumsByPtelContainsSomething() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get all the catonlinecanalenumList where ptel contains DEFAULT_PTEL
        defaultCatonlinecanalenumShouldBeFound("ptel.contains=" + DEFAULT_PTEL);

        // Get all the catonlinecanalenumList where ptel contains UPDATED_PTEL
        defaultCatonlinecanalenumShouldNotBeFound("ptel.contains=" + UPDATED_PTEL);
    }

    @Test
    @Transactional
    public void getAllCatonlinecanalenumsByPtelNotContainsSomething() throws Exception {
        // Initialize the database
        catonlinecanalenumRepository.saveAndFlush(catonlinecanalenum);

        // Get all the catonlinecanalenumList where ptel does not contain DEFAULT_PTEL
        defaultCatonlinecanalenumShouldNotBeFound("ptel.doesNotContain=" + DEFAULT_PTEL);

        // Get all the catonlinecanalenumList where ptel does not contain UPDATED_PTEL
        defaultCatonlinecanalenumShouldBeFound("ptel.doesNotContain=" + UPDATED_PTEL);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCatonlinecanalenumShouldBeFound(String filter) throws Exception {
        restCatonlinecanalenumMockMvc.perform(get("/api/catonlinecanalenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catonlinecanalenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].pel").value(hasItem(DEFAULT_PEL)))
            .andExpect(jsonPath("$.[*].ptel").value(hasItem(DEFAULT_PTEL)));

        // Check, that the count call also returns 1
        restCatonlinecanalenumMockMvc.perform(get("/api/catonlinecanalenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCatonlinecanalenumShouldNotBeFound(String filter) throws Exception {
        restCatonlinecanalenumMockMvc.perform(get("/api/catonlinecanalenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCatonlinecanalenumMockMvc.perform(get("/api/catonlinecanalenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingCatonlinecanalenum() throws Exception {
        // Get the catonlinecanalenum
        restCatonlinecanalenumMockMvc.perform(get("/api/catonlinecanalenums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCatonlinecanalenum() throws Exception {
        // Initialize the database
        catonlinecanalenumService.save(catonlinecanalenum);

        int databaseSizeBeforeUpdate = catonlinecanalenumRepository.findAll().size();

        // Update the catonlinecanalenum
        Catonlinecanalenum updatedCatonlinecanalenum = catonlinecanalenumRepository.findById(catonlinecanalenum.getId()).get();
        // Disconnect from session so that the updates on updatedCatonlinecanalenum are not directly saved in db
        em.detach(updatedCatonlinecanalenum);
        updatedCatonlinecanalenum
            .pel(UPDATED_PEL)
            .ptel(UPDATED_PTEL);

        restCatonlinecanalenumMockMvc.perform(put("/api/catonlinecanalenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCatonlinecanalenum)))
            .andExpect(status().isOk());

        // Validate the Catonlinecanalenum in the database
        List<Catonlinecanalenum> catonlinecanalenumList = catonlinecanalenumRepository.findAll();
        assertThat(catonlinecanalenumList).hasSize(databaseSizeBeforeUpdate);
        Catonlinecanalenum testCatonlinecanalenum = catonlinecanalenumList.get(catonlinecanalenumList.size() - 1);
        assertThat(testCatonlinecanalenum.getPel()).isEqualTo(UPDATED_PEL);
        assertThat(testCatonlinecanalenum.getPtel()).isEqualTo(UPDATED_PTEL);
    }

    @Test
    @Transactional
    public void updateNonExistingCatonlinecanalenum() throws Exception {
        int databaseSizeBeforeUpdate = catonlinecanalenumRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatonlinecanalenumMockMvc.perform(put("/api/catonlinecanalenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(catonlinecanalenum)))
            .andExpect(status().isBadRequest());

        // Validate the Catonlinecanalenum in the database
        List<Catonlinecanalenum> catonlinecanalenumList = catonlinecanalenumRepository.findAll();
        assertThat(catonlinecanalenumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCatonlinecanalenum() throws Exception {
        // Initialize the database
        catonlinecanalenumService.save(catonlinecanalenum);

        int databaseSizeBeforeDelete = catonlinecanalenumRepository.findAll().size();

        // Delete the catonlinecanalenum
        restCatonlinecanalenumMockMvc.perform(delete("/api/catonlinecanalenums/{id}", catonlinecanalenum.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Catonlinecanalenum> catonlinecanalenumList = catonlinecanalenumRepository.findAll();
        assertThat(catonlinecanalenumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
