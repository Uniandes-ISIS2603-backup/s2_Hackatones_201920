/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.HackatonInteresLogic;
import co.edu.uniandes.csw.hackatones.ejb.HackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.InteresLogic;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import co.edu.uniandes.csw.hackatones.persistence.InteresPersistence;
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
public class HackatonInteresLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private HackatonInteresLogic hackatonInteresLogic;

    @Inject
    private InteresLogic interesLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private HackatonEntity hackaton = new HackatonEntity();
    private List<InteresEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HackatonEntity.class.getPackage())
                .addPackage(InteresEntity.class.getPackage())
                .addPackage(HackatonInteresLogic.class.getPackage())
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
        
        em.createQuery("delete from HackatonEntity").executeUpdate();
        em.createQuery("delete from InteresEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        hackaton = factory.manufacturePojo(HackatonEntity.class);
        hackaton.setId(1L);
        hackaton.setIntereses(new ArrayList<>());
        em.persist(hackaton);

        for (int i = 0; i < 3; i++) {
            InteresEntity entity = factory.manufacturePojo(InteresEntity.class);
            entity.setHackatones(new ArrayList<>());
            entity.getHackatones().add(hackaton);
            em.persist(entity);
            data.add(entity);
            hackaton.getIntereses().add(entity);
        }
    }

    /**
     * Prueba para asociar un autor a un libro.
     *
     *
     * @throws co.edu.uniandes.csw.interesstore.exceptions.BusinessLogicException
     */
    @Test
    public void addInteresTest() throws BusinessLogicException {
        
        InteresEntity newInteres = factory.manufacturePojo(InteresEntity.class);
        interesLogic.createInteres(newInteres);
        InteresEntity interesEntity = hackatonInteresLogic.addInteres(hackaton.getId(), newInteres.getId());
        Assert.assertNotNull(interesEntity);

        Assert.assertEquals(interesEntity.getId(), newInteres.getId());
        
        InteresEntity lastInteres = hackatonInteresLogic.getInteres(hackaton.getId(), newInteres.getId());

        Assert.assertEquals(lastInteres.getId(), newInteres.getId());
    }

    /**
     * Prueba para consultar la lista de Interess de un autor.
     */
    @Test
    public void getInteressTest() {
        List<InteresEntity> interesEntities = hackatonInteresLogic.getInteress(hackaton.getId());

        Assert.assertEquals(data.size(), interesEntities.size());

        for (int i = 0; i < data.size(); i++) {
            Assert.assertTrue(interesEntities.contains(data.get(0)));
        }
    }

    /**
     * Prueba para cpnsultar un libro de un autor.
     *
     * @throws co.edu.uniandes.csw.interesstore.exceptions.BusinessLogicException
     */
    @Test
    public void getInteresTest() throws BusinessLogicException {
        InteresEntity interesEntity = data.get(0);
        InteresEntity interes = hackatonInteresLogic.getInteres(hackaton.getId(), interesEntity.getId());
        Assert.assertNotNull(interes);

        Assert.assertEquals(interesEntity.getId(), interes.getId());
    }

    /**
     * Prueba para actualizar los libros de un autor.
     *
     * @throws co.edu.uniandes.csw.interesstore.exceptions.BusinessLogicException
     */
    @Test
    public void replaceInteressTest() throws BusinessLogicException {
        List<InteresEntity> nuevaLista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            InteresEntity entity = factory.manufacturePojo(InteresEntity.class);
            entity.setHackatones(new ArrayList<>());
            entity.getHackatones().add(hackaton);
            interesLogic.createInteres(entity);
            nuevaLista.add(entity);
        }
        hackatonInteresLogic.replaceInteress(hackaton.getId(), nuevaLista);
        List<InteresEntity> interesEntities = hackatonInteresLogic.getInteress(hackaton.getId());
        for (InteresEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(interesEntities.contains(aNuevaLista));
        }
    }

    /**
     * Prueba desasociar un libro con un autor.
     *
     */
    @Test
    public void removeInteresTest() {
        for (InteresEntity interes : data) {
            hackatonInteresLogic.removeInteres(hackaton.getId(), interes.getId());
        }
        Assert.assertTrue(hackatonInteresLogic.getInteress(hackaton.getId()).isEmpty());
    }
}
