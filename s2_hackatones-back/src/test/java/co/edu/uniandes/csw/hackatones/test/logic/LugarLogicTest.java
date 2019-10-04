/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.LugarLogic;
import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.LugarPersistence;
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
 *pruebas para la clase lugar
 * @author jd.monsalve
 */
@RunWith(Arquillian.class)
public class LugarLogicTest {
    
    private PodamFactory podam = new PodamFactoryImpl();
    
    @Inject
    private LugarLogic lugarLogic;
    
    @PersistenceContext()
    private EntityManager em;
    
     private List<LugarEntity> data = new ArrayList<LugarEntity>();
    
      private List<LugarEntity> lugarData = new ArrayList();
      
    @Inject
    private UserTransaction utx;

  
    /**
     * devuelve el jar que Arquilliam va a desplegar en payara embebido
     * El jar contiene las clases, el descriptor de la base de datos y el 
     * archivo beans.xml para resolver la inyección de dependencias
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(LugarEntity.class.getPackage()).addPackage(LugarLogic.class.getPackage()).addPackage(LugarPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
    private void clearData() 
    {
        em.createQuery("delete from LugarEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            LugarEntity lugar = podam.manufacturePojo(LugarEntity.class);
            em.persist(lugar);
            lugarData.add(lugar);
        }

        LugarEntity lugar = podam.manufacturePojo(LugarEntity.class);
        em.persist(lugar);

    }
    
    

 
    /**
     * test para el método crear lugar
     * @throws BusinessLogicException  lanza excepción si no cumple alguna regla de negocio
     */
    @Test
    public void createLugar() throws BusinessLogicException
    {
        LugarEntity NuevaEntidad = podam.manufacturePojo(LugarEntity.class);
        LugarEntity resultado = lugarLogic.createLugar(NuevaEntidad);
        Assert.assertNotNull(resultado); 
        LugarEntity entidad = em.find(LugarEntity.class, resultado.getId());
        Assert.assertEquals(entidad.getNombre(), resultado.getNombre());
    }
    
    /**
     * test que intenta crear un lugar ya existente
     * @throws BusinessLogicException  lanza excepcion si no cumple una regla de negocio
     */
    @Test (expected = BusinessLogicException.class)
    public void crearLugarYaExistente() throws BusinessLogicException
    {
   LugarEntity NuevaEntidad = podam.manufacturePojo(LugarEntity.class);
   LugarEntity resultado = lugarLogic.createLugar(NuevaEntidad);
   Assert.assertNotNull(resultado);
      
   lugarLogic.createLugar(NuevaEntidad);
    }
    
    /**
     * prueba para crear un lugar con dirección nula
     * @throws BusinessLogicException  lanza excepcion si no cumple una regla de negocio
     */
    @Test (expected = BusinessLogicException.class)
    public void createLugarDireccionNull() throws BusinessLogicException
    {
    LugarEntity nuevaEntidad = podam.manufacturePojo(LugarEntity.class);
    nuevaEntidad.setDireccion(null);
    
    LugarEntity resultado  = lugarLogic.createLugar(nuevaEntidad);
    }
    
  
    /**
     * prueba para crear un lugar con ciudad nula
     * @throws BusinessLogicException  lanza excepcion si no cumple una regla de negocio
     */
    @Test (expected = BusinessLogicException.class)
    public void createLugarCiudadNull() throws BusinessLogicException
    {
    LugarEntity nuevaEntidad = podam.manufacturePojo(LugarEntity.class);
    nuevaEntidad.setCiudad(null);
    
    LugarEntity resultado  = lugarLogic.createLugar(nuevaEntidad);
    }
    
   /**
     * Prueba para actualizar un lugar.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateLugarTest() throws BusinessLogicException {
        LugarEntity entity = data.get(0);
        LugarEntity nuevo = podam.manufacturePojo(LugarEntity.class);
        nuevo.setId(entity.getId());
        lugarLogic.updateLugar(nuevo.getId(), nuevo);
        LugarEntity resp = em.find(LugarEntity.class, entity.getId());
        Assert.assertEquals(nuevo.getId(), resp.getId());
        Assert.assertEquals(nuevo.getCiudad(), resp.getCiudad());
        Assert.assertEquals(nuevo.getDireccion(), resp.getDireccion());
        Assert.assertEquals(nuevo.getNombre(), resp.getCiudad());
    }
    
      /**
     * Prueba para eliminar un lugar.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteBookTest() throws BusinessLogicException {
        LugarEntity entity = data.get(0);
        lugarLogic.deleteLugar(entity.getId());
        LugarEntity deleted = em.find(LugarEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
  
}
