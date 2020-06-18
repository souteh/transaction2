package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Transaction2App;
import com.mycompany.myapp.domain.Modeoperationenum;
import com.mycompany.myapp.repository.ModeoperationenumRepository;
import com.mycompany.myapp.service.ModeoperationenumService;
import com.mycompany.myapp.service.dto.ModeoperationenumCriteria;
import com.mycompany.myapp.service.ModeoperationenumQueryService;

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
 * Integration tests for the {@link ModeoperationenumResource} REST controller.
 */
@SpringBootTest(classes = Transaction2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class ModeoperationenumResourceIT {

    private static final String DEFAULT_AUTOMATIQUE = "AAAAAAAAAA";
    private static final String UPDATED_AUTOMATIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_MANUEL = "AAAAAAAAAA";
    private static final String UPDATED_MANUEL = "BBBBBBBBBB";

    @Autowired
    private ModeoperationenumRepository modeoperationenumRepository;

    @Autowired
    private ModeoperationenumService modeoperationenumService;

    @Autowired
    private ModeoperationenumQueryService modeoperationenumQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restModeoperationenumMockMvc;

    private Modeoperationenum modeoperationenum;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Modeoperationenum createEntity(EntityManager em) {
        Modeoperationenum modeoperationenum = new Modeoperationenum()
            .automatique(DEFAULT_AUTOMATIQUE)
            .manuel(DEFAULT_MANUEL);
        return modeoperationenum;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Modeoperationenum createUpdatedEntity(EntityManager em) {
        Modeoperationenum modeoperationenum = new Modeoperationenum()
            .automatique(UPDATED_AUTOMATIQUE)
            .manuel(UPDATED_MANUEL);
        return modeoperationenum;
    }

    @BeforeEach
    public void initTest() {
        modeoperationenum = createEntity(em);
    }

    @Test
    @Transactional
    public void createModeoperationenum() throws Exception {
        int databaseSizeBeforeCreate = modeoperationenumRepository.findAll().size();
        // Create the Modeoperationenum
        restModeoperationenumMockMvc.perform(post("/api/modeoperationenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeoperationenum)))
            .andExpect(status().isCreated());

        // Validate the Modeoperationenum in the database
        List<Modeoperationenum> modeoperationenumList = modeoperationenumRepository.findAll();
        assertThat(modeoperationenumList).hasSize(databaseSizeBeforeCreate + 1);
        Modeoperationenum testModeoperationenum = modeoperationenumList.get(modeoperationenumList.size() - 1);
        assertThat(testModeoperationenum.getAutomatique()).isEqualTo(DEFAULT_AUTOMATIQUE);
        assertThat(testModeoperationenum.getManuel()).isEqualTo(DEFAULT_MANUEL);
    }

    @Test
    @Transactional
    public void createModeoperationenumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modeoperationenumRepository.findAll().size();

        // Create the Modeoperationenum with an existing ID
        modeoperationenum.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModeoperationenumMockMvc.perform(post("/api/modeoperationenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeoperationenum)))
            .andExpect(status().isBadRequest());

        // Validate the Modeoperationenum in the database
        List<Modeoperationenum> modeoperationenumList = modeoperationenumRepository.findAll();
        assertThat(modeoperationenumList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllModeoperationenums() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get all the modeoperationenumList
        restModeoperationenumMockMvc.perform(get("/api/modeoperationenums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modeoperationenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].automatique").value(hasItem(DEFAULT_AUTOMATIQUE)))
            .andExpect(jsonPath("$.[*].manuel").value(hasItem(DEFAULT_MANUEL)));
    }
    
    @Test
    @Transactional
    public void getModeoperationenum() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get the modeoperationenum
        restModeoperationenumMockMvc.perform(get("/api/modeoperationenums/{id}", modeoperationenum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(modeoperationenum.getId().intValue()))
            .andExpect(jsonPath("$.automatique").value(DEFAULT_AUTOMATIQUE))
            .andExpect(jsonPath("$.manuel").value(DEFAULT_MANUEL));
    }


    @Test
    @Transactional
    public void getModeoperationenumsByIdFiltering() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        Long id = modeoperationenum.getId();

        defaultModeoperationenumShouldBeFound("id.equals=" + id);
        defaultModeoperationenumShouldNotBeFound("id.notEquals=" + id);

        defaultModeoperationenumShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultModeoperationenumShouldNotBeFound("id.greaterThan=" + id);

        defaultModeoperationenumShouldBeFound("id.lessThanOrEqual=" + id);
        defaultModeoperationenumShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllModeoperationenumsByAutomatiqueIsEqualToSomething() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get all the modeoperationenumList where automatique equals to DEFAULT_AUTOMATIQUE
        defaultModeoperationenumShouldBeFound("automatique.equals=" + DEFAULT_AUTOMATIQUE);

        // Get all the modeoperationenumList where automatique equals to UPDATED_AUTOMATIQUE
        defaultModeoperationenumShouldNotBeFound("automatique.equals=" + UPDATED_AUTOMATIQUE);
    }

    @Test
    @Transactional
    public void getAllModeoperationenumsByAutomatiqueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get all the modeoperationenumList where automatique not equals to DEFAULT_AUTOMATIQUE
        defaultModeoperationenumShouldNotBeFound("automatique.notEquals=" + DEFAULT_AUTOMATIQUE);

        // Get all the modeoperationenumList where automatique not equals to UPDATED_AUTOMATIQUE
        defaultModeoperationenumShouldBeFound("automatique.notEquals=" + UPDATED_AUTOMATIQUE);
    }

    @Test
    @Transactional
    public void getAllModeoperationenumsByAutomatiqueIsInShouldWork() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get all the modeoperationenumList where automatique in DEFAULT_AUTOMATIQUE or UPDATED_AUTOMATIQUE
        defaultModeoperationenumShouldBeFound("automatique.in=" + DEFAULT_AUTOMATIQUE + "," + UPDATED_AUTOMATIQUE);

        // Get all the modeoperationenumList where automatique equals to UPDATED_AUTOMATIQUE
        defaultModeoperationenumShouldNotBeFound("automatique.in=" + UPDATED_AUTOMATIQUE);
    }

    @Test
    @Transactional
    public void getAllModeoperationenumsByAutomatiqueIsNullOrNotNull() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get all the modeoperationenumList where automatique is not null
        defaultModeoperationenumShouldBeFound("automatique.specified=true");

        // Get all the modeoperationenumList where automatique is null
        defaultModeoperationenumShouldNotBeFound("automatique.specified=false");
    }
                @Test
    @Transactional
    public void getAllModeoperationenumsByAutomatiqueContainsSomething() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get all the modeoperationenumList where automatique contains DEFAULT_AUTOMATIQUE
        defaultModeoperationenumShouldBeFound("automatique.contains=" + DEFAULT_AUTOMATIQUE);

        // Get all the modeoperationenumList where automatique contains UPDATED_AUTOMATIQUE
        defaultModeoperationenumShouldNotBeFound("automatique.contains=" + UPDATED_AUTOMATIQUE);
    }

    @Test
    @Transactional
    public void getAllModeoperationenumsByAutomatiqueNotContainsSomething() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get all the modeoperationenumList where automatique does not contain DEFAULT_AUTOMATIQUE
        defaultModeoperationenumShouldNotBeFound("automatique.doesNotContain=" + DEFAULT_AUTOMATIQUE);

        // Get all the modeoperationenumList where automatique does not contain UPDATED_AUTOMATIQUE
        defaultModeoperationenumShouldBeFound("automatique.doesNotContain=" + UPDATED_AUTOMATIQUE);
    }


    @Test
    @Transactional
    public void getAllModeoperationenumsByManuelIsEqualToSomething() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get all the modeoperationenumList where manuel equals to DEFAULT_MANUEL
        defaultModeoperationenumShouldBeFound("manuel.equals=" + DEFAULT_MANUEL);

        // Get all the modeoperationenumList where manuel equals to UPDATED_MANUEL
        defaultModeoperationenumShouldNotBeFound("manuel.equals=" + UPDATED_MANUEL);
    }

    @Test
    @Transactional
    public void getAllModeoperationenumsByManuelIsNotEqualToSomething() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get all the modeoperationenumList where manuel not equals to DEFAULT_MANUEL
        defaultModeoperationenumShouldNotBeFound("manuel.notEquals=" + DEFAULT_MANUEL);

        // Get all the modeoperationenumList where manuel not equals to UPDATED_MANUEL
        defaultModeoperationenumShouldBeFound("manuel.notEquals=" + UPDATED_MANUEL);
    }

    @Test
    @Transactional
    public void getAllModeoperationenumsByManuelIsInShouldWork() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get all the modeoperationenumList where manuel in DEFAULT_MANUEL or UPDATED_MANUEL
        defaultModeoperationenumShouldBeFound("manuel.in=" + DEFAULT_MANUEL + "," + UPDATED_MANUEL);

        // Get all the modeoperationenumList where manuel equals to UPDATED_MANUEL
        defaultModeoperationenumShouldNotBeFound("manuel.in=" + UPDATED_MANUEL);
    }

    @Test
    @Transactional
    public void getAllModeoperationenumsByManuelIsNullOrNotNull() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get all the modeoperationenumList where manuel is not null
        defaultModeoperationenumShouldBeFound("manuel.specified=true");

        // Get all the modeoperationenumList where manuel is null
        defaultModeoperationenumShouldNotBeFound("manuel.specified=false");
    }
                @Test
    @Transactional
    public void getAllModeoperationenumsByManuelContainsSomething() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get all the modeoperationenumList where manuel contains DEFAULT_MANUEL
        defaultModeoperationenumShouldBeFound("manuel.contains=" + DEFAULT_MANUEL);

        // Get all the modeoperationenumList where manuel contains UPDATED_MANUEL
        defaultModeoperationenumShouldNotBeFound("manuel.contains=" + UPDATED_MANUEL);
    }

    @Test
    @Transactional
    public void getAllModeoperationenumsByManuelNotContainsSomething() throws Exception {
        // Initialize the database
        modeoperationenumRepository.saveAndFlush(modeoperationenum);

        // Get all the modeoperationenumList where manuel does not contain DEFAULT_MANUEL
        defaultModeoperationenumShouldNotBeFound("manuel.doesNotContain=" + DEFAULT_MANUEL);

        // Get all the modeoperationenumList where manuel does not contain UPDATED_MANUEL
        defaultModeoperationenumShouldBeFound("manuel.doesNotContain=" + UPDATED_MANUEL);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultModeoperationenumShouldBeFound(String filter) throws Exception {
        restModeoperationenumMockMvc.perform(get("/api/modeoperationenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modeoperationenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].automatique").value(hasItem(DEFAULT_AUTOMATIQUE)))
            .andExpect(jsonPath("$.[*].manuel").value(hasItem(DEFAULT_MANUEL)));

        // Check, that the count call also returns 1
        restModeoperationenumMockMvc.perform(get("/api/modeoperationenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultModeoperationenumShouldNotBeFound(String filter) throws Exception {
        restModeoperationenumMockMvc.perform(get("/api/modeoperationenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restModeoperationenumMockMvc.perform(get("/api/modeoperationenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingModeoperationenum() throws Exception {
        // Get the modeoperationenum
        restModeoperationenumMockMvc.perform(get("/api/modeoperationenums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModeoperationenum() throws Exception {
        // Initialize the database
        modeoperationenumService.save(modeoperationenum);

        int databaseSizeBeforeUpdate = modeoperationenumRepository.findAll().size();

        // Update the modeoperationenum
        Modeoperationenum updatedModeoperationenum = modeoperationenumRepository.findById(modeoperationenum.getId()).get();
        // Disconnect from session so that the updates on updatedModeoperationenum are not directly saved in db
        em.detach(updatedModeoperationenum);
        updatedModeoperationenum
            .automatique(UPDATED_AUTOMATIQUE)
            .manuel(UPDATED_MANUEL);

        restModeoperationenumMockMvc.perform(put("/api/modeoperationenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedModeoperationenum)))
            .andExpect(status().isOk());

        // Validate the Modeoperationenum in the database
        List<Modeoperationenum> modeoperationenumList = modeoperationenumRepository.findAll();
        assertThat(modeoperationenumList).hasSize(databaseSizeBeforeUpdate);
        Modeoperationenum testModeoperationenum = modeoperationenumList.get(modeoperationenumList.size() - 1);
        assertThat(testModeoperationenum.getAutomatique()).isEqualTo(UPDATED_AUTOMATIQUE);
        assertThat(testModeoperationenum.getManuel()).isEqualTo(UPDATED_MANUEL);
    }

    @Test
    @Transactional
    public void updateNonExistingModeoperationenum() throws Exception {
        int databaseSizeBeforeUpdate = modeoperationenumRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModeoperationenumMockMvc.perform(put("/api/modeoperationenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeoperationenum)))
            .andExpect(status().isBadRequest());

        // Validate the Modeoperationenum in the database
        List<Modeoperationenum> modeoperationenumList = modeoperationenumRepository.findAll();
        assertThat(modeoperationenumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModeoperationenum() throws Exception {
        // Initialize the database
        modeoperationenumService.save(modeoperationenum);

        int databaseSizeBeforeDelete = modeoperationenumRepository.findAll().size();

        // Delete the modeoperationenum
        restModeoperationenumMockMvc.perform(delete("/api/modeoperationenums/{id}", modeoperationenum.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Modeoperationenum> modeoperationenumList = modeoperationenumRepository.findAll();
        assertThat(modeoperationenumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
