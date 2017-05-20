<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/sign/common_style.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/sign/signup_style.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>
	<sf:form class="form-signin img-rounded" action="${pageContext.request.contextPath}/signup" method="post" commandName="userRegistrationForm">

		<h1 class="form-signin-heading" style="text-align: center">JOIN</h1>
		<br>

		<div class="form-group">
			
			<sf:input id="userid" path="username" class="form-control idsize" placeholder="아이디" /> 
			<label for="userid" title="ID"></label> 
			<sf:errors class="idfont" style="color:red;" path="username" />	
						
			<sf:password path="password" id="password" class="form-control pwsize" placeholder="비밀번호" />
			<label for="password" title="PASSWORD"></label>
			
			<sf:password path="confirmPassword" id="confirmPassword" class="form-control pwsize" placeholder="비밀번호 재확인" />
			<label for="confirmPassword"title="PASSWORD-CHECK"></label> 
			
			<sf:input path="lastName" placeholder="Last Name" class="form-control idsize"/>
			<sf:errors path="lastName" />
	
			<sf:input path="firstName" placeholder="First Name" class="form-control idsize"/>
			<sf:errors path="firstName" />
			<hr>
			
			<label id="rightt"><input size="12" id="man" type="radio" name="subject"> 남성</label>
			
			<label id="rightt"><input id="woman" type="radio" name="subject"> 여성</label>
				
				<p class="tffsize">생년월일</p>
				
			<table class="tmargin tfsize">
			<tr>
					
					
					<td>
						<select style="height:40px;" class="form-control select90" name='date_20'>
							  <option>1915</option><option>1916</option><option>1917</option><option>1918</option><option>1919</option><option>1920</option><option>1921</option><option>1922</option><option>1923</option><option>1924</option><option>1925</option><option>1926</option><option>1927</option><option>1928</option><option>1929</option><option>1930</option><option>1931</option><option>1932</option><option>1933</option><option>1934</option><option>1935</option><option>1936</option><option>1937</option><option>1938</option><option>1939</option><option>1940</option><option>1941</option><option>1942</option><option>1943</option><option>1944</option><option>1945</option><option>1946</option><option>1947</option><option>1948</option><option>1949</option><option>1950</option><option>1951</option><option>1952</option><option>1953</option><option>1954</option><option>1955</option><option>1956</option><option>1957</option><option>1958</option><option>1959</option><option>1960</option><option>1961</option><option>1962</option><option>1963</option><option>1964</option><option>1965</option><option>1966</option><option>1967</option><option>1968</option><option>1969</option><option>1970</option><option>1971</option><option>1972</option><option>1973</option><option>1974</option><option>1975</option><option>1976</option><option>1977</option><option>1978</option><option>1979</option><option>1980</option><option>1981</option><option>1982</option><option>1983</option><option selected>1984</option><option>1985</option><option>1986</option><option>1987</option><option>1988</option><option>1989</option><option>1990</option><option>1991</option><option>1992</option><option>1993</option><option>1994</option><option>1995</option><option>1996</option><option>1997</option><option>1998</option><option>1999</option><option>2000</option><option>2001</option><option>2002</option><option>2003</option><option>2004</option><option>2005</option><option>2006</option><option>2007</option><option>2008</option><option>2009</option><option>2010</option><option>2011</option><option>2012</option><option>2013</option><option>2014</option>
						</select>
					</td><td>년</td><td>
						<select style="height:40px;" class='form-control select80' name='date_30'>
							  <option selected>01</option><option>02</option><option>03</option><option>04</option><option>05</option><option>06</option><option>07</option><option>08</option><option>09</option><option>10</option><option>11</option><option>12</option>
						</select>
					</td><td>월</td><td>
						<select style="height:40px;" class='form-control select80' name='date_40'>
							  <option selected>01</option><option>02</option><option>03</option><option>04</option><option>05</option><option>06</option><option>07</option><option>08</option><option>09</option><option>10</option><option>11</option><option>12</option><option>13</option><option>14</option><option>15</option><option>16</option><option>17</option><option>18</option><option>19</option><option>20</option><option>21</option><option>22</option><option>23</option><option>24</option><option>25</option><option>26</option><option>27</option><option>28</option><option>29</option><option>30</option><option>31</option>
						</select>
					</td><td>일</td>
				</tr>
			
	</table>
	<hr>
	<p class="pcolor">관심있는 여행지 분야</p>
		<label class="labelmargin1030"><input type="checkbox" name="1">자연</label>
		<label class="labelmargin10"><input type="checkbox" name="2">역사</label>
		<label class="labelmargin10"><input type="checkbox" name="3">휴양</label>
		<label class="labelmargin10"><input type="checkbox" name="4">체험</label>
		<label class="labelmargin10"><input type="checkbox" name="5">산업</label>
		<label class="labelmargin10"><input type="checkbox" name="6">건축/조형물</label>
		<label class="labelmargin1030"><input type="checkbox" name="7">문화시설</label>
		<label class="labelmargin10"><input type="checkbox" name="8">레포츠-육상</label>
		<label class="labelmargin10"><input type="checkbox" name="9">레포츠-수상</label>
		<label class="labelmargin10"><input type="checkbox" name="10">레포츠-항공</label>
		
	<hr>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${ _csrf.token}" />
			<button class="btn btn-lg btn-primary btn-block buttonsize" type="submit">작성
				완료</button>

		</div>
	</sf:form>
