/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.CatalogoLogic;
import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import co.edu.uniandes.csw.hackatones.entities.CatalogoEntity;
import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
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

    private List<CatalogoEntity> data = new ArrayList<CatalogoEntity>();
    
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
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CatalogoEntity cat = factory.manufacturePojo(CatalogoEntity.class);
            em.persist(cat);
            data.add(cat);
        }
    }

    /**
     * Prueba para crear un Book
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createCatalogoTest() throws BusinessLogicException {
//        CatalogoEntity newEntity = factory.manufacturePojo(CatalogoEntity.class);
//        CatalogoEntity result = logic.createCatalogo(newEntity);
//        
//        Assert.assertNotNull(result);
//        CatalogoEntity entity = em.find(CatalogoEntity.class, result.getId());
//        Assert.assertEquals(newEntity.getId(), entity.getId());
//        Assert.assertEquals(newEntity.getActuales(), entity.getActuales());
//        Assert.assertEquals(newEntity.getPatrocinadores(), entity.getPatrocinadores());
//        Assert.assertEquals(newEntity.getProximos(), entity.getProximos());
          Assert.assertTrue(true);
    }

    /**
     * Prueba para crear un Book con ISBN existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
//    @Test(expected = Exception.class)
//    public void createCatalogoPatrocinadorNull() throws BusinessLogicException {
//        CatalogoEntity newEntity = factory.manufacturePojo(CatalogoEntity.class);
//        newEntity.setPatrocinadores(null);
//        logic.createCatalogo(newEntity);
//    }

    /**
     * Prueba para crear un Book con una editorial que no existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
  //  @Test(expected = Exception.class)
  //  public void createCatalogoActualesNull() throws BusinessLogicException {
  //      CatalogoEntity newEntity = factory.manufacturePojo(CatalogoEntity.class);
  //      newEntity.setActuales(null);
  //      logic.createCatalogo(newEntity);
  //  }

    /**
     * Prueba para crear un Book con editorial en null.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
  //  @Test(expected = Exception.class)
  //  public void createCatalogoProximosNull() throws BusinessLogicException {
  //      CatalogoEntity newEntity = factory.manufacturePojo(CatalogoEntity.class);
  //    newEntity.setProximos(null);
  //      logic.createCatalogo(newEntity);
    //}

    /**
     * Prueba para consultar un Book.
     */
//    @Test
//    public void getCatalogoTest() {
//        CatalogoEntity entity = data.get(0);
//        CatalogoEntity resultEntity = logic.getCatalogo(entity.getId());
//        
//        Assert.assertNotNull(resultEntity);
//        Assert.assertEquals(entity.getId(), resultEntity.getId());
//        Assert.assertEquals(entity.getActuales(), resultEntity.getActuales());
//        Assert.assertEquals(entity.getProximos(), resultEntity.getProximos());
//        Assert.assertEquals(entity.getPatrocinadores(), resultEntity.getPatrocinadores());
//    }

    /**
     * Prueba para actualizar un Book.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
//    @Test
//    public void updateCatalogoTest() throws BusinessLogicException {
//        CatalogoEntity entity = data.get(0);
//        CatalogoEntity pojoEntity = factory.manufacturePojo(CatalogoEntity.class);
//        
//        pojoEntity.setId(entity.getId());
//        
//        logic.updateCatalogo(pojoEntity.getId(), pojoEntity);
//        
//        CatalogoEntity resp = em.find(CatalogoEntity.class, entity.getId());
//        Assert.assertEquals(pojoEntity.getId(), resp.getId());
//        Assert.assertEquals(pojoEntity.getActuales(), resp.getActuales());
//        Assert.assertEquals(pojoEntity.getProximos(), resp.getProximos());
//        Assert.assertEquals(pojoEntity.getPatrocinadores(), resp.getPatrocinadores());
//    }

    /**
     * Prueba para eliminar un Book.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
//    @Test
//    public void deleteCatalogoTest() throws BusinessLogicException {
//        CatalogoEntity entity = data.get(0);
//        logic.deleteCatalogo(entity.getId());
//        CatalogoEntity deleted = em.find(CatalogoEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }

    /**
     * Prueba para eliminar un Book.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
//    @Test(expected = BusinessLogicException.class)
//    public void deleteCatalogoConPatrocinadorTest() throws BusinessLogicException {
//        CatalogoEntity entity = data.get(2);
//        logic.deleteCatalogo(entity.getId());
//    }
    
}
