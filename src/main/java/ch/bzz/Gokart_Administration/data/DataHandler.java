package ch.bzz.Gokart_Administration.data;

import ch.bzz.Gokart_Administration.model.Circuit;
import ch.bzz.Gokart_Administration.model.Gokart;
import ch.bzz.Gokart_Administration.model.Karting_company;
import ch.bzz.Gokart_Administration.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static DataHandler instance = null;
    private List<Gokart> gokartList;
    private List<Karting_company> karting_companieList;
    private List<Circuit> circuitList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setKarting_companyList(new ArrayList<>());
        readKarting_companyJSON();
        setGokartList(new ArrayList<>());
        readGokartJSON();

        setCircuitList(new ArrayList<>());
        readCircuitJSON();

    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all gokarts
     * @return list of gokarts
     */
    public List<Gokart> readAllGokarts() {
        return getGokartList();
    }

    /**
     * reads a gokart by its gokart_number
     * @param gokart_number
     * @return the Gokart (null=not found)
     */
    public Gokart readGokartByGokart_number(String gokart_number) {
        Gokart gokart = null;
        for (Gokart entry : getGokartList()) {
            if (entry.getGokart_number().equals(gokart_number)) {
                gokart = entry;
            }
        }
        return gokart;
    }

    /**
     * reads all Karting_companys
     * @return list of Karting_companys
     */
    public List<Karting_company> readAllKarting_companys() {
        return getKarting_companyList();
    }

    /**
     * reads a karting_company by its uuid
     * @param karting_companyID
     * @return the karting_company (null=not found)
     */
    public Karting_company readKarting_companyByUUID(int karting_companyID) {
        Karting_company karting_company = null;
        for (Karting_company entry : getKarting_companyList()) {
            if (entry.getKarting_companyID() == (karting_companyID)) {
                karting_company = entry;
            }
        }
        return karting_company;
    }


    /**
     * reads all Circuits
     * @return list of Circuits
     */
    public List<Circuit> readAllCircuits() {
        return getCircuitList();
    }

    /**
     * reads a karting_company by its uuid
     * @param circuitID
     * @return the karting_company (null=not found)
     */
    public Circuit readCircuitByID(int circuitID) {
        Circuit circuit = null;
        for (Circuit entry : getCircuitList()) {
            if (entry.getCircuitID() == (circuitID)) {
                circuit = entry;
            }
        }
        return circuit;
    }

    /**
     * reads the gokarts from the JSON-file
     */
    private void readGokartJSON() {
        try {
            String path = Config.getProperty("gokartJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Gokart[] gokarts = objectMapper.readValue(jsonData, Gokart[].class);
            for (Gokart gokart : gokarts) {
                getGokartList().add(gokart);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the karting_companys from the JSON-file
     */

    private void readKarting_companyJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("kartingCompanyJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Karting_company[] karting_companies = objectMapper.readValue(jsonData, Karting_company[].class);
            for (Karting_company karting_company : karting_companies) {
                getKarting_companyList().add(karting_company);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * reads the circuits from the JSON-file
     */

    private void readCircuitJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("circuitJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Circuit[] circuits = objectMapper.readValue(jsonData, Circuit[].class);
            for (Circuit circuit : circuits) {
                getCircuitList().add(circuit);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * gets gokartList
     *
     * @return value of gokartList
     */
    private List<Gokart> getGokartList() {
        return gokartList;
    }

    /**
     * sets gokartList
     *
     * @param gokartList the value to set
     */
    private void setGokartList(List<Gokart> gokartList) {
        this.gokartList = gokartList;
    }

    /**
     * gets karting_companyList
     *
     * @return value of karting_companyList
     */
    private List<Karting_company> getKarting_companyList() {
        return karting_companieList;
    }

    /**
     * sets karting_companyList
     *
     * @param karting_companieList the value to set
     */
    private void setKarting_companyList(List<Karting_company> karting_companieList) {
        this.karting_companieList = karting_companieList;
    }


    /**
     * gets circuitList
     *
     * @return value of circuitList
     */
    private List<Circuit> getCircuitList() {
        return circuitList;
    }

    /**
     * sets karting_companyList
     *
     * @param circuitList the value to set
     */
    private void setCircuitList(List<Circuit> circuitList) {
        this.circuitList = circuitList;
    }

    /**
     * inserts a new gokart into the gokartList
     *
     * @param gokart the gokart to be saved
     */
    public static void insertGokart(Gokart gokart) {
        getInstance().getGokartList().add(gokart);
        getInstance().writeGokartJSON();
    }

    /**
     * writes the bookList to the JSON-file
     */
    private void writeGokartJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("gokartJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getInstance().getGokartList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * updates the bookList
     */
    public static void updateGokart() {
        getInstance().writeGokartJSON();
    }

    /**
     * deletes a book identified by the bookUUID
     * @param gokart_number  the key
     * @return  success=true/false
     */
    public static boolean deleteGokart(String gokart_number) {
        Gokart gokart = getInstance().readGokartByGokart_number(gokart_number);
        if (gokart != null) {
            getInstance().getGokartList().remove(gokart);
            getInstance().writeGokartJSON();
            return true;
        } else {
            return false;
        }
    }


    /**
     * inserts a new circuit into the circuitList
     *
     * @param circuit the circuit to be saved
     */
    public static void insertCircuit(Circuit circuit) {
        getInstance().getCircuitList().add(circuit);
        getInstance().writeCircuitJSON();
    }

    /**
     * writes the bookList to the JSON-file
     */
    private void writeCircuitJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("circuitJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getInstance().getCircuitList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * updates the bookList
     */
    public static void updateCircuit() {
        getInstance().writeCircuitJSON();
    }

    /**
     * deletes a circuit identified by the circuitID
     * @param circuitID  the key
     * @return  success=true/false
     */
    public static boolean deleteCircuit(int circuitID) {
        Circuit circuit = getInstance().readCircuitByID(circuitID);
        if (circuit != null) {
            getInstance().getCircuitList().remove(circuit);
            getInstance().writeCircuitJSON();
            return true;
        } else {
            return false;
        }
    }




}
