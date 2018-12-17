package com.mis_ofertas_api.app.service;

import com.mis_ofertas_api.app.model.*;
import com.mis_ofertas_api.app.repository.*;
import com.mis_ofertas_api.app.util.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;

@Service
public class InitialDataService {

    private ConfigProperties configProperties;

    private UserDAO userDAO;

    private RolDAO rolDAO;

    private CountryDAO countryDAO;

    private CityDAO cityDAO;

    private CommuneDAO communeDAO;

    private StoreDAO storeDAO;

    private DiscountDAO discountDAO;

    private AreaDAO areaDAO;

    private ImageDAO imageDAO;

    private ProductDAO productDAO;

    private ProductTypeDAO productTypeDAO;

    private StatusDAO statusDAO;

    private VisitDAO visitDAO;

    private ValorationDAO valorationDAO;

    private NoteDAO noteDAO;

    private DocumentDAO documentDAO;

    private OfferDAO offerDAO;

    private OfferTypeDAO offerTypeDAO;

    @Autowired
    public void setConfigProperties(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRolDAO(RolDAO rolDAO) {
        this.rolDAO = rolDAO;
    }

    @Autowired
    public void setCountryDAO(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @Autowired
    public void setCityDAO(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Autowired
    public void setCommuneDAO(CommuneDAO communeDAO) {
        this.communeDAO = communeDAO;
    }

    @Autowired
    public void setStoreDAO(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    @Autowired
    public void setDiscountDAO(DiscountDAO discountDAO) {
        this.discountDAO = discountDAO;
    }

    @Autowired
    public void setAreaDAO(AreaDAO areaDAO) {
        this.areaDAO = areaDAO;
    }

    @Autowired
    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Autowired
    public void setProductTypeDAO(ProductTypeDAO productTypeDAO) {
        this.productTypeDAO = productTypeDAO;
    }

    @Autowired
    public void setStatusDAO(StatusDAO statusDAO) {
        this.statusDAO = statusDAO;
    }

    @Autowired
    public void setVisitDAO(VisitDAO visitDAO) {
        this.visitDAO = visitDAO;
    }

    @Autowired
    public void setValorationDAO(ValorationDAO valorationDAO) {
        this.valorationDAO = valorationDAO;
    }

    @Autowired
    public void setNoteDAO(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Autowired
    public void setDocumentDAO(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Autowired
    public void setOfferDAO(OfferDAO offerDAO) {
        this.offerDAO = offerDAO;
    }

    @Autowired
    public void setOfferTypeDAO(OfferTypeDAO offerTypeDAO) {
        this.offerTypeDAO = offerTypeDAO;
    }

    @PostConstruct
    public void init() {

        if (configProperties.getMap().get("jdbc.auto").equals("update") || configProperties.getMap().get("jdbc.auto").equals("create")) {

            Rol rolAdmin = new Rol();
            rolAdmin.setDescription("tiene acceso a todas las funcionalidades del sistema");
            rolAdmin.setName("ADMIN");
            rolDAO.insert(rolAdmin);

            Rol rolRepresentative = new Rol();
            rolRepresentative.setDescription("tiene acceso a las funcionalidades de representante de algun local comercial");
            rolRepresentative.setName("REPRESENTATIVE");
            rolDAO.insert(rolRepresentative);

            Rol rolClient = new Rol();
            rolClient.setDescription("tiene acceso a las funcionalidades de representante de algun local comercial");
            rolClient.setName("CLIENT");
            rolDAO.insert(rolClient);


            {
                Country country = new Country();
                country.setName("Chile");
                countryDAO.insert(country);
                {
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Arica");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Iquique");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Antofagasta");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Copiapo");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Vallenar");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("La serena");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Valparaiso");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Rancagua");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Talca");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Chillan");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Temuco");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Valdivia");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Osorno");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Puerto montt");
                        cityDAO.insert(city);
                    }
                    {
                        City city = new City();
                        city.setCountry(country);
                        city.setName("Punta arenas");
                        cityDAO.insert(city);
                    }


                    City city = new City();
                    city.setCountry(country);
                    city.setName("Santiago");
                    cityDAO.insert(city);
                    {
                        Commune commune = new Commune();
                        commune.setCity(city);
                        commune.setName("Las condes");
                        communeDAO.insert(commune);
                    }
                    {
                        Commune commune = new Commune();
                        commune.setCity(city);
                        commune.setName("Providencia");
                        communeDAO.insert(commune);

                        Image imageRipley=new Image();
                        imageRipley.setPath("https://res.cloudinary.com/duzvu8wmg/image/upload/v1544046444/images/20477230ripley.png");
                        imageDAO.insert(imageRipley);

                        {
                            Store store = new Store();
                            store.setCommune(commune);
                            store.setDirection("Avenida providencia #123");
                            store.setName("Ripley SA");
                            store.setImage(imageRipley);
                            storeDAO.insert(store);
                        }

                        {
                            Store store = new Store();
                            store.setCommune(commune);
                            store.setDirection("Antonio varas #666");
                            store.setName("Duoc UC");
                            store.setImage(imageRipley);
                            storeDAO.insert(store);
                        }

                        {
                            Store store = new Store();
                            store.setCommune(commune);
                            store.setDirection("Manuel montt #321");
                            store.setName("Mis ofertas LTDA.");
                            store.setImage(imageRipley);
                            storeDAO.insert(store);

                            SystemUser user = new SystemUser();
                            user.setFirstName("Javier");
                            user.setLastName("De la o");
                            user.setPoints(0);
                            user.setAvatar("avatar0.jpg");
                            user.setRol(rolAdmin);
                            user.setRut("18429403-6");
                            user.setEmail("j.delao@alumnos.duoc.cl");
                            user.setPassword("portafolio");
                            user.setStore(store);
                            userDAO.insert(user);

                            Discount discount = new Discount();
                            discount.setUser(user);
                            discount.setCreationDate(new Date());
                            discount.setExpirationDate(new Date());
                            discount.setPath("https://res.cloudinary.com/duzvu8wmg/image/upload/v1544974854/images/nuucemytyf8fu3kw83pm.png");
                            discount.setCode("28093038");
                            discount.setUsed(false);
                            discount.setPercentage(5);
                            discountDAO.insert(discount);


                            Area area = new Area();
                            area.setName("Abarrotes");
                            area.setDescription("productos como arroz y/o tallarines etc.");
                            areaDAO.insert(area);

                            ProductType productType=new ProductType();
                            productType.setName("Arroz");
                            productType.setDescription("arroz");
                            productTypeDAO.insert(productType);

                            {
                                Status status=new Status();
                                status.setName("Inactivo");
                                status.setDescription("no se encuentra disponible");
                                statusDAO.insert(status);
                            }

                            Status status=new Status();
                            status.setName("Activo");
                            status.setDescription("Se encuentra disponible");
                            statusDAO.insert(status);

                            Image imagetThermo=new Image();
                            imagetThermo.setPath("https://res.cloudinary.com/duzvu8wmg/image/upload/v1544046449/images/thermo.jpg");
                            imageDAO.insert(imagetThermo);

                            Product product=new Product();
                            product.setUser(user);
                            product.setPublicationDate(new Date());
                            product.setName("Thermo");
                            product.setDescription("Termo para lo que sea ");
                            product.setIs_perishable(false);
                            product.setExpirationDate(null);
                            product.setPrice(15000);
                            product.setProductType(productType);
                            product.setArea(area);
                            product.setStatus(status);
                            product.setImage(imagetThermo);
                            productDAO.insert(product);

                            Image imageXbox=new Image();
                            imageXbox.setPath("https://res.cloudinary.com/duzvu8wmg/image/upload/v1544046449/images/xbox.jpg");
                            imageDAO.insert(imageXbox);

                            Product productXbox=new Product();
                            productXbox.setUser(user);
                            productXbox.setPublicationDate(new Date());
                            productXbox.setName("X-box Onew");
                            productXbox.setDescription("La peor consola de todos los tiempos");
                            productXbox.setIs_perishable(false);
                            productXbox.setExpirationDate(null);
                            productXbox.setPrice(300000);
                            productXbox.setProductType(productType);
                            productXbox.setArea(area);
                            productXbox.setStatus(status);
                            productXbox.setImage(imageXbox);
                            productDAO.insert(productXbox);

                            Image imageStiwch=new Image();
                            imageStiwch.setPath("https://res.cloudinary.com/duzvu8wmg/image/upload/v1544046448/images/switch.jpg");
                            imageDAO.insert(imageStiwch);

                            Product productSwtich=new Product();
                            productSwtich.setUser(user);
                            productSwtich.setPublicationDate(new Date());
                            productSwtich.setName("Nintendo Switch");
                            productSwtich.setDescription("La primera gran consola hibrida");
                            productSwtich.setIs_perishable(false);
                            productSwtich.setExpirationDate(null);
                            productSwtich.setPrice(300000);
                            productSwtich.setProductType(productType);
                            productSwtich.setArea(area);
                            productSwtich.setStatus(status);
                            productSwtich.setImage(imageStiwch);
                            productDAO.insert(productSwtich);

                            Image imageDes=new Image();
                            imageDes.setPath("https://res.cloudinary.com/duzvu8wmg/image/upload/v1544046444/images/destapador.jpg");
                            imageDAO.insert(imageDes);

                            Product productDes=new Product();
                            productDes.setUser(user);
                            productDes.setPublicationDate(new Date());
                            productDes.setName("Destapador comun y corriente");
                            productDes.setDescription("Destapador comun y corriente");
                            productDes.setIs_perishable(false);
                            productDes.setExpirationDate(null);
                            productDes.setPrice(5000);
                            productDes.setProductType(productType);
                            productDes.setArea(area);
                            productDes.setStatus(status);
                            productDes.setImage(imageDes);
                            productDAO.insert(productDes);


                            Image image2ds=new Image();
                            image2ds.setPath("https://res.cloudinary.com/duzvu8wmg/image/upload/v1544046443/images/2ds.jpg");
                            imageDAO.insert(image2ds);

                            Product product2ds=new Product();
                            product2ds.setUser(user);
                            product2ds.setPublicationDate(new Date());
                            product2ds.setName("2ds");
                            product2ds.setDescription("2ds");
                            product2ds.setIs_perishable(false);
                            product2ds.setExpirationDate(null);
                            product2ds.setPrice(150000);
                            product2ds.setProductType(productType);
                            product2ds.setArea(area);
                            product2ds.setStatus(status);
                            product2ds.setImage(image2ds);
                            productDAO.insert(product2ds);



                            {
                                OfferType offerType = new OfferType();
                                offerType.setName("2x1");
                                offerType.setDescription("2x1");
                                offerTypeDAO.insert(offerType);
                            }

                            {
                                OfferType offerType= new OfferType();
                                offerType.setName("Super descuento");
                                offerType.setDescription("Super descuento");
                                offerTypeDAO.insert(offerType);


                                Offer offer= new Offer();
                                offer.setDiscount(15);
                                offer.setExpirationDate(new Date());
                                offer.setOfferType(offerType);
                                offer.setProduct(product);
                                offer.setPublicationDate(new Date());
                                offer.setQuantityAvailable(5);
                                offerDAO.insert(offer);

                            }




                            Visit visit= new Visit();
                            visit.setVisitDate(new Date());
                            visit.setProduct(product);
                            visit.setSystemUser(user);
                            visitDAO.insert(visit);

                            Document document=new Document();
                            document.setUploadDate(new Date());
                            document.setPath("xdxd.pdf");
                            documentDAO.insert(document);

                            Document document2=new Document();
                            document2.setUploadDate(new Date());
                            document2.setPath("2222.pdf");
                            documentDAO.insert(document2);


                            Note note = new Note();
                            note.setCommentDate(new Date());
                            note.setText("Comentario de prueba");
                            note.setDocuments(new ArrayList<Document>());
                            note.setSystemUser(user);
                            note.setProduct(product);
                            note.getDocuments().add(document);
                            note.getDocuments().add(document2);
                            noteDAO.insert(note);

                            Valoration valoration=new Valoration();
                            valoration.setProduct(product);
                            valoration.setSystemUser(user);
                            valoration.setValoration_star(4);
                            valorationDAO.insert(valoration);

                            Valoration valorationDB=valorationDAO.valoration(valoration.getId());
                            System.out.println(valorationDB);

                        }


                    }
                }
                {
                    City city = new City();
                    city.setCountry(country);
                    city.setName("Rancagua");
                    cityDAO.insert(city);
                }
            }
            {
                Country country = new Country();
                country.setName("Argentina");
                countryDAO.insert(country);
                {
                    City city = new City();
                    city.setCountry(country);
                    city.setName("Buenos Aires");
                    cityDAO.insert(city);
                }
                {
                    City city = new City();
                    city.setCountry(country);
                    city.setName("Cordoba");
                    cityDAO.insert(city);
                }
            }
            {
                Country country = new Country();
                country.setName("Peru");
                countryDAO.insert(country);
                {
                    City city = new City();
                    city.setCountry(country);
                    city.setName("Lima");
                    cityDAO.insert(city);
                }
                {
                    City city = new City();
                    city.setCountry(country);
                    city.setName("Tacna");
                    cityDAO.insert(city);
                }
            }


        }

    }


}
