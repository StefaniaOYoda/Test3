{
    "Componenti": [
        {
            "Snippet": "try (Statement statement = connection.createStatement(); ResultSet results = statement.executeQuery(query);) {
                // Process the results
                while (results.next()) {
                    // ... your code to handle each row ...
                }
            } catch (SQLException e) {
                // Handle the SQLException appropriately (e.g., log it, throw a custom exception)
                e.printStackTrace(); // Replace with proper error handling
            }",
            "Name": "SqlInjectionLesson6a.java"
        }
    ]
}
