/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.HackatonTecnologiaLogic;
import co.edu.uniandes.csw.hackatones.ejb.TecnologiaLogic;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
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
 * @hackaton s.estupinan
 */
@RunWith(Arquillian.class)
public class HackatonTecnologiaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private HackatonTecnologiaLogic hackatonTecnologiaLogic;

    @Inject
    private TecnologiaLogic tecnologiaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private HackatonEntity hackaton = new HackatonEntity();
    private List<TecnologiaEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HackatonEntity.class.getPackage())
                .addPackage(TecnologiaEntity.class.getPackage())
                .addPackage(HackatonTecnologiaLogic.class.getPackage())
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
        em.createQuery("delete from TecnologiaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        hackaton = factory.manufacturePojo(HackatonEntity.class);
        hackaton.setId(1L);
        hackaton.setTecnologias(new ArrayList<>());
        em.persist(hackaton);

        for (int i = 0; i < 3; i++) {
            TecnologiaEntity entity = factory.manufacturePojo(TecnologiaEntity.class);
            entity.setHackatones(new ArrayList<>());
            entity.getHackatones().add(hackaton);
            em.persist(entity);
            data.add(entity);
            hackaton.getTecnologias().add(entity);
        }
    }

    /**
     * Prueba para asociar un hackaton a un libro.
     *
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void addTecnologiaTest() throws BusinessLogicException {
        TecnologiaEntity newTecnologia = factory.manufacturePojo(TecnologiaEntity.class);
        tecnologiaLogic.createTecnologia(newTecnologia);
        TecnologiaEntity tecnologiaEntity = hackatonTecnologiaLogic.addTecnologia(hackaton.getId(), newTecnologia.getId());
        Assert.assertNotNull(tecnologiaEntity);

        Assert.assertEquals(tecnologiaEntity.getId(), newTecnologia.getId());
        Assert.assertEquals(tecnologiaEntity.getDescripcion(), newTecnologia.getDescripcion());
        Assert.assertEquals(tecnologiaEntity.getNombre(), newTecnologia.getNombre());


        TecnologiaEntity lastTecnologia = hackatonTecnologiaLogic.getTecnologia(hackaton.getId(), newTecnologia.getId());

        Assert.assertEquals(lastTecnologia.getId(), newTecnologia.getId());
        Assert.assertEquals(lastTecnologia.getNombre(), newTecnologia.getNombre());
        Assert.assertEquals(lastTecnologia.getDescripcion(), newTecnologia.getDescripcion());
    }

    /**
     * Prueba para consultar la lista de Tecnologias de un hackaton.
     */
    @Test
    public void getTecnologiasTest() {
        List<TecnologiaEntity> tecnologiaEntities = hackatonTecnologiaLogic.getTecnologias(hackaton.getId());

        Assert.assertEquals(data.size(), tecnologiaEntities.size());

        for (int i = 0; i < data.size(); i++) {
            Assert.assertTrue(tecnologiaEntities.contains(data.get(0)));
        }
    }

    /**
     * Prueba para consultar un libro de un hackaton.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getTecnologiaTest() throws BusinessLogicException {
        TecnologiaEntity tecnologiaEntity = data.get(0);
        TecnologiaEntity tecnologia = hackatonTecnologiaLogic.getTecnologia(hackaton.getId(), tecnologiaEntity.getId());
        Assert.assertNotNull(tecnologia);

        Assert.assertEquals(tecnologiaEntity.getId(), tecnologia.getId());
        Assert.assertEquals(tecnologiaEntity.getNombre(), tecnologia.getNombre());
        Assert.assertEquals(tecnologiaEntity.getDescripcion(), tecnologia.getDescripcion());

    }

    /**
     * Prueba para actualizar los libros de un hackaton.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void replaceTecnologiasTest() throws BusinessLogicException {
        List<TecnologiaEntity> nuevaLista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TecnologiaEntity entity = factory.manufacturePojo(TecnologiaEntity.class);
            entity.setHackatones(new ArrayList<>());
            entity.getHackatones().add(hackaton);
            tecnologiaLogic.createTecnologia(entity);
            nuevaLista.add(entity);
        }
        hackatonTecnologiaLogic.replaceTecnologias(hackaton.getId(), nuevaLista);
        List<TecnologiaEntity> tecnologiaEntities = hackatonTecnologiaLogic.getTecnologias(hackaton.getId());
        for (TecnologiaEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(tecnologiaEntities.contains(aNuevaLista));
        }
    }

    /**
     * Prueba desasociar un libro con un hackaton.
     *
     */
    @Test
    public void removeTecnologiaTest() {
        for (TecnologiaEntity tecnologia : data) {
            hackatonTecnologiaLogic.removeTecnologia(hackaton.getId(), tecnologia.getId());
            System.out.println(hackatonTecnologiaLogic.getTecnologias(hackaton.getId()).size());
        }
        Assert.assertTrue(hackatonTecnologiaLogic.getTecnologias(hackaton.getId()).isEmpty());
    }
    
}
