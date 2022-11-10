<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    
 <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Emploi du Temps</title>
</head>
<body>


    <div class="fenetre">
        <h1>Emploi du temps</h1>
        <br>       
        <!--**********************Tableau emploi du temps*********************-->
        <table border="1" class="green_background">
		     <c:forEach var="row" items="${emploiDuTemps}" varStatus ="rownum">		
		        <c:choose>
		            <c:when test="${rownum.index == 0}">
		             	<thead>
			                <tr>
				        		<c:forEach items="${row }" var="col">
					        		<th class="white_background">
					        			<c:out value="${col }" />
					        		</th>
				        		</c:forEach>	
				        	</tr>
			        	</thead>
		            </c:when>
		            <c:otherwise>
		                <tr>
			        		<c:forEach items="${row }" var="col">
				        		<c:choose>
			        				<c:when test="${col =='JAVA' }">
			        					<td class="java">
				        					<c:out value="${col }" />
				        				</td>
			        				</c:when>
			        				<c:when test="${col =='HTML' }">
			        					<td class="html">
				        					<c:out value="${col }" />
				        				</td>
			        				</c:when>
			        				<c:when test="${col =='CSS' }">
			        					<td class="css">
				        					<c:out value="${col }" />
				        				</td>
			        				</c:when>
			        				<c:when test="${col =='POO' }">
			        					<td class="poo">
				        					<c:out value="${col }" />
				        				</td>
			        				</c:when>
			        				<c:when test="${col =='HIBERNATE' }">
			        					<td class="hibernate">
				        					<c:out value="${col }" />
				        				</td>
			        				</c:when>
			        				<c:when test="${col =='ALGORITHMIQUE' }">
			        					<td class="algorithmique">
				        					<c:out value="${col }" />
				        				</td>
			        				</c:when>
			        				<c:when test="${col =='REPOS' }">
			        					<td class="repos">
				        					<c:out value="${col }" />
				        				</td>
			        				</c:when>
			        				<c:otherwise>
				        				<td>
						        			<c:out value="${col }" />
						        		</td>
			        				</c:otherwise>
			        			</c:choose>

			        		</c:forEach>	
			        	</tr>
		            </c:otherwise>
		        </c:choose>		
		    </c:forEach>
        </table>
        <br>
       <!--***********************Bouton de reinitialisation**********************-->
       <form class="btn_reinit" action="jourhoraireservlet" method="get" >
       		<div class="btn_reinit">    
	      		<input type="submit" value="Reinitialiser" name="reinit" id="reinit">
	   		</div>
       </form>
      
        <br>
        <!--***************Form pour ajouter une matiere à l'emploi du temps************-->
        <form action="jourhoraireservlet" method="post" >
            <fieldset id="fieldset">
	            <legend id="legend">Entrer un creneau a ajouter</legend>
	            <div class="box">
	                <!--****************Selection des jours de la semaines*****************-->
	                <select name="jours" id="jours">
	                    <option value="">> > > > Choisir un jour < < < < </option>
	                    <option value="1">Lundi</option>
	                    <option value="2">Mardi</option>
	                    <option value="3">Mercredi</option>
	                    <option value="4">Jeudi</option>
	                    <option value="5">Vendredi</option>
	                </select>
	             	 <c:if test="${ empty selectJours }">
	               		<div class="error"><c:out value="${ messageErreur }"/></div>
	               	</c:if>
	            </div>
	        	
	            <div class="box">
	                <!--****************Selection de l'heure du debut***********************-->
	                <select name="heureDebut" id="heureDebut">
	                	<option value="">> > > Choisir heure debut < < < </option>
	                	<c:forEach items= "${listHeureDebut }" var="heure" varStatus="loop"  >
							<option value="${ loop.index + 1}"><c:out value="${ heure }"/></option>
						</c:forEach>
	                </select>
	                <c:if test="${ empty heureDebut }">
	                	<div class="error"><c:out value="${ messageErreur }"/></div>
	                </c:if>
	                <div class="error"><c:out value="${ messageErreurChampsHeureDebut }"/></div>
	            </div>
	
	            <div class="box">
	                <!--********************Selection de l'heure de fin*****************************-->
	                <select name="heureFin" id="heureFin">
	                	<option value="">> > > > Choisir heure fin  < < < < </option>
	                	<c:forEach items= "${listHeureFin }" var="heure" varStatus="loop" >
							<option value="${ loop.index + 2 }"><c:out value="${ heure }"/></option>
						</c:forEach>
	                </select>
	                 <c:if test="${ empty heureFin }">
	                	<div class="error"><c:out value="${ messageErreur }"/></div>
	        		</c:if>
	        		<div class="error"><c:out value="${  messageErreurChampsHeureFin }"/></div>
	               
	            </div>
				
	          	<div class="box">
                    <!--***********************Selection de la matiere********************************-->
                    <select name="matiere" id="matiere">
                        <option value=""> > > > Choisir une matière < < < </option>
                        <option class="java" value="JAVA">Java</option>
                        <option class="html" value="HTML">HTML</option>
                        <option class="css" value="CSS">CSS</option>
                        <option class="poo" value="POO">POO</option>
                        <option class="hibernate" value="HIBERNATE">Hibernate</option>
                        <option class="algorithmique" value="ALGORITHMIQUE">Algorithmique</option>
                        <option class="repos" value="REPOS">Repos</option>
                    </select>
                       <c:if test="${empty matiere}">
                           <div class="error"><c:out value="${ messageErreur }"/></div>
                       </c:if> 
                </div>
				
	            <div class="btn_ajouter">
	                <!--***********************Bouton de validation*************************-->
	                <input type="submit" value="Ajouter" name="ajouter">
	            </div>
	            <div class="error"><c:out value="${ messageErreurException }"/></div>
	    </fieldset>
	   </form>
    </div>
</body>
</html>