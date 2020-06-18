package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Transaction2App;
import com.mycompany.myapp.domain.Typecanalenum;
import com.mycompany.myapp.repository.TypecanalenumRepository;
import com.mycompany.myapp.service.TypecanalenumService;
import com.mycompany.myapp.service.dto.TypecanalenumCriteria;
import com.mycompany.myapp.service.TypecanalenumQueryService;

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
 * Integration tests for the {@link TypecanalenumResource} REST controller.
 */
@SpringBootTest(classes = Transaction2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypecanalenumResourceIT {

    private static final String DEFAULT_TERMINAL = "AAAAAAAAAA";
    private static final String UPDATED_TERMINAL = "BBBBBBBBBB";

    private static final String DEFAULT_ONLINE = "AAAAAAAAAA";
    private static final String UPDATED_ONLINE = "BBBBBBBBBB";

    @Autowired
    private TypecanalenumRepository typecanalenumRepository;

    @Autowired
    private TypecanalenumService typecanalenumService;

    @Autowired
    private TypecanalenumQueryService typecanalenumQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypecanalenumMockMvc;

    private Typecanalenum typecanalenum;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Typecanalenum createEntity(EntityManager em) {
        Typecanalenum typecanalenum = new Typecanalenum()
            .terminal(DEFAULT_TERMINAL)
            .online(DEFAULT_ONLINE);
        return typecanalenum;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Typecanalenum createUpdatedEntity(EntityManager em) {
        Typecanalenum typecanalenum = new Typecanalenum()
            .terminal(UPDATED_TERMINAL)
            .online(UPDATED_ONLINE);
        return typecanalenum;
    }

    @BeforeEach
    public void initTest() {
        typecanalenum = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypecanalenum() throws Exception {
        int databaseSizeBeforeCreate = typecanalenumRepository.findAll().size();
        // Create the Typecanalenum
        restTypecanalenumMockMvc.perform(post("/api/typecanalenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typecanalenum)))
            .andExpect(status().isCreated());

        // Validate the Typecanalenum in the database
        List<Typecanalenum> typecanalenumList = typecanalenumRepository.findAll();
        assertThat(typecanalenumList).hasSize(databaseSizeBeforeCreate + 1);
        Typecanalenum testTypecanalenum = typecanalenumList.get(typecanalenumList.size() - 1);
        assertThat(testTypecanalenum.getTerminal()).isEqualTo(DEFAULT_TERMINAL);
        assertThat(testTypecanalenum.getOnline()).isEqualTo(DEFAULT_ONLINE);
    }

    @Test
    @Transactional
    public void createTypecanalenumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typecanalenumRepository.findAll().size();

        // Create the Typecanalenum with an existing ID
        typecanalenum.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypecanalenumMockMvc.perform(post("/api/typecanalenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typecanalenum)))
            .andExpect(status().isBadRequest());

        // Validate the Typecanalenum in the database
        List<Typecanalenum> typecanalenumList = typecanalenumRepository.findAll();
        assertThat(typecanalenumList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypecanalenums() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get all the typecanalenumList
        restTypecanalenumMockMvc.perform(get("/api/typecanalenums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typecanalenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].terminal").value(hasItem(DEFAULT_TERMINAL)))
            .andExpect(jsonPath("$.[*].online").value(hasItem(DEFAULT_ONLINE)));
    }
    
    @Test
    @Transactional
    public void getTypecanalenum() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get the typecanalenum
        restTypecanalenumMockMvc.perform(get("/api/typecanalenums/{id}", typecanalenum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typecanalenum.getId().intValue()))
            .andExpect(jsonPath("$.terminal").value(DEFAULT_TERMINAL))
            .andExpect(jsonPath("$.online").value(DEFAULT_ONLINE));
    }


    @Test
    @Transactional
    public void getTypecanalenumsByIdFiltering() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        Long id = typecanalenum.getId();

        defaultTypecanalenumShouldBeFound("id.equals=" + id);
        defaultTypecanalenumShouldNotBeFound("id.notEquals=" + id);

        defaultTypecanalenumShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTypecanalenumShouldNotBeFound("id.greaterThan=" + id);

        defaultTypecanalenumShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTypecanalenumShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTypecanalenumsByTerminalIsEqualToSomething() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get all the typecanalenumList where terminal equals to DEFAULT_TERMINAL
        defaultTypecanalenumShouldBeFound("terminal.equals=" + DEFAULT_TERMINAL);

        // Get all the typecanalenumList where terminal equals to UPDATED_TERMINAL
        defaultTypecanalenumShouldNotBeFound("terminal.equals=" + UPDATED_TERMINAL);
    }

    @Test
    @Transactional
    public void getAllTypecanalenumsByTerminalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get all the typecanalenumList where terminal not equals to DEFAULT_TERMINAL
        defaultTypecanalenumShouldNotBeFound("terminal.notEquals=" + DEFAULT_TERMINAL);

        // Get all the typecanalenumList where terminal not equals to UPDATED_TERMINAL
        defaultTypecanalenumShouldBeFound("terminal.notEquals=" + UPDATED_TERMINAL);
    }

    @Test
    @Transactional
    public void getAllTypecanalenumsByTerminalIsInShouldWork() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get all the typecanalenumList where terminal in DEFAULT_TERMINAL or UPDATED_TERMINAL
        defaultTypecanalenumShouldBeFound("terminal.in=" + DEFAULT_TERMINAL + "," + UPDATED_TERMINAL);

        // Get all the typecanalenumList where terminal equals to UPDATED_TERMINAL
        defaultTypecanalenumShouldNotBeFound("terminal.in=" + UPDATED_TERMINAL);
    }

    @Test
    @Transactional
    public void getAllTypecanalenumsByTerminalIsNullOrNotNull() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get all the typecanalenumList where terminal is not null
        defaultTypecanalenumShouldBeFound("terminal.specified=true");

        // Get all the typecanalenumList where terminal is null
        defaultTypecanalenumShouldNotBeFound("terminal.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypecanalenumsByTerminalContainsSomething() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get all the typecanalenumList where terminal contains DEFAULT_TERMINAL
        defaultTypecanalenumShouldBeFound("terminal.contains=" + DEFAULT_TERMINAL);

        // Get all the typecanalenumList where terminal contains UPDATED_TERMINAL
        defaultTypecanalenumShouldNotBeFound("terminal.contains=" + UPDATED_TERMINAL);
    }

    @Test
    @Transactional
    public void getAllTypecanalenumsByTerminalNotContainsSomething() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get all the typecanalenumList where terminal does not contain DEFAULT_TERMINAL
        defaultTypecanalenumShouldNotBeFound("terminal.doesNotContain=" + DEFAULT_TERMINAL);

        // Get all the typecanalenumList where terminal does not contain UPDATED_TERMINAL
        defaultTypecanalenumShouldBeFound("terminal.doesNotContain=" + UPDATED_TERMINAL);
    }


    @Test
    @Transactional
    public void getAllTypecanalenumsByOnlineIsEqualToSomething() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get all the typecanalenumList where online equals to DEFAULT_ONLINE
        defaultTypecanalenumShouldBeFound("online.equals=" + DEFAULT_ONLINE);

        // Get all the typecanalenumList where online equals to UPDATED_ONLINE
        defaultTypecanalenumShouldNotBeFound("online.equals=" + UPDATED_ONLINE);
    }

    @Test
    @Transactional
    public void getAllTypecanalenumsByOnlineIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get all the typecanalenumList where online not equals to DEFAULT_ONLINE
        defaultTypecanalenumShouldNotBeFound("online.notEquals=" + DEFAULT_ONLINE);

        // Get all the typecanalenumList where online not equals to UPDATED_ONLINE
        defaultTypecanalenumShouldBeFound("online.notEquals=" + UPDATED_ONLINE);
    }

    @Test
    @Transactional
    public void getAllTypecanalenumsByOnlineIsInShouldWork() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get all the typecanalenumList where online in DEFAULT_ONLINE or UPDATED_ONLINE
        defaultTypecanalenumShouldBeFound("online.in=" + DEFAULT_ONLINE + "," + UPDATED_ONLINE);

        // Get all the typecanalenumList where online equals to UPDATED_ONLINE
        defaultTypecanalenumShouldNotBeFound("online.in=" + UPDATED_ONLINE);
    }

    @Test
    @Transactional
    public void getAllTypecanalenumsByOnlineIsNullOrNotNull() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get all the typecanalenumList where online is not null
        defaultTypecanalenumShouldBeFound("online.specified=true");

        // Get all the typecanalenumList where online is null
        defaultTypecanalenumShouldNotBeFound("online.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypecanalenumsByOnlineContainsSomething() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get all the typecanalenumList where online contains DEFAULT_ONLINE
        defaultTypecanalenumShouldBeFound("online.contains=" + DEFAULT_ONLINE);

        // Get all the typecanalenumList where online contains UPDATED_ONLINE
        defaultTypecanalenumShouldNotBeFound("online.contains=" + UPDATED_ONLINE);
    }

    @Test
    @Transactional
    public void getAllTypecanalenumsByOnlineNotContainsSomething() throws Exception {
        // Initialize the database
        typecanalenumRepository.saveAndFlush(typecanalenum);

        // Get all the typecanalenumList where online does not contain DEFAULT_ONLINE
        defaultTypecanalenumShouldNotBeFound("online.doesNotContain=" + DEFAULT_ONLINE);

        // Get all the typecanalenumList where online does not contain UPDATED_ONLINE
        defaultTypecanalenumShouldBeFound("online.doesNotContain=" + UPDATED_ONLINE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTypecanalenumShouldBeFound(String filter) throws Exception {
        restTypecanalenumMockMvc.perform(get("/api/typecanalenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typecanalenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].terminal").value(hasItem(DEFAULT_TERMINAL)))
            .andExpect(jsonPath("$.[*].online").value(hasItem(DEFAULT_ONLINE)));

        // Check, that the count call also returns 1
        restTypecanalenumMockMvc.perform(get("/api/typecanalenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTypecanalenumShouldNotBeFound(String filter) throws Exception {
        restTypecanalenumMockMvc.perform(get("/api/typecanalenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTypecanalenumMockMvc.perform(get("/api/typecanalenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingTypecanalenum() throws Exception {
        // Get the typecanalenum
        restTypecanalenumMockMvc.perform(get("/api/typecanalenums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypecanalenum() throws Exception {
        // Initialize the database
        typecanalenumService.save(typecanalenum);

        int databaseSizeBeforeUpdate = typecanalenumRepository.findAll().size();

        // Update the typecanalenum
        Typecanalenum updatedTypecanalenum = typecanalenumRepository.findById(typecanalenum.getId()).get();
        // Disconnect from session so that the updates on updatedTypecanalenum are not directly saved in db
        em.detach(updatedTypecanalenum);
        updatedTypecanalenum
            .terminal(UPDATED_TERMINAL)
            .online(UPDATED_ONLINE);

        restTypecanalenumMockMvc.perform(put("/api/typecanalenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypecanalenum)))
            .andExpect(status().isOk());

        // Validate the Typecanalenum in the database
        List<Typecanalenum> typecanalenumList = typecanalenumRepository.findAll();
        assertThat(typecanalenumList).hasSize(databaseSizeBeforeUpdate);
        Typecanalenum testTypecanalenum = typecanalenumList.get(typecanalenumList.size() - 1);
        assertThat(testTypecanalenum.getTerminal()).isEqualTo(UPDATED_TERMINAL);
        assertThat(testTypecanalenum.getOnline()).isEqualTo(UPDATED_ONLINE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypecanalenum() throws Exception {
        int databaseSizeBeforeUpdate = typecanalenumRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypecanalenumMockMvc.perform(put("/api/typecanalenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typecanalenum)))
            .andExpect(status().isBadRequest());

        // Validate the Typecanalenum in the database
        List<Typecanalenum> typecanalenumList = typecanalenumRepository.findAll();
        assertThat(typecanalenumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypecanalenum() throws Exception {
        // Initialize the database
        typecanalenumService.save(typecanalenum);

        int databaseSizeBeforeDelete = typecanalenumRepository.findAll().size();

        // Delete the typecanalenum
        restTypecanalenumMockMvc.perform(delete("/api/typecanalenums/{id}", typecanalenum.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Typecanalenum> typecanalenumList = typecanalenumRepository.findAll();
        assertThat(typecanalenumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
