/**
 * Created by Camilla Velvin on 20.09.2017.
 */
var quizId = 0;
var scoreboardInterval;
$(document).ready(function () {
    var quizFuture = 0;
    function getFutureQuizs() {
        $.get("rest/quiz/futureQuizes", function(result) {
            if(result != null) {
                var quizName;
                var startTime;

                $("#futureQuiz").html("");
                $("#futureQuiz").append(`
                    <tr>
                        <th>Quizname</th>
                        <th>Start time</th>
                        <th></th>
                    </tr>`);

                quizFuture = result.length;
                for (var i = 0; i < result.length; i++) {
                    quizName = result[i].quiz.quizName;
                    startTime = result[i].quiz.tid;
                    quizId = result[i].id;


                    $("#futureQuiz").append(`<tr class="clickable-row${i}" data-hfref="CreateQuiz.html">
                        <td>${quizName}</td>
                        <td>${startTime}</td>
                        <td><a onclick="joinNickname('${quizId}')" href="#" id="joinQuizNickname">Join</a></td>
                    </tr>
                    `);
                }
            }
        });
    }
    getFutureQuizs();
    var interval = setInterval(getFutureQuizs, 10000);

    getOngoingQuizzes();
    var interval2 = setInterval(getOngoingQuizzes, 1000);

    function getOngoingQuizzes() {
        $.get("rest/quiz/ongoing", function (result) {
            if(result.length > 0) {
                var quizName;

                $("#ongoingQuizzes").html("");
                $("#ongoingQuizzes").append(`
                    <tr>
                        <th>Quizname</th>
                    </tr>`);

                quizFuture = result.length;
                for (var i = 0; i < result.length; i++) {
                    quizName = result[i].quiz.quizName;

                    $("#ongoingQuizzes").append(`<tr class="clickable-row${i}" data-hfref="CreateQuiz.html">
                        <td onclick="initScoreboard('${result[i].id}')">${quizName}</td>
                    </tr>
                    `)
                }
            } else {
                quizId = null;
                $("#liveScoreboard").html("");
                $("#ongoingQuizzes").html('<tr>' +
                    '<th>Quizname</th>' +
                    '</tr>' );
            }
        })
    }
});

function initScoreboard(id) {
    quizId = id;
    seeScoreboard();
    clearInterval(scoreboardInterval);
    scoreboardInterval = setInterval(seeScoreboard, 1000);
}
function seeScoreboard() {
    if(quizId == null) return;
    $.get("rest/quiz/scoreboard/"+quizId, function (result) {
        $("#liveScoreboard").html("");
        $("#liveScoreboard").append(`<tr>
            <th>Nickname</th>
            <th>Points</th>
            </tr>`);
        for (var i = 0; i < result.length; i++) {
            $("#liveScoreboard").append(`
           <tr>
            <td>${result[i].nickname}</td>
            <td>${result[i].points}</td>
           </tr>
            `)

        }
    });
}

function joinNickname(id) {
    var person = prompt("Please enter your nickname:", "");
    if (person == null || person == "") {
        txt = "User cancelled the prompt.";
    } else {

        $.post("rest/quiz/join/"+id+"/"+person)
        window.location.href = "TakeQuiz.html?id="+id+"&nickname="+person;
    }
};