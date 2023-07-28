<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!doctype html>
<html>
	<head>
		<title>My Spring WebMVC Home</title>
	</head>
	<body>
		<h3>Hello World! This is my first spring boot web mvc application!</h3>
		
		<form>
			<label>UserName:
				<input type="text" name="unm" required />
			</label>
			<button>OK</button>
		</form>
		
		<c:if test="${message ne null }">
			<h4>${message }</h4>
		</c:if>
	</body>
</html>