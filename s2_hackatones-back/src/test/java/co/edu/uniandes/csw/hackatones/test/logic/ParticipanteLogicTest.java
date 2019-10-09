/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.ParticipanteLogic;
import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ParticipantePersistence;
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
 * @author ne.cardenas
 */
@RunWith(Arquillian.class)
public class ParticipanteLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ParticipanteLogic logic;
    
    @PersistenceContext()
    protected EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ParticipanteEntity> data = new ArrayList<>();
    private List<ActualEntity> hackData = new ArrayList();
    private List<LenguajeEntity> lengData = new ArrayList();
    private List<InteresEntity> intData = new ArrayList<>();
    private List<TecnologiaEntity> tecData = new ArrayList();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ParticipanteEntity.class.getPackage())
                .addPackage(ParticipanteLogic.class.getPackage())
                .addPackage(ParticipantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
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
    
    private void clearData() {
        em.createQuery("delete from ParticipanteEntity").executeUpdate();
        em.createQuery("delete from HackatonEntity").executeUpdate();
        em.createQuery("delete from TecnologiaEntity").executeUpdate();
        em.createQuery("delete from InteresEntity").executeUpdate();
        em.createQuery("delete from LenguajeEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            LenguajeEntity lengs = factory.manufacturePojo(LenguajeEntity.class);
            em.persist(lengs);
            lengData.add(lengs);
        }
        for (int i = 0; i < 3; i++) {
            ActualEntity x = factory.manufacturePojo(ActualEntity.class);
            em.persist(x);
            hackData.add(x);
        }
        for (int i = 0; i < 3; i++) {
            TecnologiaEntity x = factory.manufacturePojo(TecnologiaEntity.class);
            em.persist(x);
            tecData.add(x);
        }
        for (int i = 0; i < 3; i++) {
            InteresEntity x = factory.manufacturePojo(InteresEntity.class);
            em.persist(x);
            intData.add(x);
        }
        //Hackaton null
        ParticipanteEntity entity1 = factory.manufacturePojo(ParticipanteEntity.class);
        entity1.setIntereses(intData);
        entity1.setLenguajes(lengData);
        entity1.setTecnologias(tecData);
        entity1.setHackaton(null);
        em.persist(entity1);
        data.add(entity1);
        
        //Interes null
        ParticipanteEntity entity2 = factory.manufacturePojo(ParticipanteEntity.class);
        entity2.setIntereses(null);
        entity2.setLenguajes(lengData);
        entity2.setTecnologias(tecData);
        entity2.setHackaton(hackData.get(0));
        em.persist(entity2);
        data.add(entity2);
        
        //Lenguaje null
        ParticipanteEntity entity3 = factory.manufacturePojo(ParticipanteEntity.class);
        entity3.setIntereses(intData);
        entity3.setLenguajes(null);
        entity3.setTecnologias(tecData);
        entity3.setHackaton(hackData.get(0));
        em.persist(entity3);
        data.add(entity3);
        
        //Tecnologia null
        ParticipanteEntity entity4 = factory.manufacturePojo(ParticipanteEntity.class);
        entity4.setIntereses(intData);
        entity4.setLenguajes(lengData);
        entity4.setTecnologias(null);
        entity4.setHackaton(hackData.get(0));
        em.persist(entity4);
        data.add(entity4);
    }
    
    @Test
    public void createParticipante() throws BusinessLogicException {
        ParticipanteEntity newEntity = factory.manufacturePojo(ParticipanteEntity.class);
        newEntity.setHackaton(hackData.get(0));
        newEntity.setLenguajes(lengData);
        newEntity.setIntereses(intData);
        newEntity.setTecnologias(tecData);
        ParticipanteEntity result = logic.createParticipante(newEntity);
        Assert.assertNotNull(result);

        ParticipanteEntity entity = em.find(ParticipanteEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    @Test(expected = BusinessLogicException.class)
    public void createHackatonNull() throws BusinessLogicException {
        ParticipanteEntity result = logic.createParticipante(data.get(0));
    }

    @Test(expected = BusinessLogicException.class)
    public void createTecnologiasNull() throws BusinessLogicException {
        ParticipanteEntity result = logic.createParticipante(data.get(3));
    }

    @Test(expected = BusinessLogicException.class)
    public void createInteresesNull() throws BusinessLogicException {
        ParticipanteEntity result = logic.createParticipante(data.get(1));
    }

    @Test(expected = BusinessLogicException.class)
    public void createLenguajesNull() throws BusinessLogicException {
        ParticipanteEntity result = logic.createParticipante(data.get(2));
    }
}
