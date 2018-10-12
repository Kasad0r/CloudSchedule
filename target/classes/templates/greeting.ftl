<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title></title>

    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css'>
    <style>
        body {
            background: white;
        }

        section {
            width: 100%;
            height: 100vh;
            padding: 3em;
            display: block;
        }

        .active {
            background: white;
            text: black;
        }

        .dropdown-item:active {
            background-color: forestgreen;
        }

        .dropdown-item.active {
            background-color: forestgreen;
        }
    </style>

</head>
<body data-spy="scroll" data-target=".navbar">

<div class="container-fluid">
    <div class="row no-gutters">
        <div class="col-sm-4 col-md-4 col-lg-3 col-xl-1 " style="background: #2E7D32">
            <nav class="outline: none navbar navbar-light  navbar-expand-sm px-0 sticky-top flex-sm-column">
                <a class="navbar-brand text-white text-center border-right border-top" href="#top"><h1>Cloud</h1>
                    <h2>Schedule</h2></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarmenu">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="navbar-collapse collapse" id="navbarmenu">
                    <ul class="nav flex-column" role="tablist">
                        <#list courses as course>
                            <li class="nav-item dropdown ">
                                <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdownMenuLink"
                                   role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                   style="background: #2E7D32">
                                    <h3>${course.courseName}</h3>
                                </a>
                                <div class="dropdown-menu aria-labelledby=navbarDropdownMenuLink text-dark">
                                    <#list course.groupList as group>
                                        <a class="dropdown-item" href="#${group.groupName}">${group.groupName}</a>
                                    </#list>
                                </div>
                            </li>
                        </#list>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="col-sm-11">
            <section id="top">
                <blockquote class="blockquote text-center">
                    <p class="mb-0">
                    <h2>Онлайн рассписание колледжа КИСИТ КНЕУ.</h2></p>
                    <footer class="blockquote-footer">Говорят у нас есть рандомайзер рассписания. Иногда он выдает странные результаты .<cite title="Source Title">Правда это или нет...</cite>
                    </footer>
                </blockquote>
            </section>
            <#list courses as course>
                <#list course.groupList as group>
                <section id="${group.groupName}">
                    <h1 class="text-center card-header bg-success text-white">${group.groupName}</h1>
                    <div class="alert alert-success" role="alert">
                        <h5>Last update: ${group.lastUpdate}</h5>
                    </div>
                    <div class="card-group">
                  <#list group.weekSchedule as day>
                      <div class="card text-white mb-3" style="background:#2E7D32 ">
                          <h3 class="card-header text-center border-bottom">${day.dayName}</h3>
                          <div class="card-body">
                                  <#list day.lessons as lesson>
                                      <h5 class="card-subtitle text-white text-center my-1 font-italic">
                                          ${lesson.startTime!}</h5>
                                      <h5 class="card-title text-center border-bottom my-1">
                                          <span style="word-spacing: 1em">${lesson.upperWeek.name!} ${lesson.upperWeek.teacher!}</span>
                                      </h5>
                                      <h5 class="card-title text-center mt-1">
                                          <span style="word-spacing: 1em">${lesson.downWeek.name!} ${lesson.downWeek.teacher!}</span>
                                      </h5>
                                  </#list>
                          </div>
                      </div>
                  </#list>
                    </div>
                </section>
                </#list>
            </#list>
        </div>
    </div>
</div>
<blockquote class="blockquote text-center">
    <p class="mb-0">Cloud Schedule</p>
    <footer class="blockquote-footer">@Copyright<cite title="Source Title"> by SSUnion 2018</cite></footer>
</blockquote>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js'></script>
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js'></script>
<script>
    $(document).ready(function () {
        console.log("ready!");
        $(".navbar a[href^='#']").on('click', function (e) {
            // store hash
            var hash = (this.hash === '#top') ? 'body' : this.hash;
            var $el = $(hash);
            if ($el.length > 0) {
                // prevent default anchor click behavior
                e.preventDefault();
                // animate
                $('html, body').animate({
                    scrollTop: $el.offset().top
                }, 500, function () {
                    // when done, add hash to url
                    // (default click behaviour)
                    window.location.hash = hash;
                });
            }
        });
    });
</script>
</body>
</html>
