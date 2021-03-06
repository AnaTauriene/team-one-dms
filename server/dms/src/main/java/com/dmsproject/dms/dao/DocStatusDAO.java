package com.dmsproject.dms.dao;

import com.dmsproject.dms.Database;
import com.dmsproject.dms.dto.DocStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;


@Component
public class DocStatusDAO {

    @Autowired
    private Database database;

    public void addDocStatus(DocStatus docStatus) {
         String query = "INSERT INTO document_status" +
                "(status_id, document_id, user_id, doc_status_descr) " +
                "values (?, ?, ?, ?)";
        try {
            PreparedStatement statement = database.connection.prepareStatement(query);
            statement.setInt(1, docStatus.getStatusId());
            statement.setInt( 2, docStatus.getDocId());
            statement.setInt( 3, docStatus.getUserId());
            statement.setString(4, docStatus.getDescription());

            statement.executeUpdate();
            statement.close();
        } catch (java.sql.SQLException e) {
            System.out.println("Error adding to database");
            System.out.println(e);
        }
    }
}
