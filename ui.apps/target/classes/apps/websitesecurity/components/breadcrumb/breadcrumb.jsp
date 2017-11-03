<%@page session="false"%><%--
Copyright 1997-2008 Day Management AG
Barfuesserplatz 6, 4001 Basel, Switzerland
All Rights Reserved.

This software is the confidential and proprietary information of
Day Management AG, ("Confidential Information"). You shall not
disclose such Confidential Information and shall use it only in
accordance with the terms of the license agreement you entered into
with Day.

==============================================================================

Breadcrumb component

Draws the breadcrumb

--%><%@include file="/libs/foundation/global.jsp"%><%
%>
<%@ page session="false"
import="org.apache.sling.jcr.api.SlingRepository,
javax.jcr.Session,
java.util.*,
javax.jcr.Node" %><%
%><%
String pageTitle = currentPage.getTitle();
if(!pageTitle.equals("Homepage") ){
%>
<div class="container-fluid bg-color-grey">
<div class="container">
<div class="breadCrumbSec">
<%
// get starting point of trail
long level = currentStyle.get("absParent", 3L);
long endLevel = currentStyle.get("relParent", 0L);
String delimStr = currentStyle.get("delim", "&nbsp;&gt;&nbsp;");
String trailStr = currentStyle.get("trail", "");
int currentLevel = currentPage.getDepth(); 
String delim = "";

int aa = currentLevel - (int)endLevel;
while (level < currentLevel - endLevel) {             
Page trail = currentPage.getAbsoluteParent((int) level);

if (trail == null) {
break;
}
String title = trail.getNavigationTitle();
if (title == null || title.equals("")) {
title = trail.getNavigationTitle();

}
if (title == null || title.equals("")) {   
title = trail.getTitle();

}
if (title == null || title.equals("")) {
title = trail.getName();

}
%><%= xssAPI.filterHTML(delim) %>

<a class="breadcrumb-node" href="<%= xssAPI.getValidHref(trail.getPath()+".html") %>"
onclick="CQ_Analytics.record({event:'followBreadcrumb',values: { breadcrumbPath: '<%= xssAPI.getValidHref(trail.getPath()) %>' },collect: false,options: { obj: this },componentPath: '<%=resource.getResourceType()%>'})"><%
%><%

if (trail.getTitle().equals("us") )  {

if(!pageTitle.equals("us") ){
%>
<a class="homeicon" href="/content/websitesecurity/en/us/homepage.html"><img src="${properties.path}"/></a>
<%}}else{%>

<%=xssAPI.encodeForHTML(title)%>
<%}%>
</a>
<%
delim = delimStr;
level++;

}

if (trailStr.length() > 0) {
%><%= xssAPI.filterHTML(trailStr) %><%
}

%>
</div>
</div>
</div>    
<%}%>
