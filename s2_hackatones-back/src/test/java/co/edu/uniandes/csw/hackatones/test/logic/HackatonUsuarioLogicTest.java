/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.HackatonUsuarioLogic;
import co.edu.uniandes.csw.hackatones.ejb.UsuarioLogic;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
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
 * @author jc.higuera
 */
@RunWith(Arquillian.class)
public class HackatonUsuarioLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private HackatonUsuarioLogic hackatonUsuarioLogic;

    @Inject
    private UsuarioLogic usuarioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private HackatonEntity hackaton = new HackatonEntity();
    private List<UsuarioEntity> data = new ArrayList<>();
    
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
                .addPackage(HackatonUsuarioLogic.class.getPackage())
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from HackatonEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        

        hackaton = factory.manufacturePojo(HackatonEntity.class);
        hackaton.setId(1L);
        hackaton.setLimiteParticipantes(Integer.MAX_VALUE);
        hackaton.setInscritos(new ArrayList<>());
        em.persist(hackaton);

        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            entity.setHackatones(new ArrayList<>());
            entity.getHackatones().add(hackaton);
            em.persist(entity);
            data.add(entity);
            hackaton.getInscritos().add(entity);
        }
    }
    
    /**
     * Prueba para asociar un hackaton a un usuario.
     *
     *
     * @throws BusinessLogicException
     */
    @Test
    public void addUsuarioTest() throws BusinessLogicException {
        
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        usuarioLogic.createUsuario(newUsuario);
        UsuarioEntity usuarioEntity = hackatonUsuarioLogic.addUsuario(hackaton.getId(), newUsuario.getId());
        Assert.assertNotNull(usuarioEntity);
        

        Assert.assertEquals(usuarioEntity.getId(), newUsuario.getId());
       
        
         
        UsuarioEntity lastUsuario = hackatonUsuarioLogic.getUsuario(hackaton.getId(), newUsuario.getId());



        Assert.assertEquals(lastUsuario.getId(), newUsuario.getId());
        
    }
    
    /**
     * Prueba para consultar la lista de Usuarios de un hackaton.
     */
    @Test
    public void getUsuariosTest() {
        List<UsuarioEntity> usuarioEntities = hackatonUsuarioLogic.getUsuarios(hackaton.getId());

        Assert.assertEquals(data.size(), usuarioEntities.size());

        for (int i = 0; i < data.size(); i++) {
            Assert.assertTrue(usuarioEntities.contains(data.get(0)));
        }
    }

    /**
     * Prueba para cpnsultar un usuario de un hackaton.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void getUsuarioTest() throws BusinessLogicException {
        UsuarioEntity usuarioEntity = data.get(0);
        UsuarioEntity usuario = hackatonUsuarioLogic.getUsuario(hackaton.getId(), usuarioEntity.getId());
        Assert.assertNotNull(usuario);

        Assert.assertEquals(usuarioEntity.getId(), usuario.getId());
        
    }
    
    /**
     * Prueba para actualizar los usuarios de un hackaton.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void replaceUsuariosTest() throws BusinessLogicException {
        List<UsuarioEntity> nuevaLista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            entity.setHackatones(new ArrayList<>());
            entity.getHackatones().add(hackaton);
            usuarioLogic.createUsuario(entity);
            nuevaLista.add(entity);
        }
        hackatonUsuarioLogic.replaceUsuarios(hackaton.getId(), nuevaLista);
        List<UsuarioEntity> usuarioEntities = hackatonUsuarioLogic.getUsuarios(hackaton.getId());
        for (UsuarioEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(usuarioEntities.contains(aNuevaLista));
        }
    }

    /**
     * Prueba desasociar un usuario con un hackaton.
     *
     */
    @Test
    public void removeUsuarioTest() {
        for (UsuarioEntity usuario : data) {
            hackatonUsuarioLogic.removeUsuario(hackaton.getId(), usuario.getId());
        }
        Assert.assertTrue(hackatonUsuarioLogic.getUsuarios(hackaton.getId()).isEmpty());
    }
}
