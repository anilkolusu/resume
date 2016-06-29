<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="home-body-mid">
	<div class="status-page">
		<table class="status-report" id="display-data-status">
			<tr class="bgcolor-for-status">
				<td>
					<div class="history-compare-title-place-profile">
						<div class="history-compare-no-place">
							<div class="user tmp">SNO</div>
						</div>
						<div class="history-compare-data-place">
							<strong>DATE</strong>
						</div>
						<div class="history-compare-from-to-title-data">
							<strong>ORIGIN AND DESTINATION</strong>
						</div>
						<div class="history-compare-dis-dur-cost-title-place-profile">
							<div class="history-data-submit">
								<strong></strong>
							</div>
						</div>
					</div>
				</td>
			</tr>
			<c:choose>
				<c:when test="${historydetails ne null}">
					<c:forEach items="${historydetails}" var="historydetail">
						<tr class="bgcolor-for-status">
							<td>
								<div class="history-compare-title-place-profile">
									<div class="history-compare-no-place">
										<div class="user tmp"><p class="sub-title">${historydetail.sno}</p></div>
									</div>
									<div class="history-compare-data-place">
										<p class="sub-title">${historydetail.date}</p>
									</div>
									<div class="history-compare-from-to-title-data">
										<p class="sub-title">${historydetail.origin} TO ${historydetail.destin}</p>
									</div>
									<div class="history-compare-dis-dur-cost-title-place-profile">
										<form action="comparedata.htm" method="post">
										<div class="history-data-submit">
											<input type="hidden" name="hid" value="${historydetail.historyid}"/>
											<input type="submit" value=" Compare "/>
										</div>
										</form>
									</div>
								</div>
							</td>
						</tr>
    				</c:forEach>
				</c:when>
				<c:otherwise>
					<p style="color:red;">No Records Found..</p>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
</div>