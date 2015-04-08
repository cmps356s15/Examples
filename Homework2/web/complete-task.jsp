<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Hifz Tracker</title>
        <link href="css/styles.css" rel="stylesheet">
    </head>

    <body>
        <jsp:include page="header.jsp" />
        <section>
            <h2>Complete Task</h2>
            <table>
                <thead>
                    <tr>
                        <th>Sura</th>
                        <th>Ayats</th>
                        <th>Due Date</th>
                        <th>Type</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${task.surah.englishName}</td>
                        <td>${task.fromAya} to ${task.toAya}</td>
                        <td>${task.dueDate}</td>
                        <td>${task.type}</td>
                    </tr>
                </tbody>
            </table>
            <br />

            <form method="post" action="completetask">
                <input type="hidden" name="taskId" value="${task.id}" >
                <label for="">Hifz level</label>
                <label>
                    <input type="radio" name="level" value="Excellent"> Excellent
                </label>
                <label>
                    <input type="radio" name="level" value="Ok"> Ok
                </label>
                <label>
                    <input type="radio" name="level" value="Poor" required> Poor
                </label>
                <br />
                <label>Completed date
                    <input id="completedDate" min="${task.dueDate}" name="completedDate" type="date" required />
                </label>
                <script type="text/javascript">
                    var today = new Date();
                    today.setDate(today.getDate());
                    document.getElementById('completedDate').valueAsDate = today;
                </script>
                <label>Comments
                    <textarea id="comment" name="comment"></textarea>
                </label>
                <input type="submit" value="Submit">
            </form>
        </section>
    </body>
</html>
