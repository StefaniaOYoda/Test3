{
    "Componenti":  [
                       {
                           "Snippet":  "return injectableQuery(userid);",
                           "Name":  "SqlInjectionLesson5b.java"
                       },
                       {
                           "Snippet":  "// Input validation (Example - needs to be tailored to specific needs)\n                           // if (!isValidLoginCount(userid)) { throw new IllegalArgumentException("Invalid userid"); }
                           ",
                           "Name":  "SqlInjectionLesson5b.java"
                       },
                       {
                           "Snippet":  "// Parameterized query -  'count' is now handled internally\n                           query.setInt(1, userid);",
                           "Name":  "SqlInjectionLesson5b.java"
                       },
                       {
                           "Snippet":  "ResultSet results = query.executeQuery();",
                           "Name":  "SqlInjectionLesson5b.java"
                       }
                   ]
}
