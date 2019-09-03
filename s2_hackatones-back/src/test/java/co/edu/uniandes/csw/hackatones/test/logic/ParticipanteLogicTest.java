/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.ParticipanteLogic;
import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ParticipantePersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ne.cardenas
 */
@RunWith(Arquillian.class)
public class ParticipanteLogicTest {
    
    @PersistenceContext()
    protected EntityManager em;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ParticipanteLogic logic;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ParticipanteEntity.class.getPackage())
                .addPackage(ParticipanteLogic.class.getPackage())
                .addPackage(ParticipantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createParticipante() throws BusinessLogicException {
        ParticipanteEntity entity = factory.manufacturePojo(ParticipanteEntity.class);
        ParticipanteEntity result = logic.createParticipante(entity);
        Assert.assertNotNull(result);
        
        ParticipanteEntity entity2 = em.find(ParticipanteEntity.class, result.getId());
        Assert.assertEquals(entity2.getHackaton(), result.getHackaton());
        Assert.assertEquals(entity2.getTecnologias(), result.getTecnologias());
        Assert.assertEquals(entity2.getIntereses(), result.getIntereses());
        Assert.assertEquals(entity2.getLenguajes(), result.getLenguajes());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createHackatonNull() throws BusinessLogicException {
        ParticipanteEntity entity = factory.manufacturePojo(ParticipanteEntity.class);
        entity.setHackaton(null);
        ParticipanteEntity result = logic.createParticipante(entity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createTecnologiasNull() throws BusinessLogicException {
        ParticipanteEntity entity = factory.manufacturePojo(ParticipanteEntity.class);
        entity.setTecnologias(null);
        ParticipanteEntity result = logic.createParticipante(entity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createInteresesNull() throws BusinessLogicException {
        ParticipanteEntity entity = factory.manufacturePojo(ParticipanteEntity.class);
        entity.setIntereses(null);
        ParticipanteEntity result = logic.createParticipante(entity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createLenguajesNull() throws BusinessLogicException {
        ParticipanteEntity entity = factory.manufacturePojo(ParticipanteEntity.class);
        entity.setLenguajes(null);
        ParticipanteEntity result = logic.createParticipante(entity);
    }
}
