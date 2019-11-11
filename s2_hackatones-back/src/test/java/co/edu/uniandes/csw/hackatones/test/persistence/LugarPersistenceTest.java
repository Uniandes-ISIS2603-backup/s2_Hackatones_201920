/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
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
 *
 * @author jd.monsalve
 */
@RunWith(Arquillian.class)
public class LugarPersistenceTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext()
    EntityManager em;
    
    
     @Inject
    UserTransaction utx;
    
    @Inject
    LugarPersistence ep;
    
    @Inject
    private LugarPersistence lugar_persistence;


    private List <LugarEntity> data = new ArrayList<LugarEntity>();
    
    /**
     * Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LugarEntity.class.getPackage())
                .addPackage(LugarPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml" );
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest(){
    try {
     utx.begin();
     em.joinTransaction();
     clearData();
     insertData();
     utx.commit();
    }
    catch(Exception e){
    e.printStackTrace();
        try{
        utx.rollback();
           }
        catch(Exception a){
            a.printStackTrace();
           }       
        }
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData(){
    em.createQuery("delete from LugarEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData(){
        
    PodamFactory factory = new PodamFactoryImpl();
    
        for (int i = 0; i < 3; i++) {
            
           LugarEntity entity = factory.manufacturePojo(LugarEntity.class);
           
           em.persist(entity);
           data.add(entity);
            
        }
    }
   
    /**
     * Test de crear un lugar 
     */
    @Test
    public void createLugarTest() {
        PodamFactory factory = new PodamFactoryImpl();
        LugarEntity lugar = factory.manufacturePojo(LugarEntity.class);
        LugarEntity resultado = ep.create(lugar);
               
        Assert.assertNotNull(resultado);
               
        LugarEntity entity = em.find(LugarEntity.class, resultado.getId());
        
        Assert.assertEquals(lugar.getNombre(), entity.getNombre());
        Assert.assertEquals(lugar.getDireccion(), entity.getDireccion());
        Assert.assertEquals(lugar.getId()  , entity.getId());
        Assert.assertEquals(lugar.getCiudad(), entity.getCiudad());

    }
    
    /**
     * test para obtener los lugares
     */
    @Test
    public void getLugaresTest(){
    List <LugarEntity> lista = lugar_persistence.findAll();
    Assert.assertEquals(data.size(), lista.size());
    
    for(LugarEntity lugar : lista){
    
        boolean encontrado = false;
        for (LugarEntity entity : data){
        
            if(lugar.getId().equals(entity.getId())){
            encontrado = true;
            }
        }
        Assert.assertTrue(encontrado);
    }
   }
    
    /**
     * test de obtener un lugar
     */
    @Test 
   public void getLugarTest(){
   LugarEntity entidad = data.get(0);
   LugarEntity nueva_entidad = lugar_persistence.find(entidad.getId());
   Assert.assertNotNull(nueva_entidad);
   Assert.assertEquals(entidad.getNombre(), nueva_entidad.getNombre());
   Assert.assertEquals(entidad.getDireccion(), nueva_entidad.getDireccion());
   Assert.assertEquals(entidad.getId()  , nueva_entidad.getId());
   Assert.assertEquals(entidad.getCiudad(), nueva_entidad.getCiudad());
   }
   /**
    * Test para eliminar un lugar
    */
   @Test
   public void deleteLugarTest(){
   LugarEntity entidad = data.get(0);
   lugar_persistence.delete(entidad.getId());
   LugarEntity eliminada = em.find(LugarEntity.class, entidad.getId());
   Assert.assertNull(eliminada);
   }
   
   /**
    * Test para actualizar un lugar
    */
   @Test
   public void updateLugarTest(){
   
       LugarEntity entidad = data.get(0);
       PodamFactory factory = new PodamFactoryImpl();
       LugarEntity nueva_entidad = factory.manufacturePojo(LugarEntity.class);
       
       nueva_entidad.setId(entidad.getId());
       
       lugar_persistence.update(nueva_entidad);
       
       LugarEntity respuesta = em.find(LugarEntity.class, entidad.getId());
       
       Assert.assertEquals(nueva_entidad.getNombre(), respuesta.getNombre());
       Assert.assertEquals(nueva_entidad.getDireccion(), respuesta.getDireccion());
       Assert.assertEquals(nueva_entidad.getId()  , respuesta.getId());
       Assert.assertEquals(nueva_entidad.getCiudad(), respuesta.getCiudad());
   }
   
   /**
    * Test para encontrar un lugar por su ID
    */
   @Test
   public void findlugarByID(){
   LugarEntity entidad = data.get(0);
   LugarEntity nueva_entidad = lugar_persistence.find(entidad.getId());
   
   Assert.assertNotNull(nueva_entidad);
   Assert.assertEquals(entidad.getId(), nueva_entidad.getId());
   }
}