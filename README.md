# SpringDuino
Spring MVC raw application with Arduino Service


Using this tool you can interface Arduino (or compatible boards) with Spring framework and REST services.
This project can be used for:
<ul>
<li>Building small domotics and IoT control panel</li>
<li>Interfacing robotics application with Java Spring</li>
</ul>

Download available on <a href="https://github.com/emanuelepaiano/SpringDuino">Github repository</a>

<h2 class="section-heading">How does it work?</h2>
Connection is based on a Java arduino serial monitor (<a href="https://github.com/emanuelepaiano/serialduino">SerialDuino</a>).


<h1 class="section-heading">Web access credentials:</h1>
<ul>
<li>Username: root - Password: password</li>
<li>Username: admin - Password: password</li>
<li>Username: guest - Password: password</li>
</ul>

You can change login settings into src/main/webapp/WEB-INF/context-security.xml
 

<h2 class="section-heading">License</h2>
<a href="http://www.apache.org/licenses/LICENSE-2.0">Apache 2.0</a>
