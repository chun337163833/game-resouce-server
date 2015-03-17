<#import "/tags/util.ftl" as util>
<!DOCTYPE html>
<html>
<head>
    <title></title>
<script type="text/javascript">

    var stompClient = null;

    function websocketCall() {
        var val = document.getElementById('text').value;
        stompClient.send("/app/notify", {}, '{"value":"'+val+'"}');
    }

	function httpCall() {
	var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
		    }
		  }
        var val = document.getElementById('text').value;
        xmlhttp.open("GET","<@util.url "/stomp/send" />?message="+val,true);
		xmlhttp.send();
    }

    function connect() {
    	var bc = document.getElementById('connect');
    	var bd = document.getElementById('disconnect');
    	bd.style.display = 'block';
    	bc.style.display = 'none';
    	document.getElementById('response').innerHTML = "";
        var socket = new SockJS('<@util.url "/stomp/notify" "" />');
        //"ws://10.233.69.166:8080/atlant-taxi/stomp/notify"
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/push', function(message) {
            var json = JSON.parse(message.body)
            	showResult(json)
            	console.log(json.result)
            });
        });
    }

    function disconnect() {
    	var bc = document.getElementById('connect');
    	var bd = document.getElementById('disconnect');
    	bc.style.display = 'block';
    	bd.style.display = 'none';
        stompClient.disconnect();
        console.log("Disconnected");
    }

    function showResult(message) {
        var response = document.getElementById('response');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message.result));
        response.appendChild(p);
    }

</script>
</head>
<body>
<div>
    <div>
        <button id="connect" onclick="connect();">Connect</button>
        <button id="disconnect" style="display:none" onclick="disconnect();">Disconnect</button><br/><br/>
    </div>
    <div id="calculationDiv">
        <input type="text" id="text" />
        <button id="sendWs" onclick="websocketCall();">Send via websocket</button>
        <button id="sendHttp" onclick="httpCall();">Send via http</button>
        <div id="response"></div>
    </div>
    <audio controls>
	  <source src="<@util.url "/audio/asi_chill_20.mp3" />" type="audio/mpeg">
	</audio>
</div>
</body>
</html> 