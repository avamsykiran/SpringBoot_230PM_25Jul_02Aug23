<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
	<head>
		<title>ECA - Employees Form</title>
	</head>
	<body>
		<jsp:include page="/header"></jsp:include>
		
		<section style="margin:auto;width:40%;padding:15px;">
			<h4>Employees Form</h4>
			<form:form modelAttribute="emp" method="POST">
				<div>
					<form:label path="empId">Employee Id</form:label>
					<form:input path="empId" readonly="true"/>
				</div>
				<div>
					<form:label path="fullName">Employee Name</form:label>
					<form:input path="fullName" />
					<form:errors path="fullName"></form:errors>
				</div>
				<div>
					<form:label path="salary">Salary</form:label>
					<form:input path="salary" type="number"/>
					<form:errors path="salary"></form:errors>
				</div>
				<div>
					<form:label path="joinDate">Date Of Joining</form:label>
					<form:input path="joinDate" type="date"/>
					<form:errors path="joinDate"></form:errors>
				</div>
				<div>
					<form:checkbox path="isContract"/>
					<form:label path="isContract">Is Contract</form:label>
					<form:errors path="isContract"></form:errors>
				</div>
				<button>Save</button>
			</form:form>
		</section> 
	</body>
</html>