///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.csw.hackatones.resources;
//
//import co.edu.uniandes.csw.hackatones.dtos.HackatonDetailDTO;
//import co.edu.uniandes.csw.hackatones.ejb.HackatonLogic;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.inject.Inject;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
///**
// *
// * @author jd.monsalve
// * @version 1.0
// */
//
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//public class HackatonesResource {
//    
// private static final Logger LOGGER = Logger.getLogger(HackatonesResource.class.getName());
//
//    @Inject
//    private HackatonLogic hackatonesLogic;
//
//   //FALTAN RELACIONES CON LAS ENTITYS
//    
//    
//    @POST
//    @Path("{HackatonId: \\d+}")
//    public HackatonDetailDTO addHackaton() {
//        LOGGER.log(Level.INFO, "AuthorBooksResource addBook: input: authorsId {0} , booksId {1}", new Object[]);
//        if (HackatonLogic.getBook(booksId) == null) {
//            throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
//        }
//        BookDetailDTO detailDTO = new BookDetailDTO(authorBookLogic.addBook(authorsId, booksId));
//        LOGGER.log(Level.INFO, "AuthorBooksResource addBook: output: {0}", detailDTO);
//        return detailDTO;
//    }
//
//    /**
//     * Busca y devuelve todos los libros que existen en un autor.
//     *
//     * @param authorsId El ID del autor del cual se buscan los libros
//     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en el
//     * autor. Si no hay ninguno retorna una lista vacía.
//     */
//    @GET
//    public List<BookDetailDTO> getBooks(@PathParam("authorsId") Long authorsId) {
//        LOGGER.log(Level.INFO, "AuthorBooksResource getBooks: input: {0}", authorsId);
//        List<BookDetailDTO> lista = booksListEntity2DTO(authorBookLogic.getBooks(authorsId));
//        LOGGER.log(Level.INFO, "AuthorBooksResource getBooks: output: {0}", lista);
//        return lista;
//    }
//
//    /**
//     * Busca y devuelve el libro con el ID recibido en la URL, relativo a un
//     * autor.
//     *
//     * @param authorsId El ID del autor del cual se busca el libro
//     * @param booksId El ID del libro que se busca
//     * @return {@link BookDetailDTO} - El libro encontrado en el autor.
//     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
//     * si el libro no está asociado al autor
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra el libro.
//     */
//    @GET
//    @Path("{booksId: \\d+}")
//    public BookDetailDTO getBook(@PathParam("authorsId") Long authorsId, @PathParam("booksId") Long booksId) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "AuthorBooksResource getBook: input: authorsId {0} , booksId {1}", new Object[]{authorsId, booksId});
//        if (bookLogic.getBook(booksId) == null) {
//            throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
//        }
//        BookDetailDTO detailDTO = new BookDetailDTO(authorBookLogic.getBook(authorsId, booksId));
//        LOGGER.log(Level.INFO, "AuthorBooksResource getBook: output: {0}", detailDTO);
//        return detailDTO;
//    }
//
//    /**
//     * Actualiza la lista de libros de un autor con la lista que se recibe en el
//     * cuerpo
//     *
//     * @param authorsId El ID del autor al cual se le va a asociar el libro
//     * @param books JSONArray {@link BookDetailDTO} - La lista de libros que se
//     * desea guardar.
//     * @return JSONArray {@link BookDetailDTO} - La lista actualizada.
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra el libro.
//     */
//    @PUT
//    public List<BookDetailDTO> replaceBooks(@PathParam("authorsId") Long authorsId, List<BookDetailDTO> books) {
//        LOGGER.log(Level.INFO, "AuthorBooksResource replaceBooks: input: authorsId {0} , books {1}", new Object[]{authorsId, books});
//        for (BookDetailDTO book : books) {
//            if (bookLogic.getBook(book.getId()) == null) {
//                throw new WebApplicationException("El recurso /books/" + book.getId() + " no existe.", 404);
//            }
//        }
//        List<BookDetailDTO> lista = booksListEntity2DTO(authorBookLogic.replaceBooks(authorsId, booksListDTO2Entity(books)));
//        LOGGER.log(Level.INFO, "AuthorBooksResource replaceBooks: output: {0}", lista);
//        return lista;
//    }
//
//    /**
//     * Elimina la conexión entre el libro y e autor recibidos en la URL.
//     *
//     * @param authorsId El ID del autor al cual se le va a desasociar el libro
//     * @param booksId El ID del libro que se desasocia
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra el libro.
//     */
//    @DELETE
//    @Path("{booksId: \\d+}")
//    public void removeBook(@PathParam("authorsId") Long authorsId, @PathParam("booksId") Long booksId) {
//        LOGGER.log(Level.INFO, "AuthorBooksResource deleteBook: input: authorsId {0} , booksId {1}", new Object[]{authorsId, booksId});
//        if (bookLogic.getBook(booksId) == null) {
//            throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
//        }
//        authorBookLogic.removeBook(authorsId, booksId);
//        LOGGER.info("AuthorBooksResource deleteBook: output: void");
//    }
//
//    /**
//     * Convierte una lista de BookEntity a una lista de BookDetailDTO.
//     *
//     * @param entityList Lista de BookEntity a convertir.
//     * @return Lista de BookDetailDTO convertida.
//     */
//    private List<BookDetailDTO> booksListEntity2DTO(List<BookEntity> entityList) {
//        List<BookDetailDTO> list = new ArrayList<>();
//        for (BookEntity entity : entityList) {
//            list.add(new BookDetailDTO(entity));
//        }
//        return list;
//    }
//
//    /**
//     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
//     *
//     * @param dtos Lista de BookDetailDTO a convertir.
//     * @return Lista de BookEntity convertida.
//     */
//    private List<BookEntity> booksListDTO2Entity(List<BookDetailDTO> dtos) {
//        List<BookEntity> list = new ArrayList<>();
//        for (BookDetailDTO dto : dtos) {
//            list.add(dto.toEntity());
//        }
//        return list;
//    }
