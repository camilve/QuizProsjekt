/**
 * Created by Camilla Velvin on 22.09.2017.
 */
$(document).ready(function () {
    var finalQuizes = 0;
    $.get("rest/quiz/previous", function (result) {
        finalQuizes = result.length;
        for(var i=0; i<result.length; i++) {
            var id = result[i].id;
            $("#scoreboardList").append(`
                <tr>
                    <td id="scoreboardList-${i}" onclick="findScoreboard('${id}')">${result[i].quiz.quizName}</td>
                </tr>`
            );
        }
    });


});
function findScoreboard(quizId) {
    $.get("rest/quiz/scoreboard/"+quizId, function (result) {
        $("#scoreboard").html("");
        $("#scoreboard").append(`<tr>
                            <th>Nickname</th>
                            <th>Points</th>
                        </tr>`);
        for(var i=0; i<result.length; i++) {
            $("#scoreboard").append(`
               <tr>
                <td>${result[i].nickname}</td>
                <td>${result[i].points}</td>
               </tr>
                `)
        }
    });
}