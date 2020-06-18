package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Transaction2App;
import com.mycompany.myapp.domain.Typeannulationenum;
import com.mycompany.myapp.repository.TypeannulationenumRepository;
import com.mycompany.myapp.service.TypeannulationenumService;
import com.mycompany.myapp.service.dto.TypeannulationenumCriteria;
import com.mycompany.myapp.service.TypeannulationenumQueryService;

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
 * Integration tests for the {@link TypeannulationenumResource} REST controller.
 */
@SpringBootTest(classes = Transaction2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeannulationenumResourceIT {

    private static final String DEFAULT_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT = "BBBBBBBBBB";

    private static final String DEFAULT_MACHINE = "AAAAAAAAAA";
    private static final String UPDATED_MACHINE = "BBBBBBBBBB";

    private static final String DEFAULT_EXCEPTIONNELLE = "AAAAAAAAAA";
    private static final String UPDATED_EXCEPTIONNELLE = "BBBBBBBBBB";

    private static final String DEFAULT_SYSTEME = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEME = "BBBBBBBBBB";

    @Autowired
    private TypeannulationenumRepository typeannulationenumRepository;

    @Autowired
    private TypeannulationenumService typeannulationenumService;

    @Autowired
    private TypeannulationenumQueryService typeannulationenumQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeannulationenumMockMvc;

    private Typeannulationenum typeannulationenum;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Typeannulationenum createEntity(EntityManager em) {
        Typeannulationenum typeannulationenum = new Typeannulationenum()
            .client(DEFAULT_CLIENT)
            .machine(DEFAULT_MACHINE)
            .exceptionnelle(DEFAULT_EXCEPTIONNELLE)
            .systeme(DEFAULT_SYSTEME);
        return typeannulationenum;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Typeannulationenum createUpdatedEntity(EntityManager em) {
        Typeannulationenum typeannulationenum = new Typeannulationenum()
            .client(UPDATED_CLIENT)
            .machine(UPDATED_MACHINE)
            .exceptionnelle(UPDATED_EXCEPTIONNELLE)
            .systeme(UPDATED_SYSTEME);
        return typeannulationenum;
    }

    @BeforeEach
    public void initTest() {
        typeannulationenum = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeannulationenum() throws Exception {
        int databaseSizeBeforeCreate = typeannulationenumRepository.findAll().size();
        // Create the Typeannulationenum
        restTypeannulationenumMockMvc.perform(post("/api/typeannulationenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeannulationenum)))
            .andExpect(status().isCreated());

        // Validate the Typeannulationenum in the database
        List<Typeannulationenum> typeannulationenumList = typeannulationenumRepository.findAll();
        assertThat(typeannulationenumList).hasSize(databaseSizeBeforeCreate + 1);
        Typeannulationenum testTypeannulationenum = typeannulationenumList.get(typeannulationenumList.size() - 1);
        assertThat(testTypeannulationenum.getClient()).isEqualTo(DEFAULT_CLIENT);
        assertThat(testTypeannulationenum.getMachine()).isEqualTo(DEFAULT_MACHINE);
        assertThat(testTypeannulationenum.getExceptionnelle()).isEqualTo(DEFAULT_EXCEPTIONNELLE);
        assertThat(testTypeannulationenum.getSysteme()).isEqualTo(DEFAULT_SYSTEME);
    }

    @Test
    @Transactional
    public void createTypeannulationenumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeannulationenumRepository.findAll().size();

        // Create the Typeannulationenum with an existing ID
        typeannulationenum.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeannulationenumMockMvc.perform(post("/api/typeannulationenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeannulationenum)))
            .andExpect(status().isBadRequest());

        // Validate the Typeannulationenum in the database
        List<Typeannulationenum> typeannulationenumList = typeannulationenumRepository.findAll();
        assertThat(typeannulationenumList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeannulationenums() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList
        restTypeannulationenumMockMvc.perform(get("/api/typeannulationenums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeannulationenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].client").value(hasItem(DEFAULT_CLIENT)))
            .andExpect(jsonPath("$.[*].machine").value(hasItem(DEFAULT_MACHINE)))
            .andExpect(jsonPath("$.[*].exceptionnelle").value(hasItem(DEFAULT_EXCEPTIONNELLE)))
            .andExpect(jsonPath("$.[*].systeme").value(hasItem(DEFAULT_SYSTEME)));
    }
    
    @Test
    @Transactional
    public void getTypeannulationenum() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get the typeannulationenum
        restTypeannulationenumMockMvc.perform(get("/api/typeannulationenums/{id}", typeannulationenum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeannulationenum.getId().intValue()))
            .andExpect(jsonPath("$.client").value(DEFAULT_CLIENT))
            .andExpect(jsonPath("$.machine").value(DEFAULT_MACHINE))
            .andExpect(jsonPath("$.exceptionnelle").value(DEFAULT_EXCEPTIONNELLE))
            .andExpect(jsonPath("$.systeme").value(DEFAULT_SYSTEME));
    }


    @Test
    @Transactional
    public void getTypeannulationenumsByIdFiltering() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        Long id = typeannulationenum.getId();

        defaultTypeannulationenumShouldBeFound("id.equals=" + id);
        defaultTypeannulationenumShouldNotBeFound("id.notEquals=" + id);

        defaultTypeannulationenumShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTypeannulationenumShouldNotBeFound("id.greaterThan=" + id);

        defaultTypeannulationenumShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTypeannulationenumShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTypeannulationenumsByClientIsEqualToSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where client equals to DEFAULT_CLIENT
        defaultTypeannulationenumShouldBeFound("client.equals=" + DEFAULT_CLIENT);

        // Get all the typeannulationenumList where client equals to UPDATED_CLIENT
        defaultTypeannulationenumShouldNotBeFound("client.equals=" + UPDATED_CLIENT);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsByClientIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where client not equals to DEFAULT_CLIENT
        defaultTypeannulationenumShouldNotBeFound("client.notEquals=" + DEFAULT_CLIENT);

        // Get all the typeannulationenumList where client not equals to UPDATED_CLIENT
        defaultTypeannulationenumShouldBeFound("client.notEquals=" + UPDATED_CLIENT);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsByClientIsInShouldWork() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where client in DEFAULT_CLIENT or UPDATED_CLIENT
        defaultTypeannulationenumShouldBeFound("client.in=" + DEFAULT_CLIENT + "," + UPDATED_CLIENT);

        // Get all the typeannulationenumList where client equals to UPDATED_CLIENT
        defaultTypeannulationenumShouldNotBeFound("client.in=" + UPDATED_CLIENT);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsByClientIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where client is not null
        defaultTypeannulationenumShouldBeFound("client.specified=true");

        // Get all the typeannulationenumList where client is null
        defaultTypeannulationenumShouldNotBeFound("client.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeannulationenumsByClientContainsSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where client contains DEFAULT_CLIENT
        defaultTypeannulationenumShouldBeFound("client.contains=" + DEFAULT_CLIENT);

        // Get all the typeannulationenumList where client contains UPDATED_CLIENT
        defaultTypeannulationenumShouldNotBeFound("client.contains=" + UPDATED_CLIENT);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsByClientNotContainsSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where client does not contain DEFAULT_CLIENT
        defaultTypeannulationenumShouldNotBeFound("client.doesNotContain=" + DEFAULT_CLIENT);

        // Get all the typeannulationenumList where client does not contain UPDATED_CLIENT
        defaultTypeannulationenumShouldBeFound("client.doesNotContain=" + UPDATED_CLIENT);
    }


    @Test
    @Transactional
    public void getAllTypeannulationenumsByMachineIsEqualToSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where machine equals to DEFAULT_MACHINE
        defaultTypeannulationenumShouldBeFound("machine.equals=" + DEFAULT_MACHINE);

        // Get all the typeannulationenumList where machine equals to UPDATED_MACHINE
        defaultTypeannulationenumShouldNotBeFound("machine.equals=" + UPDATED_MACHINE);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsByMachineIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where machine not equals to DEFAULT_MACHINE
        defaultTypeannulationenumShouldNotBeFound("machine.notEquals=" + DEFAULT_MACHINE);

        // Get all the typeannulationenumList where machine not equals to UPDATED_MACHINE
        defaultTypeannulationenumShouldBeFound("machine.notEquals=" + UPDATED_MACHINE);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsByMachineIsInShouldWork() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where machine in DEFAULT_MACHINE or UPDATED_MACHINE
        defaultTypeannulationenumShouldBeFound("machine.in=" + DEFAULT_MACHINE + "," + UPDATED_MACHINE);

        // Get all the typeannulationenumList where machine equals to UPDATED_MACHINE
        defaultTypeannulationenumShouldNotBeFound("machine.in=" + UPDATED_MACHINE);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsByMachineIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where machine is not null
        defaultTypeannulationenumShouldBeFound("machine.specified=true");

        // Get all the typeannulationenumList where machine is null
        defaultTypeannulationenumShouldNotBeFound("machine.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeannulationenumsByMachineContainsSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where machine contains DEFAULT_MACHINE
        defaultTypeannulationenumShouldBeFound("machine.contains=" + DEFAULT_MACHINE);

        // Get all the typeannulationenumList where machine contains UPDATED_MACHINE
        defaultTypeannulationenumShouldNotBeFound("machine.contains=" + UPDATED_MACHINE);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsByMachineNotContainsSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where machine does not contain DEFAULT_MACHINE
        defaultTypeannulationenumShouldNotBeFound("machine.doesNotContain=" + DEFAULT_MACHINE);

        // Get all the typeannulationenumList where machine does not contain UPDATED_MACHINE
        defaultTypeannulationenumShouldBeFound("machine.doesNotContain=" + UPDATED_MACHINE);
    }


    @Test
    @Transactional
    public void getAllTypeannulationenumsByExceptionnelleIsEqualToSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where exceptionnelle equals to DEFAULT_EXCEPTIONNELLE
        defaultTypeannulationenumShouldBeFound("exceptionnelle.equals=" + DEFAULT_EXCEPTIONNELLE);

        // Get all the typeannulationenumList where exceptionnelle equals to UPDATED_EXCEPTIONNELLE
        defaultTypeannulationenumShouldNotBeFound("exceptionnelle.equals=" + UPDATED_EXCEPTIONNELLE);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsByExceptionnelleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where exceptionnelle not equals to DEFAULT_EXCEPTIONNELLE
        defaultTypeannulationenumShouldNotBeFound("exceptionnelle.notEquals=" + DEFAULT_EXCEPTIONNELLE);

        // Get all the typeannulationenumList where exceptionnelle not equals to UPDATED_EXCEPTIONNELLE
        defaultTypeannulationenumShouldBeFound("exceptionnelle.notEquals=" + UPDATED_EXCEPTIONNELLE);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsByExceptionnelleIsInShouldWork() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where exceptionnelle in DEFAULT_EXCEPTIONNELLE or UPDATED_EXCEPTIONNELLE
        defaultTypeannulationenumShouldBeFound("exceptionnelle.in=" + DEFAULT_EXCEPTIONNELLE + "," + UPDATED_EXCEPTIONNELLE);

        // Get all the typeannulationenumList where exceptionnelle equals to UPDATED_EXCEPTIONNELLE
        defaultTypeannulationenumShouldNotBeFound("exceptionnelle.in=" + UPDATED_EXCEPTIONNELLE);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsByExceptionnelleIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where exceptionnelle is not null
        defaultTypeannulationenumShouldBeFound("exceptionnelle.specified=true");

        // Get all the typeannulationenumList where exceptionnelle is null
        defaultTypeannulationenumShouldNotBeFound("exceptionnelle.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeannulationenumsByExceptionnelleContainsSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where exceptionnelle contains DEFAULT_EXCEPTIONNELLE
        defaultTypeannulationenumShouldBeFound("exceptionnelle.contains=" + DEFAULT_EXCEPTIONNELLE);

        // Get all the typeannulationenumList where exceptionnelle contains UPDATED_EXCEPTIONNELLE
        defaultTypeannulationenumShouldNotBeFound("exceptionnelle.contains=" + UPDATED_EXCEPTIONNELLE);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsByExceptionnelleNotContainsSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where exceptionnelle does not contain DEFAULT_EXCEPTIONNELLE
        defaultTypeannulationenumShouldNotBeFound("exceptionnelle.doesNotContain=" + DEFAULT_EXCEPTIONNELLE);

        // Get all the typeannulationenumList where exceptionnelle does not contain UPDATED_EXCEPTIONNELLE
        defaultTypeannulationenumShouldBeFound("exceptionnelle.doesNotContain=" + UPDATED_EXCEPTIONNELLE);
    }


    @Test
    @Transactional
    public void getAllTypeannulationenumsBySystemeIsEqualToSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where systeme equals to DEFAULT_SYSTEME
        defaultTypeannulationenumShouldBeFound("systeme.equals=" + DEFAULT_SYSTEME);

        // Get all the typeannulationenumList where systeme equals to UPDATED_SYSTEME
        defaultTypeannulationenumShouldNotBeFound("systeme.equals=" + UPDATED_SYSTEME);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsBySystemeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where systeme not equals to DEFAULT_SYSTEME
        defaultTypeannulationenumShouldNotBeFound("systeme.notEquals=" + DEFAULT_SYSTEME);

        // Get all the typeannulationenumList where systeme not equals to UPDATED_SYSTEME
        defaultTypeannulationenumShouldBeFound("systeme.notEquals=" + UPDATED_SYSTEME);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsBySystemeIsInShouldWork() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where systeme in DEFAULT_SYSTEME or UPDATED_SYSTEME
        defaultTypeannulationenumShouldBeFound("systeme.in=" + DEFAULT_SYSTEME + "," + UPDATED_SYSTEME);

        // Get all the typeannulationenumList where systeme equals to UPDATED_SYSTEME
        defaultTypeannulationenumShouldNotBeFound("systeme.in=" + UPDATED_SYSTEME);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsBySystemeIsNullOrNotNull() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where systeme is not null
        defaultTypeannulationenumShouldBeFound("systeme.specified=true");

        // Get all the typeannulationenumList where systeme is null
        defaultTypeannulationenumShouldNotBeFound("systeme.specified=false");
    }
                @Test
    @Transactional
    public void getAllTypeannulationenumsBySystemeContainsSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where systeme contains DEFAULT_SYSTEME
        defaultTypeannulationenumShouldBeFound("systeme.contains=" + DEFAULT_SYSTEME);

        // Get all the typeannulationenumList where systeme contains UPDATED_SYSTEME
        defaultTypeannulationenumShouldNotBeFound("systeme.contains=" + UPDATED_SYSTEME);
    }

    @Test
    @Transactional
    public void getAllTypeannulationenumsBySystemeNotContainsSomething() throws Exception {
        // Initialize the database
        typeannulationenumRepository.saveAndFlush(typeannulationenum);

        // Get all the typeannulationenumList where systeme does not contain DEFAULT_SYSTEME
        defaultTypeannulationenumShouldNotBeFound("systeme.doesNotContain=" + DEFAULT_SYSTEME);

        // Get all the typeannulationenumList where systeme does not contain UPDATED_SYSTEME
        defaultTypeannulationenumShouldBeFound("systeme.doesNotContain=" + UPDATED_SYSTEME);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTypeannulationenumShouldBeFound(String filter) throws Exception {
        restTypeannulationenumMockMvc.perform(get("/api/typeannulationenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeannulationenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].client").value(hasItem(DEFAULT_CLIENT)))
            .andExpect(jsonPath("$.[*].machine").value(hasItem(DEFAULT_MACHINE)))
            .andExpect(jsonPath("$.[*].exceptionnelle").value(hasItem(DEFAULT_EXCEPTIONNELLE)))
            .andExpect(jsonPath("$.[*].systeme").value(hasItem(DEFAULT_SYSTEME)));

        // Check, that the count call also returns 1
        restTypeannulationenumMockMvc.perform(get("/api/typeannulationenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTypeannulationenumShouldNotBeFound(String filter) throws Exception {
        restTypeannulationenumMockMvc.perform(get("/api/typeannulationenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTypeannulationenumMockMvc.perform(get("/api/typeannulationenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingTypeannulationenum() throws Exception {
        // Get the typeannulationenum
        restTypeannulationenumMockMvc.perform(get("/api/typeannulationenums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeannulationenum() throws Exception {
        // Initialize the database
        typeannulationenumService.save(typeannulationenum);

        int databaseSizeBeforeUpdate = typeannulationenumRepository.findAll().size();

        // Update the typeannulationenum
        Typeannulationenum updatedTypeannulationenum = typeannulationenumRepository.findById(typeannulationenum.getId()).get();
        // Disconnect from session so that the updates on updatedTypeannulationenum are not directly saved in db
        em.detach(updatedTypeannulationenum);
        updatedTypeannulationenum
            .client(UPDATED_CLIENT)
            .machine(UPDATED_MACHINE)
            .exceptionnelle(UPDATED_EXCEPTIONNELLE)
            .systeme(UPDATED_SYSTEME);

        restTypeannulationenumMockMvc.perform(put("/api/typeannulationenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeannulationenum)))
            .andExpect(status().isOk());

        // Validate the Typeannulationenum in the database
        List<Typeannulationenum> typeannulationenumList = typeannulationenumRepository.findAll();
        assertThat(typeannulationenumList).hasSize(databaseSizeBeforeUpdate);
        Typeannulationenum testTypeannulationenum = typeannulationenumList.get(typeannulationenumList.size() - 1);
        assertThat(testTypeannulationenum.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testTypeannulationenum.getMachine()).isEqualTo(UPDATED_MACHINE);
        assertThat(testTypeannulationenum.getExceptionnelle()).isEqualTo(UPDATED_EXCEPTIONNELLE);
        assertThat(testTypeannulationenum.getSysteme()).isEqualTo(UPDATED_SYSTEME);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeannulationenum() throws Exception {
        int databaseSizeBeforeUpdate = typeannulationenumRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeannulationenumMockMvc.perform(put("/api/typeannulationenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeannulationenum)))
            .andExpect(status().isBadRequest());

        // Validate the Typeannulationenum in the database
        List<Typeannulationenum> typeannulationenumList = typeannulationenumRepository.findAll();
        assertThat(typeannulationenumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeannulationenum() throws Exception {
        // Initialize the database
        typeannulationenumService.save(typeannulationenum);

        int databaseSizeBeforeDelete = typeannulationenumRepository.findAll().size();

        // Delete the typeannulationenum
        restTypeannulationenumMockMvc.perform(delete("/api/typeannulationenums/{id}", typeannulationenum.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Typeannulationenum> typeannulationenumList = typeannulationenumRepository.findAll();
        assertThat(typeannulationenumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
