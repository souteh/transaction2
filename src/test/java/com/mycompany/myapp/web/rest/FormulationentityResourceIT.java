package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Transaction2App;
import com.mycompany.myapp.domain.Formulationentity;
import com.mycompany.myapp.repository.FormulationentityRepository;
import com.mycompany.myapp.service.FormulationentityService;
import com.mycompany.myapp.service.dto.FormulationentityCriteria;
import com.mycompany.myapp.service.FormulationentityQueryService;

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
 * Integration tests for the {@link FormulationentityResource} REST controller.
 */
@SpringBootTest(classes = Transaction2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class FormulationentityResourceIT {

    private static final Long DEFAULT_IDENTIFIANTCONC = 1L;
    private static final Long UPDATED_IDENTIFIANTCONC = 2L;
    private static final Long SMALLER_IDENTIFIANTCONC = 1L - 1L;

    private static final Long DEFAULT_IDFORMULATION = 1L;
    private static final Long UPDATED_IDFORMULATION = 2L;
    private static final Long SMALLER_IDFORMULATION = 1L - 1L;

    private static final String DEFAULT_CODEPRODUIT = "AAAAAAAAAA";
    private static final String UPDATED_CODEPRODUIT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FORMCOMPLETE = false;
    private static final Boolean UPDATED_FORMCOMPLETE = true;

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_MISECOMB = "AAAAAAAAAA";
    private static final String UPDATED_MISECOMB = "BBBBBBBBBB";

    private static final Integer DEFAULT_MISETOTALE = 1;
    private static final Integer UPDATED_MISETOTALE = 2;
    private static final Integer SMALLER_MISETOTALE = 1 - 1;

    private static final Integer DEFAULT_NUMFORM = 1;
    private static final Integer UPDATED_NUMFORM = 2;
    private static final Integer SMALLER_NUMFORM = 1 - 1;

    @Autowired
    private FormulationentityRepository formulationentityRepository;

    @Autowired
    private FormulationentityService formulationentityService;

    @Autowired
    private FormulationentityQueryService formulationentityQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormulationentityMockMvc;

    private Formulationentity formulationentity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formulationentity createEntity(EntityManager em) {
        Formulationentity formulationentity = new Formulationentity()
            .identifiantconc(DEFAULT_IDENTIFIANTCONC)
            .idformulation(DEFAULT_IDFORMULATION)
            .codeproduit(DEFAULT_CODEPRODUIT)
            .formcomplete(DEFAULT_FORMCOMPLETE)
            .designation(DEFAULT_DESIGNATION)
            .misecomb(DEFAULT_MISECOMB)
            .misetotale(DEFAULT_MISETOTALE)
            .numform(DEFAULT_NUMFORM);
        return formulationentity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formulationentity createUpdatedEntity(EntityManager em) {
        Formulationentity formulationentity = new Formulationentity()
            .identifiantconc(UPDATED_IDENTIFIANTCONC)
            .idformulation(UPDATED_IDFORMULATION)
            .codeproduit(UPDATED_CODEPRODUIT)
            .formcomplete(UPDATED_FORMCOMPLETE)
            .designation(UPDATED_DESIGNATION)
            .misecomb(UPDATED_MISECOMB)
            .misetotale(UPDATED_MISETOTALE)
            .numform(UPDATED_NUMFORM);
        return formulationentity;
    }

    @BeforeEach
    public void initTest() {
        formulationentity = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormulationentity() throws Exception {
        int databaseSizeBeforeCreate = formulationentityRepository.findAll().size();
        // Create the Formulationentity
        restFormulationentityMockMvc.perform(post("/api/formulationentities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formulationentity)))
            .andExpect(status().isCreated());

        // Validate the Formulationentity in the database
        List<Formulationentity> formulationentityList = formulationentityRepository.findAll();
        assertThat(formulationentityList).hasSize(databaseSizeBeforeCreate + 1);
        Formulationentity testFormulationentity = formulationentityList.get(formulationentityList.size() - 1);
        assertThat(testFormulationentity.getIdentifiantconc()).isEqualTo(DEFAULT_IDENTIFIANTCONC);
        assertThat(testFormulationentity.getIdformulation()).isEqualTo(DEFAULT_IDFORMULATION);
        assertThat(testFormulationentity.getCodeproduit()).isEqualTo(DEFAULT_CODEPRODUIT);
        assertThat(testFormulationentity.isFormcomplete()).isEqualTo(DEFAULT_FORMCOMPLETE);
        assertThat(testFormulationentity.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testFormulationentity.getMisecomb()).isEqualTo(DEFAULT_MISECOMB);
        assertThat(testFormulationentity.getMisetotale()).isEqualTo(DEFAULT_MISETOTALE);
        assertThat(testFormulationentity.getNumform()).isEqualTo(DEFAULT_NUMFORM);
    }

    @Test
    @Transactional
    public void createFormulationentityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formulationentityRepository.findAll().size();

        // Create the Formulationentity with an existing ID
        formulationentity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormulationentityMockMvc.perform(post("/api/formulationentities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formulationentity)))
            .andExpect(status().isBadRequest());

        // Validate the Formulationentity in the database
        List<Formulationentity> formulationentityList = formulationentityRepository.findAll();
        assertThat(formulationentityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdentifiantconcIsRequired() throws Exception {
        int databaseSizeBeforeTest = formulationentityRepository.findAll().size();
        // set the field null
        formulationentity.setIdentifiantconc(null);

        // Create the Formulationentity, which fails.


        restFormulationentityMockMvc.perform(post("/api/formulationentities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formulationentity)))
            .andExpect(status().isBadRequest());

        List<Formulationentity> formulationentityList = formulationentityRepository.findAll();
        assertThat(formulationentityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFormulationentities() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList
        restFormulationentityMockMvc.perform(get("/api/formulationentities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formulationentity.getId().intValue())))
            .andExpect(jsonPath("$.[*].identifiantconc").value(hasItem(DEFAULT_IDENTIFIANTCONC.intValue())))
            .andExpect(jsonPath("$.[*].idformulation").value(hasItem(DEFAULT_IDFORMULATION.intValue())))
            .andExpect(jsonPath("$.[*].codeproduit").value(hasItem(DEFAULT_CODEPRODUIT)))
            .andExpect(jsonPath("$.[*].formcomplete").value(hasItem(DEFAULT_FORMCOMPLETE.booleanValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].misecomb").value(hasItem(DEFAULT_MISECOMB)))
            .andExpect(jsonPath("$.[*].misetotale").value(hasItem(DEFAULT_MISETOTALE)))
            .andExpect(jsonPath("$.[*].numform").value(hasItem(DEFAULT_NUMFORM)));
    }
    
    @Test
    @Transactional
    public void getFormulationentity() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get the formulationentity
        restFormulationentityMockMvc.perform(get("/api/formulationentities/{id}", formulationentity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formulationentity.getId().intValue()))
            .andExpect(jsonPath("$.identifiantconc").value(DEFAULT_IDENTIFIANTCONC.intValue()))
            .andExpect(jsonPath("$.idformulation").value(DEFAULT_IDFORMULATION.intValue()))
            .andExpect(jsonPath("$.codeproduit").value(DEFAULT_CODEPRODUIT))
            .andExpect(jsonPath("$.formcomplete").value(DEFAULT_FORMCOMPLETE.booleanValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION))
            .andExpect(jsonPath("$.misecomb").value(DEFAULT_MISECOMB))
            .andExpect(jsonPath("$.misetotale").value(DEFAULT_MISETOTALE))
            .andExpect(jsonPath("$.numform").value(DEFAULT_NUMFORM));
    }


    @Test
    @Transactional
    public void getFormulationentitiesByIdFiltering() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        Long id = formulationentity.getId();

        defaultFormulationentityShouldBeFound("id.equals=" + id);
        defaultFormulationentityShouldNotBeFound("id.notEquals=" + id);

        defaultFormulationentityShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultFormulationentityShouldNotBeFound("id.greaterThan=" + id);

        defaultFormulationentityShouldBeFound("id.lessThanOrEqual=" + id);
        defaultFormulationentityShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllFormulationentitiesByIdentifiantconcIsEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where identifiantconc equals to DEFAULT_IDENTIFIANTCONC
        defaultFormulationentityShouldBeFound("identifiantconc.equals=" + DEFAULT_IDENTIFIANTCONC);

        // Get all the formulationentityList where identifiantconc equals to UPDATED_IDENTIFIANTCONC
        defaultFormulationentityShouldNotBeFound("identifiantconc.equals=" + UPDATED_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdentifiantconcIsNotEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where identifiantconc not equals to DEFAULT_IDENTIFIANTCONC
        defaultFormulationentityShouldNotBeFound("identifiantconc.notEquals=" + DEFAULT_IDENTIFIANTCONC);

        // Get all the formulationentityList where identifiantconc not equals to UPDATED_IDENTIFIANTCONC
        defaultFormulationentityShouldBeFound("identifiantconc.notEquals=" + UPDATED_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdentifiantconcIsInShouldWork() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where identifiantconc in DEFAULT_IDENTIFIANTCONC or UPDATED_IDENTIFIANTCONC
        defaultFormulationentityShouldBeFound("identifiantconc.in=" + DEFAULT_IDENTIFIANTCONC + "," + UPDATED_IDENTIFIANTCONC);

        // Get all the formulationentityList where identifiantconc equals to UPDATED_IDENTIFIANTCONC
        defaultFormulationentityShouldNotBeFound("identifiantconc.in=" + UPDATED_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdentifiantconcIsNullOrNotNull() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where identifiantconc is not null
        defaultFormulationentityShouldBeFound("identifiantconc.specified=true");

        // Get all the formulationentityList where identifiantconc is null
        defaultFormulationentityShouldNotBeFound("identifiantconc.specified=false");
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdentifiantconcIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where identifiantconc is greater than or equal to DEFAULT_IDENTIFIANTCONC
        defaultFormulationentityShouldBeFound("identifiantconc.greaterThanOrEqual=" + DEFAULT_IDENTIFIANTCONC);

        // Get all the formulationentityList where identifiantconc is greater than or equal to UPDATED_IDENTIFIANTCONC
        defaultFormulationentityShouldNotBeFound("identifiantconc.greaterThanOrEqual=" + UPDATED_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdentifiantconcIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where identifiantconc is less than or equal to DEFAULT_IDENTIFIANTCONC
        defaultFormulationentityShouldBeFound("identifiantconc.lessThanOrEqual=" + DEFAULT_IDENTIFIANTCONC);

        // Get all the formulationentityList where identifiantconc is less than or equal to SMALLER_IDENTIFIANTCONC
        defaultFormulationentityShouldNotBeFound("identifiantconc.lessThanOrEqual=" + SMALLER_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdentifiantconcIsLessThanSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where identifiantconc is less than DEFAULT_IDENTIFIANTCONC
        defaultFormulationentityShouldNotBeFound("identifiantconc.lessThan=" + DEFAULT_IDENTIFIANTCONC);

        // Get all the formulationentityList where identifiantconc is less than UPDATED_IDENTIFIANTCONC
        defaultFormulationentityShouldBeFound("identifiantconc.lessThan=" + UPDATED_IDENTIFIANTCONC);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdentifiantconcIsGreaterThanSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where identifiantconc is greater than DEFAULT_IDENTIFIANTCONC
        defaultFormulationentityShouldNotBeFound("identifiantconc.greaterThan=" + DEFAULT_IDENTIFIANTCONC);

        // Get all the formulationentityList where identifiantconc is greater than SMALLER_IDENTIFIANTCONC
        defaultFormulationentityShouldBeFound("identifiantconc.greaterThan=" + SMALLER_IDENTIFIANTCONC);
    }


    @Test
    @Transactional
    public void getAllFormulationentitiesByIdformulationIsEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where idformulation equals to DEFAULT_IDFORMULATION
        defaultFormulationentityShouldBeFound("idformulation.equals=" + DEFAULT_IDFORMULATION);

        // Get all the formulationentityList where idformulation equals to UPDATED_IDFORMULATION
        defaultFormulationentityShouldNotBeFound("idformulation.equals=" + UPDATED_IDFORMULATION);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdformulationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where idformulation not equals to DEFAULT_IDFORMULATION
        defaultFormulationentityShouldNotBeFound("idformulation.notEquals=" + DEFAULT_IDFORMULATION);

        // Get all the formulationentityList where idformulation not equals to UPDATED_IDFORMULATION
        defaultFormulationentityShouldBeFound("idformulation.notEquals=" + UPDATED_IDFORMULATION);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdformulationIsInShouldWork() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where idformulation in DEFAULT_IDFORMULATION or UPDATED_IDFORMULATION
        defaultFormulationentityShouldBeFound("idformulation.in=" + DEFAULT_IDFORMULATION + "," + UPDATED_IDFORMULATION);

        // Get all the formulationentityList where idformulation equals to UPDATED_IDFORMULATION
        defaultFormulationentityShouldNotBeFound("idformulation.in=" + UPDATED_IDFORMULATION);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdformulationIsNullOrNotNull() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where idformulation is not null
        defaultFormulationentityShouldBeFound("idformulation.specified=true");

        // Get all the formulationentityList where idformulation is null
        defaultFormulationentityShouldNotBeFound("idformulation.specified=false");
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdformulationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where idformulation is greater than or equal to DEFAULT_IDFORMULATION
        defaultFormulationentityShouldBeFound("idformulation.greaterThanOrEqual=" + DEFAULT_IDFORMULATION);

        // Get all the formulationentityList where idformulation is greater than or equal to UPDATED_IDFORMULATION
        defaultFormulationentityShouldNotBeFound("idformulation.greaterThanOrEqual=" + UPDATED_IDFORMULATION);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdformulationIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where idformulation is less than or equal to DEFAULT_IDFORMULATION
        defaultFormulationentityShouldBeFound("idformulation.lessThanOrEqual=" + DEFAULT_IDFORMULATION);

        // Get all the formulationentityList where idformulation is less than or equal to SMALLER_IDFORMULATION
        defaultFormulationentityShouldNotBeFound("idformulation.lessThanOrEqual=" + SMALLER_IDFORMULATION);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdformulationIsLessThanSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where idformulation is less than DEFAULT_IDFORMULATION
        defaultFormulationentityShouldNotBeFound("idformulation.lessThan=" + DEFAULT_IDFORMULATION);

        // Get all the formulationentityList where idformulation is less than UPDATED_IDFORMULATION
        defaultFormulationentityShouldBeFound("idformulation.lessThan=" + UPDATED_IDFORMULATION);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByIdformulationIsGreaterThanSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where idformulation is greater than DEFAULT_IDFORMULATION
        defaultFormulationentityShouldNotBeFound("idformulation.greaterThan=" + DEFAULT_IDFORMULATION);

        // Get all the formulationentityList where idformulation is greater than SMALLER_IDFORMULATION
        defaultFormulationentityShouldBeFound("idformulation.greaterThan=" + SMALLER_IDFORMULATION);
    }


    @Test
    @Transactional
    public void getAllFormulationentitiesByCodeproduitIsEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where codeproduit equals to DEFAULT_CODEPRODUIT
        defaultFormulationentityShouldBeFound("codeproduit.equals=" + DEFAULT_CODEPRODUIT);

        // Get all the formulationentityList where codeproduit equals to UPDATED_CODEPRODUIT
        defaultFormulationentityShouldNotBeFound("codeproduit.equals=" + UPDATED_CODEPRODUIT);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByCodeproduitIsNotEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where codeproduit not equals to DEFAULT_CODEPRODUIT
        defaultFormulationentityShouldNotBeFound("codeproduit.notEquals=" + DEFAULT_CODEPRODUIT);

        // Get all the formulationentityList where codeproduit not equals to UPDATED_CODEPRODUIT
        defaultFormulationentityShouldBeFound("codeproduit.notEquals=" + UPDATED_CODEPRODUIT);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByCodeproduitIsInShouldWork() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where codeproduit in DEFAULT_CODEPRODUIT or UPDATED_CODEPRODUIT
        defaultFormulationentityShouldBeFound("codeproduit.in=" + DEFAULT_CODEPRODUIT + "," + UPDATED_CODEPRODUIT);

        // Get all the formulationentityList where codeproduit equals to UPDATED_CODEPRODUIT
        defaultFormulationentityShouldNotBeFound("codeproduit.in=" + UPDATED_CODEPRODUIT);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByCodeproduitIsNullOrNotNull() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where codeproduit is not null
        defaultFormulationentityShouldBeFound("codeproduit.specified=true");

        // Get all the formulationentityList where codeproduit is null
        defaultFormulationentityShouldNotBeFound("codeproduit.specified=false");
    }
                @Test
    @Transactional
    public void getAllFormulationentitiesByCodeproduitContainsSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where codeproduit contains DEFAULT_CODEPRODUIT
        defaultFormulationentityShouldBeFound("codeproduit.contains=" + DEFAULT_CODEPRODUIT);

        // Get all the formulationentityList where codeproduit contains UPDATED_CODEPRODUIT
        defaultFormulationentityShouldNotBeFound("codeproduit.contains=" + UPDATED_CODEPRODUIT);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByCodeproduitNotContainsSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where codeproduit does not contain DEFAULT_CODEPRODUIT
        defaultFormulationentityShouldNotBeFound("codeproduit.doesNotContain=" + DEFAULT_CODEPRODUIT);

        // Get all the formulationentityList where codeproduit does not contain UPDATED_CODEPRODUIT
        defaultFormulationentityShouldBeFound("codeproduit.doesNotContain=" + UPDATED_CODEPRODUIT);
    }


    @Test
    @Transactional
    public void getAllFormulationentitiesByFormcompleteIsEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where formcomplete equals to DEFAULT_FORMCOMPLETE
        defaultFormulationentityShouldBeFound("formcomplete.equals=" + DEFAULT_FORMCOMPLETE);

        // Get all the formulationentityList where formcomplete equals to UPDATED_FORMCOMPLETE
        defaultFormulationentityShouldNotBeFound("formcomplete.equals=" + UPDATED_FORMCOMPLETE);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByFormcompleteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where formcomplete not equals to DEFAULT_FORMCOMPLETE
        defaultFormulationentityShouldNotBeFound("formcomplete.notEquals=" + DEFAULT_FORMCOMPLETE);

        // Get all the formulationentityList where formcomplete not equals to UPDATED_FORMCOMPLETE
        defaultFormulationentityShouldBeFound("formcomplete.notEquals=" + UPDATED_FORMCOMPLETE);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByFormcompleteIsInShouldWork() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where formcomplete in DEFAULT_FORMCOMPLETE or UPDATED_FORMCOMPLETE
        defaultFormulationentityShouldBeFound("formcomplete.in=" + DEFAULT_FORMCOMPLETE + "," + UPDATED_FORMCOMPLETE);

        // Get all the formulationentityList where formcomplete equals to UPDATED_FORMCOMPLETE
        defaultFormulationentityShouldNotBeFound("formcomplete.in=" + UPDATED_FORMCOMPLETE);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByFormcompleteIsNullOrNotNull() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where formcomplete is not null
        defaultFormulationentityShouldBeFound("formcomplete.specified=true");

        // Get all the formulationentityList where formcomplete is null
        defaultFormulationentityShouldNotBeFound("formcomplete.specified=false");
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByDesignationIsEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where designation equals to DEFAULT_DESIGNATION
        defaultFormulationentityShouldBeFound("designation.equals=" + DEFAULT_DESIGNATION);

        // Get all the formulationentityList where designation equals to UPDATED_DESIGNATION
        defaultFormulationentityShouldNotBeFound("designation.equals=" + UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByDesignationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where designation not equals to DEFAULT_DESIGNATION
        defaultFormulationentityShouldNotBeFound("designation.notEquals=" + DEFAULT_DESIGNATION);

        // Get all the formulationentityList where designation not equals to UPDATED_DESIGNATION
        defaultFormulationentityShouldBeFound("designation.notEquals=" + UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByDesignationIsInShouldWork() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where designation in DEFAULT_DESIGNATION or UPDATED_DESIGNATION
        defaultFormulationentityShouldBeFound("designation.in=" + DEFAULT_DESIGNATION + "," + UPDATED_DESIGNATION);

        // Get all the formulationentityList where designation equals to UPDATED_DESIGNATION
        defaultFormulationentityShouldNotBeFound("designation.in=" + UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByDesignationIsNullOrNotNull() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where designation is not null
        defaultFormulationentityShouldBeFound("designation.specified=true");

        // Get all the formulationentityList where designation is null
        defaultFormulationentityShouldNotBeFound("designation.specified=false");
    }
                @Test
    @Transactional
    public void getAllFormulationentitiesByDesignationContainsSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where designation contains DEFAULT_DESIGNATION
        defaultFormulationentityShouldBeFound("designation.contains=" + DEFAULT_DESIGNATION);

        // Get all the formulationentityList where designation contains UPDATED_DESIGNATION
        defaultFormulationentityShouldNotBeFound("designation.contains=" + UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByDesignationNotContainsSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where designation does not contain DEFAULT_DESIGNATION
        defaultFormulationentityShouldNotBeFound("designation.doesNotContain=" + DEFAULT_DESIGNATION);

        // Get all the formulationentityList where designation does not contain UPDATED_DESIGNATION
        defaultFormulationentityShouldBeFound("designation.doesNotContain=" + UPDATED_DESIGNATION);
    }


    @Test
    @Transactional
    public void getAllFormulationentitiesByMisecombIsEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misecomb equals to DEFAULT_MISECOMB
        defaultFormulationentityShouldBeFound("misecomb.equals=" + DEFAULT_MISECOMB);

        // Get all the formulationentityList where misecomb equals to UPDATED_MISECOMB
        defaultFormulationentityShouldNotBeFound("misecomb.equals=" + UPDATED_MISECOMB);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByMisecombIsNotEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misecomb not equals to DEFAULT_MISECOMB
        defaultFormulationentityShouldNotBeFound("misecomb.notEquals=" + DEFAULT_MISECOMB);

        // Get all the formulationentityList where misecomb not equals to UPDATED_MISECOMB
        defaultFormulationentityShouldBeFound("misecomb.notEquals=" + UPDATED_MISECOMB);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByMisecombIsInShouldWork() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misecomb in DEFAULT_MISECOMB or UPDATED_MISECOMB
        defaultFormulationentityShouldBeFound("misecomb.in=" + DEFAULT_MISECOMB + "," + UPDATED_MISECOMB);

        // Get all the formulationentityList where misecomb equals to UPDATED_MISECOMB
        defaultFormulationentityShouldNotBeFound("misecomb.in=" + UPDATED_MISECOMB);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByMisecombIsNullOrNotNull() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misecomb is not null
        defaultFormulationentityShouldBeFound("misecomb.specified=true");

        // Get all the formulationentityList where misecomb is null
        defaultFormulationentityShouldNotBeFound("misecomb.specified=false");
    }
                @Test
    @Transactional
    public void getAllFormulationentitiesByMisecombContainsSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misecomb contains DEFAULT_MISECOMB
        defaultFormulationentityShouldBeFound("misecomb.contains=" + DEFAULT_MISECOMB);

        // Get all the formulationentityList where misecomb contains UPDATED_MISECOMB
        defaultFormulationentityShouldNotBeFound("misecomb.contains=" + UPDATED_MISECOMB);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByMisecombNotContainsSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misecomb does not contain DEFAULT_MISECOMB
        defaultFormulationentityShouldNotBeFound("misecomb.doesNotContain=" + DEFAULT_MISECOMB);

        // Get all the formulationentityList where misecomb does not contain UPDATED_MISECOMB
        defaultFormulationentityShouldBeFound("misecomb.doesNotContain=" + UPDATED_MISECOMB);
    }


    @Test
    @Transactional
    public void getAllFormulationentitiesByMisetotaleIsEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misetotale equals to DEFAULT_MISETOTALE
        defaultFormulationentityShouldBeFound("misetotale.equals=" + DEFAULT_MISETOTALE);

        // Get all the formulationentityList where misetotale equals to UPDATED_MISETOTALE
        defaultFormulationentityShouldNotBeFound("misetotale.equals=" + UPDATED_MISETOTALE);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByMisetotaleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misetotale not equals to DEFAULT_MISETOTALE
        defaultFormulationentityShouldNotBeFound("misetotale.notEquals=" + DEFAULT_MISETOTALE);

        // Get all the formulationentityList where misetotale not equals to UPDATED_MISETOTALE
        defaultFormulationentityShouldBeFound("misetotale.notEquals=" + UPDATED_MISETOTALE);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByMisetotaleIsInShouldWork() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misetotale in DEFAULT_MISETOTALE or UPDATED_MISETOTALE
        defaultFormulationentityShouldBeFound("misetotale.in=" + DEFAULT_MISETOTALE + "," + UPDATED_MISETOTALE);

        // Get all the formulationentityList where misetotale equals to UPDATED_MISETOTALE
        defaultFormulationentityShouldNotBeFound("misetotale.in=" + UPDATED_MISETOTALE);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByMisetotaleIsNullOrNotNull() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misetotale is not null
        defaultFormulationentityShouldBeFound("misetotale.specified=true");

        // Get all the formulationentityList where misetotale is null
        defaultFormulationentityShouldNotBeFound("misetotale.specified=false");
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByMisetotaleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misetotale is greater than or equal to DEFAULT_MISETOTALE
        defaultFormulationentityShouldBeFound("misetotale.greaterThanOrEqual=" + DEFAULT_MISETOTALE);

        // Get all the formulationentityList where misetotale is greater than or equal to UPDATED_MISETOTALE
        defaultFormulationentityShouldNotBeFound("misetotale.greaterThanOrEqual=" + UPDATED_MISETOTALE);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByMisetotaleIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misetotale is less than or equal to DEFAULT_MISETOTALE
        defaultFormulationentityShouldBeFound("misetotale.lessThanOrEqual=" + DEFAULT_MISETOTALE);

        // Get all the formulationentityList where misetotale is less than or equal to SMALLER_MISETOTALE
        defaultFormulationentityShouldNotBeFound("misetotale.lessThanOrEqual=" + SMALLER_MISETOTALE);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByMisetotaleIsLessThanSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misetotale is less than DEFAULT_MISETOTALE
        defaultFormulationentityShouldNotBeFound("misetotale.lessThan=" + DEFAULT_MISETOTALE);

        // Get all the formulationentityList where misetotale is less than UPDATED_MISETOTALE
        defaultFormulationentityShouldBeFound("misetotale.lessThan=" + UPDATED_MISETOTALE);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByMisetotaleIsGreaterThanSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where misetotale is greater than DEFAULT_MISETOTALE
        defaultFormulationentityShouldNotBeFound("misetotale.greaterThan=" + DEFAULT_MISETOTALE);

        // Get all the formulationentityList where misetotale is greater than SMALLER_MISETOTALE
        defaultFormulationentityShouldBeFound("misetotale.greaterThan=" + SMALLER_MISETOTALE);
    }


    @Test
    @Transactional
    public void getAllFormulationentitiesByNumformIsEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where numform equals to DEFAULT_NUMFORM
        defaultFormulationentityShouldBeFound("numform.equals=" + DEFAULT_NUMFORM);

        // Get all the formulationentityList where numform equals to UPDATED_NUMFORM
        defaultFormulationentityShouldNotBeFound("numform.equals=" + UPDATED_NUMFORM);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByNumformIsNotEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where numform not equals to DEFAULT_NUMFORM
        defaultFormulationentityShouldNotBeFound("numform.notEquals=" + DEFAULT_NUMFORM);

        // Get all the formulationentityList where numform not equals to UPDATED_NUMFORM
        defaultFormulationentityShouldBeFound("numform.notEquals=" + UPDATED_NUMFORM);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByNumformIsInShouldWork() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where numform in DEFAULT_NUMFORM or UPDATED_NUMFORM
        defaultFormulationentityShouldBeFound("numform.in=" + DEFAULT_NUMFORM + "," + UPDATED_NUMFORM);

        // Get all the formulationentityList where numform equals to UPDATED_NUMFORM
        defaultFormulationentityShouldNotBeFound("numform.in=" + UPDATED_NUMFORM);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByNumformIsNullOrNotNull() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where numform is not null
        defaultFormulationentityShouldBeFound("numform.specified=true");

        // Get all the formulationentityList where numform is null
        defaultFormulationentityShouldNotBeFound("numform.specified=false");
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByNumformIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where numform is greater than or equal to DEFAULT_NUMFORM
        defaultFormulationentityShouldBeFound("numform.greaterThanOrEqual=" + DEFAULT_NUMFORM);

        // Get all the formulationentityList where numform is greater than or equal to UPDATED_NUMFORM
        defaultFormulationentityShouldNotBeFound("numform.greaterThanOrEqual=" + UPDATED_NUMFORM);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByNumformIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where numform is less than or equal to DEFAULT_NUMFORM
        defaultFormulationentityShouldBeFound("numform.lessThanOrEqual=" + DEFAULT_NUMFORM);

        // Get all the formulationentityList where numform is less than or equal to SMALLER_NUMFORM
        defaultFormulationentityShouldNotBeFound("numform.lessThanOrEqual=" + SMALLER_NUMFORM);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByNumformIsLessThanSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where numform is less than DEFAULT_NUMFORM
        defaultFormulationentityShouldNotBeFound("numform.lessThan=" + DEFAULT_NUMFORM);

        // Get all the formulationentityList where numform is less than UPDATED_NUMFORM
        defaultFormulationentityShouldBeFound("numform.lessThan=" + UPDATED_NUMFORM);
    }

    @Test
    @Transactional
    public void getAllFormulationentitiesByNumformIsGreaterThanSomething() throws Exception {
        // Initialize the database
        formulationentityRepository.saveAndFlush(formulationentity);

        // Get all the formulationentityList where numform is greater than DEFAULT_NUMFORM
        defaultFormulationentityShouldNotBeFound("numform.greaterThan=" + DEFAULT_NUMFORM);

        // Get all the formulationentityList where numform is greater than SMALLER_NUMFORM
        defaultFormulationentityShouldBeFound("numform.greaterThan=" + SMALLER_NUMFORM);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFormulationentityShouldBeFound(String filter) throws Exception {
        restFormulationentityMockMvc.perform(get("/api/formulationentities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formulationentity.getId().intValue())))
            .andExpect(jsonPath("$.[*].identifiantconc").value(hasItem(DEFAULT_IDENTIFIANTCONC.intValue())))
            .andExpect(jsonPath("$.[*].idformulation").value(hasItem(DEFAULT_IDFORMULATION.intValue())))
            .andExpect(jsonPath("$.[*].codeproduit").value(hasItem(DEFAULT_CODEPRODUIT)))
            .andExpect(jsonPath("$.[*].formcomplete").value(hasItem(DEFAULT_FORMCOMPLETE.booleanValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].misecomb").value(hasItem(DEFAULT_MISECOMB)))
            .andExpect(jsonPath("$.[*].misetotale").value(hasItem(DEFAULT_MISETOTALE)))
            .andExpect(jsonPath("$.[*].numform").value(hasItem(DEFAULT_NUMFORM)));

        // Check, that the count call also returns 1
        restFormulationentityMockMvc.perform(get("/api/formulationentities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFormulationentityShouldNotBeFound(String filter) throws Exception {
        restFormulationentityMockMvc.perform(get("/api/formulationentities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFormulationentityMockMvc.perform(get("/api/formulationentities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingFormulationentity() throws Exception {
        // Get the formulationentity
        restFormulationentityMockMvc.perform(get("/api/formulationentities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormulationentity() throws Exception {
        // Initialize the database
        formulationentityService.save(formulationentity);

        int databaseSizeBeforeUpdate = formulationentityRepository.findAll().size();

        // Update the formulationentity
        Formulationentity updatedFormulationentity = formulationentityRepository.findById(formulationentity.getId()).get();
        // Disconnect from session so that the updates on updatedFormulationentity are not directly saved in db
        em.detach(updatedFormulationentity);
        updatedFormulationentity
            .identifiantconc(UPDATED_IDENTIFIANTCONC)
            .idformulation(UPDATED_IDFORMULATION)
            .codeproduit(UPDATED_CODEPRODUIT)
            .formcomplete(UPDATED_FORMCOMPLETE)
            .designation(UPDATED_DESIGNATION)
            .misecomb(UPDATED_MISECOMB)
            .misetotale(UPDATED_MISETOTALE)
            .numform(UPDATED_NUMFORM);

        restFormulationentityMockMvc.perform(put("/api/formulationentities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormulationentity)))
            .andExpect(status().isOk());

        // Validate the Formulationentity in the database
        List<Formulationentity> formulationentityList = formulationentityRepository.findAll();
        assertThat(formulationentityList).hasSize(databaseSizeBeforeUpdate);
        Formulationentity testFormulationentity = formulationentityList.get(formulationentityList.size() - 1);
        assertThat(testFormulationentity.getIdentifiantconc()).isEqualTo(UPDATED_IDENTIFIANTCONC);
        assertThat(testFormulationentity.getIdformulation()).isEqualTo(UPDATED_IDFORMULATION);
        assertThat(testFormulationentity.getCodeproduit()).isEqualTo(UPDATED_CODEPRODUIT);
        assertThat(testFormulationentity.isFormcomplete()).isEqualTo(UPDATED_FORMCOMPLETE);
        assertThat(testFormulationentity.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testFormulationentity.getMisecomb()).isEqualTo(UPDATED_MISECOMB);
        assertThat(testFormulationentity.getMisetotale()).isEqualTo(UPDATED_MISETOTALE);
        assertThat(testFormulationentity.getNumform()).isEqualTo(UPDATED_NUMFORM);
    }

    @Test
    @Transactional
    public void updateNonExistingFormulationentity() throws Exception {
        int databaseSizeBeforeUpdate = formulationentityRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormulationentityMockMvc.perform(put("/api/formulationentities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formulationentity)))
            .andExpect(status().isBadRequest());

        // Validate the Formulationentity in the database
        List<Formulationentity> formulationentityList = formulationentityRepository.findAll();
        assertThat(formulationentityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormulationentity() throws Exception {
        // Initialize the database
        formulationentityService.save(formulationentity);

        int databaseSizeBeforeDelete = formulationentityRepository.findAll().size();

        // Delete the formulationentity
        restFormulationentityMockMvc.perform(delete("/api/formulationentities/{id}", formulationentity.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Formulationentity> formulationentityList = formulationentityRepository.findAll();
        assertThat(formulationentityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
