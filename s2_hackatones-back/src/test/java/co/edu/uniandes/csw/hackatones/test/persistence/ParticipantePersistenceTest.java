    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;
import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import co.edu.uniandes.csw.hackatones.persistence.ParticipantePersistence;
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
 * @author ne.cardenas
 */
@RunWith(Arquillian.class)
public class ParticipantePersistenceTest {
    
    @Inject
    private ParticipantePersistence pp;
    
    @PersistenceContext
    protected EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
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
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from ParticipanteEntity").executeUpdate();
    }
    
    private List<ParticipanteEntity> data = new ArrayList<ParticipanteEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ParticipanteEntity entity = factory.manufacturePojo(ParticipanteEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ParticipanteEntity.class.getPackage())
                .addPackage(ParticipantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createParticipanteTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ParticipanteEntity newEntity = factory.manufacturePojo(ParticipanteEntity.class);
        
        ParticipanteEntity pe = pp.create(newEntity);
        
        Assert.assertNotNull(pe);
        
        ParticipanteEntity entity = em.find(ParticipanteEntity.class, pe.getId());
        Assert.assertEquals(newEntity.isInscrito(), entity.isInscrito());
        //Assert.assertEquals(newEntity.getHackaton(), entity.getHackaton());
        //Assert.assertEquals(newEntity.getEquipo(), entity.getEquipo());
        //Assert.assertEquals(newEntity.getIntereses(), entity.getIntereses());
        //Assert.assertEquals(newEntity.getLenguajes(), entity.getLenguajes());
        //Assert.assertEquals(newEntity.getTecnologias(), entity.getTecnologias());
    }
    
    @Test
    public void getParticipantesTest() {
        List<ParticipanteEntity> list = pp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ParticipanteEntity ent : list) {
            boolean found = false;
            for (ParticipanteEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getParticipanteTest() {
        ParticipanteEntity entity = data.get(0);
        ParticipanteEntity newEntity = pp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.isInscrito(), entity.isInscrito());
        //Assert.assertEquals(newEntity.getHackaton(), entity.getHackaton());
        //Assert.assertEquals(newEntity.getEquipo(), entity.getEquipo());
        //Assert.assertEquals(newEntity.getIntereses(), entity.getIntereses());
        //Assert.assertEquals(newEntity.getLenguajes(), entity.getLenguajes());
        //Assert.assertEquals(newEntity.getTecnologias(), entity.getTecnologias());
    }
    
    @Test
    public void deleteParticipanteTest() {
        ParticipanteEntity entity = data.get(0);
        pp.delete(entity.getId());
        ParticipanteEntity deleted = em.find(ParticipanteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateParticipanteTest() {
        ParticipanteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ParticipanteEntity newEntity = factory.manufacturePojo(ParticipanteEntity.class);

        newEntity.setId(entity.getId());

        pp.update(newEntity);

        ParticipanteEntity resp = em.find(ParticipanteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.isInscrito(), resp.isInscrito());
        //Assert.assertEquals(newEntity.getHackaton(), resp.getHackaton());
        //Assert.assertEquals(newEntity.getEquipo(), resp.getEquipo());
//        Assert.assertEquals(newEntity.getIntereses(), resp.getIntereses());
//        Assert.assertEquals(newEntity.getLenguajes(), resp.getLenguajes());
//        Assert.assertEquals(newEntity.getTecnologias(), resp.getTecnologias());
    }
    
}
