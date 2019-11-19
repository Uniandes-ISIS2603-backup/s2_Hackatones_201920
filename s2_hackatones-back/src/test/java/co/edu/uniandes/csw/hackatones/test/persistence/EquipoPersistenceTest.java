/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.persistence.EquipoPersistence;
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
public class EquipoPersistenceTest {
    
    
       

    
    @Inject
    EquipoPersistence ep;

    
    @PersistenceContext
    EntityManager em;
        
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
        em.createQuery("delete from EquipoEntity").executeUpdate();
    }
      
     private List<EquipoEntity> data = new ArrayList<EquipoEntity>();
    
        
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        
        for (int i = 0; i < 3; i++) {
        EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);

        em.persist(entity);
        data.add(entity);
        }
    }
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EquipoEntity.class.getPackage())
                .addPackage(EquipoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Test
    public void createTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        EquipoEntity equipo = factory.manufacturePojo(EquipoEntity.class);
        EquipoEntity result = ep.create(equipo);
        Assert.assertNotNull(result);
        
        EquipoEntity entity = em.find(EquipoEntity.class, result.getId());
        Assert.assertEquals(equipo.getId(), entity.getId());
        Assert.assertTrue(equipo.equals(entity));
        Assert.assertFalse(equipo.equals(null));
        Assert.assertEquals(equipo.hashCode(), entity.hashCode());
        Assert.assertEquals(equipo.getNombre(), entity.getNombre());
        Assert.assertEquals(equipo.isEsGanador(), entity.isEsGanador());
        Assert.assertEquals(equipo.getHackaton(), entity.getHackaton());
        Assert.assertEquals(equipo.getParticipantes(), entity.getParticipantes());
        
    }
    
    @Test
    public void getTest() {
        EquipoEntity entity = data.get(0);
        EquipoEntity newEntity = ep.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
  
    @Test
    public void deleteTest() {
        EquipoEntity entity = data.get(0);
        ep.delete(entity.getId());
        EquipoEntity deleted = em.find(EquipoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
         
    @Test
    public void updateCatalogoTest() {
        EquipoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);

        newEntity.setId(entity.getId());

        ep.update(newEntity);

        EquipoEntity resp = em.find(EquipoEntity.class, entity.getId());

//        Assert.assertEquals(entity.getNombre(), resp.getNomber());
    }

}
