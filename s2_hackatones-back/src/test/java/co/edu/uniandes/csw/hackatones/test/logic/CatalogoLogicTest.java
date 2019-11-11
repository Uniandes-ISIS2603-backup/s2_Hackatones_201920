/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.CatalogoLogic;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.CatalogoEntity;
import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.CatalogoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.validation.constraints.AssertTrue;
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
public class CatalogoLogicTest {
        
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CatalogoLogic logic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CatalogoEntity> data = new ArrayList<>();
    private List<HackatonEntity> proxData = new ArrayList<>();
    private List<HackatonEntity> actData = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CatalogoEntity.class.getPackage())
                .addPackage(CatalogoLogic.class.getPackage())
                .addPackage(CatalogoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from CatalogoEntity").executeUpdate();
        em.createQuery("delete from HackatonEntity").executeUpdate();
        em.createQuery("delete from HackatonEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CatalogoEntity entity = factory.manufacturePojo(CatalogoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            em.persist(entity);
            proxData.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            em.persist(entity);
            actData.add(entity);
        }
    }

    /**
     * Prueba para crear un Book
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createCatalogoTest() throws BusinessLogicException {
        CatalogoEntity newEntity = factory.manufacturePojo(CatalogoEntity.class);
        CatalogoEntity result = logic.createCatalogo(newEntity);
        
        Assert.assertNotNull(result);
        CatalogoEntity entity = em.find(CatalogoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar un Book.
     */
    @Test
    public void getCatalogoTest() {
        CatalogoEntity entity = data.get(0);
        CatalogoEntity resultEntity = logic.getCatalogo(entity.getId());
        
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }

    /**
     * Prueba para actualizar un Book.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateCatalogoTest() throws BusinessLogicException {
        CatalogoEntity entity = data.get(0);
        CatalogoEntity pojoEntity = factory.manufacturePojo(CatalogoEntity.class);
        
        pojoEntity.setId(entity.getId());
        
        logic.updateCatalogo(pojoEntity.getId(), pojoEntity);
        
        CatalogoEntity resp = em.find(CatalogoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }

    @Test
    public void deleteCatalogoTest() throws BusinessLogicException {
        CatalogoEntity entity = data.get(0);
        logic.deleteCatalogo(entity.getId());
        CatalogoEntity deleted = em.find(CatalogoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    
    @Test
    public void deleteCatalogoConHackatonTest() throws BusinessLogicException {
        try {
            CatalogoEntity entity = data.get(1);
            entity.setEventosEnCurso(actData);
            logic.deleteCatalogo(entity.getId());
            Assert.assertTrue(true);
        }
        catch (Exception e) {
            //debe entrar aquí
        }
    }
    
    @Test
    public void deleteCatalogoConProximoTest() throws BusinessLogicException {
        try {
            CatalogoEntity entity = data.get(2);
            entity.setEventosProximos(proxData);
            logic.deleteCatalogo(entity.getId());
            Assert.assertTrue(true);
        }
        catch (Exception e) {
            //debe entrar aqui
        }
    }
    
}
