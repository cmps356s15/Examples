﻿﻿﻿<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Hifz Tracker</title>
        <link href="css/styles.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="js/script.js"></script>

    </head>

    <body>
        <jsp:include page="header.jsp" />
        <section>
            <h2>Update Task</h2>
            <form method="post" action="updatetask" id="form"> 
                <input type="hidden" name="taskId" value="${task.id}" >
                <label for="sura">Sura</label>
                <!--More info about custom data attributes @ http://www.w3schools.com/tags/att_global_data.asp-->
                <select name="sura" id="sura" onchange="updateMax(this)" required>
                    <option value=""></option>
                    <c:forEach var="surah" items="${requestScope.surahList}">
                        <option value="${surah.id}" data-ayaCount="${surah.ayaCount}"
                                ${surah.id == task.surah.id ? 'selected="selected"' : ''}
                                > ${surah.id}. ${surah.englishName} (${surah.ayaCount} Aya)

                        </option>
                    </c:forEach>
                </select>
                <br />

                <div class="range-selector">
                    <label for="fromAya">From Aya: 
                        <output id="selectedFromAya" for="fromAya">${task.fromAya}</output>
                    </label>
                    <input type="range" id="fromAya" name="fromAya"
                           min="1" max="${task.surah.ayaCount}" value="${task.fromAya}"
                           oninput="selectedFromAya.value = fromAya.value;">
                </div>

                <div class="range-selector">
                    <label for="toAya">To Aya: 
                        <output id="selectedToAya" for="toAya">${task.toAya}</output>
                    </label>
                    <input type="range" id="toAya" name="toAya"
                           min="1" max="${task.surah.ayaCount}" value="${task.toAya}"
                           oninput="selectedToAya.value = toAya.value;" >
                </div>
                <br>

                <label for="dueDate">Due Date</label>
                <input id="dueDate" name="dueDate" type="date" min=""  required value="${task.dueDate}"/>

                <label>Task Type</label>
                <label>
                    <input type="radio" id="memorization" name="type" value="Memorization" 
                           ${task.type.equals("Memorization") ? 'checked="checked"' : ''} required> Memorization
                </label>

                <label>
                    <input type="radio" id="revision" name="type" value="Revision" 
                           ${task.type.equals("Revision") ? 'checked="checked"' : ''} required> Revision
                </label>
                <br />
                <input type="submit" value="Submit" id="submit" >
            </form>
        </section>
    </body>
</html>