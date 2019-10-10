/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.CatalogoEntity;
import co.edu.uniandes.csw.hackatones.persistence.CatalogoPersistence;
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
 * @author ne.cardenas
 */
@RunWith(Arquillian.class)
public class CatalogoPersistenceTest {
    
    @Inject
    private CatalogoPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    /**
     * datos de catálogo
     */
    private List<CatalogoEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CatalogoEntity.class.getPackage())
                .addPackage(CatalogoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }   
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
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
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from CatalogoEntity").executeUpdate();
    }
    
 

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            CatalogoEntity entity = factory.manufacturePojo(CatalogoEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
    /**
     * Test para crear un catálogo
     */
    @Test
    public void createCatalogoTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CatalogoEntity newEntity = factory.manufacturePojo(CatalogoEntity.class);
        CatalogoEntity ce = cp.create(newEntity);
        
        Assert.assertNotNull(ce);
        
        CatalogoEntity entity = em.find(CatalogoEntity.class, ce.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Prueba para consultar un Catalogo.
     *
     *
     */
    @Test
    public void getCatalogoTest() {        
        CatalogoEntity entity = data.get(0);
        CatalogoEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Prueba para eliminar un Catalogo.
     */
    @Test
    public void deleteCatalogoTest() {
        CatalogoEntity entity = data.get(0);
        cp.delete(entity.getId());
        CatalogoEntity deleted = em.find(CatalogoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Catalogo.
     */
    @Test
    public void updateCatalogoTest() {
        CatalogoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CatalogoEntity newEntity = factory.manufacturePojo(CatalogoEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);

        CatalogoEntity resp = em.find(CatalogoEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
}
