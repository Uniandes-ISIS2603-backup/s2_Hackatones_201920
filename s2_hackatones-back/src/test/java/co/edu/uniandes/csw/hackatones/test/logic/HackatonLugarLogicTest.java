/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.HackatonLugarLogic;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.LugarPersistence;
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
public class HackatonLugarLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private HackatonLugarLogic HackatonLugarLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<LugarEntity> data = new ArrayList<>();

    private List<HackatonEntity> HackatonsData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LugarEntity.class.getPackage())
                .addPackage(HackatonLugarLogic.class.getPackage())
                .addPackage(LugarPersistence.class.getPackage())
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
        em.createQuery("delete from HackatonEntity").executeUpdate();
        em.createQuery("delete from LugarEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            HackatonEntity Hackatons = factory.manufacturePojo(HackatonEntity.class);
            em.persist(Hackatons);
            HackatonsData.add(Hackatons);
        }
        for (int i = 0; i < 3; i++) {
            LugarEntity entity = factory.manufacturePojo(LugarEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                HackatonsData.get(i).setLugar(entity);
            }
        }
    }

    /**
     * Prueba para asociar un Hackatons existente a un Lugar.
     */
    @Test
    public void addLugarTest() {
        LugarEntity entity = data.get(0);
        HackatonEntity HackatonEntity = HackatonsData.get(1);
        LugarEntity response = HackatonLugarLogic.addLugar(entity.getId(), HackatonEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un Lugar.
     */
    @Test
    public void getLugarTest() {
        HackatonEntity entity = HackatonsData.get(0);
        LugarEntity resultEntity = HackatonLugarLogic.getLugar(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getLugar().getId(), resultEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Hackatons asociadas a una instancia
     * de Lugar.
     */
    @Test
    public void replaceLugarTest() {
        LugarEntity entity = data.get(0);
        HackatonLugarLogic.replaceLugar(HackatonsData.get(1).getId(), entity.getId());
        entity = HackatonLugarLogic.getLugar(HackatonsData.get(1).getId());
    }

    /**
     * Prueba para desasociar un Hackaton existente de un Lugar existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void removeHackatonTest() throws BusinessLogicException {
        HackatonLugarLogic.removeLugar(HackatonsData.get(0).getId());
        Assert.assertNull(HackatonLugarLogic.getLugar(HackatonsData.get(0).getId()));
    }

    /**
     * Prueba para desasociar un Hackaton existente de un Lugar existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removeHackatonSinLugarTest() throws BusinessLogicException {
        HackatonLugarLogic.removeLugar(HackatonsData.get(1).getId());
    }
    
}
