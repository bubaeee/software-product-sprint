// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.util.ArrayList;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet( "/data")
public class DataServlet extends HttpServlet {

    ArrayList<String> comments = new ArrayList<>();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<String> returning = new ArrayList<>();
    returning.add("Bounty");
    returning.add(" Twix");
    returning.add(" Snickers");
    returning.add(" Maltesers");
    returning.add(" In that order");
    
    //response.setContentType("text/html;");
    //response.getWriter().println("Hello Bubae!");
    
    Gson gson = new Gson();
    String json = gson.toJson(returning);
    
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String text = getParameter(request, "comment-input", "");

    comments.add(text);

    // Respond with the result.
    response.setContentType("text/html;");
    response.getWriter().println(text);

    System.out.println("comments added");
    for (int i = 0; i < comments.size();i++) { 		      
	    System.out.println(comments.get(i)); 		
	}
    //redirect back to portfolio
    response.sendRedirect("/index.html");
  }

  private String getParameter(HttpServletRequest request, String comment, String defaultValue) {
    String value = request.getParameter(comment);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}
