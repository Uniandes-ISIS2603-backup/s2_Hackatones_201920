/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.LenguajeLogic;
import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.LenguajePersistence;
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
 * @author Santiago Estupinan
 */
@RunWith(Arquillian.class)
public class LenguajeLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    private LenguajeLogic logic;
    
    @Inject
    private UserTransaction utx;
    
    private List<LenguajeEntity> data = new ArrayList<>();
    
    /**
     * Crea el deployment del test
     * @return 
     */
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LenguajeEntity.class.getPackage())
                .addPackage(LenguajeLogic.class.getPackage())
                .addPackage(LenguajePersistence.class.getPackage())
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
        em.createQuery("delete from LenguajeEntity").executeUpdate();
    }
    
    /**
     * Inserta datos de prueba
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            LenguajeEntity entity = factory.manufacturePojo(LenguajeEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            data.get(0).getParticipantes().add(entity);
        }
    }
    
    /**
     * Prueba la creacion de un lenguaje
     * @throws BusinessLogicException 
     */
    @Test
    public void createLenguajeTest() throws BusinessLogicException
    {
        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);
        LenguajeEntity result = logic.createLenguaje(newEntity);
        
        Assert.assertNotNull(result);
        LenguajeEntity entity = em.find(LenguajeEntity.class, result.getId());
        Assert.assertEquals(entity.getName(), result.getName());
        
    }
    
    /**
     * Prueba la creacion de un lenguaje con nombre nulo
     * @throws BusinessLogicException 
     */
    @Test (expected = Exception.class)
    public void  createLenguajeNombreNull() throws BusinessLogicException
    {
        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);
        newEntity.setName(null);
        LenguajeEntity result = logic.createLenguaje(newEntity);
    }
    
    /**
     * Prueba la eliminacion de un lenguaje
     * @throws BusinessLogicException 
     */
    @Test
    public void deleteLenguajeTest() throws BusinessLogicException {
        LenguajeEntity entity = data.get(0);
        logic.deleteLenguaje(entity.getId());
        LenguajeEntity deleted = em.find(LenguajeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba la actualizacion de los datos de un lenguaje
     */
    @Test
    public void updateLenguajeTest() {
        LenguajeEntity entity = data.get(0);
        LenguajeEntity pojoEntity = factory.manufacturePojo(LenguajeEntity.class);

        pojoEntity.setId(entity.getId());

        logic.updateLenguaje(pojoEntity.getId(), pojoEntity);

        LenguajeEntity resp = em.find(LenguajeEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());

    }
    
    /**
     * Prueba la busqueda de un lenguaje a partir de su id
     */
    @Test
    public void getLenguajeTest() {
        LenguajeEntity entity = data.get(0);
        LenguajeEntity resultEntity = logic.getLenguaje(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());

    }
}
