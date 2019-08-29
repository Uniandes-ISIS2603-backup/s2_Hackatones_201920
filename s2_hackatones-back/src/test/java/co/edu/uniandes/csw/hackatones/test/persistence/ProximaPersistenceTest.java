/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
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
public class ProximaPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ProximaEntity.class)
                .addClass(ProximaPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    ProximaPersistence ap;
    
    @PersistenceContext
    protected EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ProximaEntity> data = new ArrayList<>();
    
    @Before
    public void configTest(){
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            }
            catch(Exception a){
                a.printStackTrace();
           }       
        }
    }
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ProximaEntity proxima = factory.manufacturePojo(ProximaEntity.class);
        ProximaEntity result = ap.create(proxima);
        Assert.assertNotNull(result);      
        
        ProximaEntity entity = em.find(ProximaEntity.class,result.getId());
        Assert.assertEquals(proxima.getId(), entity.getId());
        Assert.assertEquals(proxima.getReglas(), entity.getReglas());
        Assert.assertEquals(proxima.getRestricciones(), entity.getRestricciones());
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
    public void getProximaesTest() {
        List<ProximaEntity> list = ap.findAll();
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
        ProximaEntity newEntity = ap.find(entity.getId());
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

        ap.update(newEntity);

        ProximaEntity resp = em.find(ProximaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getReglas(), resp.getReglas());
        Assert.assertEquals(newEntity.getRestricciones(), resp.getRestricciones());
    }

    @Test
    public void deleteProximaTest() {
        ProximaEntity entity = data.get(0);
        ap.delete(entity.getId());
        ProximaEntity deleted = em.find(ProximaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void findProximaByID(){
        ProximaEntity entidad = data.get(0);
        ProximaEntity nueva_entidad = ap.find(entidad.getId());
   
        Assert.assertNotNull(nueva_entidad);
        Assert.assertEquals(entidad.getId(), nueva_entidad.getId());
   }
}
