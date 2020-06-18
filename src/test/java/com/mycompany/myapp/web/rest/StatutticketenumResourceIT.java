package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Transaction2App;
import com.mycompany.myapp.domain.Statutticketenum;
import com.mycompany.myapp.repository.StatutticketenumRepository;
import com.mycompany.myapp.service.StatutticketenumService;
import com.mycompany.myapp.service.dto.StatutticketenumCriteria;
import com.mycompany.myapp.service.StatutticketenumQueryService;

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
 * Integration tests for the {@link StatutticketenumResource} REST controller.
 */
@SpringBootTest(classes = Transaction2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class StatutticketenumResourceIT {

    private static final String DEFAULT_VALIDE = "AAAAAAAAAA";
    private static final String UPDATED_VALIDE = "BBBBBBBBBB";

    private static final String DEFAULT_ANNULE = "AAAAAAAAAA";
    private static final String UPDATED_ANNULE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYE = "AAAAAAAAAA";
    private static final String UPDATED_PAYE = "BBBBBBBBBB";

    private static final String DEFAULT_REMBOURSE = "AAAAAAAAAA";
    private static final String UPDATED_REMBOURSE = "BBBBBBBBBB";

    @Autowired
    private StatutticketenumRepository statutticketenumRepository;

    @Autowired
    private StatutticketenumService statutticketenumService;

    @Autowired
    private StatutticketenumQueryService statutticketenumQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStatutticketenumMockMvc;

    private Statutticketenum statutticketenum;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Statutticketenum createEntity(EntityManager em) {
        Statutticketenum statutticketenum = new Statutticketenum()
            .valide(DEFAULT_VALIDE)
            .annule(DEFAULT_ANNULE)
            .paye(DEFAULT_PAYE)
            .rembourse(DEFAULT_REMBOURSE);
        return statutticketenum;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Statutticketenum createUpdatedEntity(EntityManager em) {
        Statutticketenum statutticketenum = new Statutticketenum()
            .valide(UPDATED_VALIDE)
            .annule(UPDATED_ANNULE)
            .paye(UPDATED_PAYE)
            .rembourse(UPDATED_REMBOURSE);
        return statutticketenum;
    }

    @BeforeEach
    public void initTest() {
        statutticketenum = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatutticketenum() throws Exception {
        int databaseSizeBeforeCreate = statutticketenumRepository.findAll().size();
        // Create the Statutticketenum
        restStatutticketenumMockMvc.perform(post("/api/statutticketenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statutticketenum)))
            .andExpect(status().isCreated());

        // Validate the Statutticketenum in the database
        List<Statutticketenum> statutticketenumList = statutticketenumRepository.findAll();
        assertThat(statutticketenumList).hasSize(databaseSizeBeforeCreate + 1);
        Statutticketenum testStatutticketenum = statutticketenumList.get(statutticketenumList.size() - 1);
        assertThat(testStatutticketenum.getValide()).isEqualTo(DEFAULT_VALIDE);
        assertThat(testStatutticketenum.getAnnule()).isEqualTo(DEFAULT_ANNULE);
        assertThat(testStatutticketenum.getPaye()).isEqualTo(DEFAULT_PAYE);
        assertThat(testStatutticketenum.getRembourse()).isEqualTo(DEFAULT_REMBOURSE);
    }

    @Test
    @Transactional
    public void createStatutticketenumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statutticketenumRepository.findAll().size();

        // Create the Statutticketenum with an existing ID
        statutticketenum.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatutticketenumMockMvc.perform(post("/api/statutticketenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statutticketenum)))
            .andExpect(status().isBadRequest());

        // Validate the Statutticketenum in the database
        List<Statutticketenum> statutticketenumList = statutticketenumRepository.findAll();
        assertThat(statutticketenumList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStatutticketenums() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList
        restStatutticketenumMockMvc.perform(get("/api/statutticketenums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statutticketenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].valide").value(hasItem(DEFAULT_VALIDE)))
            .andExpect(jsonPath("$.[*].annule").value(hasItem(DEFAULT_ANNULE)))
            .andExpect(jsonPath("$.[*].paye").value(hasItem(DEFAULT_PAYE)))
            .andExpect(jsonPath("$.[*].rembourse").value(hasItem(DEFAULT_REMBOURSE)));
    }
    
    @Test
    @Transactional
    public void getStatutticketenum() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get the statutticketenum
        restStatutticketenumMockMvc.perform(get("/api/statutticketenums/{id}", statutticketenum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(statutticketenum.getId().intValue()))
            .andExpect(jsonPath("$.valide").value(DEFAULT_VALIDE))
            .andExpect(jsonPath("$.annule").value(DEFAULT_ANNULE))
            .andExpect(jsonPath("$.paye").value(DEFAULT_PAYE))
            .andExpect(jsonPath("$.rembourse").value(DEFAULT_REMBOURSE));
    }


    @Test
    @Transactional
    public void getStatutticketenumsByIdFiltering() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        Long id = statutticketenum.getId();

        defaultStatutticketenumShouldBeFound("id.equals=" + id);
        defaultStatutticketenumShouldNotBeFound("id.notEquals=" + id);

        defaultStatutticketenumShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultStatutticketenumShouldNotBeFound("id.greaterThan=" + id);

        defaultStatutticketenumShouldBeFound("id.lessThanOrEqual=" + id);
        defaultStatutticketenumShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllStatutticketenumsByValideIsEqualToSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where valide equals to DEFAULT_VALIDE
        defaultStatutticketenumShouldBeFound("valide.equals=" + DEFAULT_VALIDE);

        // Get all the statutticketenumList where valide equals to UPDATED_VALIDE
        defaultStatutticketenumShouldNotBeFound("valide.equals=" + UPDATED_VALIDE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByValideIsNotEqualToSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where valide not equals to DEFAULT_VALIDE
        defaultStatutticketenumShouldNotBeFound("valide.notEquals=" + DEFAULT_VALIDE);

        // Get all the statutticketenumList where valide not equals to UPDATED_VALIDE
        defaultStatutticketenumShouldBeFound("valide.notEquals=" + UPDATED_VALIDE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByValideIsInShouldWork() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where valide in DEFAULT_VALIDE or UPDATED_VALIDE
        defaultStatutticketenumShouldBeFound("valide.in=" + DEFAULT_VALIDE + "," + UPDATED_VALIDE);

        // Get all the statutticketenumList where valide equals to UPDATED_VALIDE
        defaultStatutticketenumShouldNotBeFound("valide.in=" + UPDATED_VALIDE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByValideIsNullOrNotNull() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where valide is not null
        defaultStatutticketenumShouldBeFound("valide.specified=true");

        // Get all the statutticketenumList where valide is null
        defaultStatutticketenumShouldNotBeFound("valide.specified=false");
    }
                @Test
    @Transactional
    public void getAllStatutticketenumsByValideContainsSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where valide contains DEFAULT_VALIDE
        defaultStatutticketenumShouldBeFound("valide.contains=" + DEFAULT_VALIDE);

        // Get all the statutticketenumList where valide contains UPDATED_VALIDE
        defaultStatutticketenumShouldNotBeFound("valide.contains=" + UPDATED_VALIDE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByValideNotContainsSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where valide does not contain DEFAULT_VALIDE
        defaultStatutticketenumShouldNotBeFound("valide.doesNotContain=" + DEFAULT_VALIDE);

        // Get all the statutticketenumList where valide does not contain UPDATED_VALIDE
        defaultStatutticketenumShouldBeFound("valide.doesNotContain=" + UPDATED_VALIDE);
    }


    @Test
    @Transactional
    public void getAllStatutticketenumsByAnnuleIsEqualToSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where annule equals to DEFAULT_ANNULE
        defaultStatutticketenumShouldBeFound("annule.equals=" + DEFAULT_ANNULE);

        // Get all the statutticketenumList where annule equals to UPDATED_ANNULE
        defaultStatutticketenumShouldNotBeFound("annule.equals=" + UPDATED_ANNULE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByAnnuleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where annule not equals to DEFAULT_ANNULE
        defaultStatutticketenumShouldNotBeFound("annule.notEquals=" + DEFAULT_ANNULE);

        // Get all the statutticketenumList where annule not equals to UPDATED_ANNULE
        defaultStatutticketenumShouldBeFound("annule.notEquals=" + UPDATED_ANNULE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByAnnuleIsInShouldWork() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where annule in DEFAULT_ANNULE or UPDATED_ANNULE
        defaultStatutticketenumShouldBeFound("annule.in=" + DEFAULT_ANNULE + "," + UPDATED_ANNULE);

        // Get all the statutticketenumList where annule equals to UPDATED_ANNULE
        defaultStatutticketenumShouldNotBeFound("annule.in=" + UPDATED_ANNULE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByAnnuleIsNullOrNotNull() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where annule is not null
        defaultStatutticketenumShouldBeFound("annule.specified=true");

        // Get all the statutticketenumList where annule is null
        defaultStatutticketenumShouldNotBeFound("annule.specified=false");
    }
                @Test
    @Transactional
    public void getAllStatutticketenumsByAnnuleContainsSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where annule contains DEFAULT_ANNULE
        defaultStatutticketenumShouldBeFound("annule.contains=" + DEFAULT_ANNULE);

        // Get all the statutticketenumList where annule contains UPDATED_ANNULE
        defaultStatutticketenumShouldNotBeFound("annule.contains=" + UPDATED_ANNULE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByAnnuleNotContainsSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where annule does not contain DEFAULT_ANNULE
        defaultStatutticketenumShouldNotBeFound("annule.doesNotContain=" + DEFAULT_ANNULE);

        // Get all the statutticketenumList where annule does not contain UPDATED_ANNULE
        defaultStatutticketenumShouldBeFound("annule.doesNotContain=" + UPDATED_ANNULE);
    }


    @Test
    @Transactional
    public void getAllStatutticketenumsByPayeIsEqualToSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where paye equals to DEFAULT_PAYE
        defaultStatutticketenumShouldBeFound("paye.equals=" + DEFAULT_PAYE);

        // Get all the statutticketenumList where paye equals to UPDATED_PAYE
        defaultStatutticketenumShouldNotBeFound("paye.equals=" + UPDATED_PAYE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByPayeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where paye not equals to DEFAULT_PAYE
        defaultStatutticketenumShouldNotBeFound("paye.notEquals=" + DEFAULT_PAYE);

        // Get all the statutticketenumList where paye not equals to UPDATED_PAYE
        defaultStatutticketenumShouldBeFound("paye.notEquals=" + UPDATED_PAYE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByPayeIsInShouldWork() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where paye in DEFAULT_PAYE or UPDATED_PAYE
        defaultStatutticketenumShouldBeFound("paye.in=" + DEFAULT_PAYE + "," + UPDATED_PAYE);

        // Get all the statutticketenumList where paye equals to UPDATED_PAYE
        defaultStatutticketenumShouldNotBeFound("paye.in=" + UPDATED_PAYE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByPayeIsNullOrNotNull() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where paye is not null
        defaultStatutticketenumShouldBeFound("paye.specified=true");

        // Get all the statutticketenumList where paye is null
        defaultStatutticketenumShouldNotBeFound("paye.specified=false");
    }
                @Test
    @Transactional
    public void getAllStatutticketenumsByPayeContainsSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where paye contains DEFAULT_PAYE
        defaultStatutticketenumShouldBeFound("paye.contains=" + DEFAULT_PAYE);

        // Get all the statutticketenumList where paye contains UPDATED_PAYE
        defaultStatutticketenumShouldNotBeFound("paye.contains=" + UPDATED_PAYE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByPayeNotContainsSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where paye does not contain DEFAULT_PAYE
        defaultStatutticketenumShouldNotBeFound("paye.doesNotContain=" + DEFAULT_PAYE);

        // Get all the statutticketenumList where paye does not contain UPDATED_PAYE
        defaultStatutticketenumShouldBeFound("paye.doesNotContain=" + UPDATED_PAYE);
    }


    @Test
    @Transactional
    public void getAllStatutticketenumsByRembourseIsEqualToSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where rembourse equals to DEFAULT_REMBOURSE
        defaultStatutticketenumShouldBeFound("rembourse.equals=" + DEFAULT_REMBOURSE);

        // Get all the statutticketenumList where rembourse equals to UPDATED_REMBOURSE
        defaultStatutticketenumShouldNotBeFound("rembourse.equals=" + UPDATED_REMBOURSE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByRembourseIsNotEqualToSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where rembourse not equals to DEFAULT_REMBOURSE
        defaultStatutticketenumShouldNotBeFound("rembourse.notEquals=" + DEFAULT_REMBOURSE);

        // Get all the statutticketenumList where rembourse not equals to UPDATED_REMBOURSE
        defaultStatutticketenumShouldBeFound("rembourse.notEquals=" + UPDATED_REMBOURSE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByRembourseIsInShouldWork() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where rembourse in DEFAULT_REMBOURSE or UPDATED_REMBOURSE
        defaultStatutticketenumShouldBeFound("rembourse.in=" + DEFAULT_REMBOURSE + "," + UPDATED_REMBOURSE);

        // Get all the statutticketenumList where rembourse equals to UPDATED_REMBOURSE
        defaultStatutticketenumShouldNotBeFound("rembourse.in=" + UPDATED_REMBOURSE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByRembourseIsNullOrNotNull() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where rembourse is not null
        defaultStatutticketenumShouldBeFound("rembourse.specified=true");

        // Get all the statutticketenumList where rembourse is null
        defaultStatutticketenumShouldNotBeFound("rembourse.specified=false");
    }
                @Test
    @Transactional
    public void getAllStatutticketenumsByRembourseContainsSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where rembourse contains DEFAULT_REMBOURSE
        defaultStatutticketenumShouldBeFound("rembourse.contains=" + DEFAULT_REMBOURSE);

        // Get all the statutticketenumList where rembourse contains UPDATED_REMBOURSE
        defaultStatutticketenumShouldNotBeFound("rembourse.contains=" + UPDATED_REMBOURSE);
    }

    @Test
    @Transactional
    public void getAllStatutticketenumsByRembourseNotContainsSomething() throws Exception {
        // Initialize the database
        statutticketenumRepository.saveAndFlush(statutticketenum);

        // Get all the statutticketenumList where rembourse does not contain DEFAULT_REMBOURSE
        defaultStatutticketenumShouldNotBeFound("rembourse.doesNotContain=" + DEFAULT_REMBOURSE);

        // Get all the statutticketenumList where rembourse does not contain UPDATED_REMBOURSE
        defaultStatutticketenumShouldBeFound("rembourse.doesNotContain=" + UPDATED_REMBOURSE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultStatutticketenumShouldBeFound(String filter) throws Exception {
        restStatutticketenumMockMvc.perform(get("/api/statutticketenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statutticketenum.getId().intValue())))
            .andExpect(jsonPath("$.[*].valide").value(hasItem(DEFAULT_VALIDE)))
            .andExpect(jsonPath("$.[*].annule").value(hasItem(DEFAULT_ANNULE)))
            .andExpect(jsonPath("$.[*].paye").value(hasItem(DEFAULT_PAYE)))
            .andExpect(jsonPath("$.[*].rembourse").value(hasItem(DEFAULT_REMBOURSE)));

        // Check, that the count call also returns 1
        restStatutticketenumMockMvc.perform(get("/api/statutticketenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultStatutticketenumShouldNotBeFound(String filter) throws Exception {
        restStatutticketenumMockMvc.perform(get("/api/statutticketenums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restStatutticketenumMockMvc.perform(get("/api/statutticketenums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingStatutticketenum() throws Exception {
        // Get the statutticketenum
        restStatutticketenumMockMvc.perform(get("/api/statutticketenums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatutticketenum() throws Exception {
        // Initialize the database
        statutticketenumService.save(statutticketenum);

        int databaseSizeBeforeUpdate = statutticketenumRepository.findAll().size();

        // Update the statutticketenum
        Statutticketenum updatedStatutticketenum = statutticketenumRepository.findById(statutticketenum.getId()).get();
        // Disconnect from session so that the updates on updatedStatutticketenum are not directly saved in db
        em.detach(updatedStatutticketenum);
        updatedStatutticketenum
            .valide(UPDATED_VALIDE)
            .annule(UPDATED_ANNULE)
            .paye(UPDATED_PAYE)
            .rembourse(UPDATED_REMBOURSE);

        restStatutticketenumMockMvc.perform(put("/api/statutticketenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedStatutticketenum)))
            .andExpect(status().isOk());

        // Validate the Statutticketenum in the database
        List<Statutticketenum> statutticketenumList = statutticketenumRepository.findAll();
        assertThat(statutticketenumList).hasSize(databaseSizeBeforeUpdate);
        Statutticketenum testStatutticketenum = statutticketenumList.get(statutticketenumList.size() - 1);
        assertThat(testStatutticketenum.getValide()).isEqualTo(UPDATED_VALIDE);
        assertThat(testStatutticketenum.getAnnule()).isEqualTo(UPDATED_ANNULE);
        assertThat(testStatutticketenum.getPaye()).isEqualTo(UPDATED_PAYE);
        assertThat(testStatutticketenum.getRembourse()).isEqualTo(UPDATED_REMBOURSE);
    }

    @Test
    @Transactional
    public void updateNonExistingStatutticketenum() throws Exception {
        int databaseSizeBeforeUpdate = statutticketenumRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatutticketenumMockMvc.perform(put("/api/statutticketenums")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(statutticketenum)))
            .andExpect(status().isBadRequest());

        // Validate the Statutticketenum in the database
        List<Statutticketenum> statutticketenumList = statutticketenumRepository.findAll();
        assertThat(statutticketenumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatutticketenum() throws Exception {
        // Initialize the database
        statutticketenumService.save(statutticketenum);

        int databaseSizeBeforeDelete = statutticketenumRepository.findAll().size();

        // Delete the statutticketenum
        restStatutticketenumMockMvc.perform(delete("/api/statutticketenums/{id}", statutticketenum.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Statutticketenum> statutticketenumList = statutticketenumRepository.findAll();
        assertThat(statutticketenumList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
