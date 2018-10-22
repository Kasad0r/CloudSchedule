<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title></title>

    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css'>
    <script src="//cdnjs.cloudflare.com/ajax/libs/p2.js/0.6.0/p2.min.js"></script>
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

        #status_label {
            position: absolute;
            top: 768px;
            width: 768px;
            color: black;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-size: 1.5em;
            text-align: center;
            pointer-events: none;
        }

        #drawing_canvas {
            position: absolute;
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
                    <footer class="blockquote-footer">Говорят что рассписание у нас рандомится. <cite
                            title="Source Title">Иногда мы сами в это верим...</cite>
                    </footer>
                    <canvas id="drawing_canvas" class="mb-5"></canvas>
                    <div id="status_label">loading...</div>
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
<script>
    const TWO_PI = Math.PI * 2;
    const HALF_PI = Math.PI * 0.5;
    // canvas settings
    var viewWidth = 768,
            viewHeight = 768,
            viewCenterX = viewWidth * 0.5,
            viewCenterY = viewHeight * 0.5,
            drawingCanvas = document.getElementById("drawing_canvas"),
            ctx,
            timeStep = (1 / 60),
            time = 0;

    var ppm = 24, // pixels per meter
            physicsWidth = viewWidth / ppm,
            physicsHeight = viewHeight / ppm,
            physicsCenterX = physicsWidth * 0.5,
            physicsCenterY = physicsHeight * 0.5;

    var world;

    var wheel,
            arrow,
            mouseBody,
            mouseConstraint;

    var arrowMaterial,
            pinMaterial,
            contactMaterial;

    var wheelSpinning = false,
            wheelStopped = true;

    var particles = [];

    var statusLabel = document.getElementById('status_label');

    window.onload = function () {
        initDrawingCanvas();
        initPhysics();

        requestAnimationFrame(loop);

        statusLabel.innerHTML = 'Кто-то хочет 2 пары Марцыновской подряд?!';
    };

    function initDrawingCanvas() {
        drawingCanvas.width = viewWidth;
        drawingCanvas.height = viewHeight;
        ctx = drawingCanvas.getContext('2d');

        drawingCanvas.addEventListener('mousemove', updateMouseBodyPosition);
        drawingCanvas.addEventListener('mousedown', checkStartDrag);
        drawingCanvas.addEventListener('mouseup', checkEndDrag);
        drawingCanvas.addEventListener('mouseout', checkEndDrag);
    }

    function updateMouseBodyPosition(e) {
        var p = getPhysicsCoord(e);
        mouseBody.position[0] = p.x;
        mouseBody.position[1] = p.y;
    }

    function checkStartDrag(e) {
        if (world.hitTest(mouseBody.position, [wheel.body])[0]) {

            mouseConstraint = new p2.RevoluteConstraint(mouseBody, wheel.body, {
                worldPivot: mouseBody.position,
                collideConnected: false
            });

            world.addConstraint(mouseConstraint);
        }

        if (wheelSpinning === true) {
            wheelSpinning = false;
            wheelStopped = true;
            statusLabel.innerHTML = "Физ-ра на 4 часа , клэсик.";
        }
    }

    function checkEndDrag(e) {
        if (mouseConstraint) {
            world.removeConstraint(mouseConstraint);
            mouseConstraint = null;

            if (wheelSpinning === false && wheelStopped === true) {
                if (Math.abs(wheel.body.angularVelocity) > 7.5) {
                    wheelSpinning = true;
                    wheelStopped = false;
                    console.log('good spin');
                    statusLabel.innerHTML = 'Крути, крути ...'
                }
                else {
                    console.log('sissy');
                    statusLabel.innerHTML = 'Крути сильнее...'
                }
            }
        }
    }

    function getPhysicsCoord(e) {
        var rect = drawingCanvas.getBoundingClientRect(),
                x = (e.clientX - rect.left) / ppm,
                y = physicsHeight - (e.clientY - rect.top) / ppm;

        return {x: x, y: y};
    }

    function initPhysics() {
        world = new p2.World();
        world.solver.iterations = 100;
        world.solver.tolerance = 0;

        arrowMaterial = new p2.Material();
        pinMaterial = new p2.Material();
        contactMaterial = new p2.ContactMaterial(arrowMaterial, pinMaterial, {
            friction: 0.0,
            restitution: 0.1
        });
        world.addContactMaterial(contactMaterial);

        var wheelRadius = 8,
                wheelX = physicsCenterX,
                wheelY = wheelRadius + 4,
                arrowX = wheelX,
                arrowY = wheelY + wheelRadius + 0.625;

        wheel = new Wheel(wheelX, wheelY, wheelRadius, 32, 0.25, 7.5);
        wheel.body.angle = (Math.PI / 32.5);
        wheel.body.angularVelocity = 5;
        arrow = new Arrow(arrowX, arrowY, 0.5, 1.5);
        mouseBody = new p2.Body();

        world.addBody(mouseBody);
    }

    function spawnPartices() {
        for (var i = 0; i < 200; i++) {
            var p0 = new Point(viewCenterX, viewCenterY - 64);
            var p1 = new Point(viewCenterX, 0);
            var p2 = new Point(Math.random() * viewWidth, Math.random() * viewCenterY);
            var p3 = new Point(Math.random() * viewWidth, viewHeight + 64);

            particles.push(new Particle(p0, p1, p2, p3));
        }
    }

    function update() {
        particles.forEach(function (p) {
            p.update();
            if (p.complete) {
                particles.splice(particles.indexOf(p), 1);
            }
        });

        // p2 does not support continuous collision detection :(
        // but stepping twice seems to help
        // considering there are only a few bodies, this is ok for now.
        world.step(timeStep * 0.5);
        world.step(timeStep * 0.5);

        world.step(timeStep * 0.5);
        world.step(timeStep * 0.5);

        var phrases = ['jopa',
            'pizdec',
            'tlen',
            'uninie',
            'nahui ti suda postupil, uebok a?',
            'segodnya na fizru na 10 chasov, potom matan na 9 vechera, udachi brat',
            'opyat` paru probuhaesh, daun blya'
        ];
        randomBlya = Math.floor(Math.random() * 7);

        if (wheelSpinning === true && wheelStopped === false &&
                wheel.body.angularVelocity < 1 && arrow.hasStopped()) {

            var win = wheel.gotLucky();

            wheelStopped = true;
            wheelSpinning = false;

            wheel.body.angularVelocity = 0;

            if (win) {
                spawnPartices();
                statusLabel.innerHTML = 'Пар нет братан, отдыхай!'
            }
            else {
                statusLabel.innerHTML = phrases[Math.floor(Math.random() * phrases.length)];
            }
        }
    }

    function draw() {
        // ctx.fillStyle = '#fff';
        ctx.clearRect(0, 0, viewWidth, viewHeight);

        wheel.draw();
        arrow.draw();

        particles.forEach(function (p) {
            p.draw();
        });
    }

    function loop() {
        update();
        draw();

        requestAnimationFrame(loop);
    }

    /////////////////////////////
    // wheel of fortune
    /////////////////////////////
    function Wheel(x, y, radius, segments, pinRadius, pinDistance) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.segments = segments;
        this.pinRadius = pinRadius;
        this.pinDistance = pinDistance;

        this.pX = this.x * ppm;
        this.pY = (physicsHeight - this.y) * ppm;
        this.pRadius = this.radius * ppm;
        this.pPinRadius = this.pinRadius * ppm;
        this.pPinPositions = [];

        this.deltaPI = TWO_PI / this.segments;

        this.createBody();
        this.createPins();
    }

    Wheel.prototype = {
        createBody: function () {
            this.body = new p2.Body({mass: 1, position: [this.x, this.y]});
            this.body.angularDamping = 0.0;
            this.body.addShape(new p2.Circle(this.radius));
            this.body.shapes[0].sensor = true; //TODO use collision bits instead

            var axis = new p2.Body({position: [this.x, this.y]});
            var constraint = new p2.LockConstraint(this.body, axis);
            constraint.collideConnected = false;

            world.addBody(this.body);
            world.addBody(axis);
            world.addConstraint(constraint);
        },
        createPins: function () {
            var l = this.segments,
                    pin = new p2.Circle(this.pinRadius);

            pin.material = pinMaterial;

            for (var i = 0; i < l; i++) {
                var x = Math.cos(i / l * TWO_PI) * this.pinDistance,
                        y = Math.sin(i / l * TWO_PI) * this.pinDistance;

                this.body.addShape(pin, [x, y]);
                this.pPinPositions[i] = [x * ppm, -y * ppm];
            }
        },
        gotLucky: function () {
            var currentRotation = wheel.body.angle % TWO_PI,
                    currentSegment = Math.floor(currentRotation / this.deltaPI);

            return (currentSegment % 2 === 0);
        },
        draw: function () {
            // TODO this should be cached in a canvas, and drawn as an image
            // also, more doodads
            ctx.save();
            ctx.translate(this.pX, this.pY);

            ctx.beginPath();
            ctx.fillStyle = '#1B5E20';
            ctx.arc(0, 0, this.pRadius + 24, 0, TWO_PI);
            ctx.fill();
            ctx.fillRect(-12, 0, 24, 400);

            ctx.rotate(-this.body.angle);

            for (var i = 0; i < this.segments; i++) {
                ctx.fillStyle = (i % 2 === 0) ? '#4CAF50' : '#2E7D32';
                ctx.beginPath();
                ctx.arc(0, 0, this.pRadius, i * this.deltaPI, (i + 1) * this.deltaPI);
                ctx.lineTo(0, 0);
                ctx.closePath();
                ctx.fill();
            }

            ctx.fillStyle = '#E8F5E9';

            this.pPinPositions.forEach(function (p) {
                ctx.beginPath();
                ctx.arc(p[0], p[1], this.pPinRadius, 0, TWO_PI);
                ctx.fill();
            }, this);

            ctx.restore();
        }
    };
    /////////////////////////////
    // arrow on top of the wheel of fortune
    /////////////////////////////
    function Arrow(x, y, w, h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.verts = [];

        this.pX = this.x * ppm;
        this.pY = (physicsHeight - this.y) * ppm;
        this.pVerts = [];

        this.createBody();
    }

    Arrow.prototype = {
        createBody: function () {
            this.body = new p2.Body({mass: 1, position: [this.x, this.y]});
            this.body.addShape(this.createArrowShape());

            var axis = new p2.Body({position: [this.x, this.y]});
            var constraint = new p2.RevoluteConstraint(this.body, axis, {
                worldPivot: [this.x, this.y]
            });
            constraint.collideConnected = false;

            var left = new p2.Body({position: [this.x - 2, this.y]});
            var right = new p2.Body({position: [this.x + 2, this.y]});
            var leftConstraint = new p2.DistanceConstraint(this.body, left, {
                localAnchorA: [-this.w * 2, this.h * 0.25],
                collideConnected: false
            });
            var rightConstraint = new p2.DistanceConstraint(this.body, right, {
                localAnchorA: [this.w * 2, this.h * 0.25],
                collideConnected: false
            });
            var s = 32,
                    r = 4;

            leftConstraint.setStiffness(s);
            leftConstraint.setRelaxation(r);
            rightConstraint.setStiffness(s);
            rightConstraint.setRelaxation(r);

            world.addBody(this.body);
            world.addBody(axis);
            world.addConstraint(constraint);
            world.addConstraint(leftConstraint);
            world.addConstraint(rightConstraint);
        },

        createArrowShape: function () {
            this.verts[0] = [0, this.h * 0.25];
            this.verts[1] = [-this.w * 0.5, 0];
            this.verts[2] = [0, -this.h * 0.75];
            this.verts[3] = [this.w * 0.5, 0];

            this.pVerts[0] = [this.verts[0][0] * ppm, -this.verts[0][1] * ppm];
            this.pVerts[1] = [this.verts[1][0] * ppm, -this.verts[1][1] * ppm];
            this.pVerts[2] = [this.verts[2][0] * ppm, -this.verts[2][1] * ppm];
            this.pVerts[3] = [this.verts[3][0] * ppm, -this.verts[3][1] * ppm];

            var shape = new p2.Convex(this.verts);
            shape.material = arrowMaterial;

            return shape;
        },
        hasStopped: function () {
            var angle = Math.abs(this.body.angle % TWO_PI);

            return (angle < 1e-3 || (TWO_PI - angle) < 1e-3);
        },
        update: function () {

        },
        draw: function () {
            ctx.save();
            ctx.translate(this.pX, this.pY);
            ctx.rotate(-this.body.angle);

            ctx.fillStyle = '#401911';

            ctx.beginPath();
            ctx.moveTo(this.pVerts[0][0], this.pVerts[0][1]);
            ctx.lineTo(this.pVerts[1][0], this.pVerts[1][1]);
            ctx.lineTo(this.pVerts[2][0], this.pVerts[2][1]);
            ctx.lineTo(this.pVerts[3][0], this.pVerts[3][1]);
            ctx.closePath();
            ctx.fill();

            ctx.restore();
        }
    };
    /////////////////////////////
    // your reward
    /////////////////////////////
    Particle = function (p0, p1, p2, p3) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;

        this.time = 0;
        this.duration = 3 + Math.random() * 2;
        this.color = 'hsl(' + Math.floor(Math.random() * 360) + ',100%,50%)';

        this.w = 10;
        this.h = 7;

        this.complete = false;
    };
    Particle.prototype = {
        update: function () {
            this.time = Math.min(this.duration, this.time + timeStep);

            var f = Ease.outCubic(this.time, 0, 1, this.duration);
            var p = cubeBezier(this.p0, this.p1, this.p2, this.p3, f);

            var dx = p.x - this.x;
            var dy = p.y - this.y;

            this.r = Math.atan2(dy, dx) + HALF_PI;
            this.sy = Math.sin(Math.PI * f * 10);
            this.x = p.x;
            this.y = p.y;

            this.complete = this.time === this.duration;
        },
        draw: function () {
            ctx.save();
            ctx.translate(this.x, this.y);
            ctx.rotate(this.r);
            ctx.scale(1, this.sy);

            ctx.fillStyle = this.color;
            ctx.fillRect(-this.w * 0.5, -this.h * 0.5, this.w, this.h);

            ctx.restore();
        }
    };
    Point = function (x, y) {
        this.x = x || 0;
        this.y = y || 0;
    };
    /////////////////////////////
    // math
    /////////////////////////////
    /**
     * easing equations from http://gizma.com/easing/
     * t = current time
     * b = start value
     * c = delta value
     * d = duration
     */
    var Ease = {
        inCubic: function (t, b, c, d) {
            t /= d;
            return c * t * t * t + b;
        },
        outCubic: function (t, b, c, d) {
            t /= d;
            t--;
            return c * (t * t * t + 1) + b;
        },
        inOutCubic: function (t, b, c, d) {
            t /= d / 2;
            if (t < 1) return c / 2 * t * t * t + b;
            t -= 2;
            return c / 2 * (t * t * t + 2) + b;
        },
        inBack: function (t, b, c, d, s) {
            s = s || 1.70158;
            return c * (t /= d) * t * ((s + 1) * t - s) + b;
        }
    };

    function cubeBezier(p0, c0, c1, p1, t) {
        var p = new Point();
        var nt = (1 - t);

        p.x = nt * nt * nt * p0.x + 3 * nt * nt * t * c0.x + 3 * nt * t * t * c1.x + t * t * t * p1.x;
        p.y = nt * nt * nt * p0.y + 3 * nt * nt * t * c0.y + 3 * nt * t * t * c1.y + t * t * t * p1.y;

        return p;
    }
</script>
</body>
</html>
