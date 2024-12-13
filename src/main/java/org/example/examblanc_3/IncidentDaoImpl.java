package org.example.examblanc_3;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public abstract class IncidentDaoImpl implements IncidentDao {

    public boolean inserer(Incident incident){
        DatabaseMetaData DataBaseConnection = null;
        try(Connection connection = DataBaseConnection.getConnection()){
            String query = "insert into incidents values(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,incident.getMemberId());
            preparedStatement.setString(2,incident.getReference());
            preparedStatement.setDate(3,incident.getTime());
            preparedStatement.setString(4,incident.getStatus());

            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public boolean inserer(Set<Incident> incidents){
        DatabaseMetaData DataBaseConnection = null;
        try(Connection connection = DataBaseConnection.getConnection()){
            String query = "insert into incidents values(?,?,?,?)";
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for(Incident incident : incidents){
                preparedStatement.setInt(1,incident.getMemberId());
                preparedStatement.setString(2,incident.getReference());
                preparedStatement.setDate(3,incident.getTime());
                preparedStatement.setString(4,incident.getStatus());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            connection.commit();

            connection.setAutoCommit(true);
        }catch(SQLException e){
            try(Connection connection = DataBaseConnection.getConnection()){
                connection.rollback();
                connection.setAutoCommit(true);
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
