/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.HackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.LenguajeHackatonLogic;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
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
 * @lenguaje a.pedraza
 */
@RunWith(Arquillian.class)
public class LenguajeHackatonLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private LenguajeHackatonLogic lenguajeHackatonLogic;

    @Inject
    private HackatonLogic hackatonLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private LenguajeEntity lenguaje = new LenguajeEntity();
    private List<HackatonEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LenguajeEntity.class.getPackage())
                .addPackage(HackatonEntity.class.getPackage())
                .addPackage(LenguajeHackatonLogic.class.getPackage())
                .addPackage(LenguajePersistence.class.getPackage())
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
        
        em.createQuery("delete from LenguajeEntity").executeUpdate();
        em.createQuery("delete from HackatonEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        lenguaje = factory.manufacturePojo(LenguajeEntity.class);
        lenguaje.setId(1L);
        lenguaje.setHackatones(new ArrayList<>());
        em.persist(lenguaje);

        for (int i = 0; i < 3; i++) {
            HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            entity.setLenguajes(new ArrayList<>());
            entity.getLenguajes().add(lenguaje);
            em.persist(entity);
            data.add(entity);
            lenguaje.getHackatones().add(entity);
        }
    }

    /**
     * Prueba para asociar un autor a un libro.
     *
     *
     * @throws co.edu.uniandes.csw.hackatonstore.exceptions.BusinessLogicException
     */
    @Test
    public void addHackatonTest() throws BusinessLogicException {
        
        HackatonEntity newHackaton = factory.manufacturePojo(HackatonEntity.class);
        hackatonLogic.createHackaton(newHackaton);
        HackatonEntity hackatonEntity = lenguajeHackatonLogic.addHackaton(lenguaje.getId(), newHackaton.getId());
        Assert.assertNotNull(hackatonEntity);

        Assert.assertEquals(hackatonEntity.getId(), newHackaton.getId());
        
        HackatonEntity lastHackaton = lenguajeHackatonLogic.getHackaton(lenguaje.getId(), newHackaton.getId());

        Assert.assertEquals(lastHackaton.getId(), newHackaton.getId());
    }

    /**
     * Prueba para consultar la lista de Hackatons de un autor.
     */
    @Test
    public void getHackatonsTest() {
        List<HackatonEntity> hackatonEntities = lenguajeHackatonLogic.getHackatones(lenguaje.getId());

        Assert.assertEquals(data.size(), hackatonEntities.size());

        for (int i = 0; i < data.size(); i++) {
            Assert.assertTrue(hackatonEntities.contains(data.get(0)));
        }
    }

    /**
     * Prueba para cpnsultar un libro de un autor.
     *
     * @throws co.edu.uniandes.csw.hackatonstore.exceptions.BusinessLogicException
     */
    @Test
    public void getHackatonTest() throws BusinessLogicException {
        HackatonEntity hackatonEntity = data.get(0);
        HackatonEntity hackaton = lenguajeHackatonLogic.getHackaton(lenguaje.getId(), hackatonEntity.getId());
        Assert.assertNotNull(hackaton);

        Assert.assertEquals(hackatonEntity.getId(), hackaton.getId());
    }

    /**
     * Prueba para actualizar los libros de un autor.
     *
     * @throws co.edu.uniandes.csw.hackatonstore.exceptions.BusinessLogicException
     */
    @Test
    public void replaceHackatonsTest() throws BusinessLogicException {
        List<HackatonEntity> nuevaLista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            entity.setLenguajes(new ArrayList<>());
            entity.getLenguajes().add(lenguaje);
            hackatonLogic.createHackaton(entity);
            nuevaLista.add(entity);
        }
        lenguajeHackatonLogic.replaceHackatones(lenguaje.getId(), nuevaLista);
        List<HackatonEntity> hackatonEntities = lenguajeHackatonLogic.getHackatones(lenguaje.getId());
        for (HackatonEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(hackatonEntities.contains(aNuevaLista));
        }
    }

    /**
     * Prueba desasociar un libro con un autor.
     *
     */
    @Test
    public void removeHackatonTest() {
        for (HackatonEntity hackaton : data) {
            lenguajeHackatonLogic.removeHackaton(lenguaje.getId(), hackaton.getId());
        }
        Assert.assertTrue(lenguajeHackatonLogic.getHackatones(lenguaje.getId()).isEmpty());
    }
}
