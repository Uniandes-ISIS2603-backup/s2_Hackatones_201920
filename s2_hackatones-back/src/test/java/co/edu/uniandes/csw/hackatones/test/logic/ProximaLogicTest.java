/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.ProximaLogic;
import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ProximaPersistence;
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
public class ProximaLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProximaEntity.class.getPackage())
                .addPackage(ProximaLogic.class.getPackage())
                .addPackage(ProximaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    private PodamFactory factory =  new PodamFactoryImpl();
    
    @Inject
    private ProximaLogic proximaLogic;
    
    @PersistenceContext()
    protected EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<ProximaEntity> data = new ArrayList<>();
    
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
        em.createQuery("delete from ProximaEntity").executeUpdate();
    }
    
     private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ProximaEntity entity = factory.manufacturePojo(ProximaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }    
    
    @Test
    public void createProxima() throws BusinessLogicException{
        
        ProximaEntity newEntity = factory.manufacturePojo(ProximaEntity.class);
        ProximaEntity result = proximaLogic.createProxima(newEntity);
        Assert.assertNotNull(result);
        
        ProximaEntity entity = em.find(ProximaEntity.class, result.getId());
        Assert.assertEquals(entity.getReglas(), result.getReglas());
        Assert.assertEquals(entity.getRestricciones(), result.getRestricciones());
    }
    
    @Test
    public void getProximaesTest() {
        List<ProximaEntity> list = proximaLogic.getProximas();
        Assert.assertEquals(data.size(), list.size());
        for (ProximaEntity ent : list) {
            boolean found = false;
            for (ProximaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getProximaTest() {
        ProximaEntity entity = data.get(0);
        ProximaEntity newEntity = proximaLogic.getProxima(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getReglas(), entity.getReglas());
        Assert.assertEquals(newEntity.getRestricciones(), entity.getRestricciones());
    }

    @Test
    public void updateProximaTest() {
        ProximaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ProximaEntity newEntity = factory.manufacturePojo(ProximaEntity.class);

        newEntity.setId(entity.getId());

        proximaLogic.updateProxima(newEntity.getId(), newEntity);

        ProximaEntity resp = em.find(ProximaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getReglas(), resp.getReglas());
    }
    
    @Test
    public void deleteProximaTest() throws BusinessLogicException {
        ProximaEntity entity = data.get(0);
        proximaLogic.deleteProxima(entity.getId());
        ProximaEntity deleted = em.find(ProximaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
    @Test(expected = BusinessLogicException.class)
    public void createProximaRestriccionesNull() throws BusinessLogicException{
        
        ProximaEntity newEntity = factory.manufacturePojo(ProximaEntity.class);
        newEntity.setRestricciones(null);
        ProximaEntity result = proximaLogic.createProxima(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProximaReglasNull() throws BusinessLogicException{
        
        ProximaEntity newEntity = factory.manufacturePojo(ProximaEntity.class);
        newEntity.setReglas(null);
        ProximaEntity result = proximaLogic.createProxima(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProximaRestriccionesCadenaVacia() throws BusinessLogicException{
        
        ProximaEntity newEntity = factory.manufacturePojo(ProximaEntity.class);
        newEntity.setRestricciones("");
        ProximaEntity result = proximaLogic.createProxima(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProximaReglasCadenaVacia() throws BusinessLogicException{
        
        ProximaEntity newEntity = factory.manufacturePojo(ProximaEntity.class);
        newEntity.setReglas("");
        ProximaEntity result = proximaLogic.createProxima(newEntity);
    }
}