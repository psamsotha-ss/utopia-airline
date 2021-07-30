package com.ss.utopia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ss.utopia.console.Consoles;
import com.ss.utopia.db.DatabaseManager;
import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.AirplaneType;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.menu.Menus;
import com.ss.utopia.repository.FlightRepository;
import com.ss.utopia.repository.JdbcFlightRepository;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String... args) throws SQLException, IOException {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        DataSource dataSource = dbManager.getDataSource();
//        FlightRepository repository = new JdbcFlightRepository(dataSource);
//        List<Flight> flights = repository.findAll();
//        for (Flight flight: flights) {
//            System.out.println(flight);
//        }

        Menus.newMainMenu(Consoles.newReaderConsole(), dataSource).run();
    }

    private static Airplane getAirplane(int id, DataSource dataSource) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Airplane airplane = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("SELECT a.id, at.id, at.max_capacity FROM airplane a JOIN airplane_type at ON at.id=a.type_id WHERE a.id=?");
            ps.setInt(1, id);
            logger.debug("Querying: {}", ps);
            rs = ps.executeQuery();
            if (rs.next()) {
                int aId = rs.getInt(1);
                int typeId = rs.getInt(2);
                int typeCapacity = rs.getInt(3);
                AirplaneType type = new AirplaneType(typeId, typeCapacity);
                airplane = new Airplane(aId, type);
                logger.debug("Found result: " + airplane);
            }
        } finally {
            logger.debug("Closing database resources: {} {} {}", conn, ps, rs);
            if (conn != null) conn.close();
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }

        return airplane;
    }
}
