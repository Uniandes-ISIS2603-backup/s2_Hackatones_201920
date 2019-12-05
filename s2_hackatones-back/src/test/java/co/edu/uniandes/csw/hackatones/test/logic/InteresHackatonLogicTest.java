/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.InteresHackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.InteresHackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.HackatonLogic;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.InteresPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ja.torresl
 */
@RunWith(Arquillian.class)
public class InteresHackatonLogicTest 
{
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private InteresHackatonLogic interesHackatonLogic;

    @Inject
    private HackatonLogic hackatonLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private InteresEntity interes = new InteresEntity();
    private List<HackatonEntity> data = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HackatonEntity.class.getPackage())
                .addPackage(InteresEntity.class.getPackage())
                .addPackage(InteresHackatonLogic.class.getPackage())
                .addPackage(InteresPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from HackatonEntity").executeUpdate();
        em.createQuery("delete from InteresEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        

        interes = factory.manufacturePojo(InteresEntity.class);
        interes.setId(1L);
        interes.setParticipantes(new ArrayList<>());
        em.persist(interes);

        for (int i = 0; i < 3; i++) {
            HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            entity.setIntereses(new ArrayList<>());
            entity.getIntereses().add(interes);
            em.persist(entity);
            data.add(entity);
            interes.getHackatones().add(entity);
        }
    }
    
    /**
     * Prueba para asociar un interes a un hackaton.
     *
     *
     * @throws BusinessLogicException
     */
    @Test
    public void addHackatonTest() throws BusinessLogicException {
        
        HackatonEntity newHackaton = factory.manufacturePojo(HackatonEntity.class);
        hackatonLogic.createHackaton(newHackaton);
        HackatonEntity hackatonEntity = interesHackatonLogic.addHackaton(interes.getId(), newHackaton.getId());
        Assert.assertNotNull(hackatonEntity);
        

        Assert.assertEquals(hackatonEntity.getId(), newHackaton.getId());
       
        
         
        HackatonEntity lastHackaton = interesHackatonLogic.getHackaton(interes.getId(), newHackaton.getId());



        Assert.assertEquals(lastHackaton.getId(), newHackaton.getId());
        
    }
    
    /**
     * Prueba para consultar la lista de Hackatons de un interes.
     */
    @Test
    public void getHackatonesTest() {
        List<HackatonEntity> hackatonEntities = interesHackatonLogic.getHackatones(interes.getId());

        Assert.assertEquals(data.size(), hackatonEntities.size());

        for (int i = 0; i < data.size(); i++) {
            Assert.assertTrue(hackatonEntities.contains(data.get(0)));
        }
    }

    /**
     * Prueba para cpnsultar un hackaton de un interes.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void getParticipanteTest() throws BusinessLogicException {
        HackatonEntity hackatonEntity = data.get(0);
        HackatonEntity hackaton = interesHackatonLogic.getHackaton(interes.getId(), hackatonEntity.getId());
        Assert.assertNotNull(hackaton);

        Assert.assertEquals(hackatonEntity.getId(), hackaton.getId());
        
    }
    
    /**
     * Prueba para actualizar los hackatons de un interes.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void replaceHackatonesTest() throws BusinessLogicException {
        List<HackatonEntity> nuevaLista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            entity.setIntereses(new ArrayList<>());
            entity.getIntereses().add(interes);
            hackatonLogic.createHackaton(entity);
            nuevaLista.add(entity);
        }
        interesHackatonLogic.replaceHackatones(interes.getId(), nuevaLista);
        List<HackatonEntity> hackatonEntities = interesHackatonLogic.getHackatones(interes.getId());
        for (HackatonEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(hackatonEntities.contains(aNuevaLista));
        }
    }

    /**
     * Prueba desasociar un hackaton con un interes.
     *
     */
    @Test
    public void removeHackatonTest() {
        for (HackatonEntity hackaton : data) {
            interesHackatonLogic.removeParticipante(interes.getId(), hackaton.getId());
        }
        Assert.assertTrue(interesHackatonLogic.getHackatones(interes.getId()).isEmpty());
    }
    
}
