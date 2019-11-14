/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.HackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.UsuarioHackatonLogic;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
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
 * @author jc.higuera
 */
@RunWith(Arquillian.class)
public class UsuarioHackatonLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private UsuarioHackatonLogic usuarioHackatonLogic;

    @Inject
    private HackatonLogic hackatonLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private UsuarioEntity usuario = new UsuarioEntity();
    private List<HackatonEntity> data = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(HackatonEntity.class.getPackage())
                .addPackage(UsuarioHackatonLogic.class.getPackage())
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
        em.createQuery("delete from HackatonEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        usuario = factory.manufacturePojo(UsuarioEntity.class);
        usuario.setId(1L);
        usuario.setHackatones(new ArrayList<>());
        em.persist(usuario);

        for (int i = 0; i < 3; i++) {
            HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            entity.setInscritos(new ArrayList<>());
            entity.getInscritos().add(usuario);
            em.persist(entity);
            data.add(entity);
            usuario.getHackatones().add(entity);
        }
    }
    
    /**
     * Prueba para asociar un usuario a una hackaton.
     *
     *
     * @throws BusinessLogicException
     */
    
    @Test
    public void addHackatonTest() throws BusinessLogicException {
        HackatonEntity newHackaton = factory.manufacturePojo(HackatonEntity.class);
        hackatonLogic.createHackaton(newHackaton);
        HackatonEntity hackatonEntity = usuarioHackatonLogic.addHackaton(usuario.getId(), newHackaton.getId());
        Assert.assertNotNull(hackatonEntity);

        Assert.assertEquals(hackatonEntity.getId(), newHackaton.getId());
        
        HackatonEntity lastHackaton = usuarioHackatonLogic.getHackaton(usuario.getId(), newHackaton.getId());

        Assert.assertEquals(lastHackaton.getId(), newHackaton.getId());
    }
    
    /**
     * Prueba para consultar la lista de Hackatones de un usuario.
     */
    @Test
    public void getHackatonesTest() {
        List<HackatonEntity> hackatonEntities = usuarioHackatonLogic.getHackatones(usuario.getId());

        Assert.assertEquals(data.size(), hackatonEntities.size());

        for (int i = 0; i < data.size(); i++) {
            Assert.assertTrue(hackatonEntities.contains(data.get(0)));
        }
    }
    
    /**
     * Prueba para consultar una hackaton de un usuario.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void getHackatonTest() throws BusinessLogicException {
        HackatonEntity hackatonEntity = data.get(0);
        HackatonEntity hackaton = usuarioHackatonLogic.getHackaton(usuario.getId(), hackatonEntity.getId());
        Assert.assertNotNull(hackaton);

        Assert.assertEquals(hackatonEntity.getId(), hackaton.getId());
        
    }
    
    /**
     * Prueba para actualizar las hackatones de un usuario.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void replaceHackatonesTest() throws BusinessLogicException {
        List<HackatonEntity> nuevaLista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            entity.setInscritos(new ArrayList<>());
            entity.getInscritos().add(usuario);
            hackatonLogic.createHackaton(entity);
            nuevaLista.add(entity);
        }
        usuarioHackatonLogic.replaceHackaton(usuario.getId(), nuevaLista);
        List<HackatonEntity> hackatonEntities = usuarioHackatonLogic.getHackatones(usuario.getId());
        for (HackatonEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(hackatonEntities.contains(aNuevaLista));
        }
    }
    
    /**
     * Prueba desasociar una hackaton con un usuario.
     *
     */
    @Test
    public void removeHackatonTest() {
        for (HackatonEntity hackaton : data) {
            usuarioHackatonLogic.removeHackaton(usuario.getId(), hackaton.getId());
        }
        System.out.println(usuarioHackatonLogic.getHackatones(usuario.getId()).size());
        Assert.assertTrue(usuarioHackatonLogic.getHackatones(usuario.getId()).isEmpty());
    }
}
