/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.TecnologiaLogic;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
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
import static org.glassfish.pfl.basic.tools.argparser.ElementParser.factory;
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
 * @author ja.torresl
 */
@RunWith(Arquillian.class)
public class TecnologiaLogicTest {
    

        
    @Deployment
    public static JavaArchive createDlepoyment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TecnologiaEntity.class.getPackage())
                .addPackage(TecnologiaLogic.class.getPackage())
                .addPackage(TecnologiaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
   /**
     * Realiza la configuracion del test
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
     * Borra los datos anteriores
     */
    private void clearData() {
        em.createQuery("delete from TecnologiaEntity").executeUpdate();
    }
    
    /**
     * Inserta datos de prueba
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            TecnologiaEntity entity = factory.manufacturePojo(TecnologiaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            data.get(0).getParticipantes().add(entity);
        }
    }
     
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    private TecnologiaLogic tecnologiaLogic;
    
    @Inject
    private UserTransaction utx;
    
    private List<TecnologiaEntity> data = new ArrayList<>();
     
    @Test
    public void createTecnologia() throws BusinessLogicException{
       
        TecnologiaEntity newEntity = factory.manufacturePojo(TecnologiaEntity.class);
        TecnologiaEntity result = tecnologiaLogic.createTecnologia(newEntity);
        Assert.assertNotNull(result);
        
        TecnologiaEntity entity =  em.find(TecnologiaEntity.class, result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
        Assert.assertEquals(entity.getParticipantes(), result.getParticipantes());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createTecnologiaNombreNull() throws BusinessLogicException{
        
        TecnologiaEntity newEntity = factory.manufacturePojo(TecnologiaEntity.class);
        newEntity.setNombre(null);
        TecnologiaEntity result = tecnologiaLogic.createTecnologia(newEntity);
    }
    
    
    /**
     * Prueba la eliminacion de un tecnologia
     * @throws BusinessLogicException 
     */
    @Test
    public void deleteTecnologiaTest() throws BusinessLogicException {
        TecnologiaEntity entity = data.get(0);
        tecnologiaLogic.deleteTecnologia(entity.getId());
        TecnologiaEntity deleted = em.find(TecnologiaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba la actualizacion de los datos de un tecnologia
     */
    @Test
    public void updateTecnologiaTest() {
        TecnologiaEntity entity = data.get(0);
        TecnologiaEntity pojoEntity = factory.manufacturePojo(TecnologiaEntity.class);

        pojoEntity.setId(entity.getId());

        tecnologiaLogic.updateTecnologia(pojoEntity.getId(), pojoEntity);

        TecnologiaEntity resp = em.find(TecnologiaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());

    }
    
    /**
     * Prueba la busqueda de un tecnologia a partir de su id
     */
    @Test
    public void getTecnologiaTest() {
        TecnologiaEntity entity = data.get(0);
        TecnologiaEntity resultEntity = tecnologiaLogic.getTecnologia(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());

    }
 
}
