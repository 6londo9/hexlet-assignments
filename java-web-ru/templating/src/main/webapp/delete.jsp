<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Delete user</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
        	rel="stylesheet"
        	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
        	crossorigin="anonymous">
    </head>
    <body>
    	<h4>Do you really want to delete user: <c:out value='${user.get("firstName")} ${user.get("lastName")}?'/></h4>
    	<form method="post" action="/users/delete">
    		<button type="submit" class="btn btn-danger" name="id" value='${user.get("id")}'>Delete</button>
    	</form>
    </body>
</html>
<!-- END -->
