/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import co.edu.uniandes.csw.hackatones.persistence.PatrocinadorPersistence;
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
 * @author Santiago Estupinan
 */
@RunWith(Arquillian.class)
public class PatrocinadorPersistanceTest {
    
    @PersistenceContext()
    EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(PatrocinadorEntity.class)
                .addClass(PatrocinadorPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml" );

                }
    
    @Inject
    PatrocinadorPersistence pp;
    
    @Inject
    UserTransaction utx;

    private List<PatrocinadorEntity> data = new ArrayList<>();
    
    @Before
    public void configTest()
    {
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
        PatrocinadorEntity patrocinador = factory.manufacturePojo(PatrocinadorEntity.class);
        PatrocinadorEntity result = pp.create(patrocinador);
        Assert.assertNotNull(result);      
        
        PatrocinadorEntity entity = em.find(PatrocinadorEntity.class,result.getId());
        Assert.assertEquals(patrocinador.getNombre(), entity.getNombre());
        Assert.assertEquals(patrocinador.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(patrocinador.getUbicacion(), entity.getUbicacion());
        Assert.assertEquals(patrocinador.getInfoAdicional(), entity.getInfoAdicional());

    }    
    
    private void clearData() {
        em.createQuery("delete from PatrocinadorEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PatrocinadorEntity entity = factory.manufacturePojo(PatrocinadorEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void getPatrocinadorTest() {
        PatrocinadorEntity entity = data.get(0);
        PatrocinadorEntity newEntity = pp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());

    }

    @Test
    public void updatePatrocinadorTest() {
        PatrocinadorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PatrocinadorEntity newEntity = factory.manufacturePojo(PatrocinadorEntity.class);

        newEntity.setId(entity.getId());

        pp.update(newEntity);

        PatrocinadorEntity resp = em.find(PatrocinadorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    @Test
    public void deletPatrocinadorTest() {
        PatrocinadorEntity entity = data.get(0);
        pp.delete(entity.getId());
        PatrocinadorEntity deleted = em.find(PatrocinadorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
