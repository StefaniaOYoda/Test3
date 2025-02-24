{
    "Componenti":  [
                       {
                           "Snippet":  "return injectableQuery(validateLoginCount(login_count), userid);",
                           "Name":  "SqlInjectionLesson5b.java"
                       },
                       {
                           "Snippet":  "//Validation Function",
                           "Name":  "SqlInjectionLesson5b.java"
                       },
                       {
                           "Snippet":  "private int validateLoginCount(String loginCount) {
                                try {
                                    int count = Integer.parseInt(loginCount);
                                    if (count > 0) { //Example validation:  Check if it's a positive integer
                                        return count;
                                    } else {
                                        return 0; // Or throw an exception for invalid input
                                    }
                                } catch (NumberFormatException e) {
                                    return 0; // Or throw an exception for invalid input
                                }
                            }",
                           "Name":  "SqlInjectionLesson5b.java"
                       },
                       {
                           "Snippet":  "query.setInt(1, count);",
                           "Name":  "SqlInjectionLesson5b.java"
                       },
                       {
                           "Snippet":  "ResultSet results = query.executeQuery();",
                           "Name":  "SqlInjectionLesson5b.java"
                       }
                   ]
}
