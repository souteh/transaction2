package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Transaction2App;
import com.mycompany.myapp.domain.Clientglentity;
import com.mycompany.myapp.repository.ClientglentityRepository;
import com.mycompany.myapp.service.ClientglentityService;
import com.mycompany.myapp.service.dto.ClientglentityCriteria;
import com.mycompany.myapp.service.ClientglentityQueryService;

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
 * Integration tests for the {@link ClientglentityResource} REST controller.
 */
@SpringBootTest(classes = Transaction2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class ClientglentityResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_CIN = "AAAAAAAAAA";
    private static final String UPDATED_CIN = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTAIRE = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE = "BBBBBBBBBB";

    @Autowired
    private ClientglentityRepository clientglentityRepository;

    @Autowired
    private ClientglentityService clientglentityService;

    @Autowired
    private ClientglentityQueryService clientglentityQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClientglentityMockMvc;

    private Clientglentity clientglentity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Clientglentity createEntity(EntityManager em) {
        Clientglentity clientglentity = new Clientglentity()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .cin(DEFAULT_CIN)
            .telephone(DEFAULT_TELEPHONE)
            .commentaire(DEFAULT_COMMENTAIRE);
        return clientglentity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Clientglentity createUpdatedEntity(EntityManager em) {
        Clientglentity clientglentity = new Clientglentity()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .cin(UPDATED_CIN)
            .telephone(UPDATED_TELEPHONE)
            .commentaire(UPDATED_COMMENTAIRE);
        return clientglentity;
    }

    @BeforeEach
    public void initTest() {
        clientglentity = createEntity(em);
    }

    @Test
    @Transactional
    public void createClientglentity() throws Exception {
        int databaseSizeBeforeCreate = clientglentityRepository.findAll().size();
        // Create the Clientglentity
        restClientglentityMockMvc.perform(post("/api/clientglentities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientglentity)))
            .andExpect(status().isCreated());

        // Validate the Clientglentity in the database
        List<Clientglentity> clientglentityList = clientglentityRepository.findAll();
        assertThat(clientglentityList).hasSize(databaseSizeBeforeCreate + 1);
        Clientglentity testClientglentity = clientglentityList.get(clientglentityList.size() - 1);
        assertThat(testClientglentity.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testClientglentity.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testClientglentity.getCin()).isEqualTo(DEFAULT_CIN);
        assertThat(testClientglentity.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testClientglentity.getCommentaire()).isEqualTo(DEFAULT_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void createClientglentityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clientglentityRepository.findAll().size();

        // Create the Clientglentity with an existing ID
        clientglentity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientglentityMockMvc.perform(post("/api/clientglentities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientglentity)))
            .andExpect(status().isBadRequest());

        // Validate the Clientglentity in the database
        List<Clientglentity> clientglentityList = clientglentityRepository.findAll();
        assertThat(clientglentityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClientglentities() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList
        restClientglentityMockMvc.perform(get("/api/clientglentities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientglentity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].cin").value(hasItem(DEFAULT_CIN)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].commentaire").value(hasItem(DEFAULT_COMMENTAIRE)));
    }
    
    @Test
    @Transactional
    public void getClientglentity() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get the clientglentity
        restClientglentityMockMvc.perform(get("/api/clientglentities/{id}", clientglentity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clientglentity.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.cin").value(DEFAULT_CIN))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.commentaire").value(DEFAULT_COMMENTAIRE));
    }


    @Test
    @Transactional
    public void getClientglentitiesByIdFiltering() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        Long id = clientglentity.getId();

        defaultClientglentityShouldBeFound("id.equals=" + id);
        defaultClientglentityShouldNotBeFound("id.notEquals=" + id);

        defaultClientglentityShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultClientglentityShouldNotBeFound("id.greaterThan=" + id);

        defaultClientglentityShouldBeFound("id.lessThanOrEqual=" + id);
        defaultClientglentityShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllClientglentitiesByNomIsEqualToSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where nom equals to DEFAULT_NOM
        defaultClientglentityShouldBeFound("nom.equals=" + DEFAULT_NOM);

        // Get all the clientglentityList where nom equals to UPDATED_NOM
        defaultClientglentityShouldNotBeFound("nom.equals=" + UPDATED_NOM);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByNomIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where nom not equals to DEFAULT_NOM
        defaultClientglentityShouldNotBeFound("nom.notEquals=" + DEFAULT_NOM);

        // Get all the clientglentityList where nom not equals to UPDATED_NOM
        defaultClientglentityShouldBeFound("nom.notEquals=" + UPDATED_NOM);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByNomIsInShouldWork() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where nom in DEFAULT_NOM or UPDATED_NOM
        defaultClientglentityShouldBeFound("nom.in=" + DEFAULT_NOM + "," + UPDATED_NOM);

        // Get all the clientglentityList where nom equals to UPDATED_NOM
        defaultClientglentityShouldNotBeFound("nom.in=" + UPDATED_NOM);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByNomIsNullOrNotNull() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where nom is not null
        defaultClientglentityShouldBeFound("nom.specified=true");

        // Get all the clientglentityList where nom is null
        defaultClientglentityShouldNotBeFound("nom.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientglentitiesByNomContainsSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where nom contains DEFAULT_NOM
        defaultClientglentityShouldBeFound("nom.contains=" + DEFAULT_NOM);

        // Get all the clientglentityList where nom contains UPDATED_NOM
        defaultClientglentityShouldNotBeFound("nom.contains=" + UPDATED_NOM);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByNomNotContainsSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where nom does not contain DEFAULT_NOM
        defaultClientglentityShouldNotBeFound("nom.doesNotContain=" + DEFAULT_NOM);

        // Get all the clientglentityList where nom does not contain UPDATED_NOM
        defaultClientglentityShouldBeFound("nom.doesNotContain=" + UPDATED_NOM);
    }


    @Test
    @Transactional
    public void getAllClientglentitiesByPrenomIsEqualToSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where prenom equals to DEFAULT_PRENOM
        defaultClientglentityShouldBeFound("prenom.equals=" + DEFAULT_PRENOM);

        // Get all the clientglentityList where prenom equals to UPDATED_PRENOM
        defaultClientglentityShouldNotBeFound("prenom.equals=" + UPDATED_PRENOM);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByPrenomIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where prenom not equals to DEFAULT_PRENOM
        defaultClientglentityShouldNotBeFound("prenom.notEquals=" + DEFAULT_PRENOM);

        // Get all the clientglentityList where prenom not equals to UPDATED_PRENOM
        defaultClientglentityShouldBeFound("prenom.notEquals=" + UPDATED_PRENOM);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByPrenomIsInShouldWork() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where prenom in DEFAULT_PRENOM or UPDATED_PRENOM
        defaultClientglentityShouldBeFound("prenom.in=" + DEFAULT_PRENOM + "," + UPDATED_PRENOM);

        // Get all the clientglentityList where prenom equals to UPDATED_PRENOM
        defaultClientglentityShouldNotBeFound("prenom.in=" + UPDATED_PRENOM);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByPrenomIsNullOrNotNull() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where prenom is not null
        defaultClientglentityShouldBeFound("prenom.specified=true");

        // Get all the clientglentityList where prenom is null
        defaultClientglentityShouldNotBeFound("prenom.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientglentitiesByPrenomContainsSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where prenom contains DEFAULT_PRENOM
        defaultClientglentityShouldBeFound("prenom.contains=" + DEFAULT_PRENOM);

        // Get all the clientglentityList where prenom contains UPDATED_PRENOM
        defaultClientglentityShouldNotBeFound("prenom.contains=" + UPDATED_PRENOM);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByPrenomNotContainsSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where prenom does not contain DEFAULT_PRENOM
        defaultClientglentityShouldNotBeFound("prenom.doesNotContain=" + DEFAULT_PRENOM);

        // Get all the clientglentityList where prenom does not contain UPDATED_PRENOM
        defaultClientglentityShouldBeFound("prenom.doesNotContain=" + UPDATED_PRENOM);
    }


    @Test
    @Transactional
    public void getAllClientglentitiesByCinIsEqualToSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where cin equals to DEFAULT_CIN
        defaultClientglentityShouldBeFound("cin.equals=" + DEFAULT_CIN);

        // Get all the clientglentityList where cin equals to UPDATED_CIN
        defaultClientglentityShouldNotBeFound("cin.equals=" + UPDATED_CIN);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByCinIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where cin not equals to DEFAULT_CIN
        defaultClientglentityShouldNotBeFound("cin.notEquals=" + DEFAULT_CIN);

        // Get all the clientglentityList where cin not equals to UPDATED_CIN
        defaultClientglentityShouldBeFound("cin.notEquals=" + UPDATED_CIN);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByCinIsInShouldWork() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where cin in DEFAULT_CIN or UPDATED_CIN
        defaultClientglentityShouldBeFound("cin.in=" + DEFAULT_CIN + "," + UPDATED_CIN);

        // Get all the clientglentityList where cin equals to UPDATED_CIN
        defaultClientglentityShouldNotBeFound("cin.in=" + UPDATED_CIN);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByCinIsNullOrNotNull() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where cin is not null
        defaultClientglentityShouldBeFound("cin.specified=true");

        // Get all the clientglentityList where cin is null
        defaultClientglentityShouldNotBeFound("cin.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientglentitiesByCinContainsSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where cin contains DEFAULT_CIN
        defaultClientglentityShouldBeFound("cin.contains=" + DEFAULT_CIN);

        // Get all the clientglentityList where cin contains UPDATED_CIN
        defaultClientglentityShouldNotBeFound("cin.contains=" + UPDATED_CIN);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByCinNotContainsSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where cin does not contain DEFAULT_CIN
        defaultClientglentityShouldNotBeFound("cin.doesNotContain=" + DEFAULT_CIN);

        // Get all the clientglentityList where cin does not contain UPDATED_CIN
        defaultClientglentityShouldBeFound("cin.doesNotContain=" + UPDATED_CIN);
    }


    @Test
    @Transactional
    public void getAllClientglentitiesByTelephoneIsEqualToSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where telephone equals to DEFAULT_TELEPHONE
        defaultClientglentityShouldBeFound("telephone.equals=" + DEFAULT_TELEPHONE);

        // Get all the clientglentityList where telephone equals to UPDATED_TELEPHONE
        defaultClientglentityShouldNotBeFound("telephone.equals=" + UPDATED_TELEPHONE);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByTelephoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where telephone not equals to DEFAULT_TELEPHONE
        defaultClientglentityShouldNotBeFound("telephone.notEquals=" + DEFAULT_TELEPHONE);

        // Get all the clientglentityList where telephone not equals to UPDATED_TELEPHONE
        defaultClientglentityShouldBeFound("telephone.notEquals=" + UPDATED_TELEPHONE);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByTelephoneIsInShouldWork() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where telephone in DEFAULT_TELEPHONE or UPDATED_TELEPHONE
        defaultClientglentityShouldBeFound("telephone.in=" + DEFAULT_TELEPHONE + "," + UPDATED_TELEPHONE);

        // Get all the clientglentityList where telephone equals to UPDATED_TELEPHONE
        defaultClientglentityShouldNotBeFound("telephone.in=" + UPDATED_TELEPHONE);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByTelephoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where telephone is not null
        defaultClientglentityShouldBeFound("telephone.specified=true");

        // Get all the clientglentityList where telephone is null
        defaultClientglentityShouldNotBeFound("telephone.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientglentitiesByTelephoneContainsSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where telephone contains DEFAULT_TELEPHONE
        defaultClientglentityShouldBeFound("telephone.contains=" + DEFAULT_TELEPHONE);

        // Get all the clientglentityList where telephone contains UPDATED_TELEPHONE
        defaultClientglentityShouldNotBeFound("telephone.contains=" + UPDATED_TELEPHONE);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByTelephoneNotContainsSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where telephone does not contain DEFAULT_TELEPHONE
        defaultClientglentityShouldNotBeFound("telephone.doesNotContain=" + DEFAULT_TELEPHONE);

        // Get all the clientglentityList where telephone does not contain UPDATED_TELEPHONE
        defaultClientglentityShouldBeFound("telephone.doesNotContain=" + UPDATED_TELEPHONE);
    }


    @Test
    @Transactional
    public void getAllClientglentitiesByCommentaireIsEqualToSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where commentaire equals to DEFAULT_COMMENTAIRE
        defaultClientglentityShouldBeFound("commentaire.equals=" + DEFAULT_COMMENTAIRE);

        // Get all the clientglentityList where commentaire equals to UPDATED_COMMENTAIRE
        defaultClientglentityShouldNotBeFound("commentaire.equals=" + UPDATED_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByCommentaireIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where commentaire not equals to DEFAULT_COMMENTAIRE
        defaultClientglentityShouldNotBeFound("commentaire.notEquals=" + DEFAULT_COMMENTAIRE);

        // Get all the clientglentityList where commentaire not equals to UPDATED_COMMENTAIRE
        defaultClientglentityShouldBeFound("commentaire.notEquals=" + UPDATED_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByCommentaireIsInShouldWork() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where commentaire in DEFAULT_COMMENTAIRE or UPDATED_COMMENTAIRE
        defaultClientglentityShouldBeFound("commentaire.in=" + DEFAULT_COMMENTAIRE + "," + UPDATED_COMMENTAIRE);

        // Get all the clientglentityList where commentaire equals to UPDATED_COMMENTAIRE
        defaultClientglentityShouldNotBeFound("commentaire.in=" + UPDATED_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByCommentaireIsNullOrNotNull() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where commentaire is not null
        defaultClientglentityShouldBeFound("commentaire.specified=true");

        // Get all the clientglentityList where commentaire is null
        defaultClientglentityShouldNotBeFound("commentaire.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientglentitiesByCommentaireContainsSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where commentaire contains DEFAULT_COMMENTAIRE
        defaultClientglentityShouldBeFound("commentaire.contains=" + DEFAULT_COMMENTAIRE);

        // Get all the clientglentityList where commentaire contains UPDATED_COMMENTAIRE
        defaultClientglentityShouldNotBeFound("commentaire.contains=" + UPDATED_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void getAllClientglentitiesByCommentaireNotContainsSomething() throws Exception {
        // Initialize the database
        clientglentityRepository.saveAndFlush(clientglentity);

        // Get all the clientglentityList where commentaire does not contain DEFAULT_COMMENTAIRE
        defaultClientglentityShouldNotBeFound("commentaire.doesNotContain=" + DEFAULT_COMMENTAIRE);

        // Get all the clientglentityList where commentaire does not contain UPDATED_COMMENTAIRE
        defaultClientglentityShouldBeFound("commentaire.doesNotContain=" + UPDATED_COMMENTAIRE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultClientglentityShouldBeFound(String filter) throws Exception {
        restClientglentityMockMvc.perform(get("/api/clientglentities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientglentity.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].cin").value(hasItem(DEFAULT_CIN)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].commentaire").value(hasItem(DEFAULT_COMMENTAIRE)));

        // Check, that the count call also returns 1
        restClientglentityMockMvc.perform(get("/api/clientglentities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultClientglentityShouldNotBeFound(String filter) throws Exception {
        restClientglentityMockMvc.perform(get("/api/clientglentities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restClientglentityMockMvc.perform(get("/api/clientglentities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingClientglentity() throws Exception {
        // Get the clientglentity
        restClientglentityMockMvc.perform(get("/api/clientglentities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClientglentity() throws Exception {
        // Initialize the database
        clientglentityService.save(clientglentity);

        int databaseSizeBeforeUpdate = clientglentityRepository.findAll().size();

        // Update the clientglentity
        Clientglentity updatedClientglentity = clientglentityRepository.findById(clientglentity.getId()).get();
        // Disconnect from session so that the updates on updatedClientglentity are not directly saved in db
        em.detach(updatedClientglentity);
        updatedClientglentity
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .cin(UPDATED_CIN)
            .telephone(UPDATED_TELEPHONE)
            .commentaire(UPDATED_COMMENTAIRE);

        restClientglentityMockMvc.perform(put("/api/clientglentities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedClientglentity)))
            .andExpect(status().isOk());

        // Validate the Clientglentity in the database
        List<Clientglentity> clientglentityList = clientglentityRepository.findAll();
        assertThat(clientglentityList).hasSize(databaseSizeBeforeUpdate);
        Clientglentity testClientglentity = clientglentityList.get(clientglentityList.size() - 1);
        assertThat(testClientglentity.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testClientglentity.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testClientglentity.getCin()).isEqualTo(UPDATED_CIN);
        assertThat(testClientglentity.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testClientglentity.getCommentaire()).isEqualTo(UPDATED_COMMENTAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingClientglentity() throws Exception {
        int databaseSizeBeforeUpdate = clientglentityRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientglentityMockMvc.perform(put("/api/clientglentities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientglentity)))
            .andExpect(status().isBadRequest());

        // Validate the Clientglentity in the database
        List<Clientglentity> clientglentityList = clientglentityRepository.findAll();
        assertThat(clientglentityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClientglentity() throws Exception {
        // Initialize the database
        clientglentityService.save(clientglentity);

        int databaseSizeBeforeDelete = clientglentityRepository.findAll().size();

        // Delete the clientglentity
        restClientglentityMockMvc.perform(delete("/api/clientglentities/{id}", clientglentity.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Clientglentity> clientglentityList = clientglentityRepository.findAll();
        assertThat(clientglentityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
