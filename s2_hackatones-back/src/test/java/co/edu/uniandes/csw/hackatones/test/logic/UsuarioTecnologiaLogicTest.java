/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.TecnologiaLogic;
import co.edu.uniandes.csw.hackatones.ejb.UsuarioTecnologiaLogic;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.UsuarioPersistence;
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
public class UsuarioTecnologiaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private UsuarioTecnologiaLogic usuarioTecnologiaLogic;

    @Inject
    private TecnologiaLogic tecnologiaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private UsuarioEntity usuario = new UsuarioEntity();
    private List<TecnologiaEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(TecnologiaEntity.class.getPackage())
                .addPackage(UsuarioTecnologiaLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from TecnologiaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        usuario = factory.manufacturePojo(UsuarioEntity.class);
        usuario.setId(1L);
        usuario.setTecnologias(new ArrayList<>());
        em.persist(usuario);

        for (int i = 0; i < 3; i++) {
            TecnologiaEntity entity = factory.manufacturePojo(TecnologiaEntity.class);
            entity.setParticipantes(new ArrayList<>());
            entity.getParticipantes().add(usuario);
            em.persist(entity);
            data.add(entity);
            usuario.getTecnologias().add(entity);
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
        TecnologiaEntity tecnologiaEntity = usuarioTecnologiaLogic.addTecnologia(usuario.getId(), newTecnologia.getId());
        Assert.assertNotNull(tecnologiaEntity);

        Assert.assertEquals(tecnologiaEntity.getId(), newTecnologia.getId());
        Assert.assertEquals(tecnologiaEntity.getDescripcion(), newTecnologia.getDescripcion());
        Assert.assertEquals(tecnologiaEntity.getNombre(), newTecnologia.getNombre());

        TecnologiaEntity lastTecnologia = usuarioTecnologiaLogic.getTecnologia(usuario.getId(), newTecnologia.getId());

        Assert.assertEquals(lastTecnologia.getId(), newTecnologia.getId());
        Assert.assertEquals(lastTecnologia.getNombre(), newTecnologia.getNombre());
        Assert.assertEquals(lastTecnologia.getDescripcion(), newTecnologia.getDescripcion());
    }

    /**
     * Prueba para consultar la lista de Tecnologias de un hackaton.
     */
    @Test
    public void getTecnologiasTest() {
        List<TecnologiaEntity> tecnologiaEntities = usuarioTecnologiaLogic.getTecnologias(usuario.getId());

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
        TecnologiaEntity tecnologia = usuarioTecnologiaLogic.getTecnologia(usuario.getId(), tecnologiaEntity.getId());
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
            entity.setParticipantes(new ArrayList<>());
            entity.getParticipantes().add(usuario);
            tecnologiaLogic.createTecnologia(entity);
            nuevaLista.add(entity);
        }
        usuarioTecnologiaLogic.replaceTecnologias(usuario.getId(), nuevaLista);
        List<TecnologiaEntity> tecnologiaEntities = usuarioTecnologiaLogic.getTecnologias(usuario.getId());
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
            usuarioTecnologiaLogic.removeTecnologia(usuario.getId(), tecnologia.getId());
            System.out.println(usuarioTecnologiaLogic.getTecnologias(usuario.getId()).size());
        }
        Assert.assertTrue(usuarioTecnologiaLogic.getTecnologias(usuario.getId()).isEmpty());
    }
    
}
