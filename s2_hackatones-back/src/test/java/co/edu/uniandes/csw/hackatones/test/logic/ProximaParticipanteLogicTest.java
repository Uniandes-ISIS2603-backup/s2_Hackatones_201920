/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.ProximaParticipanteLogic;
import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
//import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
//import co.edu.uniandes.csw.hackatones.persistence.ProximaPersistence;
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
 * @proxima a.pedraza
 */
@RunWith(Arquillian.class)
public class ProximaParticipanteLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ProximaParticipanteLogic proximaParticipanteLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

//    private List<ProximaEntity> data = new ArrayList<>();

    private List<ParticipanteEntity> participantesData = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
//                .addPackage(ProximaEntity.class.getPackage())
                .addPackage(ProximaParticipanteLogic.class.getPackage())
//                .addPackage(ProximaPersistence.class.getPackage())
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
        em.createQuery("delete from ParticipanteEntity").executeUpdate();
        em.createQuery("delete from ProximaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ParticipanteEntity participantes = factory.manufacturePojo(ParticipanteEntity.class);
            
            em.persist(participantes);
            participantesData.add(participantes);
        }
        for (int i = 0; i < 3; i++) {
//            ProximaEntity entity = factory.manufacturePojo(ProximaEntity.class);
//            em.persist(entity);
//            data.add(entity);
//            
        }
    }

    /**
     * Prueba para asociar un Participante existente a un Proxima.
     */
    @Test
    public void addParticipanteTest() {
////        ProximaEntity entity = data.get(0);
//        ParticipanteEntity participanteEntity = participantesData.get(1);
//        ParticipanteEntity response = proximaParticipanteLogic.addParticipante(entity.getId(), participanteEntity.getId());
//
//        Assert.assertNotNull(response);
//        Assert.assertEquals(participanteEntity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un Participante.
     */
    @Test
    public void getParticipanteTest() throws BusinessLogicException {
//        ProximaEntity proxima = data.get(0);
//        ParticipanteEntity entity = proxima.getInscritos().get(0);
//        ParticipanteEntity resultEntity = proximaParticipanteLogic.getParticipante(proxima.getId(), entity.getId());
//        Assert.assertNotNull(resultEntity);
//        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Participantes asociadas a una instancia
     * de Proxima.
     */
    @Test
    public void replaceParticipanteTest() throws BusinessLogicException {
//        ProximaEntity entity = data.get(0);
//        proximaParticipanteLogic.replaceParticipantess(entity.getId(), participantesData);
//        ParticipanteEntity newEntity = proximaParticipanteLogic.getParticipante(entity.getId(), participantesData.get(1).getId());
//        Assert.assertTrue(proximaParticipanteLogic.getParticipantes(entity.getId()).contains(newEntity.getId()));
    }

    /**
     * Prueba para desasociar un Participante existente de un Proxima existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void removeParticipanteTest() throws BusinessLogicException {
//        ProximaEntity entity = data.get(0);
//        Long idPar = entity.getInscritos().get(0).getId();
//        proximaParticipanteLogic.removeParticipante(entity.getId(), idPar);
//        Assert.assertNull(proximaParticipanteLogic.getParticipante(entity.getId(),idPar));
    }
}
