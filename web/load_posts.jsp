<%@page import="java.util.List"%>
<%@page import="entities.Post"%>
<%@page import="dao.PostDao"%>
<%@page import="dao.PostDao"%>
<%@page import="helper.ConnectionProvider"%>
<%@page import="entities.user"%>
<div class="row">

    <%
        
        user uuu=(user)session.getAttribute("currentUser");
        
        Thread.sleep(1000);
        PostDao d = new PostDao(ConnectionProvider.getConnection());
        
        int cid = Integer.parseInt(request.getParameter("cid"));
        List<Post> posts = null;
        if (cid == 0) {
            posts = d.getAllPosts();
        } else {
            posts = d.getPostByCatId(cid);
        }
        
        if (posts.size() == 0) {
            out.println("<h3 class='display-3 text-center'>No Posts in this category..</h3>");
            return;
        }
        
        for (Post p : posts) {
    %>

    <div class="col-md-6 mt-2">
        <div class="card">
            <img class="card-img-top" src="blog_pics/<%= p.getpPic()%>" alt="Card image cap">
            <div class="card-body">
                <b><%= p.getpTitle()%></b>
                <%
                    String str = p.getpContent();
                    String less;
                    if(str.length()>500){
                        less=str.substring(0,500);
                 %>
                        <p><%= less %></p>
                  <%  
                      }else{
                    %>    
                          <p><%= str %></p>
                          <%
                            }
//                    String less=str.substring(0,(str.length()/4));
                    %>
                 


            </div>
            <div class="card-footer primary-background text-center">
                

                
                <a href="show_blog_page.jsp?post_id=<%= p.getPid()%>" class="btn btn-outline-light btn-sm">Read More...</a>
                <a href="#!" class="btn btn-outline-light btn-sm"> <i class="fa fa-commenting-o"></i> <span>20</span>  </a>
            </div>

        </div>


    </div>


    <%
        }
        

    %>

</div>
