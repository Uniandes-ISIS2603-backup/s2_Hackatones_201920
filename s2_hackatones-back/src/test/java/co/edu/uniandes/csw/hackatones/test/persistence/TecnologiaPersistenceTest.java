/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.persistence.InteresPersistence;
import co.edu.uniandes.csw.hackatones.persistence.TecnologiaPersistence;
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
public class TecnologiaPersistenceTest {
       

    
    @Inject
    TecnologiaPersistence cp;

    
    @PersistenceContext
    EntityManager em;
        
    
        /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    

    
     
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
        em.createQuery("delete from TecnologiaEntity").executeUpdate();
    }
    
   private List<TecnologiaEntity> data = new ArrayList<TecnologiaEntity>();
    
        
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        
        for (int i = 0; i < 3; i++) {
        TecnologiaEntity entity = factory.manufacturePojo(TecnologiaEntity.class);

        em.persist(entity);
        data.add(entity);
        }
    }
    

    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(TecnologiaEntity.class)
                .addClass(TecnologiaPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Test
    public void createTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        TecnologiaEntity tecnologia = factory.manufacturePojo(TecnologiaEntity.class);
        TecnologiaEntity result = cp.create(tecnologia);
        Assert.assertNotNull(result);
        
        TecnologiaEntity entity = em.find(TecnologiaEntity.class, result.getId());
        Assert.assertEquals(tecnologia.getNombre(), entity.getNombre());
        Assert.assertEquals(tecnologia.getInteresados(), entity.getInteresados());
    }
    

    @Test
    public void getTest() {
        TecnologiaEntity entity = data.get(0);
        TecnologiaEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getInteresados(), entity.getInteresados());
    }
    
        
    @Test
    public void updateTest() {
        TecnologiaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TecnologiaEntity newEntity = factory.manufacturePojo(TecnologiaEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);

        TecnologiaEntity resp = em.find(TecnologiaEntity.class, entity.getId());

     
        //Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        //Assert.assertEquals(newEntity.getInteresados(), resp.getInteresados());
    }
    
    @Test
    public void deleteTest() {
        TecnologiaEntity entity = data.get(0);
        cp.delete(entity.getId());
        TecnologiaEntity deleted = em.find(TecnologiaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    
}
