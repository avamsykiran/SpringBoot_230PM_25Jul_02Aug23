<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
	<head>
		<title>ECA - Employees List</title>
	</head>
	<body>
		<jsp:include page="/header"></jsp:include>
		
		<section style="margin:auto;width:90%;padding:15px;">
			<h4>Employees List</h4>
			
			<c:choose>
				<c:when test="${empsList eq null }">
					<p><strong>Data could not be retrieved</strong></p>
				</c:when>
				<c:when test="${empsList.isEmpty() }">
					<p><strong>No records found the database.</strong></p>
				</c:when>
				<c:otherwise>
					<table style="width:100%; border: 1px solid black;">
						<thead>
							<tr>
								<th>Emp#</th>
								<th>Full Name</th>
								<th>Salary</th>
								<th>Join Date</th>
								<th>Type</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="e" items="${empsList }">
								<tr style="text-align:center">
									<td>${e.empId}</td>
									<td>${e.fullName}</td>
									<td>${e.salary}</td>
									<td>${e.joinDate}</td>
									<td>${e.isContract?"Contract Employee":"Regular Employee"}</td>
									<td>
										<a href="/emps/edit?empId=${e.empId }">EDIT</a> <span> | </span>
										<a href="/emps/del?empId=${e.empId }">DELETE</a> 
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</section> 
	</body>
</html>