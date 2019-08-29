/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import co.edu.uniandes.csw.hackatones.persistence.CalificacionPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author a.pedraza
 */
@RunWith(Arquillian.class)
public class CalificacionPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CalificacionEntity.class)
                .addClass(CalificacionPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    CalificacionPersistence cp;
    
    @PersistenceContext()
    protected EntityManager em;
    
     @Inject
    UserTransaction utx;

    private List<CalificacionEntity> data = new ArrayList<>();
    
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
        CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result = cp.create(calificacion);
        Assert.assertNotNull(result);      
        
        CalificacionEntity entity = em.find(CalificacionEntity.class,result.getId());
        Assert.assertEquals(calificacion.getId(), entity.getId());
        Assert.assertEquals(calificacion.getComentario(), entity.getComentario());
        Assert.assertEquals(calificacion.getCalificacion(), entity.getCalificacion());
    }    
    
    private void clearData() {
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void getCalificacionesTest() {
        List<CalificacionEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CalificacionEntity ent : list) {
            boolean found = false;
            for (CalificacionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
        Assert.assertEquals(newEntity.getCalificacion(), entity.getCalificacion());
    }

    @Test
    public void updateCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);

        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getCalificacion(), resp.getCalificacion());
    }

    @Test
    public void deleteCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        cp.delete(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void findCalificacionByID(){
        CalificacionEntity entidad = data.get(0);
        CalificacionEntity nueva_entidad = cp.find(entidad.getId());
   
        Assert.assertNotNull(nueva_entidad);
        Assert.assertEquals(entidad.getId(), nueva_entidad.getId());
   }
}
