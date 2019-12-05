/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import co.edu.uniandes.csw.hackatones.persistence.LenguajePersistence;
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
 * @author Santiago Estupiñan
 */
@RunWith(Arquillian.class)
public class LenguajePersistenceTest {
    /**
     * Crea el deployment del test
     * @return 
     */
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LenguajeEntity.class.getPackage())
                .addPackage(LenguajePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    /**
     * Persistencia del test
     */
    @Inject
    LenguajePersistence lp;
    /**
     * Entity manager del test
     */
    @PersistenceContext()
    protected EntityManager em;
    /**
     * User transaction
     */
    @Inject
    UserTransaction utx;
    /**
     * Lista de los datos a usar
     */
    private List<LenguajeEntity> data = new ArrayList<>();
    /**
     * Configura los datos del test
     */
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
    

    /**
     * Prueba la creacion de un lenguaje
     */
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        LenguajeEntity lenguaje = factory.manufacturePojo(LenguajeEntity.class);
        LenguajeEntity result = lp.create(lenguaje);
        Assert.assertNotNull(result);      
        
        LenguajeEntity entity = em.find(LenguajeEntity.class,result.getId());
        Assert.assertTrue(lenguaje.equals(entity));
        Assert.assertFalse(lenguaje.equals(null));
        Assert.assertEquals(lenguaje.hashCode(), entity.hashCode());
        Assert.assertEquals(lenguaje.getId(), entity.getId());
        Assert.assertEquals(lenguaje.getName(), entity.getName());
        Assert.assertEquals(lenguaje.getImagen(), entity.getImagen());
        Assert.assertEquals(lenguaje.getHackatones(), entity.getHackatones());

    }    
    /**
     * Borra datos anteriores
     */
    private void clearData() {
        em.createQuery("delete from LenguajeEntity").executeUpdate();
    }
    /**
     * Inserta los datos a probar
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            LenguajeEntity entity = factory.manufacturePojo(LenguajeEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba la busqueda de un lenguaje
     */
    @Test
    public void getLenguajeTest() {
        LenguajeEntity entity = data.get(0);
        LenguajeEntity newEntity = lp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        

    }
    /**
     * Prueba la actualizacion de los datos de un lenguaje
     */
    @Test
    public void updateLenguajeTest() {
        LenguajeEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);

        newEntity.setId(entity.getId());

        lp.update(newEntity);

        LenguajeEntity resp = em.find(LenguajeEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    /**
     * Prueba la eliminacion de un lenguaje
     */
    @Test
    public void deleteLenguajeTest() {
        LenguajeEntity entity = data.get(0);
        lp.delete(entity.getId());
        LenguajeEntity deleted = em.find(LenguajeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
