/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.persistence.ActualPersistence;
import co.edu.uniandes.csw.hackatones.persistence.EquipoPersistence;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import co.edu.uniandes.csw.hackatones.persistence.UsuarioPersistence;
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
public class ActualPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActualEntity.class.getPackage())
                .addPackage(ActualPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    ActualPersistence ap;
    
    @PersistenceContext()
    protected EntityManager em;
    
     @Inject
    UserTransaction utx;

    private List<ActualEntity> data = new ArrayList<>();
    
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
        ActualEntity actual = factory.manufacturePojo(ActualEntity.class);
        ActualEntity result = ap.create(actual);
        Assert.assertNotNull(result);      
        
        ActualEntity entity = em.find(ActualEntity.class,result.getId());
        Assert.assertEquals(actual.getId(), entity.getId());
        Assert.assertEquals(actual.getReglas(), entity.getReglas());
        Assert.assertEquals(actual.getRestricciones(), entity.getRestricciones());
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
    public void getActualesTest() {
        List<ActualEntity> list = ap.findAll();
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
        ActualEntity newEntity = ap.find(entity.getId());
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

        ap.update(newEntity);

        ActualEntity resp = em.find(ActualEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getReglas(), resp.getReglas());
        Assert.assertEquals(newEntity.getRestricciones(), resp.getRestricciones());
    }

    @Test
    public void deleteActualTest() {
        ActualEntity entity = data.get(0);
        ap.delete(entity.getId());
        ActualEntity deleted = em.find(ActualEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void findActualByID(){
        ActualEntity entidad = data.get(0);
        ActualEntity nueva_entidad = ap.find(entidad.getId());
   
        Assert.assertNotNull(nueva_entidad);
        Assert.assertEquals(entidad.getId(), nueva_entidad.getId());
   }
}
