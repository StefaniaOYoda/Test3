/*
 * This file is part of WebGoat, an Open Web Application Security Project utility. For details, please see http://www.owasp.org/
 *
 * Copyright (c) 2002 - 2019 Bruce Mayhew
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * Getting Source ==============
 *
 * Source for this application is maintained at https://github.com/WebGoat/WebGoat, a repository for free software projects.
 */

package org.owasp.webgoat.lessons.sqlinjection.introduction;

import static org.owasp.webgoat.container.assignments.AttackResultBuilder.failed;
import static org.owasp.webgoat.container.assignments.AttackResultBuilder.success;

import java.io.IOException;
import java.sql.*;
import org.owasp.webgoat.container.LessonDataSource;
import org.owasp.webgoat.container.assignments.AssignmentEndpoint;
import org.owasp.webgoat.container.assignments.AssignmentHints;
import org.owasp.webgoat.container.assignments.AttackResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AssignmentHints(
    value = {
      "SqlStringInjectionHint5b1",
      "SqlStringInjectionHint5b2",
      "SqlStringInjectionHint5b3",
      "SqlStringInjectionHint5b4"
    })
public class SqlInjectionLesson5b implements AssignmentEndpoint {

  private final LessonDataSource dataSource;

  public SqlInjectionLesson5b(LessonDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @PostMapping("/SqlInjection/assignment5b")
  @ResponseBody
  public AttackResult completed(@RequestParam String userid, @RequestParam String login_count)
      throws IOException {
    return injectableQuery(login_count, userid);
  }

  protected AttackResult injectableQuery(String login_count, String accountName) {
    String queryString = "SELECT * From user_data WHERE Login_Count = ? and userid = ?"; // Parameterized query
    try (Connection connection = dataSource.getConnection();
         PreparedStatement query = connection.prepareStatement(
            queryString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

      int count = 0;
      try {
        count = Integer.parseInt(login_count);
      } catch (NumberFormatException e) { // More specific exception
        return failed(this)
            .output("Invalid input: '" + login_count + "' is not a valid number.")
            .build();
      }

      query.setInt(1, count); // Set parameter 1
      query.setString(2, accountName); // Set parameter 2

      try {
        ResultSet results = query.executeQuery();

        if (results != null && results.first()) {
          ResultSetMetaData resultsMetaData = results.getMetaData();
          StringBuilder output = new StringBuilder();
          output.append(SqlInjectionLesson5a.writeTable(results, resultsMetaData));
          results.last();

          if (results.getRow() >= 6) {
            return success(this)
                .feedback("sql-injection.5b.success")
                .output("Query successful.")
                .feedbackArgs(output.toString())
                .build();
          } else {
            return failed(this)
                .output(output.toString() + "\nQuery returned fewer than 6 rows.")
                .build();
          }
        } else {
          return failed(this)
              .feedback("sql-injection.5b.no.results")
              .output("Query returned no results.")
              .build();
        }
      } catch (SQLException sqle) {
        return failed(this)
            .output("SQL Error: " + sqle.getMessage()) // More concise error message
            .build();
      }
    } catch (Exception e) {
      return failed(this)
          .output("An unexpected error occurred: " + e.getMessage()) // More user friendly message
          .build();
    }
  }
}