<h2 class="sign-title"><a href="<c:url value="/"/>">Tour Of All</a></h2>
<div class="sign-form-container">

<sf:form action="${pageContext.request.contextPath}/signup" method="post" commandName="userRegistrationForm">
  <div class="container">
    <c:if test="${userRegistrationForm.signInProvider == null}">
    	<h3>일반 가입자</h3>
  	 	<sf:input path="username" placeholder="New ID" class="input"/>
   		<sf:errors path="username" />
   	
   		<sf:password path="password"  placeholder="New Password"/>    
   		<sf:errors path="password" />
   	
   		<sf:password path="confirmPassword" placeholder="Repeat Password"/>
		<sf:errors path="confirmPassword" />
	
		<sf:input path="lastName" placeholder="Last Name" class="input name"/>
		<sf:errors path="lastName" />
	
		<sf:input path="firstName" placeholder="First Name" class="input name"/>
		<sf:errors path="firstName" />

    	<div class="check-container">
        	<label style="display:block;"><b>성별</b></label>
        	<span><sf:radiobutton path="gender" value="0"/>남성</span>
        	<span><sf:radiobutton path="gender" value="1"/>여성</span>
    	</div>
    </c:if>
    <c:if test="${userRegistrationForm.signInProvider == 'facebook'}">
    	<h3>페이스북 가입자</h3>
    </c:if>
    <c:if test="${userRegistrationForm.signInProvider == 'twitter'}">
    	<h3>트위터 가입자</h3>
    </c:if>
    <c:if test="${userRegistrationForm.signInProvider != null}">
    	<sf:hidden path="username"/>
    	<sf:hidden path="password"/>
    	<sf:hidden path="confirmPassword"/>
    	<sf:hidden path="lastName"/>
    	<sf:hidden path="firstName"/>
    	<sf:hidden path="gender"/>
    	<sf:hidden path="signInProvider"/>
    </c:if>
    <div class="check-container">
        <label style="display:block;"><b>생일</b></label>   
        <sf:select path="year">
        	<c:forEach var="year" items="${years}">
        		<sf:option value="${year}" />
        	</c:forEach>
        </sf:select>년 
        		
        <sf:select path="month">
        	<c:forEach var="month" items="${months}">
        		<sf:option value="${month}" />
        	</c:forEach>
        </sf:select>월
        		
        <sf:select path="date">
        	<c:forEach var="date" items="${dates}">
        		<sf:option value="${date}" />
        	</c:forEach>
        </sf:select>일
        		
    </div>
  </div>

    <div class="clearfix">
      <button type="button" class="cancelbtn">Cancel</button>
      <button type="submit" class="signupbtn">Sign Up</button>
    </div>
  
</sf:form>
</div>
</body>
</html>