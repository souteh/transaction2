package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Transaction2App;
import com.mycompany.myapp.domain.Typepaiementenum;
import com.mycompany.myapp.repository.TypepaiementenumRepository;
import com.mycompany.myapp.service.TypepaiementenumService;
import com.mycompany.myapp.service.dto.TypepaiementenumCriteria;
import com.mycompany.myapp.service.TypepaiementenumQueryService;

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
 * Integration tests for the {@link TypepaiementenumResource} REST controller.
 */
@SpringBootTest(classes = Transaction2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypepaiementenumResourceIT {

    private static final String DEFAULT_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL = "BBBBBBBBBB";

    private static final String DEFAULT_AVANCE = "AAAAAAAAAA";
    private static final String UPDATED_AVANCE = "BBBBBBBBBB";

    @Autowired
    private TypepaiementenumRepository typepaiementenumRepository;

    @Autowired
    private TypepaiementenumService typepaiementenumService;

    @Autowired
    private TypepaiementenumQueryService typepaiementenumQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypepaiementenumMockMvc;

    private Typepaiementenum typepaiementenum;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Typepaiementenum createEntity(EntityManager em) {
        Typepaiementenum typepaiementenum = new Typepaiementenum()
            .total(DEFAULT_TOTAL)
            .avance(DEFAULT_AVANCE);
        return typepaiementenum;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Typepaiementenum createUpdatedEntity(EntityManager em) {
        Typepaiementenum typepaiementenum = new Typepaiementenum()
            .total(UPDATED_TOTAL)
            .avance(UPDATED_AVANCE);
        return typepaiementenum;
    }

    @BeforeEach
    public void initTest() {
        typepaiementenum = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypepaiementenum() throws Exception {
        int databaseSizeBeforeCreate = typepaiementenumRepository.findAll().size();
        // Create the Typepaiementenum
        restTypepaiementenumMockMvc.perform(post("/api/typepaiementenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typepaiementenum)))
            .andExpect(status().isCreated());

        // Validate the Typepaiementenum in the database
        List<Typepaiementenum> typepaiementenumList = typepaiementenumRepository.findAll();
        assertThat(typepaiementenumList).hasSize(databaseSizeBeforeCreate + 1);
        Typepaiementenum testTypepaiementenum = typepaiementenumList.get(typepaiementenumList.size() - 1);
        assertThat(testTypepaiementenum.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testTypepaiementenum.getAvance()).isEqualTo(DEFAULT_AVANCE);
    }

    @Test
    @Transactional
    public void createTypepaiementenumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typepaiementenumRepository.findAll().size();

        // Create the Typepaiementenum with an existing ID
        typepaiementenum.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypepaiementenumMockMvc.perform(post("/api/typepaiementenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typepaiementenum)))
            .andExpect(status().isBadRequest());

        // Validate the Typepaiementenum in the database
        List<Typepaiementenum> typepaiementenumList = typepaiementenumRepository.findAll();
        assertThat(typepaiementenumList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypepaiementenums() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get all the typepaiementenumList
        restTypepaiementenumMockMvc.perform(get("/api/typepaiementenums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typepaiementenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)))
            .andExpect(jsonPath("$.[*].avance").value(hasItem(DEFAULT_AVANCE)));
    }
    
    @Test
    @Transactional
    public void getTypepaiementenum() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get the typepaiementenum
        restTypepaiementenumMockMvc.perform(get("/api/typepaiementenums/{id}", typepaiementenum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typepaiementenum.getId().intValue()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL))
            .andExpect(jsonPath("$.avance").value(DEFAULT_AVANCE));
    }


    @Test
    @Transactional
    public void getTypepaiementenumsByIdFiltering() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        Long id = typepaiementenum.getId();

        defaultTypepaiementenumShouldBeFound("id.equals=" + id);
        defaultTypepaiementenumShouldNotBeFound("id.notEquals=" + id);

        defaultTypepaiementenumShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTypepaiementenumShouldNotBeFound("id.greaterThan=" + id);

        defaultTypepaiementenumShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTypepaiementenumShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTypepaiementenumsByTotalIsEqualToSomething() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get all the typepaiementenumList where total equals to DEFAULT_TOTAL
        defaultTypepaiementenumShouldBeFound("total.equals=" + DEFAULT_TOTAL);

        // Get all the typepaiementenumList where total equals to UPDATED_TOTAL
        defaultTypepaiementenumShouldNotBeFound("total.equals=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    public void getAllTypepaiementenumsByTotalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get all the typepaiementenumList where total not equals to DEFAULT_TOTAL
        defaultTypepaiementenumShouldNotBeFound("total.notEquals=" + DEFAULT_TOTAL);

        // Get all the typepaiementenumList where total not equals to UPDATED_TOTAL
        defaultTypepaiementenumShouldBeFound("total.notEquals=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    public void getAllTypepaiementenumsByTotalIsInShouldWork() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get all the typepaiementenumList where total in DEFAULT_TOTAL or UPDATED_TOTAL
        defaultTypepaiementenumShouldBeFound("total.in=" + DEFAULT_TOTAL + "," + UPDATED_TOTAL);

        // Get all the typepaiementenumList where total equals to UPDATED_TOTAL
        defaultTypepaiementenumShouldNotBeFound("total.in=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    public void getAllTypepaiementenumsByTotalIsNullOrNotNull() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get all the typepaiementenumList where total is not null
        defaultTypepaiementenumShouldBeFound("total.specified=true");

        // Get all the typepaiementenumList where total is null
        defaultTypepaiementenumShouldNotBeFound("total.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypepaiementenumsByTotalContainsSomething() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get all the typepaiementenumList where total contains DEFAULT_TOTAL
        defaultTypepaiementenumShouldBeFound("total.contains=" + DEFAULT_TOTAL);

        // Get all the typepaiementenumList where total contains UPDATED_TOTAL
        defaultTypepaiementenumShouldNotBeFound("total.contains=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    public void getAllTypepaiementenumsByTotalNotContainsSomething() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get all the typepaiementenumList where total does not contain DEFAULT_TOTAL
        defaultTypepaiementenumShouldNotBeFound("total.doesNotContain=" + DEFAULT_TOTAL);

        // Get all the typepaiementenumList where total does not contain UPDATED_TOTAL
        defaultTypepaiementenumShouldBeFound("total.doesNotContain=" + UPDATED_TOTAL);
    }


    @Test
    @Transactional
    public void getAllTypepaiementenumsByAvanceIsEqualToSomething() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get all the typepaiementenumList where avance equals to DEFAULT_AVANCE
        defaultTypepaiementenumShouldBeFound("avance.equals=" + DEFAULT_AVANCE);

        // Get all the typepaiementenumList where avance equals to UPDATED_AVANCE
        defaultTypepaiementenumShouldNotBeFound("avance.equals=" + UPDATED_AVANCE);
    }

    @Test
    @Transactional
    public void getAllTypepaiementenumsByAvanceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get all the typepaiementenumList where avance not equals to DEFAULT_AVANCE
        defaultTypepaiementenumShouldNotBeFound("avance.notEquals=" + DEFAULT_AVANCE);

        // Get all the typepaiementenumList where avance not equals to UPDATED_AVANCE
        defaultTypepaiementenumShouldBeFound("avance.notEquals=" + UPDATED_AVANCE);
    }

    @Test
    @Transactional
    public void getAllTypepaiementenumsByAvanceIsInShouldWork() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get all the typepaiementenumList where avance in DEFAULT_AVANCE or UPDATED_AVANCE
        defaultTypepaiementenumShouldBeFound("avance.in=" + DEFAULT_AVANCE + "," + UPDATED_AVANCE);

        // Get all the typepaiementenumList where avance equals to UPDATED_AVANCE
        defaultTypepaiementenumShouldNotBeFound("avance.in=" + UPDATED_AVANCE);
    }

    @Test
    @Transactional
    public void getAllTypepaiementenumsByAvanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get all the typepaiementenumList where avance is not null
        defaultTypepaiementenumShouldBeFound("avance.specified=true");

        // Get all the typepaiementenumList where avance is null
        defaultTypepaiementenumShouldNotBeFound("avance.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypepaiementenumsByAvanceContainsSomething() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get all the typepaiementenumList where avance contains DEFAULT_AVANCE
        defaultTypepaiementenumShouldBeFound("avance.contains=" + DEFAULT_AVANCE);

        // Get all the typepaiementenumList where avance contains UPDATED_AVANCE
        defaultTypepaiementenumShouldNotBeFound("avance.contains=" + UPDATED_AVANCE);
    }

    @Test
    @Transactional
    public void getAllTypepaiementenumsByAvanceNotContainsSomething() throws Exception {
        // Initialize the database
        typepaiementenumRepository.saveAndFlush(typepaiementenum);

        // Get all the typepaiementenumList where avance does not contain DEFAULT_AVANCE
        defaultTypepaiementenumShouldNotBeFound("avance.doesNotContain=" + DEFAULT_AVANCE);

        // Get all the typepaiementenumList where avance does not contain UPDATED_AVANCE
        defaultTypepaiementenumShouldBeFound("avance.doesNotContain=" + UPDATED_AVANCE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTypepaiementenumShouldBeFound(String filter) throws Exception {
        restTypepaiementenumMockMvc.perform(get("/api/typepaiementenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typepaiementenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)))
            .andExpect(jsonPath("$.[*].avance").value(hasItem(DEFAULT_AVANCE)));

        // Check, that the count call also returns 1
        restTypepaiementenumMockMvc.perform(get("/api/typepaiementenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTypepaiementenumShouldNotBeFound(String filter) throws Exception {
        restTypepaiementenumMockMvc.perform(get("/api/typepaiementenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTypepaiementenumMockMvc.perform(get("/api/typepaiementenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingTypepaiementenum() throws Exception {
        // Get the typepaiementenum
        restTypepaiementenumMockMvc.perform(get("/api/typepaiementenums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypepaiementenum() throws Exception {
        // Initialize the database
        typepaiementenumService.save(typepaiementenum);

        int databaseSizeBeforeUpdate = typepaiementenumRepository.findAll().size();

        // Update the typepaiementenum
        Typepaiementenum updatedTypepaiementenum = typepaiementenumRepository.findById(typepaiementenum.getId()).get();
        // Disconnect from session so that the updates on updatedTypepaiementenum are not directly saved in db
        em.detach(updatedTypepaiementenum);
        updatedTypepaiementenum
            .total(UPDATED_TOTAL)
            .avance(UPDATED_AVANCE);

        restTypepaiementenumMockMvc.perform(put("/api/typepaiementenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypepaiementenum)))
            .andExpect(status().isOk());

        // Validate the Typepaiementenum in the database
        List<Typepaiementenum> typepaiementenumList = typepaiementenumRepository.findAll();
        assertThat(typepaiementenumList).hasSize(databaseSizeBeforeUpdate);
        Typepaiementenum testTypepaiementenum = typepaiementenumList.get(typepaiementenumList.size() - 1);
        assertThat(testTypepaiementenum.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testTypepaiementenum.getAvance()).isEqualTo(UPDATED_AVANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypepaiementenum() throws Exception {
        int databaseSizeBeforeUpdate = typepaiementenumRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypepaiementenumMockMvc.perform(put("/api/typepaiementenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typepaiementenum)))
            .andExpect(status().isBadRequest());

        // Validate the Typepaiementenum in the database
        List<Typepaiementenum> typepaiementenumList = typepaiementenumRepository.findAll();
        assertThat(typepaiementenumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypepaiementenum() throws Exception {
        // Initialize the database
        typepaiementenumService.save(typepaiementenum);

        int databaseSizeBeforeDelete = typepaiementenumRepository.findAll().size();

        // Delete the typepaiementenum
        restTypepaiementenumMockMvc.perform(delete("/api/typepaiementenums/{id}", typepaiementenum.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Typepaiementenum> typepaiementenumList = typepaiementenumRepository.findAll();
        assertThat(typepaiementenumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
