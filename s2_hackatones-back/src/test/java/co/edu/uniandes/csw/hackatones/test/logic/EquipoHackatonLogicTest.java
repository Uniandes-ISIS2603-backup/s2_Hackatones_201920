/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.EquipoHackatonLogic;
import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
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
public class EquipoHackatonLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private EquipoHackatonLogic EquipoHackatonLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<HackatonEntity> data = new ArrayList<>();

    private List<EquipoEntity> EquiposData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HackatonEntity.class.getPackage())
                .addPackage(EquipoHackatonLogic.class.getPackage())
                .addPackage(HackatonPersistence.class.getPackage())
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
        em.createQuery("delete from EquipoEntity").executeUpdate();
        em.createQuery("delete from HackatonEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EquipoEntity Equipos = factory.manufacturePojo(EquipoEntity.class);
            em.persist(Equipos);
            EquiposData.add(Equipos);
        }
        for (int i = 0; i < 3; i++) {
            HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                EquiposData.get(i).setHackaton(entity);
            }
        }
    }

    /**
     * Prueba para asociar un Equipos existente a un Hackaton.
     */
    @Test
    public void addHackatonTest() {
        HackatonEntity entity = data.get(0);
        EquipoEntity EquipoEntity = EquiposData.get(1);
        HackatonEntity response = EquipoHackatonLogic.addHackaton(entity.getId(), EquipoEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un Hackaton.
     */
    @Test
    public void getHackatonTest() {
        EquipoEntity entity = EquiposData.get(0);
        HackatonEntity resultEntity = EquipoHackatonLogic.getHackaton(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getHackaton().getId(), resultEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Equipos asociadas a una instancia
     * de Hackaton.
     */
    @Test
    public void replaceHackatonTest() {
        HackatonEntity entity = data.get(0);
        EquipoHackatonLogic.replaceHackaton(EquiposData.get(1).getId(), entity.getId());
        entity = EquipoHackatonLogic.getHackaton(EquiposData.get(1).getId());
        Assert.assertTrue(entity.getEquipos().contains(EquiposData.get(1)));
    }

    /**
     * Prueba para desasociar un Equipo existente de un Hackaton existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void removeEquipoTest() throws BusinessLogicException {
        EquipoHackatonLogic.removeHackaton(EquiposData.get(0).getId());
        Assert.assertNull(EquipoHackatonLogic.getHackaton(EquiposData.get(0).getId()));
    }

    /**
     * Prueba para desasociar un Equipo existente de un Hackaton existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removeEquipoSinHackatonTest() throws BusinessLogicException {
        EquipoHackatonLogic.removeHackaton(EquiposData.get(1).getId());
    }
}
