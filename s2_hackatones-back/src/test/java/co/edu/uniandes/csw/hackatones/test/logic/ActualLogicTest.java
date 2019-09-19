/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.ActualLogic;
import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ActualPersistence;
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
 * @author a.pedraza
 */
@RunWith(Arquillian.class)
public class ActualLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActualEntity.class.getPackage())
                .addPackage(ActualLogic.class.getPackage())
                .addPackage(ActualPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    private PodamFactory factory =  new PodamFactoryImpl();
    
    @Inject
    private ActualLogic actualLogic;
    
    @PersistenceContext()
    protected EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<ActualEntity> data = new ArrayList<>();
    
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
        em.createQuery("delete from ActualEntity").executeUpdate();
    }
    
     private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ActualEntity entity = factory.manufacturePojo(ActualEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }    
    
    @Test
    public void createActual() throws BusinessLogicException{
        
        ActualEntity newEntity = factory.manufacturePojo(ActualEntity.class);
        ActualEntity result = actualLogic.createActual(newEntity);
        Assert.assertNotNull(result);
        
        ActualEntity entity = em.find(ActualEntity.class, result.getId());
        Assert.assertEquals(entity.getReglas(), result.getReglas());
        Assert.assertEquals(entity.getRestricciones(), result.getRestricciones());
    }
    
    @Test
    public void getActualesTest() {
        List<ActualEntity> list = actualLogic.getActuales();
        Assert.assertEquals(data.size(), list.size());
        for (ActualEntity ent : list) {
            boolean found = false;
            for (ActualEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getActualTest() {
        ActualEntity entity = data.get(0);
        ActualEntity newEntity = actualLogic.getActual(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getReglas(), entity.getReglas());
        Assert.assertEquals(newEntity.getRestricciones(), entity.getRestricciones());
    }

    @Test
    public void updateActualTest() {
        ActualEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ActualEntity newEntity = factory.manufacturePojo(ActualEntity.class);

        newEntity.setId(entity.getId());

        actualLogic.updateActual(newEntity.getId(), newEntity);

        ActualEntity resp = em.find(ActualEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getReglas(), resp.getReglas());
    }
    
    @Test
    public void deleteActualTest() throws BusinessLogicException {
        ActualEntity entity = data.get(0);
        actualLogic.deleteActual(entity.getId());
        ActualEntity deleted = em.find(ActualEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
    @Test(expected = BusinessLogicException.class)
    public void createActualRestriccionesNull() throws BusinessLogicException{
        
        ActualEntity newEntity = factory.manufacturePojo(ActualEntity.class);
        newEntity.setRestricciones(null);
        ActualEntity result = actualLogic.createActual(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createActualReglasNull() throws BusinessLogicException{
        
        ActualEntity newEntity = factory.manufacturePojo(ActualEntity.class);
        newEntity.setReglas(null);
        ActualEntity result = actualLogic.createActual(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createActualRestriccionesCadenaVacia() throws BusinessLogicException{
        
        ActualEntity newEntity = factory.manufacturePojo(ActualEntity.class);
        newEntity.setRestricciones("");
        ActualEntity result = actualLogic.createActual(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createActualReglasCadenaVacia() throws BusinessLogicException{
        
        ActualEntity newEntity = factory.manufacturePojo(ActualEntity.class);
        newEntity.setReglas("");
        ActualEntity result = actualLogic.createActual(newEntity);
    }
}