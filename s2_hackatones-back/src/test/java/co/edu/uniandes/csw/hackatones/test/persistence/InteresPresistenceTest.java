/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
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
public class InteresPresistenceTest {
   
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(InteresEntity.class)
                .addClass(InteresPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    InteresPersistence ip;

    @Inject
    UserTransaction utx;
    
    @PersistenceContext()
    EntityManager em;
        
    
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
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
        em.createQuery("delete from InteresEntity").executeUpdate();
    }
    
    private List<InteresEntity> data = new ArrayList<InteresEntity>();
    
        
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        
        for (int i = 0; i < 3; i++) {
        InteresEntity entity = factory.manufacturePojo(InteresEntity.class);

        em.persist(entity);
        data.add(entity);
        }
    }
    
  
    
    @Test
    public void createTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        InteresEntity interes = factory.manufacturePojo(InteresEntity.class);
        InteresEntity result = ip.create(interes);
        Assert.assertNotNull(result);
        
       
        InteresEntity entity = em.find(InteresEntity.class, result.getNombre());
        Assert.assertEquals(interes.getId(), entity.getId());
        Assert.assertEquals(interes.getNombre(), entity.getNombre());
        Assert.assertEquals(interes.getDescripcion(), entity.getDescripcion());
    }
    
       @Test
    public void getParticipantesTest() {
        List<InteresEntity> list = ip.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (InteresEntity ent : list) {
            boolean found = false;
            for (InteresEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
            
    @Test
    public void updateTest() {
        InteresEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        InteresEntity newEntity = factory.manufacturePojo(InteresEntity.class);

        newEntity.setId(entity.getId());

        ip.update(newEntity);

        InteresEntity resp = em.find(InteresEntity.class, entity.getId());

     
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
    }
    
    @Test
    public void deleteTest() {
        InteresEntity entity = data.get(0);
        ip.delete(entity.getId());
        InteresEntity deleted = em.find(InteresEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
