<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!doctype html>
<html>
	<head>
		<title>My Spring WebMVC Loan</title>
	</head>
	<body>
		<h3>Loan Page</h3>
		
		<form:form method="POST" modelAttribute="loan">
			<div>
				<form:label path="principle">Principle Amount</form:label>
				<form:input path="principle" type="number"/>
			</div>
			<div>
				<form:label path="terms">Term(s)</form:label>
				<form:input path="terms" type="number"/>
			</div>
			<div>
				<form:label path="interestRate">Rate Of Interest</form:label>
				<form:input path="interestRate"/>
			</div>
			<button>Compute</button>
		</form:form>
		
		<c:if test="${si ne null }">
			<p> The interet to be paid is <strong>${si}</strong></p> 
		</c:if>
	</body>
</html>