<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Hifz Tracker</title>
        <link href="css/styles.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="js/add-update-task.js"></script>
    </head>

    <body>
        <jsp:include page="header.jsp" />

        <section>
            <h2>Add Task</h2>
            <form method="post" action="addtask" id="addForm">

                <label for="sura">Sura</label>
                <!--More info about custom data attributes @ http://www.w3schools.com/tags/att_global_data.asp-->
                <select name="sura" id="sura" required>
                    <option value=""></option>
                    <c:forEach var="surah" items="${requestScope.surahList}">
                        <option value="${surah.id}" data-ayaCount="${surah.ayaCount}">
                            ${surah.id}. ${surah.englishName} (${surah.ayaCount} Aya)
                        </option>
                    </c:forEach>
                </select>
                <br />

                <div class="range-selector">
                    <label for="fromAya">From Aya: 
                        <output id="selectedFromAya" for="fromAya">1</output>
                    </label>
                    1 <input type="range" id="fromAya" name="fromAya"
                           min="1" max="1" value="1"
                           oninput="selectedFromAya.value = fromAya.value;">
                    <span id="fromMaxAya"></span>
                </div>

                <div class="range-selector">
                    <label for="toAya">To Aya: 
                        <output id="selectedToAya" for="toAya">1</output>
                    </label>
                    1 <input type="range" id="toAya" name="toAya"
                           min="1" max="1" value="1"
                           oninput="selectedToAya.value = toAya.value;">
                    <span id="toMaxAya"></span>
                </div>
                <br>

                <label for="dueDate">Due Date</label>
                <input id="dueDate" name="dueDate" type="date" required />

                <label>Task Type</label>
                <label>
                    <input type="radio" id="memorization" name="type" value="Memorization" required> Memorization
                </label>

                <label>
                    <input type="radio" id="revision" name="type" value="Revision"> Revision
                </label>
                <br />
                <input type="submit" value="Submit" id="submit" >

            </form>
        </section>

    </body>
</html>
