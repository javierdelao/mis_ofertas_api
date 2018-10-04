package com.mis_ofertas_api.app.service;

import com.mis_ofertas_api.app.model.*;
import com.mis_ofertas_api.app.repository.*;
import com.mis_ofertas_api.app.util.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InitialDataService {

    private ConfigProperties configProperties;

    private UserDAO userDAO;

    private RolDAO rolDAO;

    private CountryDAO countryDAO;

    private CityDAO cityDAO;

    private CommuneDAO communeDAO;

    private StoreDAO storeDAO;

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

    @PostConstruct
    public void init() {

        if (configProperties.getMap().get("jdbc.auto").equals("update") || configProperties.getMap().get("jdbc.auto").equals("create")) {

            Rol rolAdmin = new Rol();
            rolAdmin.setDescription("tiene acceso a todas las funcionalidades del sistema");
            rolAdmin.setName("ADMIN");
            rolDAO.insert(rolAdmin);

            Rol rolRepresentative=new Rol();
            rolRepresentative.setDescription("tiene acceso a las funcionalidades de representante de algun local comercial");
            rolRepresentative.setName("REPRESENTATIVE");
            rolDAO.insert(rolRepresentative);

            Rol rolClient=new Rol();
            rolClient.setDescription("tiene acceso a las funcionalidades de representante de algun local comercial");
            rolClient.setName("CLIENT");
            rolDAO.insert(rolClient);


            {
                Country country =new Country();
                country.setName("Chile");
                countryDAO.insert(country);
                {
                    City city=new City();
                    city.setCountry(country);
                    city.setName("Santiago");
                    cityDAO.insert(city);
                    {
                        Commune commune=new Commune();
                        commune.setCity(city);
                        commune.setName("Las condes");
                        communeDAO.insert(commune);
                    }
                    {
                        Commune commune=new Commune();
                        commune.setCity(city);
                        commune.setName("Providencia");
                        communeDAO.insert(commune);

                        {
                            Store store=new Store();
                            store.setCommune(commune);
                            store.setDirection("Avenida providencia #123");
                            store.setName("Ripley SA");
                            storeDAO.insert(store);
                        }

                        {
                            Store store = new Store();
                            store.setCommune(commune);
                            store.setDirection("Antonio varas #666");
                            store.setName("Duoc UC");
                            storeDAO.insert(store);
                        }

                        {
                            Store store=new Store();
                            store.setCommune(commune);
                            store.setDirection("Manuel montt #321");
                            store.setName("Mis ofertas LTDA.");
                            storeDAO.insert(store);

                            SystemUser user=new SystemUser();
                            user.setFirstName("Javier");
                            user.setLastName("De la o");
                            user.setRol(rolAdmin);
                            user.setRut("18429403-6");
                            user.setEmail("j.delao@alumnos.duoc.cl");
                            user.setPassword("portafolio");
                            user.setStore(store);
                            userDAO.insert(user);
                        }


                    }
                }
                {
                    City city=new City();
                    city.setCountry(country);
                    city.setName("Rancagua");
                    cityDAO.insert(city);
                }
            }
            {
                Country country =new Country();
                country.setName("Argentina");
                countryDAO.insert(country);
                {
                    City city=new City();
                    city.setCountry(country);
                    city.setName("Buenos Aires");
                    cityDAO.insert(city);
                }
                {
                    City city=new City();
                    city.setCountry(country);
                    city.setName("Cordoba");
                    cityDAO.insert(city);
                }
            }
            {
                Country country =new Country();
                country.setName("Peru");
                countryDAO.insert(country);
                {
                    City city=new City();
                    city.setCountry(country);
                    city.setName("Lima");
                    cityDAO.insert(city);
                }
                {
                    City city=new City();
                    city.setCountry(country);
                    city.setName("Tacna");
                    cityDAO.insert(city);
                }
            }


        }

    }


}
