package Aerolinea;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Aerolinea {

    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////
    String nombre;
    private final HashMap<String, LinkedList<Vuelo>> vuelos; // Tree map -> K: Destino | V: LinkedList<Vuelo> (Ordenado segun precio)
    private final HashSet<Cliente> clientes;
    private final ArrayList<Avion> aviones;
    private final ArrayList<Aeropuerto> aeropuertos;

    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

    public Aerolinea(String nombre) {
        this.nombre = nombre;
        this.vuelos = new HashMap<String, LinkedList<Vuelo>>();
        this.clientes = new HashSet<Cliente>();
        this.aviones = new ArrayList<Avion>();
        this.aeropuertos = new ArrayList<Aeropuerto>();
    }

    ////////////////////////////////////////////
    // GETTERS AND SETTERS ---------------------
    ////////////////////////////////////////////

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    ////////////////////////////////////////////
    // METODOS ----------------------------
    ////////////////////////////////////////////

    // funcion provisorioa para probar agregarVuelos
//    public void addVuelosHC() {
//        Aeropuerto nuevayork = new Aeropuerto("JFK", "Nueva York", "Estados Unidos");
//        Aeropuerto londres = new Aeropuerto("LHR", "Londres", "Reino Unido");
//        Aeropuerto paris = new Aeropuerto("CDG", "Paris", "Francia");
//        Aeropuerto tokio = new Aeropuerto("HND", "Tokio", "Japon");
//        Aeropuerto sydney = new Aeropuerto("SYD", "Sydney", "Australia");
//        Aeropuerto dubai = new Aeropuerto("DXB", "Dubai", "Emiratos Arabes Unidos");
//        Aeropuerto sanfrancisco = new Aeropuerto("SFO", "San Francisco", "Estados Unidos");
//        Aeropuerto frankfurt = new Aeropuerto("FRA", "Frankfurt", "Alemania");
//        Aeropuerto seul = new Aeropuerto("ICN", "Seul", "Corea del Sur");
//        Aeropuerto ciudadmexico = new Aeropuerto("MEX", "Ciudad de Mexico", "Mexico");
//        Aeropuerto buenosaires = new Aeropuerto("EZE", "Buenos Aires", "Argentina");
//
//        aeropuertos.add(nuevayork);
//        aeropuertos.add(londres);
//        aeropuertos.add(paris);
//        aeropuertos.add(tokio);
//        aeropuertos.add(sydney);
//        aeropuertos.add(dubai);
//        aeropuertos.add(sanfrancisco);
//        aeropuertos.add(frankfurt);
//        aeropuertos.add(seul);
//        aeropuertos.add(ciudadmexico);
//        aeropuertos.add(buenosaires);
//
//        Avion boeing747 = new Avion("A001", "Boeing 747", 2000.0, 150);
//        Avion privado1 = new Avion("B002", "Cessna Citation X", 3000.0, 8);
//        Avion aribusA380 = new Avion("C003", "Airbus A380", 8000.0, 550);
//        Avion privado2 = new Avion("D004", "Gulfstream G650", 7000.0, 14);
//        Avion boeing787 = new Avion("E005", "Boeing 787", 11000.0, 300);
//        Avion privado3 = new Avion("F006", "Bombardier Global 6000", 6000.0, 12);
//
//        aviones.add(boeing747);
//        aviones.add(privado1);
//        aviones.add(aribusA380);
//        aviones.add(privado2);
//        aviones.add(boeing787);
//        aviones.add(privado3);
//
//        LocalDateTime fecha1 = LocalDateTime.of(2023, 7, 1, 8, 0);
//        LocalDateTime fecha2 = LocalDateTime.of(2023, 7, 2, 12, 30);
//        LocalDateTime fecha3 = LocalDateTime.of(2023, 7, 3, 16, 45);
//        LocalDateTime fecha4 = LocalDateTime.of(2023, 7, 4, 9, 15);
//        LocalDateTime fecha5 = LocalDateTime.of(2023, 7, 5, 14, 0);
//        LocalDateTime fecha6 = LocalDateTime.of(2023, 7, 6, 19, 30);
//        LocalDateTime fecha7 = LocalDateTime.of(2023, 7, 7, 8, 0);
//        LocalDateTime fecha8 = LocalDateTime.of(2023, 7, 9, 12, 30);
//        LocalDateTime fecha9 = LocalDateTime.of(2023, 7, 10, 16, 45);
//        LocalDateTime fecha10 = LocalDateTime.of(2023, 7, 11, 9, 15);
//        LocalDateTime fecha11 = LocalDateTime.of(2023, 7, 12, 14, 0);
//        LocalDateTime fecha12 = LocalDateTime.of(2023, 7, 13, 19, 30);
//        LocalDateTime fecha13 = LocalDateTime.of(2023, 7, 14, 8, 0);
//        LocalDateTime fecha14 = LocalDateTime.of(2023, 7, 16, 12, 30);
//        LocalDateTime fecha15 = LocalDateTime.of(2023, 7, 17, 16, 45);
//        LocalDateTime fecha16 = LocalDateTime.of(2023, 7, 18, 9, 15);
//        LocalDateTime fecha17 = LocalDateTime.of(2023, 7, 19, 14, 0);
//        LocalDateTime fecha18 = LocalDateTime.of(2023, 7, 20, 19, 30);
//        LocalDateTime fecha19 = LocalDateTime.of(2023, 7, 21, 8, 0);
//        LocalDateTime fecha20 = LocalDateTime.of(2023, 7, 23, 12, 30);
//        LocalDateTime fecha21 = LocalDateTime.of(2023, 7, 24, 16, 45);
//        LocalDateTime fecha22 = LocalDateTime.of(2023, 7, 25, 9, 15);
//        LocalDateTime fecha23 = LocalDateTime.of(2023, 7, 26, 14, 0);
//        LocalDateTime fecha24 = LocalDateTime.of(2023, 7, 27, 19, 30);
//        LocalDateTime fecha25 = LocalDateTime.of(2023, 7, 29, 8, 0);
//        LocalDateTime fecha26 = LocalDateTime.of(2023, 7, 30, 12, 30);
//        LocalDateTime fecha27 = LocalDateTime.of(2023, 7, 31, 16, 45);
//
//        LocalDateTime fecha28 = LocalDateTime.of(2023, 8, 1, 9, 15);
//        LocalDateTime fecha29 = LocalDateTime.of(2023, 8, 2, 14, 0);
//        LocalDateTime fecha30 = LocalDateTime.of(2023, 8, 3, 19, 30);
//        LocalDateTime fecha31 = LocalDateTime.of(2023, 8, 4, 8, 0);
//        LocalDateTime fecha32 = LocalDateTime.of(2023, 8, 5, 12, 30);
//        LocalDateTime fecha33 = LocalDateTime.of(2023, 8, 6, 16, 45);
//        LocalDateTime fecha34 = LocalDateTime.of(2023, 8, 7, 9, 15);
//        LocalDateTime fecha35 = LocalDateTime.of(2023, 8, 8, 14, 0);
//        LocalDateTime fecha36 = LocalDateTime.of(2023, 8, 9, 19, 30);
//        LocalDateTime fecha37 = LocalDateTime.of(2023, 8, 10, 8, 0);
//        LocalDateTime fecha38 = LocalDateTime.of(2023, 8, 11, 12, 30);
//        LocalDateTime fecha39 = LocalDateTime.of(2023, 8, 12, 18, 0);
//        LocalDateTime fecha40 = LocalDateTime.of(2023, 8, 13, 9, 15);
//        LocalDateTime fecha41 = LocalDateTime.of(2023, 8, 14, 14, 0);
//        LocalDateTime fecha42 = LocalDateTime.of(2023, 8, 15, 19, 30);
//        LocalDateTime fecha43 = LocalDateTime.of(2023, 8, 16, 8, 0);
//        LocalDateTime fecha44 = LocalDateTime.of(2023, 8, 17, 12, 30);
//        LocalDateTime fecha45 = LocalDateTime.of(2023, 8, 18, 16, 45);
//        LocalDateTime fecha46 = LocalDateTime.of(2023, 8, 19, 9, 15);
//        LocalDateTime fecha47 = LocalDateTime.of(2023, 8, 20, 14, 0);
//        LocalDateTime fecha48 = LocalDateTime.of(2023, 8, 21, 19, 30);
//        LocalDateTime fecha49 = LocalDateTime.of(2023, 8, 22, 8, 0);
//        LocalDateTime fecha50 = LocalDateTime.of(2023, 8, 23, 12, 30);
//        LocalDateTime fecha51 = LocalDateTime.of(2023, 8, 24, 16, 45);
//        LocalDateTime fecha52 = LocalDateTime.of(2023, 8, 25, 9, 15);
//        LocalDateTime fecha53 = LocalDateTime.of(2023, 8, 26, 14, 0);
//        LocalDateTime fecha54 = LocalDateTime.of(2023, 8, 27, 19, 30);
//        LocalDateTime fecha55 = LocalDateTime.of(2023, 8, 28, 8, 0);
//        LocalDateTime fecha56 = LocalDateTime.of(2023, 8, 29, 12, 30);
//        LocalDateTime fecha57 = LocalDateTime.of(2023, 8, 30, 16, 45);
//        LocalDateTime fecha58 = LocalDateTime.of(2023, 8, 31, 9, 15);
//
//        // Vuelos del 1 de julio de 2023
//        Vuelo vuelo1julio1 = new Vuelo("NJ001", 500.0, nuevayork, londres, 5500.0, boeing747, fecha1, 7.0);
//        Vuelo vuelo1julio2 = new Vuelo("LN001", 800.0, londres, nuevayork, 5500.0, privado1, fecha2, 6.0);
//
//// Vuelos del 2 de julio de 2023
//        Vuelo vuelo2julio1 = new Vuelo("NP002", 1500.0, nuevayork, paris, 5800.0, aribusA380, fecha3, 9.0);
//        Vuelo vuelo2julio2 = new Vuelo("PN002", 1200.0, paris, nuevayork, 5800.0, privado2, fecha4, 8.5);
//
//// Vuelos del 3 de julio de 2023
//        Vuelo vuelo3julio1 = new Vuelo("NS003", 1800.0, nuevayork, sydney, 16000.0, boeing787, fecha5, 19.0);
//        Vuelo vuelo3julio2 = new Vuelo("SN003", 2100.0, sydney, nuevayork, 16000.0, privado3, fecha6, 18.5);
//
//// Vuelos del 4 de julio de 2023
//        Vuelo vuelo4julio1 = new Vuelo("PL004", 1200.0, paris, londres, 344.0, privado1, fecha7, 1.0);
//        Vuelo vuelo4julio2 = new Vuelo("LP004", 1400.0, londres, paris, 344.0, boeing747, fecha8, 1.5);
//
//// Vuelos del 5 de julio de 2023
//        Vuelo vuelo5julio1 = new Vuelo("TP005", 900.0, tokio, paris, 9600.0, privado2, fecha9, 12.0);
//        Vuelo vuelo5julio2 = new Vuelo("PT005", 1100.0, paris, tokio, 9600.0, aribusA380, fecha10, 11.5);
//
//// Vuelos del 6 de julio de 2023
//        Vuelo vuelo6julio1 = new Vuelo("SD006", 600.0, sydney, dubai, 12000.0, boeing787, fecha11, 15.0);
//        Vuelo vuelo6julio2 = new Vuelo("DS006", 800.0, dubai, sydney, 12000.0, privado3, fecha12, 14.5);
//
//// Vuelos del 7 de julio de 2023
//        Vuelo vuelo7julio1 = new Vuelo("SF007", 700.0, sydney, frankfurt, 16500.0, privado1, fecha13, 18.0);
//        Vuelo vuelo7julio2 = new Vuelo("FS007", 900.0, frankfurt, sydney, 16500.0, boeing787, fecha14, 17.5);
//
//        // Vuelos del 8 de julio de 2023
//        Vuelo vuelo8julio1 = new Vuelo("FL008", 800.0, frankfurt, londres, 344.0, aribusA380, fecha15, 2.0);
//        Vuelo vuelo8julio2 = new Vuelo("LF008", 1000.0, londres, frankfurt, 344.0, privado2, fecha16, 1.5);
//
//// Vuelos del 9 de julio de 2023
//        Vuelo vuelo9julio1 = new Vuelo("PN009", 1300.0, paris, nuevayork, 5500.0, privado3, fecha17, 7.0);
//        Vuelo vuelo9julio2 = new Vuelo("NP009", 1600.0, nuevayork, paris, 5500.0, boeing747, fecha18, 6.5);
//
//// Vuelos del 10 de julio de 2023
//        Vuelo vuelo10julio1 = new Vuelo("DT010", 600.0, dubai, tokio, 9600.0, boeing787, fecha19, 12.0);
//        Vuelo vuelo10julio2 = new Vuelo("TD010", 800.0, tokio, dubai, 9600.0, privado1, fecha20, 11.5);
//
//// Vuelos del 11 de julio de 2023
//        Vuelo vuelo11julio1 = new Vuelo("LS011", 700.0, londres, sydney, 16000.0, privado2, fecha21, 17.0);
//        Vuelo vuelo11julio2 = new Vuelo("SL011", 900.0, sydney, londres, 16000.0, boeing747, fecha22, 16.5);
//
//// Vuelos del 12 de julio de 2023
//        Vuelo vuelo12julio1 = new Vuelo("FT012", 1000.0, frankfurt, tokio, 12000.0, aribusA380, fecha23, 14.0);
//        Vuelo vuelo12julio2 = new Vuelo("TF012", 1200.0, tokio, frankfurt, 12000.0, privado3, fecha24, 13.5);
//
//// Vuelos del 13 de julio de 2023
//        Vuelo vuelo13julio1 = new Vuelo("PT013", 800.0, paris, tokio, 9600.0, privado1, fecha25, 11.0);
//        Vuelo vuelo13julio2 = new Vuelo("TP013", 1000.0, tokio, paris, 9600.0, boeing787, fecha26, 10.5);
//
//// Vuelos del 14 de julio de 2023
//        Vuelo vuelo14julio1 = new Vuelo("SD014", 900.0, sydney, dubai, 12000.0, boeing747, fecha27, 16.0);
//        Vuelo vuelo14julio2 = new Vuelo("DS014", 1100.0, dubai, sydney, 12000.0, privado2, fecha28, 15.5);
//// Vuelos del 15 de julio de 2023
//        Vuelo vuelo15julio1 = new Vuelo("LF015", 800.0, londres, frankfurt, 344.0, aribusA380, fecha29, 2.0);
//        Vuelo vuelo15julio2 = new Vuelo("FL015", 1000.0, frankfurt, londres, 344.0, privado3, fecha30, 1.5);
//
//// Vuelos del 16 de julio de 2023
//        Vuelo vuelo16julio1 = new Vuelo("PN016", 1300.0, paris, nuevayork, 5500.0, privado1, fecha31, 7.0);
//        Vuelo vuelo16julio2 = new Vuelo("NP016", 1600.0, nuevayork, paris, 5500.0, boeing747, fecha32, 6.5);
//
//// Vuelos del 17 de julio de 2023
//        Vuelo vuelo17julio1 = new Vuelo("DT017", 600.0, dubai, tokio, 9600.0, boeing787, fecha33, 12.0);
//        Vuelo vuelo17julio2 = new Vuelo("TD017", 800.0, tokio, dubai, 9600.0, privado2, fecha34, 11.5);
//
//// Vuelos del 18 de julio de 2023
//        Vuelo vuelo18julio1 = new Vuelo("LS018", 700.0, londres, sydney, 16000.0, privado3, fecha35, 17.0);
//        Vuelo vuelo18julio2 = new Vuelo("SL018", 900.0, sydney, londres, 16000.0, boeing747, fecha36, 16.5);
//
//// Vuelos del 19 de julio de 2023
//        Vuelo vuelo19julio1 = new Vuelo("FT019", 1000.0, frankfurt, tokio, 12000.0, aribusA380, fecha37, 14.0);
//        Vuelo vuelo19julio2 = new Vuelo("TF019", 1200.0, tokio, frankfurt, 12000.0, privado1, fecha38, 13.5);
//
//// Vuelos del 20 de julio de 2023
//        Vuelo vuelo20julio1 = new Vuelo("PT020", 800.0, paris, tokio, 9600.0, privado2, fecha39, 11.0);
//        Vuelo vuelo20julio2 = new Vuelo("TP020", 1000.0, tokio, paris, 9600.0, boeing787, fecha40, 10.5);
//
//// Vuelos del 21 de julio de 2023
//        Vuelo vuelo21julio1 = new Vuelo("SD021", 900.0, sydney, dubai, 12000.0, boeing747, fecha41, 16.0);
//        Vuelo vuelo21julio2 = new Vuelo("DS021", 1100.0, dubai, sydney, 12000.0, privado3, fecha42, 15.5);
//
//// Vuelos del 22 de julio de 2023
//        Vuelo vuelo22julio1 = new Vuelo("LF022", 800.0, londres, frankfurt, 344.0, aribusA380, fecha43, 2.0);
//        Vuelo vuelo22julio2 = new Vuelo("FL022", 1000.0, frankfurt, londres, 344.0, privado1, fecha44, 1.5);
//// Vuelos del 23 de julio de 2023
//        Vuelo vuelo23julio1 = new Vuelo("PS023", 700.0, paris, sydney, 16000.0, privado2, fecha45, 17.0);
//        Vuelo vuelo23julio2 = new Vuelo("SP023", 900.0, sydney, paris, 16000.0, boeing787, fecha46, 16.5);
//
//// Vuelos del 24 de julio de 2023
//        Vuelo vuelo24julio1 = new Vuelo("DT024", 1000.0, dubai, tokio, 9600.0, aribusA380, fecha47, 12.0);
//        Vuelo vuelo24julio2 = new Vuelo("TD024", 1200.0, tokio, dubai, 9600.0, privado3, fecha48, 11.5);
//
//// Vuelos del 25 de julio de 2023
//        Vuelo vuelo25julio1 = new Vuelo("FP025", 800.0, frankfurt, paris, 344.0, privado1, fecha49, 2.0);
//        Vuelo vuelo25julio2 = new Vuelo("PF025", 1000.0, paris, frankfurt, 344.0, boeing747, fecha50, 1.5);
//
//// Vuelos del 26 de julio de 2023
//        Vuelo vuelo26julio1 = new Vuelo("LS026", 1300.0, londres, sydney, 16000.0, boeing787, fecha51, 17.0);
//        Vuelo vuelo26julio2 = new Vuelo("SL026", 1600.0, sydney, londres, 16000.0, privado2, fecha52, 16.5);
//
//// Vuelos del 27 de julio de 2023
//        Vuelo vuelo27julio1 = new Vuelo("NP027", 1000.0, nuevayork, paris, 5500.0, aribusA380, fecha53, 7.0);
//        Vuelo vuelo27julio2 = new Vuelo("PN027", 1200.0, paris, nuevayork, 5500.0, privado3, fecha54, 6.5);
//
//// Vuelos del 28 de julio de 2023
//        Vuelo vuelo28julio1 = new Vuelo("SD028", 600.0, sydney, dubai, 12000.0, privado1, fecha55, 16.0);
//        Vuelo vuelo28julio2 = new Vuelo("DS028", 800.0, dubai, sydney, 12000.0, boeing747, fecha56, 15.5);
//
//// Vuelos del 29 de julio de 2023
//        Vuelo vuelo29julio1 = new Vuelo("TF029", 800.0, tokio, frankfurt, 12000.0, privado2, fecha57, 14.0);
//        Vuelo vuelo29julio2 = new Vuelo("FT029", 1000.0, frankfurt, tokio, 12000.0, aribusA380, fecha58, 13.5);
//
//        Vuelo vuelo30julio1 = new Vuelo("BN030", 600.0, buenosaires, nuevayork, 5800.0, boeing787, fecha51, 10.0);
//        Vuelo vuelo30julio2 = new Vuelo("NB030", 800.0, nuevayork, buenosaires, 5800.0, privado1, fecha31, 9.5);
//
//        Vuelo vuelo31julio1 = new Vuelo("LP031", 500.0, londres, paris, 344.0, privado2, fecha1, 2.0);
//        Vuelo vuelo31julio2 = new Vuelo("PL031", 700.0, paris, londres, 344.0, boeing747, fecha2, 1.5);
//
//        Vuelo vuelo1agosto1 = new Vuelo("TB801", 1200.0, tokio, buenosaires, 19000.0, aribusA380, fecha23, 20.0);
//        Vuelo vuelo1agosto2 = new Vuelo("BT801", 1400.0, buenosaires, tokio, 19000.0, privado3, fecha4, 19.5);
//
//        Vuelo vuelo2agosto1 = new Vuelo("SF802", 900.0, sanfrancisco, frankfurt, 9200.0, privado1, fecha15, 11.0);
//        Vuelo vuelo2agosto2 = new Vuelo("FS802", 1100.0, frankfurt, sanfrancisco, 9200.0, boeing747, fecha6, 10.5);
//
//        Vuelo vuelo3agosto1 = new Vuelo("SM803", 1300.0, seul, ciudadmexico, 18000.0, aribusA380, fecha37, 14.0);
//        Vuelo vuelo3agosto2 = new Vuelo("MS803", 1500.0, ciudadmexico, seul, 18000.0, privado2, fecha28, 13.5);
//
//        Vuelo vuelo4agosto1 = new Vuelo("DB804", 800.0, dubai, buenosaires, 13000.0, privado3, fecha6, 15.0);
//        Vuelo vuelo4agosto2 = new Vuelo("BD804", 1000.0, buenosaires, dubai, 13000.0, boeing787, fecha55, 14.5);
//
//        Vuelo vuelo5agosto1 = new Vuelo("TS805", 700.0, tokio, seul, 8600.0, boeing787, fecha11, 9.0);
//        Vuelo vuelo5agosto2 = new Vuelo("ST805", 900.0, seul, tokio, 8600.0, privado1, fecha22, 8.5);
//
//        Vuelo vuelo6agosto1 = new Vuelo("SN806", 1000.0, sydney, nuevayork, 16000.0, privado2, fecha32, 17.0);
//        Vuelo vuelo6agosto2 = new Vuelo("NS806", 1200.0, nuevayork, sydney, 16000.0, aribusA380, fecha14, 16.5);
//
//// Vuelos del 10 de agosto de 2023
//        Vuelo vuelo10agosto1 = new Vuelo("BM810", 800.0, buenosaires, ciudadmexico, 5500.0, boeing787, fecha15, 10.0);
//        Vuelo vuelo10agosto2 = new Vuelo("MB810", 1000.0, ciudadmexico, buenosaires, 5500.0, privado1, fecha33, 9.5);
//
//// Vuelos del 15 de agosto de 2023
//        Vuelo vuelo15agosto1 = new Vuelo("CS815", 600.0, ciudadmexico, seul, 12000.0, privado2, fecha12, 12.0);
//        Vuelo vuelo15agosto2 = new Vuelo("SC815", 800.0, seul, ciudadmexico, 12000.0, aribusA380, fecha18, 11.5);
//
//// Vuelos del 20 de agosto de 2023
//        Vuelo vuelo20agosto1 = new Vuelo("BS820", 900.0, buenosaires, seul, 19000.0, privado3, fecha8, 15.0);
//        Vuelo vuelo20agosto2 = new Vuelo("SB820", 1100.0, seul, buenosaires, 19000.0, boeing747, fecha34, 14.5);
//
//// Vuelos del 25 de agosto de 2023
//        Vuelo vuelo25agosto1 = new Vuelo("CM825", 1200.0, ciudadmexico, londres, 9000.0, boeing787, fecha32, 16.0);
//        Vuelo vuelo25agosto2 = new Vuelo("MC825", 1400.0, londres, ciudadmexico, 9000.0, privado1, fecha43, 15.5);
//
//// Vuelos del 30 de agosto de 2023
//        Vuelo vuelo30agosto1 = new Vuelo("BS830", 700.0, buenosaires, sanfrancisco, 12000.0, privado2, fecha23, 11.0);
//        Vuelo vuelo30agosto2 = new Vuelo("SB830", 900.0, sanfrancisco, buenosaires, 12000.0, aribusA380, fecha13, 10.5);
//
//// Vuelos del 5 de septiembre de 2023
//        Vuelo vuelo5septiembre1 = new Vuelo("CS905", 800.0, ciudadmexico, sydney, 16000.0, aribusA380, fecha14, 14.0);
//        Vuelo vuelo5septiembre2 = new Vuelo("SC905", 1000.0, sydney, ciudadmexico, 16000.0, privado3, fecha56, 13.5);
//
//// Vuelos del 10 de septiembre de 2023
//        Vuelo vuelo10septiembre1 = new Vuelo("SL910", 1300.0, seul, londres, 10000.0, privado1, fecha57, 15.0);
//        Vuelo vuelo10septiembre2 = new Vuelo("LS910", 1500.0, londres, seul, 10000.0, boeing787, fecha8, 14.5);
//
//        // Vuelos del 1 de julio de 2023
//        agregarVuelo(vuelo1julio1);
//        agregarVuelo(vuelo1julio2);
//
//// Vuelos del 2 de julio de 2023
//        agregarVuelo(vuelo2julio1);
//        agregarVuelo(vuelo2julio2);
//
//// Vuelos del 3 de julio de 2023
//        agregarVuelo(vuelo3julio1);
//        agregarVuelo(vuelo3julio2);
//
//// Vuelos del 4 de julio de 2023
//        agregarVuelo(vuelo4julio1);
//        agregarVuelo(vuelo4julio2);
//
//// Vuelos del 5 de julio de 2023
//        agregarVuelo(vuelo5julio1);
//        agregarVuelo(vuelo5julio2);
//
//// Vuelos del 6 de julio de 2023
//        agregarVuelo(vuelo6julio1);
//        agregarVuelo(vuelo6julio2);
//
//// Vuelos del 7 de julio de 2023
//        agregarVuelo(vuelo7julio1);
//        agregarVuelo(vuelo7julio2);
//
//// Vuelos del 8 de julio de 2023
//        agregarVuelo(vuelo8julio1);
//        agregarVuelo(vuelo8julio2);
//
//// Vuelos del 9 de julio de 2023
//        agregarVuelo(vuelo9julio1);
//        agregarVuelo(vuelo9julio2);
//
//// Vuelos del 10 de julio de 2023
//        agregarVuelo(vuelo10julio1);
//        agregarVuelo(vuelo10julio2);
//
//// Vuelos del 11 de julio de 2023
//        agregarVuelo(vuelo11julio1);
//        agregarVuelo(vuelo11julio2);
//
//// Vuelos del 12 de julio de 2023
//        agregarVuelo(vuelo12julio1);
//        agregarVuelo(vuelo12julio2);
//
//// Vuelos del 13 de julio de 2023
//        agregarVuelo(vuelo13julio1);
//        agregarVuelo(vuelo13julio2);
//
//// Vuelos del 14 de julio de 2023
//        agregarVuelo(vuelo14julio1);
//        agregarVuelo(vuelo14julio2);
//
//// Vuelos del 15 de julio de 2023
//        agregarVuelo(vuelo15julio1);
//        agregarVuelo(vuelo15julio2);
//
//// Vuelos del 16 de julio de 2023
//        agregarVuelo(vuelo16julio1);
//        agregarVuelo(vuelo16julio2);
//
//// Vuelos del 17 de julio de 2023
//        agregarVuelo(vuelo17julio1);
//        agregarVuelo(vuelo17julio2);
//
//        agregarVuelo(vuelo18julio1);
//        agregarVuelo(vuelo18julio2);
//        agregarVuelo(vuelo19julio1);
//        agregarVuelo(vuelo19julio2);
//        agregarVuelo(vuelo20julio1);
//        agregarVuelo(vuelo20julio2);
//        agregarVuelo(vuelo21julio1);
//        agregarVuelo(vuelo21julio2);
//        agregarVuelo(vuelo22julio1);
//        agregarVuelo(vuelo22julio2);
//        agregarVuelo(vuelo23julio1);
//        agregarVuelo(vuelo23julio2);
//        agregarVuelo(vuelo24julio1);
//        agregarVuelo(vuelo24julio2);
//        agregarVuelo(vuelo25julio1);
//        agregarVuelo(vuelo25julio2);
//        agregarVuelo(vuelo26julio1);
//        agregarVuelo(vuelo26julio2);
//        agregarVuelo(vuelo27julio1);
//        agregarVuelo(vuelo27julio2);
//        agregarVuelo(vuelo28julio1);
//        agregarVuelo(vuelo28julio2);
//        agregarVuelo(vuelo29julio1);
//        agregarVuelo(vuelo29julio2);
//        agregarVuelo(vuelo30julio1);
//        agregarVuelo(vuelo30julio2);
//        agregarVuelo(vuelo31julio1);
//        agregarVuelo(vuelo31julio2);
//        agregarVuelo(vuelo1agosto1);
//        agregarVuelo(vuelo1agosto2);
//        agregarVuelo(vuelo2agosto1);
//        agregarVuelo(vuelo2agosto2);
//        agregarVuelo(vuelo3agosto1);
//        agregarVuelo(vuelo3agosto2);
//        agregarVuelo(vuelo4agosto1);
//        agregarVuelo(vuelo4agosto2);
//        agregarVuelo(vuelo5agosto1);
//        agregarVuelo(vuelo5agosto2);
//        agregarVuelo(vuelo6agosto1);
//        agregarVuelo(vuelo6agosto2);
//        agregarVuelo(vuelo10agosto1);
//        agregarVuelo(vuelo10agosto2);
//        agregarVuelo(vuelo15agosto1);
//        agregarVuelo(vuelo15agosto2);
//        agregarVuelo(vuelo20agosto1);
//        agregarVuelo(vuelo20agosto2);
//        agregarVuelo(vuelo25agosto1);
//        agregarVuelo(vuelo25agosto2);
//        agregarVuelo(vuelo30agosto1);
//        agregarVuelo(vuelo30agosto2);
//        agregarVuelo(vuelo10septiembre1);
//        agregarVuelo(vuelo10septiembre2);
//        agregarVuelo(vuelo5septiembre1);
//        agregarVuelo(vuelo5septiembre2);
//
//
//    }


//    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//           VUELOS       ||||||||||||||||||||||||||||||||||||||||||||||||
//    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    public LinkedList<Vuelo> obtenerVuelosFechaSalida(LinkedList<Vuelo> vuelosModificar, LocalDateTime minima, LocalDateTime maxima) {
        LinkedList<Vuelo> listaRetornar = new LinkedList<>();

        Iterator<Vuelo> iterator = vuelosModificar.iterator();
        while (iterator.hasNext()) {
            Vuelo vuelo = iterator.next();
            if (vuelo.getSalida().isAfter(minima) && vuelo.getSalida().isBefore(maxima)) {
                listaRetornar.add(vuelo);
            }
        }

        if (listaRetornar.isEmpty()) {
            System.out.println("No existen vuelos entre las fechas especificadas");
        }

        return listaRetornar;
    }


    public LinkedList<Vuelo> obtenerVuelosOrigen(String ciudadOrigen) {
        LinkedList<Vuelo> listaAgregarVuelos = new LinkedList<Vuelo>();

        String[] keysDestinos = vuelos.keySet().toArray(new String[0]);

        for (String destino: keysDestinos) {
            LinkedList<Vuelo> vuelosPorKey = vuelos.get(destino);

            for (Vuelo vue: vuelosPorKey) {
                if (vue.getOrigen().getCiudad().equals(ciudadOrigen)) {
                    listaAgregarVuelos.add(vue);
                }
            }
        }

        return listaAgregarVuelos;
    }

    public LinkedList<Vuelo> obtenerVuelosDestinoOrigen(String destino, String origen) {
        LinkedList<Vuelo> vuelosDestinoOrigen = new LinkedList<Vuelo>();
        if (vuelos.containsKey(destino)) {
            vuelosDestinoOrigen = new LinkedList<>(vuelos.get(destino)); // asi se pasa por copia (y no por referencia que rompe todoo)
        }
        try {
            if (vuelos.get(destino) != null) { // aca compruebo de nuevo en este caso si es null, que en lo de arriba no puedo porque ya crea la lista por default
                Iterator<Vuelo> iterator = vuelosDestinoOrigen.iterator();
                while (iterator.hasNext()) {
                    Vuelo vuelo = iterator.next();
                    if (!vuelo.getOrigen().getCiudad().equals(origen)) {
                        iterator.remove();
                    }
                }
            }
            else {
                throw new AeropuertoInexistenteException("El aeropuerto de destino ingresado no existe.");
            }
        }
        catch (AeropuertoInexistenteException e) {
            System.out.println(e.getMessage());
        }

        return vuelosDestinoOrigen;
    }

    public void mostrarVuelosPorLista(LinkedList<Vuelo> vuelosMostrar) {
        if (!vuelosMostrar.isEmpty()) {
            for (Vuelo vue : vuelosMostrar) {
                System.out.println("----------------");
                System.out.println(vue.toStringCorto());
            }
            System.out.println("----------------");
        }
        else {
            System.out.println("No se encontraron vuelos");
        }
    }


    public void agregarVueloTeclado() {
        Scanner scan = new Scanner(System.in);
        System.out.println("AGREGAR VUELO");
        System.out.print("Codigo: ");
        String codigo = scan.nextLine();
        Vuelo vuelo = buscarVuelo(codigo);

        try {
            if (vuelo == null) {
                vuelo = cargarVueloPorTeclado(codigo);
                agregarVuelo(vuelo);
                // AGREGAR VUELO A LA COLECCION
                System.out.println("VUELO AGREGADO CON ÉXITO");
            } else {
                throw new VueloExistenteException("ERROR: EL VUELO INGRESADO YA EXISTE");
            }
        } catch (VueloExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void agregarVuelo(Vuelo vuelo) {
        Vuelo vueloBuscado = buscarVuelo(vuelo.getCodigoVuelo());

        try {
            if (vueloBuscado == null) {
                String destinoCiudad = vuelo.getDestino().getCiudad();
                LinkedList<Vuelo> addVuelos = null;
                if (vuelos.containsKey(destinoCiudad)) {
                    addVuelos = vuelos.get(destinoCiudad);
                } else {
                    addVuelos = new LinkedList<Vuelo>();
                }

                addVuelos.add(vuelo);

                vuelos.put(destinoCiudad, addVuelos);

                System.out.println("VUELO AGREGADO CON ÉXITO");
            } else {
                throw new VueloExistenteException("El vuelo que se intento agregar ya existe! '" + vuelo.getCodigoVuelo() + "'");
            }
        } catch (VueloExistenteException e) {
            System.out.println(e.getMessage());
        }


    }

    public Vuelo buscarVuelo(String codigo) {
        Vuelo vuelo = null;

        String[] destinos = vuelos.keySet().toArray(new String[0]);

        for (String destino : destinos) {
            for (Vuelo v : vuelos.get(destino)) {
                if (v.getCodigoVuelo().equals(codigo)) {
                    vuelo = v;
                }
            }
        }

        return vuelo;
    }

    private Vuelo cargarVueloPorTeclado(String codigo) {
        Scanner scan = new Scanner(System.in);

        Vuelo vuelo = null;
        System.out.print("Aeropuerto de origen (codigo): ");
        String codigoOrigen = scan.nextLine();
        Aeropuerto origen = buscarAeropuerto(codigoOrigen);

        try {
            if (origen != null) {
                System.out.print("Aeropuerto de destino: ");
                String codigoDestino = scan.nextLine();
                Aeropuerto destino = buscarAeropuerto(codigoDestino);

                if (destino != null) {
                    System.out.print("Fecha y hora de partida (AAAA-MM-DD HH:mm): ");
                    String fechaHora = scan.nextLine();
                    LocalDateTime salida = LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                    System.out.print("Distancia: ");
                    double distancia = scan.nextDouble();
                    scan.nextLine();

                    System.out.print("Duración en minutos: ");
                    double duracion = scan.nextDouble();
                    scan.nextLine();

                    System.out.print("Avion: ");
                    String codigoAvion = scan.nextLine();
                    Avion avion = buscarAvion(codigoAvion);

                    if (avion != null) {
                        System.out.print("Precio: ");
                        double precio = scan.nextInt();
                        scan.nextLine();

                        vuelo = new Vuelo(codigo, precio, origen, destino, distancia, avion, salida, duracion);
                    } else {
                        throw new AvionInexistenteException("El avion en cuestion no existe.");
                    }

                } else {
                    throw new AeropuertoInexistenteException("El aeropuerto de destino no existe.");
                }
            } else {
                throw new AeropuertoInexistenteException("El aeropuerto de origen no existe.");
            }
        } catch (AeropuertoInexistenteException | AvionInexistenteException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }

        return vuelo;
    }

    public void modificarVuelo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("MODIFICAR VUELO");
        System.out.print("Código de vuelo: ");
        String codigo = scan.nextLine();
        Vuelo vuelo = buscarVuelo(codigo);

        try {

            if (vuelo != null) {
                System.out.println("1. Fecha y hora de salida");
                System.out.println("2. Distancia");
                System.out.println("3. Duracion");
                System.out.println("4. Avión");
                System.out.println("5. Precio");
                System.out.println("6. Estado del vuelo");
                System.out.print("Seleccione el campo a modificar: ");
                int opcion = scan.nextInt();
                scan.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Nueva fecha y hora de partida (AAAA-MM-DD HH:mm): ");
                        String fechaHora = scan.nextLine();
                        LocalDateTime salida = LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        vuelo.setSalida(salida);
                        break;

                    case 2:
                        System.out.print("Nueva distancia: ");
                        double distancia = scan.nextDouble();
                        scan.nextLine();
                        vuelo.setDistanciaKm(distancia);
                        break;

                    case 3:
                        System.out.print("Nueva duracion: ");
                        double duracion = scan.nextDouble();
                        scan.nextLine();
                        vuelo.setDuracion(duracion);
                        break;

                    case 4:
                        System.out.print("Nuevo avion (codigo): ");
                        String codigoAvion = scan.nextLine();
                        Avion avion = buscarAvion(codigoAvion);
                        if (avion != null) {
                            vuelo.setAvion(avion);
                        } else {
                            throw new AvionInexistenteException("No se encontro el avion buscado.");
                        }
                        break;

                    case 5:
                        System.out.print("Nuevo precio: ");
                        double precio = scan.nextDouble();
                        scan.nextLine();
                        vuelo.setPrecio(precio);
                        break;

                    case 6:
                        System.out.print("Estado del vuelo: ");
                        String estado = scan.nextLine();
                        if (estado.equals("Demorado")) {
                            vuelo.setDemorado();
                        } else if (estado.equals("En horario")) {
                            vuelo.setEnHorario();
                        } else if (estado.equals("Cancelado")) {
                            vuelo.setCancelado();
                        } else {
                            System.out.println("ERROR: ESTADO INVÁLIDO");
                        }
                        break;

                    default:
                        System.out.println("ERROR: OPCIÓN INVÁLIDA");
                        break;
                }

                System.out.println("VUELO MODIFICADO CON ÉXITO");
            } else {
                throw new VueloInexistenteException("El vuelo que se quiere modificar no existe.");
            }
        } catch (VueloInexistenteException | AvionInexistenteException | InputMismatchException |
                 java.time.format.DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarVuelo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Código de vuelo: ");
        String codigo = scan.nextLine();
        Vuelo vuelo = buscarVuelo(codigo);

        try {
            if (vuelo != null) {
                LinkedList<Vuelo> vuelosCiudad = vuelos.get(vuelo.getDestino().getCiudad());
                vuelosCiudad.remove(vuelo);
                System.out.println("VUELO ELIMINADO CON ÉXITO");
            } else {
                throw new VueloInexistenteException("El vuelo a eliminar no se encontro.");
            }
        } catch (VueloInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarVuelos() {
        String[] lista_destinos = vuelos.keySet().toArray(new String[0]);
        for (String destino : lista_destinos) {
            System.out.println("----------------");
            System.out.println("Destino: " + destino);
            mostrarVuelosDestino(vuelos.get(destino));
        }
    }

    private void mostrarVuelosDestino(LinkedList<Vuelo> vuelos_destino) {
        int i = 0;
        for (Vuelo vue : vuelos_destino) {
            System.out.println("#" + i);
            i += 1;
            System.out.println(vue.toString());
        }
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    //       AVIONES      ||||||||||||||||||||||||||||||||||||||||||||||||
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    public void agregarAvionesTeclado() {
        Scanner scan = new Scanner(System.in);
        char continuar = 's';

        while (continuar == 's') {
            boolean avionExistente = true;

            while (avionExistente) {
                System.out.print("ID: ");
                String id = scan.nextLine();
                Avion avion = buscarAvion(id);
                if (avion != null) {
                    System.out.println("El avion ya existe, intente de nuevo cargando uno diferente");
                } else {
                    avionExistente = false;
                    avion = cargarAvionPorTeclado(id);
                    agregarAvion(avion);
                }
            }

            System.out.println("Quiere seguir ingresando aviones? 's'/'n'");
            continuar = scan.nextLine().charAt(0);
        }
    }

    private Avion cargarAvionPorTeclado(String id) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = scan.nextLine();
        System.out.print("Distancia: ");
        double distancia = scan.nextDouble();
        scan.nextLine();
        System.out.print("Cantidad de pasajeros: ");
        int cantidad = scan.nextInt(); //////// CHEQUEAR QUE NO SE ROMPA ESTA MIERDA SI PONES UN STRING EN EL INT ************************************
        scan.nextLine();

        return new Avion(id, nombre, distancia, cantidad);
    }

    public void agregarAvion(Avion avion) {
        try {
            if (buscarAvion(avion.getId()) == null) {
                aviones.add(avion);
                System.out.println("AVIÓN AGREGADO CON ÉXITO");
            } else {
                throw new AvionExistenteException();
            }

        } catch (AvionExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarAvion() {
        Scanner scan = new Scanner(System.in);
        System.out.println("MODIFICAR AVION");
        System.out.print("ID del avión: ");
        String id = scan.nextLine();
        Avion avion = buscarAvion(id);

        if (avion != null) {
            System.out.println("1. Nombre");
            System.out.println("2. Distancia");
            System.out.println("3. Cantidad de pasajeros");
            System.out.print("Seleccione el campo a modificar: ");
            int opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    String nombre = scan.nextLine();
                    avion.setNombre(nombre);
                    break;

                case 2:
                    System.out.print("Nueva distancia: ");
                    double distancia = scan.nextDouble();
                    scan.nextLine();
                    avion.setDistancia(distancia);
                    break;

                case 3:
                    System.out.print("Nueva cantidad de pasajeros: ");
                    int cantidad = scan.nextInt();
                    scan.nextLine();
                    avion.setCantidadPasajeros(cantidad);
                    break;

                default:
                    System.out.println("ERROR: OPCIÓN INVÁLIDA");
                    break;
            }

            System.out.println("AVIÓN MODIFICADO CON ÉXITO");
        } else {
            System.out.println("ERROR: AVIÓN NO ENCONTRADO");
        }
    }

    public void eliminarAvion(String id) {
        Avion avion = buscarAvion(id);

        try {
            if (avion != null) {
                aviones.remove(avion);
                System.out.println("AVIÓN ELIMINADO CON ÉXITO");
            } else {
                throw new AvionInexistenteException();
            }
        } catch (AvionInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarAviones() {
        System.out.println("AVIONES");
        for (Avion avion : aviones) {
            System.out.println(avion.toString());
        }
    }

    private Avion buscarAvion(String id) {
        Avion avion = null;

        for (Avion a : aviones) {
            if (a.getId().equals(id)) {
                avion = a;
            }
        }

        return avion;
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    //       AEROPUERTOS      |||||||||||||||||||||||||||||||||||||||||||
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    public Aeropuerto buscarAeropuerto(String codigo) { // por codigo de aeropuerto
        Aeropuerto aeropuerto = null;

        for (Aeropuerto a : aeropuertos) {
            if (a.getCodigo().equals(codigo)) {
                aeropuerto = a;
            }
        }

        return aeropuerto;
    }

    public Aeropuerto buscarAeropuertoCiudad(String ciudad) { // por ciudad
        Aeropuerto aeropuerto = null;

        for (Aeropuerto a : aeropuertos) {
            if (a.getCiudad().equals(ciudad)) {
                aeropuerto = a;
            }
        }

        return aeropuerto;
    }

    public void agregarAeropuertosTeclado() {
        Scanner scan = new Scanner(System.in);
        char continuar = 's';

        while (continuar == 's') {
            boolean aeropuertoExistente = true;

            while (aeropuertoExistente) {
                System.out.print("Codigo: ");
                String codigo = scan.nextLine();
                Aeropuerto aeropuerto = buscarAeropuerto(codigo);
                if (aeropuerto != null) {
                    System.out.println("El aeropuerto ya existe, intente de nuevo cargando uno diferente");
                } else {
                    aeropuertoExistente = false;
                    aeropuerto = cargarAeropuertoPorTeclado(codigo);
                    agregarAeropuerto(aeropuerto);
                }
            }

            System.out.println("Quiere seguir ingresando aeropuertos? 's'/'n'");
            continuar = scan.nextLine().charAt(0);
        }
    }

    private Aeropuerto cargarAeropuertoPorTeclado(String codigo) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ciudad: ");
        String ciudad = scan.nextLine();
        System.out.print("País: ");
        String pais = scan.nextLine();
        scan.nextLine();

        return new Aeropuerto(codigo, ciudad, pais);
    }

    public void agregarAeropuerto(Aeropuerto aeropuerto) {
        try {
            if (buscarAeropuerto(aeropuerto.getCodigo()) == null) {
                aeropuertos.add(aeropuerto);
                System.out.println("AEROPUERTO AGREGADO CON ÉXITO");
            } else {
                throw new AeropuertoExistenteException();
            }

        } catch (AeropuertoExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarAeropuerto() {
        Scanner scan = new Scanner(System.in);
        System.out.println("MODIFICAR AEROPUERTO");
        System.out.print("Código del aeropuerto: ");
        String codigo = scan.nextLine();
        Aeropuerto aeropuerto = buscarAeropuerto(codigo);

        if (aeropuerto != null) {
            System.out.println("1. Ciudad");
            System.out.println("2. País");
            System.out.print("Seleccione el campo a modificar: ");
            int opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nueva ciudad: ");
                    String ciudad = scan.nextLine();
                    aeropuerto.setCiudad(ciudad);
                    break;

                case 2:
                    System.out.print("Nuevo país: ");
                    String pais = scan.nextLine();
                    scan.nextLine();
                    aeropuerto.setPais(pais);
                    break;

                default:
                    System.out.println("ERROR: OPCIÓN INVÁLIDA");
                    break;
            }

            System.out.println("AEROPUERTO MODIFICADO CON ÉXITO");
        } else {
            System.out.println("ERROR: AEROPUERTO NO ENCONTRADO");
        }
    }

    public void eliminarAeropuerto(String codigo) {
        Aeropuerto aeropuerto = buscarAeropuerto(codigo);
        try {
            if (aeropuerto != null) {
                aeropuertos.remove(aeropuerto);
                System.out.println("AEROPUERTO ELIMINADO CON ÉXITO");
            } else {
                throw new AeropuertoInexistenteException();
            }
        } catch (AeropuertoInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarAeropuerto() {
        System.out.println("AEROPUERTOS");
        for (Aeropuerto aeropuerto: aeropuertos) {
            System.out.println(aeropuerto.toString());
        }
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    //       CLIENTES          |||||||||||||||||||||||||||||||||||||||||||
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    public void mostrarClientes() { //ADMIN FUNC
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
    }

    public boolean existeCliente(Cliente cliente) {
        for (Cliente c : clientes) {
            if (c.getPasaporte().equalsIgnoreCase(cliente.getPasaporte()) || c.getNombreDeUsuario().equalsIgnoreCase(cliente.getNombreDeUsuario())) {
                return true;
            }
        }
        return false;
    }

    public Cliente agregarClientePorTeclado() {
        Scanner scanner = new Scanner(System.in);

        String nombre = "";
        String apellido = "";
        String pasaporte = "";
        String usuario = "";
        String contrasena = "";

        boolean nombreValido = false;
        boolean apellidoValido = false;
        boolean pasaporteValido = false;
        boolean usuarioValido = false;
        boolean contrasenaValida = false;

        while (!nombreValido) {
            System.out.print("Nombre: ");
            nombre = scanner.nextLine();

            // Comprobar si el nombre contiene números
            if (nombre.matches(".*\\d.*")) {
                System.out.println("El nombre no puede contener números.");
            } else {
                nombreValido = true;
            }
        }

        while (!apellidoValido) {
            System.out.print("Apellido: ");
            apellido = scanner.nextLine();

            // Comprobar si el apellido contiene números
            if (apellido.matches(".*\\d.*")) {
                System.out.println("El apellido no puede contener números.");
            } else {
                apellidoValido = true;
            }
        }

        while (!pasaporteValido) {
            System.out.print("N° de pasaporte (X1234567): ");
            pasaporte = scanner.nextLine();

            // formato: A1234567)
            if (!pasaporte.matches("[A-Z]\\d{7}")) {
                System.out.println("El pasaporte ingresado no es válido.");
            } else {
                pasaporteValido = true;
            }
        }

        while (!usuarioValido) {
            System.out.print("Nombre de usuario: ");
            usuario = scanner.nextLine();

            if (usuario.contains(" ")) {
                System.out.println("El usuario no puede contener espacios en blanco.");
            } else {
                usuarioValido = true;
            }
        }

        while (!contrasenaValida) {
            System.out.print("Contraseña: ");
            contrasena = scanner.nextLine();

            if (contrasena.length() < 6) {
                System.out.println("La contraseña debe tener al menos 6 caracteres.");
            } else {
                contrasenaValida = true;
            }
        }

        return new Cliente(nombre, apellido, pasaporte, usuario, contrasena);
    }

    public void agregarCliente(Cliente cliente) {
        try {
            if (existeCliente(cliente)) {
                throw new ClienteExistenteException();
            }
            clientes.add(cliente);
        } catch (ClienteExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarCliente(String pasaporte) {
        Cliente clienteAEliminar = null;
        for (Cliente cliente : clientes) {
            if (cliente.getPasaporte().equals(pasaporte)) {
                clienteAEliminar = cliente;
                break;
            }
        }
        try {
            if (clienteAEliminar == null) {
                throw new ClienteInexistenteException();
            }
            clientes.remove(clienteAEliminar);
        } catch (ClienteInexistenteException e) {
            System.out.println(e.getMessage());
        }


    }

    public void modificarCliente(String nombreDeUsuario) {
        Cliente clienteAModificar = null;
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                if (cliente.getNombreDeUsuario().equals(nombreDeUsuario)) {
                    clienteAModificar = cliente;
                    break;
                }
            }
        }
        if (clienteAModificar == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.println("¿Qué campo desea modificar?");
        System.out.println("1 - Nombre");
        System.out.println("2 - Apellido");
        System.out.println("3 - Pasaporte");
        System.out.println("4 - Modificar todos los campos");

        int opcion = input.nextInt();
        input.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Ingrese el nuevo nombre para el cliente:");
                String nuevoNombre = input.nextLine();
                clienteAModificar.setNombre(nuevoNombre);
                break;
            case 2:
                System.out.println("Ingrese el nuevo apellido para el cliente:");
                String nuevoApellido = input.nextLine();
                clienteAModificar.setApellido(nuevoApellido);
                break;
            case 3:
                System.out.println("Ingrese el nuevo pasaporte para el cliente:");
                String nuevoPasaporte = input.nextLine();
                clienteAModificar.setPasaporte(nuevoPasaporte);
                break;
            case 4:
                System.out.println("Ingrese el nuevo nombre para el cliente:");
                nuevoNombre = input.nextLine();
                System.out.println("Ingrese el nuevo apellido para el cliente:");
                nuevoApellido = input.nextLine();
                System.out.println("Ingrese el nuevo pasaporte para el cliente:");
                nuevoPasaporte = input.nextLine();
                clienteAModificar.setNombre(nuevoNombre);
                clienteAModificar.setApellido(nuevoApellido);
                clienteAModificar.setPasaporte(nuevoPasaporte);
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    //       JSON              |||||||||||||||||||||||||||||||||||||||||||
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    public void cargarJson(String pathname) {
        File file = new File(pathname);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            Gson gson = new GsonBuilder() // El Gson builder para poder cargar el LocalDateTime que rompe el archivo
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();

            gson.toJson(this, Aerolinea.class, bufferedWriter);

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("|X| ERROR CARGANDO AEROLINEA A JSON |X|");
            System.out.println(e.getMessage());
        }

    }

    public static Aerolinea leerJson(String pathname) {
        Aerolinea aerolinea = null;
        File file = new File(pathname);
        if (file.exists()) {
            if (file.canRead()) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                    Gson gson = new GsonBuilder() // El Gson builder para poder leer el LocalDateTime que rompe el archivo
                            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                            .create();

                    aerolinea = gson.fromJson(bufferedReader, Aerolinea.class);

                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("|X| ERROR E/S LEYENDO EL JSON |X|");
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("|X| ERROR LEYENDO EL JSON, NO TIENE PERMISOS |X|");
            }
        } else {
            System.out.println("|X| ERROR LEYENDO EL JSON, NO EXISTE EL ARCHIVO |X|");
        }

        return aerolinea;
    }

    public Cliente buscarUsuario(String username) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombreDeUsuario().equals(username)) {
                return cliente;
            }
        }
        return null; // No se encontró el usuario
    }

    public void verificarNivel (Cliente cliente) {
        if(cliente instanceof Estandar) {
            if(cliente.getMillas() >= 2500) {
                Gold aux = new Gold(cliente);
                clientes.remove(cliente);
                clientes.add(aux);
                System.out.println("Felicitaciones! Ahora sos socio nivel Gold.");
            }
        }
        else if(cliente instanceof Gold) {
            if(cliente.getMillas() >= 5000) {
                Platinum aux = new Platinum(cliente);
                clientes.remove(cliente);
                clientes.add(aux);
                System.out.println("Felicitaciones! Ascendiste al nivel Platinum.");
            }
        }
        else if(cliente instanceof Platinum) {
            if(cliente.getMillas() >= 10000) {
                Black aux = new Black(cliente);
                clientes.remove(cliente);
                clientes.add(aux);
                System.out.println("Felicitaciones! Ascendiste al nivel Black.");
            }
        }
    }
}