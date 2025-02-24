{
    "Componenti":  [
                       {
                           "Snippet":  "return injectableQuery(validatedLoginCount(login_count), userid);",
                           "Name":  "SqlInjectionLesson5b.java"
                       },
                       {
                           "Snippet":  "int validatedCount = validatedLoginCount(login_count);",
                           "Name":  "SqlInjectionLesson5b.java"
                       },
                       {
                           "Snippet":  "query.setInt(1, validatedCount);",
                           "Name":  "SqlInjectionLesson5b.java"
                       },
                       {
                           "Snippet":  "ResultSet results = query.executeQuery();",
                           "Name":  "SqlInjectionLesson5b.java"
                       },
                       {
                           "Snippet": "private int validatedLoginCount(String loginCount) { \n            try {\n                int count = Integer.parseInt(loginCount);\n                if (count > 0) { //Basic validation: positive integer\n                    return count;\n                } else { \n                    return 0; //Or throw an exception for invalid input\n                }\n            } catch (NumberFormatException e) {\n                return 0; //Or throw an exception for invalid input\n            }
        }",
                           "Name": "SqlInjectionLesson5b.java"
                       }
                   ]
}
