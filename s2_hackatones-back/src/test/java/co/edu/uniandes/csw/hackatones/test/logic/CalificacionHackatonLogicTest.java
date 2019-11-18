/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.CalificacionHackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.CalificacionLogic;
import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
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
 * @author s.estupinan
 */
@RunWith(Arquillian.class)
public class CalificacionHackatonLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CalificacionLogic calificacionLogic;
    @Inject
    private CalificacionHackatonLogic calificacionHackatonLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<HackatonEntity> data = new ArrayList<HackatonEntity>();

    private List<CalificacionEntity> calificacionData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HackatonEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
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
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from HackatonEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CalificacionEntity calificaciones = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(calificaciones);
            calificacionData.add(calificaciones);
        }
        for (int i = 0; i < 3; i++) {
            HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                calificacionData.get(i).setHackaton(entity);
            }
        }
    }

    /**
     * Prueba para remplazar las instancias de calificaciones asociadas a una instancia
     * de hackaton.
     */
    @Test
    public void replaceHackatonTest() {
        CalificacionEntity entity = calificacionData.get(0);
        calificacionHackatonLogic.replacehackaton(entity.getId(), data.get(1).getId());
        entity = calificacionLogic.getCalificacion(entity.getId());
        Assert.assertEquals(entity.getHackaton(), data.get(1));
    }

    /**
     * Prueba para desasociar un Book existente de un hackaton existente
     *
     * @throws co.edu.uniandes.csw.calificacionestore.exceptions.BusinessLogicException
     */
//    @Test
//    public void removeCalificacionesTest() throws BusinessLogicException {
//        calificacionHackatonLogic.removeHackaton(calificacionData.get(0).getId());
//        CalificacionEntity response = calificacionLogic.getCalificacion(calificacionData.get(0).getId());
//        Assert.assertNull(response.getHackaton());
//    }
//    
}
