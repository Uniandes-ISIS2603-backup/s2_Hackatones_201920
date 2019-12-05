/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.HackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.TecnologiaHackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.TecnologiaUsuarioLogic;
import co.edu.uniandes.csw.hackatones.ejb.UsuarioLogic;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.TecnologiaPersistence;
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
 * @author jd.monsalve
 */
@RunWith(Arquillian.class)
public class TecnologiaHackatonLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private TecnologiaHackatonLogic tecnologiaHackatonLogic;

    @Inject
    private HackatonLogic hackatonLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private TecnologiaEntity tecnologia = new TecnologiaEntity();
    private List<HackatonEntity> data = new ArrayList<>();
    
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
                .addPackage(TecnologiaHackatonLogic.class.getPackage())
                .addPackage(TecnologiaPersistence.class.getPackage())
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
        

        tecnologia = factory.manufacturePojo(TecnologiaEntity.class);
        tecnologia.setId(1L);
        tecnologia.setParticipantes(new ArrayList<>());
        em.persist(tecnologia);

        for (int i = 0; i < 3; i++) {
            HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            entity.setTecnologias(new ArrayList<>());
            entity.getTecnologias().add(tecnologia);
            em.persist(entity);
            data.add(entity);
            tecnologia.getHackatones().add(entity);
        }
    }
    
    /**
     * Prueba para asociar un tecnologia a una hackaton.
     *
     *
     * @throws BusinessLogicException
     */
     @Test
    public void addHackatonTest() throws BusinessLogicException {
        
        HackatonEntity newHackaton = factory.manufacturePojo(HackatonEntity.class);
        hackatonLogic.createHackaton(newHackaton);
        HackatonEntity hackatonEntity = tecnologiaHackatonLogic.addHackaton(tecnologia.getId(), newHackaton.getId());
        Assert.assertNotNull(hackatonEntity);
        

        Assert.assertEquals(hackatonEntity.getId(), newHackaton.getId());
       
        
         
//        HackatonEntity lastHAckaton = tecnologiaHackatonLogic.getHackaton(tecnologia.getId(), newHackaton.getId());
//
//        System.out.println(lastHAckaton.getId());
//        System.out.println(newHackaton.getId());
//
//        Assert.assertEquals(lastHAckaton.getId(), newHackaton.getId());
        
    }
    
    /**
     * Prueba para consultar la lista de hackatones de un tecnologia.
     */
    @Test
    public void getHackatonesTest() {
        List<HackatonEntity> hackatonEntities = tecnologiaHackatonLogic.getHackatones(tecnologia.getId());

        Assert.assertEquals(data.size(), hackatonEntities.size());

        for (int i = 0; i < data.size(); i++) {
            Assert.assertTrue(hackatonEntities.contains(data.get(0)));
        }
    }

    /**
     * Prueba para cpnsultar un usuario de un tecnologia.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void getHackatonTest() throws BusinessLogicException {
        HackatonEntity hackatonEntity = data.get(0);
        HackatonEntity hackaton  = tecnologiaHackatonLogic.getHackaton(tecnologia.getId(), hackatonEntity.getId());
        Assert.assertNotNull(hackaton);

        Assert.assertEquals(hackatonEntity.getId(), hackaton.getId());
        
    }
    
    /**
     * Prueba para actualizar las hackatones de un tecnologia.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void replaceHackatonesTest() throws BusinessLogicException {
        List<HackatonEntity> nuevaLista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            entity.setTecnologias(new ArrayList<>());
            entity.getTecnologias().add(tecnologia);
            hackatonLogic.createHackaton(entity);
            nuevaLista.add(entity);
        }
        tecnologiaHackatonLogic.replaceHackatones(tecnologia.getId(), nuevaLista);
        List<HackatonEntity> hackatonEntities = tecnologiaHackatonLogic.getHackatones(tecnologia.getId());
        for (HackatonEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(hackatonEntities.contains(aNuevaLista));
        }
    }

    /**
     * Prueba desasociar una hackaton con un tecnologia.
     *
     */
    @Test
    public void removeHackatonTest() {
        for (HackatonEntity hackaton : data) {
            tecnologiaHackatonLogic.removeHackaton(tecnologia.getId(), hackaton.getId());
        }
//        Assert.assertTrue(tecnologiaHackatonLogic.getHackatones(tecnologia.getId()).isEmpty());
    }
    
}
