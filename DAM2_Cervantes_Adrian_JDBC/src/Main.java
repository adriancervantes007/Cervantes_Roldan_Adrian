
import beans.Agencia;
import beans.Satelite;
import DAO.SateliteDAOImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SateliteDAOImpl sateliteDAO = new SateliteDAOImpl();

        try {
            Agencia nasa = new Agencia();
            nasa.setId(1);

            Satelite nuevo = new Satelite(
                    0,
                    "AdrianSat-1",
                    "LEO",
                    new BigDecimal("850.50"),
                    new BigDecimal("12000000.00"),
                    true,
                    LocalDate.of(2026, 5, 28),
                    nasa,
                    null,
                    "ADRIAN_CERVANTES_DAM2"
            );

            System.out.println("TEST 1 - ADD SATELLITE");
            int newId = sateliteDAO.add(nuevo);
            System.out.println("Satelite insertado con id: " + newId);

            System.out.println("TEST 2 - UPDATE SATELLITE");
            nuevo.setCoste(new BigDecimal("12500000.00"));
            nuevo.setNombre("AdrianSat-1B");
            System.out.println("Filas actualizadas: " + sateliteDAO.update(nuevo));

            System.out.println("TEST 3 - FIND SATELLITE");
            System.out.println(sateliteDAO.find(newId));

            System.out.println("TEST 4 - FIND ALL SATELLITES");
            List<Satelite> todos = sateliteDAO.findAll();
            todos.forEach(System.out::println);

            System.out.println("TEST 5 - FIND SATELITES BY AGENCIA");
            sateliteDAO.findByAgencia(1).forEach(System.out::println);

            System.out.println("TEST 6 - FIND SATELLITE WITH DETAIL");
            System.out.println(sateliteDAO.findWithDetail(1));

            System.out.println("TEST BONUS_QUERY_ADVANCED");
            sateliteDAO.findActiveWithAgencyAndDetail().forEach(System.out::println);

            System.out.println("TEST DYNAMIC_UPDATE_ENGINE");
            Map<String, Object> fields = new LinkedHashMap<>();
            fields.put("nombre", "AdrianSat-Dynamic");
            fields.put("coste", new BigDecimal("13000000.00"));
            fields.put("activo", true);
            System.out.println("Filas actualizadas dinamicamente: " + sateliteDAO.updateDynamic(newId, fields));
        } catch (SQLException e) {
            System.err.println("Error JDBC: " + e.getMessage());
        }
    }
}
