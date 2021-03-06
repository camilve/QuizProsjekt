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
                $("#findQuizzes").html("");
                $("#futureQuiz").append(`
                    <tr>
                        <th style="width: 25%">Quizname </th>
                        <th style="width: 25%">Appropriate for</th>
                        <th style="width: 20%">Start time</th>
                        <th style="width: 10%">Number of questions</th>
                        <th style="width: 10%">Participants</th>
                        <th style="width: 10%">Click to join a quiz</th>                       
                    </tr>`);

                quizFuture = result.length;


                for (var i = 0; i < result.length; i++) {
                    quizName = result[i].quiz.quizName;
                    startTime = result[i].quiz.tid;
                    quizId = result[i].id;


                    $("#futureQuiz").append(`<tr class="clickable-row${i}" data-hfref="CreateQuiz.html">
                        <td>${quizName}</td>
                        <td>${result[i].quiz.ages}</td>
                        <td>${startTime}</td>
                        <td>${result[i].quiz.questions.length}</td>
                        <td>${result[i].quizzers.length}</td>
                        <td><a onclick="joinNickname('${quizId}')" href="#" id="joinQuizNickname">Join</a></td>                       
                    </tr>
                    `);
                }
            }
            if(result.length ==0) {
                $("#findQuizzes").append(`<div>
                    There are no future quizzes, <a href="CreateQuiz.html">create</a> one </div>
                    `);
            }
        });
    }
    getFutureQuizs();
    //sets interval, so the table is updated
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

//opens a live scoreboard, which update every second
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

//adds a nickname to a quiz
function joinNickname(id) {
    var person = prompt("Please enter your nickname:", "");
    if (person == null || person == "") {
        txt = "User cancelled the prompt.";
    } else {
        $.post("rest/quiz/join/"+id+"/"+person);
        window.location.href = "TakeQuiz.html?id="+id+"&nickname="+person;
    }
};